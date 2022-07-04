package structure;

public class Lecturer extends BasicStructure {
    private static int NEXT_ID = 1;

    public Lecturer() {
        this.id = Lecturer.NEXT_ID++;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                '}';
    }
}
