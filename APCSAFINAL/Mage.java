
public class Mage extends Player
{
    //Active: Shockwave – damage all enemies
    public Mage()
    {
        super();
        setclass("Mage");
        setSkill("Witching Hour");
        addTo(new Staffs());
        addTo(new Enhance());
        addTo(new IronSkin());
        addTo(new Regen());
        addTo(new Heal());
    }
    public void Shockwave(Enemy one, Enemy two, Enemy three, Enemy four)
    {
        if (this.getBlast() >= 5)
        {
            one.setHP(one.getHP()-(this.getAtk() - one.getDef()));
            two.setHP(two.getHP()-(this.getAtk() - two.getDef()));
            three.setHP(three.getHP()-(this.getAtk() - three.getDef()));
            four.setHP(four.getHP()-(this.getAtk() - four.getDef()));
            System.out.println("Attack all enemies.\nRemaining Enemy HP: ");
            System.out.println(one.getHP());
            System.out.println(two.getHP());
            System.out.println(three.getHP());
            System.out.println(four.getHP());
            resetBlast();
            turnInc();
        }
        else
        {
            System.out.println("Recharging mana. Unable to use Witching Hour.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void Shockwave(Enemy one, Enemy two, Enemy three)
    {
        if (this.getBlast() >= 5)
        {
            one.setHP(one.getHP()-(this.getAtk() - one.getDef()));
            two.setHP(two.getHP()-(this.getAtk() - two.getDef()));
            three.setHP(three.getHP()-(this.getAtk() - three.getDef()));
            System.out.println("Attack all enemies.\nRemaining Enemy HP: ");
            System.out.println(one.getHP());
            System.out.println(two.getHP());
            System.out.println(three.getHP());
        }
        else
        {
            System.out.println("Recharging mana. Unable to use Witching Hour.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void Shockwave(Enemy one, Enemy two)
    {
        if (this.getBlast() >= 5)
        {
            one.setHP(one.getHP()-(this.getAtk() - one.getDef()));
            two.setHP(two.getHP()-(this.getAtk() - two.getDef()));
            System.out.println("Attack all enemies.\nRemaining Enemy HP: ");
            System.out.println(one.getHP());
            System.out.println(two.getHP());
        }
        else
        {
            System.out.println("Recharging mana. Unable to use Witching Hour.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    public void Shockwave(Enemy one)
    {
        if (this.getBlast() >= 5)
        {
            one.setHP(one.getHP()-(this.getAtk() - one.getDef()));
            System.out.println("Attack all enemies.\nRemaining Enemy HP: ");
            System.out.println(one.getHP());
        }
        else
        {
            System.out.println("Recharging mana. Unable to use Witching Hour.");
            System.out.println("Use in "+(5-this.getBlast())+" turns");
        }
    }
    
    public void printSkillInfo()
    {
        System.out.println("Skill – Witching Hour");
        System.out.println("Type: Active Offensive");
        System.out.println("Attack all enemies.");
        System.out.println("Skill counter must reach 5 or above to activate skill.");
    }
}
