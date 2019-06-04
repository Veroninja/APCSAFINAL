
public class Knight extends Player
{
    //Active: Death Parade – defeat all enemies with HP lower than self
    public Knight()
    {
        super();
        setclass("Knight");
        setSkill("Death Parade");
        addTo(new Swords());
        addTo(new Shields());
        addTo(new Enhance());
        addTo(new IronSkin());
        addTo(new Heal());
    }
    public void useSkill (Enemy one)
    {
        if (this.getBlast() >= 5)
        {
            int count = 0;
            if (one.getHP() < this.getHP())
            {
                one.setHP(0);
            }
            else
            {
                count++;
            }
            System.out.println("Trigger death of all enemies with HP lower than self.");
            System.out.println("Remaining enemies: "+count);
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging skill. Unable to use Death Parade.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void useSkill (Enemy one, Enemy two)
    {
        if (this.getBlast() >= 5)
        {
            int count = 0;
            if (one.getHP() < this.getHP())
            {
                one.setHP(0);
            }
            else
            {
                count++;
            }
            if (two.getHP() < this.getHP())
            {
                two.setHP(0);
            }
            else
            {
                count++;
            }
            System.out.println("Trigger death of all enemies with HP lower than self.");
            System.out.println("Remaining enemies: "+count);
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging skill. Unable to use Death Parade.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void useSkill (Enemy one, Enemy two, Enemy three)
    {
        if (this.getBlast() >= 5)
        {
            int count = 0;
            if (one.getHP() < this.getHP())
            {
                one.setHP(0);
            }
            else
            {
                count++;
            }
            if (two.getHP() < this.getHP())
            {
                two.setHP(0);
            }
            else
            {
                count++;
            }
            if (three.getHP() < this.getHP())
            {
                three.setHP(0);
            }
            else
            {
                count++;
            }
            System.out.println("Trigger death of all enemies with HP lower than self.");
            System.out.println("Remaining enemies: "+count);
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging skill. Unable to use Death Parade.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void useSkill (Enemy one, Enemy two, Enemy three, Enemy four)
    {
        if (this.getBlast() >= 5)
        {
            int count = 0;
            if (one.getHP() < this.getHP())
            {
                one.setHP(0);
            }
            else
            {
                count++;
            }
            if (two.getHP() < this.getHP())
            {
                two.setHP(0);
            }
            else
            {
                count++;
            }
            if (three.getHP() < this.getHP())
            {
                three.setHP(0);
            }
            else
            {
                count++;
            }
            if (four.getHP() < this.getHP())
            {
                four.setHP(0);
            }
            else
            {
                count++;
            }
            System.out.println("Trigger death of all enemies with HP lower than self.");
            System.out.println("Remaining enemies: "+count);
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging skill. Unable to use Death Parade.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    
    public void printSkillInfo()
    {
        System.out.println("Skill – Death Parade");
        System.out.println("Type: Active Offensive");
        System.out.println("Trigger death of all enemies with HP lower than self.");
        System.out.println("Skill counter must reach 5 or above to activate skill.");
    }
}
