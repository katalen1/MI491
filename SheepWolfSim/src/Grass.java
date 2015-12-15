/**
 * Created by bigka on 12/14/2015.
 */
public class Grass implements Grows {
    int size;
    int repCount;

    public Grass(){
        this.size = 1;
        this.repCount=0;
    }

    public int getSize() {
        return size;
    }

    public void grow(){
        if(this.size < 5){ //max size of grass is five, if less than 5 add 1
            this.size += 1;
        }
    }

    public void reproduced() {
        this.repCount+=1;
    }

    @Override
    public int getReprodCounter() {
        return this.repCount;
    }
}
