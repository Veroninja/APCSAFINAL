import java.util.*;
public class Game
{
    public static void main(String[] args)
    {
        //total 1200 posible squares to be
        Object[][][] dungeon = new Object[4][30][40];
        Dungeon d = new Dungeon(dungeon);
        
        Player demo = new Player();
        Player[] p = new Player[1];
        
        Scanner sc1 = new Scanner(System.in);
        
        System.out.println("Please enter your username.");
        String user = sc1.nextLine();
        
        System.out.println("Welcome, "+user+".");
        System.out.println("");
        System.out.println("This game is a text-based adventure game, where you navigate a dungeon collecting items and");
        System.out.println("defeating hostile creatures, with the ultimate goal of slaying the three Dragons living in");
        System.out.println("the depths of the dungeon.");
        System.out.println("");
        System.out.println("As a player, you may choose to be any one of four classes of adventurers: a knight, a mage,");
        System.out.println("an archer, or a thief. Each class has their unique skill set and starting items in their");
        System.out.println("inventory as shown below:");
        System.out.println("");
        
        System.out.println("1. Knight");
        ((Knight)demo).printSkillInfo();
        System.out.println("");
        
        System.out.println("2. Mage");
        ((Mage)demo).printSkillInfo();
        System.out.println("");
        
        System.out.println("3. Archer");
        ((Archer)demo).printSkillInfo();
        System.out.println("");
        
        System.out.println("4. Thief");
        ((Thief)demo).printSkillInfo();
        System.out.println("");
        
        System.out.println("Please select a class.");
        int classChoice = sc1.nextInt();
        decideClass(classChoice, p);
        p[0].setName(user);
        System.out.println("You have chosen the "+p[0].getclass()+" class. Below is a list of your player information:");
        System.out.println("");
        p[0].printInfo();
        System.out.println("");
        System.out.println("You may begin your journey. Good luck. Don't go hunting dragons before you reach level 40.");
        System.out.println("");
        
        while(true)
        {
            boolean valid = true;
            printActions();
            int a = sc1.nextInt();
            while(valid==true)
            {
                 if(a==1)
                 //move: includes movement, check for encounter, and corresponding actions
                 {
                     whichDirection();
                     if(occupied(dungeon)==true)
                     {
                         p[0].encounter(p[0].getRow(), p[0].getCol(), dungeon);
                     }
                     break;
                 }
                 else if(a==2)
                 //print player stats
                 {
                     p[0].printStats();
                 }
                 else if(a==3)
                 //print all player info
                 {
                     p[0].printInfo();
                 } 
                 else if(a==4)
                 //check items in inv
                 {
                     p[0].listInv();
                 }
                 else if(a==5)
                 //check items in use
                 {
                     p[0].listUsing();
                 }
                 else if(a==6)
                 //use item
                 {
                     boolean ok = false; // check validity of user input
                     p[0].listInv(); //print items in inventory
                     System.out.println("Which item do you want to use?");
                     int x = sc1.nextInt();
                     while (ok == false)
                     {
                         if (x<=p[0].shortInvSize() && x>=1)
                         //if x is within 1 to shortInvSize(), check type of item and use
                         {
                             if (((Boosters)(p[0].shortInv()).get(x-1)).getType().equals("Booster"))
                                 p[0].useBoost((Boosters)(shortInv.get(x-1)));
                             else if (((Weapons)(p[0].shortInv().get(x-1))).getName().equals("Dart"))
                             {
                                 int num = 0;
                                 if(((Enemy)d[num][getRow()][getCol()]).getHP()>0)
                                 {
                                     p[0].useDarts((Darts)p[0].shortInv().get(x-1),(Enemy)dungeon[num][p[0].getRow()][p[0].getCol()]);
                                 }
                                 else
                                     num++;
                             }
                             else
                                 p[0].useWeapon((Weapons)p[0].shortInv().get(x-1));
                             ok = true;
                         }
                         else
                             System.out.println("Invalid choice, please retry.");
                     }
                 }
                 else if(a==7)
                 //discard item
                 {
                     boolean ok = false;
                     p[0].listInv();
                     System.out.println("Which item do you want to discard?");
                     int x = sc1.nextInt();
                     while (ok == false)
                     {
                         if (x<=p[0].InvSize() && x>=1)
                         {
                             p[0].stopUse((Item)p[0].inv().get(x-1));
                             ok = true;
                         }
                         else
                            System.out.println("Invalid choice, please retry.");
                        }
                 }
                 else 
                 {
                     System.out.println("Invalid choice, please retry.");
                     valid = false;
                 }
            }
        }
    }
    public static void printActions()
    {
        System.out.println("Please select a course of action:");
        System.out.println("1. Move"); //only action that triggers turnInc()
        System.out.println("2. Check player stats (Attack, Defense, HP, skill counter)");
        System.out.println("3. Check all player info");
        System.out.println("4. Check items in inventory");
        System.out.println("5. Check items in use");
        System.out.println("6. Use an item");
        System.out.println("7. Discard an item from inventory");
    }
    public static void decideClass(int choice, Player[] arr)
    {
        boolean valid = false;
        while (valid==false)
        {
            if (choice==1)
            {
                 Player p = new Knight();
                 arr[0] = p;
                 valid = true;
            }
            else if (choice==2)
            {
                 Player p = new Mage();
                 arr[0] = p;
                 valid = true;
            }
            else if (choice==3)
            {
                 Player p = new Archer();
                 arr[0] = p;
                 valid = true;
            }
            else if (choice==4)
            {
                 Player p = new Thief();
                 arr[0] = p;
                 valid = true;
            }
            else
                 System.out.println("Invalid choice, please retry.");
        }
    }
}
