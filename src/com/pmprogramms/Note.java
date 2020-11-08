package com.pmprogramms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Scanner;

public class Note {
    public Note() {
        clearScreen();
        runNote();
    }

    private void runNote() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        String menu = String.format("""
                =========================================================
                                        NOTE V 0.0.3
                                 Create by PatrykMCoder
                        last update at %s
                                            MENU
                =========================================================
                 1. Create
                 2. Open
                 3. Exit
                """, new Date(System.currentTimeMillis()));
        while (!exit) {
            System.out.println(menu);
            try {
                switch (Integer.parseInt(scanner.next())) {
                    case 1: {
                        clearScreen();
                        Scanner scannerFile = new Scanner(System.in);
                        System.out.println("Enter path where you want save file");
                        String path = scannerFile.nextLine();
                        System.out.println("Enter file name with your extension");
                        String name = scannerFile.nextLine();
                        File file = new File(path + "/" + name);
                        if (!file.exists() && file.createNewFile()) {
                            String note = scannerFile.nextLine();

                            FileWriter fileWriter = new FileWriter(file);
                            fileWriter.write(note);
                            fileWriter.close();
                        } else
                            System.out.println("FILE NOT CREATE. try again");
                        break;
                    }
                    case 2: {
                        clearScreen();
                        Scanner scannerFile = new Scanner(System.in);
                        System.out.println("Enter path and name in one");
                        String pathAndName = scannerFile.nextLine();
                        File file = new File(pathAndName);
                        if (file.exists()) {
                            String note = Files.readString(Path.of(file.getPath()));
                            System.out.println(note);
                            System.out.println("Do you want write? Y/N");
                            String answer = scannerFile.nextLine().toUpperCase();
                            clearScreen();
                            switch (answer) {
                                case "Y" -> {
                                    System.out.print(note);
                                    String noteWrite = note + scannerFile.nextLine();
                                    FileWriter fileWriter = new FileWriter(file);
                                    fileWriter.write(noteWrite);
                                    fileWriter.close();
                                }
                                case "N" -> {
                                }
                            }

                        } else
                            System.out.println("FILE NOT EXIST. try again");
                        break;

                    }
                    case 3: {
                        exit = true;
                        break;
                    }
                    default:
                        break;
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
