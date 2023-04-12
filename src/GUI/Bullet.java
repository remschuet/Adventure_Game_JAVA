package GUI;

public class Bullet extends CollaborationEntity{

    private final static int SPEED = 25;
    private static int NbrAmmo = 5;

    private int nbrMovement = 30;
    private final Gameplay.DIRECTION DIRECTION;

    Bullet(int posX, int posY, int width, int height, String path, Gameplay.DIRECTION DIRECTION)
    {
        super(posX, posY, width, height, path);
        this.DIRECTION = DIRECTION;
    }

    public boolean decreaseNbrMovement()
    {
        nbrMovement--;
        return nbrMovement > 0;     // return true if > 0
    }

    public Gameplay.DIRECTION getDIRECTION()
    {
        return this.DIRECTION;
    }

    public int getSpeed()
    {
        return SPEED;
    }

    public void increasePos(GraphicalEntity entity, int increaseX, int increaseY)
    {
        entity.setLocation(entity.getX() + increaseX, entity.getY() + increaseY);
    }

    public static boolean decreaseNbrAmmo()
    {
        NbrAmmo--;
        return NbrAmmo >= 0;
    }
}