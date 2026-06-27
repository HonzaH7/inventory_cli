package inventory.domain;

import inventory.exception.InvalidChemicalException;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Chemical implements Comparable<Chemical> {
    private final String name;
    private final String id;
    private final Category category;
    private final double concentration;
    private final Set<Hazard> hazards;

    public Chemical(String name, String id, Category category, double concentration, Set<Hazard> hazards) {
        if (concentration < 0) {
            throw new InvalidChemicalException("Concentration cannot be negative (" + concentration + ").");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidChemicalException("Name cannot be null or blank");
        }
        if (id == null || id.isBlank()) {
            throw new InvalidChemicalException("Id cannot be null or blank");
        }

        this.name = name;
        this.id = id;
        this.category = Objects.requireNonNull(category, "Category cannot be null.");
        this.concentration = concentration;
        this.hazards = EnumSet.copyOf(Objects.requireNonNull(hazards, "Hazards cannot be null"));

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chemical chemical = (Chemical) o;
        return Objects.equals(id, chemical.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Chemical{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", category=" + category +
                ", concentration=" + concentration +
                ", hazards=" + hazards +
                '}';
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getConcentration() {
        return concentration;
    }

    @Override
    public int compareTo(Chemical o) {
        return this.id.compareTo(o.getId());
    }

    public Set<Hazard> getHazards() {
        return EnumSet.copyOf(hazards);
    }
}
