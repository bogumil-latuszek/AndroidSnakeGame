package pl.wsei.mobilne.profesjonalnaaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class GameActivity extends AppCompatActivity {

    private GridBoard gridBoard;
    private int score;
    private Snake snake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridBoard = new GridBoard();
        TextView tv1 = findViewById(R.id.textView1);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        TextView tv5 = findViewById(R.id.textView5);
        TextView tv6 = findViewById(R.id.textView6);
        TextView tv7 = findViewById(R.id.textView7);
        TextView tv8 = findViewById(R.id.textView8);
        TextView tv9 = findViewById(R.id.textView9);
        TextView tv10 = findViewById(R.id.textView10);
        TextView tv11 = findViewById(R.id.textView11);
        TextView tv12 = findViewById(R.id.textView12);
        TextView tv13 = findViewById(R.id.textView13);
        TextView tv14 = findViewById(R.id.textView14);
        TextView tv15 = findViewById(R.id.textView15);
        TextView tv16 = findViewById(R.id.textView16);
        TextView tv17 = findViewById(R.id.textView17);
        TextView tv18 = findViewById(R.id.textView18);
        TextView tv19 = findViewById(R.id.textView19);
        TextView tv20 = findViewById(R.id.textView20);
        TextView tv21 = findViewById(R.id.textView21);
        TextView tv22 = findViewById(R.id.textView22);
        TextView tv23 = findViewById(R.id.textView23);
        TextView tv24 = findViewById(R.id.textView24);
        TextView tv25 = findViewById(R.id.textView25);
        TextView[] textViews = new TextView[]{tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,tv19,tv20,tv21,tv22,tv23,tv24,tv25};
        gridBoard.AddViews(textViews);

        for( TextView tv : textViews){
            tv.setText("");
        }
        snake = new Snake(gridBoard.cellsLinear[5], "o");
        //System.out.println("snake head coordinates");
        //System.out.println(snake.snakeBody.getFirst().X + ", " +snake.snakeBody.getFirst().Y);

        for( TextView tv : textViews){
            tv.setOnTouchListener(new OnSwipeTouchListener(GameActivity.this) {
                public void onSwipeTop() {
                    snake.ChangeDirection(Direction.down);
                }
                public void onSwipeRight() {
                    snake.ChangeDirection(Direction.right);
                }
                public void onSwipeLeft() {
                    snake.ChangeDirection(Direction.left);
                }
                public void onSwipeBottom() {
                    snake.ChangeDirection(Direction.up);
                }

            });
        }

        score = 0;

        final Handler handler = new Handler();
        final int delay = 1000; // 1000 milliseconds == 1 second
        handler.postDelayed(new Runnable() {
            public void run() {
                boolean gameEnded = GameFrame(); // main code
                if(!gameEnded){
                    handler.postDelayed(this, delay);
                }
                else{
                    Intent i = new Intent(GameActivity.this, GameOverActivity.class);
                    startActivity(i);
                }
            }
        }, delay);




    }
    //frame
    public boolean GameFrame(){

        //going out of bounds
        if( !gridBoard.HasCell(snake.MoveToX(), snake.MoveToY()) ){
            return true;
        }
        Cell nextCell = gridBoard.GetCell(snake.MoveToX(), snake.MoveToY());
        //collision with itself
        if(snake.hasCell(nextCell)){
            if(nextCell.IsEqual(snake.snakeBody.getLast())){
                return true;
            }
        }
        snake.ProcessCell(nextCell);
        //than generate new food:
        gridBoard.TryGeneratingFood(snake.SnakeBodyToArray());
        //than update the map??
        return false;
    }

}