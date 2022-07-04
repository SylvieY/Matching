package structure;

public class Student extends BasicStructure {
    private static int NEXT_ID = 1;

    public Student() {
        this.id = Student.NEXT_ID++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
