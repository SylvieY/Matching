package MatchingModels.MaxMatching;

import Generator.IncompletePreference;
import MatchingModels.SMI.*;
import Structure.BasicStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Maximum Cardinality Matching Algorithm
 *
 * @author yangsuiyi
 *
 * */
public class MaxMatching {
    BasicStructure[] proposers, disposers;
    Stack<BasicStructure> proposerStack;

    /** Constructor with random generator */
    public MaxMatching(IncompletePreference rg) {
        this.proposers = rg.students;
        this.disposers = rg.lecturers;
        this.proposerStack = rg.studentStack;
    }

    /** Constructor with two sides of agents */
    public MaxMatching(BasicStructure[] proposers, BasicStructure[] disposers) {
        this.proposers = proposers;
        this.disposers = disposers;
        this.proposerStack = new Stack<>();
        for (BasicStructure p: proposers) {
            this.proposerStack.push(p);
        }
    }

    /** Constructor with two sides of agents and the stack of proposers */
    public MaxMatching(BasicStructure[] proposers, BasicStructure[] disposers, Stack<BasicStructure> proposerStack) {
        this.proposers = proposers;
        this.disposers = disposers;
        this.proposerStack = proposerStack;
    }

    /** Maximum Matching starts with empty matching */
    public void pureMM() {
        BasicStructure u;
        while ((u=getExposedUnvisited())!=null) {
            reset();
            BasicStructure endVertex = searchAP(u);
            if (endVertex!=null){
                augment(endVertex);
            }
        }
//        System.out.println("------Maximum Cardinality Matching------");
//        outputMatch();
//        System.out.println("------------------END------------------");
    }

    /** Maximum Matching starts with a stable matching */
    public void workFlow() {
        SMI.match(proposerStack);
        System.out.println("------------Stable Matching------------");
        outputMatch();
        System.out.println("----------Stable Matching Done---------");
        BasicStructure u;
        while ((u=getExposedUnvisited())!=null) {
            reset();
//            System.out.println("Unmatched & Untried Vertex on the left: " + u);
            BasicStructure endVertex = searchAP(u);
            if (endVertex!=null){
//                System.out.println("Augmenting Path's End Vertex: " + endVertex);
                augment(endVertex);
            }
        }
        System.out.println("------Maximum Cardinality Matching------");
        outputMatch();
        System.out.println("------------------END------------------");

    }

    /** Find an agent on the proposing side that is free and has not been taken as a starting vertex.
     *  @return such vertex */
    public BasicStructure getExposedUnvisited(){
        for (BasicStructure p: proposers) {
            if (p.getFree() && !p.getStart()) {
                return p;
            }
        }
        return null;
    }

    /** Search for augmenting path
     * @return the end vertex of the augmenting path */
    public BasicStructure searchAP(BasicStructure u) {
//        System.out.println("----------Start Finding Augmenting Path----------");
        List<BasicStructure> queue = new ArrayList<>();
        u.setStart(true);
//        System.out.println("Start: " + u);
        queue.add(u);
//        System.out.println("Starting Queue: " + queue);
        while (queue.size()>0) {
            BasicStructure v = queue.remove(0);
//            System.out.println("Successfully removed " + v + " from the queue");
//            System.out.println("Queue: " + queue);
//            if (u != v) {
//                System.out.println("Next: " + v);
//            }
            v.setVisited(true);
//            System.out.println("Set " + u +  "'s visited to true");
//            System.out.println(v + "'s adjacency list: " + v.preferenceListToString());
            for (BasicStructure w: v.getPreferenceList()){
                if (!w.getVisited()){
//                    System.out.println("An unvisited partner " + w);
                    w.setVisited(true);
                    if (w.getFree()){
                        w.setPredecessor(v);
//                        System.out.println("----Augmenting path found with end vertex: "+ w + "----");
                        return w;
                    }else if(w.getPartner() != v){
                        w.setPredecessor(v);
//                        System.out.println(w + " is matched to: " + w.getPartner());
                        queue.add(w.getPartner());
//                        System.out.println("Add " + w.getPartner() + " to the queue");
//                        System.out.println("Queue: " + queue);
                    }
                }
            }
        }
//        System.out.println("No augmenting path found for " + u);
        return null;
    }

    /** Augment the matching along the augmenting path found.
     * Switch the original partner to predecessor. */
    public void augment(BasicStructure endVertex){
        BasicStructure v,w,temp;
        w = endVertex;
//        System.out.println("Set w = " + endVertex);
        v = w.getPredecessor();
//        System.out.println("Update v = " + w + "'s predecessor " + v);
//        System.out.println(v + "'s start status: " + v.getStart());
        while (!v.getStart() || (v.getStart()&&!v.getFree())) {
            temp = v.getPartner();
//            temp.setFree(true);
//            temp.setPartner(null);
            v.setPartner(w);
            v.setFree(false);
            v.setPreferencePointer(v.getRankingList()[w.getID()-1]-1);
            w.setPartner(v);
            w.setFree(false);
            w.setPreferencePointer(w.getRankingList()[v.getID()-1]-1);

            w = temp;
            v = w.getPredecessor();
        }
//        System.out.println(v + " is the start vertex.");
        v.setPartner(w);
        v.setFree(false);
        v.setPreferencePointer(v.getRankingList()[w.getID()-1]-1);
        w.setPartner(v);
        w.setFree(false);
        w.setPreferencePointer(w.getRankingList()[v.getID()-1]-1);
    }

    /** Reset the status of disposers to unvisited and no predecessor */
    public void reset() {
        for (BasicStructure w: disposers) {
            w.setVisited(false);
            w.setPredecessor(null);
        }
    }

    /** Output the matching result:
     * First show the matched pairs,
     * Then show the unmatched agents. */
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
}
