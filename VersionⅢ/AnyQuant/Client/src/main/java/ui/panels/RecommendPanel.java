package ui.panels;

import businesslogic.RecommendPythonimpl;
import businesslogicservice.RecommendPython;
import ui.FontFactory;
import vo.RecommendVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2016/5/5.
 */
public class RecommendPanel extends JPanel{
    JLabel l_or,l_grs,text;
    OrangeRecommend orangeRecommend;
    GoodReferralShares goodReferralShares;
    RecommendPython recommendPython;
    public RecommendPanel(String name){
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(32,34,39));
        recommendPython=new RecommendPythonimpl();
        RecommendVO recommendVO=recommendPython.getRecommendVoByPython(name);
        l_or=new JLabel();
        l_or.setOpaque(false);
        l_or.setForeground(Color.magenta);

        l_or.setFont(FontFactory.getChinese(Font.PLAIN,20));
        l_or.setText("牛叉诊股");
        l_or.setBounds(50,20,150,50);
        this.add(l_or);

        goodReferralShares=new GoodReferralShares(recommendVO.getAnalyzeNum(),recommendVO.getAnalyzeString(),recommendVO.getMTrend(),recommendVO.getITrend(),recommendVO.getLTrend(),recommendVO.getAnalString(),recommendVO.getStartNum());
        goodReferralShares.setBounds(50,70,950,250);
        this.add(goodReferralShares);

        l_grs=new JLabel();
        l_grs.setOpaque(false);
        l_grs.setForeground(Color.magenta);

        l_grs.setFont(FontFactory.getChinese(Font.PLAIN,20));
        l_grs.setText("机构评级");
        l_grs.setBounds(50,320,150,50);
        this.add(l_grs);

        text=new JLabel();
        text.setOpaque(false);
        text.setForeground(Color.black);
        text.setFont(FontFactory.getChinese(Font.PLAIN,20));
        text.setText("——数据来源同花顺财经");
        text.setBounds(750,630,230,40);
        this.add(text);

        orangeRecommend=new OrangeRecommend(recommendVO.getOrangeAna());
        orangeRecommend.setBounds(50,370,900,350);
        this.add(orangeRecommend);
    }
}
