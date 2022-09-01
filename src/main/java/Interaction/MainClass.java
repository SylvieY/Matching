package Interaction;
import Checking.CheckStable;
import Generator.CompletePreference;
import Generator.IncompletePreference;
import MatchingModels.StableMatching.*;
import MatchingModels.SMI.*;
import MatchingModels.MaxPopularity.*;
import MatchingModels.MaxMatching.*;
import Structure.BasicStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static Interaction.Utils.*;

/**
 * The class that builds user interaction with command line and reads input through scanner.
 *
 * @author yangsuiyi 2022-09-01
 *
 * */
public class MainClass {
    public static Scanner scanner = new Scanner(System.in);
    static BasicStructure[] students, lecturers;
    static Stack<BasicStructure> studentStack;
    static int matchingSize, cost;
    static int[] profile;
    public static ArrayList<String[]> tabelCell = new ArrayList<>();
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
        profile = new int[N];
        System.out.println("We provide two ways to generate data.");
        System.out.println("1. Random bipartite data   2. Prepared bipartite data");
        System.out.println("Please select from 1/2: ");
        int dataChoice = scanner.nextInt();
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
                        outputAnalysis("Stable Matching");
                        tableGeneration();
                        break;
                    case 2:
                        System.out.println("Please enter the minimum length of proposer's preference list: ");
                        int min = scanner.nextInt();
                        System.out.println("Please enter the maximum length of proposer's preference list: ");
                        int max = scanner.nextInt();
                        IncompletePreference rg2 = new IncompletePreference(N, min, max);
                        rg2.generateData();
                        students = rg2.students;
                        lecturers = rg2.lecturers;
                        studentStack = rg2.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        tableGeneration();
                        break;
                    case 3:
                        System.out.println("Please enter the minimum length of proposer's preference list: ");
                        int min3 = scanner.nextInt();
                        System.out.println("Please enter the maximum length of proposer's preference list: ");
                        int max3 = scanner.nextInt();
                        IncompletePreference rg3 = new IncompletePreference(N, min3, max3);
                        rg3.generateData();
                        students = rg3.students;
                        lecturers = rg3.lecturers;
                        studentStack = rg3.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        Boolean check3 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check3);
                        outputAnalysis("Maximum Cardinality Popular Matching");
                        tableGeneration();
                        break;
                    case 4:
                        System.out.println("Please enter the minimum length of proposer's preference list: ");
                        int min4 = scanner.nextInt();
                        System.out.println("Please enter the maximum length of proposer's preference list: ");
                        int max4 = scanner.nextInt();
                        IncompletePreference rg4 = new IncompletePreference(N, min4, max4);
                        rg4.generateData();
                        students = rg4.students;
                        lecturers = rg4.lecturers;
                        studentStack = rg4.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        MaxMatching maxMatching = new MaxMatching(students, lecturers, studentStack);
                        maxMatching.workFlow();
                        outputMatching("Maximum Cardinality Matching");
                        outputAnalysis("Maximum Cardinality Matching");
                        tableGeneration();
                        break;
                    case 5:
                        System.out.println("Please enter the minimum length of proposer's preference list: ");
                        int min5 = scanner.nextInt();
                        System.out.println("Please enter the maximum length of proposer's preference list: ");
                        int max5 = scanner.nextInt();
                        IncompletePreference rg5 = new IncompletePreference(N, min5, max5);
                        rg5.generateData();
                        students = rg5.students;
                        lecturers = rg5.lecturers;
                        studentStack = rg5.studentStack;
                        outputData();
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        Boolean check5 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check5);
                        outputAnalysis("Maximum Cardinality Popular Matching");
                        MaxMatching maxMatching5 = new MaxMatching(students, lecturers, studentStack);
                        maxMatching5.pureMM();
                        outputMatching("Maximum Cardinality Matching");
                        outputAnalysis("Maximum Cardinality Matching");
                        tableGeneration();
                        break;
                }
                break;

            /** Note:
             * If you are going to paste data in the command line,
             * make sure keep an "enter" after the last line and paste the "enter" together with the data.
             * Otherwise, you may have to manually enter an "enter" after pasting the data.  */
            case 2:
                createAgents(N);
                outputData();
                switch (algorithmNo) {
                    case 1:
                        StableMatch.match(studentStack);
                        outputMatching("Stable Matching");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching");
                        tableGeneration();
                        break;
                    case 2:
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        tableGeneration();
                        break;
                    case 3:
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        Boolean check3 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check3);
                        outputAnalysis("Maximum Cardinality Popular Matching");
                        tableGeneration();
                        break;
                    case 4:
//                        SMI.match(studentStack);
//                        outputMatching("Stable Matching with Incomplete preference list");
//                        outputChecking("Stability", CheckStable.isStable(students));
//                        outputAnalysis("Stable Matching with Incomplete preference list");
                        MaxMatching maxMatching = new MaxMatching(students, lecturers, studentStack);
                        maxMatching.pureMM();
                        outputMatching("Maximum Cardinality Matching");
                        outputAnalysis("Maximum Cardinality Matching");
                        tableGeneration();
                        break;
                    case 5:
                        SMI.match(studentStack);
                        outputMatching("Stable Matching with Incomplete preference list");
                        outputChecking("Stability", CheckStable.isStable(students));
                        outputAnalysis("Stable Matching with Incomplete preference list");
                        Boolean check5 = MainMP.purePM(students, lecturers, studentStack);
                        outputMatching("Maximum Cardinality Popular Matching");
                        outputChecking("Popularity", check5);
                        outputAnalysis("Maximum Cardinality Popular Matching");

                        MaxMatching maxMatching5 = new MaxMatching(students, lecturers, studentStack);
                        maxMatching5.pureMM();
                        outputMatching("Maximum Cardinality Matching");
                        outputAnalysis("Maximum Cardinality Matching");
                        tableGeneration();
                        break;
                }
                break;
        }
    }
}
