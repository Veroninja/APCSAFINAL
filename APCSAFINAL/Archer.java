
public class Archer extends Player
{
    //Active: Skyfall – true damage up to two enemies
    private int evasion;
    public Archer()
    {
        super();
        setclass("Archer");
        setSkill("Skyfall");
        addTo(new Crossbows());
        addTo(new Darts());
        addTo(new Darts());
        addTo(new Heal());
    }
    
    public void setE()
    {
        evasion = (int)(Math.random()*5+1);
    }
    public int getE()
    {
        return evasion;
    }
    
    public void useSkill(Enemy one)
    {
        if (this.getBlast() >= 5)
        {
            one.setHP(one.getHP()-this.getAtk());
            System.out.println("Attack enemy.\nRemaining Enemy HP: ");
            System.out.println(one.getHP());
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging skill. Unable to use Skyfall.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void useSkill(Enemy one, Enemy two)
    {
        if (this.getBlast() >= 5)
        {
            one.setHP(one.getHP()-this.getAtk());
            two.setHP(two.getHP()-this.getAtk());
            System.out.println("Attack two enemies.\nRemaining Enemy HP: ");
            System.out.println(one.getHP());
            System.out.println(two.getHP());
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging skill. Unable to use Skyfall.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    
    public void printSkillInfo()
    {
        System.out.println("Skill – Skyfall");
        System.out.println("Type: Active Offensive");
        System.out.println("Bypass enemy defenses and deal true damage to front and back row enemies.");
        System.out.println("Skill counter must reach 5 or above to activate skill.");
        System.out.println("");
        System.out.println("Additional Passive – Evasion");
        System.out.println("Upon incoming attack, 20% chance to evade damage.");
    }
}
