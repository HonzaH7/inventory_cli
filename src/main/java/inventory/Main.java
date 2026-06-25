package inventory;

import inventory.util.ChemicalNotFoundException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Chemical NaOH = new Chemical("NaOH", "012394", Category.BASE, 1, EnumSet.of(Hazard.CORROSIVE, Hazard.ETCHING));
        Chemical HCl = new Chemical("HCl", "002394", Category.ACID, 5, EnumSet.of(Hazard.ETCHING, Hazard.EYEBURNS, Hazard.CORROSIVE));
        Chemical H2SO4 = new Chemical("H2SO4", "025594", Category.ACID, 2, EnumSet.of(Hazard.ETCHING, Hazard.EYEBURNS, Hazard.CORROSIVE));
        Chemical NaCl = new Chemical("NaCl", "102394", Category.SALT, 3, EnumSet.noneOf(Hazard.class));

        Repository<String, Chemical> repo = new InMemoryRepository<>();
        repo.save(NaOH.getId(), NaOH);
        repo.save(HCl.getId(), HCl);
        repo.save(H2SO4.getId(), H2SO4);
        repo.save(NaCl.getId(), NaCl);

        Chemical mustExist = repo.findById("012394")
                        .orElseThrow(() -> new ChemicalNotFoundException("Chemical 012394 not found"));
        System.out.println("Found: " + mustExist.getName());

        //Chemical missing = repo.findById("nenene").orElseThrow(() -> new ChemicalNotFoundException("Chemical nenene not found"));


        repo.findById("012394").ifPresent(System.out::println);
        repo.findById("idk?").ifPresentOrElse(System.out::println, () -> System.out.println("Not found"));
        System.out.println(repo.findAll());
        //repo.deleteById("002394");
        System.out.println(repo.findAll());
        System.out.println(describe(new CorrosivesCabinet(true)));
        System.out.println(describe(new StandardShelf("Lab A", 3)));

        System.out.println("--------------------------------------------");
        Predicate<Chemical> isConcentrated = c -> c.getConcentration() > 4;
        System.out.println("Predicate: " + isConcentrated.test(NaCl));

        System.out.println("--------------------------------------------");
        Function<Chemical, String> name = s -> s.getName();
        System.out.println("Function: " + name.apply(NaOH));

        System.out.println("--------------------------------------------");
        Consumer<Chemical> printer = c -> System.out.println("Consumer: " + c.getHazards());
        printer.accept(NaOH);

        System.out.println("--------------------------------------------");
        Supplier<Chemical> greeter = () -> NaOH;
        System.out.println("Supplier: " + greeter.get());

        System.out.println("--------------------------------------------");

        List<String> filteredChemicalsByConcentration = repo.findAll().stream()
                .filter(c -> c.getConcentration() > 2)
                .map(c -> c.getName())
                .toList();

        System.out.println("Streams: " + filteredChemicalsByConcentration);
        Map<Category, List<Chemical>> byCategory = repo.findAll().stream()
                .collect(Collectors.groupingBy(c -> c.getCategory()));
        System.out.println("GroupingBy: " + byCategory);
        Map<Category, Long> countByCategory = repo.findAll().stream()
                .collect(Collectors.groupingBy(c -> c.getCategory(), Collectors.counting()));
        System.out.println("GroupingBy: " + countByCategory);
    }

    static String describe(StorageLocation loc) {
        return switch (loc) {
            case CorrosivesCabinet c ->
                    "Corrosives cabinet (" + (c.ventilated() ? "ventilated" : "not ventilated") + ")";
            case StandardShelf s ->
                    "Standard shelf in " + s.room() + ", position " + s.position();
        };
    }
}
