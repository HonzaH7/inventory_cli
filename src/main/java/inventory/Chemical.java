package inventory;

import java.util.Objects;

public class Chemical implements Comparable<Chemical> {
    private final String name;
    private final String id;
    private final Category category;
    private final double concentration;

    public Chemical(String name, String id, Category category, double concentration) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.concentration = concentration;
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
                '}';
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public int compareTo(Chemical o) {
        return this.id.compareTo(o.getId());
    }
}
