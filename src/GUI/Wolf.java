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
        // initialisation des noeuds de départ et d'arrivée
        AStarAlgorithm.Node src = new AStarAlgorithm.Node(
                (this.getX() + (currentCenterX - 280)) / 60,
                (this.getY() + (currentCenterY - 280)) / 60, 0, 0, 0);
        AStarAlgorithm.Node dest = new AStarAlgorithm.Node(
                (currentCenterX / 60), (currentCenterY / 60), 0, 0, 0);

        this.path = AStarAlgorithm.aStarSearch(mapArray, src, dest);
    }
}
