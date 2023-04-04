package GUI;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ScreenManager extends JFrame implements KeyListener {

    private final List<GraphicalEntity> listGraphicalEntity;
    private Player player;
    private MovementEntity movementEntity;
    private int currentCenterX = 250 + 30;
    private int currentCenterY = 250 + 30;

    ScreenManager(final int WIND_WIDTH, final int WIND_HEIGHT, List<GraphicalEntity> listGraphicalEntity, MovementEntity movementEntity)
    {
        this.listGraphicalEntity = listGraphicalEntity;
        this.movementEntity = movementEntity;
        // Create Move Entity

        this.setTitle("My GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIND_WIDTH, WIND_HEIGHT);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void setInventoryAndPlayer(InventoryManager inventoryManager,char key)
    {
        inventoryManager.setInvisibleInventory(this.listGraphicalEntity);

        for (GraphicalEntity player: this.listGraphicalEntity)
        {
            if (player instanceof Player)
            {
                Gameplay.DIRECTION direction = Gameplay.DIRECTION.UP;
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
        String physicalBlockName = inventoryManager.tryToUseInventory(this.listGraphicalEntity, key);

        if (!physicalBlockName.equals("")) {
            int posX = 0;
            int posY = 0;

            for(GraphicalEntity cursor: this.listGraphicalEntity)
            {
                if (cursor instanceof TargetingCursor)
                {
                    Gameplay.DIRECTION direction = ((TargetingCursor)cursor).getDirection();
                    switch (direction)
                    {
                        case LEFT : posX = 130; posY = 250; break;
                        case RIGHT: posX = 370; posY = 250; break;
                        case DOWN : posX = 250; posY = 370; break;
                        case UP: posX = 250; posY = 130; break;
                        // FIX ME
                    }
                }
            }
            createNewBlock(physicalBlockName, posX, posY);
        }
    }

    public void createNewBlock(String physicalBlock, int posX, int posY)
    {
        boolean blockActive = false;

        for (GraphicalEntity collidedEntity: this.listGraphicalEntity) {

            if (!(collidedEntity.getIfIsActive()) && collidedEntity.getImagePath().equals(physicalBlock)) {
                collidedEntity.setLocation(posX, posY);              // new Position
                collidedEntity.setActive();                             // active
                blockActive = true;
                break;
            }
        }
        if (!blockActive)
            System.out.println("Create New Block not available for " + physicalBlock);
    }

    public void callMovement(int moveX, int moveY, InventoryManager inventoryManager, char e)
    {   // When player press w-a-s-d
        if(movementEntity.moveEntity(moveX, moveY))
        {
            switch (e)
            {
                case 'w': this.currentCenterY -= moveY; break;
                case 's': this.currentCenterY -= moveY; break;
                case 'a': this.currentCenterX -= moveX; break;
                case 'd': this.currentCenterX -= moveX; break;
            }
            System.out.println("PosX : " + this.currentCenterX + " PosY : " + this.currentCenterY);
        }
        setInventoryAndPlayer(inventoryManager, e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        InventoryManager inventoryManager = new InventoryManager();

        switch (e.getKeyChar())
        {
            case 'a': callMovement(10, 0, inventoryManager, e.getKeyChar()); break;
            case 's': callMovement(0, -10, inventoryManager, e.getKeyChar()); break;
            case 'd': callMovement(-10, 0, inventoryManager, e.getKeyChar()); break;
            case 'w': callMovement(0, 10, inventoryManager, e.getKeyChar()); break;
            case 'e': inventoryManager.setVisibilityInventory(this.listGraphicalEntity); break;
            case 'f': inventoryManager.playerSearchCollaborationEntity(this.listGraphicalEntity); break;
            case '1', '2', '3', '4', '5', '6' : tryToUseItemInInventory(inventoryManager, e.getKeyChar()); break;
            case 'q': ; inventoryManager.mineBlock(this.listGraphicalEntity); break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
