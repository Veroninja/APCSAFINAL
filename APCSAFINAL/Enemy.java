
public class Enemy
{
    private String name = "";
    private String type = "";
    private String rank = "";
    private int hp;
    private int atk;
    private int def;
    private int expGiven;
    private int num;
    
    public Enemy()
    {
       type = "Enemy";
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String theN)
    {
        name = theN;
    }
    
    public String getType()
    {
        return type;
        //no setters – never changes 
    }
    
    public int getHP()
    {
        return hp;
    }
    public void setHP(int theHP)
    {
        hp = theHP;
    }
    
    public int getAtk()
    {
        return atk;
    }
    public void setAtk(int theAtk)
    {
        atk = theAtk;
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
                     p.setHP(p.getHP()-(this.getAtk() - p.getDef()));
                     System.out.println("Damage done: "+(this.getAtk() - p.getDef()));
                     System.out.println("Remaining player HP: "+p.getHP());
                }
                else
                {
                     System.out.println("Attack not higher than player defense, no damage done.");
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
                     p.setHP(p.getHP()-(this.getAtk() - p.getDef()));
                     System.out.println("Damage done: "+(this.getAtk() - p.getDef()));
                     System.out.println("Remaining player HP: "+p.getHP());
                }
                else
                {
                     System.out.println("Attack not higher than player defense, no damage done.");
                }
            }
        }
        else
        //if player is a Knight or Mage, deal dmg only if self atk higher than player def
        {
            if (this.getAtk() > p.getDef())
            {
                p.setHP(p.getHP()-(this.getAtk() - p.getDef()));
                System.out.println("Damage done: "+(this.getAtk() - p.getDef()));
                System.out.println("Remaining player HP: "+p.getHP());
            }
            else
            {
                System.out.println("Attack not higher than player defense, no damage done.");
            }
        }
    }
  
    public int getDef()
    {
        return def;
    }
    public void setDef(int theDef)
    {
        def = theDef;
    }
    
    public int getExpGiven()
    {
        return expGiven;
    }
    public void setExpGiven(int exp)
    {
        expGiven = exp;
    }
    
    public int getNum()
    {
        return num;
    }
    public void setPackNum()
    {
        num = (int)((Math.random()*3)+2);
        // generates 2-4 enemies, for direwolves/goblins/slimes
    }
    public void setNum(int theNum)
    {
        num = theNum;
        //for definite pairs/singles (wraiths, dragons)
    }
    
    public String getRank()
    {
        return rank;
    }
    public void setRank(String theR)
    {
        rank = theR;
    }
    
    public void powerInc(Player p)
    {
        atk += (int)(1.5*(p.getLv()-1));
        def += (int)(1.5*(p.getLv()-1));
        hp += 5*(p.getLv()-1);
        expGiven += 15;
    }
}
