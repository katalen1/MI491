import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by bigka on 12/14/2015.
 */
public class Landscape {
    Grows landscape[][];
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
        this.landscape = new Grows[100][100];
        this.rows = 50;
        this.columns = 50;
        this.steps = 100;
        this.grass = 20;
        this.sheep = 20;
        this.wolf = 20;
    }

    public Landscape(int rows, int columns, int steps, int grass, int sheep, int wolf){
        this.landscape = new Grows[rows][columns];
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
                this.landscape[i][j] = null;
            }
        }
    }

    public void delete(int X, int Y){
        this.landscape[X][Y] = null;
    }

    public void populate(int grass, int sheep, int wolf){
        Random rand = new Random();
        while (grass !=0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if(this.landscape[randomRow][randomColumn] == null){
                this.landscape[randomRow][randomColumn] = new Grass();
                grass -=1;
            }
        }

        while (sheep !=0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if(this.landscape[randomRow][randomColumn] == null){
                this.landscape[randomRow][randomColumn] = new Sheep();
                sheep -=1;
            }
        }

        while (wolf !=0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if(this.landscape[randomRow][randomColumn] == null){
                this.landscape[randomRow][randomColumn] = new Wolf();
                wolf -=1;
            }
        }

    }

    public void count(){
        this.grassCount = 0;
        this.sheepCount = 0;
        this.wolfCount = 0;

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
        Random rand = new Random();
        ArrayList<Integer> openRow = new ArrayList<>();
        ArrayList<Integer> openCol = new ArrayList<>();
        try {
            for (int i = (X - 1) % this.rows; i < X + 1; i++) {
                if (i < 0) {
                    i = 100 + i;
                }
                for (int j = (Y - 1) % this.columns; j < Y + 1; j++) {
                    if (j < 0) {
                        j = 100 + i;
                    }
                    if (this.landscape[i][j] == null) {
                        openRow.add(i);
                        openCol.add(j);
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
        if(openRow.size() != 0) {
            int randomTile = rand.nextInt(openRow.size());
            int row = openRow.get(randomTile);
            int col = openCol.get(randomTile);
            delete(row, col);
            this.landscape[row][col] = new Grass();
        }
    }

    public void move(int x, int y){
        if(landscape[x][y] instanceof Eats){
            if(((Eats)landscape[x][y]).getHunger() <=5){
                search(x,y);
            }else if(((Eats)landscape[x][y]).getHunger() > 5 && ((Eats)landscape[x][y]).getReprodCounter() == 0 && landscape[x][y].getSize() >3){
                reproduce(x,y);
            }else{
                Random rand = new Random();
                ArrayList<Integer> openRow = new ArrayList<>();
                ArrayList<Integer> openCol = new ArrayList<>();
                try {
                    for (int i = (x - ((Eats)landscape[x][y]).getSearchRad()) % this.rows; i < x + ((Eats)landscape[x][y]).getSearchRad(); i++) {
                        if (i < 0) {
                            i = 100 + i;
                        }
                        for (int j = (y - ((Eats)landscape[x][y]).getSearchRad()) % this.columns; j < y + ((Eats)landscape[x][y]).getSearchRad(); j++) {
                            if (j < 0) {
                                j = 100 + i;
                            }
                            if (this.landscape[i][j] == null) {
                                openRow.add(i);
                                openCol.add(j);
                            }
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){

                }
                if(openRow.size() != 0) {
                    int randomTile = rand.nextInt(openRow.size());
                    int row = openRow.get(randomTile);
                    int col = openCol.get(randomTile);
                    this.landscape[row][col] = landscape[x][y];
                    delete(x,y);
                }
            }
        }
    }

    public void search(int x, int y){
        if(landscape[x][y] instanceof Sheep){
            Random rand = new Random();
            ArrayList<Integer> grassRow = new ArrayList<>();
            ArrayList<Integer> grassCol = new ArrayList<>();
            ArrayList<Integer> openRow = new ArrayList<>();
            ArrayList<Integer> openCol = new ArrayList<>();
            try {
                for (int i = (x - 2) % this.rows; i < x + 2; i++) {
                    if (i < 0) {
                        i = 100 + i;
                    }
                    for (int j = (y - 2) % this.columns; j < y + 2; j++) {
                        if (j < 0) {
                            j = 100 + i;
                        }
                        if (this.landscape[i][j] instanceof Grass) {
                            grassRow.add(i);
                            grassCol.add(j);
                        }else if (this.landscape[i][j] == null) {
                            openRow.add(i);
                            openCol.add(j);
                        }
                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){

            }

            if(grassRow.size() != 0) {
                int randomTile = rand.nextInt(grassRow.size());
                int row = grassRow.get(randomTile);
                int col = grassCol.get(randomTile);
                int size = landscape[row][col].getSize();
                delete(row, col);
                this.landscape[row][col] = this.landscape[x][y];
                delete(x,y);
                ((Eats)landscape[row][col]).eats(size);
                System.out.println("Sheep at " +x+ " " +y+ " move to " +row+ " " +col+ " ate grass size " +size+ " so hunger" + ((Eats)landscape[row][col]).getHunger());
            }else if(openRow.size() != 0) {
                int randomTile = rand.nextInt(openRow.size());
                int row = openRow.get(randomTile);
                int col = openCol.get(randomTile);
                this.landscape[row][col] = landscape[x][y];
                delete(x,y);
            }
        }else if(landscape[x][y] instanceof Wolf){
            Random rand = new Random();
            ArrayList<Integer> sheepRow = new ArrayList<>();
            ArrayList<Integer> sheepCol = new ArrayList<>();
            ArrayList<Integer> openRow = new ArrayList<>();
            ArrayList<Integer> openCol = new ArrayList<>();
            try {
                for (int i = (x - 3) % this.rows; i < x + 3; i++) {
                    if (i < 0) {
                        i = 100 + i;
                    }
                    for (int j = (y - 3) % this.columns; j < y + 3; j++) {
                        if (j < 0) {
                            j = 100 + i;
                        }
                        if (this.landscape[i][j] instanceof Sheep) {
                            sheepRow.add(i);
                            sheepCol.add(j);
                        }else if (this.landscape[i][j] == null) {
                            openRow.add(i);
                            openCol.add(j);
                        }
                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){

            }

            if(sheepRow.size() != 0) {
                int randomTile = rand.nextInt(sheepRow.size());
                int row = sheepRow.get(randomTile);
                int col = sheepCol.get(randomTile);
                delete(row, col);
                this.landscape[row][col] = this.landscape[x][y];
                delete(x,y);
                ((Eats)landscape[row][col]).eats(10);
                System.out.println("Wolf at " +x+ " " +y+ " move to " +row+ " " +col+ " ate sheep so hunger " + ((Eats)landscape[row][col]).getHunger());
            }else if(openRow.size() != 0) {
                int randomTile = rand.nextInt(openRow.size());
                int row = openRow.get(randomTile);
                int col = openCol.get(randomTile);
                delete(row, col);
                this.landscape[row][col] = landscape[x][y];
                delete(x,y);
            }
        }
    }

    public void reproduce(int x, int y){
        Random rand = new Random();
        ArrayList<Integer> openRow = new ArrayList<>();
        ArrayList<Integer> openCol = new ArrayList<>();
        boolean oppGenderPresent = false;
        try {
            for (int i = (x - 1) % this.rows; i < x + 1; i++) {
                if (i < 0) {
                    i = 100 + i;
                }
                for (int j = (y - 1) % this.columns; j < y + 1; j++) {
                    if (j < 0) {
                        j = 100 + i;
                    }
                    if (this.landscape[i][j] == null) {
                        openRow.add(i);
                        openCol.add(j);
                    } else if (this.landscape[x][y] instanceof Sheep) {
                        if (this.landscape[i][j] instanceof Sheep && ((Eats) landscape[x][y]).getGender() != ((Eats) landscape[i][j]).getGender()) {
                            oppGenderPresent = true;
                        }
                    } else if (this.landscape[x][y] instanceof Wolf) {
                        if (this.landscape[i][j] instanceof Wolf && ((Eats) landscape[x][y]).getGender() != ((Eats) landscape[i][j]).getGender()) {
                            oppGenderPresent = true;
                        }
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
        if(openRow.size() != 0 && oppGenderPresent == true) {
            int randomTile = rand.nextInt(openRow.size());
            int row = openRow.get(randomTile);
            int col = openCol.get(randomTile);
            if(this.landscape[x][y] instanceof Sheep) {
                this.landscape[row][col] = new Sheep();
                System.out.println("Sheep at " +x+" "+y+" and "+row+" "+col+" reproduced");
            }else if(this.landscape[x][y] instanceof Wolf){
                this.landscape[row][col] = new Wolf();
                System.out.println("Sheep at " +x+" "+y+" and "+row+" "+col+" reproduced");
            }
        }
    }

    public void step() {
        int step = this.steps;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.landscape[i][j] instanceof Grass) {
                    if(step%2==0 && landscape[i][j].getSize() > 2) {
                        spread(i, j);
                    }
                    landscape[i][j].grow();
                }else if(this.landscape[i][j] instanceof Eats){
                    if(landscape[i][j].getSize() > 15 || ((Eats)landscape[i][j]).getHunger() == 0){
                        if(landscape[i][j]instanceof Sheep) {
                            System.out.println("Sheep at " + i + " " + j + " dies");
                        }else if(landscape[i][j] instanceof Wolf){
                            System.out.println("Wolf at " + i + " " + j + " dies");
                        }
                        delete(i,j);
                    }else {
                        landscape[i][j].grow();
                        move(i, j);
                    }
                }
            }
        }
    }
}
