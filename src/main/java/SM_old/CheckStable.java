package SM_old;

import structure.BasicStructure;

public class CheckStable {
    public static boolean isStable(BasicStructure[] S) {
        for (BasicStructure s:S) {
            BasicStructure[] sPreferenceList = s.getPreferenceList();
            if (s.getFree()){
                for (BasicStructure l: sPreferenceList) {
                    if (l.getFree()) {
                        System.out.println(s + " and " + l + " are both free. Blocking pair.");
                        return false;
                    }
                    int index = indexOf(l.getPreferenceList(), s);
                    if ((index != -1) && (index < l.getPreferencePointer())) {
                        System.out.println(s + " is free, and" + l + "prefers s to his partner. Blocking pair.");
                        return false;
                    }
                }
            } else {
                for (int i=0; i<s.getPreferencePointer(); i++) {
                    BasicStructure l = sPreferenceList[i];
                    BasicStructure[] lPreferenceList = l.getPreferenceList();
                    if (l.getFree()) {
                        System.out.println(l + " is free, and" + s + "prefers l to his partner. Blocking pair.");
                        return false;
                    }
                    int index = indexOf(lPreferenceList, s);
                    if (index != -1 && index < l.getPreferencePointer()) {
                        System.out.println(l + " prefers " + s + " to his current partner");
                        return false;
                    } else if (index == -1) {
                        System.out.println("index = -1. It should not happen. Each subject should be matched. ");
                        return false;
                    }
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
