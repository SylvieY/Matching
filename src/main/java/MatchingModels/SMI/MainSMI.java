package MatchingModels.SMI;

import Structure.BasicStructure;
import Structure.Lecturer;
import Structure.Student;
import Checking.CheckStable;
import Generator.IncompletePreference;

import java.io.IOException;
import java.util.Stack;

public class MainSMI {
    private static int passCount=0;
    private static int failCount=0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<10000; i++) {
            matchWithRandomData(100,1,2);
        }
//        matchWithRandomData(1000);
//        match2();
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        long endTime = System.currentTimeMillis();
        System.out.println("Running time: " + (endTime-startTime) + "ms");
    }

    public static void matchWithRandomData(int n, int min, int max) throws IOException {
        IncompletePreference rg = new IncompletePreference(n,min,max);
        rg.generateData();
//        BasicStructure[] studentList = rg.students;
//        BasicStructure[] lecturerList = rg.lecturers;
        Stack<BasicStructure> studentStack = rg.studentStack;
        SMI.match(studentStack);
//        for (BasicStructure s: studentList) {
//            if (!s.getFree()) {
//                System.out.println(s + " : " + s.getPartner());
//            } else {
//                System.out.println(s + " : Unmatched");
//            }
//        }
//        for (BasicStructure l: lecturerList) {
//            if (l.getFree()) {
//                System.out.println(l + " : Unmatched");
//            }
//        }
//        boolean isStable = CheckStable.isStable(studentList);
//        if (isStable) {
//            passCount++;
//            System.out.println("Stable Check: PASS");
//        } else {
//            failCount++;
//            System.out.println("Stable Check: FAIL");
//        }
    }

    public static void match(BasicStructure[] students, BasicStructure[] lecturers, Stack<BasicStructure> studentStack){
        SMI.match(studentStack);
        boolean isStable = CheckStable.isStable(students);
        if (isStable) {
            System.out.println("Stable Check: PASS");
        } else {
            System.out.println("Stable Check: FAIL");
        }
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
        Stack<BasicStructure> studentStack = new Stack<>();
        for (BasicStructure s: studentList) {
            studentStack.push(s);
        }
        SMI.match(studentStack);
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

    public static void match2() {
        Student a1 = new Student(1,20);
        Student a2 = new Student(2,20);
        Student a3 = new Student(3,20);
        Student a4 = new Student(4,20);
        Student a5 = new Student(5,20);
        Student a6 = new Student(6,20);
        Student a7 = new Student(7,20);
        Student a8 = new Student(8,20);
        Student a9 = new Student(9,20);
        Student a10 = new Student(10,20);
        Student a11 = new Student(11,20);
        Student a12 = new Student(12,20);
        Student a13 = new Student(13,20);
        Student a14 = new Student(14,20);
        Student a15 = new Student(15,20);
        Student a16 = new Student(16,20);
        Student a17 = new Student(17,20);
        Student a18 = new Student(18,20);
        Student a19 = new Student(19,20);
        Student a20 = new Student(20,20);

        Lecturer b1 = new Lecturer(1,20);
        Lecturer b2 = new Lecturer(2,20);
        Lecturer b3 = new Lecturer(3,20);
        Lecturer b4 = new Lecturer(4,20);
        Lecturer b5 = new Lecturer(5,20);
        Lecturer b6 = new Lecturer(6,20);
        Lecturer b7 = new Lecturer(7,20);
        Lecturer b8 = new Lecturer(8,20);
        Lecturer b9 = new Lecturer(9,20);
        Lecturer b10 = new Lecturer(10,20);
        Lecturer b11 = new Lecturer(11,20);
        Lecturer b12 = new Lecturer(12,20);
        Lecturer b13 = new Lecturer(13,20);
        Lecturer b14 = new Lecturer(14,20);
        Lecturer b15 = new Lecturer(15,20);
        Lecturer b16 = new Lecturer(16,20);
        Lecturer b17 = new Lecturer(17,20);
        Lecturer b18 = new Lecturer(18,20);
        Lecturer b19 = new Lecturer(19,20);
        Lecturer b20 = new Lecturer(20,20);

        a1.setPreferenceList(new BasicStructure[]{b18, b5, b3});
        a1.setRankingList(new int[]{0, 0, 3, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0});
        a2.setPreferenceList(new BasicStructure[]{b20, b6, b16, b13, b5, b7, b2, b11, b17, b19, b3, b4});
        a2.setRankingList(new int[]{0, 7, 11, 12, 5, 2, 6, 0, 0, 0, 8, 0, 4, 0, 0, 3, 9, 0, 10, 1});
        a3.setPreferenceList(new BasicStructure[]{b5, b10, b11, b16, b12, b19, b1, b18});
        a3.setRankingList(new int[]{7, 0, 0, 0, 1, 0, 0, 0, 0, 2, 3, 5, 0, 0, 0, 4, 0, 8, 6, 0});
        a4.setPreferenceList(new BasicStructure[]{b9, b16, b17});
        a4.setRankingList(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 3, 0, 0, 0});
        a5.setPreferenceList(new BasicStructure[]{b5, b3, b17});
        a5.setRankingList(new int[]{0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0});
        a6.setPreferenceList(new BasicStructure[]{b9, b18, b6, b10, b2, b8, b3, b11, b19});
        a6.setRankingList(new int[]{0, 5, 7, 0, 0, 3, 0, 6, 1, 4, 8, 0, 0, 0, 0, 0, 0, 2, 9, 0});
        a7.setPreferenceList(new BasicStructure[]{b12, b5, b11, b4});
        a7.setRankingList(new int[]{0, 0, 0, 4, 2, 0, 0, 0, 0, 0, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0});
        a8.setPreferenceList(new BasicStructure[]{b6, b10, b4, b14, b5, b11, b8, b12, b7, b3, b19, b20, b15, b17, b1});
        a8.setRankingList(new int[]{15, 0, 10, 3, 5, 1, 9, 7, 0, 2, 6, 8, 0, 4, 13, 0, 14, 0, 11, 12});
        a9.setPreferenceList(new BasicStructure[]{b3, b14, b9, b1, b12, b16, b15, b11, b19, b10, b6, b8, b4, b7, b18, b17});
        a9.setRankingList(new int[]{4, 0, 1, 13, 0, 11, 14, 12, 3, 10, 8, 5, 0, 2, 7, 6, 16, 15, 9, 0});
        a10.setPreferenceList(new BasicStructure[]{b17, b5, b9, b20, b18, b4, b3, b6, b1, b13, b19, b2, b14, b16, b10, b7, b12, b11});
        a10.setRankingList(new int[]{9, 12, 7, 6, 2, 8, 16, 0, 3, 15, 18, 17, 10, 13, 0, 14, 1, 5, 11, 4});
        a11.setPreferenceList(new BasicStructure[]{b16, b12, b18, b5});
        a11.setRankingList(new int[]{0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 3, 0, 0});
        a12.setPreferenceList(new BasicStructure[]{b6, b4, b10, b20, b9, b12, b17, b19, b2, b14, b15, b3, b16, b8, b5});
        a12.setRankingList(new int[]{0, 9, 12, 2, 15, 1, 0, 14, 5, 3, 0, 6, 0, 10, 11, 13, 7, 0, 8, 4});
        a13.setPreferenceList(new BasicStructure[]{b14, b19, b16, b9, b10, b6, b8, b15, b11, b3});
        a13.setRankingList(new int[]{0, 0, 10, 0, 0, 6, 0, 7, 4, 5, 9, 0, 0, 1, 8, 3, 0, 0, 2, 0});
        a14.setPreferenceList(new BasicStructure[]{b5, b9, b3, b7, b11});
        a14.setRankingList(new int[]{0, 0, 3, 0, 1, 0, 4, 0, 2, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        a15.setPreferenceList(new BasicStructure[]{b4, b19});
        a15.setRankingList(new int[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0});
        a16.setPreferenceList(new BasicStructure[]{b7, b9, b15, b19, b18, b1, b10, b3});
        a16.setRankingList(new int[]{6, 0, 8, 0, 0, 0, 1, 0, 2, 7, 0, 0, 0, 0, 3, 0, 0, 5, 4, 0});
        a17.setPreferenceList(new BasicStructure[]{b8, b19, b14, b7, b10, b1, b17, b5, b13, b2, b15, b16, b20, b11, b12, b9, b18, b6});
        a17.setRankingList(new int[]{6, 10, 0, 0, 8, 18, 4, 1, 16, 5, 14, 15, 9, 3, 11, 12, 7, 17, 2, 13});
        a18.setPreferenceList(new BasicStructure[]{b11, b19, b1, b10, b18, b4, b5, b7, b17, b20, b14, b16, b15, b3, b12, b6});
        a18.setRankingList(new int[]{3, 0, 14, 6, 7, 16, 8, 0, 0, 4, 1, 15, 0, 11, 13, 12, 9, 5, 2, 10});
        a19.setPreferenceList(new BasicStructure[]{b7, b3, b11, b13, b14, b9});
        a19.setRankingList(new int[]{0, 0, 2, 0, 0, 0, 1, 0, 6, 0, 3, 0, 4, 5, 0, 0, 0, 0, 0, 0});
        a20.setPreferenceList(new BasicStructure[]{b17, b14, b5, b13, b11, b9, b15, b7, b18, b2, b20 ,b10});
        a20.setRankingList(new int[]{0, 10, 0, 0, 3, 0, 8, 0, 6, 12, 5, 0, 4, 2, 7, 0, 1, 9, 0, 11});



        b1.setPreferenceList(new BasicStructure[]{a3, a10, a16, a8, a18, a9, a17});
        b1.setRankingList(new int[]{0, 0, 1, 0, 0, 0, 0, 4, 6, 2, 0, 0, 0, 0, 0, 3, 7, 5, 0, 0});
        b2.setPreferenceList(new BasicStructure[]{a6, a2, a17, a12, a10, a20});
        b2.setRankingList(new int[]{0, 2, 0, 0, 0, 1, 0, 0, 0, 5, 0, 4, 0, 0, 0, 0, 3, 0, 0, 6});
        b3.setPreferenceList(new BasicStructure[]{a9, a14, a12, a1, a19, a5, a18, a16, a6, a10, a2, a13, a8});
        b3.setRankingList(new int[]{4, 11, 0, 0, 6, 9, 0, 13, 1, 10, 0, 3, 12, 2, 0, 8, 0, 7, 5, 0});
        b4.setPreferenceList(new BasicStructure[]{a12, a7, a9, a2, a15, a18, a8, a10});
        b4.setRankingList(new int[]{0, 4, 0, 0, 0, 0, 2, 7, 3, 8, 0, 1, 0, 0, 5, 0, 0, 6, 0, 0});
        b5.setPreferenceList(new BasicStructure[]{a5, a17, a2, a12, a8, a14, a10, a20, a3, a18, a7, a11, a1});
        b5.setRankingList(new int[]{13, 3, 9, 0, 1, 0, 11, 5, 0, 7, 12, 4, 0, 6, 0, 0, 2, 10, 0, 8});
        b6.setPreferenceList(new BasicStructure[]{a10, a8, a6, a12, a2, a18, a17, a13, a9});
        b6.setRankingList(new int[]{0, 5, 0, 0, 0, 3, 0, 2, 9, 1, 0, 4, 8, 0, 0, 0, 7, 6, 0, 0});
        b7.setPreferenceList(new BasicStructure[]{a2, a10, a16, a20, a9, a17, a14, a8, a18, a19});
        b7.setRankingList(new int[]{0, 1, 0, 0, 0, 0, 0, 8, 5, 2, 0, 0, 0, 7, 0, 3, 6, 9, 10, 4});
        b8.setPreferenceList(new BasicStructure[]{a8, a9, a13, a6, a17, a12});
        b8.setRankingList(new int[]{0, 0, 0, 0, 0, 4, 0, 1, 2, 0, 0, 6, 3, 0, 0, 0, 5, 0, 0, 0});
        b9.setPreferenceList(new BasicStructure[]{a10, a17, a13, a20, a16, a19, a9, a12, a6, a14, a4});
        b9.setRankingList(new int[]{0, 0, 0, 11, 0, 9, 0, 0, 7, 1, 0, 8, 3, 10, 0, 5, 2, 0, 6, 4});
        b10.setPreferenceList(new BasicStructure[]{a8, a3, a13, a9, a16, a20, a10, a6, a17, a18, a12});
        b10.setRankingList(new int[]{0, 0, 2, 0, 0, 8, 0, 1, 4, 7, 0, 11, 3, 0, 0, 5, 9, 10, 0, 6});
        b11.setPreferenceList(new BasicStructure[]{a2, a8, a6, a13, a14, a20, a18, a10, a3, a19, a9, a17, a7});
        b11.setRankingList(new int[]{0, 1, 9, 0, 0, 3, 13, 2, 11, 8, 0, 0, 4, 5, 0, 0, 12, 7, 10, 6});
        b12.setPreferenceList(new BasicStructure[]{a11, a9, a12, a8, a17, a3, a18, a10, a7});
        b12.setRankingList(new int[]{0, 0, 6, 0, 0, 0, 9, 4, 2, 8, 1, 3, 0, 0, 0, 0, 5, 7, 0, 0});
        b13.setPreferenceList(new BasicStructure[]{a10, a17, a2, a20, a19});
        b13.setRankingList(new int[]{0, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 5, 4});
        b14.setPreferenceList(new BasicStructure[]{a19, a12, a18, a13, a20, a9, a10, a8, a17});
        b14.setRankingList(new int[]{0, 0, 0, 0, 0, 0, 0, 8, 6, 7, 0, 2, 4, 0, 0, 0, 9, 3, 1, 5});
        b15.setPreferenceList(new BasicStructure[]{a9, a18, a8, a16, a13, a20, a12, a17});
        b15.setRankingList(new int[]{0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 0, 7, 5, 0, 0, 4, 8, 2, 0, 6});
        b16.setPreferenceList(new BasicStructure[]{a4, a3, a10, a12, a2, a13, a17, a9, a11, a18});
        b16.setRankingList(new int[]{0, 5, 2, 1, 0, 0, 0, 0, 8, 3, 9, 4, 6, 0, 0, 0, 7, 10, 0, 0});
        b17.setPreferenceList(new BasicStructure[]{a20, a9, a8, a4, a17, a10, a12, a2, a5, a18});
        b17.setRankingList(new int[]{0, 8, 0, 4, 9, 0, 0, 3, 2, 6, 0, 7, 0, 0, 0, 0, 5, 10, 0, 1});
        b18.setPreferenceList(new BasicStructure[]{a10, a18, a6, a20 ,a9, a1, a3, a11, a17, a16});
        b18.setRankingList(new int[]{6, 0, 7, 0, 0, 3, 0, 0, 5, 1, 8, 0, 0, 0, 0, 10, 9, 2, 0, 4});
        b19.setPreferenceList(new BasicStructure[]{a2, a16, a8, a9, a12, a6, a18, a3, a15, a13, a10, a17});
        b19.setRankingList(new int[]{0, 1, 8, 0, 0, 6, 0, 3, 4, 11, 0, 5, 10, 0, 9, 2, 12, 7, 0, 0});
        b20.setPreferenceList(new BasicStructure[]{a18, a2, a10, a8, a17, a20, a12});
        b20.setRankingList(new int[]{0, 2, 0, 0, 0, 0, 0, 4, 0, 3, 0, 7, 0, 0, 0, 0, 5, 1, 0, 6});

        BasicStructure[] studentList = new BasicStructure[]{a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20};
        BasicStructure[] lecturerList = new BasicStructure[]{b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20};
        Stack<BasicStructure> studentStack = new Stack<>();
        for (BasicStructure s: studentList) {
            studentStack.push(s);
        }
        SMI.match(studentStack);
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

}
