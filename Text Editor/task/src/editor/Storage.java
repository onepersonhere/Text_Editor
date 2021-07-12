package editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private static String filepath = "C:\\Users\\wh\\IdeaProjects\\Text Editor\\Text Editor\\task\\src\\editor\\res\\";
    //private static String filepath = "";
    public static void saveToFile(String filename){
        try {
            createFile(filepath+filename);
            FileWriter myWriter = new FileWriter(filepath+filename);
            System.out.println("Saved:\n"+TextPane.getTextArea().getText());
            myWriter.write(TextPane.getTextArea().getText());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void loadFromFile(String filename){
        try {
            String data = Files.readString(Paths.get(filepath + filename));
            System.out.println("Loaded:\n" + data);
            TextPane.getTextArea().setText(data);
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createFile(String filename){
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
