package pl.wsei.mobilne.profesjonalnaaplikacja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartGame(View v){
        Toast.makeText(getApplicationContext(), "your game is starting...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        startActivity(i);
    }
}