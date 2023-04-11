package GUI;

import java.util.ArrayList;
import java.util.List;

public class Gameplay {

    private final int NB_CASE = 30;
    private final int[][] mapArray = new int[NB_CASE][NB_CASE];

    private final static int sizeCase = 60;
    private final static boolean isTesting = false;
    private static final int WIND_WIDTH = 600;
    private static final int WIND_HEIGHT = 600;

    private List<GraphicalEntity> listGraphicalEntity = new ArrayList<>();

    public enum DIRECTION {DOWN, LEFT, UP, RIGHT};

    public Gameplay()
    {
        // Create Move Entity
        MovementEntity movementEntity = new MovementEntity(this.listGraphicalEntity, mapArray);

        // Create Screen Manager
        ScreenManager myFrame = new ScreenManager(WIND_WIDTH, WIND_HEIGHT, this.listGraphicalEntity, movementEntity, mapArray);
        myFrame.setLayout(null);

        TargetingCursor targetingCursor = new TargetingCursor
                (240, 240, 20, 20, "", DIRECTION.UP, WIND_WIDTH, WIND_HEIGHT); // TargetingCursor.png
        this.listGraphicalEntity.add(targetingCursor);

        // Create player
        Player player = new Player(0, 0, 60, 60, "Water.png", WIND_WIDTH, WIND_HEIGHT, targetingCursor);
        this.listGraphicalEntity.add(player);
        movementEntity.setPlayer(player);

        // Create inventory
        Inventory inventory = new Inventory(WIND_WIDTH / 2 - 120,WIND_HEIGHT / 2 - 120, 200, (int)(200 * 1.26), "Inventory.png");
        this.listGraphicalEntity.add(inventory);

        // Create Animal
        listGraphicalEntity.add(new Pig(sizeCase * 2, sizeCase * 2, sizeCase, sizeCase, "Pig.png"));
        // listGraphicalEntity.add(new Wolf(sizeCase * 2, sizeCase * 10, sizeCase, sizeCase, "Wolf.png"));

        if (!isTesting) {
            listGraphicalEntity.add(new Pig(sizeCase * 4, sizeCase * 20, sizeCase, sizeCase, "Pig.png"));
            listGraphicalEntity.add(new Pig(sizeCase * 10, sizeCase * 7, sizeCase, sizeCase, "Pig.png"));
            listGraphicalEntity.add(new Pig(sizeCase * 8, sizeCase * 10, sizeCase, sizeCase, "Pig.png"));

            listGraphicalEntity.add(new Wolf(sizeCase * 2, sizeCase * 22, sizeCase, sizeCase, "Wolf.png"));
            listGraphicalEntity.add(new Wolf(sizeCase * 28, sizeCase * 22, sizeCase, sizeCase, "Wolf.png"));
        }
        // Create map
        new MapGenerator(this.listGraphicalEntity, mapArray, isTesting);


        // Add entity to screen
        for(GraphicalEntity entity : this.listGraphicalEntity)
            myFrame.add(entity);

        // Timer
        new MyTimer(myFrame);
    }
}
