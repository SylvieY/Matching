package structure;

public class Lecturer extends BasicStructure {
    Student[] preferenceList;
    Student partner;
    Boolean free=true;
    private static int NEXT_ID = 1;

    public Lecturer(String name) {
        super(name);
        this.id = Lecturer.NEXT_ID++;
    }

    public Student[] getPreferenceList() {
        return preferenceList;
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
