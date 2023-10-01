import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1024, 768);
    }

    public void printSchoolNames(String city, String type) {
        CsvReader reader1 = new CsvReader("students.csv");
        int numOfMaleStudents = 0;
        int numOfFemaleStudents = 0;
        int changeX=0;
        int changeY=0;
        reader1.skipRow();
        SaxionApp.clear();
        while (reader1.loadRow()) {
            String cityName = reader1.getString(1);
            String typeName = reader1.getString(2);
            numOfMaleStudents = reader1.getInt(4);
            numOfFemaleStudents = reader1.getInt(5);
            if (cityName.equals(city) && (typeName.equals(type))) {
                SaxionApp.print(reader1.getString(0), Color.green);
                SaxionApp.print(", ", Color.green);
                SaxionApp.print(reader1.getString(3), Color.green, true);
                drawStudentsCircles(changeX, changeY, numOfMaleStudents, numOfFemaleStudents);
                changeY += 19;
                changeX = 1;
            }
        }
    }
     public void drawStudentsCircles(int changeX, int changeY, int numOfMaleStudents, int numOfFemaleStudents) {
         for (int i = 0; i <= numOfMaleStudents - 1; i++) {
             SaxionApp.setFill(Color.CYAN);
             SaxionApp.drawCircle(700 + changeX, 10 + changeY, 4);
             changeX += 8;
         }
         changeX = 0;
         for (int i = 0; i <= numOfFemaleStudents - 1; i++) {
             SaxionApp.setFill(Color.red);
             SaxionApp.drawCircle(700 + (changeX + (8 * numOfMaleStudents)), 10 + changeY, 4);
             changeX += 8;
         }
     }

     public int[] countStudent (int year){
        boolean validOptionChosen = false;
        int numOfStudents = 0;
         int[] numOfStudents1 = new int[3];
         int colon1 = 0;
         int colon2 = 0;
         int numOfStudentsA = 0;
         int numOfStudentsD = 0;
         int numOfStudentsE = 0;

             if (year == 4) { colon1 = 4; colon2 = 5; }
             if (year == 5) { colon1 = 6; colon2 = 7; }
             if (year == 6) { colon1 = 8; colon2 = 9; }




             CsvReader reader2 = new CsvReader("students.csv");
             reader2.skipRow();

             while (reader2.loadRow()) {
                 if (reader2.getString(1).equals("APELDOORN")) {
                     numOfStudents = reader2.getInt(colon1) + reader2.getInt(colon2);
                     numOfStudentsA += numOfStudents;
                 }
                 numOfStudents1[0] = numOfStudentsA;
                 if (reader2.getString(1).equals("DEVENTER")) {
                     numOfStudents = reader2.getInt(colon1) + reader2.getInt(colon2);
                     numOfStudentsD += numOfStudents;
                 }
                 numOfStudents1[1] = numOfStudentsD;
                 if (reader2.getString(1).equals("ENSCHEDE")) {
                     numOfStudents = reader2.getInt(colon1) + reader2.getInt(colon2);
                     numOfStudentsE += numOfStudents;
                 }
                 numOfStudents1[2] = numOfStudentsE;
             }

            return numOfStudents1;
     }
     public void run() {
        CsvReader reader = new CsvReader("students.csv");
        SaxionApp.printLine("Welcome to the Enschede, Deventer and Apelodoorn schoolsystem!");
        SaxionApp.printLine("--------------------------------------------------------------");
        SaxionApp.printLine("1. Print all the schoolnames");
        SaxionApp.printLine("2. Get total number of students per year");
        SaxionApp.printLine("3. Draw student overview chart");
        SaxionApp.printLine("0. Exit");

        int option = 0;
        boolean validOptionChosen = false;

        while (!validOptionChosen) {
            option = SaxionApp.readInt();
                switch (option) {
                    case 1:

                        String str = "";
                        String str1 = "";
                        reader.skipRow();
                        while (reader.loadRow()) {
                            str = reader.getString(0);
                            if (!str.equals(str1)) {
                                SaxionApp.printLine("- " + str);
                            }
                            str1 = str;
                        }
                        validOptionChosen = true;
                        break;
                    case 2:
                        int numOfMaleStudents = 0;
                        int numOfFemaleStudents = 0;
                        int numOfStudentsA = 0;
                        int numOfStudentsD = 0;
                        int numOfStudentsE = 0;
                        String cityName = "";
                        int yearNum = 0;


                            while(!validOptionChosen) {
                                SaxionApp.printLine("Which year (4, 5, or 6)?");
                                yearNum = SaxionApp.readInt();
                                if (yearNum > 3 && yearNum < 7) {

                                    SaxionApp.printLine("In Apeldoorn there are " + countStudent(yearNum)[0]);
                                    SaxionApp.printLine("In Deventer there are " + countStudent(yearNum)[1]);
                                    SaxionApp.printLine("In Enschede there are " + countStudent(yearNum)[2]);

                                    validOptionChosen = true;
                                }
                                else {validOptionChosen = false;}
                            }

                            break;
                    case 3:
                        SaxionApp.printLine("Which city?");
                        String city = SaxionApp.readString();
                        SaxionApp.printLine("Which type (HAVO / VWO)?");
                        String type = SaxionApp.readString();
                        if ((city.equals("apeldoorn") || city.equals("Apeldoorn") || city.equals("APELDOORN")) && (type.equals("havo") || type.equals("HAVO")))
                            printSchoolNames("APELDOORN", "HAVO"); validOptionChosen = true;
                        if ((city.equals("apeldoorn") || city.equals("Apeldoorn") || city.equals("APELDOORN")) && (type.equals("vwo") || type.equals("VWO")))
                            printSchoolNames("APELDOORN", "VWO"); validOptionChosen = true;
                        if ((city.equals("deventer") || city.equals("Deventer") || city.equals("DEVENTER")) && (type.equals("havo") || type.equals("HAVO")))
                            printSchoolNames("DEVENTER", "HAVO"); validOptionChosen = true;
                        if ((city.equals("deventer") || city.equals("Deventer") || city.equals("DEVENTER")) && (type.equals("vwo") || type.equals("VWO")))
                            printSchoolNames("DEVENTER", "VWO"); validOptionChosen = true;
                        if ((city.equals("enschede") || city.equals("Enschede") || city.equals("ENSCHEDE")) && (type.equals("havo") || type.equals("HAVO")))
                            printSchoolNames("ENSCHEDE", "HAVO"); validOptionChosen = true;
                        if ((city.equals("enschede") || city.equals("Enschede") || city.equals("ENSCHEDE")) && (type.equals("vwo") || type.equals("VWO")))
                            printSchoolNames("ENSCHEDE", "VWO"); validOptionChosen = true;
                    case 0:
                        validOptionChosen = true;
                        break;
                    default:
                        SaxionApp.printLine("This option is not provided, please try again");
            }
        }
    }
}
