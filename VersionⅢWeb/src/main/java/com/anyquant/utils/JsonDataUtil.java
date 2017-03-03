package com.anyquant.utils;


import com.anyquant.config.State;
import com.anyquant.config.StringMessage;
import net.sf.json.JSONObject;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JiayiWu on 2016/2/28.
 */
public class JsonDataUtil {

	static JsonDataUtil single = null;

	private JsonDataUtil() {

	}

	public static JsonDataUtil instance() {
		if (null == single)
			single = new JsonDataUtil();
		return single;
	}






	
	

	
	






	/**
	 * @Title: getNowTimeStockResult
	 * @Description: 获取实时数据
	 * @param codeNum
	 *            获取制定股票的实时数据
	 * @return json格式数据
	 * @throws
	 */
	public StringMessage getNowTimeStockResult(String codeNum){
		String APPKEY ="1b9fd1c962226d4da30a181c6dc14dba";
		String url = "http://web.juhe.cn:8080/finance/stock/hs";
		List<UrlValueBean> require = new ArrayList<UrlValueBean>();
		require.add(new UrlValueBean("gid",codeNum));
		require.add(new UrlValueBean("key",APPKEY));
		String result = getHttpResponse(url, require.iterator(),false);
		JSONObject object = JSONObject.fromObject(result);
		if(object.getInt("error_code")==0){
			return new StringMessage(State.OK,object.get("result").toString());
		}else{
			return new StringMessage(State.ERROR,object.get("error_code")+":"+object.get("reason"));
		}

	}


	/**
	 * @Title: getNowTimeMarketResult
	 * @Description: 获取实时数据
	 * @param
	 * @return json格式数据(SH)
	 * @throws
	 */
	public  StringMessage getNowTimeMarketResult(){
		String APPKEY ="1b9fd1c962226d4da30a181c6dc14dba";
		String url = "http://web.juhe.cn:8080/finance/stock/hs";
		List<UrlValueBean> require = new ArrayList<UrlValueBean>();
		require.add(new UrlValueBean("type","0"));
		require.add(new UrlValueBean("key",APPKEY));
		String result = getHttpResponse(url, require.iterator(),false);
		JSONObject object = JSONObject.fromObject(result);
		if(object.getInt("error_code")==0){
			return new StringMessage(State.OK,object.get("result").toString());
		}else{
			return new StringMessage(State.ERROR,object.get("error_code")+":"+object.get("reason"));
		}

	}
	/**
	 * @Title: getHttpResponse
	 * @Description: 通用性的数据请求
	 * @param allConfigUrl
	 *            获取数据的url iter 存储筛选条件
	 * @return json格式数据
	 * @throws
	 */

	private static String getHttpResponse(String allConfigUrl, Iterator iter,boolean isUseXAuthCode) {
		BufferedReader in = null;
		StringBuffer result = null;
		try {
			if (null != iter) {
				allConfigUrl+="?";
				while (iter.hasNext()) {
					UrlValueBean value = (UrlValueBean) iter.next();
					allConfigUrl+=value.getKey()+"="+value.getValue()+"&";
				}
				allConfigUrl = allConfigUrl.substring(0, allConfigUrl.length()-1) ;
			}


			URI uri = new URI(allConfigUrl);
			URL url = uri.toURL();
			URLConnection connection = url.openConnection();
			if(isUseXAuthCode) {
				connection.setRequestProperty("X-Auth-Code",
						"975d83e2bfde1056648695bd1745ff08");
			}
			connection.connect();



			result = new StringBuffer();
			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}

			return result.toString();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"网络连接超时,请检查网络环境或网站服务器是否正常启动", "系统信息", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,
						"网络连接超时,请检查网络环境或网站服务器是否正常启动", "系统信息", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
				e2.printStackTrace();
			}
		}

		return null;

	}

}
