package fr.uha.ensisa.marcia.raidorganizer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MaximeMarcia on 02/11/2016.
 */

public class Member implements Parcelable {

    private String Name;
    private int Specialisation;
    private int dps;
    private String note;
    private int classeId;
    private int portraitId = 0;
    private int ilvl = 0;
    private int metier1 = 0;
    private int metier2 = 0;
    private int phoneNumber = 5556;



    public Member(){
        this.Name = "";
        this.Specialisation = MemberClass.DPS;
        this.dps = 0;
        this.note = "";
        this.classeId=MemberClass.WARRIOR;
    }

    public Member(String Name, int Specialisation, int classeId, int dps, String note){
        this.Name = Name;
        this.Specialisation = Specialisation;
        this.dps = dps;
        this.note = note;
        this.classeId=classeId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSpecialisation(int specialisation) {
        Specialisation = specialisation;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return Name;
    }

    public int getSpecialisation() {
        return Specialisation;
    }

    public int getDps() {
        return dps;
    }

    public String getNote() {
        return note;
    }

    public int getClasseId() {
        return classeId;
    }

    public void setClasseId(int classeId) {
        this.classeId = classeId;
    }

    public int getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(int portraitId) {
        this.portraitId = portraitId;
    }

    public int getIlvl() {
        return ilvl;
    }

    public void setIlvl(int ilvl) {
        this.ilvl = ilvl;
    }

    public int getMetier1() {
        return metier1;
    }

    public void setMetier1(int metier1) {
        this.metier1 = metier1;
    }

    public int getMetier2() {
        return metier2;
    }

    public void setMetier2(int metier2) {
        this.metier2 = metier2;
    }

    public String getClassString(){

        switch (classeId){
            case MemberClass.DEATHNIGHT :
                return "Death Knight";
            case MemberClass.DRUID :
                return "Druid";
            case MemberClass.HUNTER :
                return "Hunter";
            case MemberClass.MAGE :
                return "Mage";
            case MemberClass.PALADIN :
                return "Paladin";
            case MemberClass.PRIEST :
                return "Prêtre";
            case MemberClass.ROGUE :
                return "Voleur";
            case MemberClass.SHAMAN :
                return "Shaman";
            case MemberClass.WARLOCK :
                return "Démoniste";
            case MemberClass.WARRIOR :
                return "Guerrier";
            default: return "";
        }
    }

    public String getSpecialisationString(){
        switch (Specialisation){
            case MemberClass.DPS:
                return "DPS";
            case MemberClass.HEALER:
                return "Soigneur";
            case MemberClass.TANK:
                return "Tank";
            default: return "";
        }
    }

    public String getProfession1String(){
        switch (metier1){
            case Profession.ALCHEMY :
                return "Alchemy";
            case Profession.ARCHEOLOGIE:
                return "Archeologie";
            case Profession.BLACKSMITHING:
                return "Blacksmith";
            case Profession.COOKING:
                return "Cooking";
            case Profession.ENCHANTING:
                return "enchanting";
            case Profession.ENGINEERING:
                return "engineering";
            case Profession.FIRSTAID:
                return "First-aid";
            case Profession.FISHINF:
                return "Fishing";
            case Profession.HERBALISM:
                return "Hebalism";
            case Profession.JEWELCRAFTING:
                return "Jewelcrafting";
            case Profession.MINING:
                return "Mining";
            case Profession.SKINNING:
                return "Skinning";
            case Profession.TAILORING:
                return "Tailoring";
            default:
                return "No profession";
        }
    }

    public String getProfession2String(){
        switch (metier2){
            case Profession.ALCHEMY :
                return "Alchemy";
            case Profession.ARCHEOLOGIE:
                return "Archeologie";
            case Profession.BLACKSMITHING:
                return "Blacksmith";
            case Profession.COOKING:
                return "Cooking";
            case Profession.ENCHANTING:
                return "enchanting";
            case Profession.ENGINEERING:
                return "engineering";
            case Profession.FIRSTAID:
                return "First-aid";
            case Profession.FISHINF:
                return "Fishing";
            case Profession.HERBALISM:
                return "Hebalism";
            case Profession.JEWELCRAFTING:
                return "Jewelcrafting";
            case Profession.MINING:
                return "Mining";
            case Profession.SKINNING:
                return "Skinning";
            case Profession.TAILORING:
                return "Tailoring";
            default:
                return "No profession";
        }
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Member(Parcel in) {
        Name = in.readString();
        Specialisation = in.readInt();
        dps = in.readInt();
        note = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeInt(Specialisation);
        dest.writeInt(dps);
        dest.writeString(note);
        dest.writeInt(classeId);
        dest.writeInt(portraitId);
        dest.writeInt(ilvl);
        dest.writeInt(metier1);
        dest.writeInt(metier2);
        dest.writeInt(phoneNumber);
    }





    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {

            Member member = new Member();
            member.setName(in.readString());
            member.setSpecialisation(in.readInt());
            member.setDps(in.readInt());
            member.setNote(in.readString());
            member.setClasseId(in.readInt());
            member.setPortraitId(in.readInt());
            member.setIlvl(in.readInt());
            member.setMetier1(in.readInt());
            member.setMetier2(in.readInt());
            member.setPhoneNumber(in.readInt());

            return member;
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
}
