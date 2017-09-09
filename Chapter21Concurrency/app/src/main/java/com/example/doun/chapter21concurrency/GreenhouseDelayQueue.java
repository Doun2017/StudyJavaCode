//: concurrency/GreenhouseDelayQueue.java
// Rewriting innerclasses/GreenhouseController.java
// to use a ScheduledThreadPoolExecutor.
// {Args: 5000}
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.util.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

class SchedulerDelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    protected DelayQueue<SchedulerDelayedTask> queue;

    public int getDelta() {
        return delta;
    }

    private final int delta;    //希腊语字母表第四字母δ; （河流的）三角洲; [数]变量增量;
    private final long trigger; // 触发器  即触发时间
    protected static List<SchedulerDelayedTask> sequence = new ArrayList<>();

    public SchedulerDelayedTask(int delayInMilliseconds, DelayQueue<SchedulerDelayedTask> e) {
        delta = delayInMilliseconds;
        queue = e;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert( trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed arg) {
        SchedulerDelayedTask that = (SchedulerDelayedTask) arg;
        if (trigger < that.trigger) return -1;
        if (trigger > that.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        //DelayedTask的任务就是打印一下自己
        System.out.print(this + " ");
    }

    @Override
    public String toString() {
//        return String.format("[%1$-4d]", delta) + " Task " + id;
        return String.format(Locale.ENGLISH, "[%1$-4d]", delta) + " Task " + id;
    }

    public String summary() { //概要
        return "(" + id + ":" + delta + ")";
    }

}


class SchedulerDelayedTaskConsumer implements Runnable {
    private DelayQueue<SchedulerDelayedTask> q;

    public SchedulerDelayedTaskConsumer(DelayQueue<SchedulerDelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                q.take().run(); // Run task with the current thread
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        System.out.println("Finished SchedulerDelayedTaskConsumer");
    }
}
public class GreenhouseDelayQueue {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        thermostat = value;
    }

    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn extends SchedulerDelayedTask {
        public LightOn(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            // Put hardware control code here to
            // physically turn on the light.
            System.out.println("Turning on lights");
            light = true;
            queue.add(new LightOn(getDelta(), queue));
        }
    }

    class LightOff extends SchedulerDelayedTask {
        public LightOff(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            // Put hardware control code here to
            // physically turn off the light.
            System.out.println("Turning off lights");
            light = false;
            queue.add(new LightOff(getDelta(), queue));
        }
    }

    class WaterOn extends SchedulerDelayedTask {
        public WaterOn(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            // Put hardware control code here.
            System.out.println("Turning greenhouse water on");
            water = true;
            queue.add(new WaterOn(getDelta(), queue));
        }
    }

    class WaterOff extends SchedulerDelayedTask {
        public WaterOff(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            // Put hardware control code here.
            System.out.println("Turning greenhouse water off");
            water = false;
            queue.add(new WaterOff(getDelta(), queue));
        }
    }

    class ThermostatNight extends SchedulerDelayedTask {
        public ThermostatNight(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            // Put hardware control code here.
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
            queue.add(new ThermostatNight(getDelta(), queue));
        }
    }

    class ThermostatDay extends SchedulerDelayedTask {
        public ThermostatDay(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            // Put hardware control code here.
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
            queue.add(new ThermostatDay(getDelta(), queue));
        }
    }

    class Bell extends SchedulerDelayedTask {
        public Bell(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            System.out.println("Bing!");
            queue.add(new Bell(getDelta(), queue));
        }
    }

    //EndSentinel:结尾的哨兵
    class EndSentinel extends SchedulerDelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e) {
            super(delay, null);
            exec = e;
        }

        @Override
        public void run() {
            for (SchedulerDelayedTask pt : sequence) {
                System.out.print(pt.summary() + " ");
            }

            for (DataPoint d : data)
                System.out.println(d);
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }

    // New feature: data collection
    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        public String toString() {
            return time.getTime() + String.format(Locale.ENGLISH, " temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }

    private Calendar lastTime = Calendar.getInstance();

    { // Adjust date to the half hour
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

    class CollectData extends SchedulerDelayedTask {
        public CollectData(int delay, DelayQueue<SchedulerDelayedTask> e) {
            super(delay, e);
        }
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseDelayQueue.this) {
                // Pretend the interval is longer than it is:
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                // One in 5 chances of reversing the direction:
                if (rand.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                // Store previous value:
                lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
                if (rand.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity + humidityDirection * rand.nextFloat();
                // Calendar must be cloned, otherwise all
                // DataPoints hold references to the same lastTime.
                // For a basic object like Calendar, clone() is OK.
                data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
            }
            queue.add(new CollectData(getDelta(), queue));

        }
    }

    public static void main(String[] args) {

        GreenhouseDelayQueue gh = new GreenhouseDelayQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<SchedulerDelayedTask> queue = new DelayQueue<>();
        // Fill with tasks that have random delays:
        queue.put(gh.new Bell(1000, queue));
        queue.put(gh.new ThermostatNight(2000, queue));
        queue.put(gh.new LightOn(200, queue));
        queue.put(gh.new LightOff(400, queue));
        queue.put(gh.new WaterOn(600, queue));
        queue.put(gh.new WaterOff(800, queue));
        queue.put(gh.new ThermostatDay(1400, queue));
        queue.put(gh.new CollectData(500, queue));
        // Set the stopping point
        queue.add(gh.new EndSentinel(5000, exec));
        exec.execute(new SchedulerDelayedTaskConsumer(queue));




//        GreenhouseDelayQueue gh = new GreenhouseDelayQueue();
//        gh.schedule(gh.new Terminate(), 5000);
//        // Former "Restart" class not necessary:
//        gh.repeat(gh.new Bell(), 0, 1000);
//        gh.repeat(gh.new ThermostatNight(), 0, 2000);
//        gh.repeat(gh.new LightOn(), 0, 200);
//        gh.repeat(gh.new LightOff(), 0, 400);
//        gh.repeat(gh.new WaterOn(), 0, 600);
//        gh.repeat(gh.new WaterOff(), 0, 800);
//        gh.repeat(gh.new ThermostatDay(), 0, 1400);
//        gh.repeat(gh.new CollectData(), 500, 500);
    }
} /* (Execute to see output) *///:~
