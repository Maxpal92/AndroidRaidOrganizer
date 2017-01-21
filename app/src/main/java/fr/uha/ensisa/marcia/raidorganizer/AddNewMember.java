package fr.uha.ensisa.marcia.raidorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.id;

public class AddNewMember extends AppCompatActivity {

    private EditText nameEdit;
    private EditText dpsEdit;
    private EditText noteEdit;
    private EditText ilvlEdit;
    private EditText phoneNumber;
    private Spinner classSpinner;
    private Spinner metier1Spinner;
    private Spinner metier2Spinner;

    private ArrayAdapter<CharSequence> spinnerAdapter;
    private ArrayAdapter<CharSequence> spinnerAdapter2;
    private int classId = MemberClass.PALADIN;
    private int spe = MemberClass.DPS;
    private int metier1 = Profession.ALCHEMY;
    private int metier2 = Profession.ARCHEOLOGIE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_member);

        nameEdit = (EditText) findViewById(R.id.editMemberName);
        dpsEdit = (EditText) findViewById(R.id.editMemberDps);
        noteEdit = (EditText) findViewById(R.id.editMemberNote);
        ilvlEdit = (EditText) findViewById(R.id.ilvlEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);

        classSpinner = (Spinner) findViewById(R.id.classSpinner);
        spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.classes_names,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(spinnerAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()){
                    case "Paladin":
                        classId=MemberClass.PALADIN;
                        break;
                    case "Mage":
                        classId=MemberClass.MAGE;
                        break;
                    case "Warrior":
                        classId=MemberClass.WARRIOR;
                        break;
                    case "Hunter":
                        classId=MemberClass.HUNTER;
                        break;
                    case "Warlock":
                        classId=MemberClass.WARLOCK;
                        break;
                    case "Death Knight":
                        classId=MemberClass.DEATHNIGHT;
                        break;
                    case "Demon Hunter":
                        classId=MemberClass.DEATHNIGHT;
                        break;
                    case "Rogue":
                        classId=MemberClass.ROGUE;
                        break;
                    case "Druid":
                        classId=MemberClass.DRUID;
                        break;
                    case "Shaman":
                        classId=MemberClass.SHAMAN;
                        break;
                    case "Priest":
                        classId=MemberClass.PRIEST;
                        break;
                    default:
                        System.out.println("There may be a problem");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        metier1Spinner = (Spinner) findViewById(R.id.metier1Spinner);
        spinnerAdapter2 = ArrayAdapter.createFromResource(this,R.array.professions,android.R.layout.simple_spinner_item);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metier1Spinner.setAdapter(spinnerAdapter2);
        metier1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()){
                    case "Alchemy" :
                        metier1 = Profession.ALCHEMY;
                        break;
                    case "Archeologie" :
                        metier1 = Profession.ARCHEOLOGIE;
                        break;
                    case "Blacksmithing" :
                        metier1 = Profession.BLACKSMITHING;
                        break;
                    case "Cooking" :
                        metier1 = Profession.COOKING;
                        break;
                    case "Enchanting" :
                        metier1 = Profession.ENCHANTING;
                        break;
                    case "Engineering" :
                        metier1 = Profession.ENGINEERING;
                        break;
                    case "Firs-aid" :
                        metier1 = Profession.FIRSTAID;
                        break;
                    case "Fishing" :
                        metier1 = Profession.FISHINF;
                        break;
                    case "Herbalism" :
                        metier1 = Profession.HERBALISM;
                        break;
                    case "Jewelcrafting" :
                        metier1 = Profession.JEWELCRAFTING;
                        break;
                    case "Mining" :
                        metier1 = Profession.MINING;
                        break;
                    case "Skinning" :
                        metier1 = Profession.SKINNING;
                        break;
                    case "Tailoring" :
                        metier1 = Profession.TAILORING;
                        break;
                    case "No profession" :
                        metier1 = 0;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        metier2Spinner = (Spinner) findViewById(R.id.metier2Spinner);
        metier2Spinner.setAdapter(spinnerAdapter2);
        metier2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()){
                    case "Alchemy" :
                        metier2 = Profession.ALCHEMY;
                        break;
                    case "Archeologie" :
                        metier2 = Profession.ARCHEOLOGIE;
                        break;
                    case "Blacksmithing" :
                        metier2 = Profession.BLACKSMITHING;
                        break;
                    case "Cooking" :
                        metier2 = Profession.COOKING;
                        break;
                    case "Enchanting" :
                        metier2 = Profession.ENCHANTING;
                        break;
                    case "Engineering" :
                        metier2 = Profession.ENGINEERING;
                        break;
                    case "Firs-aid" :
                        metier2 = Profession.FIRSTAID;
                        break;
                    case "Fishing" :
                        metier2 = Profession.FISHINF;
                        break;
                    case "Herbalism" :
                        metier2 = Profession.HERBALISM;
                        break;
                    case "Jewelcrafting" :
                        metier2 = Profession.JEWELCRAFTING;
                        break;
                    case "Mining" :
                        metier2 = Profession.MINING;
                        break;
                    case "Skinning" :
                        metier2 = Profession.SKINNING;
                        break;
                    case "Tailoring" :
                        metier2 = Profession.TAILORING;
                        break;
                    case "No profession" :
                        metier1 = 0;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Intent intent = getIntent();

        if(intent.hasExtra("memberToEdit")){
            Member currentMember = intent.getParcelableExtra("memberToEdit");
            nameEdit.setText(currentMember.getName());
            dpsEdit.setText(Integer.toString(currentMember.getDps()));
            noteEdit.setText(currentMember.getNote());
            ilvlEdit.setText(Integer.toString(currentMember.getIlvl()));
            phoneNumber.setText(Integer.toString(currentMember.getPhoneNumber()));
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.isDPSRadioButton:
                if (checked)
                    spe = MemberClass.DPS;
                    break;
            case R.id.isHealRadioButton:
                if (checked)
                    spe = MemberClass.HEALER;
                    break;
            case R.id.isTankRadioButton:
                if(checked)
                    spe = MemberClass.TANK;
                break;
            default: spe = MemberClass.DPS;
        }
    }

    protected void onValid(View v){
        Member member = new Member();

        if(nameEdit.length()==0){
            member.setName("No name");
        }else member.setName(nameEdit.getText().toString());

        if(noteEdit.length() == 0){
            member.setNote("No note");
        }else member.setNote(noteEdit.getText().toString());

        try {
            String dps = String.valueOf(dpsEdit.getText());
            member.setDps(Integer.parseInt(dps));
            member.setPhoneNumber(Integer.parseInt(phoneNumber.getText().toString()));
        }
        catch(NumberFormatException e){
            System.out.println("Only integers are avaible");
            member.setDps(0);
            member.setPhoneNumber(5556);
        }
        try{
            String ilvl = String.valueOf(ilvlEdit.getText());
            member.setIlvl(Integer.parseInt(ilvl));
        }
        catch (NumberFormatException e){
            member.setIlvl(0);
        }

        member.setClasseId(classId);
        member.setSpecialisation(spe);
        member.setMetier1(metier1);
        member.setMetier2(metier2);
        Intent intent = getIntent();
        intent.putExtra("newMember",member);
        setResult(RESULT_OK,intent);
        System.out.println("test");
        finish();
    }
}
