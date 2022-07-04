package structure;

import java.util.Arrays;
import java.util.Objects;

public class BasicStructure {
    String name;
    int id;
    int preferencePointer;
    BasicStructure[] preferenceList;
    BasicStructure partner;
    Boolean free;

    public BasicStructure() {
        this.preferencePointer = 0;
        this.free = true;
    }

    public BasicStructure(String name) {
        this.name = name;
        this.preferencePointer = 0;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public int getPreferencePointer() {
        return preferencePointer;
    }

    public int getId() {
        return id;
    }

    public BasicStructure[] getPreferenceList() {
        return preferenceList;
    }

    public String preferenceListToString() {
        return Arrays.toString(preferenceList);
    }

    public BasicStructure getPartner() {
        return partner;
    }

    public Boolean getFree() {
        return free;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreferencePointer(int preferencePointer) {
        this.preferencePointer = preferencePointer;
    }

    public void setPreferenceList(BasicStructure[] preferenceList) {
        this.preferenceList = preferenceList;
    }

    public void setPartner(BasicStructure partner) {
        this.partner = partner;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicStructure agent = (BasicStructure) o;
        return id == agent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}