import java.io.File;
import java.io.FileReader;

public class TextReader {
    public String getTextFromFile(File file) {
        StringBuilder text = new StringBuilder();
        try (var reader = new FileReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {
                text.append(((char) c));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.printf("for file.txt : %s\n", file.getPath());
        }
        return text.toString();
    }
}
