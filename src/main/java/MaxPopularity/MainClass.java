package MaxPopularity;

import MaxPopularity.RandomGenerator;
import MaxPopularity.StableMatch;
import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.IOException;
import java.util.Stack;

public class MainClass {
    public static int pass = 0;
    public static int fail = 0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
//        for (int i=0; i<10000; i++) {
//            matchWithRandomData(1000);
//        }
        matchWithRandomData(20);
//        match2();
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime-startTime) + "ms");
    }

    public static void matchWithRandomData(int n) throws IOException {
        RandomGenerator rg = new RandomGenerator(n);
        rg.generateData();
        BasicStructure[] studentList = rg.students;
        BasicStructure[] lecturerList = rg.lecturers;
        PopularMatching pm = new PopularMatching(studentList, lecturerList);
        pm.popularMatching();
        CheckPopular cp = new CheckPopular(studentList);
        cp.checkPopular();
    }

    public static void match1() {
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
        PopularMatching pm = new PopularMatching(studentList, lecturerList);
        pm.popularMatching();
        CheckPopular cp = new CheckPopular(studentList);
        cp.checkPopular();
    }

    public static void match2() {
        Student a1 = new Student(1,8);
        Student a2 = new Student(2,8);
        Student a3 = new Student(3,8);
        Student a4 = new Student(4,8);
        Student a5 = new Student(5,8);
        Student a6 = new Student(6,8);
        Student a7 = new Student(7,8);
        Student a8 = new Student(8,8);
        Lecturer b1 = new Lecturer(1,8);
        Lecturer b2 = new Lecturer(2,8);
        Lecturer b3 = new Lecturer(3,8);
        Lecturer b4 = new Lecturer(4,8);
        Lecturer b5 = new Lecturer(5,8);
        Lecturer b6 = new Lecturer(6,8);
        Lecturer b7 = new Lecturer(7,8);
        Lecturer b8 = new Lecturer(8,8);
        a1.setPreferenceList(new BasicStructure[]{b7});
        a1.setRankingList(new int[]{0,0,0,0,0,0,1,0});
        a2.setPreferenceList(new BasicStructure[]{b7});
        a2.setRankingList(new int[]{0,0,0,0,0,0,1,0});
        a3.setPreferenceList(new BasicStructure[]{b3, b8, b2, b5, b4, b7, b6});
        a3.setRankingList(new int[]{0,3,1,5,4,7,6,2});
        a4.setPreferenceList(new BasicStructure[]{b3, b2, b5, b1, b4, b6, b7, b8});
        a4.setRankingList(new int[]{4,2,1,5,3,6,7,8});
        a5.setPreferenceList(new BasicStructure[]{b7, b1, b3, b8, b5, b6, b2, b4});
        a5.setRankingList(new int[]{2,7,3,8,5,6,1,4});
        a6.setPreferenceList(new BasicStructure[]{b4, b6, b2});
        a6.setRankingList(new int[]{0,3,0,1,0,2,0,0});
        a7.setPreferenceList(new BasicStructure[]{b7, b3});
        a7.setRankingList(new int[]{0,0,2,0,0,0,1,0});
        a8.setPreferenceList(new BasicStructure[]{b1, b7, b4, b8, b3, b2, b6, b5});
        a8.setRankingList(new int[]{1,6,5,3,8,7,2,4});

        b1.setPreferenceList(new BasicStructure[]{a8, a4, a5});
        b1.setRankingList(new int[]{0,0,0,2,3,0,0,1});
        b2.setPreferenceList(new BasicStructure[]{a4, a3, a6, a8, a5});
        b2.setRankingList(new int[]{0,0,2,1,5,3,0,4});
        b3.setPreferenceList(new BasicStructure[]{a8, a3, a5, a7, a4});
        b3.setRankingList(new int[]{0,0,2,5,3,0,4,1});
        b4.setPreferenceList(new BasicStructure[]{a4, a8, a6, a3, a5});
        b4.setRankingList(new int[]{0,0,4,1,5,3,0,2});
        b5.setPreferenceList(new BasicStructure[]{a4, a3, a5, a8});
        b5.setRankingList(new int[]{0,0,2,1,3,0,0,4});
        b6.setPreferenceList(new BasicStructure[]{a6, a4, a5, a8, a3});
        b6.setRankingList(new int[]{0,0,5,2,3,1,0,4});
        b7.setPreferenceList(new BasicStructure[]{a5, a1, a2, a3, a7, a4, a8});
        b7.setRankingList(new int[]{2,3,4,6,1,0,5,7});
        b8.setPreferenceList(new BasicStructure[]{a3, a4, a8, a5});
        b8.setRankingList(new int[]{0,0,1,2,4,0,0,3});

        BasicStructure[] studentList = new BasicStructure[]{a1,a2,a3,a4,a5,a6,a7,a8};
        BasicStructure[] lecturerList = new BasicStructure[]{b1,b2,b3,b4,b5,b6,b7,b8};
        PopularMatching pm = new PopularMatching(studentList, lecturerList);
        pm.popularMatching();
        CheckPopular cp = new CheckPopular(studentList);
        cp.checkPopular();
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
