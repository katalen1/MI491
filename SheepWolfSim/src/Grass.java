/**
 * Created by bigka on 12/14/2015.
 */
public class Grass implements Grows {
    int size;

    public Grass(){
        this.size = 1;
    }

    public int getSize() {
        return size;
    }

    public void grow(){
        if(this.size < 5){
            this.size += 1;
        }
    }
}
