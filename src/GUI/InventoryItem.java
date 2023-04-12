package GUI;

public class InventoryItem extends GraphicalEntity{

    private final String blockType;

    InventoryItem(int posX, int posY, int width, int height, String path, String blockType)
    {
        super(posX, posY, width, height, path);
        this.setVisible(false);
        this.blockType = blockType;
    }

    public void setInvisible()
    {
        setVisible(false);
    }

    public void setVisible()
    {
        setVisible(true);
    }

    public String getBlockType()
    {
        return this.blockType;
    }
}