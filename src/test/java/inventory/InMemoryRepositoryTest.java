package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryRepositoryTest {

    private Repository<String, Chemical> repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryRepository<>();
        Chemical NaOH = new Chemical("NaOH", "012394", Category.BASE, 1, EnumSet.of(Hazard.CORROSIVE, Hazard.ETCHING, Hazard.EYEBURNS));
        Chemical HCl = new Chemical("HCl", "022394", Category.ACID, 1, EnumSet.of(Hazard.CORROSIVE, Hazard.ETCHING));
        Chemical H2SO4 = new Chemical("H2SO4", "032394", Category.ACID, 1, EnumSet.of(Hazard.ETCHING));
        repo.save("012394", NaOH);
        repo.save("022394", HCl);
        repo.save("032394", H2SO4);
    }

    @Test
    void findByIdReturnsChemicalWhenExists() {
        boolean result = repo.findById("012394").isPresent();
        assertTrue(result);
    }

    @Test
    void findByIdReturnsEmptyWhenMissing() {
        boolean result = repo.findById("042394").isEmpty();
        assertTrue(result);
    }

    @Test
    void deleteByIdRemovesChemical() {
        repo.deleteById("032394");
        boolean result = repo.findById("032394").isPresent();
        assertFalse(result);
    }
}
