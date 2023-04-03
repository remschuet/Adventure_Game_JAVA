package GUI;

public class InventoryItem extends GraphicalEntity{

    private boolean isVisible = false;
    private final String blockType;

    InventoryItem(int posX, int posY, int width, int height, String path, String blockType)
    {
        super(posX, posY, width, height, path);
        this.setVisible(false);
        this.blockType = blockType;
    }

    public void setInvisible()
    {
        this.isVisible = false;
        setVisible(false);
    }

    public void setVisible()
    {
        this.isVisible = true;
        setVisible(true);
    }

    public GraphicalEntity getBlockType()
    {
        Rock rock = new Rock(0, 0, 60, 60, "Rock.png", "RockItem.png");

        return rock;
    }
}