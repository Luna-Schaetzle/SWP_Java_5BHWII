import java.io.File;
import java.util.Scanner;

public class Re_fi_se_2 {

    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name you are looking for: ");
        String fileName = scanner.nextLine();
        System.out.println("Enter the path to the folder you want to start to search in: ");
        String filePath = scanner.nextLine();
        String foundfilePath = searchFile(fileName, filePath);
        System.out.println("File found at: " + foundfilePath);

    }

    public static String searchFile(String fileName, String filePath){
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        System.out.println(filePath);
        for(File file : files){
            System.out.println(file.getName());
        }
        if (files != null) {
            for(File file : files){
                if(file.getName().equals(fileName)){
                    //System.out.println("File found at: " + file.getAbsolutePath());
                    return file.getAbsolutePath();
                }
            }
            for(File file : files){
                if(file.isDirectory()){
                    filePath = filePath + "\\" + file.getName();
                    searchFile(fileName, filePath);
                }
                else{
                    System.out.println("File not found");
                }
            }
        }
        return null;
    }

    /*
    public static boolean searchdirectory(String fileName, String filePath){
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        System.out.println(filePath);
        if (files != null) {
            for(File file : files){
                    if(file.getName().equals(fileName)){
                        System.out.println("File found at: " + file.getAbsolutePath());
                        return true;
                    }
                    else if(file.isDirectory()){
                        filePath = filePath + "\\" + file.getName();
                        searchdirectory(fileName, filePath);
                    }
            
                }
            
        }
        return false;
    }
         */



}
