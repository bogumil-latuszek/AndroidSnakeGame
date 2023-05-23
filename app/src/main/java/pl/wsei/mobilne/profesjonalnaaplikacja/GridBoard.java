package pl.wsei.mobilne.profesjonalnaaplikacja;

import android.widget.TextView;

public class GridBoard {

    public boolean foodExists;
    private Cell[][] cells;
    public Cell[] cellsLinear;

    public class DoOnFoodEaten implements IDelegate{

        @Override
        public void Trigger() {
            UpdateFoodStatus(false);
        }
    }

    //private TextView[] views;
    public GridBoard(){
        //views = new TextView[25];
        cells = new Cell[5][5];
        cellsLinear = new Cell[25];
        foodExists = false;
    }
    public boolean HasCell(int x, int y){
        if(x>=0 && x<cells[0].length && y>=0 && y< cells.length){
            return true;
        }
        return false;
    }
    public Cell GetCell(int x, int y){
        return  cells[x][y];
    }
    public  Cell GetCenterCell(){
        int middle = cellsLinear.length / 2;
        return cellsLinear[middle];
    }
    public void UpdateFoodStatus( boolean status){
        foodExists = status;
    }
    public boolean AddViews(TextView[] tv){

        DoOnFoodEaten doOnFoodEaten = new DoOnFoodEaten();

        double squareBase = Math.sqrt(tv.length);
        if(squareBase != Math.floor(squareBase)){ // input number is not a perfect square
            return false;
        }
        int squareBaseWholeNumber = (int)squareBase;
        for(int i = 0; i < squareBase; i ++){
            for(int j = 0; j < squareBase; j ++){
                int numberInSequence = i*squareBaseWholeNumber+j;
                cells[i][j] = new Cell(tv[numberInSequence], i, j, doOnFoodEaten);
                cellsLinear[numberInSequence] = cells[i][j];
            }
        }
        return  true;
    }
    public void TryGeneratingFood(Cell[] spaceOccupied){
        if(!foodExists){
            boolean succeded = GenerateFood(spaceOccupied);
            if(succeded){
                UpdateFoodStatus(true);
            }
        }
    }
    public boolean GenerateFood (Cell[] spaceOccupied){
        int startingNumber = (int)(Math.random()*24) ;
        for(int i = startingNumber; i < cellsLinear.length; i ++){
            boolean isOccupied = false;
            for(Cell occupiedCell : spaceOccupied){
                if( occupiedCell.IsEqual(cellsLinear[i]) ){
                    isOccupied = true;
                }
            }
            if(!isOccupied){
                cellsLinear[i].PlaceFood();
                return true;
            }
        }
        return false;
    }
}
