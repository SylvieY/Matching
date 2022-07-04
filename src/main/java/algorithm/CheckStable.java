package algorithm;

import structure.BasicStructure;
import structure.Student;

public class CheckStable {
    public static boolean isStable(Student[] S) {
        for (Student s:S) {
            BasicStructure[] sPreferenceList = s.getPreferenceList();
            if (sPreferenceList.length == 0){
                continue;
            }
            for (int i=0; i<s.getPreferencePointer(); i++) {
                BasicStructure l = sPreferenceList[i];
                BasicStructure[] lPreferenceList = l.getPreferenceList();
                int index = indexOf(lPreferenceList, s);
                if (index == -1){
                    continue;
                }
                if (index < l.getPreferencePointer()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int indexOf(BasicStructure[] sList, BasicStructure s) {
        for (int i=0; i<sList.length; i++) {
            if (sList[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
}
