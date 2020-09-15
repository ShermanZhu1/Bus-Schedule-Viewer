package ui;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<AUIobserver> auiObservers;

    public Subject() {
        auiObservers = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: If observer is not already on list, add observer
    public void addObserver(AUIobserver o) {
        if (!auiObservers.contains(o)) {
            auiObservers.add(o);
        }
    }

    //MODIFIES: this
    //EFFECTS: If observer is on list, remove observer
    public void removeObserver(AUIobserver o) {
        if (auiObservers.contains(o)) {
            auiObservers.remove(o);
        }
    }

    //EFFECTS: Calls update on all observers in list
    public void notifyObservers(String busstop) {
        for (AUIobserver o : auiObservers) {
            o.update(busstop);
        }
    }
}
