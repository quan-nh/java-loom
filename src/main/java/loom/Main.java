package loom;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        var c = new AtomicLong();

        for (var i = 0; i < 1_000_000; i++) {
            // regular thread
            // new Thread(() -> {
            // c.incrementAndGet();
            // }).start();
            Thread.builder().virtual().task(() -> {
                c.incrementAndGet();
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println(c.get());
    }
}
