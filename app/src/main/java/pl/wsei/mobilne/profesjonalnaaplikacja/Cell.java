package pl.wsei.mobilne.profesjonalnaaplikacja;

import android.widget.TextView;

public class Cell {
    public TextView textView;
    public boolean HasFood;

    private IDelegate onFoodEaten;

    final int X;
    final int Y;

    public Cell(TextView graphicalRepresentation, int x, int y, IDelegate OnFoodEaten){
        HasFood = false;
        textView = graphicalRepresentation;
        X = x;
        Y = y;
        onFoodEaten = OnFoodEaten;
    }

    public void PlaceFood(){
        HasFood = true;
        textView.setText("X");
    }
    public boolean IsEqual(Cell otherCell){
        if(this.X == otherCell.X && this.Y == otherCell.Y){
            return true;
        }
        return false;
    }

    public boolean HasSamePositionAs(int x, int y){
        if(this.X == x && this.Y == y){
            return true;
        }
        return false;
    }

    public void EatFood(){
        HasFood = false;
        textView.setText("");
        onFoodEaten.Trigger();
    }



}
