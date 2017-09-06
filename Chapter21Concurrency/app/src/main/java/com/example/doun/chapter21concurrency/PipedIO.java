//: concurrency/PipedIO.java
// Using pipes for inter-task I/O
package com.example.doun.chapter21concurrency;

import java.util.concurrent.*;
import java.io.*;
import java.util.*;
//import static net.mindview.util.System.out.println.*;

class CharacterQueue extends LinkedBlockingQueue<Character> {
}
class Sender implements Runnable {
    private Random rand = new Random(47);
    private CharacterQueue characterQueue;
//    private PipedWriter out = new PipedWriter();

    public Sender(CharacterQueue characterQueue) {
        this.characterQueue = characterQueue;
    }

//    public PipedWriter getPipedWriter() {
//        return out;
//    }

    public void run() {
        try {
            while (true)
                for (char c = 'A'; c <= 'z'; c++) {
//                    out.write(c);
                    characterQueue.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
//        } catch (IOException e) {
//            System.out.println(e + " Sender write exception");
        } catch (InterruptedException e) {
            System.out.println(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;
    private CharacterQueue characterQueue;

    public Receiver(CharacterQueue characterQueue) {
        this.characterQueue = characterQueue;
    }


//    public Receiver(Sender sender) throws IOException {
//        in = new PipedReader(sender.getPipedWriter());
//    }

    public void run() {
        try {
            while (true) {
                // Blocks until characters are there:
//                System.out.print("Read: " + (char) in.read() + ", ");
                System.out.print("Read: " + characterQueue.take() + ", ");
            }
//        } catch (IOException e) {
//            System.out.println(e + " Receiver read exception");
        }catch (InterruptedException e) {
            System.out.println(e + " Receiver sleep interrupted");
        }
    }
}

public class PipedIO {
    public static void main(String[] args) throws Exception {
        CharacterQueue characterQueue = new CharacterQueue();
        Sender sender = new Sender(characterQueue);
        Receiver receiver = new Receiver(characterQueue);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
//        Sender sender = new Sender();
//        Receiver receiver = new Receiver(sender);
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(sender);
//        exec.execute(receiver);
//        TimeUnit.SECONDS.sleep(4);
//        exec.shutdownNow();
    }
} /* Output: (65% match)
Read: A, Read: B, Read: C, Read: D, Read: E, Read: F, Read: G, Read: H, Read: I, Read: J, Read: K, Read: L, Read: M, java.lang.InterruptedException: sleep interrupted Sender sleep interrupted
java.io.InterruptedIOException Receiver read exception
*///:~
