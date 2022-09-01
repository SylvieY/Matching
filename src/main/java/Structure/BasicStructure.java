package Structure;

import java.util.*;

/** BasicStructure is the mutual class for two sides of agents.
 *
 * @author yangsuiyi  2022-09-01
 *
 * */
public class BasicStructure {
    /** ID as an integer, to represent the agent, starting from 1. */
    int id;

    /** The pointer that indicates the current partner's preference rank, starting from 0. */
    int preferencePointer;

    /** An array that stores the ranks of neighbours in current agent's preference list. Rank starts from 1. */
    int[] rankingList;

    /** An array that records the current agent's preference over the other side of agents. */
    BasicStructure[] preferenceList;

    /** A list of preferences. Used to add single agent to the preference list when generating random data. */
    ArrayList<BasicStructure> preference;

    /** The attribute used to store the matched agent. */
    BasicStructure partner;

    /** The matching status of the current agent.
     * True stands for free, and false stands for matched. */
    Boolean free;

    /** A set of agents, forming a reduced graph related to a matching,
     * used in the algorithm that checks whether the matching is popular. */
    Set<BasicStructure> gmPartners;

    /** The attribute indicating whether the current agent is marked,
     * used in the algorithm that checks whether the matching is popular. */
    Boolean mark;

    /** The attribute indicating whether the current agent is the start of an augmenting path,
     * used in the algorithm that checks whether the matching is popular.*/
    Boolean start;

    /** The attribute indicating whether the current agent has been visited,
     * used in the algorithm that checks whether the matching is popular. */
    Boolean visited;

    /** The attribute used to store the previous vertex of the current agent in an augmenting path,
     * used in the algorithm that checks whether the matching is popular. */
    BasicStructure predecessor;

    public BasicStructure(int id, int n) {
        this.id = id;
        this.preferencePointer = 0;
        this.rankingList = new int[n];
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