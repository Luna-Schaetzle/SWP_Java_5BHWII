import java.io.File;
import java.util.Scanner;

public class Re_fi_se_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name you are looking for: ");
        String fileName = scanner.nextLine();
        System.out.println("Enter the path to the folder you want to start the search in: ");
        String filePath = scanner.nextLine();
        String foundFilePath = searchFile(fileName, filePath);
        if (foundFilePath != null) {
            System.out.println("File found at: " + foundFilePath);
        } else {
            System.out.println("File not found.");
        }
    }

    public static String searchFile(String fileName, String filePath) {
        File folder = new File(filePath);
        System.out.println(filePath);
        // Überprüfen, ob das Verzeichnis existiert und ob es ein Verzeichnis ist
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid directory: " + filePath);
            return null;
        }

        File[] files = folder.listFiles();

        // Überprüfen, ob das Verzeichnis Dateien enthält
        if (files == null) {
            System.out.println("No files found in the directory or access denied: " + filePath);
            return null;
        }

        // Alle Dateien/Verzeichnisse durchlaufen
        for (File file : files) {
            // Falls die Datei gefunden wurde, Rückgabe des absoluten Pfads
            if (file.getName().equalsIgnoreCase(fileName)) {
                return file.getAbsolutePath();
            }
        }   

        // Wenn die Datei nicht im aktuellen Verzeichnis gefunden wurde, Unterverzeichnisse durchsuchen
        for (File file : files) {
            if (file.isDirectory()) {
                // Rekursiver Aufruf, um in Unterverzeichnissen zu suchen
                String foundFilePath = searchFile(fileName, file.getAbsolutePath());
                if (foundFilePath != null) {
                    return foundFilePath;
                }
            }
        }

        // Datei wurde nicht gefunden
        return null;
    }
}
