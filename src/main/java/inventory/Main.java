package inventory;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Chemical NaOH = new Chemical("NaOH", "012394", Category.BASE, 1);
        Chemical HCl = new Chemical("HCl", "002394", Category.ACID, 5);
        Chemical NaCl = new Chemical("NaCl", "102394", Category.SALT, 3);

        Repository<String, Chemical> repo = new InMemoryRepository<>();
        repo.save(NaOH.getId(), NaOH);
        repo.save(HCl.getId(), HCl);
        repo.save(NaCl.getId(), NaCl);

        Chemical found = repo.findById("012394");
        System.out.println(found);
        System.out.println(repo.findById("idk?"));
        System.out.println(repo.findAll());
        repo.deleteById("002394");
        System.out.println(repo.findAll());


    }
}
