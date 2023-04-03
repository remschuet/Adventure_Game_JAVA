package GUI;

import javax.swing.*;

public class MyTimer {

    private final MovementEntity movementEntity;

    MyTimer(MovementEntity movementEntity)
    {
        this.movementEntity = movementEntity;

        int delay = 1000;
        Timer timer = new Timer(delay, e -> callEverySecond());
        timer.start();
    }

    private void callEverySecond()
    {
        this.movementEntity.callEverySecond();
    }
}
