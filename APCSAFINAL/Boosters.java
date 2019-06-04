
public class Boosters extends Item
{
    private int addHP;
    private int turncount;
    private int stackcount;
    
    public Boosters()
    {
        setType("Booster");
        turncount = 3;
    }
    
    public int getAdd()
    {
        return addHP;
    }
    public void setAdd(int theAdd)
    {
        addHP = theAdd;
    }
    public void addInc()
    {
        addHP = addHP*2;
    }
    
    public int getTurn()
    {
        return turncount;
    }
    public void resetTurn()
    {
        turncount = 3;
    }
    public void subTurn()
    {
        if (turncount>0)
        {
            turncount--;
        }
    }
    
    public int getStack()
    {
        return stackcount;
    }
    public void stackInc()
    {
        stackcount++;
    }
    public void stack(Boosters i)
    {
        i.atkInc();
        i.defInc();
        i.addInc();
    }
}
