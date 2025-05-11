package college.learnCollege2.MiniShell;
import java.util.Scanner;
public class MiniShell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShellCommandHandler handler = new ShellCommandHandler();

        System.out.println("Welcome to MiniShell! Type 'help' for a list of commands.");

        while (true) {
            System.out.print(handler.getCurrentDirectory().getAbsolutePath() + " > ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : null;
            if(command.equals("pwd")) {
                handler.printWorkingDirectory();
            }
            if(command.equals("ls")) {
                handler.ListDirectory();
            }
            if(command.equals("cd")) {
                handler.changeDirectory(argument);
            }
            if(command.equals("mkdir")) {
                handler.makeDirectory(argument);
            }
            if(command.equals("touch")) {
                handler.createFile(argument);
            }
            if(command.equals("help")) {
                handler.PrintHelp();
            }
            if (command.equals("exit")) {
                System.out.println("Goodbye!");
                return;
            }
        }
    }
}
