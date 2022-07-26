package MaxPopularity;


import structure.BasicStructure;

import java.util.*;

public class CheckPopular {
    BasicStructure[] A;
    List<Map.Entry<BasicStructure, BasicStructure>> edgeList;
    List<Map.Entry<Integer, Integer>> labelList;
    Set<BasicStructure> markedA;
    Set<BasicStructure> markedB;

    public CheckPopular(BasicStructure[] a) {
        A = a;
        edgeList = new ArrayList<>();
        labelList = new ArrayList<>();
        markedA = new HashSet<>();
        markedB = new HashSet<>();
    }

    public Boolean isPopular() {
        createGM(A);
        if (layeredGraph1()) {
            System.out.println("Graph1 Check PASS!");
            if (layeredGraph2()) {
                System.out.println("Graph2 Check PASS! \nThe input matching is POPULAR!");
                return true;
            }
        }
        return false;
    }

    public void createGM(BasicStructure[] A) {
        for (BasicStructure a: A) {
            BasicStructure[] partnerList = a.getPreferenceList();
            int aIndex = a.getID()-1;
            /** a is unmatched */
            if (a.getFree()) {
                for (BasicStructure b: partnerList) {
                    Map.Entry<BasicStructure, BasicStructure> edge = new AbstractMap.SimpleEntry<>(a,b);
                    int currentRank = b.getRankingList()[aIndex];
                    /** If b is unmatched, then (a,b) forms a blocking pair for the popular matching.
                     *  The input matching is not popular. */
                    if (b.getFree()) {
                        System.out.println("Unmatched Pair detected. The original matching is not popular.");
                        break;
                    }
                    /** If b prefers a than its partner in M, then label (a,b) as (1,1).
                     * Add a,b to each other's gmPartners. */
                    else if ( currentRank-1 < b.getPreferencePointer()) {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(1,1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.setMark(true);
                        b.setMark(true);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                        markedA.add(a);
                        markedB.add(b);
                    }
                    /** If b prefers its partner in M than a, then label (a,b) as (1,-1).
                     * Add a,b to each other's gmPartners. */
                    else if ( currentRank-1 > b.getPreferencePointer()) {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(1,-1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                    }
                }
            }
            /** a is matched */
            else {
                /** for b that a prefers to its partner in M */
                for (int i=0; i<a.getPreferencePointer(); i++) {
                    BasicStructure b = partnerList[i];
                    Map.Entry<BasicStructure, BasicStructure> edge = new AbstractMap.SimpleEntry<>(a,b);
                    int currentRank = b.getRankingList()[aIndex];
                    /** If b is unmatched, label (a,b) as (1,1). */
                    if (b.getFree()) {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(1,1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.setMark(true);
                        b.setMark(true);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                        markedA.add(a);
                        markedB.add(b);
                    }
                    /** If b prefers a than its partner in M, then label (a,b) as (1,1).
                     * Mark a,b.
                     * Add a,b to each other's gmPartners.*/
                    else if ( currentRank-1 < b.getPreferencePointer()) {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(1,1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.setMark(true);
                        b.setMark(true);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                        markedA.add(a);
                        markedB.add(b);
                    }
                    /** If b prefers its partner in M than a, then label (a,b) as (1,-1).
                     * Add a,b to each other's gmPartners. */
                    else if ( currentRank-1 > b.getPreferencePointer())  {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(1,-1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                    }
                }

                /** for b that a prefers less than its partner in M */
                for (int i=a.getPreferencePointer()+1; i< partnerList.length; i++) {
                    BasicStructure b = partnerList[i];
                    Map.Entry<BasicStructure, BasicStructure> edge = new AbstractMap.SimpleEntry<>(a,b);
                    int currentRank = b.getRankingList()[aIndex];
                    /** If b is unmatched, label (a,b) as (-1,1).
                     *  Add a,b to each other's gmPartners. */
                    if (b.getFree()) {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(-1,1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                    }
                    /** If b prefers a than its partner in M, label (a,b) as (-1,1).
                     *  Add a,b to each other's gmPartners. */
                    else if ( currentRank-1 < b.getPreferencePointer()) {
                        Map.Entry<Integer, Integer> label = new AbstractMap.SimpleEntry<>(-1,1);
                        labelList.add(label);
                        edgeList.add(edge);
                        a.addGmPartner(b);
                        b.addGmPartner(a);
                    }
                }
            }
        }
    }

    public Boolean layeredGraph1() {
        /** Layer 0 : marked women [b]
         * If Layer 0 is empty, return "popular".
         * If woman b is unmatched, return "unpopular". */
        if (markedB.isEmpty()){
            System.out.println("Graph 1 - Layer 0: No marked women.");
            return true;
        }
        System.out.println("Graph 1 - Layer 0: " + markedB);
        for (BasicStructure b: markedB) {
            if (b.getFree()) {
                System.out.println("Graph 1 - An unmatched woman " + b + " is encountered. The input matching is unpopular!");
                return false;
            }
        }
        List<BasicStructure> layer0 = new ArrayList<>(markedB);
        Set<BasicStructure> occurredB = new HashSet<>(markedB);
        Set<BasicStructure> occurredA = new HashSet<>();

        while (!layer0.isEmpty()){
            /** Layer 1: partners of Layer 0 in M [a]
             * If man a is marked, return "unpopular".
             * If man a has not occurred in the graph, then add a to layer 1.*/
            List<BasicStructure> layer1 = new ArrayList<>();
            for (BasicStructure b : layer0) {
                BasicStructure a = b.getPartner();
                if (a.getMark()) {
                    System.out.println("Graph 1 - A marked man " + a + " is encountered. The input matching is unpopular!");
                    return false;
                }
                if (!occurredA.contains(a)){
                    layer1.add(a);
                    occurredA.add(a);
                }
            }

            /** If Layer 1 is empty, return "popular". */
            if (layer1.isEmpty()) {
                return true;
            }

            System.out.println("Graph 1 - Layer 1: " + layer1);

            /** If Layer1 is not empty, keep on building layers.
             * Layer 2: new neighbors of Layer 1 [b]
             * If woman b is unmatched, return "unpopular".
             * If woman b has not occurred in the graph, then add b to layer 2. */
            List<BasicStructure> layer2 = new ArrayList<>();
            for (BasicStructure a : layer1) {
                for (BasicStructure b: a.getGmPartners()) {
                    if (b.getFree()) {
                        System.out.println("Graph 1 - An unmatched woman " + b + " is encountered. The input matching is unpopular!");
                        return false;
                    }
                    if (!occurredB.contains(b)) {
                        layer2.add(b);
                        occurredB.add(b);
                    }
                }
            }
            layer0 = layer2;
            System.out.println("Graph 1 - Layer 0: " + layer0);
        }
        return true;
    }

    public Boolean layeredGraph2() {
        /** Layer 0 : marked men [a]
         * If Layer 0 is empty, return "popular".
         * If man a is marked, return "unpopular". */
        if (markedA.isEmpty()){
            System.out.println("Graph 2 - Layer 0: No marked men.");
            return true;
        }
        System.out.println("Graph 2 - Layer 0: " + markedA);
        for (BasicStructure a: markedA) {
            if (a.getFree()) {
                System.out.println("Graph 2 - An unmatched man " + a + " is encountered. The input matching is unpopular!");
                return false;
            }
        }
        List<BasicStructure> layer0 = new ArrayList<>(markedA);
        Set<BasicStructure> occurredB = new HashSet<>();
        Set<BasicStructure> occurredA = new HashSet<>(markedA);

        while (!layer0.isEmpty()){
            /** Layer 1: partners of Layer 0 in M [b]
             * If woman b has not occurred in the graph, then add b to layer 1.*/
            List<BasicStructure> layer1 = new ArrayList<>();
            for (BasicStructure a : layer0) {
                BasicStructure b = a.getPartner();
                if (!occurredB.contains(b)){
                    layer1.add(b);
                    occurredB.add(b);
                }
            }

            /** If Layer 1 is empty, return "popular". */
            if (layer1.isEmpty()) {
                return true;
            }

            System.out.println("Graph 2 - Layer 1: " + layer1);

            /** If Layer1 is not empty, keep on building layers.
             * Layer 2: new neighbors of Layer 1 [a]
             * If man a is unmatched, return "unpopular".
             * If man a has not occurred in the graph, then add a to layer 2. */
            List<BasicStructure> layer2 = new ArrayList<>();
            for (BasicStructure b : layer1) {
                for (BasicStructure a: b.getGmPartners()) {
                    if (a.getFree()) {
                        System.out.println("Graph 2 - An unmatched man " + a + " is encountered. The input matching is unpopular!");
                        return false;
                    }
                    if (!occurredA.contains(a)) {
                        layer2.add(a);
                        occurredA.add(a);
                    }
                }
            }
            layer0 = layer2;
            System.out.println("Graph 2 - Layer 0: " + layer0);
        }
        return true;
    }
}
