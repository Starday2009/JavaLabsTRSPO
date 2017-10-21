package Thread;
import java.text.DateFormat;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainThread {
    int i, b;

    Thread th = new Thread(new Runnable(){
        public void run(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите два числа");
            i = sc.nextInt();
            b = sc.nextInt();
            System.out.println(i+b);
        }
    });
    Thread th2 = new Thread(new Runnable(){
            public void run(){
            System.out.println("Вполняется работа второго потока");
        }
    });

    public static void main(String args[]) throws InterruptedException {
        MainThread m = new MainThread();
        m.th.start();
        m.th2.start();
        for(int i = 1; i < 60; i++) {
            System.out.println("Тест"+m.th2);
            TimeUnit.SECONDS.sleep(5);
        }

    }
}

