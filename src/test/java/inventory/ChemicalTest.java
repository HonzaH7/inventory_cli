package inventory;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChemicalTest {

    @Test
    void sameIdMeansOneElementInSet() {
        Chemical NaOH = new Chemical("NaOH", "002394", Category.BASE, 1);
        Chemical HCl = new Chemical("HCl", "002394", Category.ACID, 5);
        Set<Chemical> setOfChemicals = new HashSet<>();

        setOfChemicals.add(NaOH);
        setOfChemicals.add(HCl);
        assertEquals(1, setOfChemicals.size());
    }


}
