package Demo.ui.myui;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HP on 2016/3/9.
 */
public class MySwitcher  extends TimerTask {
    private Timer timer;
    private SwitcherService panel;
    private int start_x;
    private int start_y;
    private int end_x;
    private int end_y;
    private int x;
    public void switchPanel(SwitcherService panel, int start_x, int start_y, int end_x, int end_y){
        this.panel=panel;
        timer=new Timer();
        this.start_x=start_x;
        this.x=start_x;
        this.start_y=start_y;
        this.end_x=end_x;
        this.end_y=end_y;
        timer.schedule(this,0,50);
    }

    @Override
    public void run() {

        if(x<=end_x){
            timer.cancel();
            timer=null;
        }else {
            panel.init(x, start_y, false);
            x -= ((start_x - end_x) / 10);
        }

    }
}
