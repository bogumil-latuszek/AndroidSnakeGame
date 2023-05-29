package pl.wsei.mobilne.profesjonalnaaplikacja;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView scoreValue;
    private TextView scoreLabel;

    private void unhideScore(){
        scoreLabel.setVisibility(View.VISIBLE);
        scoreValue.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreValue = findViewById(R.id.yourScore);
        scoreValue.setVisibility(View.GONE);
        scoreLabel = findViewById(R.id.scoreLabel);
        scoreLabel.setVisibility(View.GONE);
    }

    public void StartGame(View v){
        Toast.makeText(getApplicationContext(), "your game is starting...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        //startActivity(i);
        someActivityResultLauncher.launch(i);
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 123) {
                        // There are no request codes
                        Intent data = result.getData();
                        int score = data.getIntExtra("score", 0);
                        //String score = data.getStringExtra("score");
                        scoreValue.setText(""+score);
                        unhideScore();
                        //Toast.makeText(getApplicationContext(), "score:"+score, Toast.LENGTH_SHORT).show();
                    }
                }
            });


}