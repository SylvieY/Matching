package MatchingModels.SM_old;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class RandomGenerator {
    private Random rand = new Random();
    private int N;
    BasicStructure[] students;
    BasicStructure[] lecturers;
    Stack<BasicStructure> studentStack = new Stack<>();

    public RandomGenerator(int n) {
        N = n;
    }

    /** Generate random complete preference lists */
    public void generateData() throws IOException {
        BasicStructure[] students = new Student[N];
        BasicStructure[] lecturers = new Lecturer[N];
        for (int i=0; i<N; i++) {
            Student s = new Student(i+1, N);
            students[i] = s;
            studentStack.push(s);
            Lecturer l = new Lecturer(i+1, N);
            lecturers[i] = l;
        }
        for (BasicStructure s: students) {
            s.setPreferenceList(fullPrefList(lecturers));
            System.out.println(s+" preference list: "+s.preferenceListToString());
        }
        for (BasicStructure l: lecturers) {
            l.setPreferenceList(fullPrefList(students));
            System.out.println(l + " preference list: " + l.preferenceListToString());
        }
        this.students = students;
        this.lecturers = lecturers;
    }


    public BasicStructure[] fullPrefList(BasicStructure[] arr) {
        BasicStructure[] newPrefList = Arrays.copyOf(arr, arr.length);
        for (int i = newPrefList.length-1; i>=0; i--) {
            int index = rand.nextInt(i+1);
            BasicStructure temp = newPrefList[index];
            newPrefList[index] = newPrefList[i];
            newPrefList[i] = temp;
        }
        return newPrefList;
    }

}
