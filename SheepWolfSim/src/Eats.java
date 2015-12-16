
/**
 * Created by bigka on 12/14/2015.
 */
public interface Eats {
    void eats(int size);//increase the hunger pool of the wolf/sheep based on size of what they ate
    int getHunger(); //return value of objects current hunger pool
    int getSearchRad(); //range in which the object can search for food sources
    String getGender(); //return string value of gender for use in searching for a mate for reproduction
}
