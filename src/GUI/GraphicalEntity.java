package GUI;

import javax.swing.*;
import java.awt.*;

public class GraphicalEntity extends JLabel{

    private static LoadImageRAM loadImageRAM = new LoadImageRAM();

    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected String imagePath;
    protected boolean isActive;

    GraphicalEntity(int posX, int posY, int width, int height, String imagePath)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;

        this.setIcon(loadImageRAM.imageMap.get(imagePath));

        this.setBackground(Color.RED);
        this.setBounds(this.posX, this.posY, this.width, this.height);
        this.isActive = true;
    }

    public void setInactive()
    {
        this.isActive = false;
        this.setIcon(new ImageIcon(""));
    }

    public void setActive()
    {
        this.isActive = true;
        this.setIcon(loadImageRAM.imageMap.get(this.imagePath));
    }

    public boolean getIfIsActive()
    {
        return this.isActive;
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    public void setPosXY(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
        System.out.println("New Pos X : " + this.posX + " Y : " + this.posY);
    }
}