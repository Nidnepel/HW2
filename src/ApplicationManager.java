public class ApplicationManager {
    public static void start() {
        DataReader reader = new ConsoleReader();
        System.out.println(reader.getStartDir().getPath());
    }
}
