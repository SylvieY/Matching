package StableMatching;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MainClass {
    private static int passCount=0;
    private static int failCount=0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<100; i++) {
            matchWithRandomData(10000);
        }
//        matchWithRandomData(4);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime-startTime) + "ms");
    }

    public static void match() {
        Student a1 = new Student(1,6);
        Student a2 = new Student(2,6);
        Student a3 = new Student(3,6);
        Student a4 = new Student(4,6);
        Student a5 = new Student(5,6);
        Student a6 = new Student(6,6);
        Lecturer b1 = new Lecturer(1,6);
        Lecturer b2 = new Lecturer(2,6);
        Lecturer b3 = new Lecturer(3,6);
        Lecturer b4 = new Lecturer(4,6);
        Lecturer b5 = new Lecturer(5,6);
        Lecturer b6 = new Lecturer(6,6);
        a1.setPreferenceList(new BasicStructure[]{b4, b2, b1, b3, b5, b6});
        a2.setPreferenceList(new BasicStructure[]{b1, b3, b5, b2, b6, b4});
        a3.setPreferenceList(new BasicStructure[]{b6, b2, b3, b4, b1, b5});
        a4.setPreferenceList(new BasicStructure[]{b5, b3, b1, b6, b4, b2});
        a5.setPreferenceList(new BasicStructure[]{b3, b6, b2, b4, b1, b5});
        a6.setPreferenceList(new BasicStructure[]{b2, b1, b4, b5, b6, b3});
        b1.setPreferenceList(new BasicStructure[]{a2, a1, a3, a4, a6, a5});
        b2.setPreferenceList(new BasicStructure[]{a2, a3, a5, a6, a1, a4});
        b3.setPreferenceList(new BasicStructure[]{a3, a5, a4, a2, a1, a6});
        b4.setPreferenceList(new BasicStructure[]{a5, a4, a6, a1, a2, a3});
        b5.setPreferenceList(new BasicStructure[]{a3, a2, a1, a6, a5, a4});
        b6.setPreferenceList(new BasicStructure[]{a4, a6, a2, a3, a5, a1});
        BasicStructure[] studentList = new BasicStructure[]{a1,a2,a3,a4,a5,a6};
        BasicStructure[] lecturerList = new BasicStructure[]{b1,b2,b3,b4,b5,b6};
        Stack<BasicStructure> studentStack = new Stack<>();
        for (BasicStructure s: studentList) {
            studentStack.push(s);
        }
        StableMatch.match(studentStack);
        System.out.println("---------------Stable Match---------------");
        for (BasicStructure s: studentList) {
            System.out.println(s+" : " + s.getPartner());
        }
        boolean isStable = CheckStable.isStable(studentList, lecturerList);
        if (isStable) {
            passCount++;
            System.out.println("-------------Stable Check: PASS------------");
        } else {
            failCount++;
            System.out.println("-------------Stable Check: FAIL------------");
        }
    }

    public static void matchWithRandomData(int n) throws IOException {
//        FileWriter fw = new FileWriter(file, true);
//        fw.write("Round "+ (i+1) +"\n");
        RandomGenerator rg = new RandomGenerator(n);
//        rg.generateData();
        rg.generateDataV2();
        StableMatch.match(rg.studentStack);
        System.out.println("---------------Stable Match---------------");
        for (BasicStructure s: rg.students) {
            System.out.println(s+" : " + s.getPartner());
        }
        boolean isStable = CheckStable.isStable(rg.students, rg.lecturers);
        if (isStable) {
            passCount++;
            System.out.println("-------------Stable Check: PASS------------");
        } else {
            failCount++;
            System.out.println("-------------Stable Check: FAIL------------");
        }
//        fw.close();
    }

    /**
    // Create File
     public static File file;
     static {
        try {
             file = createFile("Data.txt");}
        catch (IOException e) {
             e.printStackTrace();
         }
     }

    public static File createFile(String d) throws IOException {
        File dir = new File("./src/main/java/data/");
        if (!dir.exists()){
            dir.mkdir();
        }
        File directory = new File("./src/main/java/data/"+d);
        String path = null;
        try {
            path = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert path != null;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
    */
}
