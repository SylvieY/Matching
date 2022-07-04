package algorithm;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.*;

public class RandomGenerator {
    private Random rand = new Random();
    private int N;
    Student[] students;
    Lecturer[] lecturers;

    public RandomGenerator(int n) {
        N = n;
    }

    public void generateData() throws IOException {
        Student[] students = new Student[N];
        Lecturer[] lecturers = new Lecturer[N];
        for (int i=0; i<N; i++) {
            Student s = new Student();
            students[i] = s;
            Lecturer l = new Lecturer();
            lecturers[i] = l;
        }
//        FileWriter fw = new FileWriter(file, true);
        for(Student s: students) {
            s.setPreferenceList(incompleteLecturers(lecturers));
            System.out.println(s+" preference: "+s.preferenceListToString());
//            fw.write(s+" preference: "+s.preferenceListToString()+"\n");

        }
        for (Lecturer l: lecturers) {
            l.setPreferenceList(incompleteStudents(students));
            System.out.println(l+" preference: "+l.preferenceListToString());
//            fw.write(l+" preference: "+l.preferenceListToString()+"\n");

        }
//        fw.close();
        this.students = students;
        this.lecturers = lecturers;
    }

    // Generate a random incomplete list of Students
    public Student[] incompleteStudents(Student[] arr) {
        List<Student> students = new LinkedList<>(Arrays.asList(arr));
        int len = rand.nextInt(arr.length);
        Student[] incompleteStudents = new Student[len];
        while (len>0) {
            int index = rand.nextInt(students.size());
            incompleteStudents[len-1] = students.get(index);
            students.remove(index);
            len--;
        }
        return incompleteStudents;
    }

    // Generate a random incomplete list for Lecturers
    public Lecturer[] incompleteLecturers(Lecturer[] arr) {
        List<Lecturer> lecturers = new LinkedList<>(Arrays.asList(arr));
        int len = rand.nextInt(arr.length);
        Lecturer[] incompleteLecturers = new Lecturer[len];
        while (len>0) {
            int index = rand.nextInt(lecturers.size());
            incompleteLecturers[len-1] = lecturers.get(index);
            lecturers.remove(index);
            len--;
        }
        return incompleteLecturers;
    }

}
