package structure;

import java.util.Arrays;

public class Lecturer extends BasicStructure {
    Student[] preferenceList;
    Student partner;
    Boolean free;
    private static int NEXT_ID = 1;

    public Lecturer() {
        this.id = Lecturer.NEXT_ID++;
        this.free = true;
    }

    public Lecturer(String name) {
        super(name);
        this.id = Lecturer.NEXT_ID++;
    }

    public Student[] getPreferenceList() {
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

    public Student getPartner() {
        return partner;
    }

    public Boolean getFree() {
        return free;
    }

    public void setPreferenceList(Student[] preferenceList) {
        this.preferenceList = preferenceList;
    }

    public void setPartner(Student partner) {
        this.partner = partner;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }
}
