package structure;

import java.util.*;

public class BasicStructure {
    int id;
    int preferencePointer;
    int[] rankingList;
    int rankPointer;
    BasicStructure[] preferenceList;
    ArrayList<BasicStructure> preference;
    BasicStructure partner;
    Boolean free;
    Set<BasicStructure> gmPartners;
    Boolean mark;
    Boolean start;
    Boolean visited;
    BasicStructure predecessor;

    public BasicStructure(int id, int n) {
        this.id = id;
        this.preferencePointer = 0;
        this.rankingList = new int[n];
        this.rankPointer = 0;
        this.preferenceList = new BasicStructure[n];
        this.preference = new ArrayList<>();
        this.gmPartners = new HashSet<>();
        this.free = true;
        this.mark = false;
        this.start = false;
        this.visited = false;
        this.predecessor = null;
    }

    public int getID() {
        return id;
    }

    public int getPreferencePointer() {
        return preferencePointer;
    }

    public int[] getRankingList() {
        return rankingList;
    }

    public int getRankPointer() {
        return rankPointer;
    }
    public String rankingListToString() {
        return Arrays.toString(rankingList);
    }

    public BasicStructure[] getPreferenceList() {
        return preferenceList;
    }

    public String preferenceListToString() {
        return Arrays.toString(preferenceList);
    }

    public BasicStructure[] getPreferenceAsArray() {
        return preference.toArray(new BasicStructure[preference.size()]);
    }

    public BasicStructure getPartner() {
        return partner;
    }

    public Boolean getFree() {
        return free;
    }

    public BasicStructure getPredecessor() {
        return predecessor;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public void setPredecessor(BasicStructure predecessor) {
        this.predecessor = predecessor;
    }

    public void setPreferencePointer(int preferencePointer) {
        this.preferencePointer = preferencePointer;
    }

    public void setPreferenceList(BasicStructure[] preferenceList) {
        this.preferenceList = preferenceList;
    }

    public void setSinglePreferenceInList(int index, BasicStructure s) {
        this.preferenceList[index] = s;
    }

    public void setPreference(BasicStructure s) {
        this.preference.add(s);
        this.rankPointer++;
    }

    public Set<BasicStructure> getGmPartners() {
        return gmPartners;
    }

    public void setGmPartners(Set<BasicStructure> gmPartners) {
        this.gmPartners = gmPartners;
    }

    public void addGmPartner(BasicStructure s) {
        this.gmPartners.add(s);
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    public void setRankingList(int[] rankingList) {
        this.rankingList = rankingList;
    }

    public void setRank(int i, int rank) {
        this.rankingList[i] = rank;
    }

    public void setPartner(BasicStructure partner) {
        this.partner = partner;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }


}