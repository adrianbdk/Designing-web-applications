import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Stream;

class MyRunnable implements Runnable {
    private final File file;

    public MyRunnable(File MyFile){
        this.file = MyFile;
    }

    @Override
    public void run() {
        try (Stream<String> lines = Files.lines(Path.of(file.getPath()))){
            System.out.println("Number of lines in file '" + file + "' ->" + lines.count() + "<- "
            + Thread.currentThread().getName());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

class ParallelCounter {

    public static void linesCounter(String[] args){

        ExecutorService poolExecutor = new ThreadPoolExecutor(args.length, args.length*2,
                0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (String path : args) {
            File file = new File(path);
            if (file.exists()) {
                poolExecutor.execute(new MyRunnable(file));
            }
        }

        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(args) + " " + args.length);

        long startTime = System.nanoTime();
        linesCounter(args);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Estimated time: " + estimatedTime + "ns");

    }
}