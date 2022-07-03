package algorithm;

import structure.Lecturer;
import structure.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import static algorithm.MainClass.file;

public class StableMatch {
    public static void match(Stack<Student> studentStack) throws IOException {
        // While the stack of students is not empty
        while (!studentStack.empty()) {

            // Take the student on the top of the stack
            Student s = studentStack.peek();
//            System.out.println("Matching for "+s);
//            FileWriter fw = new FileWriter(file, true);
//            fw.write("Matching for "+s+"\n");
            int preferencePointer = s.getPreferencePointer();
            Lecturer[] prefLecturerList = s.getPreferenceList();
            // If the preference pointer is out of range, then student s has no more candidates to match
            if (preferencePointer >= prefLecturerList.length) {
                // thus pop student s out of stack
                studentStack.pop();
//                System.out.println("The preference pointer is out of range. Pop out of stack.");
//                fw.write("The preference pointer is out of range. Pop out of stack. \n");
                continue;
            }
            Lecturer l = prefLecturerList[preferencePointer];
            int currantRank = getIndex(l.getPreferenceList(), s);
            // -1 means l's preference list does not contain s
            if (currantRank == -1) {
                // thus go to the next l that s prefers
                s.setPreferencePointer(preferencePointer+1);
//                System.out.println(l+" does not accept " + s + ". Move to the next Lecturer.");
//                fw.write(l+" does not accept " + s + ". Move to the next Lecturer. \n");
                continue;
            }

            if (l.getFree())
            {
                s.setPartner(l);
                s.setFree(false);
                studentStack.pop();
                l.setPartner(s);
                l.setPreferencePointer(currantRank);
                l.setFree(false);
//                System.out.println(l+" is assigned to "+s);
//                fw.write(l+" is assigned to "+s + "\n");
            }
            else if (currantRank < l.getPreferencePointer())
            {
                // Update the former partner
                Student formerPartner = l.getPartner();
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
                studentStack.pop();
                studentStack.push(formerPartner);
//                System.out.println(l+" was assigned to "+ formerPartner+", and is assigned to " + s);
//                fw.write(l+" was assigned to "+ formerPartner+", and is assigned to " + s + "\n");
            }
            else
            {
                Student curPartner = l.getPartner();
//                System.out.println(l+" prefers current partner " + curPartner + " to " + s);
//                fw.write(l+" prefers current partner " + curPartner + " to " + s + "\n");
                s.setPreferencePointer(preferencePointer+1);
            }
//            fw.close();
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
