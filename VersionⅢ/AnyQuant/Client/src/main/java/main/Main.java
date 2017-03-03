package main;


import data.MACDDataImp;
import dataservice.MACDDataService;
import net.LoginNetImpl;
import ui.LoadingUI;
import ui.LoadingUIFactory;
import ui.MainFrame;
import util.PythonSpider;

import javax.swing.*;


/**
 * Created by HP on 2016/3/2.
 */
public class Main {
    private static boolean netCheck=true;
    private static  boolean startok = false;
 public   static   MainFrame frame ;
    public static void main(String[] args) {

        //update Macd


//        LoadingUI lodingui = new LoadingUI();
        LoadingUI lodingui = LoadingUIFactory.getLoadingUIFrame(2);
        lodingui.setVisible(true);
//        PythonSpider.initPython();
        MACDDataService macd = new MACDDataImp();
        macd.update();
        try {
            //延迟载入效果
//            Thread.currentThread().sleep(2000);
        }catch (Exception e){
            System.out.println("breaking");
        }


        try{
            LoginNetImpl check = new LoginNetImpl();
            netCheck = check.connect();
        }catch (Exception e){
            netCheck = false;
        }

        if(netCheck == true) {
            frame = new MainFrame();
            lodingui.setVisible(false);
            frame.setVisible(true);
            startok = true;

        }else{
            lodingui.setVisible(false);
            System.out.print("catch");
            JOptionPane.showMessageDialog(null,
                    "网络连接错误,请检查网络环境!", "系统信息", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

}

