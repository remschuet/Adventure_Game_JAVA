package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class LoadImageRAM {

    public HashMap<String, ImageIcon> imageMap = new HashMap<>();

    LoadImageRAM() {
        File folder = new File("C:\\Users\\remsc\\OneDrive\\Documents\\Java Project\\FirstGui\\image");
        File[] files = folder.listFiles();

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile() && fileName.endsWith(".png")) {
                ImageIcon image = new ImageIcon(fileName);
                int[] size = getSizeForImage(fileName); // get imge size

                Image imageResized = image.getImage().getScaledInstance(size[0], size[1], Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(imageResized);

                imageMap.put(fileName, imageIcon);
            }
        }
    }

    private int[] getSizeForImage(String imageName) {
        if (imageName.endsWith("Item.png")) {
            return new int[]{50, 50};
        } else if (imageName.equals("Inventory.png")) {
            return new int[]{200, (int)(200 * 1.26)};
        }else if(imageName.equals("Background.png")) {
            return new int[]{1800, 1800};
        }else if (imageName.equals("Tree.png")){
            return new int[]{120, 120};
        }else if (imageName.equals("TargetingCursor.png")) {
            return new int[]{20, 20};
        }
        else
            return new int[]{60, 60};
    }
}
