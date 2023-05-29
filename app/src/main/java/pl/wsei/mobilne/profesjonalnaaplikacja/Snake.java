package pl.wsei.mobilne.profesjonalnaaplikacja;

import java.util.LinkedList;

public class Snake {
    private Direction facingDirection;
    public LinkedList<Cell> snakeBody;
    private String bodySymbol;

    public boolean hasCell(Cell otherCell){
        boolean found = false;
        for(Cell bodyCell: snakeBody){
            if(bodyCell.IsEqual(otherCell)){
                found = true;
                break;
            }
        }
        return  found;
    }

    public Cell[] SnakeBodyToArray(){
        Cell[] snakeBodyArray = new Cell[snakeBody.size()];
        for(int a = 0; a < snakeBody.size(); a++){
            snakeBodyArray[a] = snakeBody.get(a);
        }
        return snakeBodyArray;
    }
    public Snake(Cell firstCell, String BodySymbol){
        snakeBody = new LinkedList<Cell>();
        bodySymbol = BodySymbol;
        firstCell.PlaceFood();
        ProcessCell(firstCell);
        facingDirection = Direction.right;
    }

    public int MoveToX(){
        if (facingDirection == Direction.up){

            return snakeBody.getLast().X + 1;
        }
        if (facingDirection == Direction.down){
            return snakeBody.getLast().X - 1;
        }
        return snakeBody.getLast().X;
    }

    public int MoveToY(){
        if (facingDirection == Direction.right){
            return snakeBody.getLast().Y + 1;
        }
        if (facingDirection == Direction.left){
            return snakeBody.getLast().Y - 1;
        }
        return snakeBody.getLast().Y;
    }


    public void ProcessCell(Cell cell){
        System.out.println("new cell:");
        System.out.println(cell.X +", " + cell.Y + ", has food:" + cell.HasFood);
        if(cell.HasFood){
            cell.EatFood();
            cell.textView.setText(bodySymbol);
            snakeBody.add(cell);
        }
        else{
            snakeBody.getFirst().textView.setText("");
            snakeBody.removeFirst();
            cell.textView.setText(bodySymbol);
            snakeBody.add(cell);
        }
        for(Cell c : snakeBody){
            System.out.println(c.X +", " + c.Y + ", has food:" + c.HasFood);
        }
    }

    public void ChangeDirection(Direction directionOrder){
        if(snakeBody.size() == 1){
            facingDirection = directionOrder;
            return;
        }
        if(directionOrder == facingDirection){
            return;
        }
        if(directionOrder == Direction.right){
            if(!(facingDirection == Direction.left)){
                facingDirection = directionOrder;
                return;
            }
        }
        if(directionOrder == Direction.left){
            if(!(facingDirection == Direction.right)){
                facingDirection = directionOrder;
                return;
            }
        }
        if(directionOrder == Direction.up){
            if(!(facingDirection == Direction.down)){
                facingDirection = directionOrder;
                return;
            }
        }
        if(directionOrder == Direction.down){
            if(!(facingDirection == Direction.up)){
                facingDirection = directionOrder;
                return;
            }
        }
    }


}
