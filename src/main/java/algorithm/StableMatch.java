package algorithm;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class StableMatch {

    public static void match(Stack<BasicStructure> objStack) {
        // While the stack of students is not empty
        while (!objStack.empty()) {

            // Take the object on the top of the stack
            BasicStructure s = objStack.peek();
//            System.out.println("Matching for "+s);
            int preferencePointer = s.getPreferencePointer();
            BasicStructure[] prefLecturerList = s.getPreferenceList();
            // If the preference pointer is out of range, then student s has no more candidates to match
            if (preferencePointer >= prefLecturerList.length) {
                // thus pop student s out of stack
                objStack.pop();
//                System.out.println("The preference pointer is out of range. Pop out of stack.");
                continue;
            }
            BasicStructure l = prefLecturerList[preferencePointer];
            int currantRank = getIndex(l.getPreferenceList(), s);
            // -1 means l's preference list does not contain s
            if (currantRank == -1) {
                // thus go to the next l that s prefers
                s.setPreferencePointer(preferencePointer+1);
//                System.out.println(l+" does not accept " + s + ". Move to the next Lecturer.");
                continue;
            }

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
            else if (currantRank < l.getPreferencePointer())
            {
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
                l.setPreferencePointer(currantRank);

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

    // Get the index
    public static int getIndex(BasicStructure[] arr, BasicStructure obj) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }
}
