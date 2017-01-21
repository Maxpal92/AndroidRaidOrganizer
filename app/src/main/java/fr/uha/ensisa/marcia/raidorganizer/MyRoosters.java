package fr.uha.ensisa.marcia.raidorganizer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyRoosters extends AppCompatActivity {

    private static Model model = new Model().populate();
    private ListView listRoosters;
    private RoostersAdapter adapter;
    private static final int MEMBERS_LIST = 1;
    private static final int ROOSTER_EDIT = 2;
    private static final int SEND_MESSAGE_TO_ROOSTER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_roosters);
        Intent intent = getIntent();

        if(intent.getParcelableExtra("newRooster") != null) {

            Rooster r = intent.getParcelableExtra("newRooster");
            model.getRoosters().add(r);

        }

        listRoosters = (ListView) findViewById(R.id.listRoosters);
        adapter = new RoostersAdapter(getApplicationContext(), model.getRoosters());
        listRoosters.setAdapter(adapter);

        listRoosters.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MyRoostersMembers.class);
                intent.putExtra("roosterFromMyRoosters",model.getRoosters().get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent,MEMBERS_LIST);
            }
        });

        registerForContextMenu(listRoosters);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.id_delete:
                model.getRoosters().remove(info.position);
                adapter.notifyDataSetChanged();

                return true;

            case R.id.id_edit:
                Intent intent = new Intent(getApplicationContext(), CreateNewRooster.class);
                intent.putExtra("roosterToEdit",model.getRoosters().get(info.position));
                intent.putExtra("position",info.position);
                startActivityForResult(intent, ROOSTER_EDIT);
                return true;

            case R.id.id_close:
                return true;
            case R.id.id_SendMessage:
                Intent intentSendMessage = new Intent(getApplicationContext(), SendMessage.class);
                intentSendMessage.putExtra("sendMessageToRooster",model.getRoosters().get(info.position));
                startActivityForResult(intentSendMessage,SEND_MESSAGE_TO_ROOSTER);
                Toast.makeText(getApplicationContext(),"Message Sended", Toast.LENGTH_SHORT).show();
                return true;


            default:
                return super.onContextItemSelected(item);
        }


    }


    /**
     Permet de rajouter un menu contextuel pour la listVIew des membres du rooster. pour cela il faut override la methode
     OnCreateContextMenu et ensuite la lier à la listView
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.members_contextual_menu,menu);
    }



    // Creation de l'adapter qui permet de convertir un Rooster en une vue View avec la methode getView()
    public class RoostersAdapter extends ArrayAdapter<Rooster> {

        public RoostersAdapter(Context context, List<Rooster> list) {
            super(context, android.R.layout.simple_list_item_1, list);  //explication de simple_list_item_1 ?
        }

        public View getView(int position, View convertView, ViewGroup parent){

            // Recupère les données pour l'objet situé à cette postion
            Rooster rooster = getItem(position);
            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.rooster_item, parent, false);
            }
            // Lookup view for data population (Associe les TextView TvName etc... à ceux de rooster_item.xml)
            TextView TvName = (TextView) convertView.findViewById(R.id.name);
            TextView TvDescription = (TextView) convertView.findViewById(R.id.description);

            // Populate the data into the template view using the data object
            TvName.setText(rooster.getName());
            TvDescription.setText(rooster.getDescription());

            return convertView;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adapter.notifyDataSetChanged();

        if(requestCode == MEMBERS_LIST && resultCode == RESULT_OK ){

            Rooster rooster = data.getParcelableExtra("roosterFromAddNewMember");
            int position = data.getIntExtra("position",-1);

            if(position!=-1){
                model.getRoosters().remove(position);
                model.getRoosters().add(rooster);
            }
        }

        if(requestCode == ROOSTER_EDIT && resultCode == RESULT_OK){

            Rooster rooster = data.getParcelableExtra("rooster");
            int position = data.getIntExtra("position",-1);

            if(position!=-1){
                model.getRoosters().remove(position);
                model.getRoosters().add(rooster);
            }
        }
    }
}
