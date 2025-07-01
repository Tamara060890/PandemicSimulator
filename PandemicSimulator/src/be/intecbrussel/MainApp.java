package be.intecbrussel;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        // Haal alle patiënten op (de lijst met duplicaten)
        List<Patient> allPatients = Patient.getAllPatients();

        // Verwijder duplicaten en behoud de volgorde waarin ze binnenkwamen
        LinkedHashSet<Patient> uniquePatients = new LinkedHashSet<>(allPatients);

        // Zet de unieke patiënten in een Queue om ze te verwerken
        Queue<Patient> patientQueue = new LinkedList<>(uniquePatients);

        // Maak een map om patiënten per categorie in op te slaan
        Map<Integer, List<Patient>> patientCategories = new HashMap<>();

        // Categorie 1:
        // Iedereen met hoge koorts (>=40) OF bejaarden (>=65) met koorts (>=38) die het onbekende virus hebben
        List<Patient> category1 = patientQueue.stream()
                .filter(p -> ((p.getTemperature() >= 40 || (p.getAge() >= 65 && p.getTemperature() >= 38)) && p.isUnknownVirus()))
                .toList();
        patientCategories.put(1, category1);
        patientQueue.removeAll(category1); // Verwijder ze uit de wachtrij

        // Categorie 2:
        // De rest met gewone koorts (>=38) die het onbekende virus hebben
        List<Patient> category2 = patientQueue.stream()
                .filter(p -> (p.getTemperature() >= 38 && p.isUnknownVirus()))
                .toList();
        patientCategories.put(2, category2);
        patientQueue.removeAll(category2); // Verwijder ze uit de wachtrij

        // Categorie 3:
        // Patiënten zonder koorts (<38) maar met het onbekende virus
        List<Patient> category3 = patientQueue.stream()
                .filter(p -> (p.getTemperature() < 38 && p.isUnknownVirus()))
                .toList();
        patientCategories.put(3, category3);
        patientQueue.removeAll(category3); // Verwijder ze uit de wachtrij

        // Categorie 4:
        // Patiënten met koorts (>=38) maar met een bekend virus (dus zonder onbekend virus)
        List<Patient> category4 = patientQueue.stream()
                .filter(p -> (p.getTemperature() >= 38 && !p.isUnknownVirus()))
                .toList();
        patientCategories.put(4, category4);
        patientQueue.removeAll(category4); // Verwijder ze uit de wachtrij

        // Print alle patiënten per categorie
        System.out.println("--- Patiënten per categorie ---");
        for (Map.Entry<Integer, List<Patient>> entry : patientCategories.entrySet()) {
            System.out.println("Categorie " + entry.getKey() + ":");
            entry.getValue().forEach(System.out::println);
            System.out.println(); // Lege regel voor overzichtelijkheid
        }

        // Controleer of er nog patiënten in de wachtrij zitten die naar huis of apotheker kunnen
        System.out.println("--- Patiënten die naar huis of apotheker mogen ---");
        if (patientQueue.isEmpty()) {
            System.out.println("Alle patiënten zijn gecategoriseerd. Wachtrij is leeg.");
        } else {
            patientQueue.forEach(System.out::println);
        }
    }
}
