package Interaction;
import Checking.CheckPopular;
import Checking.CheckStable;
import Generator.CompletePreference;
import Generator.IncompletePreference;
import MatchingModels.StableMatching.*;
import MatchingModels.SMI.*;
import MatchingModels.MaxPopularity.*;
import MatchingModels.MaxMatching.*;
import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MainClass {
    public static Scanner scanner = new Scanner(System.in);
    static BasicStructure[] students, lecturers;
    static Stack<BasicStructure> studentStack;
    public static File file;
    public static FileWriter fw;
    static {
        try {
            file = createFile("Data.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("We have 4 matching algorithms.");
        System.out.println("1. Stable Matching with Complete Preference List \n2. Stable Matching with Incomplete Preference List \n3. Maximum Cardinality Popular Matching \n4. Maximum Cardinality Matching \n5. Apply 2~4 together");
        System.out.println("Please select from 1~5: ");
        int algorithmNo = scanner.nextInt();
        System.out.println("Please input the size of agent: ");
        int N  = scanner.nextInt();
        System.out.println("We provide two ways to generate data.");
        System.out.println("1. Random bipartite data   2. Prepared bipartite data");
        System.out.println("Please select from 1/2: ");
        int dataChoice = scanner.nextInt();
        System.out.println("dataChoice: " + dataChoice);
        System.out.println("algorithmNo: " + algorithmNo);
        switch (dataChoice){
            case 1:
                switch (algorithmNo) {
                    case 1:
                        CompletePreference rg = new CompletePreference(N);
                        rg.generateDataV2();
                        students = rg.students;
                        lecturers = rg.lecturers;
                        studentStack = rg.studentStack;
                        outputData();
                        StableMatch.match(studentStack);
                        outputMatching("Stable Matching");
                        outputChecking("Stability", CheckStable.isStable(students));
                        break;
                    case 2:
                        IncompletePreference rg2 = new IncompletePreference(N);
                        rg2.generateData();
                        students = rg2.students;
                        lecturers = rg2.lecturers;
                        studentStack = rg2.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        break;
                    case 3:
                        IncompletePreference rg3 = new IncompletePreference(N);
                        rg3.generateData();
                        students = rg3.students;
                        lecturers = rg3.lecturers;
                        studentStack = rg3.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        Boolean check3 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check3);
                        break;
                    case 4:
                        IncompletePreference rg4 = new IncompletePreference(N);
                        rg4.generateData();
                        students = rg4.students;
                        lecturers = rg4.lecturers;
                        studentStack = rg4.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        MaxMatching maxMatching = new MaxMatching(students, lecturers, studentStack);
                        maxMatching.workFlow();
                        outputMatching("Maximum Cardinality Matching");
                        break;
                    case 5:
                        IncompletePreference rg5 = new IncompletePreference(N);
                        rg5.generateData();
                        students = rg5.students;
                        lecturers = rg5.lecturers;
                        studentStack = rg5.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        Boolean check5 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check5);
                        MaxMatching maxMatching5 = new MaxMatching(students, lecturers, studentStack);
                        maxMatching5.workFlow();
                        outputMatching("Maximum Cardinality Matching");
                        break;
                }
                break;
            case 2:
                createAgents(N);
                outputData();
                switch (algorithmNo) {
                    case 1:
                        StableMatch.match(studentStack);
                        outputMatching("Stable Matching");
                        outputChecking("Stability", CheckStable.isStable(students));
                        break;
                    case 2:
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        break;
                    case 3:
                        Boolean check3 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check3);
                        break;
                    case 4:
                        MaxMatching maxMatching = new MaxMatching(students, lecturers, studentStack);
                        maxMatching.workFlow();
                        outputMatching("Maximum Cardinality Matching");
                        break;
                    case 5:
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        Boolean check5 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check5);
                        MaxMatching maxMatching5 = new MaxMatching(students, lecturers, studentStack);
                        maxMatching5.workFlow();
                        outputMatching("Maximum Cardinality Matching");
                        break;
                }
                break;
        }
    }

    public static void createAgents(int N) {
        for (int i=0; i<N; i++) {
            students[i] = new Student(i+1,N);
            lecturers[i] = new Lecturer(i+1,N);
        }
        List<String> data;
        scanner.nextLine();
        System.out.println("Please get your bipartite data ready and paste here: ");
        for (int i=0; i<N; i++) {
            data = List.of(scanner.nextLine().split("[:,]"));
            BasicStructure s = students[i];
            for (int k=1; k<data.size(); k++) {
                int lid = Integer.parseInt(data.get(k));
                BasicStructure l = lecturers[lid-1];
                s.setPreference(l);
                s.setRank(lid-1, k);
            }
            s.setPreferenceList(s.getPreferenceAsArray());
        }
        for (int i=N; i<N*2; i++) {
            data = List.of(scanner.nextLine().split("[:,]"));
            BasicStructure l = lecturers[i-N];
            for (int k=1; k<data.size(); k++) {
                int sid = Integer.parseInt(data.get(k));
                BasicStructure s = students[sid-1];
                l.setPreference(s);
                l.setRank(sid-1, k);
            }
            l.setPreferenceList(l.getPreferenceAsArray());
        }
        scanner.close();
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

    public static void outputData() throws IOException {
        fw = new FileWriter(file, true);
        System.out.println("---------------Data---------------");
        fw.write("---------------Data---------------\n");
        for (BasicStructure s: students) {
            System.out.println(s + " preference : "  + s.preferenceListToString());
            System.out.println(s + " rank list : "  + s.rankingListToString());
            fw.write(s + " preference : "  + s.preferenceListToString() + "\n");
            fw.write(s + " rank list : "  + s.rankingListToString() + "\n");
        }
        for (BasicStructure l: lecturers) {
            System.out.println(l + " preference : "  + l.preferenceListToString());
            System.out.println(l + " rank list : "  + l.rankingListToString());
            fw.write(l + " preference : "  + l.preferenceListToString() + "\n");
            fw.write(l + " rank list : "  + l.rankingListToString() + "\n");
        }
        fw.close();
    }

    public static void outputMatching(String algorithmName) throws IOException {
        fw = new FileWriter(file, true);
        System.out.println("---------------" + algorithmName + "---------------");
        fw.write("---------------" + algorithmName + "---------------\n");
        for (BasicStructure s: students) {
            if (!s.getFree()) {
                System.out.println(s + " : " + s.getPartner());
                fw.write(s + " : " + s.getPartner() + "\n");
            } else {
                System.out.println(s + " : Unmatched");
                fw.write(s + " : Unmatched \n");
            }
        }
        for (BasicStructure l: lecturers) {
            if (l.getFree()) {
                System.out.println(l + " : Unmatched");
                fw.write(l + " : Unmatched \n");
            }
        }
        fw.close();
    }

    public static void outputChecking(String type, Boolean check) throws IOException {
        System.out.println("---------------" + type + " Check: " + check + "---------------");
        fw = new FileWriter(file, true);
        fw.write("---------------" + type + " Check: " + check + "---------------\n");
        fw.close();
    }
}