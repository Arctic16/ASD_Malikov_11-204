import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSaver {

    private static final Path dataPath = Paths.get("src/" +
            "data.txt");

    private static final String VALUES_SEPARATOR = "; ";

    private static final int SETS_COUNT = 10000;

    public static void renderData() throws IOException {

        PrintWriter printWriter = new PrintWriter(dataPath.toFile());
        DataGenerator dataGenerator = new DataGenerator();

        int[] data = dataGenerator.generateIntValues(-10_000_000, 10_000_000, 10000);
        for (int value : data) {
            printWriter.print(value + VALUES_SEPARATOR);
            }
            printWriter.println();
            printWriter.close();
        }



    public static void main(String[] args) {
        try {
            renderData();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to open file", exception);
        } finally {
            System.out.println("Data was successfully saved to file");
        }
    }
}