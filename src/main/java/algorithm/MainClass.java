package algorithm;

import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    // Run 10,000 times with N=1000. Time consumed 6691290ms.
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<10000; i++){
            FileWriter fw = new FileWriter(file, true);
            fw.write("Round "+ (i+1) +"\n");
            RandomGenerator rg = new RandomGenerator(1000);
            rg.generateData();
            Stack<Student> studentStack = rg.studentStack;
            Student[] studentList = rg.students;
            StableMatch.match(studentStack);
            fw.write("Student : Lecturer \n");
            System.out.println("Student : Lecturer");
            for (Student s : studentList) {
                if (s.getFree()){
                    System.out.println(s+ " : Unmatched");
                    fw.write(s + " : Unmatched \n");
                }
                else{
                    System.out.println(s + " : " + s.getPartner());
                    fw.write(s + " : " + s.getPartner()+"\n");
                }
            }
            fw.write("Lecturer : Student \n");
            System.out.println("Lecturer : Student");
            for (Lecturer l: rg.lecturers) {
                if (l.getFree()){
                    System.out.println(l + " : Unmatched");
                    fw.write(l + " : Unmatched \n");
                }
                else{
                    System.out.println(l + " : " + l.getPartner());
                    fw.write(l + " : " + l.getPartner()+"\n");
                }
            }

            boolean isStable = CheckStable.isStable(studentList);
            if (isStable) {
                fw.write("Stable Check: Pass \n");
                System.out.println("Stable Check: Pass");
            } else {
                fw.write("Stable Check: Fail \n");
                System.out.println("Stable Check: Fail");
                break;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Running time: " + (endTime-startTime) + "ms");
            fw.write("Running time: " + (endTime-startTime) + "ms \n");
            fw.close();
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
}
