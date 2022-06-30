package algorithm;

import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class MainClass {
    // Run 10,000 times. Time consumed: 707537ms.
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        for (int i=0; i< 10000; i++) {
            RandomGenerator rg = new RandomGenerator(1000);
            File file = rg.createFile("Data_"+ (i+1) + ".txt");
            rg.generateData(file);
            Stack<Student> studentStack = rg.studentStack;
            Student[] studentList = rg.students;
            StableMatch.match(studentStack);
            FileWriter fw = new FileWriter(file, true);
            for (Student s : studentList) {
                fw.write("{ " + s.getID() + " : " + s.getPartner().getID() + " } ");
//            System.out.println(s.getID() + " : " + s.getPartner().getID());
//            System.out.println("Student " + s.getID()+ " is matched with " + s.getPartner().getID());
            }
            boolean isStable = CheckStable.isStable(studentList);
            if (isStable) {
                fw.write("Stable Check: Pass");
//                System.out.println("After checking, the above match is stable");
            } else {
                fw.write("Stable Check: Fail");
//                System.out.println("After checking, the above match is not stable");
            }
            fw.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime-startTime) + "ms");
    }

}
