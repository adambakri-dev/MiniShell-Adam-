package college.learnCollege2.MiniShell;

import java.io.File;
import java.io.IOException;

public class ShellCommandHandler {

    // Tip: This variable holds the directory the user is currently "inside"
    private File currentDirectory;

    // Constructor: Sets the current directory to the folder where the program was started
    public ShellCommandHandler() {
        this.currentDirectory = new File(System.getProperty("user.dir")); // Tip: System property "user.dir" gives the starting working directory
    }

    // Prints the full path of the current directory
    public void printWorkingDirectory() {
        System.out.println(currentDirectory.getAbsolutePath());
    }

    // Lists files and directories in the current directory
    public void ListDirectory() {
        File[] files = currentDirectory.listFiles(); // Tip: listFiles() returns an array of File objects in the directory
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    System.out.println(" [Dir] : " + files[i].getName()); // Tip: getName() gives just the name, not the full path
                } else {
                    System.out.println(" [File] : " + files[i].getName());
                }
            }
        } else {
            System.out.println("Unable to access directory contents.");
        }
    }

    // Changes to a different directory
    public void changeDirectory(String name) {
        if (name == null) {
            System.out.println("Usage: cd [directory_name]");
            return;
        }

        if (name.equals("..")) {
            // Tip: ".." means go up to the parent directory
            File parent = currentDirectory.getParentFile();
            if (parent != null) {
                currentDirectory = parent;
            } else {
                System.out.println("Already at the root directory.");
            }
            return; // Important: Stop here to avoid checking next block
        }

        File newDir = new File(currentDirectory, name); // Tip: This builds a path like currentDirectory/name
        if (newDir.exists() && newDir.isDirectory()) {
            currentDirectory = newDir;
        } else {
            System.out.println("Directory not found: " + name);
        }
    }

    // Creates a new folder
    public void makeDirectory(String name) {
        if (name == null) {
            System.out.println("Usage: mkdir [directory_name]");
            return;
        }

        File newDir = new File(currentDirectory, name);
        if (newDir.exists()) {
            System.out.println("Directory already exists.");
        } else {
            boolean created = newDir.mkdir(); // Tip: mkdir() returns true if creation succeeded
            if (created) {
                System.out.println("Directory created: " + name);
            } else {
                System.out.println("Failed to create directory.");
            }
        }
    }

    // Creates a new file in the current directory
    public void createFile(String name) {
        if (name == null) {
            System.out.println("Usage: touch [file_name]");
            return;
        }

        File newFile = new File(currentDirectory, name);
        if (newFile.exists()) {
            System.out.println("File already exists.");
        } else {
            try {
                boolean created = newFile.createNewFile(); // Tip: createNewFile() throws IOException if there’s a problem
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

    // Shows all supported commands
    public void PrintHelp() {
        System.out.println("Available commands:");
        System.out.println("  pwd    =>   Print current working directory");
        System.out.println("  ls     =>   List files and directories");
        System.out.println("  cd     =>   Change current directory");
        System.out.println("  mkdir  =>   Create a new directory");
        System.out.println("  touch  =>   Create a new file");
        System.out.println("  help   =>   Show this help message");
        System.out.println("  exit   =>   Exit the shell");
    }

    // Returns the current working directory (used by MiniShell)
    public File getCurrentDirectory() {
        return currentDirectory;
    }
}
