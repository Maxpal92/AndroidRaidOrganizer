package fr.uha.ensisa.marcia.raidorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ROOSTER = 1;
    private static final int MY_ROOSTERS = 2;
    private Rooster roosterFromCreateNewRooster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createRooster = (Button) findViewById(R.id.CreateRooster);
        createRooster.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateNewRooster.class);
                startActivityForResult(intent, ADD_ROOSTER);
            }
        });

        Button myRoosters = (Button) findViewById((R.id.MyRoosters));
        myRoosters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MyRoosters.class);

                if (roosterFromCreateNewRooster != null) {
                    intent.putExtra("newRooster", roosterFromCreateNewRooster);
                    roosterFromCreateNewRooster = null;
                }

                startActivityForResult(intent, MY_ROOSTERS);
            }
        });
    }


    // Permet de récupérer le rooster crée depuis l'activité CreateNewRooster
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ROOSTER && resultCode == RESULT_OK) {
            this.roosterFromCreateNewRooster = data.getParcelableExtra("rooster");
        }
    }
}
