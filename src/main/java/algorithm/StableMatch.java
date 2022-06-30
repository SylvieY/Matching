package algorithm;

import structure.Lecturer;
import structure.Student;
import java.util.Stack;

public class StableMatch {
    public static void match(Stack<Student> studentStack) {
        // while the stack of students is not empty
        while (!studentStack.empty()) {

            // take the student on the top of the stack
            Student s = studentStack.peek();
//            System.out.println(s.getName());

            int preferencePointer = s.getPreferencePointer();
            Lecturer[] prefLecturerList = s.getPreferenceList();
            Lecturer l = prefLecturerList[preferencePointer];
            int currantRank = getIndex(l.getPreferenceList(), s);

            if (l.getFree())
            {
                s.setPartner(l);
                studentStack.pop();
                l.setPartner(s);
                l.setPreferencePointer(currantRank);
                l.setFree(false);
//                System.out.println(l.getID()+" was free and is assigned to "+s.getID());
            }
            else if (currantRank < l.getPreferencePointer())
            {
                // Update the former partner
                Student formerPartner = l.getPartner();
                int p = formerPartner.getPreferencePointer();
                formerPartner.setPreferencePointer(p+1);
                formerPartner.setPartner(null);

                // Update the new partner
                s.setPartner(l);
                l.setPartner(s);
                l.setPreferencePointer(currantRank);

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
    public static int getIndex(Student[] arr, Student obj) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }
}
