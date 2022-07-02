package structure;

import java.util.Objects;

public class BasicStructure {
    String name;
    int id;
    int preferencePointer;

    public BasicStructure() {
        this.preferencePointer = 0;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreferencePointer(int preferencePointer) {
        this.preferencePointer = preferencePointer;
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