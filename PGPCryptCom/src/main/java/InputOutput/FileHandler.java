/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InputOutput;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author adipu
 */
public class FileHandler implements IFileHandler{

    boolean checkFolder(String ApplicationRoot) {
        File f = new File(ApplicationRoot);
        boolean ok = f.isDirectory();
        return ok;
    }

    boolean checkFile(String PathForLogFile) {
        File f = new File(PathForLogFile);
        boolean ok = f.isFile();
        return ok;
    }

    void createDirectory(String Path) {
        new File(Path).mkdirs();
    }

    public void createFile(String path, String name) {
        try {
            File file = new File(path + "\\" + name);
            if (file.createNewFile()) {
                //log file created
            } else {
                // log file exists
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            //log
        }
    }

    public void writeToFile(String path, String name, String text) {
        try {
            FileWriter writer = new FileWriter(path + "\\" + name);
            writer.write(text);
            writer.close();
            //log write success
        } catch (IOException e) {
            //log
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public String readFromFile(String path, String name)
    {
        String text = "";
        try 
        {
            Path fileName = Path.of(path + "\\" + name);
            text = Files.readString(fileName);
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
        }
        return text;
    }
    

    List<String> getFilesInDirectory(String FolderPath) {
        final File folder = new File(FolderPath);
        List<String> list = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            list.add(fileEntry.getName());
        }
        return list;
    }
    
    
}
