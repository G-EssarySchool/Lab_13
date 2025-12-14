import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<String> items = new ArrayList<>();

    private static boolean fileLoaded = false;

    private static boolean fileEdited = false;

    private static boolean fileSaved = false;

    private static String currentFilename = null;

    public static void main(String[] args) throws IOException {

        boolean quit = false;

        while (!quit) {

            printMenu();

            String choice = InputHelper.getRegExString(
                    scan,
                    "Choose an option: ",
                    "[AaDdPpQqOoSsCc]"
            ).toUpperCase();

            switch (choice) {
                case "A":
                    addItem();
                    break;

                case "D":
                    deleteItem();
                    break;

                case "P":
                    printList();
                    break;

                case "O":
                    openFile();
                    break;

                case "S":
                    saveFile();
                    break;

                case "C":
                    clearList();
                    break;

                case "Q":
                    quit = quitProgram();
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("A - Add Item");
        System.out.println("D - Delete Item");
        System.out.println("P - Print List");
        System.out.println("O - Open List File");
        System.out.println("S - Save List File");
        System.out.println("C - Clear List");
        System.out.println("Q - Quit Program");
    }

    private static void addItem() {
        String item = InputHelper.getNonZeroLengthString(scan, "Enter item to add: ");
        items.add(item);
        fileEdited = true;
        fileSaved = false;
        System.out.println("Item added.");
    }

    private static void deleteItem() {
        if (items.isEmpty()) {
            System.out.println("List is empty — nothing to delete.");
            return;
        }

        printList();

        int index = InputHelper.getRangedInt(
                scan,
                "Enter index to delete (0 - " + (items.size() - 1) + "): ",
                0,
                items.size() - 1
        );

        System.out.println("Removed: " + items.remove(index));
        fileEdited = true;
        fileSaved = false;
    }

    private static void printList() {
        if (items.isEmpty()) {
            System.out.println("[ List is empty ]");
            return;
        }

        System.out.println("\nCurrent List:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + " - " + items.get(i));
        }
    }

    private static void clearList() {

        if (items.isEmpty()) {
            System.out.println("List is already empty.");
            return;
        }

        String confirm = InputHelper.getRegExString(
                scan,
                "Clear entire list? [Y/N]: ",
                "[YyNn]"
        );

        if (confirm.equalsIgnoreCase("Y")) {
            items.clear();
            fileEdited = true;
            fileSaved = false;
            fileLoaded = false;
            currentFilename = null;
            System.out.println("List cleared.");
        }
    }

    private static void openFile() throws IOException {

        if (fileEdited) {
            String saveFirst = InputHelper.getRegExString(
                    scan,
                    "You have unsaved changes. Save first? [Y/N]: ",
                    "[YyNn]"
            );

            if (saveFirst.equalsIgnoreCase("Y")) {
                saveFile();
            }
        }

        items.clear();
        currentFilename = IOHelper.openFile(items);

        fileLoaded = true;
        fileEdited = false;
        fileSaved = true;

        System.out.println("Loaded file: " + currentFilename);
    }

    private static void saveFile() throws IOException {

        if (currentFilename == null) {
            JFileChooser chooser = new JFileChooser();
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                currentFilename = file.getName();

                if (!currentFilename.endsWith(".txt")) {
                    currentFilename += ".txt";
                }
            } else {
                System.out.println("Save cancelled.");
                return;
            }
        }

        IOHelper.writeFile(items, currentFilename);
        fileSaved = true;
        fileEdited = false;
        fileLoaded = true;
    }

    private static boolean quitProgram() throws IOException {

        if (fileEdited) {
            String saveFirst = InputHelper.getRegExString(
                    scan,
                    "You have unsaved changes. Save before quitting? [Y/N]: ",
                    "[YyNn]"
            );

            if (saveFirst.equalsIgnoreCase("Y")) {
                saveFile();
            }
        }

        String confirm = InputHelper.getRegExString(
                scan,
                "Are you sure you want to quit? [Y/N]: ",
                "[YyNn]"
        );

        return confirm.equalsIgnoreCase("Y");
    }
}
