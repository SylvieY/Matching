package algorithm;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MainClass {
    public static File file;
    static {
        try {
            file = createFile("Data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
//        for (int i=0; i<10000; i++){
//            matchWithRandomData(1000); // Time consumed:20644735ms. Average time: 2064ms.
//        }
        match();
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime-startTime) + "ms");
    }

    public static void match() {
        Student a1 = new Student();
        Student a2 = new Student();
        Student a3 = new Student();
        Student a4 = new Student();
        Student a5 = new Student();
        Student a6 = new Student();
        Lecturer b1 = new Lecturer();
        Lecturer b2 = new Lecturer();
        Lecturer b3 = new Lecturer();
        Lecturer b4 = new Lecturer();
        Lecturer b5 = new Lecturer();
        Lecturer b6 = new Lecturer();
        a1.setPreferenceList(new BasicStructure[]{b1});
        a2.setPreferenceList(new BasicStructure[]{b1, b5, b2});
        a3.setPreferenceList(new BasicStructure[]{b4, b2, b3});
        a4.setPreferenceList(new BasicStructure[]{b4});
        a5.setPreferenceList(new BasicStructure[]{b5, b4});
        a6.setPreferenceList(new BasicStructure[]{b5, b6});
        b1.setPreferenceList(new BasicStructure[]{a2, a1});
        b2.setPreferenceList(new BasicStructure[]{a2, a3});
        b3.setPreferenceList(new BasicStructure[]{a3});
        b4.setPreferenceList(new BasicStructure[]{a5, a3, a4});
        b5.setPreferenceList(new BasicStructure[]{a2, a6, a5});
        b6.setPreferenceList(new BasicStructure[]{a6});
        MaxPopular mp = new MaxPopular();
        Set<BasicStructure> students = new HashSet<>(Arrays.asList(a1,a2,a3,a4,a5,a6));
        Set<BasicStructure> lecturers = new HashSet<>(Arrays.asList(b1,b2,b3,b4,b5,b6));
        Set<BasicStructure> R = mp.main(students, lecturers);
        mp.outputMatch(R);
    }

    public static void matchWithRandomData(int n) throws IOException {
//        FileWriter fw = new FileWriter(file, true);
//        fw.write("Round "+ (i+1) +"\n");
        RandomGenerator rg = new RandomGenerator(n);
        rg.generateData();
        Student[] studentList = rg.students;
        Lecturer[] lecturerList = rg.lecturers;
        Set<BasicStructure> studentSet = new HashSet<>(Arrays.asList(studentList));
        Set<BasicStructure> lecturerSet = new HashSet<>(Arrays.asList(lecturerList));
        MaxPopular mp = new MaxPopular();
        Set<BasicStructure> R = mp.main(studentSet, lecturerSet);
        mp.outputMatch(R);
//        fw.close();
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

}
