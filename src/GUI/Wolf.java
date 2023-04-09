package GUI;

import java.util.List;

public class Wolf extends Animal{

    List<AStarAlgorithm.Node> path;

    Wolf(int posX, int posY, int width, int height, String path) {
        super(posX, posY, width, height, path);

        this.inventoryItemName = "MeatItem.png";
        this.usageDurability = 1;
    }

    public void updatePath(int[][] mapArray, int currentCenterX, int currentCenterY)
    {
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        // initialisation des noeuds de départ et d'arrivée
        System.out.println("Wolf : " + mapArray[8][7]);

        AStarAlgorithm.Node src = new AStarAlgorithm.Node(
                (int)(this.getX() + (currentCenterX - 280)) / 60,
                (int)(this.getY() + (currentCenterY - 280)) / 60, 0, 0, 0);
        AStarAlgorithm.Node dest = new AStarAlgorithm.Node(
                (int)(currentCenterX / 60), (int)(currentCenterY / 60), 0, 0, 0);

        this.path = starAlgorithm.aStarSearch(mapArray, src, dest);
    }
}
