import java.io.IOException;

class MyRunnablee implements Runnable {
    final String nazwa;

    public MyRunnablee(String nazwa1) {
        this.nazwa = nazwa1;
    }

    @Override
    public void run() {
        System.out.println(this.nazwa);
    }
}

public class test {


    public static void main(String[] args) throws IOException {
        String nazwa = "nazwapliku";

        Thread t1 = new Thread(new MyRunnablee(nazwa));
        t1.start();
        Thread t2 = new Thread(new MyRunnablee(nazwa));
        t2.start();
        Thread t3 = new Thread(new MyRunnablee(nazwa));
        t3.start();
    }
}
