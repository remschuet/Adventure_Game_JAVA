package GUI;

public class Berry extends CollaborationEntity {

    Berry(int posX, int posY, int width, int height, String path) {
        super(posX, posY, width, height, path);

        this.inventoryItemName = "AppleItem.png";
        this.usageDurability = 2;
    }
}
