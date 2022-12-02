package org.example;

public class mainPersonController {
    private static int person_id;


    public  mainPersonController(){}
    public static int getPerson_id() {
        return person_id;
    }

    public static void setPerson_id(int person_id) {
        mainPersonController.person_id = person_id;
    }
}
