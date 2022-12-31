import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader {
    public static File readRootDirectory() {
        var in = new Scanner(System.in);
        while (true) {
            String input = safeRead(in);
            var rootDirectory = new File(input);
            if (checkAccessAndCorrectInputDirectory(rootDirectory)) {
                return rootDirectory;
            }
        }
    }

    private static String safeRead(Scanner in) {
        String input = "";
        while (input.equals("")) {
            try {
                input = in.nextLine();
            } catch (NoSuchElementException ex) {
                System.out.println("Please input root directory path!");
            }
        }
        input = input.replace('/', File.pathSeparatorChar);
        return input;
    }

    private static boolean checkAccessAndCorrectInputDirectory(File directory) {
        boolean isCorrect = false;
        try {
            isCorrect = directory.isDirectory();
            if (!isCorrect) {
                System.out.println("This string is not correct path!\n Please input a correct path");
            }
        } catch (SecurityException ex) {
            System.out.println("Entered directory is not available!");
        }
        return isCorrect;
    }
}
