import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Re_fi_se_array {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Benutzer nach dem Dateinamen und dem Pfad fragen
        System.out.println("Enter the file name you are looking for: ");
        String fileName = scanner.nextLine();
        System.out.println("Enter the path to the folder you want to start the search in: ");
        String filePath = scanner.nextLine();

        // Datei suchen
        ArrayList<String> foundFilePath = new ArrayList<>();
        foundFilePath = searchFile(fileName, filePath, foundFilePath);

        // Gefundene Dateien ausgeben
        if (!foundFilePath.isEmpty()) {
            for (String path : foundFilePath) {
                System.out.println("File found at: " + path);
            }
        } else {
            System.out.println("File not found.");
        }
    }

    public static ArrayList<String> searchFile(String fileName, String filePath, ArrayList<String> foundFilePath) {
        File folder = new File(filePath);

        // Überprüfen, ob das Verzeichnis existiert und ob es sich um ein Verzeichnis handelt
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid directory: " + filePath);
            return foundFilePath;
        }

        File[] files = folder.listFiles();

        // Überprüfen, ob das Verzeichnis Dateien enthält
        if (files == null) {
            System.out.println("No files found in the directory or access denied: " + filePath);
            return foundFilePath;
        }

        // Durchlauf der Dateien im aktuellen Verzeichnis
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(fileName)) {
                foundFilePath.add(file.getAbsolutePath());
            }
        }

        // Durchlauf der Unterverzeichnisse
        for (File file : files) {
            if (file.isDirectory()) {
                // Rekursive Suche in Unterverzeichnissen
                searchFile(fileName, file.getAbsolutePath(), foundFilePath);
            }
        }

        return foundFilePath;
    }
}

