package Generator;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.*;

public class IncompletePreference {
    private Random rand = new Random();
    private int N;
    public BasicStructure[] students;
    public BasicStructure[] lecturers;
    public Stack<BasicStructure> studentStack = new Stack<>();

    public IncompletePreference(int n) {
        N = n;
    }

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
        this.students = students;
        this.lecturers = lecturers;
        incompleteList();
    }

    /** Generate random incomplete preference lists with consistency between two parties
     * New Method
     * 1. Random the length of student's incomplete preference list
     * 2. Fulfill the incomplete preference list by selecting from full lecturer list
     * 3. The lecturer selected each time will be replaced by the last lecturer in the list.
     *      And next time, the range of the selection keeps reducing.
     * 4. The student's rank and lecturer's preference can both be set in the meantime.
     * 5. Shuffle lecturer's preference in the same way. */
    public void incompleteList() {
        for (BasicStructure s: students) {
            BasicStructure[] newLecturers = Arrays.copyOf(lecturers, N);
            int len = rand.nextInt(N)+1;
            for (int i=0; i<len; i++) {
                int index = rand.nextInt(N-i);
                BasicStructure l = newLecturers[index];
                s.setPreference(l);
                l.setPreference(s);
                s.setRank(l.getID()-1, i+1);
                newLecturers[index] = newLecturers[N-i-1];
            }
            s.setPreferenceList(s.getPreferenceAsArray());
//            System.out.println(s+" : "+s.preferenceListToString());
//            System.out.println(s+" : "+s.rankingListToString());
        }
        for (BasicStructure l: lecturers) {
            BasicStructure[] lPrefList = l.getPreferenceAsArray();
            int size = lPrefList.length;
            l.setPreferenceList(new BasicStructure[size]);
            for (int i=0; i<size; i++) {
                int index = rand.nextInt(size-i);
                BasicStructure s = lPrefList[index];
                l.setSinglePreferenceInList(i, s);
                l.setRank(s.getID()-1, i+1);
                lPrefList[index] = lPrefList[size-i-1];
            }
//            System.out.println(l + " : " + l.preferenceListToString());
//            System.out.println(l + " : " + l.rankingListToString());
        }
    }
}
