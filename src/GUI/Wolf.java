package GUI;

import java.util.List;

public class Wolf extends Animal{

    List<AStarAlgorithm.Node> path;

    Wolf(int posX, int posY, int width, int height, String path) {
        super(posX, posY, width, height, path);

        this.inventoryItemName = "MeatItem.png";
        this.usageDurability = 1;

    }

    public void createPath(int[][] mapArray, int currentCenterX, int currentCenterY)
    {
        AStarAlgorithm starAlgorithm = new AStarAlgorithm();
        // initialisation des noeuds de départ et d'arrivée
        System.out.println((this.getX()) + " - " + (this.getY()));

        AStarAlgorithm.Node src = new AStarAlgorithm.Node(
                (int)(this.getX() + (currentCenterX - 280)) / 60,
                (int)(this.getY() + (currentCenterY - 280)) / 60, 0, 0, 0);
        AStarAlgorithm.Node dest = new AStarAlgorithm.Node(2, 2, 0, 0, 0);

        this.path = starAlgorithm.aStarSearch(mapArray, src, dest);

        for (AStarAlgorithm.Node node : this.path) {
            System.out.println("(" + node.x + ", " + node.y + ")");
        }
    }

}
