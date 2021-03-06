
/**
 * Created by bigka on 12/14/2015.
 */
public class Sheep implements Grows, Eats {
    int age;
    int hunger;
    int reprodCounter;
    int searchRad;
    String gender;

    public Sheep(){
        this.age =0;
        this.hunger = 15;
        this.reprodCounter = 0;
        this.searchRad = 2;
        this.gender = setGender();
    }

    public String setGender(){ //randomize gender
        double rand = Math.random() * 1;
        if (Math.round(rand) == 1) {
            return "M";
        } else {
            return "F";
        }
    }

    public int getSize() {
        return this.age;
    }

    public int getHunger() {
        return this.hunger;
    }

    public int getReprodCounter() {
        return this.reprodCounter;
    }

    public int getSearchRad() {
        return this.searchRad;
    }

    public String getGender() {
        return this.gender;
    }

    public void eats(int size){
        this.hunger += size;
        if(this.hunger > 15){
            this.hunger = 15;
        }
    }

    public void grow(){
        this.age+=1;
        if(this.hunger>0) { //each turn minus one from hunger
            this.hunger -= 1;
        }
        if(this.reprodCounter>0) {  //if the wolf has recently reproduced, minus one frm the counter each turn until it reaches 0 again
            this.reprodCounter -= 1;
        }
    }

    public void reproduced(){
        this.reprodCounter = 5;
    }
}
