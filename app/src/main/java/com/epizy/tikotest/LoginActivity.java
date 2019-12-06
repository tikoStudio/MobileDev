package com.epizy.tikotest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText player1Name, player2Name;
    private Button startPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find views
        player1Name = (EditText) findViewById(R.id.player1Name);
        player2Name = (EditText) findViewById(R.id.player2Name);

        startPlaying = (Button) findViewById(R.id.buttonNamePicked);

        startPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name1 = player1Name.getText().toString();
                final String name2 = player2Name.getText().toString();

                // toast message if required input fields are empty
                if(name1.isEmpty() || name2.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill in the name fields" ,Toast.LENGTH_LONG).show();

                }else {
                    //make intent to go to next screen + send data (names) with it
                    Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                    mainIntent.putExtra("name1", name1);
                    mainIntent.putExtra("name2", name2);
                    startActivity(mainIntent);
                }

            }
        });


    }
}

