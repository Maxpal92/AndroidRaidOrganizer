package fr.uha.ensisa.marcia.raidorganizer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendMessage extends AppCompatActivity {

    private EditText message;
    private TextView reciver;
    public final int MY_PERMITIONS_REQUEST_SEND_SMS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        message = (EditText) findViewById(R.id.id_message);
        reciver = (TextView) findViewById(R.id.TextView_reciver);

        Intent intent = getIntent();
        if(intent.hasExtra("sendMessage")){
            Member member = intent.getParcelableExtra("sendMessage");

            reciver.setText(member.getName());
        }
        if(intent.hasExtra("sendMessageToRooster")){
            Rooster rooster = intent.getParcelableExtra("sendMessageToRooster");
            reciver.setText("Message pour le rooster : " + rooster.getName());
        }

    }
    /**
     * Regarde si la permission d'envoyer un sms pour cette application est donnée
     * @return true si la permission est donnée, false si elle est refusée
     */
    public boolean checkPermission(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else return true;
    }

    /**
     * Demande la permission à l'utilisateur, d'envoyer un SMS avec cette application
     */
    public void setPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMITIONS_REQUEST_SEND_SMS);
    }

    /**
     * Gère la reponse de l'utilisateur lors d'une demande de permission.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("ON REQUEST PERMISION RESULT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        switch (requestCode){
            case MY_PERMITIONS_REQUEST_SEND_SMS :
                //On regarde la réponse de l'utilisateur :
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // L'utilisateur a accepté l'utilisation de SMS
                    //sendMessage("5556",message.getText().toString());
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                } else {
                    //L'utilisateur a refusé l'utilisation de sms
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }



    public void OnClick(View v){
        sendMessage(message.getText().toString());
    }

    private void sendMessage(String message) {
            if(checkPermission()) {
                SmsManager smsManager = SmsManager.getDefault();
                if(getIntent().hasExtra("sendMessage")){
                    Intent intent = getIntent();
                    Member member = intent.getParcelableExtra("sendMessage");
                    String PersonnalMessage = "Bonjour " + member.getName() + " !" +"\n" + message;
                    smsManager.sendTextMessage(Integer.toString(member.getPhoneNumber()), null, PersonnalMessage, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sended Succesfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if(getIntent().hasExtra("sendMessageToRooster")){
                    System.out.println("Rooster");
                    Intent intent = getIntent();
                    Rooster rooster = intent.getParcelableExtra("sendMessageToRooster");
                    for(Member member: rooster.getMembers()){
                        String PersonnalMessage = "Message du Raid Leader à tous les membres du raid : " +"\n"+ message;
                        smsManager.sendTextMessage(Integer.toString(member.getPhoneNumber()),null,PersonnalMessage, null, null);
                    }
                    Toast.makeText(getApplicationContext(),"SMS sended to All member Succesfully",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }else{
                setPermission();
            }
    }
}