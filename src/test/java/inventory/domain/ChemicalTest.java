package inventory.domain;

import inventory.exception.InvalidChemicalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChemicalTest {

    @DisplayName("Two chemicals with same id collapse to one in a HashSet")
    @Test
    void sameIdMeansOneElementInSet() {
        Chemical NaOH = new Chemical("NaOH", "002394", Category.BASE, 1, EnumSet.of(Hazard.CORROSIVE, Hazard.ETCHING));
        Chemical HCl = new Chemical("HCl", "002394", Category.ACID, 5, EnumSet.of(Hazard.ETCHING, Hazard.EYEBURNS, Hazard.CORROSIVE));
        Set<Chemical> setOfChemicals = new HashSet<>();

        setOfChemicals.add(NaOH);
        setOfChemicals.add(HCl);
        assertEquals(1, setOfChemicals.size());
    }

    @Test
    void nullIdThrows() {
        assertThrows(InvalidChemicalException.class, () -> new Chemical(
                "NaOH",
                null,
                Category.BASE,
                1,
                EnumSet.noneOf(Hazard.class)
        ));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.5, 100})
    void acceptsValidConcentration(double concentration) {
        assertDoesNotThrow(
                () -> new Chemical("NaOH", "002394", Category.BASE, concentration,
                        EnumSet.noneOf(Hazard.class)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -1})
    void rejectsNegativeConcentration(double concentration) {
        assertThrows(InvalidChemicalException.class,
                () -> new Chemical("NaOH", "002394", Category.BASE, concentration,
                        EnumSet.noneOf(Hazard.class)));
    }

    @Test
    void blankNameThrows() {
        assertThrows(InvalidChemicalException.class, () -> new Chemical("", "28282", Category.BASE, 1,
                EnumSet.noneOf(Hazard.class)));
    }

}
