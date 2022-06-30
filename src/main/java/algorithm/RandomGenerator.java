package algorithm;

import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class RandomGenerator {
    private Random rand = new Random();
    private int N;
    Student[] students;
    Lecturer[] lecturers;
    Stack<Student> studentStack;

    public RandomGenerator(int n) {
        N = n;
    }

    public void generateData(File file) throws IOException {
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
        FileWriter fw = new FileWriter(file, true);
        for (int i=0; i<N; i++) {
            students[i].setPreferenceList(shuffleLecturers(lecturers));
//            System.out.println("Student "+students[i].getID()+" preference: "+students[i].preferenceListToString());
            fw.write("Student "+students[i].getID()+" preference: "+students[i].preferenceListToString()+"\n");
            lecturers[i].setPreferenceList(shuffleStudents(students));
//            System.out.println("Lecturer "+lecturers[i].getID()+" preference: "+lecturers[i].preferenceListToString());
            fw.write("Lecturer "+lecturers[i].getID()+" preference: "+lecturers[i].preferenceListToString()+"\n");
        }
        fw.close();
        this.students = students;
        this.lecturers = lecturers;
        this.studentStack = studentStack;
    }

    public void swapStudents(Student[] arr, int a, int b) {
        Student temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public Student[] shuffleStudents(Student[] arr) {
        Student[] newArray = Arrays.copyOf(arr, arr.length);
        for (int i = newArray.length; i>0; i--) {
            int index = rand.nextInt(i);
            swapStudents(newArray, index, i-1);
        }
        return newArray;
    }

    public void swapLecturers(Lecturer[] arr, int a, int b) {
        Lecturer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public Lecturer[] shuffleLecturers(Lecturer[] arr) {
        Lecturer[] newArray = Arrays.copyOf(arr, arr.length);
        for (int i = newArray.length; i>0; i--) {
            int index = rand.nextInt(i);
            swapLecturers(newArray, index, i-1);
        }
        return newArray;
    }

    public File createFile(String d) throws IOException {
        File directory = new File("./src/main/java/data/"+d);
        String path = null;
        try {
            path = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert path != null;
        File file = new File(path);
        System.out.println(path);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
}
