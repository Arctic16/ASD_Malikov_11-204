import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    private static final Path dataPath = Paths.get("src/" +
            "data.txt");

    private static final Path resultsPath1 = Paths.get("src/" +
            "resultInsert.txt");
    private static final Path resultsPath2 = Paths.get("src/" +
            "resultRemove.txt");
    private static final Path resultsPath3 = Paths.get("src/" +
            "resultContains.txt");

    private static final String VALUES_SEPARATOR = "; ";


    public static void getAATreeResults() throws IOException {
        PrintWriter printWriter1 = new PrintWriter(resultsPath1.toAbsolutePath().toFile());
        PrintWriter printWriter2 = new PrintWriter(resultsPath2.toAbsolutePath().toFile());
        PrintWriter printWriter3 = new PrintWriter(resultsPath3.toAbsolutePath().toFile());
        Scanner scanner = new Scanner(dataPath.toAbsolutePath().toFile());

        long end, start;
        AATree aa = new AATree();

            String line = scanner.nextLine();
            if (line.contains("Set")) line = scanner.nextLine();
            int[] data = Arrays
                    .stream(line.split(VALUES_SEPARATOR))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int i = 0; i < data.length; i++) {
                start = System.nanoTime();
                aa.insert(data[i]);
                end = System.nanoTime();
                printWriter1.println((end - start) +" - " + aa.getIterations());
                aa.resetIterations();
            }
        int[] datas = new int[100];
        for (int i = 0; i < 100; i++) {
            datas[i] = DataGenerator.generateInt(0, 9999);
        }
        for (int i = 0; i < datas.length; i++) {
            start = System.nanoTime();
            aa.remove(data[datas[i]]);
            end = System.nanoTime();
            printWriter2.println((end - start) + " - " + aa.getIterations());
            aa.resetIterations();
        }
        int[] dat = new int[1000];
        for (int i = 0; i < 1000; i++) {
            dat[i] = DataGenerator.generateInt(0, 9999);
        }
        for (int i = 0; i < dat.length; i++) {
            start = System.nanoTime();
            aa.contains(data[dat[i]]);
            end = System.nanoTime();
            printWriter3.println((end - start) + " - " + aa.getIterations());
            aa.resetIterations();
        }

        printWriter1.close();
        printWriter2.close();
        printWriter3.close();
        scanner.close();
    }


    public static void main(String[] args) {
        try {
            getAATreeResults();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to open file", exception);
        }
    }
}
