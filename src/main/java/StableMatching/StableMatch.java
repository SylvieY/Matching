package StableMatching;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class StableMatch {

    public static void match(Stack<BasicStructure> objStack) {
        // While the stack of students is not empty
        while (!objStack.empty()) {

            // Take the object on the top of the stack
            BasicStructure s = objStack.peek();
//            System.out.println("Matching for "+s);
            int preferencePointer = s.getPreferencePointer();
            BasicStructure[] prefLecturerList = s.getPreferenceList();
            BasicStructure l = prefLecturerList[preferencePointer];
            int currantRank = l.getRankingList()[s.getID()-1];
//            System.out.println(s + " pref pointer: " + preferencePointer);
//            System.out.println(s + " rank in l: "+currantRank);
            if (l.getFree())
            {
                s.setPartner(l);
                s.setFree(false);
                objStack.pop();
                l.setPartner(s);
                l.setPreferencePointer(currantRank);
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
                objStack.pop();
                objStack.push(formerPartner);
//                System.out.println(l+" was assigned to "+ formerPartner+", and is assigned to " + s);
            }
            else
            {
//                BasicStructure curPartner = l.getPartner();
//                System.out.println(l+" prefers current partner " + curPartner + " to " + s);
                s.setPreferencePointer(preferencePointer+1);
            }
        }
    }

    public static void outputMatch(Set<BasicStructure> L, Set<BasicStructure> R) {
        Set<BasicStructure> matchedSet  = new HashSet<>();
        System.out.println("The stable match is: ");
        for (BasicStructure l: L) {
            if (!l.getFree()) {
                if (l instanceof Student) {
                    matchedSet.add(l);
                    System.out.println(l + " ： " + l.getPartner());
                }
                if (l instanceof Lecturer) {
                    matchedSet.add(l.getPartner());
                    System.out.println(l.getPartner() + " : " + l);
                }
            }
        }
        for (BasicStructure r: R) {
            if (!r.getFree()) {
                if (r instanceof  Student && !(matchedSet.contains(r))) {
                    matchedSet.add(r);
                    System.out.println(r + " ： " + r.getPartner());
                }
                if (r instanceof Lecturer && !(matchedSet.contains(r.getPartner()))) {
                    matchedSet.add(r.getPartner());
                    System.out.println(r.getPartner() + " : " + r);
                }
            }
        }
    }

    // Get the index. --No need because of the ranking list
//    public static int getIndex(BasicStructure[] arr, BasicStructure obj) {
//        for (int i=0; i<arr.length; i++) {
//            if (arr[i].equals(obj)) {
//                return i;
//            }
//        }
//        return -1;
//    }

}
