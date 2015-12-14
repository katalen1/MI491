/**
 * Created by bigka on 12/14/2015.
 */
public class Wolf extends Landscape {
    int age;
    int hunger;
    int reprodCounter;
    int searchRad;
    String gender;

    public Wolf(){
        this.age =0;
        this.hunger = 10;
        this.reprodCounter = 0;
        this.searchRad = 3;
        this.gender = setGender();
    }

    public String setGender(){
        double rand = Math.random() * 1;
        if (Math.round(rand) == 1) {
            return "M";
        } else {
            return "F";
        }
    }

    public int getAge() {
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

    public void age(){
        this.age +=1;
    }

    public void eat(){
        this.hunger = 10;
    }

    public void reproduced(){
        this.reprodCounter = 5;
    }

}
