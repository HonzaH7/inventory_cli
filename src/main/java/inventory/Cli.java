package inventory;

import inventory.util.InvalidChemicalException;
import inventory.util.InventoryException;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.Set;

public class Cli {
    private final Repository<String, Chemical> repo = new InMemoryRepository<>();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("""
            Welcome to our chemical inventory
            Press numbers to choose an action."""
        );

        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1" -> add();
                    case "2" -> withdraw();
                    case "3" -> listAll();
                    case "4" -> find();
                    case "5" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Unknown action: " + choice);
                }
            } catch (IllegalArgumentException | InventoryException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }





    private void find() {
        System.out.println("Enter ID: ");
        String id = scanner.nextLine();
        repo.findById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println(id + " not found")
        );
    }

    private void listAll() {
        repo.findAll().forEach(System.out::println);
    }

    private void withdraw() {
        System.out.println("Enter ID: ");
        String id = scanner.nextLine();
        repo.findById(id).ifPresentOrElse(
                c -> {
                    repo.deleteById(id);
                    System.out.println("Withdrew: " + c.getName());
                },
                () -> System.out.println(id + " not found")
        );
    }

    private void add() {
        System.out.println("Enter Chemical formula: ");
        String name = scanner.nextLine();
        System.out.println("Enter ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter category (acid/base/salt): ");
        String input = scanner.nextLine();
        Category category;
        try {
            category = Category.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidChemicalException("Unknown category: " + input, e);
        }
        System.out.println("Enter concentration: ");
        double concentration = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter properties (etching, corrosive, eyeburns) or empty: ");
        Set<Hazard> hazards = EnumSet.noneOf(Hazard.class);
        for (String part : scanner.nextLine().split(",")) {
            String token = part.trim().toUpperCase();
            if (!token.isBlank()) {
                hazards.add(Hazard.valueOf(token));
            }
        }
        Chemical c = new Chemical(name, id, category, concentration, hazards);
        repo.save(id, c);
        System.out.println("Chemical " + c.getName() + " successfully saved.");
    }

    private void printMenu() {
        System.out.println("""
            1. Add a chemical
            2. Withdraw a chemical
            3. List all chemicals
            4. Find a chemical by an ID
            5. Exit"""
        );
        System.out.print("> ");
    }
}
