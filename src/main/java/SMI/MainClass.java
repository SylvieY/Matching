package SMI;

import SMI.CheckStable;
import SMI.RandomGenerator;
import SMI.StableMatch;
import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.Stack;

public class MainClass {
    private static int passCount=0;
    private static int failCount=0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<10000; i++) {
            matchWithRandomData(1000);
        }
//        matchWithRandomData(1000);
//        match();
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime-startTime) + "ms");
    }

    public static void matchWithRandomData(int n) throws IOException {
        RandomGenerator rg = new RandomGenerator(n);
        rg.generateData();
        BasicStructure[] studentList = rg.students;
        BasicStructure[] lecturerList = rg.lecturers;
        Stack<BasicStructure> studentStack = rg.studentStack;
        StableMatch.match(studentStack);
        for (BasicStructure s: studentList) {
            if (!s.getFree()) {
                System.out.println(s + " : " + s.getPartner());
            } else {
                System.out.println(s + " : Unmatched");
            }
        }
        for (BasicStructure l: lecturerList) {
            if (l.getFree()) {
                System.out.println(l + " : Unmatched");
            }
        }
        boolean isStable = CheckStable.isStable(studentList);
        if (isStable) {
            passCount++;
            System.out.println("Stable Check: PASS");
        } else {
            failCount++;
            System.out.println("Stable Check: FAIL");
        }
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
        a1.setPreferenceList(new BasicStructure[]{b1});
        a1.setRankingList(new int[]{1,0,0,0,0,0});
        a2.setPreferenceList(new BasicStructure[]{b1, b5, b2});
        a2.setRankingList(new int[]{1,3,0,0,2,0});
        a3.setPreferenceList(new BasicStructure[]{b4, b2, b3});
        a3.setRankingList(new int[]{0,2,3,1,0,0});
        a4.setPreferenceList(new BasicStructure[]{b4});
        a4.setRankingList(new int[]{0,0,0,1,0,0});
        a5.setPreferenceList(new BasicStructure[]{b5, b4});
        a5.setRankingList(new int[]{0,0,0,2,1,0});
        a6.setPreferenceList(new BasicStructure[]{b5, b6});
        a6.setRankingList(new int[]{0,0,0,0,1,2});
        b1.setPreferenceList(new BasicStructure[]{a2, a1});
        b1.setRankingList(new int[]{2,1,0,0,0,0});
        b2.setPreferenceList(new BasicStructure[]{a2, a3});
        b2.setRankingList(new int[]{0,1,1,0,0,0});
        b3.setPreferenceList(new BasicStructure[]{a3});
        b3.setRankingList(new int[]{0,0,1,0,0,0});
        b4.setPreferenceList(new BasicStructure[]{a5, a3, a4});
        b4.setRankingList(new int[]{0,0,2,3,1,0});
        b5.setPreferenceList(new BasicStructure[]{a2, a6, a5});
        b5.setRankingList(new int[]{0,1,0,0,3,2});
        b6.setPreferenceList(new BasicStructure[]{a6});
        b6.setRankingList(new int[]{0,0,0,0,0,1});
        BasicStructure[] studentList = new BasicStructure[]{a1,a2,a3,a4,a5,a6};
        BasicStructure[] lecturerList = new BasicStructure[]{b1,b2,b3,b4,b5,b6};
        Stack<BasicStructure> studentStack = new Stack<>();
        for (BasicStructure s: studentList) {
            studentStack.push(s);
        }
        StableMatch.match(studentStack);
        for (BasicStructure s: studentList) {
            if (!s.getFree()) {
                System.out.println(s + " : " + s.getPartner());
            } else {
                System.out.println(s + " : Unmatched");
            }
        }
        for (BasicStructure l: lecturerList) {
            if (l.getFree()) {
                System.out.println(l + " : Unmatched");
            }
        }
        boolean isStable = CheckStable.isStable(studentList);
        if (isStable) {
            System.out.println("Stable Check: PASS");
        } else {
            System.out.println("Stable Check: FAIL");
        }
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
