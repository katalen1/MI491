import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by bigka on 12/14/2015.
 */
public class main {

    public static void main(String args[]) {
        Landscape landscape = new Landscape();
        landscape.clear();
        landscape.populate(250,500,0);
        landscape.count();
        landscape.print();
        System.out.println("Grass count: " +landscape.getGrassCount());
        System.out.println("Sheep count: " +landscape.getSheepCount() +" Male: " +landscape.getMSheep()+ " Female: " +landscape.getFSheep());
        System.out.println("Wolf count: " +landscape.getWolfCount()+" Male: " +landscape.getMWolf()+ " Female: " +landscape.getFWolf());


        for(int i=0; i<20; i++){
            landscape.step();

            if(i%3==0){
                landscape.count();
                System.out.println("Grass count: " +landscape.getGrassCount());
                System.out.println("Sheep count: " +landscape.getSheepCount());
                System.out.println("Wolf count: " +landscape.getWolfCount());
            }
        }
        landscape.count();
        System.out.println("Grass count: " +landscape.getGrassCount());
        System.out.println("Sheep count: " +landscape.getSheepCount());
        System.out.println("Wolf count: " +landscape.getWolfCount());
    }
}
