package fr.uha.ensisa.marcia.raidorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateNewRooster extends AppCompatActivity {

    private EditText roosterNameEdit;
    private EditText roosterDescriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_rooster);

        roosterNameEdit = (EditText) findViewById(R.id.roosterNameEdit);
        roosterDescriptionEdit = (EditText) findViewById(R.id.roosterDescriptionEdit);

        Intent intent = getIntent();

        if(intent.hasExtra("roosterToEdit")){
            Rooster currentRooster = intent.getParcelableExtra("roosterToEdit");
            roosterNameEdit.setText(currentRooster.getName());
            roosterDescriptionEdit.setText(currentRooster.getDescription());
        }

    }

    // onValid a été bundé avec le bouton valid
    protected void onValid(View v) {
        Intent intent = getIntent();
        Rooster rooster = new Rooster(roosterNameEdit.getText().toString());
        rooster.setDescription(roosterDescriptionEdit.getText().toString());

        intent.putExtra("rooster", rooster);
        setResult(RESULT_OK, intent);
        finish();
    }
}
