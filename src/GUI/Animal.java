package GUI;

public class Animal extends CollaborationEntity {

    private static final int SPEED = 30;
    private static final int RUN = 20;
    private final int[] direction = new int[]{SPEED, 0};              // x, y

    Animal(int posX, int posY, int width, int height, String path) {
        super(posX, posY, width, height, path);
    }

    public int getPosX()
    {
        return getX();
    }

    public int getPosY()
    {
        return this.posY;
    }

    public int getSpeed() {
        return SPEED;
    }

    public int getRun()
    {
        return RUN;
    }

    public int getDirectionX()
    {
        return this.direction[0];
    }

    public int getDirectionY()
    {
        return this.direction[1];
    }

    public void increasePos(GraphicalEntity entity, int increaseX, int increaseY)
    {
        if (increaseX != 0)
        {
            this.direction[0] = SPEED * (increaseX / Math.abs(increaseX));      // récupérer le signe
            this.direction[1] = 0;
        }
        else if (increaseY != 0)
        {
            this.direction[0] = 0;
            this.direction[1] = SPEED * (increaseY / Math.abs(increaseY));
        }
        entity.setLocation(entity.getX() + increaseX, entity.getY() + increaseY);
    }

    public void decreasePos(GraphicalEntity entity, int decreaseX, int decreaseY)
    {
        entity.setLocation(entity.getX() - decreaseX, entity.getY() - decreaseY);
    }
}
