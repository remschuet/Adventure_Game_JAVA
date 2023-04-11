package GUI;

public class Bullet extends PhysicalEntity{

    private final static int SPEED = 10;
    private int nbrMovement = 10;
    private Gameplay.DIRECTION direction;

    Bullet(int posX, int posY, int width, int height, String path, Gameplay.DIRECTION direction)
    {
        super(posX, posY, width, height, path);
        this.direction = direction;
    }

    public boolean decreaseNbrMovement()
    {
        nbrMovement--;
        return nbrMovement > 0;     // return true if > 0
    }

    public Gameplay.DIRECTION getDirection()
    {
        return this.direction;
    }

    public int getSpeed()
    {
        return SPEED;
    }

    public void increasePos(GraphicalEntity entity, int increaseX, int increaseY)
    {
        entity.setLocation(entity.getX() + increaseX, entity.getY() + increaseY);
    }

    public void decreasePos(GraphicalEntity entity, int decreaseX, int decreaseY)
    {
        entity.setLocation(entity.getX() - decreaseX, entity.getY() - decreaseY);
    }
}
