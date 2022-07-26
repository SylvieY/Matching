package structure;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
