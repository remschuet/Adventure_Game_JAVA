package GUI;

public class Animal extends CollaborationEntity {

    private final int speed = 30;
    private final int run = 20;
    private int[] direction = new int[]{speed, 0};              // x, y

    Animal(int posX, int posY, int width, int height, String path) {
        super(posX, posY, width, height, path);

    }

    public int getSpeed() {
        return this.speed;
    }

    public int getRun()
    {
        return this.run;
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
            this.direction[0] = speed * (increaseX / Math.abs(increaseX));      // récupérer le signe
            this.direction[1] = 0;
        }
        else if (increaseY != 0)
        {
            this.direction[0] = 0;
            this.direction[1] = speed * (increaseY / Math.abs(increaseY));
        }
        entity.setLocation(entity.getX() + increaseX, entity.getY() + increaseY);
    }

    public void decreasePos(GraphicalEntity entity, int decreaseX, int decreaseY)
    {
        entity.setLocation(entity.getX() - decreaseX, entity.getY() - decreaseY);
    }
}
