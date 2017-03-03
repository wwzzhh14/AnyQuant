package main;


//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import net.LoginNetImpl;
import ui.LoadingUI;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;


/**
 * Created by HP on 2016/3/2.
 */
public class Main {

    private static boolean netCheck=true;

    public static void main(String[] args) {

        LoadingUI lodingui = new LoadingUI();
        lodingui.setVisible(true);

        try{
            LoginNetImpl check = new LoginNetImpl();
            netCheck = check.connect();
        }catch (Exception e){
            netCheck = false;
        }

        if(netCheck == true) {

            MainFrame frame = new MainFrame();
            lodingui.setVisible(false);
            frame.setVisible(true);

        }else{
            lodingui.setVisible(false);
            System.out.print("catch");
            JOptionPane.showMessageDialog(null,
                    "网络连接错误,请检查网络环境!", "系统信息", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

}
