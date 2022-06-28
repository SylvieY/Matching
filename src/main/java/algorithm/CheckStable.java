package algorithm;

import structure.Lecturer;
import structure.Student;

public class CheckStable {
    public static boolean isStable(Student[] S) {
        for (Student s:S) {
            Lecturer[] sPreferenceList = s.getPreferenceList();
            for (int i=0; i<s.getPreferencePointer(); i++) {
                Lecturer l = sPreferenceList[i];
                Student[] lPreferenceList = l.getPreferenceList();
                int index = indexOfStudent(lPreferenceList, s);
                if (index < l.getPreferencePointer()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int indexOfStudent(Student[] sList, Student s) {
        for (int i=0; i<sList.length; i++) {
            if (sList[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
}
