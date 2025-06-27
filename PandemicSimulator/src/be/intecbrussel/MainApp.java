package be.intecbrussel;

import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        //Alle patiënten ophalen
        List<Patient> patients = Patient.getAllPatients();

        //Opdracht1
        //Alleen unieke patiënten, in volgorde van binnenkomst
        Set<Patient> uniquePatients = new LinkedHashSet<>(patients);

        System.out.println("-- Unieke patiënten in volgorde van binnenkomst --");
        uniquePatients.forEach(System.out::println);

        //Opdracht2
        //Patiënten sorteren eerst op hoge temperatuur, dan op leeftijd en nadien of de patient een verzekering heeft.
        List<Patient> sortedPatients = new ArrayList<>(uniquePatients);
        sortedPatients.sort(
                new TemperatureSorter()
                        .thenComparing(new AgeSorter())
                        .thenComparing(new InsuranceSorter())
        );

        Queue<Patient> patientQueue = new LinkedList<>(sortedPatients);
        System.out.println("-- Gesorteerde patiënten met hoge temperatuur en hogere leegtijd --");
        patientQueue.forEach(System.out::println);

        //Opdracht 4:

        //Category 1:
        Map<Integer, List<Patient>> patientCategories = new HashMap<>();
        List<Patient> category1 = patientQueue.stream()
                .filter(p->(p.getTemperature()>=40 || (p.getAge()>=65 && p.getTemperature()>=38)) &&p.isUnknownVirus())
                .collect(Collectors.toList());
        patientCategories.put(1,category1);
        patientQueue.removeAll(category1);
        System.out.println("-- Category 1 patiënten --");
        category1.forEach(System.out::println);

        //Category 2:
        List<Patient> category2 = patientQueue.stream()
                .filter(p->(p.getTemperature()>=38) && p.isUnknownVirus())
                .collect(Collectors.toList());
        patientCategories.put(2,category2);
        patientQueue.removeAll(category2);
        System.out.println("-- Category 2 patiënten --");
        category1.forEach(System.out::println);



    }
}
