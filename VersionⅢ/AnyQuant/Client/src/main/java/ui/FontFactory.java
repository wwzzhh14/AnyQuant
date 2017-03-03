package ui;

import java.awt.*;

/**
 * Created by Jiayiwu on 16/4/9.
 */
public class FontFactory {

    private static String chineseFontName;
    static{
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = e.getAvailableFontFamilyNames();
        String[] targetFontNames = {"雅黑","Microsoft YaHei","微软雅黑"};
        for(String aFontName:fontNames)
        {
            for(String aTargetFont:targetFontNames){
                if(aFontName.equals(aTargetFont)){
                    chineseFontName = aTargetFont;
                }
            }
        }
        if(chineseFontName==null){
            chineseFontName = "宋体";
        }

    }

    public static Font getChinese(int style, int size) {
        return new Font(chineseFontName,style,size);
    }
}
