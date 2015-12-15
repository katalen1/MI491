/**
 * Created by bigka on 12/14/2015.
 */
public interface Grows {

    void grow(); //increase age or size
    int getSize(); //also used to find age
    void reproduced(); //sheep/wolf/grass has reproduced/spread
    int getReprodCounter(); //return the value of the number of times a grass object has reproduced or if a sheep/wolf has reproduced in the last 5 turns
}
