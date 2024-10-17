package LabActivity;

import java.util.LinkedList;

public class linkedlist {

    public static void main(String[] args) {
        LinkedList<String> person = new LinkedList<>();


      person.add("francis");
        person.addFirst("shella");
        person.addLast("joy");
        person.add(1, "jaylene");

        System.out.println("Linked List Original: " + person);
        System.out.println(person.size());


        if (person.contains("shella")) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        boolean containslion = person.contains("shella");
        System.out.println(containslion);


    }

}