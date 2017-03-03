package businesslogic;

import vo.RecommendVO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;

/**
 * Created by Jiayiwu on 16/5/4.
 */
public class RecommendPythonimpl implements businesslogicservice.RecommendPython {

    public RecommendVO getRecommendVoByPython(String stockName) {

        String path ="mydata/analyse/";
        if(stockName.contains("sh")){
            stockName=stockName.replace("sh","");
        }
        path=path+stockName+".data";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            RecommendVO vo = new RecommendVO();
            Class<RecommendVO> recommendVOClass = (Class<RecommendVO>) vo.getClass();
            String result="";
            while((result=br.readLine())!=null){

                String [] param = result.split("\\|");

                if(!isOK(param))
                    continue;
                Method [] methods = recommendVOClass.getMethods();
                for(int i = 0;i<methods.length;i++){
                    if(methods[i].getName().contains(param[0])&&methods[i].getName().contains("set")){
                        methods[i].invoke(vo,new Object[]{param});
                    }
                }
            }

            return vo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e ){
            e.printStackTrace();
        }

        return null;
    }

    private boolean isOK(String [] param){
        boolean isMatch=true;
        for(int i = 0;i<param.length;i++){
            if(param[i].equals("暂无数据")) {
                isMatch = false;
                break;
            }
        }
        if(param.length==1)
        {
            isMatch=false;
        }
        return isMatch;
    }
}
