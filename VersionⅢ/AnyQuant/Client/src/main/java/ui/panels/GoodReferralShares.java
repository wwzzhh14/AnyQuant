package ui.panels;

import ui.FontFactory;

import ui.myui.EmptyTextField;
import ui.myui.MyButton;
import vo.RecommendStarVO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2016/5/4.
 */
public class GoodReferralShares extends JLabel {
    JLabel l_MTrend ,l_ITrend,l_LTrend,analyzeNum,AnalyzeString,l_star;
    JTextArea MTrend,ITrend,LTrend,AnalString;
    StarPanel tech, fee, infomation, industry, basic;
    JButton score,rank;
    EmptyTextField text_score,rank_score;
//    //技术面诊股
//    private double tech;
//    //资金面诊股
//    private double fee;
//    //消息面诊股
//    private double infomation;
//    //行业面诊股
//    private double industry;
//    //基本面诊股
//    private double basic;
    public GoodReferralShares(double aNum, String AString, String MT, String IT, String LT, String AnalS, RecommendStarVO recommendStarVO){
        l_star=new JLabel();
        l_star.setOpaque(false);
        l_star.setBounds(500,20,500,230);
        this.add(l_star);

        JLabel l_tech=new JLabel();
        l_tech.setOpaque(false);
        l_tech.setForeground(Color.white);
        l_tech.setText("技术面诊股 ");
        l_tech.setBounds(0,0,80,20);
        l_star.add(l_tech);
        JLabel n_tech=new JLabel();
        n_tech.setOpaque(false);
        n_tech.setForeground(Color.red);
        n_tech.setText(recommendStarVO.getTech()+"");
        n_tech.setBounds(80,0,30,20);
        l_star.add(n_tech);
        tech=new StarPanel(recommendStarVO.getTech());
        tech.setBounds(130,0,100,20);
        l_star.add(tech);

        JLabel l_fee=new JLabel();
        l_fee.setOpaque(false);
        l_fee.setForeground(Color.white);
        l_fee.setText("资金面诊股 ");
        l_fee.setBounds(0,30,80,20);
        l_star.add(l_fee);
        JLabel n_fee=new JLabel();
        n_fee.setOpaque(false);
        n_fee.setForeground(Color.red);
        n_fee.setText(recommendStarVO.getFee()+"");
        n_fee.setBounds(80,30,50,20);
        l_star.add(n_fee);
        fee=new StarPanel(recommendStarVO.getFee());
        fee.setBounds(130,30,100,20);
        l_star.add(fee);

        JLabel l_infomation=new JLabel();
        l_infomation.setOpaque(false);
        l_infomation.setForeground(Color.white);
        l_infomation.setText("消息面诊股 ");
        l_infomation.setBounds(0,60,80,20);
        l_star.add(l_infomation);
        JLabel n_infomation=new JLabel();
        n_infomation.setOpaque(false);
        n_infomation.setForeground(Color.red);
        n_infomation.setText(recommendStarVO.getInfomation()+"");
        n_infomation.setBounds(80,60,50,20);
        l_star.add(n_infomation);
        infomation=new StarPanel(recommendStarVO.getInfomation());
        infomation.setBounds(130,60,100,20);
        l_star.add(infomation);

        JLabel l_industry=new JLabel();
        l_industry.setOpaque(false);
        l_industry.setForeground(Color.white);
        l_industry.setText("行业面诊股 ");
        l_industry.setBounds(0,90,80,20);
        l_star.add(l_industry);
        JLabel n_industry=new JLabel();
        n_industry.setOpaque(false);
        n_industry.setForeground(Color.red);
        n_industry.setText(recommendStarVO.getIndustry()+"");
        n_industry.setBounds(80,90,50,20);
        l_star.add(n_industry);
        industry=new StarPanel(recommendStarVO.getIndustry());
        industry.setBounds(130,90,100,20);
        l_star.add(industry);

        JLabel l_basic=new JLabel();
        l_basic.setOpaque(false);
        l_basic.setForeground(Color.white);
        l_basic.setText("基本面诊股 ");
        l_basic.setBounds(0,120,80,20);
        l_star.add(l_basic);
        JLabel n_basic=new JLabel();
        n_basic.setOpaque(false);
        n_basic.setForeground(Color.red);
        n_basic.setText(recommendStarVO.getBasic()+"");
        n_basic.setBounds(80,120,50,20);
        l_star.add(n_basic);
        basic=new StarPanel(recommendStarVO.getBasic());
        basic.setBounds(130,120,100,20);
        l_star.add(basic);

        score = new MyButton(0,0,145,82);
        score.setIcon(new ImageIcon("ui/score-up.png"));
        text_score = new EmptyTextField(48,0,145,82);
        text_score.setForeground(Color.BLACK);
        text_score.setText(aNum+"");
        text_score.setFont(new Font("Arail", Font.BOLD, 35));
        text_score.setEditable(false);

        analyzeNum=new JLabel();
        analyzeNum.setOpaque(true);
        analyzeNum.setForeground(Color.black);
        analyzeNum.setBackground(new Color(32,34,39));
        analyzeNum.setFont(FontFactory.getChinese(Font.PLAIN,14));
//        analyzeNum.setIcon(new ImageIcon("ui/score-up.png"));
        analyzeNum.add(text_score);
        analyzeNum.add(score);
//        analyzeNum.setText(aNum+"");
        analyzeNum.setBounds(10,30,145,82);
        this.add(analyzeNum);

        rank = new MyButton(1,0,143,69);
        rank.setIcon(new ImageIcon("ui/score-bottom.png"));
        rank_score = new EmptyTextField(17,0,145,40);
        rank_score.setText(AString);
        rank_score.setEditable(false);

        AnalyzeString=new JLabel();
        AnalyzeString.setOpaque(true);
        AnalyzeString.setForeground(Color.black);
        AnalyzeString.setBackground(new Color(32,34,39));
        AnalyzeString.setFont(FontFactory.getChinese(Font.PLAIN,14));
//        AnalyzeString.setIcon(new ImageIcon("ui/score-bottom.png"));
        AnalyzeString.add(rank_score);
        AnalyzeString.add(rank);
//        AnalyzeString.setText(AString);
        AnalyzeString.setBounds(10,112,145,69);
        this.add(AnalyzeString);

        l_MTrend=new JLabel();
        l_MTrend.setForeground(Color.white);
        l_MTrend.setBackground(new Color(0,0,0,0));
        l_MTrend.setFont(FontFactory.getChinese(Font.PLAIN,14));
        l_MTrend.setText("短期趋势：");
        l_MTrend.setBounds(170,5,80,20);
        this.add(l_MTrend);

        MTrend=new JTextArea();
        MTrend.setBounds(250,5,200,40);
        MTrend.setOpaque(false);
        MTrend.setForeground(Color.red);
        MTrend.setText(MT);
        MTrend.setLineWrap(true);
        MTrend.setEditable(false);
        this.add(MTrend);

        l_ITrend=new JLabel();
        l_ITrend.setForeground(Color.white);
        l_ITrend.setBackground(new Color(0,0,0,0));
        l_ITrend.setFont(FontFactory.getChinese(Font.PLAIN,14));
        l_ITrend.setText("中期趋势：");
        l_ITrend.setBounds(170,45,80,20);
        this.add(l_ITrend);

        ITrend=new JTextArea();
        ITrend.setBounds(250,45,200,40);
        ITrend.setOpaque(false);
        ITrend.setForeground(Color.red);
        ITrend.setText(IT);
        ITrend.setLineWrap(true);
        ITrend.setEditable(false);
        this.add(ITrend);

        l_LTrend=new JLabel();
        l_LTrend.setForeground(Color.white);
        l_LTrend.setBackground(new Color(0,0,0,0));
        l_LTrend.setFont(FontFactory.getChinese(Font.PLAIN,14));
        l_LTrend.setText("长期趋势：");
        l_LTrend.setBounds(170,85,80,20);
        this.add(l_LTrend);

        LTrend=new JTextArea();
        LTrend.setBounds(250,85,200,60);
        LTrend.setOpaque(false);
        LTrend.setForeground(Color.red);
        LTrend.setText(LT);
        LTrend.setLineWrap(true);
        LTrend.setEditable(false);
        this.add(LTrend);


        AnalString=new JTextArea();
        AnalString.setBounds(170,145,280,105);
        AnalString.setOpaque(false);
        AnalString.setForeground(Color.cyan);
        AnalString.setLineWrap(true);
        AnalString.setText(AnalS);
        AnalString.setEditable(false);
        this.add(AnalString);

    }
}
