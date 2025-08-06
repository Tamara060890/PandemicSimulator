package be.intecbrussel;

import java.util.Comparator;

public class InsuranceSorter implements Comparator<Patient> {
    // Vergelijk twee patiënten op verzekeringsstatus
    @Override
    public int compare(Patient p1, Patient p2){
        return Boolean.compare(p2.isEnsured(), p1.isEnsured());
    }
}

