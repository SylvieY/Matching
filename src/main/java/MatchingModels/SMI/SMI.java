package MatchingModels.SMI;

import Structure.BasicStructure;

import java.util.Stack;

public class SMI {

    public static void match(Stack<BasicStructure> objStack) {
        // While the stack of students is not empty
        while (!objStack.empty()) {

            // Take the object on the top of the stack
            BasicStructure s = objStack.pop();
//            System.out.println("Matching for "+s);
            int preferencePointer = s.getPreferencePointer();
            BasicStructure[] prefLecturerList = s.getPreferenceList();
            if (preferencePointer >= prefLecturerList.length ) {
                continue;
            }
            BasicStructure l = prefLecturerList[preferencePointer];
            // The following if statement is for popular matching when a lecturer with empty preference list will be placed to be the proposer.
            if (l == null) {
                continue;
            }
            int currantRank = l.getRankingList()[s.getID()-1];
            if (l.getFree())
            {
                s.setPartner(l);
                s.setFree(false);
                l.setPartner(s);
                l.setPreferencePointer(currantRank-1);
                l.setFree(false);
//                System.out.println(l+" is assigned to "+s);
            }
            else if ((currantRank-1) < l.getPreferencePointer())   // rank starts from 1, pointer starts from 0
            {
//                System.out.println(l + " pref pointer: "+ l.getPreferencePointer() + ". Current s is more preferred.");
                // Update the former partner
                BasicStructure formerPartner = l.getPartner();
                int p = formerPartner.getPreferencePointer();
                formerPartner.setPreferencePointer(p+1);
                formerPartner.setPartner(null);
                formerPartner.setFree(true);

                // Update the new partner
                s.setPartner(l);
                s.setFree(false);
                l.setPartner(s);
                l.setPreferencePointer(currantRank-1);

                // Update the stack
                objStack.push(formerPartner);
//                System.out.println(l+" was assigned to "+ formerPartner+", and is assigned to " + s);
            }
            else
            {
//                BasicStructure curPartner = l.getPartner();
//                System.out.println(l+" prefers current partner " + curPartner + " to " + s);
                s.setPreferencePointer(preferencePointer+1);
                objStack.push(s);
            }
        }
    }

}
