package algorithm;

import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static algorithm.MainClass.file;

public class RandomGenerator {
    private Random rand = new Random();
    private int N;
    Student[] students;
    Lecturer[] lecturers;
    Stack<Student> studentStack;

    public RandomGenerator(int n) {
        N = n;
    }

    public void generateData() throws IOException {
        Student[] students = new Student[N];
        Lecturer[] lecturers = new Lecturer[N];
        Stack<Student> studentStack = new Stack<>();
        for (int i=0; i<N; i++) {
            Student s = new Student();
            students[i] = s;
            studentStack.push(s);
            Lecturer l = new Lecturer();
            lecturers[i] = l;
        }
//        FileWriter fw = new FileWriter(file, true);
        for (int i=0; i<N; i++) {
            students[i].setPreferenceList(incompleteLecturers(lecturers));
            System.out.println(students[i]+" preference: "+students[i].preferenceListToString());
//            fw.write(students[i]+" preference: "+students[i].preferenceListToString()+"\n");
        }
        for (int i=0; i<N; i++) {
            lecturers[i].setPreferenceList(incompleteStudents(students));
            System.out.println(lecturers[i]+" preference: "+lecturers[i].preferenceListToString());
//            fw.write(lecturers[i]+" preference: "+lecturers[i].preferenceListToString()+"\n");
        }
//        fw.close();
        this.students = students;
        this.lecturers = lecturers;
        this.studentStack = studentStack;
    }

    // Generate a random incomplete list for Students
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
