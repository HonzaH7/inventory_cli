package inventory;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Chemical NaOH = new Chemical("NaOH", "012394", Category.BASE, 1);
        Chemical HCl = new Chemical("HCl", "002394", Category.ACID, 5);
        Chemical NaCl = new Chemical("NaCl", "102394", Category.SALT, 3);

        List<Chemical> listOfChemicals = new ArrayList<>();
        listOfChemicals.add(NaOH);
        listOfChemicals.add(HCl);
        listOfChemicals.add(NaCl);
        listOfChemicals.add(NaCl);
        listOfChemicals.sort(Comparator.comparing(Chemical::getCategory).thenComparing(Chemical::getName));
        listOfChemicals.sort(null);
        System.out.println(listOfChemicals.size());
        for (Chemical ch : listOfChemicals) {
            System.out.println(ch);
        }
        System.out.println(" ----------------------------- ");
        Set<Chemical> setOfChemicals = new HashSet<>();
        setOfChemicals.add(NaOH);
        setOfChemicals.add(HCl);
        setOfChemicals.add(NaCl);
        setOfChemicals.add(new Chemical("NaCl", "0802394", Category.SALT, 3));
        System.out.println(setOfChemicals.size());
        for (Chemical ch : setOfChemicals) {
            System.out.println(ch);
        }
        System.out.println(" ----------------------------- ");
        Map<String, Chemical> treeOfChemicals = new TreeMap<>();
        treeOfChemicals.put(HCl.getId(), HCl);
        treeOfChemicals.put(NaCl.getId(), NaCl);
        treeOfChemicals.put(NaOH.getId(), NaOH);
        for (Chemical ch : treeOfChemicals.values()) {
            System.out.println(ch);
        }
    }
}
