
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Shell {

    Scanner scanner = new Scanner(System.in);
    String startDir;
    File currentDir;

    public Shell(String rootDir) {
        this.currentDir = new File(rootDir);
        this.startDir = currentDir.getAbsolutePath();
        if (!this.currentDir.isDirectory()) {
            System.out.println("Invalid directory: " + rootDir);
            System.exit(1);
        }

        while (true) {
            System.out.print("$ ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String Prompt = scanner.nextLine().trim();
            String[] CmdList = Prompt.split(" ");
            String Command = CmdList[0];
            ArrayList<String> Args = new ArrayList<String>(Arrays.asList(CmdList));
            Args.remove(0);

            try {
                switch (Command) {
                    case "ls": {
                        File openedDir = new File(currentDir.getAbsolutePath());
                        for (File file : openedDir.listFiles()) {
                            System.out.printf("  %-20s %.1f KB%s",
                                    file.getName() + (file.isDirectory() ? "/" : ""),
                                    file.length() / 1024.0, System.lineSeparator());
                        }
                        break;
                    }
                    case "cd": {
                        if (Args.isEmpty()) {
                            Args.add(this.startDir);
                        }
                        String targetPath = Args.get(0);
                        File targetDir = new File(currentDir, targetPath);
                        if (!targetDir.exists()) {
                            System.err.println("No such directory: " + targetPath);
                        } else if (!targetDir.isDirectory()) {
                            System.err.println("Not a directory: " + targetPath);
                        } else {
                            currentDir = targetDir.getCanonicalFile();
                        }
                        break;
                    }
                    case "pwd": {
                        System.out.println(currentDir.getAbsolutePath());
                        break;
                    }
                    case "mv": {
                        boolean moved = false;
                        if (Args.size() != 2) {
                            System.err.println("Usage: mv <source-file> <destination>");
                            break;
                        }
                        File SourceFile = new File(currentDir, Args.get(0));
                        File DestFile = new File(currentDir, Args.get(1));

                        if (!SourceFile.exists()) {
                            System.err.println("No such file or dir: " + SourceFile.getName());
                            break;
                        }
                        if (SourceFile.isDirectory()) {
                            System.err.println("Source must be a file: " + SourceFile.getName());
                            break;
                        }
                        if (DestFile.isDirectory()) {
                            moved = SourceFile.renameTo(new File(DestFile, SourceFile.getName()));
                        } else {
                            moved = SourceFile.renameTo(DestFile);
                        }
                        System.out.println(moved ? "Move successful." : "Move failed.");
                        break;
                    }
                    case "exit": {
                        System.out.println("Exiting shell.");
                        System.exit(0);
                        break;
                    }

                    default:
                        System.out.println("Unknown command: `" + Command + "'");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
