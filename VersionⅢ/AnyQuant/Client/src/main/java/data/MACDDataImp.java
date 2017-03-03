package data;
import dataservice.MACDDataService;
import net.StockNetImpl;
import netservice.StockNetService;
import po.StockInfoPO;
import po.macdPO;
import util.TimeUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2016/3/28.
 */
public class MACDDataImp implements MACDDataService{

    StockNetService stockNetService=new StockNetImpl();
    private StringBuffer s1=new StringBuffer();
    private StringBuffer s2=new StringBuffer();
    private StringBuffer s3=new StringBuffer();

    private BufferedReader getReader(String path) {
        File file = new File(path);
        BufferedReader br = null;
        try {
            FileInputStream fs = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fs));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

    private BufferedWriter getWriter(String path,boolean isCovered){
        File f=new File(path);
        BufferedWriter bw=null;
        try {
            FileWriter fw= new FileWriter(f,!isCovered);
            bw  = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bw;
    }
    private boolean hasUpdated(){
        BufferedReader bf=getReader("myData/log.txt");
        String date=TimeUtil.getNowDate();
        try {
            String logDate=bf.readLine();
            if(logDate.equals(date)){
                return true;
            }else {
                BufferedWriter bw=getWriter("myData/log.txt",true);
                bw.write(date);
                bw.flush();
                bw.close();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<macdPO> getData(String startTime, String endTime, String codeName, String type){
        long time1= TimeUtil.getTimeLong(startTime);
        long time2= TimeUtil.getTimeLong(endTime);
        long time3=0;
        ArrayList<macdPO> resultList=new ArrayList<macdPO>();
        StringBuffer path=new StringBuffer("mydata/");
        path.append(type).append("/").append(codeName).append(".dat");
        BufferedReader br=getReader(path.toString());
        String s="";
        if(br!=null){
            try {
                while ((s=br.readLine())!=null){
                    String[] array=s.split("/");
                    time3=TimeUtil.getTimeLong(array[0]);
                    if(time3!=0&&time3>=time1&&time3<=time2){
                        resultList.add(new macdPO(array[0],Double.parseDouble(array[1])));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br=null;
            }
        }
        return resultList;
    }

    public void update(){
        if(!hasUpdated()){
            BufferedReader br1=getReader("mydata/EMA12/ema12.txt");
            BufferedReader br2=getReader("mydata/EMA26/ema26.txt");
            StringBuffer resultBuffer1=new StringBuffer();
            StringBuffer resultBuffer2=new StringBuffer();
            String s1="";
            String s2="";
            double ema12=0;
            double ema26=0;
            try {
                while ((!((s1=br1.readLine()).equals("end")))&&(!((s2=br2.readLine()).equals("end")))){
                    String array1[]=s1.split("/");
                    String array2[]=s2.split("/");
                    ema12=Double.parseDouble(array1[2]);
                    ema26=Double.parseDouble(array2[2]);
                    ArrayList<StockInfoPO> poList=stockNetService.getStockInfoByTime(array1[1],TimeUtil.getPassedDate(-1,array1[0]),TimeUtil.getNowDate());
                    for(int i=0;i<poList.size();i++){
                        ema12=poList.get(i).getClose()*2/13+ema12*11/13;
                        ema26=poList.get(i).getClose()*2/27+ema26*25/27;
                        updateData(ema12,ema26,poList.get(i).getDate(),array1[1]);
                    }
                    if(poList.size()>0) {
                        resultBuffer1.append(poList.get(poList.size() - 1).getDate()).append("/").append(array1[1]).append("/").append(ema12 + "").append("\n");
                        resultBuffer2.append(poList.get(poList.size() - 1).getDate()).append("/").append(array2[1]).append("/").append(ema26 + "").append("\n");
                    }
                    saveResult(array1[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultBuffer1.append("end");
            resultBuffer2.append("end");
            BufferedWriter bw1=getWriter("mydata/EMA12/ema12.txt",true);
            BufferedWriter bw2=getWriter("mydata/EMA26/ema26.txt",true);
            try {
                bw1.write(resultBuffer1.toString());
                bw1.flush();
                bw2.write(resultBuffer2.toString());
                bw2.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    bw1.close();
                    bw1=null;
                    bw2.close();
                    bw2=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        s1=null;
        s2=null;
        s3=null;
    }

    private void updateData(double ema12,double ema26,String date,String codeNum){
        String lastDate=TimeUtil.getPassedDate(1,date);
        while (getData(lastDate,TimeUtil.getPassedDate(-1,lastDate),codeNum,"DEA").size()==0){
            lastDate=TimeUtil.getPassedDate(1,lastDate);
        }
        double lastDea=getData(lastDate,TimeUtil.getPassedDate(-1,lastDate),codeNum,"DEA").get(0).getValue();
        double dif=ema12-ema26;
        double dea=(lastDea)*8/10+dif*2/10;
        double macd=(dif-dea*2);

        s1.append(date).append("/").append(dea+"").append("\n");
        s2.append(date).append("/").append(dif+"").append("\n");
        s3.append(date).append("/").append(macd+"").append("\n");
    }
    private void saveResult(String codeNum){
        StringBuffer path1=new StringBuffer("mydata/DEA/");
        path1.append(codeNum).append(".dat");
        StringBuffer path2=new StringBuffer("mydata/DIF/");
        path2.append(codeNum).append(".dat");
        StringBuffer path3=new StringBuffer("mydata/MACD/");
        path3.append(codeNum).append(".dat");
        BufferedWriter bw1=getWriter(path1.toString(),false);
        BufferedWriter bw2=getWriter(path2.toString(),false);
        BufferedWriter bw3=getWriter(path3.toString(),false);

        try {
            bw1.write(s1.toString());
            bw1.flush();
            bw2.write(s2.toString());
            bw2.flush();
            bw3.write(s3.toString());
            bw3.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw1.close();
                bw2.close();
                bw3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        s1.delete(0,s1.length());
        s2.delete(0,s2.length());
        s3.delete(0,s3.length());
    }
}
