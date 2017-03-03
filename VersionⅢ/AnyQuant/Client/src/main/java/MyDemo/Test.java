package MyDemo;

/**
 * Created by Jiayiwu on 16/8/2.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Test {
    public static void main(String args[]){
        String stockName="sh601998";
        int index = Integer.parseInt(stockName.substring(2))%25;
        System.out.println(stockName.hashCode());
    }
}
