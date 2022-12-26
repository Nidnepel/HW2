import java.io.File;

public abstract class DataReader {
    File startDir; //TODO: как сделать приватным?

    public File getStartDir() {
        return startDir;
    }
}
