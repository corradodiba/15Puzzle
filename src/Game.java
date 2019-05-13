import java.util.ArrayList;
import java.util.Random;

public class Game {

    private int[][] grid;

    public Game() {
        this.grid = new int[4][4];

        this.reset();
        this.shuffle(100);

    }

    public boolean swife(int choiceMovement){

        int x = this.coordsEmptyBox()[0];
        int y = this.coordsEmptyBox()[1];

        switch (choiceMovement){

            case 1:
                    int[] left = this.coordsMovements().get(0);

                    if(left != null) {

                        this.grid[x][y] = this.grid[left[0]][left[1]];
                        this.grid[left[0]][left[1]] = 0;
                        return true;
                    }
            break;

            case 2:

                int[] top = this.coordsMovements().get(1);

                if(top != null) {

                    this.grid[x][y] = this.grid[top[0]][top[1]];
                    this.grid[top[0]][top[1]] = 0;
                    return true;
                }
            break;


            case 3:

                int[] right = this.coordsMovements().get(2);

                if(right != null) {

                    this.grid[x][y] = this.grid[right[0]][right[1]];
                    this.grid[right[0]][right[1]] = 0;
                    return true;
                }
            break;


            case 4:

                int[] bottom = this.coordsMovements().get(3);

                if(bottom != null) {

                    this.grid[x][y] = this.grid[bottom[0]][bottom[1]];
                    this.grid[bottom[0]][bottom[1]] = 0;
                    return true;
                }
            break;
        }

        return false;
    }

    public int[] coordsEmptyBox(){

        int size = this.grid.length;
        int[] coords = new int[2];

        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if (this.grid[x][y] == 0){
                   coords[0] = x;
                   coords[1] = y;
                }
            }
        }
        return coords;
    }

    public ArrayList<int []> coordsMovements(){

        int size = this.grid.length;
        int[] emptyBox = this.coordsEmptyBox();

        int x = emptyBox[0];
        int y = emptyBox[1];


        int[] left = {x, y - 1};
        int[] top = {x - 1, y};
        int[] right = {x, y + 1};
        int[] bottom = {x + 1, y};

        ArrayList<int []> coords = new ArrayList<>();

        if(coordinateControl(left)){ coords.add(left); } else{coords.add(null);}
        if(coordinateControl(top)){ coords.add(top); } else{coords.add(null);}
        if(coordinateControl(right)){ coords.add(right); } else{coords.add(null);}
        if(coordinateControl(bottom)){ coords.add(bottom); } else{coords.add(null);}

        return coords;
    }

    public boolean coordinateControl(int [] coords){

        int size = this.grid.length;

        if((coords[0] < 0) || (coords[0] >= size) || (coords[1] < 0) || (coords[1] >= size)){
            return false;
        }
        return true;
    }

    public void shuffle(int num){

        Random random = new Random();
        int movement;

        for(int i = 0; i < num; i++){
            movement = random.nextInt(5);
            this.swife(movement);
        }

    }

    public void reset(){

        int size = this.grid.length;
        int num = 1;

        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(num != (size * size)){
                    this.grid[x][y] = num;
                    num++;
                }
                else{
                    this.grid[x][y] = 0; //empty box = 0
                }
            }
        }
    }

    public boolean win(){

        int size = this.grid.length;
        int num = 0;

        for(int x = 0; x < size; x++){
            for(int y = 0; y < size && num != (size * size - 1); y++){
                num = this.grid[x][y];
                if((y == (size - 1)) && (this.grid[x + 1][0] == (num + 1))){
                    num++;
                    continue;
                }
                if(this.grid[x][y + 1] != (num + 1)){
                    return false;
                }
                num++;
            }
        }
        return true;
    }

    @Override
    public String toString() {

        String grid = "";
        int size = this.grid.length;

        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                grid += "|\t" + this.grid[x][y] + "\t|";
            }
            grid += "\n\n";
        }
        return grid;
    }
}
