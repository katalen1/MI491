/**
 * Created by bigka on 12/14/2015.
 */
public class Grass extends Landscape {
    int size;

    public Grass(){
        this.size = 1;
    }

    public void grow(){
        if(this.size < 5){
            this.size += 1;
        }
    }
}
