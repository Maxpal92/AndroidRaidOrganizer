package fr.uha.ensisa.marcia.raidorganizer;

import android.content.ClipData;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 16/10/2016.
 */

public class Rooster implements Parcelable {

    private String name;        // le nom du rooster
    private String description; // Une decription sucincte du rooster
    private List<Member> members;

    public Rooster(String name) {
        this.name = name;
        this.description = "";
        this.members = new ArrayList<Member>();
    }

    public Rooster() {
        this.name = "NewRooster";
        this.description = "";
        this.members = new ArrayList<Member>();

    }

    public String getName() {
        return name;

    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String s) {
        this.description = s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Member member){
        this.members.add(member);
    }

    public List<Member> getMembers(){
        return this.members;
    }

    public int totalDPS(){
        int totalDPS = 0;
        for(Member m: members){
            totalDPS += m.getDps();
        }
        return totalDPS;
    }

    protected Rooster(Parcel in) {
        /*name = in.readString();
        description = in.readString();*/
        this.name = in.readString();
        this.description = in.readString();
            if(members == null) {
                members = new ArrayList<Member>();
            }
            in.readTypedList(members, Member.CREATOR);

        /*Parcelable [] ps = in.readParcelableArray(this.getClass().getClassLoader());
        this.members = new ArrayList<>(ps.length);
        for(Parcelable p : ps){
            members.add((Member) p);
        }*/

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeTypedList(members);
        /*Member[] array = members.toArray(new Member[members.size()]);
        dest.writeParcelableArray(array,0);*/

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Rooster> CREATOR = new Creator<Rooster>() {
        public ArrayList<Member> members;

        @Override
        public Rooster createFromParcel(Parcel in) {
            return new Rooster(in);
        }

        @Override
        public Rooster[] newArray(int size) {
            return new Rooster[size];
        }
    };


}
