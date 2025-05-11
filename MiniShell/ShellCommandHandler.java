package college.learnCollege2.MiniShell;
import java.io.File;
import java.io.IOException;
public class ShellCommandHandler {
    private File currentDirectory;
    public ShellCommandHandler(){
        this.currentDirectory = new File(System.getProperty("user.dir"));
    }
    public void printWorkingDirectory(){
        System.out.println(currentDirectory.getAbsolutePath());
    }
    public void ListDirectory(){
        File[] files = currentDirectory.listFiles();
        if (files != null) {
            for (int i = 0 ; i< files.length ; i++) {
                if (files[i].isDirectory()) {
                    System.out.println(" [Dir] : " + files[i].getName());
                } else {
                    System.out.println(" [File] : " + files[i].getName());
                }
            }
        }
        else {
            System.out.println("Unable to access directory contents.");
        }
    }
    public void changeDirectory(String name){
        if (name == null) {
            System.out.println("Usage: cd [directory_name]");
            return;
        }
        if (name.equals("..")) {
            File parent = currentDirectory.getParentFile();
            if (parent != null) {
                currentDirectory = parent;
            } else {
                System.out.println("Already Exist.");
            }
        }
        File NewDir= new File(currentDirectory , name);
        if (NewDir.exists()) {
            currentDirectory = NewDir;
        } else {
            System.out.println("Directory not found: " + name);
        }
    }
    public void makeDirectory(String name){
        if (name == null) {
            System.out.println("Usage: mkdir [directory_name]");
        }
        File newDir = new File(currentDirectory, name);
        if (newDir.exists()) {
            System.out.println("Directory already exists.");
        } else {
            boolean created = newDir.mkdir();
            if (created) {
                System.out.println("Directory created: " + name);
            } else {
                System.out.println("Failed to create directory.");
            }
        }
    }
    public void createFile(String name){
        if (name == null) {
            System.out.println("Usage: touch [file_name]");
            return;
        }
        File NewFile = new File(currentDirectory, name);
        if (NewFile.exists()) {
            System.out.println("File already exists.");
        } else {
            try {
                boolean created = NewFile.createNewFile();
                if (created) {
                    System.out.println("File created: " + name);
                } else {
                    System.out.println("Failed to create file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }
    public void PrintHelp(){
        System.out.println("Available commands:");
        System.out.println("  pwd    =>   Print current working directory");
        System.out.println("  ls     =>   List files and directories");
        System.out.println("  cd     =>   Change current directory");
        System.out.println("  mkdir  =>   Create a new directory");
        System.out.println("  touch  =>   Create a new file");
        System.out.println("  help   =>   Show this help message");
        System.out.println("  exit   =>   Exit the shell");
    }
    public File getCurrentDirectory() {
        return currentDirectory;
    }
}
