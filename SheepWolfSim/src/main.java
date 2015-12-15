
/**
 * Created by bigka on 12/14/2015.
 */
public class main {

    public static void main(String args[]) {
        Landscape landscape = new Landscape();
        landscape.clear();
        landscape.populate(40,75,50);
        landscape.count();
        landscape.print();
        System.out.println("Grass count: " +landscape.getGrassCount());
        System.out.println("Sheep count: " +landscape.getSheepCount());
        System.out.println("Wolf count: " +landscape.getWolfCount());

        for(int i=0; i<20; i++){
            landscape.step();
            landscape.print();
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
