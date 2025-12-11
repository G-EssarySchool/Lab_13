import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> items = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        boolean loaded;
        boolean edited;
        boolean saved;

        items.add("Kanye West");
        items.add("JID");
        items.add("Malcolm Todd");
        items.add("Mac DeMarco");
        items.add("Zach Templar");
        items.add("J. Cole");
        items.add("Travis Scott");
        items.add("Tyler The Creator");
        items.add("Drake");
        items.add("Future");

        boolean quit = false;
        while (!quit) {

            printList();

            System.out.println("\nMENU OPTIONS:");
            System.out.println("A – Add an item to the list");
            System.out.println("D – Delete an item from the list");
            System.out.println("P – Print the list");
            System.out.println("Q – Quit the program");

            String choice = InputHelper.getRegExString(scan, "Choose an option: ", "[AaDdPpQq]");

            switch (choice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit = quitProgram();
                    break;
            }
        }
    }

    public static void addItem() {
        String newItem = InputHelper.getNonZeroLengthString(scan, "Enter an item to add: ");
        items.add(newItem);
        System.out.println("Item added!\n");
    }

    public static void deleteItem() {
        if (items.isEmpty()) {
            System.out.println("List is empty—nothing to delete.\n");
            return;
        }
        int indexToRemove = InputHelper.getRangedInt(scan, "Enter index of item to delete (0 - " + (items.size() - 1) + "): ", 0, items.size() - 1);
        System.out.println("Removed: " + items.remove(indexToRemove));
    }

    public static void printList() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + " - " + items.get(i));
        }
    }

    public static boolean quitProgram() {
        String confirm = InputHelper.getRegExString(scan, "Are you sure you want to quit? [Y/N]: ", "[YyNn]");
        return confirm.equalsIgnoreCase("Y");
    }
}