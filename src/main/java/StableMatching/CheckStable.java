package StableMatching;

import structure.BasicStructure;
import structure.Student;

public class CheckStable {
    public static boolean isStable(BasicStructure[] S, BasicStructure[] L) {
        for (BasicStructure s:S) {
            BasicStructure[] sPreferenceList = s.getPreferenceList();
            if (s.getFree()){
                for (BasicStructure l: sPreferenceList) {
                    if (l.getFree()) {
                        return false;
                    } else if (l.getRankingList()[s.getID()-1] < (l.getPreferencePointer()+1)) {
                        return false;
                    }
                }
            }
            else{
                for (int i=0; i<s.getPreferencePointer(); i++) {
                    BasicStructure l = sPreferenceList[i];
                    int currentRank = l.getRankingList()[s.getID()-1];
                    if ((currentRank!=0) && ((currentRank-1) < l.getPreferencePointer())) {
                        return false;
                    } else if (currentRank == 0) {
                        System.out.println(s);
                        System.out.println(l);
                        System.out.println("currentRank = 0. It should not happen. Each subject should be matched. ");
                        return false;
                    }
                }
            }

        }
        return true;
    }

//    public static int indexOf(BasicStructure[] sList, BasicStructure s) {
//        for (int i=0; i<sList.length; i++) {
//            if (sList[i].equals(s)) {
//                return i;
//            }
//        }
//        return -1;
//    }
}