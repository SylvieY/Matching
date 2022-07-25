package StableMatching;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

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
            setRankingList(s);
            System.out.println(s + " ranking list: " + s.rankingListToString());
        }
        for (BasicStructure l: lecturers) {
            l.setPreferenceList(fullPrefList(students));
            System.out.println(l + " preference list: " + l.preferenceListToString());
            setRankingList(l);
            System.out.println(l + " ranking list: " + l.rankingListToString());
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

    public void setRankingList(BasicStructure s) {
        BasicStructure[] spl = s.getPreferenceList();
        for (int i=0; i<spl.length; i++) {
            BasicStructure l = spl[i];
            s.setRank(l.getID()-1, i+1);
        }
    }

    public void fullPrefRank() {
        for (BasicStructure s: students) {
            BasicStructure[] newLecturers = Arrays.copyOf(lecturers, N);
            for (int i=0; i<N; i++) {
                int index = rand.nextInt(N-i);
                BasicStructure l = newLecturers[index];
                s.setSinglePreferenceInList(i, l);
                l.setPreference(s);
                s.setRank(l.getID()-1, i+1);
                newLecturers[index] = newLecturers[N-i-1];
            }
            System.out.println(s+" : "+s.preferenceListToString());
            System.out.println(s+" : "+s.rankingListToString());
        }
        for (BasicStructure l: lecturers) {
            BasicStructure[] lPrefList = l.getPreferenceAsArray();
            int size = lPrefList.length;
            for (int i=0; i<size; i++) {
                int index = rand.nextInt(size-i);
                BasicStructure s = lPrefList[index];
                l.setSinglePreferenceInList(i, s);
                l.setRank(s.getID()-1, i+1);
                lPrefList[index] = lPrefList[size-i-1];
            }
            System.out.println(l + " : " + l.preferenceListToString());
            System.out.println(l + " : " + l.rankingListToString());
        }
    }

    public void generateDataV2() {
        BasicStructure[] students = new Student[N];
        BasicStructure[] lecturers = new Lecturer[N];
        for (int i=0; i<N; i++) {
            Student s = new Student(i+1, N);
            students[i] = s;
            studentStack.push(s);
            Lecturer l = new Lecturer(i+1, N);
            lecturers[i] = l;
        }
        this.students = students;
        this.lecturers = lecturers;
        fullPrefRank();
    }

}
