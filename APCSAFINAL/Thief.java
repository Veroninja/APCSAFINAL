
public class Thief extends Player
{
    //Passive: Steady Blade – deals true damage
    private int evasion;
    public Thief()
    {
        super();
        setclass("Thief");
        setSkill("Steady Blade");
        addTo(new Daggers());
        addTo(new Daggers());
        addTo(new Darts());
        addTo(new Darts());
        addTo(new Darts());
        addTo(new Darts());
        addTo(new Heal());
    }
    
    public void setE()
    {
        evasion = (int)(Math.random()*4)+1;
    }
    public int getE()
    {
        return evasion;
    }
    
    public void attack(Enemy e)
    {
        e.setHP(e.getHP()-this.getAtk());
        System.out.println("Damage done: "+this.getAtk());
        System.out.println("Remaining Enemy HP: "+e.getHP());
    }
    
    public void useSkill()
    {
        System.out.println("Skill: Steady Blade is passive, no activation required.");
    }
    
    public void printSkillInfo()
    {
        System.out.println("Skill – Steady Blade");
        System.out.println("Type: Passive Offensive");
        System.out.println("Bypass enemy defenses and deal true damage.");
        System.out.println("Passive skill, no activation required.");
        System.out.println("");
        System.out.println("Additional Passive – Evasion");
        System.out.println("Upon incoming attack, 25% chance to evade damage.");
    }
}
