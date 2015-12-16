
/**
 * Created by bigka on 12/14/2015.
 */
public class main {

    public static void main(String args[]) {
        Landscape landscape = new Landscape();
        landscape.clear();
        landscape.populate(75,200,50);
        landscape.count();
        landscape.print();
        System.out.println("Grass count: " +landscape.getGrassCount());
        System.out.println("Sheep count: " +landscape.getSheepCount() +" Male: " +landscape.getMSheep()+ " Female: " +landscape.getFSheep());
        System.out.println("Wolf count: " +landscape.getWolfCount()+" Male: " +landscape.getMWolf()+ " Female: " +landscape.getFWolf());


        for(int i=0; i<100; i++){
            landscape.step(i);

            if(i%3==0){
                landscape.count();
                System.out.println("Grass count: " +landscape.getGrassCount());
                System.out.println("Sheep count: " +landscape.getSheepCount());
                System.out.println("Wolf count: " +landscape.getWolfCount());
            }
        }
        landscape.print();
        landscape.count();
        System.out.println("Grass count: " +landscape.getGrassCount());
        System.out.println("Sheep count: " +landscape.getSheepCount());
        System.out.println("Wolf count: " +landscape.getWolfCount());
    }
}
