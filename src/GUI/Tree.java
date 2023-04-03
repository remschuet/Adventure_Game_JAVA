package GUI;

public class Tree extends CollaborationEntity {

    Tree(int posX, int posY, int width, int height, String path) {
        super(posX, posY, width, height, path);

        this.inventoryItemName = "WoodItem.png";
        this.usageDurability = 5;
    }
}
