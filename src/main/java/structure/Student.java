package structure;

public class Student extends BasicStructure {
    Lecturer[] preferenceList;
    Lecturer partner;
    private static int NEXT_ID = 1;

    public Student(String name) {
        super(name);
        this.id = Student.NEXT_ID++;
    }

    public Lecturer[] getPreferenceList() {
        return preferenceList;
    }

    public Lecturer getPartner() {
        return partner;
    }

    public void setPreferenceList(Lecturer[] preferenceList) {
        this.preferenceList = preferenceList;
    }

    public void setPartner(Lecturer partner) {
        this.partner = partner;
    }

}
