package GUI;

public class Bullet extends PhysicalEntity{

    private final static int SPEED = 10;
    private int nbrMovement = 10;

    Bullet(int posX, int posY, int width, int height, String path)
    {
        super(posX, posY, width, height, path);
    }

    public boolean decreaseNbrMovement()
    {
        nbrMovement--;
        return nbrMovement > 0;     // return true if > 0
    }

    public int getSpeed()
    {
        return SPEED;
    }
}
