package SM_old;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;
import java.util.Stack;

public class StableMatch {
    public static void match(Stack<BasicStructure> studentStack) {
        // while the stack of students is not empty
        while (!studentStack.empty()) {

            // take the student on the top of the stack
            BasicStructure s = studentStack.peek();
            int preferencePointer = s.getPreferencePointer();
            BasicStructure[] prefLecturerList = s.getPreferenceList();
            BasicStructure l = prefLecturerList[preferencePointer];
            int currentRank = getIndex(l.getPreferenceList(), s);

            if (l.getFree())
            {
                s.setPartner(l);
                s.setFree(false);
                studentStack.pop();
                l.setPartner(s);
                l.setPreferencePointer(currentRank);
                l.setFree(false);
//                System.out.println(l.getID()+" was free and is assigned to "+s.getID());
            }
            else if (currentRank < l.getPreferencePointer())
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
                l.setPreferencePointer(currentRank);

                // Update the stack
                studentStack.pop();
                studentStack.push(formerPartner);
//                System.out.println(l.getID()+" was assigned to "+ formerPartner.getID()+", and is assigned to " + s.getID());
            }
            else
            {
//                System.out.println(s.getID() + " failed to match the lecturer of index " + preferencePointer + " whose ID is " + l.getID());
                s.setPreferencePointer(preferencePointer+1);
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
