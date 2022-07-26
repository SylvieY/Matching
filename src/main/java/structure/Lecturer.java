package structure;

import java.util.Objects;

public class Lecturer extends BasicStructure {
    public Lecturer(int id, int n) {
        super(id, n);
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;
        Lecturer that = (Lecturer) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
