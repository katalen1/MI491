import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bigka on 12/14/2015.
 */
public class main {

    public static void main(String args[]) {
        boolean cont = true;
        Scanner input = new Scanner(System.in);
        Landscape landscape= new Landscape();
        List<String> data = new ArrayList<>();
        ArrayList<Landscape> landscapes = new ArrayList<>();


        while(cont == true){

            System.out.println();
            System.out.println("To run the simulation please enter R");
            System.out.println("If you'd like to save the counts from each step to a file please enter S (Must be done after the simulation has ran)");
            System.out.println("To print out a specific step from the last simulation please enter P");
            System.out.println("To exit please enter E");
            String next = input.nextLine();
            switch (next){
                case "R":

                    landscapes.clear();
                    landscape.clear();
                    System.out.println("To create your own landscape with custom values please enter C");
                    System.out.println("To run a simulation with the default values please enter D");
                    System.out.println("The default landscape is 50x50 with an initial population of 100 grass, and 50 of each Sheep and Wolves. It runs for 50 turns.");
                    String cust = input.nextLine();
                    if(cust.equals("C")){
                        System.out.println("Please enter the number of rows for the landscape: ");
                        int rows = input.nextInt();
                        System.out.println("Please enter the number of columns for the landscape:");
                        int col = input.nextInt();
                        System.out.println("Please enter the number of initial grass object to populate the landscape with: ");
                        int grass = input.nextInt();
                        System.out.println("Please enter the number of initial sheep object to populate the landscape with: ");
                        int sheep = input.nextInt();
                        System.out.println("Please enter the number of initial wolf object to populate the landscape with: ");
                        int wolf = input.nextInt();
                        System.out.println("Please enter the number of turns you'd like the simulations to run for: ");
                        int steps = input.nextInt();
                        landscape = new Landscape(rows,col,steps,grass,sheep,wolf);
                    }else if(cust.equals("D")){
                        landscape = new Landscape();
                    }else{
                        System.out.println("Invalid input please try again.");
                    }


                    landscape.populate(landscape.getGrass(), landscape.getSheep(), landscape.getWolf());
                    landscape.count();
                    System.out.println("The simulations initial settings:");
                    System.out.println("Rows: " +landscape.getRows()+ " Columns: "+landscape.getColumns()+ " Steps: "+ landscape.getSteps());
                    landscape.getGrassCount();
                    landscape.getSheepCount();
                    landscape.getWolfCount();
                    landscape.print();


                    for(int i=0; i<landscape.getSteps(); i++){
                        landscape.step(i);
                        landscapes.add(landscape);
                        landscape.count();
                        data.add("Step: " +(i+1)+ "\t"+landscape.returnCounts());

                        /*if(landscape.getSheepCnt()<5){  //re-population settings
                            landscape.repopSheep(25);
                        }

                        if(landscape.getWolfCnt()<5){
                            landscape.repopWolf(25);
                        }*/

                        if(i==Math.round(landscape.getSteps()/2)){
                            landscape.print();
                        }
                    }
                    landscape.print();
                    landscape.count();
                    System.out.println("The simulations final stats:");
                    landscape.getGrassCount();
                    landscape.getSheepCount();
                    landscape.getWolfCount();
                    break;

                case "P":

                    System.out.println("Please enter the number of the turn that you would like the system to print out");
                    int turn = input.nextInt();

                    landscape = landscapes.get(turn-1);
                    landscape.print();
                    System.out.println("The simulations stats for turn " +turn+":");
                    landscape.getGrassCount();
                    landscape.getSheepCount();
                    landscape.getWolfCount();
                    break;

                case "S"://have to exit the loop for the file to become available
                    System.out.println("Please enter the desired filename: ");
                    String filename = input.nextLine();

                    try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
                        for(String d: data){
                            out.println(d);
                        }
                        out.close();
                    }catch (IOException e) {
                    }
                    System.out.println("Please note, you will need to exit the simulation to read this file.");
                    break;


                case "E"://have to exit the loop for the file to become available

                    System.out.println("Thanks for playing!");
                    cont=false;
                    break;
            }

        }
    }
}
