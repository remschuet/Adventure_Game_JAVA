package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Gameplay {

    private final int WIND_WIDTH = 600;
    private final int WIND_HEIGHT = 600;
    private List<GraphicalEntity> listGraphicalEntity = new ArrayList<>();
    public enum DIRECTION {DOWN, LEFT, UP, RIGHT};

    public Gameplay()
    {
        // Create Move Entity
        MovementEntity movementEntity = new MovementEntity(this.listGraphicalEntity);

        // Create Screen Manager
        ScreenManager myFrame = new ScreenManager(this.WIND_WIDTH, this.WIND_HEIGHT, this.listGraphicalEntity, movementEntity);
        myFrame.setLayout(null);

        TargetingCursor targetingCursor = new TargetingCursor
                (240, 240, 20, 20, "TargetingCursor.png", DIRECTION.UP, this.WIND_WIDTH, this.WIND_HEIGHT);
        this.listGraphicalEntity.add(targetingCursor);

        // Create player
        Player player = new Player(0, 0, 60, 60, "Water.png", WIND_WIDTH, WIND_HEIGHT, targetingCursor);
        this.listGraphicalEntity.add(player);
        myFrame.setPlayer(player);
        movementEntity.setPlayer(player);

        // Create inventory
        Inventory inventory = new Inventory(WIND_WIDTH / 2 - 120,WIND_HEIGHT / 2 - 120, 200, (int)(200 * 1.26), "Inventory.png");
        this.listGraphicalEntity.add(inventory);        // Set it visible

        Animal pig = new Animal(180, 180, 60, 60, "Pig.png");
        listGraphicalEntity.add(pig);

        MapGenerator map = new MapGenerator(this.listGraphicalEntity);

        // Add entity to screen
        for(GraphicalEntity entity : this.listGraphicalEntity)
            myFrame.add(entity);
        myFrame.createNewBlock();

        // Timer
        MyTimer timer = new MyTimer(movementEntity);
    }
}
