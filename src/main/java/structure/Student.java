package structure;

import java.util.Arrays;

public class Student extends BasicStructure {
    Lecturer[] preferenceList;
    Lecturer partner;
    Boolean free;
    private static int NEXT_ID = 1;

    public Student() {
        this.id = Student.NEXT_ID++;
        this.free = true;
    }

    public Student(String name) {
        super(name);
        this.id = Student.NEXT_ID++;
        this.preferencePointer = 0;
    }

    public Lecturer[] getPreferenceList() {
        return preferenceList;
    }

    public String preferenceListToString() {
        int len = preferenceList.length;
        int[] pl = new int[len];
        for (int i=0; i<len; i++) {
            pl[i] = preferenceList[i].getID();
        }
        return Arrays.toString(pl);
    }

    public Lecturer getPartner() {
        return partner;
    }

    public Boolean getFree() {
        return free;
    }

    public void setPreferenceList(Lecturer[] preferenceList) {
        this.preferenceList = preferenceList;
    }

    public void setPartner(Lecturer partner) {
        this.partner = partner;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }
}
