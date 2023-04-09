package GUI;

import javax.swing.*;

public class MyTimer {

    private ScreenManager myFrame;

    MyTimer(ScreenManager myFrame)
    {
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
