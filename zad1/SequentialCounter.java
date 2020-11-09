import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class SequentialCounter {

    public static void linesCounter(String[] args){

        for (String path : args) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    Stream<String> lines = lines = Files.lines(Path.of(file.getPath()));
                    System.out.println("Number of lines in file '" + file
                            + "' " + lines.count());
                }catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(args) + " " + args.length);

        long startTime = System.nanoTime();
        linesCounter(args);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Estimated time: "+ estimatedTime + "ns");
    }
}
