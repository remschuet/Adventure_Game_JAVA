package GUI;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ScreenManager extends JFrame implements KeyListener {

    private final int WIND_WIDTH;
    private final int WIND_HEIGHT;

    private final List<GraphicalEntity> listGraphicalEntity;
    private final MovementEntity movementEntity;
    private int currentCenterX = 250 + 30;
    private int currentCenterY = 250 + 30;
    private final int[][] mapArray;

    ScreenManager(final int WIND_WIDTH, final int WIND_HEIGHT, List<GraphicalEntity> listGraphicalEntity, MovementEntity movementEntity, int[][] mapArray)
    {
        this.WIND_WIDTH = WIND_WIDTH;
        this.WIND_HEIGHT = WIND_HEIGHT;
        this.listGraphicalEntity = listGraphicalEntity;
        this.movementEntity = movementEntity;
        this.mapArray = mapArray;
        // Create Move Entity

        this.setTitle("My GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIND_WIDTH, WIND_HEIGHT);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    public void setInventoryAndPlayer(InventoryManager inventoryManager,char key)
    {
        inventoryManager.setInvisibleInventory(this.listGraphicalEntity);

        for (GraphicalEntity player: this.listGraphicalEntity)
        {
            if (player instanceof Player)
            {
                Gameplay.DIRECTION direction = null;
                switch (key) {
                    case 'a'-> direction = Gameplay.DIRECTION.LEFT;
                    case 'd'-> direction =  Gameplay.DIRECTION.RIGHT;
                    case 'w'-> direction =  Gameplay.DIRECTION.UP;
                    case 's'-> direction = Gameplay.DIRECTION.DOWN;
                }
                ((Player)player).setDirection(direction);
            }
        }
    }

    public void tryToUseItemInInventory(InventoryManager inventoryManager, char key)
    {
        int posX;
        int posY;

        for(GraphicalEntity cursor: this.listGraphicalEntity)
        {
            if (cursor instanceof TargetingCursor)
            {
                int blocX = 0, blocY = 0;
                Gameplay.DIRECTION direction = ((TargetingCursor)cursor).getDirection();
                switch (direction) {
                    case LEFT -> {
                        blocX = (int) Math.floor((this.currentCenterX - 30) / 60.f);
                        blocY = (int) Math.round((float) this.currentCenterY / 60.f + 0.5);
                    }
                    case RIGHT -> {
                        blocX = (int) Math.ceil((this.currentCenterX - 30) / 60.0) + 2;
                        blocY = (int) Math.round((float) this.currentCenterY / 60.f + 0.5);
                    }
                    case DOWN -> {
                        blocY = (int) Math.ceil((this.currentCenterY - 30.f) / 60.0) + 2;
                        blocX = (int) Math.round((float) this.currentCenterX / 60.f + 0.5);
                    }
                    case UP -> {
                        blocY = (int) Math.floor((this.currentCenterY - 30.f) / 60);
                        blocX = (int) Math.round((float) this.currentCenterX / 60.f + 0.5);
                    }
                }
                if (this.mapArray[blocX - 1][blocY - 1] == 0) {
                    posY = blocY * 60 - (this.currentCenterY - 250 + 30);
                    posX = blocX * 60 - (this.currentCenterX - 250 + 30);

                    String physicalBlockName = inventoryManager.tryToUseInventory(this.listGraphicalEntity, key);

                    if (!physicalBlockName.equals("")) {
                        this.mapArray[blocX - 1][blocY - 1] = 1;
                        createNewBlock(physicalBlockName, posX, posY, blocX, blocY);
                    }
                }
            }
            break;
        }

    }

    public void createNewBlock(String physicalBlock, int posX, int posY, int blocX, int blocY)
    {
        boolean blockActive = false;

        for (GraphicalEntity collidedEntity: this.listGraphicalEntity) {

            if (!(collidedEntity.getIfIsActive()) && collidedEntity.getImagePath().equals(physicalBlock)) {
                collidedEntity.setLocation(posX, posY);              // new Position
                collidedEntity.setActive();                             // active
                collidedEntity.setPosXY(blocX * 60 - 60, blocY * 60 - 60);
                blockActive = true;
                break;
            }
        }
        if (!blockActive)
            System.out.println("Create New Block not available for " + physicalBlock);
    }

    public void callMovement(int moveX, int moveY, InventoryManager inventoryManager, char e)
    {   // When player press w-a-s-d
        if(movementEntity.movePigEntity(moveX, moveY))
        {
            switch (e)
            {
                case 'w', 's'-> this.currentCenterY -= moveY;
                case 'a', 'd'-> this.currentCenterX -= moveX;
            }
        }
        setInventoryAndPlayer(inventoryManager, e);
    }

    public void callEverySecond()
    {
        this.movementEntity.callEverySecond(currentCenterX, currentCenterY);
    }

    public void callEvery200()
    {
        this.movementEntity.callEvery200();
    }

    public void callShoot()
    {
        for(GraphicalEntity player: this.listGraphicalEntity)

            if (player instanceof Player) {
                Gameplay.DIRECTION direction = ((Player)player).getDirection();
                if (Bullet.decreaseNbrAmmo()) {
                    // GET POSITION from player.Cursor.getPosXY ou player pour des collisions
                    Bullet bullet = new Bullet(this.WIND_WIDTH / 2 - 25, this.WIND_HEIGHT / 2 - 25, 20, 20, "TargetingCursor.png", direction);
                    this.listGraphicalEntity.add(10, bullet);
                    this.add(bullet, 0);
                }
                break;
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        InventoryManager inventoryManager = new InventoryManager();

        switch (e.getKeyChar())
        {
            case 'a'-> callMovement(10, 0, inventoryManager, e.getKeyChar());
            case 's'-> callMovement(0, -10, inventoryManager, e.getKeyChar());
            case 'd'-> callMovement(-10, 0, inventoryManager, e.getKeyChar());
            case 'w'-> callMovement(0, 10, inventoryManager, e.getKeyChar());
            case 'e'-> inventoryManager.setVisibilityInventory(this.listGraphicalEntity);
            case 'f'-> inventoryManager.playerSearchCollaborationEntity(this.listGraphicalEntity);
            case '0', '1', '2', '3', '4', '5', '6' -> tryToUseItemInInventory(inventoryManager, e.getKeyChar());
            case 'q'-> inventoryManager.mineBlock(this.listGraphicalEntity, this.mapArray);
            case 'x' -> callShoot();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
