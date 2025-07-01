package be.intecbrussel;

import java.util.Comparator;

public class TemperatureSorter implements Comparator<Patient> {
    // Vergelijkt twee patiënten op temperatuur, zodat degene met de hoogste temperatuur eerst komt
    @Override
    public int compare(Patient p1, Patient p2){
        return Integer.compare(p2.getTemperature(), p1.getTemperature());
    }
}
