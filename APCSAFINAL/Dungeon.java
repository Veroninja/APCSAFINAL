
public class Dungeon
{
    public Dungeon(Object[][][] dungeon)
    {
        //column 0
        dungeon[0][0][0] = new Enhance();
        
        dungeon[0][17][0] = new Enhance();
        dungeon[1][17][0] = new IronSkin();
        
        dungeon[0][6][0] = new Goblins();
        dungeon[1][6][0] = new Goblins();
        
        dungeon[0][20][0] = new Slimes();
        dungeon[1][20][0] = new Enhance();
        
        dungeon[0][24][0] = new Enhance();
        dungeon[1][24][0] = new IronSkin();
        dungeon[2][24][0] = new Heal();
        
        dungeon[0][27][0] = new Goblins();
        
        //column 1
        dungeon[0][1][1] = new Goblins();
        
        dungeon[0][4][1] = new Heal();
        dungeon[1][4][1] = new IronSkin();
        
        dungeon[0][8][1] = new Slimes();
        dungeon[1][8][1] = new Slimes();
        
        dungeon[0][11][1] = new Slimes();
        
        dungeon[0][13][1] = new Enhance();
        dungeon[1][13][1] = new IronSkin();
        
        dungeon[0][25][1] = new Goblins();
        
        //column 2
        
        //column 3
        
        //column 4
        
        //column 5
        
        //column 6
        
        //column 7
        
        //column 8
        
        //column 9
        
        //column 10
        
        //column 11
        
        //column 12
        
        //column 13
        
        //column 14
        
        //column 15
        
        //column 16
        
        //column 17
        
        //column 18
        
        //column 19
        
        //column 20
    }
}
