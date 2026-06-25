package inventory;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Chemical NaOH = new Chemical("NaOH", "012394", Category.BASE, 1, EnumSet.of(Hazard.CORROSIVE, Hazard.ETCHING));
        Chemical HCl = new Chemical("HCl", "002394", Category.ACID, 5, EnumSet.of(Hazard.ETCHING, Hazard.EYEBURNS, Hazard.CORROSIVE));
        Chemical NaCl = new Chemical("NaCl", "102394", Category.SALT, 3, EnumSet.noneOf(Hazard.class));

        Repository<String, Chemical> repo = new InMemoryRepository<>();
        repo.save(NaOH.getId(), NaOH);
        repo.save(HCl.getId(), HCl);
        repo.save(NaCl.getId(), NaCl);

        System.out.println(repo.findById("012394"));
        System.out.println(repo.findById("idk?"));
        System.out.println(repo.findAll());
        repo.deleteById("002394");
        System.out.println(repo.findAll());
        System.out.println(describe(new CorrosivesCabinet(true)));
        System.out.println(describe(new StandardShelf("Lab A", 3)));


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
