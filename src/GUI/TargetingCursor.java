package GUI;

public class TargetingCursor extends CollaborationEntity{

    private Gameplay.DIRECTION direction;
    private final int WIND_WIDTH;
    private final int WIND_HEIGHT;

    TargetingCursor(int posX, int posY, int width, int height, String imagePath, Gameplay.DIRECTION direction, int WIND_WIDTH, int WIND_HEIGHT)
    {
        super(posX, posY, width, height, imagePath);
        this.direction = direction;
        this.WIND_WIDTH = WIND_WIDTH;
        this.WIND_HEIGHT = WIND_HEIGHT;
    }

    public void setDirection(Gameplay.DIRECTION direction)
    {
        this.direction = direction;

        int posX = 30, posY = 30;

        switch (direction)
        {
            case UP     ->  {posX = this.WIND_WIDTH / 2 - 30; posY = this.WIND_HEIGHT / 2 - 90;}
            case DOWN   ->  {posX = this.WIND_WIDTH / 2 - 30; posY = this.WIND_HEIGHT / 2 + 30;}
            case LEFT   ->  {posX = this.WIND_WIDTH / 2 - 90; posY = this.WIND_HEIGHT / 2 - 30;}
            case RIGHT  ->  {posX = this.WIND_WIDTH / 2 + 30; posY = this.WIND_HEIGHT / 2 - 30;}
        }
        this.setLocation(posX, posY);
    }

    public Gameplay.DIRECTION getDirection()
    {
        return this.direction;
    }
}
