//: innerclasses/GreenhouseController.java
// Configure and execute the greenhouse system.
// {Args: 5000}
package com.wsn.chapter18javaio;

import com.wsn.chapter18javaio.controller.*;

public class GreenhouseController {
    public static void main(String[] args) {
//        GreenhouseControls gc = new GreenhouseControls();
//        // Instead of hard-wiring, you could parse
//        // configuration information from a text file here:
//        gc.addEvent(gc.new Bell(900));
//        Event[] eventList = {gc.new ThermostatNight(0),
//                gc.new LightOn(200),
//                gc.new LightOff(400),
//                gc.new WaterOn(600),
//                gc.new WaterOff(800),
//                gc.new ThermostatDay(1400)};
//        gc.addEvent(gc.new Restart(2000, eventList));
//        if (args.length == 1)
//            gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
//        gc.run();

        //读取配置文件
        Practise10 p = new Practise10();
        try{
            p.read("events.txt");
        }catch (Exception e){
            e.printStackTrace();
            System.out.print(e.toString() + "\n");
        }

        //按照配置文件进行事件
        GreenhouseControls gc = new GreenhouseControls();
        while (p.list.size()>0){
            System.out.print(p.list.getFirst() + "\n");
            String s[] = p.list.getFirst().split(" ");
            switch (s[0]){
                case "Bell":
                    gc.addEvent(gc.new Bell(Integer.valueOf(s[1])));
                case "LightOn":
                    gc.addEvent(gc.new LightOn(Integer.valueOf(s[1])));
                case "LightOff":
                    gc.addEvent(gc.new LightOff(Integer.valueOf(s[1])));
                case "WaterOn":
                    gc.addEvent(gc.new WaterOn(Integer.valueOf(s[1])));
                case "WaterOff":
                    gc.addEvent(gc.new WaterOff(Integer.valueOf(s[1])));
                case "ThermostatDay":
                    gc.addEvent(gc.new ThermostatDay(Integer.valueOf(s[1])));
                case "ThermostatNight":
                    gc.addEvent(gc.new ThermostatNight(Integer.valueOf(s[1])));
                case "Terminate":
                    gc.addEvent(new GreenhouseControls.Terminate(new Integer(s[1])));
            }
            p.list.removeFirst();
        }

        System.out.print("\n");

        gc.run();

    }
} /* Output: Bing!
 Thermostat on night setting Light is on Light is off Greenhouse water is on Greenhouse water is off
 Thermostat on day setting Restarting system Terminating *//*:~*/