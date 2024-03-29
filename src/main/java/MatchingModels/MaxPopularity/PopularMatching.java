package MatchingModels.MaxPopularity;

import Generator.IncompletePreference;
import Checking.CheckStable;
import MatchingModels.SMI.*;
import Structure.BasicStructure;
import Structure.Lecturer;
import Structure.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Popular Matching Algorithm
 */
public class PopularMatching {
    BasicStructure[] proposers, disposers;
    Stack<BasicStructure> proposerStack = new Stack<>();
    ArrayList<BasicStructure> vertex, left, right, leftPrime, rightPrime;
    Boolean rPerfect = true;

    public PopularMatching(IncompletePreference rg) {
        this.proposers = rg.students;
        this.disposers = rg.lecturers;
        this.proposerStack = rg.studentStack;
        this.vertex = new ArrayList<>();
        this.vertex.addAll(Arrays.asList(proposers));
        this.vertex.addAll(Arrays.asList(disposers));
        this.left = new ArrayList<>();
    }

    public PopularMatching(BasicStructure[] proposers, BasicStructure[] disposers) {
        this.proposers = proposers;
        this.disposers = disposers;
        for (BasicStructure p: proposers) {
            this.proposerStack.push(p);
        }
        this.vertex = new ArrayList<>();
        this.vertex.addAll(Arrays.asList(proposers));
        this.vertex.addAll(Arrays.asList(disposers));
        this.left = new ArrayList<>();
    }

    public PopularMatching(BasicStructure[] proposers, BasicStructure[] disposers, Stack<BasicStructure> proposerStack) {
        this.proposers = proposers;
        this.disposers = disposers;
        this.proposerStack = proposerStack;
        this.vertex = new ArrayList<>();
        this.vertex.addAll(Arrays.asList(proposers));
        this.vertex.addAll(Arrays.asList(disposers));
        this.left = new ArrayList<>();
    }

    public void popularMatchingBasedOnStableMatching() {
        initiate();
        iteration();
    }

    public void popularMatching() {
        SMI.match(proposerStack);
//        outputMatch();
//        stableCheck(proposers);
        initiate();
        iteration();
    }

    public void initiate() {
        right = new ArrayList<>(vertex);
        for (BasicStructure d: disposers) {
            if (d.getFree()) {
//                System.out.println("Unmatched Lecturers: " + d);
                rPerfect = false;
                left.add(d);
                right.remove(d);
            }
        }
        for (BasicStructure p: proposers) {
            if (p.getFree()) {
                left.add(p);
                right.remove(p);
            }
        }
    }

    public void iteration() {
//        int i=1;
        while (!rPerfect) {
            reset();
//            System.out.println("---------------Iteration " + i + "---------------");
//            System.out.println("left : " + left.toString());
//            System.out.println("right: " + right.toString());
            Stack<BasicStructure> proposerStack = new Stack<>();
            for (BasicStructure l: left) { proposerStack.push(l); }
            SMI.match(proposerStack);
//            outputMatch();
            if (isRPerfect(right)) {
                rPerfect = true;
//                System.out.println("---------------R-Perfect!!!---------------");
                break;
            }
//            System.out.println("---------------NOT R-Perfect---------------");

//            System.out.println("---------------Iteration " + i + " Prime---------------");
            leftPrime = new ArrayList<>(left);
            rightPrime = new ArrayList<>(right);
            for (BasicStructure r: right) {
                if (r.getFree() && (r instanceof Student)) {
                    leftPrime.add(r);
                    rightPrime.remove(r);
                }
            }
            reset();
//            System.out.println("leftPrime : " + leftPrime.toString());
//            System.out.println("rightPrime: " + rightPrime.toString());
            proposerStack = new Stack<>();
            for (BasicStructure l: leftPrime) { proposerStack.push(l); }
            SMI.match(proposerStack);
//            outputMatch();
            if (isRPerfect(rightPrime)) {
                rPerfect = true;
//                System.out.println("---------------R-Perfect!!!---------------");
                break;
            }
//            System.out.println("---------------NOT R-Perfect---------------");

            ArrayList<BasicStructure> newLeft = new ArrayList<>(left);
            ArrayList<BasicStructure> newRight = new ArrayList<>(right);
            for (BasicStructure r: rightPrime) {
                if (r.getFree() && (r instanceof Lecturer)) {
                    newLeft.add(r);
                    newRight.remove(r);
                }
            }
            left = newLeft;
            right = newRight;
//            i++;
        }
//        System.out.println("---------------Iteration Over---------------");
//        System.out.println("----------Maximum Cardinality Popular Matching----------");
//        outputMatch();
    }

    public Boolean isRPerfect(ArrayList<BasicStructure> right) {
        for (BasicStructure r: right) {
            if (r.getFree()) {return false;}
        }
        return true;
    }

    public void reset() {
        for (BasicStructure v: vertex) {
            v.setFree(true);
            v.setPartner(null);
            v.setPreferencePointer(0);
        }
    }

    public void outputMatch() {
        for (BasicStructure s: proposers) {
            if (!s.getFree()) {
                System.out.println(s + " : " + s.getPartner());
            }
        }
        for (BasicStructure s: proposers) {
            if (s.getFree()) {
                System.out.println(s + " : Unmatched");
            }
        }
        for (BasicStructure l: disposers) {
            if (l.getFree()) {
                System.out.println(l + " : Unmatched");
            }
        }
    }

    public void stableCheck(BasicStructure[] A) {
        boolean isStable = CheckStable.isStable(A);
        if (isStable) {
            System.out.println("Stable Check: PASS");
        } else {
            System.out.println("Stable Check: FAIL");
        }
    }

}
