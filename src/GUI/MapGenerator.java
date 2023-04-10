package GUI;

import java.util.List;

public class MapGenerator {
    private final List<GraphicalEntity> listGraphicalEntity;
    private final int NB_CASE = 30;
    private int[][] mapArray;



    MapGenerator(List<GraphicalEntity> listGraphicalEntity, int[][] mapArray, boolean isTesting)
    {
        this.listGraphicalEntity = listGraphicalEntity;
        this.mapArray = mapArray;

        initTheMap();
        setWater();
        setRock();
        setWood();
        setBerry();
        createCase();
        createTree(isTesting);

        listGraphicalEntity.add(new GraphicalEntity(-360, -360, 1800, 1800, "Background.png"));
        listGraphicalEntity.add(new GraphicalEntity(-360, 1800 -420, 1800, 1800, "Background.png"));
        listGraphicalEntity.add(new GraphicalEntity(1800 -420, -360, 1800, 1800, "Background.png"));
        listGraphicalEntity.add(new GraphicalEntity(1800 -420, 1800 -420, 1800, 1800, "Background.png"));
    }

    private void initTheMap()
    {
        for (int x = 0; x < this.NB_CASE; x++)
            for (int y = 0; y < this.NB_CASE; y++)
            {
                if (x == 0 || y == 0 || x == this.NB_CASE - 1 || y == this.NB_CASE - 1)
                    this.mapArray[x][y] = -1;
                else
                    this.mapArray[x][y] = 0;
            }
    }

    private  void setWater()
    {
        this.mapArray[3][4] = 1;
        this.mapArray[3][5] = 1;
        this.mapArray[3][6] = 1;
        this.mapArray[2][6] = 1;
        this.mapArray[3][7] = 1;
/*
        this.mapArray[2][21] = 1;
        this.mapArray[3][21] = 1;
        this.mapArray[4][21] = 1;
        this.mapArray[6][21] = 1;
        this.mapArray[7][21] = 1;
        this.mapArray[3][20] = 1;
        this.mapArray[4][20] = 1;
        this.mapArray[6][19] = 1;
        this.mapArray[7][22] = 1;
        this.mapArray[7][20] = 1;   */
    }

    private void setRock()
    {
        //mapArray[2][26] = 4;
        // mapArray[6][28] = 4;

        mapArray[5][1] = 2;
        mapArray[6][1] = 2;
    }

    private void setWood()
    {
        mapArray[7][4] = 3;                               mapArray[10][4] = 3; mapArray[11][4] = 3;
        mapArray[7][5] = 3;                                               mapArray[11][5] = 3;
        mapArray[7][6] = 3;                                               mapArray[11][6] = 3;
        mapArray[7][7] = 3;                                               mapArray[11][7] = 3;
        mapArray[7][8] = 3; mapArray[8][8] = 3; mapArray[9][8] = 3; mapArray[10][8] = 3; mapArray[11][8] = 3;
    }

    public void setBerry()
    {
        mapArray[8][9] = 4;
        mapArray[9][9] = 4;
        mapArray[10][9] = 4;

        mapArray[2][25] = 4;
        mapArray[2][26] = 4;
        mapArray[6][28] = 4;
    }

    private void createCase()
    {
        for (int x = 0; x < this.NB_CASE; x++)
            for (int y = 0; y < this.NB_CASE; y++)
            {
                String textureName = "Grass.png";       // for -1
                switch (mapArray[x][y])
                {
                    case 1: textureName = "Water.png"; break;
                    case 2: textureName = "Rock.png"; break;
                    case 3: textureName = "Wood.png"; break;
                    case 4: textureName = "Berry.png"; break;
                }

                if (mapArray[x][y] == - 1)
                    listGraphicalEntity.add(new PhysicalEntity(x * 60, y * 60, 60, 60, textureName));
                else if(mapArray[x][y] == 1)
                    listGraphicalEntity.add(new PhysicalEntity(x * 60, y * 60, 60, 60, textureName));
                else if(mapArray[x][y] == 2)
                    listGraphicalEntity.add(new Rock(x * 60, y * 60, 60, 60, textureName, "RockItem.png"));
                else if(mapArray[x][y] == 3)
                    listGraphicalEntity.add(new Wood(x * 60, y * 60, 60, 60, textureName, "WoodItem.png"));
                else if (mapArray[x][y] == 4)
                    listGraphicalEntity.add(new Berry(x * 60, y * 60, 60, 60, textureName));
                else if (mapArray[x][y] != 0)
                    listGraphicalEntity.add(new PhysicalEntity(x * 60, y * 60, 60, 60, textureName));

                if (this.mapArray[x][y] > 0 || this.mapArray[x][y] == -1)
                    this.mapArray[x][y] = 1;    // Can't move
                else
                    this.mapArray[x][y] = 0;    // Can't move
            }
    }

    private void displayTree(int posX, int posY) {
        listGraphicalEntity.add(new Tree(60 * posX, 60 * posY, 120, 120, "Tree.png"));
        for (int i = 0; i < 5; i++) {
            Wood wood1 = new Wood(60, 60, 60, 60, "Wood.png", "WoodItem.png");
            listGraphicalEntity.add(wood1);
            wood1.setInactive();
        }
    }
    public void createTree(boolean isTesting)
    {
        displayTree(1, 1);
        if (!isTesting) {
            displayTree(3, 12);
            displayTree(5, 12);
            displayTree(3, 14);

            displayTree(2, 27);
            displayTree(4, 27);
            displayTree(7, 27);
            displayTree(9, 27);
            displayTree(3, 25);
            displayTree(5, 25);

        }
    }
}
