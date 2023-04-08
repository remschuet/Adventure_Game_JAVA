package GUI;

import javax.swing.*;

public class MyTimer {

    private final MovementEntity movementEntity;
    private ScreenManager myFrame;

    MyTimer(MovementEntity movementEntity, ScreenManager myFrame)
    {
        this.movementEntity = movementEntity;
        this.myFrame = myFrame;

        int delay = 1000;
        Timer timer = new Timer(delay, e -> callEverySecond());
        timer.start();
    }

    private void callEverySecond()
    {
        this.myFrame.callEverySecond();
    }
}
