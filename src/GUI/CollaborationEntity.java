package GUI;

public class CollaborationEntity extends PhysicalEntity{

    protected String inventoryItemName;
    protected int usageDurability;

    CollaborationEntity(int posX, int posY, int width, int height, String path)
    {
        super(posX, posY, width, height, path);
    }

    public String getInventoryItemName()
    {
        return this.inventoryItemName;
    }

    public int getUsageDurability()
    {
        return this.usageDurability;
    }

    public void decreaseUsageDurability() {
        decreaseUsageDurability(1);
    }

    public void decreaseUsageDurability(int value) {
        this.usageDurability -= value;
    }
}
