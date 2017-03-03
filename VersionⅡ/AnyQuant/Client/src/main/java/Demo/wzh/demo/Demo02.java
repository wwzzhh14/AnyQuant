package Demo.wzh.demo;

import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by HP on 2016/3/14.
 */
public class Demo02 extends JPanel {

    private int MyWidth,MyHeight;
    private final int offset=30;
    private double[] datas1;
    private double[] datas2;
    private Graphics graphics;
    private Color lineColor1;
    private Color lineColor2;
    private String[] dates;


    public Demo02(int x,int y,int width,int height,Color bg,double[] data1,double[] data2,Color line1,Color line2,String[] dates) {
        super();
        this.setBackground(bg);
        this.setBounds(x,y,width,height);
        lineColor1=line1;
        lineColor2=line2;
        MyWidth=this.getWidth()-2*offset;
        MyHeight=this.getHeight()-2*offset;
        this.datas1=data1;
        this.datas2=data2;
        this.dates=dates;
    }
    public void drawLine(double[] datas,Color c,Graphics g,int w){
        double[] temp=sort(datas);
        double max=temp[datas.length-1];
        double min=temp[0];
        int length=datas.length;
        MyPoint startPoint=null;
        MyPoint endPoint=null;
        int i=0;
        for(;i<length-1;i++){
            startPoint=new MyPoint((((double)i)/(double)(length-1))*MyWidth+offset,MyHeight*((max-datas[i])/(max-min))+offset);
//            System.out.println("start_x:"+startPoint.getX()+",start_y:"+startPoint.getY());
            endPoint=new MyPoint((((double)i+1)/(double)(length-1))*MyWidth+offset,MyHeight*((max-datas[i+1])/(max-min))+offset);
//            System.out.println("end_x:"+endPoint.getX()+",end_y:"+endPoint.getY());
            g.setColor(c);
            g.drawLine((int)startPoint.getX(),(int)startPoint.getY(),(int)endPoint.getX(),(int)endPoint.getY());
            g.setColor(Color.white);
            g.drawString(dates[i],(int)((((double)i)/(double)(length-1))*MyWidth),MyHeight+offset+15);
            g.drawString(datas[i]+"",offset+w,(int)(MyHeight*((max-datas[i])/(max-min))+offset));
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine((int)startPoint.getX(),(int)startPoint.getY(),offset+w,(int)startPoint.getY());
            g.drawLine((int)startPoint.getX(),(int)startPoint.getY(),(int)startPoint.getX(),MyHeight+offset);
        }
        g.drawString(dates[i],(int)((((double)i)/(double)(length-1))*MyWidth),MyHeight+offset+15);
        g.drawString(datas[i]+"",offset+w,(int)(MyHeight*((max-datas[i])/(max-min))+offset));
        g.drawLine((int)startPoint.getX(),(int)startPoint.getY(),offset+w,(int)startPoint.getY());
        g.drawLine((int)startPoint.getX(),(int)startPoint.getY(),(int)startPoint.getX(),MyHeight+offset);
//        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.white);
        MyPoint point1=new MyPoint(offset,this.getHeight()-offset);
        MyPoint point2=new MyPoint(this.getWidth()-offset,this.getHeight()-offset);
        MyPoint point3=new MyPoint(offset,offset);
        MyPoint point4=new MyPoint(this.getWidth()-offset,offset);
        g.drawLine((int)point1.getX(),(int)point1.getY(),(int)point2.getX(),(int)point2.getY());
        g.drawLine((int)point1.getX(),(int)point1.getY(),(int)point3.getX(),(int)point3.getY());
        g.drawLine((int)point2.getX(),(int)point1.getY(),(int)point4.getX(),(int)point3.getY());
        drawLine(datas1,lineColor1,g,0);
        drawLine(datas2,lineColor2,g,MyWidth);
    }

    private double[] sort(double[] datas){
        double[] temp=new double[datas.length];
        for(int i=0;i<datas.length;i++){
            temp[i]=datas[i];
        }

        for(int i=0;i<temp.length-1;i++){
            for(int j=i+1;j<temp.length;j++){
                if(temp[i]>temp[j]){
                    double temp2=temp[i];
                    temp[i]=temp[j];
                    temp[j]=temp2;
                }
            }
        }
        return temp;
    }
}
