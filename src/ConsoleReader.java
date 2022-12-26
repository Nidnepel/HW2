import java.io.File;
import java.util.Scanner;

public class ConsoleReader extends DataReader { // TODO: Возможно вынести в пакет

    ConsoleReader() {
        startDir = read();
    }

    private File read() {
        var in = new Scanner(System.in);
        String input = in.nextLine();
        var dir = new File(input);
        while (!dir.isDirectory()) {
            System.out.println("This string is not correct path!\n Please input a correct path");
            input = in.nextLine();
            dir = new File(input);
        }
        return dir;
    }
}
