package GUI;

public class Player extends PhysicalEntity {

    private final int WIND_WIDTH;
    private final int WIND_HEIGHT;
    private final TargetingCursor targetingCursor;
    private Gameplay.DIRECTION direction;

    Player(int posX, int posY, int width, int height, String path, final int WIND_WIDTH, final int WIND_HEIGHT, TargetingCursor targetingCursor)
    {
        super(posX, posY, width, height, path);

        this.WIND_WIDTH = WIND_WIDTH;
        this.WIND_HEIGHT = WIND_HEIGHT;
        this.targetingCursor = targetingCursor;
        setStartPosition();                   // starting position
        setDirection(Gameplay.DIRECTION.UP);  // start direction
    }

    private void setStartPosition()
    {
        this.setBounds(this.WIND_WIDTH / 2 - 50, this.WIND_HEIGHT / 2 - 50, this.width, this.height);
    }

    public void setDirection(Gameplay.DIRECTION direction)
    {
        this.direction = direction;
        this.targetingCursor.setDirection(direction);
    }

    public Gameplay.DIRECTION getDirection()
    {
        return this.direction;
    }
}