import java.util.*;
public class Player
{
    private String name = "";
    private String type = "";
    private String Class = "";
    private String rank = "";
    private String skill = "";
    private int hp;
    private int atk;
    private int def;
    private int exp;
    private int expNeeded;
    private int lv;
    private int turncount;
    private int blastcount;
    
    private ArrayList<Item> inventory = new ArrayList<Item>(20);
    private ArrayList<Item> shortInv = new ArrayList<Item>();
    private ArrayList<Item> using = new ArrayList<Item>(10);
    
    private int rowPos = 15;
    private int colPos = 0;
    
    Scanner sc = new Scanner(System.in);
    
    public Player()
    {
       name = "";
       type = "Player";
       rank = "Rookie";
       hp = 100;
       atk = 5;
       def = 5;
       lv = 1;
       exp = 0;
       expNeeded = 100;
       blastcount = 0;
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String theN)
    {
        name = theN;
        //user input
    }
    
    public String getType()
    {
        return type;
        //no setters – it never changes 
    }
    
    public String getclass()
    {
        return Class;
    }
    public void setclass(String theClass)
    {
        Class = theClass;
    }
    
    
    public String getRank()
    {
        return rank;
    }
    public void setRank()
    {
        if (lv<10)
            rank = "Rookie";
        else if (lv >= 10 && lv < 30)
            rank = "Explorer";
        else if (lv >= 30 && lv< 50)
            rank = "Warden";
        else if (lv >= 50)
            rank = "Adventurer";
    }
    
    public int getHP()
    {
        return hp;
        //use boost to add hp
    }
    public void setHP(int theHP)
    {
        hp = theHP;
    }
    
    public int getAtk()
    {
        return atk;
        //use boost/weapon to add atk or use darts/daggers to attack
    }
    
    
    public int getDef()
    {
        return def;
        //use boost or weapons to add def
    }
    
    public int getLv()
    {
        return lv;
    }
    public void lvInc()
    {
        //atk/def/HP increases as lv increases
        lv++;
        atk += 2*(lv-1);
        def += 2*(lv-1);
        hp += 5*(lv-1);
        if (lv == 10)
            System.out.println("Rank change: Explorer");
        else if (lv == 30)
            System.out.println("Rank change: Warden");
        else if (lv == 50)
            System.out.println("Rank change: Adventurer");
        System.out.println("Level up!");
        System.out.println("Exp: "+exp+"/"+expNeeded);
    }
    
    public int getEXP()
    {
        return exp;
    }
    public void addEXP(int add)
    {
        if (exp+add >= expNeeded)
        {
            lvInc();
            exp = exp+add-expNeeded;
            expNeeded += 75;
        }
        else
            exp+=add;
    }
    
    public int getExpNeeded()
    {
        return expNeeded;
        //sets in addEXP bcs doesn't change unless level up
    }
    
    public int getTurnCount()
    {
        return turncount;
    }
    public void turnInc()
    {
        turncount++;
        blastcount++;
        for(Item i : using)
        //check every used item's turn count (really only for Boosters)
        {
            if (((Boosters)i).getTurn()>0)
            //if more than 0, turncount-1
            {
                ((Boosters)i).subTurn();
            }
            else
            //if equals 0, remove from use
            {
                stopUse(i);
            }
        }
    }
    
    //using/stop using items
    public void useBoost(Boosters i)
    //using boosts count as a turn
    {
        if (using.size() < 10)
        //if still have space in "using"...
        {
            boolean have = false;
            int index = 0;
            if (!(i.getName().equals("War Cry") || i.getName().equals("Tank Up")) || i.getName().equals("Regeneration"))
            //if boost is not stackable...
            {
                for(Item use : using)
                //check if already using boost
                {
                     if(use.getName().equals(i.getName()))
                     {
                         have = true;
                     }
                }
                if(have==true)
                //if already using, print message, boost remains in inventory
                {
                     System.out.println("Already using boost. Please wait until boost expires to use again.");
                }
                else
                //if not using, add to "using" list, remove from inventory
                {
                     atk += i.getAtk();
                     def += i.getDef();
                     hp += i.getAdd();
                     inventory.remove(i);
                     using.add(i);
                     System.out.println("Used "+i.getName());
                     printStats();
                     System.out.println("Remaining inventory space: "+inventory.size()+"/20");
                     System.out.println("Items in use: "+using.size()+"/10");
                     turnInc();
                }
            }
            else
            //if boost is stackable...
            {
                for(Item use : using)
                //check if already using boost
                {
                     if(use.getName().equals(i.getName()))
                     {
                         have = true;
                     }
                }
                if(have==true)
                //if already using...
                {
                     if (((Boosters)using.get(using.indexOf(i))).getStack()<3)
                     //if not reached 3 stacks, stack and +1 stack count, reset turn count, remove from inventory
                     {
                         Boosters b = ((Boosters)(using.get(using.indexOf(i))));
                         b.stack(i);
                         //make a copy of Booster i, then stack
                         
                         using.set(using.indexOf(i),b);
                         atk += b.getAtk();
                         def += b.getDef();
                         hp += b.getAdd();
                         //replace i with b(stacked version of i), and add to player stats
                         
                         b.stackInc();
                         b.resetTurn();
                         inventory.remove(i);
                         System.out.println("Used "+b.getName());
                         printStats();
                         System.out.println("Remaining inventory space: "+inventory.size()+"/20");
                         System.out.println("Items in use: "+using.size()+"/10");
                         turnInc();
                     }
                     else
                     //if already 3 stacks, print message, boost remains in inventory
                     {
                         System.out.println("Max stacks reached. Please wait until stacks expire to use again.");
                     }
                }
                else
                //if not using, add to "using" list, +1 stack count, remove from inventory
                {
                        using.add(i);
                        atk += i.getAtk();
                        def += i.getDef();
                        hp += i.getAdd();
                        i.stackInc();
                        inventory.remove(i);
                        System.out.println("Used "+i.getName());
                        printStats();
                        System.out.println("Remaining inventory space: "+inventory.size()+"/20");
                        System.out.println("Items in use: "+using.size()+"/10");
                        turnInc();
                }
            }
        }
        else
        {
            System.out.println("Reached maximum number of items you can use at once.");
            System.out.println("Please stop using at least one item before repeating this action.");
        }
    }
    public void stopUse(Item i)
    //discarding a boost/weapon doesn't count as a turn
    {
        //remove item from "using" and subtract item's atk/def from player
        //HP remains unchanged, will remove item (can't stack), but will not sub HP
        using.remove(i);
        atk -= i.getAtk();
        def -= i.getDef();
        System.out.println("Discarded "+i.getName());
        printStats();
        System.out.println("Remaining inventory space: "+inventory.size()+"/20");
        System.out.println("Items in use: "+using.size()+"/10");
    }
    public void useWeapon(Item i)
    //using weapons don't count as a turn
    {
        if (using.size() < 10)
        //if still have space in "using"...
        {
            atk += i.getAtk();
            def += i.getDef();
            using.add(i);
            inventory.remove(i);
            System.out.println("Used "+i.getName());
            printStats();
            System.out.println("Remaining inventory space: "+inventory.size()+"/20");
            System.out.println("Items in use: "+using.size()+"/10");
        }
        else
        {
            System.out.println("Reached maximum number of items you can use at once.");
            System.out.println("Please stop using at least one item before repeating this action.");
        }
    }
    public void useDarts(Darts d, Enemy e)
    //darts deal true damage, immediately remove from inventory after use
    {
        e.setHP(e.getHP()-d.getAtk());
        inventory.remove(d);
    }
    
    //regarding items in inventory (actions)
    public int invSize()
    {
        return inventory.size();
    }
    public ArrayList<Item> inv()
    {
        return inventory;
    }
    public int shortInvSize()
    {
        return shortInv.size();
    }
    public ArrayList<Item> shortInv()
    {
        return shortInv;
    }
    
    public void addTo(Item i)
    {
        if (inventory.size() < 20)
        //add item if still have space in inventory
        {
            inventory.add(i);
            System.out.println("Added "+i.getName()+" to inventory.");
        }
        else
        //print message if no space
        {
            System.out.println("Insufficient space in inventory.");
            System.out.println("Please use or discard at least one item to store new item.");
        }
    }
    public void remove(Item i)
    {
        inventory.remove(i);
        System.out.println("Remaining inventory space: "+inventory.size()+"/20");
    }
    
    //list items in inventory and in use (return)
    public int containsInv(Item i)
    {
        int count = 0;
        for(Item x : inventory)
        //for every item in inventory...
        {
            if (i.getName().equals(x.getName()))
            //if name of item equals name of target item, count++
            {
                count++;
            }
        }
        return count;
    }
    public void listInv()
    {
        shortInv.clear();
        ArrayList<String> names = new ArrayList<String>();
        int num = 1;
        
        System.out.println("Below is a list of items in your inventory, with the number of each item listed in brackets:");
        for(Item i : inventory)
        //for every item in inventory...
        {
            for(String s : names)
            //for every string in "names"...
            {
                if (!(i.getName().equals(s)))
                //if name of item isn't in "names", add to "names"
                {
                    names.add(i.getName());
                    System.out.println(num+". "+i.getName()+" ("+containsInv(i)+")\n");
                    shortInv.add(i);
                    num++;
                }
            }
        }
    }
    public void listUsing()
    {
        shortInv.clear();
        int num = 1;
        
        System.out.println("Below are the items currently in use, with stacks listed next to each boost:");
        for(Item i : using)
        //for every item in "using", add the name of the item and its stacks to "names"
        {
                if(i.getType().equals("Booster"))
                {
                    System.out.println(num+". "+i.getName()+" ("+(((Boosters)i).getStack())+")\n");
                    shortInv.add(i);
                    num++;
                }
                else
                {
                    System.out.println(num+". "+i.getName()+"\n");
                    num++;
                }
        }
    }
    
    public String getSkill()
    {
        return skill;
    }
    public void setSkill(String theSkill)
    {
        skill = theSkill;
    }
    public void useSkill()
    {
    }
    
    public int getBlast()
    {
        return blastcount;
    }
    public void blastInc()
    {
        blastcount++;
    }
    public void resetBlast()
    {
        blastcount = 0;
    }
    
    //printing player info (return)
    public void printInfo()
    {
        System.out.println("Username: "+getName());
        System.out.println("Class: "+getclass());
        System.out.println("Rank: "+getRank());
        System.out.println("Attack: "+getAtk());
        System.out.println("Defense: "+getDef());
        System.out.println("HP: "+getHP());
        System.out.println("Level: "+getLv());
        System.out.println("Exp: "+getEXP()+"/"+getExpNeeded());
        System.out.println("Items in inventory: "+inventory.size()+"/20");
        System.out.println("Items in use: "+using.size()+"/10");
        System.out.println("Skill counter: "+getBlast());
        System.out.println("Skill: "+getSkill());
        System.out.println("");
    }
    public void printStats()
    {
        System.out.println("Attack: "+getAtk());
        System.out.println("Defense: "+getDef());
        System.out.println("HP: "+getHP());
        System.out.println("Skill counter: "+getBlast());
    }
    
    //getters and setters for player position
    public int getCol()
    {
        return colPos;
    }
    public void colPosInc()
    {
         colPos++;
    }
    public void colPosSub()
    {
         colPos--;
    }
    public int getRow()
    {
        return rowPos;
    }
    public void rowPosInc()
    {
         rowPos++;
    }
    public void rowPosSub()
    {
         rowPos--;
    }
    
    //methods for player movement and decisions (actions)
    public void whichDirection()
    {
        System.out.println("Select a direction:");
        System.out.println("1. Forward \n2. Backward \n3. Left \n4. Right");
        int d = sc.nextInt();
        move(d);
    }
    public void move(int m)
    {
        boolean valid = false;
        while(valid==false)
        {
            if (m==1)//1 = forward(towards right on map)
            {
                if (getCol() != 39)
                {
                    colPosInc();
                    valid=true;
                }
                else
                    System.out.println("Reached edge of dungeon. Please select another direction");
                }
            else if (m==2)//2 = backward(towards left on map)
            {
                if (getCol() != 0)
                {
                    colPosSub();
                    valid=true;
                }
                    
                else
                    System.out.println("Reached edge of dungeon. Please select another direction");
            }
            else if (m==3)//3 = left(upwards on map)
            {
                if (getRow() != 0)
                {
                    rowPosSub();
                    valid=true;
                }
                else
                    System.out.println("Reached edge of dungeon. Please select another direction");
                }
            else if (m==4)//4 = right(downwards on map)
            {
                if (getRow() != 29)
                {
                    rowPosInc();
                    valid=true;
                }
                else
                    System.out.println("Reached edge of dungeon. Please select another direction");
            }
            else
            {
                System.out.println("Invalid choice, please retry.");
            }
        }
        if (getCol()==20)
        {
            System.out.println("Warning: you have passed the halfway point of the dungeon. Here there be dragons. Good luck.");
        }
    }
    
    //methods if player encounters item/enemy (actions)
    public boolean occupied(Object[][][] d)
    {
        boolean o = false;
        if (((Item)(d[0][rowPos][colPos])).getType().equals("Booster") || ((Item)(d[0][rowPos][colPos])).getType().equals("Weapon") || ((Enemy)d[0][rowPos][colPos]).getType().equals("Enemy"))
        {
            o = true;
        }
        return o;
    }
    public void encounter(int row, int col, Object[][][] d)
    //encountering enemy or items chest
    {
        if(((Enemy)(d[0][row][col])).getType().equals("Enemy"))
        {
            if (((Enemy)(d[0][row][col])).getHP()>0)
            {
                int count = 0;
                System.out.println("Warning: hostile creature(s) detected:");
                for(int i = 0; i <=3; i++)
                {
                    if(((Enemy)(d[i][row][col])).getType().equals("Enemy"))
                    {
                        System.out.println(((Enemy)(d[i][row][col])).getName());
                        count++;
                    }
                }
                System.out.println("");
                while(getHP()>0)
                {
                    int num = 0;
                    printEncounter();
                    int a = sc.nextInt();
                    this.encounterAction(a, count, d);//(decision, number of enemies, dungeon)
                    while(num<count)
                    {
                        if(((Enemy)(d[num][row][col])).getHP()>0)
                        {
                            ((Enemy)(d[num][row][col])).attack(this);
                            break;
                      }
                        else
                            num++;
                    }
                }
                if (getHP() <= 0)
                {
                    System.out.println("HP at 0. Game over.");
                    System.exit(0);
                }
            }
        }
        else if(((Item)(d[0][row][col])).getType().equals("Booster") || ((Item)(d[0][row][col])).getType().equals("Weapon"))
        {
            System.out.println("You have encountered a wooden chest. Contents:");
            for(int i = 0; i <=3; i++)
            {
                 if(((Item)(d[i][row][col])).getType().equals("Booster") || ((Item)(d[i][row][col])).getType().equals("Weapon"))
                 {
                     System.out.println(((Item)(d[i][row][col])).getName());
                 }
            }
            System.out.println("Collect items? 1. Yes, 2. No");
            int a = sc.nextInt();
            collectItems(a,row,col,d);
        }
    }
    public void encounterAction(int a, int c, Object[][][]d)
    {
        boolean valid = true;
        while(valid==true)
        {
            if(a==1)
            //attack front row
            {
                int num = 0;
                while(num<c)
                {
                    if(((Enemy)d[num][getRow()][getCol()]).getHP()>0)
                    {
                        attack((Enemy)d[num][getRow()][getCol()]);
                        break;
                    }
                    else
                    {
                        ((Enemy)d[num][getRow()][getCol()]).setHP(0);
                        addEXP(((Enemy)d[num][getRow()][getCol()]).getExpGiven());
                        num++;
                    }
                }
                break;  
            }
            else if(a==2)
            //use skill
            {
                this.useSkill();
                break;
            }
            else if(a==3)
            //use item
            {
                boolean ok = false;
                listInv();
                System.out.println("Which item do you want to use?");
                int x = sc.nextInt();
                while (ok == false)
                {
                    if (x<=shortInv.size() && x>=1)
                    {
                        if ((shortInv.get(x-1)).getType().equals("Booster"))
                            useBoost((Boosters)shortInv.get(x-1));
                        else if (((Weapons)shortInv.get(x-1)).getName().equals("Darts"))
                        {
                            int num = 0;
                            if(((Enemy)d[num][getRow()][getCol()]).getHP()>0)
                            {
                                useDarts((Darts)shortInv.get(x-1), (Enemy)d[num][getRow()][getCol()]);
                            }
                            else
                                num++;
                        }
                        else
                            useWeapon((Weapons)shortInv.get(x-1));
                        ok = true;
                    }
                    else
                        System.out.println("Invalid choice, please retry.");
                }
            } 
            else if(a==4)
            //discard/stop using item
            {
                boolean ok = false;
                listUsing();
                System.out.println("Which item do you want to discard?");
                int x = sc.nextInt();
                while (ok == false)
                {
                    if (x<=shortInv.size() && x>=1)
                    {
                        stopUse(shortInv.get(x-1));
                        ok = true;
                    }
                    else
                        System.out.println("Invalid choice, please retry.");
                }
            }
            else if(a==5)
            //Check player stats
            {
                printStats();
            }
            else if(a==6)
            //Check items in inventory
            {
                listInv();
            }
            else if(a==7)
            //Check items in use
            {
                listUsing();
            }
            else 
            {
                System.out.println("Invalid choice, please retry.");
                valid = false;
            }
        }
    }
    public void collectItems(int a, int r, int c, Object[][][] d)
    {
        boolean valid = false;
        while (valid==false)
        {
            if (a==1)//1 = yes, collect items
            {
                for(int i = 0; i <=3; i++)
                {
                    addTo((Item)(d[i][r][c]));
                    ((Item)(d[i][r][c])).setType("");
                }
                System.out.println("Number of items in inventory: "+invSize()+"/20");
                valid = true;
            }
            else if (a==2)//2 = no, don't collect
            {
                System.out.println("No items collected. Number of items in inventory: "+invSize()+"/20");
                valid = true;
            }
            else
                System.out.println("Invalid choice, please retry.");
        }
    }
    public void attack(Enemy e)
    {
        if (!(e.getName().equals("Wraith")))
        //if enemy is not a Wraith, deal dmg if self atk is higher than enemy def
        {
            if (this.getAtk() > e.getDef())
            {
                e.setHP(e.getHP()-(this.getAtk() - e.getDef()));
                System.out.println("Damage done: "+(this.getAtk() - e.getDef()));
                System.out.println("Remaining Enemy HP: "+e.getHP());
            }
            else
            {
                System.out.println("Attack not higher than enemy defense, no damage done.");
            }
        }
        else
        //if enemy is a Wraith, deal dmg only if chance does not equal 1
        {
            ((Wraiths)e).setChance();
            if(((Wraiths)e).getChance()==1)
            {
                System.out.println("Missed enemy, no damage done.");
            }
            else
            {
                if (this.getAtk() > e.getDef())
                {
                     e.setHP(e.getHP()-(this.getAtk() - e.getDef()));
                     System.out.println("Damage done: "+(this.getAtk() - e.getDef()));
                     System.out.println("Remaining Enemy HP: "+e.getHP());
                }
                else
                {
                     System.out.println("Attack not higher than enemy defense, no damage done.");
                }
            }
        }
        turnInc();
    }
    
    public void printEncounter()
    {
        System.out.println("Please select a course of action:");
        System.out.println("1. Attack front row enemy");
        System.out.println("2. Use skill");
        System.out.println("3. Use an item");
        System.out.println("4. Stop using an item and discard");
        System.out.println("5. Check player stats (Attack, Defense, HP, skill counter)");
        System.out.println("6. Check items in inventory");
        System.out.println("7. Check items in use");
    }
}
