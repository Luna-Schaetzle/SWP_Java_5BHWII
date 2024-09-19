import java.io.File;

public class Main {

    public static void main(String[] args) {
        File folder = new File("C:/path/to/your/folder");
        listFiles(folder);
    }

    public static void listFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file);
                } else {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }
    
}
