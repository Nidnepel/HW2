import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A static class responsible for securely reading the root directory path from the console.
 */
public class ConsoleReader {
    /**
     * Reads the path to the root directory from the console and returns an object of type file
     *
     * @return File type variable built on the root directory.
     */
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

    /**
     * Safe read data
     *
     * @param in - Scanner for read data from console
     * @return input string
     */
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

    /**
     * Check access and correct input directory.
     *
     * @param directory - intended directory
     * @return Is entered directory correct and available
     */
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
