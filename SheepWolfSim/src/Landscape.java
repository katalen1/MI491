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
    int mSheep;
    int fSheep;
    int mWolf;
    int fWolf;

    //Constructors
    public Landscape(){
        this.landscape = new Grows[50][50];
        this.rows = 50;
        this.columns = 50;
        this.steps = 50;
        this.grass = 100;
        this.sheep = 50;
        this.wolf = 50;
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

    public int getGrass(){return this.grass;}

    public int getSheep(){return this.sheep;}

    public int getWolf(){return this.wolf;}

    public int getSheepCnt(){return this.sheepCount;}

    public int getWolfCnt(){return this.wolfCount;}

    public void getGrassCount(){
        System.out.println("Grass: " + this.grassCount);
    }

    public void getSheepCount(){
        System.out.println("Sheep: "+ this.sheepCount + " M: "+this.mSheep+ " F: "+this.fSheep);
    }

    public void getWolfCount(){
        System.out.println("Wolves: "+ this.wolfCount + " M: "+this.mWolf+ " F: "+this.fWolf);
    }

    public String returnCounts(){
        return "Grass: " +this.grassCount+ " Sheep: " +this.sheepCount+ " Wolves: " +wolfCount;
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
    }  //delete an eaten object o

    public void repopSheep(int sheep){
        Random rand = new Random();

        while (sheep != 0) {  //same method as above
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if (this.landscape[randomRow][randomColumn] == null) {
                this.landscape[randomRow][randomColumn] = new Sheep();
                sheep -= 1;
            }
        }
    }

    public void repopWolf(int wolf){
        Random rand = new Random();

        while (wolf != 0) {
            int randomRow = rand.nextInt(this.rows);
            int randomColumn = rand.nextInt(this.columns);
            if (this.landscape[randomRow][randomColumn] == null) {
                this.landscape[randomRow][randomColumn] = new Wolf();
                wolf -= 1;
            }
        }
    }

   public void populate(int grass, int sheep, int wolf){

       if(grass+sheep+wolf>this.rows*this.columns){ //make sure the user is not trying to over populate the landscape
           System.out.println("There is not enough room in the Landscape to populate all of these objects.");
       }else {
           Random rand = new Random();
           while (grass != 0) { //randomly populate the landscape
               int randomRow = rand.nextInt(this.rows); //random row value within range
               int randomColumn = rand.nextInt(this.columns); //random column value within range
               if (this.landscape[randomRow][randomColumn] == null) {  //make sure the block is null
                   this.landscape[randomRow][randomColumn] = new Grass(); //populate
                   grass -= 1; //minus on from the initial population total
               }
           }

           while (sheep != 0) {  //same method as above
               int randomRow = rand.nextInt(this.rows);
               int randomColumn = rand.nextInt(this.columns);
               if (this.landscape[randomRow][randomColumn] == null) {
                   this.landscape[randomRow][randomColumn] = new Sheep();
                   sheep -= 1;
               }
           }

           while (wolf != 0) {
               int randomRow = rand.nextInt(this.rows);
               int randomColumn = rand.nextInt(this.columns);
               if (this.landscape[randomRow][randomColumn] == null) {
                   this.landscape[randomRow][randomColumn] = new Wolf();
                   wolf -= 1;
               }
           }
       }
    }

    public void count(){  //find the number of instances of each object
        this.grassCount = 0;
        this.sheepCount = 0;
        this.wolfCount = 0;
        this.mSheep =0;
        this.fSheep =0;
        this.mWolf =0;
        this.fWolf =0;

        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.columns; j++){
                if(this.landscape[i][j] instanceof Grass){
                    this.grassCount +=1;                                    //if grass object found add 1 to count
                }else if(this.landscape[i][j] instanceof Sheep){
                    this.sheepCount +=1;                                    //if sheep object found add 1 to count
                    if(((Eats)this.landscape[i][j]).getGender() == "M"){
                        mSheep+=1;                                          //if male sheep object found add 1 to count
                    }else  if(((Eats)this.landscape[i][j]).getGender() == "F"){
                        fSheep+=1;                                          //if female sheep object found add 1 to count
                    }
                }else if(this.landscape[i][j] instanceof Wolf){
                    this.wolfCount +=1;                                     //if wolf object found add 1 to count
                    if(((Eats)this.landscape[i][j]).getGender() == "M"){
                        mWolf+=1;                                           //if male wolf object found add 1 to count
                    }else  if(((Eats)this.landscape[i][j]).getGender() == "F"){
                        fWolf+=1;                                           //if female wolf object found add 1 to count
                    }
                }
            }
        }
    }

    public void print(){  //iterate through the landscape, when an instance of an object is found print out a designated character that represents the object
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
        ArrayList<Integer> openRow = new ArrayList<>();  //array to hold x cord of open space
        ArrayList<Integer> openCol = new ArrayList<>();  //array to hold y cord of open space

        try {

            for (int i=X-1; i < X + 1; i++) {
                for (int j = Y-1; j < Y + 1; j++) {

                    int k =i;
                    int l =j;
                    if(i<0){                //screen wrapping
                        k= i+this.rows;
                    }else if(i>=this.rows){
                        k=i%this.rows;
                    }
                    if(j<0){
                        l= j+ this.columns;
                    }else if(j>=this.columns){
                        l=j%this.columns;
                    }

                    if (this.landscape[k][l] == null) { //if block is empty add its position to the arrays
                        openRow.add(k); //add x cord to array
                        openCol.add(l); //add y cord to array
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            //e.printStackTrace();    //All instances occur when the value is the max length of row or column, not sure why this occurs.
        }
        if(openRow.size() != 0) { //as long as there is an open space around, pick one randomly and then populate it with a new instance of grass
            int randomTile = rand.nextInt(openRow.size());
            int row = openRow.get(randomTile);
            int col = openCol.get(randomTile);
            this.landscape[row][col] = new Grass();
        }
    }

    public void move(int x, int y){
        if(landscape[x][y] instanceof Eats){
            if(((Eats)landscape[x][y]).getHunger() > 5 && landscape[x][y].getReprodCounter()==0 &&landscape[x][y].getSize()>1){
                reproduce(x,y); //if the the block is an instance of an Eats object (sheep/wolf) and has over 5 hunger, has not reproduced in the last 5 turns and has been on the landscape for over 2 turns, attempt to reproduce
            }else if(((Eats)landscape[x][y]).getHunger() <=7) {
                search(x, y); //if the objects hunger is less than seven actively search for food source
            }else{ //if object does not meet either of the above conditions move randomly to an open space within the objects search radius. Uses same method as spread() for finding open spaces
                Random rand = new Random();
                ArrayList<Integer> openRow = new ArrayList<>();
                ArrayList<Integer> openCol = new ArrayList<>();

                int o = x-((Eats)landscape[x][y]).getSearchRad();
                int p = y-((Eats)landscape[x][y]).getSearchRad();

                if(o<0){
                    o+=this.rows;
                }else if(x>=this.rows){
                    o=o%this.rows;
                }
                if(p<0){
                    p+=this.columns;
                }else if(p>=this.columns){
                    p=p%this.columns;
                }

                try {
                    for (int i = o; i < o + ((Eats)landscape[x][y]).getSearchRad()*2; i++) {
                        for (int j = p; j < p +((Eats)landscape[x][y]).getSearchRad()*2; j++) {
                            if (this.landscape[i][j] == null) {
                                openRow.add(i);
                                openCol.add(j);
                            }
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    //e.printStackTrace();    //All instances occur when the value is the max length of row or column, not sure why this occurs.
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
            ArrayList<Integer> grassRow = new ArrayList<>(); //stores x cord of grass object
            ArrayList<Integer> grassCol = new ArrayList<>(); //store y cord of grass obj
            ArrayList<Integer> openRow = new ArrayList<>(); //stores x cord of open space
            ArrayList<Integer> openCol = new ArrayList<>(); //stores y cord of open space

            try {
                int o = x-2;        //wrapping
                int p = y-2;        //search radius 2

                if(o<0){
                    o+=this.rows;
                }else if(x>=this.rows){
                    o=o%this.rows;
                }
                if(p<0){
                    p+=this.columns;
                }else if(p>=this.columns){
                    p=p%this.columns; //wrapping
                }
                for (int i = o; i < o + 4; i++) {       //search radius 2 so we want x-2 -> x +2 or o -> o+4
                    for (int j = p; j < p + 4; j++) {
                        if (this.landscape[i][j] instanceof Grass) { //search for grass in range
                            grassRow.add(i);    //add x cord of grass
                            grassCol.add(j);    //add y cord of grass
                        }else if (this.landscape[i][j] == null) {
                            openRow.add(i);     //add x cord of open space
                            openCol.add(j);     //add y cord of openspace
                        }
                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){
                //e.printStackTrace();    //All instances occur when the value is the max length of row or column, not sure why this occurs.
            }

            if(grassRow.size() != 0) { //if there is a grass object in range
                int randomTile = rand.nextInt(grassRow.size()); //pick random grass object to eat
                int row = grassRow.get(randomTile); //get x cord
                int col = grassCol.get(randomTile); //get y cord
                int size = landscape[row][col].getSize(); //get size of grass object
                delete(row, col); //delete grass object
                this.landscape[row][col] = this.landscape[x][y]; //move sheep to location of old grass obj
                delete(x,y); //delete the old instance of the sheep that just moved
                ((Eats)landscape[row][col]).eats(size); //add hunger to sheep based on size of grass it just ate
                //System.out.println("Sheep at " +x+ " " +y+ " move to " +row+ " " +col+ " ate grass size " +size+ " so hunger" + ((Eats)landscape[row][col]).getHunger()); //for debugging shows instance of when and where a sheep ate grass
            }else if(openRow.size() != 0) { //if no instance of grass nearby move to random open space
                int randomTile = rand.nextInt(openRow.size());
                int row = openRow.get(randomTile);
                int col = openCol.get(randomTile);
                this.landscape[row][col] = landscape[x][y];
                delete(x,y);
            }
        }else if(landscape[x][y] instanceof Wolf){ //same process as sheep searching for food, only the search radius is 3
            Random rand = new Random();
            ArrayList<Integer> sheepRow = new ArrayList<>();
            ArrayList<Integer> sheepCol = new ArrayList<>();
            ArrayList<Integer> openRow = new ArrayList<>();
            ArrayList<Integer> openCol = new ArrayList<>();
            int o = x-3;        //search radius 3
            int p = y-3;

            if(o<0){
                o+=this.rows;
            }else if(x>=this.rows){
                o=o%this.rows;
            }
            if(p<0){
                p+=this.columns;
            }else if(p>=this.columns){
                p=p%this.columns;
            }

            try {
                for (int i = o; i < o+6; i++) {     //search radius 3 so o-> o+6
                    for (int j = p; j < p + 6; j++) {
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
                //e.printStackTrace();    //All instances occur when the value is the max length of row or column, not sure why this occurs.
            }

            if(sheepRow.size() != 0) { //if sheep in range go eat it
                int randomTile = rand.nextInt(sheepRow.size());
                int row = sheepRow.get(randomTile);
                int col = sheepCol.get(randomTile);
                int size = ((Eats)landscape[row][col]).getHunger(); //size depends on how 'fat' the sheep is
                delete(row, col);
                this.landscape[row][col] = this.landscape[x][y];
                delete(x,y);
                ((Eats)landscape[row][col]).eats(size);
                //System.out.println("Wolf at " +x+ " " +y+ " move to " +row+ " " +col+ " ate sheep so hunger " + ((Eats)landscape[row][col]).getHunger()); //for debugging shows when and where a wolf ate a sheep
            }else if(openRow.size() != 0) { //no sheep in range move randomly
                int randomTile = rand.nextInt(openRow.size());
                int row = openRow.get(randomTile);
                int col = openCol.get(randomTile);
                delete(row, col);
                this.landscape[row][col] = landscape[x][y];
                delete(x,y);
            }
        }
    }

    public void reproduce(int x, int y){    //search for opposite sex of the same object type and reproduce
        Random rand = new Random();
        ArrayList<Integer> openRow = new ArrayList<>();
        ArrayList<Integer> openCol = new ArrayList<>();
        boolean oppGenderPresent = false;
        int o = x-1;        //search radius of 1
        int p = y-1;

        if(o<0){
            o+=this.rows;
        }else if(o>=this.rows){
            o=o%this.rows;
        }
        if(p<0){
            p+=this.columns;
        }else if(p>=this.columns){
            p=p%this.columns;
        }
        try {
            for (int i = o ; i < o + 2; i++) {      //search radius of 1, x-1 -> x+1 or o -> 0+2
                for (int j = p; j < p + 2; j++) {
                    if (this.landscape[i][j] == null) { // find nearby open spaces that can be popuplated
                        openRow.add(i);
                        openCol.add(j);
                    } else if (this.landscape[x][y] instanceof Sheep) { //object is sheep
                        if (this.landscape[i][j] instanceof Sheep && ((Eats) landscape[x][y]).getGender() != ((Eats) landscape[i][j]).getGender()) { //look for other sheep of opposite sex
                            oppGenderPresent = true;
                        }
                    } else if (this.landscape[x][y] instanceof Wolf) { //object is sheep
                        if (this.landscape[i][j] instanceof Wolf && ((Eats) landscape[x][y]).getGender() != ((Eats) landscape[i][j]).getGender()) { //look for other wolf of opposite sex
                            oppGenderPresent = true;
                        }//I found that this is the biggest limiting factor in the reproduction of sheep/wolves. It is extremely hard to guarantee that opposite sexs of the same type will ever come near each other
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            //e.printStackTrace();    //All instances occur when the value is the max length of row or column, not sure why this occurs.
        }
        if(openRow.size() != 0 && oppGenderPresent == true) { //if opposite gender found reproduce
            int randomTile = rand.nextInt(openRow.size()); //pick random emtpy space nearby to populate
            int row = openRow.get(randomTile);  //empty x cord to populate
            int col = openCol.get(randomTile);  //empty y cord to populate
            if(this.landscape[x][y] instanceof Sheep) { //if calling reproduce on a sheep object
                this.landscape[row][col] = new Sheep(); //create new sheep in open space
                this.landscape[x][y].reproduced(); //set the initial sheeps repCounter to 5. Figured this was life like seeing as a male could continue in reproduce but a female couldn't until after birth, so only one object needs it counter set
                //System.out.println("Sheep at " +x+" "+y+" reproduced new sheep at "+row+" "+col+" repCount"+ landscape[x][y].getReprodCounter());  //for debugging tells when a sheep has reproduced and where the new sheep was place
            }else if(this.landscape[x][y] instanceof Wolf){ //same process as above only for wolf
                this.landscape[row][col] = new Wolf();
                this.landscape[x][y].reproduced();
                //System.out.println("Wolf at " +x+" "+y+" reproduced new wolf at "+row+" "+col); //debugging
            }
        }
    }

    public void step(int step) { //call all of the necessary functions on each object once per step. pass value of step for use in controlling rates
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.landscape[i][j] instanceof Grass) { //if grass obj do the following
                    if(landscape[i][j].getSize() >3 &&landscape[i][j].getReprodCounter() <=2) { //grass can spread if it has size >4 and has only reproduced 3 or less times.
                        count();
                        if(this.grassCount<(this.rows*this.columns)/4) { //keep grass population in check by only allowing it to be 1/4 the total area max
                            spread(i, j); //call function to create new grass in available empty space
                            landscape[i][j].reproduced(); //increase the number associated with how many times this plant has reproduced
                        }
                    }else if(step%2==0) {
                        landscape[i][j].grow(); //increase the size of the plant every 2 turns. helps control overgrowth of grass in the beginning
                    }
                }else if(this.landscape[i][j] instanceof Eats){ //if an instance of a sheep or a wolf
                    if(((Eats)landscape[i][j]).getHunger() == 0){ //if hunger equals 0 the object dies, took age death out because it was one of the reasons simulations only lasted 15/20 turns
                        /*if(landscape[i][j]instanceof Sheep) { //used for debugging
                            System.out.println("Sheep at " + i + " " + j + " dies"); //tells when a sheep died from starvation
                        }else if(landscape[i][j] instanceof Wolf){
                            System.out.println("Wolf at " + i + " " + j + " dies"); //tells when a wolf died from starvation
                        }*/
                        delete(i,j); //delete dead object
                    }else { //as long as the sheep/wolf has hunger above 0
                        landscape[i][j].grow(); //increase age, decrease hunger and reproduction counter
                        move(i, j);//call move function that will search for food, reproduce or move randomly based on the objects attribute values
                    }
                }
            }
        }
    }
}


