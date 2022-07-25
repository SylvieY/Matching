package structure;

public class Student extends BasicStructure {
    public Student(int id, int n) {
        super(id, n);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
