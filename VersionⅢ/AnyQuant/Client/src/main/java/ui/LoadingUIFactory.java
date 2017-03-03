package ui;



/**
 * Created by Jiayiwu on 16/4/11.
 */
public class LoadingUIFactory {
//    static LoadingUI loadingUI = new LoadingUI();


    private LoadingUIFactory(){

    }

    public static LoadingUI getLoadingUIFrame(int num){


        return new LoadingUI(num);
    }

    
}
