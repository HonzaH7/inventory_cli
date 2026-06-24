package inventory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Chemical NaOH = new Chemical("NaOH", "002394", Category.BASE, 1);
        Chemical HCl = new Chemical("HCl", "012394", Category.ACID, 5);
        Chemical NaCl = new Chemical("NaCl", "102394", Category.SALT, 3);

        List<Chemical> listOfChemicals = new ArrayList<>();
        listOfChemicals.add(NaOH);
        listOfChemicals.add(HCl);
        listOfChemicals.add(NaCl);
    }
}
