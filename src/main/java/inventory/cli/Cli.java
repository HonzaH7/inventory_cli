package inventory.cli;

import inventory.domain.Chemical;
import inventory.exception.InventoryException;
import inventory.repository.Repository;

import java.util.Scanner;

public class Cli {
    private final Repository<String, Chemical> repo;
    private final ChemicalParser parser = new ChemicalParser();
    private final Scanner scanner = new Scanner(System.in);

    public Cli(Repository<String, Chemical> repo) {
        this.repo = repo;
    }

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


    private void add() {
        String name = prompt("Enter Chemical formula: ");
        String id = prompt("Enter ID: ");
        String category = prompt("Enter category (acid/base/salt): ");
        String concentration = prompt("Enter concentration: ");
        String hazards = prompt("Enter properties (etching, corrosive, eyeburns) or empty: ");

        Chemical c = parser.fromInput(name, id, category, concentration, hazards);
        repo.save(c.getId(), c);
        System.out.println("Chemical " + c.getName() + " successfully saved.");
    }

    private void withdraw() {
        String id = prompt("Enter ID: ");
        repo.findById(id).ifPresentOrElse(
                c -> {
                    repo.deleteById(id);
                    System.out.println("Withdrew: " + c.getName());
                },
                () -> System.out.println(id + " not found")
        );
    }

    private void listAll() {
        repo.findAll().forEach(System.out::println);
    }

    private void find() {
        String id = prompt("Enter ID: ");
        repo.findById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println(id + " not found")
        );
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

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
