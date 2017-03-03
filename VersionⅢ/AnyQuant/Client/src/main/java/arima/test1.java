package arima;

import businesslogic.predictionlogic.ArimaAlgorithmImp;
import businesslogicservice.predictionlogicservice.ArimaPredictionBLService;
import util.TimeUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class test1 {

    public static void main(String args[]) {
//        Scanner ino = null;
//        try {
//            ArrayList<Double> arraylist = new ArrayList<Double>();
//            ino = new Scanner(new File("C:\\Users\\elva\\workspace\\Arima\\Data\\ceshidata.txt"));
//            while (ino.hasNext()) {
//                arraylist.add(Double.parseDouble(ino.next()));
//            }
//            double[] dataArray = new double[arraylist.size() - 1];
//            for (int i = 0; i < arraylist.size() - 1; i++)
//                dataArray[i] = arraylist.get(i);
//
//            //System.out.println(arraylist.size());
//
//            ARIMA arima = new ARIMA(dataArray);
//
//            int[] model = arima.getARIMAmodel();
//            System.out.println("Best model is [p,q]=" + "[" + model[0] + " " + model[1] + "]");
//            System.out.println("Predict value=" + arima.aftDeal(arima.predictValue(model[0], model[1])));
//            System.out.println("Predict error=" + (arima.aftDeal(arima.predictValue(model[0], model[1])) - arraylist.get(arraylist.size() - 1)) / arraylist.get(arraylist.size() - 1) * 100 + "%");
//
//            //	String[] str = (String[])list1.toArray(new String[0]);
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            ino.close();
//        }
        ArimaPredictionBLService aps = new ArimaAlgorithmImp();
        try {
            for (int i = 600;i<601;i++){
//                System.out.print("兴业银行:");
                aps.test("sh601166",i);
            }


//            System.out.print("招商银行:");
//            aps.test("sh600036",19);
//
//            System.out.print("华夏银行:");
//            aps.test("sh600015",19);
//
//            System.out.print("民生银行:");
//            aps.test("sh600016",19);
//
//            System.out.print("建设银行:");
//            aps.test("sh601939",19);
//
//            System.out.print("光大银行:");
//            aps.test("sh601818",19);
//
//            System.out.print("南京银行:");
//            aps.test("sh601009",19);
//
//            System.out.print("交通银行:");
//            aps.test("sh601328",19);
//
//            System.out.print("中国银行:");
//            aps.test("sh601988",19);
//
//            System.out.print("中信银行:");
//            aps.test("sh601998",19);
//
//            System.out.print("北京银行:");
//            aps.test("sh601169",19);
//
//            System.out.print("浦发银行:");
//            aps.test("sh600000",19);
//
//            System.out.print("工商银行:");
//            aps.test("sh601398",19);
//
//            System.out.print("农业银行:");
//            aps.test("sh601288",19);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}