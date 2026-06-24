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
        System.out.println(Collections.max(repo.findAll()));
        System.out.println(max(repo.findAll()));

    }

    static <T extends Comparable<T>> T max(List<T> list) {
        T max = list.getFirst();
        for (T element : list) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}
