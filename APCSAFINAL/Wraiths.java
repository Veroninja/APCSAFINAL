
public class Wraiths extends Enemy
{
    private int chance;
    public Wraiths()
    {
        super();
        setName("Wraith");
        setRank("Demon");
        setAtk(35);
        setDef(25);
        setHP(150);
        setExpGiven(50);
        setPackNum();
    }
    
    public int getChance()
    {
        return chance;
    }
    public void setChance()
    {
        chance = (int)(Math.random()*10)+1;
    }
    
    public void attack(Player p)
    {
        if (p.getName().equals("Archer"))
        //if player is an Archer, deal dmg only if evasion does not equal 1
        {
            ((Archer)p).setE();
            if (((Archer)p).getE()==1)
            {
                System.out.println("Missed player, no damage done.");
            }
            else
            //deal dmg only if self atk higher than player def
            {
                if (this.getAtk() > p.getDef())
                {
                     p.setHP(p.getHP()-this.getAtk());
                     System.out.println("Damage done: "+this.getAtk());
                     System.out.println("Remaining player HP: "+p.getHP());
                }
                else
                {
                     System.out.println("Attack not higher than enemy defense, no damage done.");
                }
            }
        }
        else if (p.getName().equals("Thief"))
        //if player is a Thief, deal dmg only if evasion does not equal 1
        {
            ((Thief)p).setE();
            if (((Thief)p).getE()==1)
            {
                System.out.println("Missed player, no damage done.");
            }
            else
            //deal dmg only if self atk higher than player def
            {
                if (this.getAtk() > p.getDef())
                {
                     p.setHP(p.getHP()-this.getAtk());
                     System.out.println("Damage done: "+this.getAtk());
                     System.out.println("Remaining player HP: "+p.getHP());
                }
                else
                {
                     System.out.println("Attack not higher than enemy defense, no damage done.");
                }
            }
        }
        else
        //if player is a Knight or Mage, deal dmg only if self atk higher than player def
        {
            if (this.getAtk() > p.getDef())
            {
                p.setHP(p.getHP()-this.getAtk());
                System.out.println("Damage done: "+this.getAtk());
                System.out.println("Remaining Enemy HP: "+p.getHP());
            }
            else
            {
                System.out.println("Attack not higher than enemy defense, no damage done.");
            }
        }
    }
}
