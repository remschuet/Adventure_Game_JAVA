package GUI;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends GraphicalEntity{

    private final List<InventoryItem> listInventoryItem = new ArrayList<>();
    private boolean isVisible = false;

    Inventory(int posX, int posY, int width, int height, String imagePath)
    {
        super(posX, posY, width, height, imagePath);
        this.setVisible(false);
    }

    public int[] getPosItem(int nbrItem)
    {
        int[] position = {25, 35};
        switch (nbrItem) {
            case 1 -> position[0] = 120;
            case 2 -> position[1] = 110;
            case 3 -> { position[0] = 120; position[1] = 110; }
            case 4 -> position[1] = 182;
            case 5 -> { position[0] = 120; position[1] = 182; }
        }
        return position;
    }

    public void setNewItem(String name)
    {
        int nbrItem = listInventoryItem.size();
        if (nbrItem < 6)
        {
            int[] position = getPosItem(nbrItem);

            String blockType = name.replace("Item", "");

            InventoryItem item = new InventoryItem(position[0], position[1], 50, 50, name, blockType);
            this.listInventoryItem.add(item);
            this.add(item);
        }
        else
            System.out.println("Inventary full");
    }

    public void setVisibility()
    {
        if (!this.isVisible)
            displayItem();
        else
            hideItem();

        this.isVisible = !this.isVisible;
        setVisible(this.isVisible);
        this.setBounds(this.posX, this.posY, this.width, this.height);
    }
    public void setInvisible()
    {
        this.isVisible = false;
        setVisible(false);
    }

    public boolean getVisibility()
    {
        return this.isVisible;
    }

    public String useItem(int numberCallByUser)
    {
        InventoryItem inventoryItem;
        int index = 0;
        if (numberCallByUser > 0)
             index = numberCallByUser - 1;
        String physicalBlock = "";

        if (this.listInventoryItem.size() >= numberCallByUser && this.listInventoryItem.size() != 0) {
            hideItem();
            inventoryItem = this.listInventoryItem.get(index);
            physicalBlock = inventoryItem.getBlockType();
            this.listInventoryItem.remove(index);

            // Modify position for each element
            int positionItem = 0;
            for (InventoryItem item : this.listInventoryItem)
            {
                int[] position = getPosItem(positionItem);
                item.setLocation(position[0], position[1]);
                positionItem++;
            }

            displayItem();
        }
        else
            physicalBlock = "";
        return physicalBlock;
    }

    public void displayItem()
    {
        for(InventoryItem item : listInventoryItem)
            item.setVisible();
    }

    public void hideItem()
    {
        for(InventoryItem item : listInventoryItem)
            item.setInvisible();
    }
}
