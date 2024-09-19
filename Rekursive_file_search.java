import java.io.File;
import java.util.Scanner;


public class Rekursive_file_search {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name you are looking for: ");
        String fileName = scanner.nextLine();
        System.out.println("Enter the path to the folder you want to start to search in: ");
        String filePath = scanner.nextLine();
        String foundfilePath = searchFile(fileName, filePath);


    }

    public static String searchFile(String fileName, String filePath){
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        if (files != null) {
            for(File file : files){
                if(file.isDirectory()){
                    searchFile(fileName, file.getAbsolutePath());
                }else{
                    if(file.getName().equals(fileName)){
                        System.out.println("File found at: " + file.getAbsolutePath());
                        return file.getAbsolutePath();
                    }
                }
            }
        }
        return null;
    }

}
