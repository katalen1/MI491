import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by bigka on 12/14/2015.
 */
public class Landscape {
    Object landscape[][];
    int rows;
    int columns;
    int steps;
    int grass;
    int sheep;
    int wolf;
    int grassCount;
    int sheepCount;
    int wolfCount;

    //Constructors
    public Landscape(){
        this.landscape = new Object[100][100];
        this.rows = 100;
        this.columns = 100;
        this.steps = 100;
        this.grass = 20;
        this.sheep = 20;
        this.wolf = 20;
    }

    public Landscape(int rows, int columns, int steps, int grass, int sheep, int wolf){
        this.landscape = new Object[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.steps = steps;
        this.grass = grass;
        this.sheep = sheep;
        this.wolf = wolf;
    }

    //Methods
    public int getRows(){
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getGrassCount(){
        return this.grassCount;
    }

    public int getSheepCount(){
        return this.sheepCount;
    }

    public int getWolfCount(){
        return this.wolfCount;
    }

    public void clear(){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.columns; j++){
                this.landscape[i][j] = " ";
            }
        }
    }

    public void delete(int X, int Y){
        this.landscape[X][Y] = " ";
    }

    public void populate(int grass, int sheep, int wolf){
        Random rand = new Random();
        while (grass !=0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if(this.landscape[randomRow][randomColumn] == " "){
                this.landscape[randomRow][randomColumn] = new Grass();
                grass -=1;
            }
        }

        while (sheep !=0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if(this.landscape[randomRow][randomColumn] == " "){
                this.landscape[randomRow][randomColumn] = new Sheep();
                sheep -=1;
            }
        }

        while (wolf !=0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if(this.landscape[randomRow][randomColumn] == " "){
                this.landscape[randomRow][randomColumn] = new Wolf();
                wolf -=1;
            }
        }

    }

    public void count(){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.columns; j++){
                if(this.landscape[i][j] instanceof Grass){
                    this.grassCount +=1;
                }else if(this.landscape[i][j] instanceof Sheep){
                    this.sheepCount +=1;
                }else if(this.landscape[i][j] instanceof Wolf){
                    this.wolfCount +=1;
                }
            }
        }
    }

    public void print(){
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.columns; j++){
                if(this.landscape[i][j] instanceof Grass){
                    System.out.print("*");
                }else if(this.landscape[i][j] instanceof Sheep){
                    System.out.print("S");
                }else if(this.landscape[i][j] instanceof Wolf){
                    System.out.print("W");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void spread(int X, int Y){
        HashMap<Integer, Integer> openTiles = new HashMap();
        Random rand = new Random();
        List openRow = new ArrayList<>();

        for(int i = X-1; i<X+1; i++){
            for(int j = Y-1; i<Y+1; j++){
                if(this.landscape[i%this.rows][j%this.columns] == " "){
                    openTiles.put(i,j);
                    openRow.add(i%this.rows);
                }
            }
        }

        int randomTile = rand.nextInt(openTiles.size());
        int row = (int) openRow.get(randomTile);
        this.landscape[row][openTiles.get(row)] = new Grass();
    }

    public void step() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.landscape[i][j] instanceof Grass) {
                    spread(i, j);
                }
            }
        }
    }
}
