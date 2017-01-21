package fr.uha.ensisa.marcia.raidorganizer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyRoostersMembers extends AppCompatActivity {

    private static final int ADD_NEW_MEMBER = 1;
    private static final int EDIT_MEMBER = 2;
    private static final int DETAIL_MEMBER = 3;
    private static final int SEND_MESSAGE = 4;


    private MembersAdapter adapter;
    private ListView listMember;
    private Rooster rooster;
    private TextView totalDPS;
    private TextView totalMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_roosters_members);
        Intent intent = getIntent();

        totalDPS = (TextView) findViewById(R.id.totalDPSTextVIew);
        totalMembers = (TextView) findViewById(R.id.totalMemberTextView);

        Button addMemberButton = (Button) findViewById(R.id.addMemberButton);
        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddNewMember.class);
                startActivityForResult(intent,ADD_NEW_MEMBER);
            }
        });



        if(intent.getParcelableExtra("roosterFromMyRoosters") != null) {
            rooster = intent.getParcelableExtra("roosterFromMyRoosters");
        }

        listMember = (ListView) findViewById(R.id.listMembers);
        adapter = new MembersAdapter(getApplicationContext(),rooster.getMembers());
        listMember.setAdapter(adapter);

        listMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MemberDetails.class);
                intent.putExtra("memberDetail",rooster.getMembers().get(position));
                startActivityForResult(intent,DETAIL_MEMBER);
            }
        });
        /*
        listMember.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                rooster.getMembers().remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        */

        /**
         * Lie le menu contextuelle à la listView
         */
        registerForContextMenu(listMember);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.id_delete:
                rooster.getMembers().remove(info.position);
                adapter.notifyDataSetChanged();

                totalDPS.setText("Total DPS : " + Integer.toString(rooster.totalDPS()));
                totalMembers.setText("Members : " + Integer.toString(rooster.getMembers().size()));
                return true;

            case R.id.id_edit:
                Intent intent = new Intent(getApplicationContext(),AddNewMember.class);
                intent.putExtra("memberToEdit",rooster.getMembers().get(info.position));
                intent.putExtra("position",info.position);
                startActivityForResult(intent,EDIT_MEMBER);
                adapter.notifyDataSetChanged();
                return true;

            case R.id.id_close:
                return true;
            case R.id.id_SendMessage:
                Intent intentSendMessage = new Intent(getApplicationContext(), SendMessage.class);
                intentSendMessage.putExtra("sendMessage",rooster.getMembers().get(info.position));
                startActivityForResult(intentSendMessage,SEND_MESSAGE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_NEW_MEMBER && resultCode==RESULT_OK){
            rooster.addMember((Member)data.getParcelableExtra("newMember"));
            adapter.notifyDataSetChanged();
        }
        if(requestCode==EDIT_MEMBER && resultCode==RESULT_OK){
            Member m = data.getParcelableExtra("newMember");
            int position = data.getIntExtra("position",-1);
            if(position!=-1){
                rooster.getMembers().remove(position);
                rooster.getMembers().add(m);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("roosterFromAddNewMember", rooster);
        setResult(RESULT_OK,intent);
        System.out.println("On Back Pressed");
        finish();
    }

    public class MembersAdapter extends ArrayAdapter<Member> {

        public MembersAdapter(Context context, List<Member> list) {
            super(context, android.R.layout.simple_list_item_1, list);  //explication de simple_list_item_1 ?
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // trouve le membre de ma lise avec lequel travailler
            Member member = getItem(position);
            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.member_item, parent, false);
            }

            // Lookup view for data population (Associe les TextView TvName etc... à ceux de rooster_item.xml)
            TextView TvName = (TextView) convertView.findViewById(R.id.memberName);
            TextView TvIlvl = (TextView) convertView.findViewById(R.id.memberIlvl);
            TextView TvNote = (TextView)convertView.findViewById(R.id.memberNote);
            ImageView classe = (ImageView) convertView.findViewById(R.id.classeId);
            ImageView portrait = (ImageView) convertView.findViewById(R.id.portraitId);
            ImageView spe = (ImageView) convertView.findViewById((R.id.spe));

            // Populate the data into the template view using the data object
            TvName.setText(member.getName());
            TvIlvl.setText(Integer.toString(member.getIlvl()));
            TvNote.setText(member.getNote());
            classe.setImageResource(member.getClasseId());
            portrait.setImageResource(R.drawable.humain);
            spe.setImageResource(member.getSpecialisation());


            /**
             Met à joue les informations générales sur le Rooster
             */

            totalDPS.setText("Total DPS : " + Integer.toString(rooster.totalDPS()));
            totalMembers.setText("Members : " + Integer.toString(rooster.getMembers().size()));

            return convertView;

        }
    }
}
