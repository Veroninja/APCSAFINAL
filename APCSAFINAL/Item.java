
public class Item
{
    private int atk;
    private int def;
    private String name;
    private String type;
    
    public int getAtk()
    {
        return atk;
    }
    public void setAtk(int theAtk)
    {
        atk = theAtk;
    }
    public void atkInc()
    {
        atk = atk*2;
    }
    
    public int getDef()
    {
        return def;
    }
    public void setDef(int theDef)
    {
        def = theDef;
    }
    public void defInc()
    {
        def = def*2;
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
    }
    public void setType(String theType)
    {
        type = theType;
    }
}
