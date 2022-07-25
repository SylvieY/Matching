package structure;

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
}
