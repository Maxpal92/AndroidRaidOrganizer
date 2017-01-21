package fr.uha.ensisa.marcia.raidorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maxim on 30/12/2016.
 */

public class MemberDetails extends AppCompatActivity {

    private TextView name;
    private TextView classe;
    private TextView spe;
    private TextView metier1;
    private TextView metier2;
    private TextView dps;
    private TextView note;
    private ImageView metier1Image;
    private ImageView metier2Image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);

        name = (TextView) findViewById(R.id.nameDetail);
        classe = (TextView) findViewById(R.id.classeDetail);
        spe = (TextView) findViewById(R.id.specialisationDetail);
        metier1 = (TextView) findViewById(R.id.metierPrincipale);
        metier2 = (TextView) findViewById(R.id.metierSecondaire);
        dps = (TextView) findViewById(R.id.dpsDetail);
        note = (TextView) findViewById(R.id.noteDetail);
        metier1Image = (ImageView) findViewById(R.id.metier1ImageView);
        metier2Image = (ImageView) findViewById(R.id.metier2ImageVIew);

        Intent intent = getIntent();

        if(intent.hasExtra("memberDetail")){
            Member currentMember = intent.getParcelableExtra("memberDetail");
            name.setText(currentMember.getName());
            dps.setText(Integer.toString(currentMember.getDps()));
            note.setText(currentMember.getNote());
            classe.setText(currentMember.getClassString());
            spe.setText(currentMember.getSpecialisationString());
            metier1.setText(currentMember.getProfession1String());
            metier2.setText(currentMember.getProfession2String());
            metier1Image.setImageResource(currentMember.getMetier1());
            metier2Image.setImageResource(currentMember.getMetier2());
        }
    }
}
