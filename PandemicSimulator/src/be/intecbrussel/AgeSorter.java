package be.intecbrussel;

import java.util.Comparator;

public class AgeSorter implements Comparator<Patient> {
    @Override
    public int compare(Patient p1, Patient p2){
        return Integer.compare(p2.getAge(), p1.getAge());
    }
}
