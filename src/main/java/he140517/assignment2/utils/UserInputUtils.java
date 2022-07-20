package he140517.assignment2.utils;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author dattdhe140517
 */
public class UserInputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntAndCheck(String msg, int min, int max) {
        int result = 0;
        String errMsg = "Error: Only accept input is integer, in range of " + min + " and " + max;
        String tmp;

        System.out.print(msg);

        while (true) {
            tmp = scanner.nextLine();

            if (tmp.strip().equals("exit")) {
                result = -1;
                break;
            }

            try {
                result = Integer.parseInt(tmp);

                if (result < min || result > max) {
                    throw new NumberFormatException(errMsg);
                }

                break;
            } catch (NumberFormatException ex) {
                System.err.println(errMsg);
                System.out.print("Try again (if not, type `exit`): ");
            }
        }

        return result;
    }

    public static String getAndCheckExistFilePath(String msg) {
        boolean doesExist = false;
        String filePath = "";
        File tmpFile;

        System.out.print(msg);

        while (!doesExist) {
            filePath = scanner.nextLine();
            if (filePath == "back") {
                return filePath;
            }

            tmpFile = new File(filePath);
            doesExist = tmpFile.exists();
            if (!doesExist) {
                System.out.println("File (" + filePath + ") does NOT exist. Please try again (type `back` if want to back to menu)");
                System.out.print("File path: ");
            }
        }

        return filePath;
    }

    public static String getString(String msg) {
        System.out.print(msg);
        String content = scanner.nextLine();
        return content;
    }
}
