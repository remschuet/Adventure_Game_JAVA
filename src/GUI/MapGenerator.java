package GUI;

import java.util.List;

public class MapGenerator {
    private final List<GraphicalEntity> listGraphicalEntity;
    private final int NB_CASE = 30;
    private int[][] tab = new int[NB_CASE][NB_CASE];


    MapGenerator(List<GraphicalEntity> listGraphicalEntity)
    {
        this.listGraphicalEntity = listGraphicalEntity;
        initTheMap();
        setWater();
        setRock();
        setWood();
        setBerry();
        createCase();
        createTree();

        listGraphicalEntity.add(new GraphicalEntity(0, 0, 1800, 1800, "Background.png"));
    }

    private void initTheMap()
    {
        for (int x = 0; x < this.NB_CASE; x++)
            for (int y = 0; y < this.NB_CASE; y++)
            {
                if (x == 0 || y == 0 || x == this.NB_CASE - 1 || y == this.NB_CASE - 1)
                    this.tab[x][y] = -1;
                else
                    this.tab[x][y] = 0;
            }
    }

    private  void setWater()
    {
        this.tab[3][4] = 1;
        this.tab[3][5] = 1;
        this.tab[3][6] = 1;
        this.tab[2][6] = 1;
        this.tab[3][7] = 1;
    }

    private void setRock()
    {
        tab[5][1] = 2;
        tab[6][1] = 2;
    }

    private void setWood()
    {
        tab[7][4] = 3;                               tab[10][4] = 3; tab[11][4] = 3;
        tab[7][5] = 3;                                               tab[11][5] = 3;
        tab[7][6] = 3;                                               tab[11][6] = 3;
        tab[7][7] = 3;                                               tab[11][7] = 3;
        tab[7][8] = 3; tab[8][8] = 3; tab[9][8] = 3; tab[10][8] = 3; tab[11][8] = 3;
    }

    public void setBerry()
    {
        tab[8][9] = 4;
        tab[9][9] = 4;
        tab[10][9] = 4;
    }

    private void createCase()
    {
        //listGraphicalEntity.add(new GraphicalEntity(60, 60, 60, 60, "Background.png"));

        for (int x = 0; x < this.NB_CASE; x++)
            for (int y = 0; y < this.NB_CASE; y++)
            {
                String textureName = "Grass.png";       // for -1
                switch (tab[x][y])
                {
                    case 1: textureName = "Water.png"; break;
                    case 2: textureName = "Rock.png"; break;
                    case 3: textureName = "Wood.png"; break;
                    case 4: textureName = "Berry.png"; break;
                }

                if (tab[x][y] == - 1)
                    listGraphicalEntity.add(new PhysicalEntity(x * 60, y * 60, 60, 60, textureName));
                else if(tab[x][y] == 1)
                    listGraphicalEntity.add(new PhysicalEntity(x * 60, y * 60, 60, 60, textureName));
                else if(tab[x][y] == 2)
                    listGraphicalEntity.add(new Rock(x * 60, y * 60, 60, 60, textureName, "RockItem.png"));
                else if(tab[x][y] == 3)
                    listGraphicalEntity.add(new Wood(x * 60, y * 60, 60, 60, textureName, "WoodItem.png"));
                else if (tab[x][y] == 4)
                    listGraphicalEntity.add(new Berry(x * 60, y * 60, 60, 60, textureName));
                else if (tab[x][y] != 0)
                    listGraphicalEntity.add(new PhysicalEntity(x * 60, y * 60, 60, 60, textureName));
            }
    }

    public void createTree()
    {
        listGraphicalEntity.add(new Tree(60, 60, 120, 120, "Tree.png"));
        for(int i = 0; i < 5; i++)
        {
            Wood wood = new Wood(60, 60, 60, 60, "Wood.png", "WoodItem.png");
            listGraphicalEntity.add(wood);
            wood.setInactive();
        }
    }
}
