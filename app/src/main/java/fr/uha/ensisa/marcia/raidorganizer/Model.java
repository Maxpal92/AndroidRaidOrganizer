package fr.uha.ensisa.marcia.raidorganizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 23/10/2016.
 */

public class Model {
    private List<Rooster> roosters;

    public List<Rooster> getRoosters(){
        if(roosters == null) roosters =new ArrayList<>();
        return roosters;
    }

    public Model populate(){


        Rooster r1 = new Rooster("Rooster 1");
        r1.setDescription("le Rooster 1");
        r1.addMember(new Member("Palaouf",MemberClass.DPS,MemberClass.PALADIN,150,"Un paladin dps"));
        r1.addMember(new Member("Magos",MemberClass.DPS,MemberClass.MAGE,150,"Un mage de feu"));
        r1.addMember(new Member("ShamanKing",MemberClass.HEALER,MemberClass.SHAMAN,150,"Un shaman heal"));
        r1.addMember(new Member("Druidos",MemberClass.TANK,MemberClass.DRUID,150,"Un bon petit tank druide"));

        getRoosters().add(r1);

        return this;
    }


}
