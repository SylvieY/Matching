package algorithm;

import structure.BasicStructure;
import structure.Lecturer;
import structure.Student;

import java.util.*;

public class MaxPopular {
    Set<BasicStructure> L, R, V, L1, R1, LPrime, RPrime, L2, R2, RFinal;
    Stack<BasicStructure> objStack = new Stack<>();
    Boolean iter = true;

    public MaxPopular() {}

    public Set<BasicStructure> main(Set<BasicStructure> students, Set<BasicStructure> lecturers) {
        L = students;
        R = lecturers;
        for (BasicStructure s: students) {
            objStack.push(s);
        }
        System.out.println("--------------Stable Matching Start--------------");
        StableMatch.match(objStack);
        StableMatch.outputMatch(students, lecturers);
        if (isPerfect(R)) {
            System.out.println("--------------R-Perfect: Yes [RFinal = R]--------------");
            RFinal = R;
            return RFinal;
        }
        System.out.println("--------------[R] R-Perfect: No--------------");

        System.out.println("--------------Initial Round--------------");
        initiate(students, lecturers);
        System.out.println("--------------Stable Matching Start--------------");
        StableMatch.match(objStack);
        StableMatch.outputMatch(L1, R1);
        if (isPerfect(R1)) {
            System.out.println("--------------R-Perfect: Yes [RFinal = R1]--------------");
            RFinal = R;
            return RFinal;
        }
        System.out.println("--------------[R1] R-Perfect: No--------------");

        int i = 1;
        while (iter) {
            System.out.println("--------------Iteration " + i + " Start--------------");
            iterate();
            System.out.println("--------------Done--------------");
            i++;
        }
        return RFinal;
    }

    public void iterate() {
        System.out.println("--------------Odd Round Start--------------");
        refillOdd();
        pushToStack(LPrime);
        setFree();
        StableMatch.match(objStack);
        StableMatch.outputMatch(LPrime, RPrime);
        if (isPerfect(RPrime)) {
            iter = false;
            RFinal = RPrime;
            System.out.println("--------------R-Perfect: Yes [RFinal = RPrime]--------------");
            return;
        }
        System.out.println("--------------[RPrime] R-Perfect: No--------------");
        System.out.println("--------------Even Round Start--------------");
        refillEven();
        pushToStack(L2);
        setFree();
        StableMatch.match(objStack);
        StableMatch.outputMatch(L2, R2);
        if (isPerfect(R2)) {
            iter = false;
            RFinal = R2;
            System.out.println("--------------R-Perfect: Yes [RFinal = R2]--------------");
            return;
        }
        System.out.println("--------------[RT] R-Perfect: No--------------");
        L1 = L2;
        R1 = R2;
    }

    public void initiate(Set<BasicStructure> students, Set<BasicStructure> lecturers) {
        Set<BasicStructure> all = new HashSet<>();
        all.addAll(students);
        all.addAll(lecturers);
        V = all;
        Set<BasicStructure> unmatched = new HashSet<>();
        Set<BasicStructure> matched = new HashSet<>();
        for (BasicStructure s: students) {
            if (s.getFree()) {
                unmatched.add(s);
                objStack.push(s);
            } else {
                matched.add(s);
                s.setFree(true);
                s.setPartner(null);
                s.setPreferencePointer(0);
            }
        }
        for (BasicStructure l: lecturers) {
            if(l.getFree()) {
                unmatched.add(l);
                objStack.push(l);
            } else {
                matched.add(l);
                l.setFree(true);
                l.setPartner(null);
                l.setPreferencePointer(0);
            }
        }
        L1 = unmatched;
        R1 = matched;
    }

    public void refillOdd() {
        Set<BasicStructure> Temp1 = new HashSet<>();
        Set<BasicStructure> Temp2 = new HashSet<>();
        for (BasicStructure obj: R1) {
            if (obj instanceof Student && obj.getFree()) {
                Temp1.add(obj);
            }
        }
        Temp1.addAll(L1);
        LPrime = Temp1;
        Temp2.addAll(V);
        Temp2.removeAll(LPrime);
        RPrime = Temp2;
    }

    public void refillEven() {
        Set<BasicStructure> Temp1 = new HashSet<>();
        Set<BasicStructure> Temp2 = new HashSet<>();
        for (BasicStructure obj: R) {
            if (obj instanceof Lecturer && obj.getFree()) {
                Temp1.add(obj);
            }
        }
        Temp1.addAll(L1);
        L2 = Temp1;
        Temp2.addAll(V);
        Temp2.removeAll(L2);
        R2 = Temp2;
    }

    public Boolean isPerfect(Set<BasicStructure> set){
        for (BasicStructure r: set) {
            if (r.getFree()) {
                return false;
            }
        }
        return true;
    }

    public void setFree() {
        for (BasicStructure obj: V) {
            obj.setFree(true);
            obj.setPartner(null);
            obj.setPreferencePointer(0);
        }
    }

    public void pushToStack(Set<BasicStructure> set) {
        for (BasicStructure obj: set) {
            objStack.push(obj);
        }
    }

    public void outputMatch(Set<BasicStructure> R) {
        System.out.println("The Maximum Cardinality Popular Matching is: ");
        for (BasicStructure r: R) {
            if (r instanceof Student) {
                System.out.println(r + " ： " + r.getPartner());
            }
            if (r instanceof Lecturer) {
                System.out.println(r.getPartner() + " : " + r);
            }

        }
    }

}
