package GUI;

import javax.swing.*;

public class MyTimer {

    private final ScreenManager myFrame;
    private final static int DELAY1 = 1000;
    private final static int DELAY2 = 50;

    MyTimer(ScreenManager myFrame)
    {
        this.myFrame = myFrame;

        Timer timerMovement = new Timer(DELAY1, e -> callEverySecond());
        timerMovement.start();

        Timer timerBullet = new Timer(DELAY2, e -> callEvery100());
        timerBullet.start();
    }


    private void callEverySecond()
    {
        this.myFrame.callEverySecond();
    }

    private void callEvery100()
    {
        this.myFrame.callEvery200();
    }
}
