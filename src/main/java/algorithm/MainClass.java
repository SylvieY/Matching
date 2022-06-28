package algorithm;

import structure.Lecturer;
import structure.Student;
import java.util.Stack;

public class MainClass {
    public static void main(String[] args) {
        Student s1 = new Student("1");
        Student s2 = new Student("2");
        Student s3 = new Student("3");
        Student s4 = new Student("4");
        Lecturer l1 = new Lecturer("A");
        Lecturer l2 = new Lecturer("B");
        Lecturer l3 = new Lecturer("C");
        Lecturer l4 = new Lecturer("D");
        s1.setPreferenceList(new Lecturer[]{l2, l4, l1, l3});
        s2.setPreferenceList(new Lecturer[]{l3, l1, l4, l2});
        s3.setPreferenceList(new Lecturer[]{l2, l3, l1, l4});
        s4.setPreferenceList(new Lecturer[]{l4, l1, l3, l2});
        l1.setPreferenceList(new Student[]{s2, s1, s4, s3});
        l2.setPreferenceList(new Student[]{s4, s3, s1, s2});
        l3.setPreferenceList(new Student[]{s1, s4, s3, s2});
        l4.setPreferenceList(new Student[]{s2, s1, s4, s3});
        Student[] studentList = new Student[]{s1, s2, s3, s4};
//        Lecturer[] lecturerList = new Lecturer[]{l1, l2, l3, l4};
        Stack<Student> studentStack = new Stack<>();
        for (Student s: studentList) {
            studentStack.push(s);
        }
        StableMatch.match(studentStack);
        for (Student s: studentList) {
            System.out.println("Student " + s.getName() + " is matched with " + s.getPartner().getName());
        }
        Boolean isStable = CheckStable.isStable(studentList);
        if (isStable) {
            System.out.println("After checking, the above match is stable");
        }else {
            System.out.println("After checking, the above match is not stable");
        }

    }
}
