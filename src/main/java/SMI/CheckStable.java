package SMI;

import structure.BasicStructure;

public class CheckStable {
    public static boolean isStable(BasicStructure[] S) {
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
                    if (l.getFree()) {
                        return false;
                    } else if ((currentRank!=0) && ((currentRank-1) < l.getPreferencePointer())) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
