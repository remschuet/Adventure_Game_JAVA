package GUI;

import java.util.ArrayList;
import java.util.List;

public class Gameplay {

    private static final int WIND_WIDTH = 600;
    private static final int WIND_HEIGHT = 600;

    private final int NB_CASE = 30;
    private final int[][] mapArray = new int[NB_CASE][NB_CASE];

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
                (240, 240, 20, 20, "TargetingCursor.png", DIRECTION.UP, WIND_WIDTH, WIND_HEIGHT);
        this.listGraphicalEntity.add(targetingCursor);

        // Create player
        Player player = new Player(0, 0, 60, 60, "Player.png", WIND_WIDTH, WIND_HEIGHT, targetingCursor);
        this.listGraphicalEntity.add(player);
        myFrame.setPlayer(player);
        movementEntity.setPlayer(player);

        // Create inventory
        Inventory inventory = new Inventory(WIND_WIDTH / 2 - 120,WIND_HEIGHT / 2 - 120, 200, (int)(200 * 1.26), "Inventory.png");
        this.listGraphicalEntity.add(inventory);

        // Create Animal
        Pig pig = new Pig(180, 180, 60, 60, "Pig.png");
        listGraphicalEntity.add(pig);

        Wolf wolf = new Wolf(180, 600, 60, 60, "Wolf.png");
        listGraphicalEntity.add(wolf);

        // Create map
        new MapGenerator(this.listGraphicalEntity, mapArray);

        // Add entity to screen
        for(GraphicalEntity entity : this.listGraphicalEntity)
            myFrame.add(entity);

        // Timer
        new MyTimer(myFrame);
    }
}
