
/**
 * Created by bigka on 12/14/2015.
 */
public class main {

    public static void main(String args[]) {
        Landscape landscape = new Landscape();
        landscape.populate(20,20,20);
        landscape.count();
        landscape.print();
        System.out.println("Grass count: " +landscape.getGrassCount());
        System.out.println("Sheep count: " +landscape.getSheepCount());
        System.out.println("Wolf count: " +landscape.getWolfCount());
    }
}
