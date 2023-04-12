package GUI;

public class Gun {
    private static int NbrAmmo = 7;

    public boolean Shoot()
    {
        if (NbrAmmo > 0)
        {
            NbrAmmo--;
            return true;
            }
        return false;
    }

    public void reload(int additional)
    {
        NbrAmmo += additional;
    }
}
