import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for get all files in a directory.
 */
public class FileReceiver {
    /**
     * List of all files in directory
     */
    final private List<File> files;

    /**
     * Constructor
     *
     * @param directory - processed directory
     */
    FileReceiver(File directory) {
        files = new ArrayList<>();
        receiveFiles(directory);
    }

    /**
     * recursive search for files
     *
     * @param dir - processed directory
     */
    private void receiveFiles(File dir) {
        try {
            for (var element : Objects.requireNonNull(dir.listFiles())) {
                if (element.isFile()) {
                    files.add(element);
                }
                if (element.isDirectory()) {
                    receiveFiles(element);
                }
            }
        } catch (SecurityException ex) {
            System.out.println("Cannot get access to get list files in directory or check for type");
        } catch (NullPointerException ex) {
            System.out.println("Directory reference is null");
        }
    }

    /**
     * Getter
     *
     * @return list of files
     */
    public List<File> getFiles() {
        return files;
    }
}