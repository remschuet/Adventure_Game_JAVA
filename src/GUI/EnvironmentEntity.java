package GUI;

public class EnvironmentEntity extends PhysicalEntity{

    private final String namePNG;

    EnvironmentEntity(int posX, int posY, int width, int height, String path, String namePNG)
    {
        super(posX, posY, width, height, path);
        this.namePNG = namePNG;
    }

    public String getItemNamePNG()
    {
        return this.namePNG;
    }
}
