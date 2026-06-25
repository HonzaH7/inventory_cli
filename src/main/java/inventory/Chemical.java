package inventory;

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
        this.name = name;
        this.id = id;
        this.category = category;
        this.concentration = concentration;
        this.hazards = EnumSet.copyOf(hazards);

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
