package inventory.cli;

import inventory.repository.InMemoryRepository;

public class Main {
    public static void main(String[] args) {
        new Cli(new InMemoryRepository<>()).run();
    }
}