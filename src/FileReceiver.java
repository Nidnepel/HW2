import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileReceiver {
    final private List<File> files;

    FileReceiver(File dir) {
        files = new ArrayList<>();
        receiveFiles(dir);
    }

    private void receiveFiles(File dir) {
        try {
            for (var i : Objects.requireNonNull(dir.listFiles())) {
                if (i.isFile()) {
                    files.add(i);
                }
                if (i.isDirectory()) {
                    receiveFiles(i);
                }
            }
        } catch (SecurityException ex) {
            System.out.println("Cannot get access to get list files in directory or check for type");
        } catch (NullPointerException ex) {
            System.out.println("Directory reference is null");
        }
    }

    public List<File> getFiles() {
        return files;
    }
}