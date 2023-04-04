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
                        case LEFT : {
                            int blocX = (int) Math.floor((this.currentCenterX - 30) / 60);
                            int blocY = Math.round(this.currentCenterY / 60) + 1;
                            System.out.println(blocX);
                            posY = blocY * 60 - (this.currentCenterY - 250 + 30);
                            posX = blocX * 60 - (this.currentCenterX - 250 + 30);
                        }
                        break;
                        case RIGHT: {
                            int blocX = (int) Math.ceil((this.currentCenterX -30) / 60.0) + 2;
                            int blocY = Math.round(this.currentCenterY / 60) + 1;
                            System.out.println(blocX);
                            posY = blocY * 60 - (this.currentCenterY - 250 + 30);
                            posX = blocX * 60 - (this.currentCenterX - 250 + 30);
                        } break;
                        case DOWN :
                        {
                            int blocY = (int) Math.ceil((this.currentCenterY - 30) / 60.0) + 2;
                            int blocX = Math.round(this.currentCenterX / 60);
                            System.out.println(blocY);
                            posY = blocY * 60 - (this.currentCenterY - 250 + 30);
                            posX = blocX * 60 - (this.currentCenterX - 250 + 30) + 60;
                        }
                        break;
                        case UP: {
                            int blocY = (int) Math.floor((this.currentCenterY - 30) / 60);
                            int blocX = Math.round(this.currentCenterX / 60);
                            System.out.println(blocY);
                            posY = blocY * 60 - (this.currentCenterY - 250 + 30);
                            posX = blocX * 60 - (this.currentCenterX - 250 + 30) + 60;
                        }break;
                    }
                }
            }
            createNewBlock(physicalBlockName, posX, posY);
        }

        /*

                            switch (direction)
                    {
                        case LEFT : posX = 130; posY = 250; break;
                        case RIGHT: posX = 370; posY = 250; break;
                        case DOWN : posX = 250; posY = 370; break;
                        case UP: {
                            posX = 250;
                            posY = 130;
                        }break;
         */
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
                case 'w', 's'-> this.currentCenterY -= moveY;
                case 'a', 'd'-> this.currentCenterX -= moveX;
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
            case 'a'-> callMovement(10, 0, inventoryManager, e.getKeyChar());
            case 's'-> callMovement(0, -10, inventoryManager, e.getKeyChar());
            case 'd'-> callMovement(-10, 0, inventoryManager, e.getKeyChar());
            case 'w'-> callMovement(0, 10, inventoryManager, e.getKeyChar());
            case 'e'-> inventoryManager.setVisibilityInventory(this.listGraphicalEntity);
            case 'f'-> inventoryManager.playerSearchCollaborationEntity(this.listGraphicalEntity);
            case '1', '2', '3', '4', '5', '6' -> tryToUseItemInInventory(inventoryManager, e.getKeyChar());
            case 'q'-> inventoryManager.mineBlock(this.listGraphicalEntity);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
