package MatchingModels.StableMatching;

import structure.BasicStructure;

import java.util.*;

public class StableMatch {

    public static void match(Stack<BasicStructure> objStack) {
        /** While the stack of students is not empty */
        while (!objStack.empty()) {

            /** Take the object on the top of the stack */
            BasicStructure s = objStack.peek();
            int preferencePointer = s.getPreferencePointer();
            BasicStructure[] prefLecturerList = s.getPreferenceList();
            BasicStructure l = prefLecturerList[preferencePointer];
            int currantRank = l.getRankingList()[s.getID()-1];
            if (l.getFree())
            {
                s.setPartner(l);
                s.setFree(false);
                objStack.pop();
                l.setPartner(s);
                l.setPreferencePointer(currantRank-1);
                l.setFree(false);
            }
            else if ((currantRank-1) < l.getPreferencePointer())   // rank starts from 1, pointer starts from 0
            {
                /** Update the former partner */
                BasicStructure formerPartner = l.getPartner();
                int p = formerPartner.getPreferencePointer();
                formerPartner.setPreferencePointer(p+1);
                formerPartner.setPartner(null);
                formerPartner.setFree(true);

                /** Update the new partner */
                s.setPartner(l);
                s.setFree(false);
                l.setPartner(s);
                l.setPreferencePointer(currantRank-1);

                /** Update the stack */
                objStack.pop();
                objStack.push(formerPartner);
            }
            else
            {
                s.setPreferencePointer(preferencePointer+1);
            }
        }
    }

}
