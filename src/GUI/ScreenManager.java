package GUI;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ScreenManager extends JFrame implements KeyListener {

    private final List<GraphicalEntity> listGraphicalEntity;
    private Player player;
    private MovementEntity movementEntity;

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
        inventoryManager.setInvisible(this.listGraphicalEntity);

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
        GraphicalEntity physicalBlock = inventoryManager.tryToUseInventory(this.listGraphicalEntity, key);

        if (physicalBlock != null)
            createNewBlock();
    }

    public void createNewBlock()
    {
        System.out.println("Create New Block not available");
        /*
        Rock rock = new Rock(0, 0, 60, 60, "Rock.png", "RockItem.png");
        rock.setBounds(180, 180, 60, 60);
        physicalBlock.setLocation(20, 40);
        ock.setVisible(true);
        listGraphicalEntity.add(rock);
        this.add(rock);
        int index = this.getComponentCount() - 1; // Index of the last component added
        this.setComponentZOrder(rock, index); // Moves the object in front of the others */
    }

    @Override
    public void keyTyped(KeyEvent e) {
        InventoryManager inventoryManager = new InventoryManager();

        switch (e.getKeyChar())
        {
            case 'a': movementEntity.moveEntity(10, 0);    setInventoryAndPlayer(inventoryManager, e.getKeyChar()); break;
            case 's': movementEntity.moveEntity(0, -10);   setInventoryAndPlayer(inventoryManager, e.getKeyChar()); break;
            case 'd': movementEntity.moveEntity(-10, 0);   setInventoryAndPlayer(inventoryManager, e.getKeyChar()); break;
            case 'w': movementEntity.moveEntity(0, 10);    setInventoryAndPlayer(inventoryManager, e.getKeyChar()); break;
            case 'e': inventoryManager.setStatementVisibility(this.listGraphicalEntity); break;
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
