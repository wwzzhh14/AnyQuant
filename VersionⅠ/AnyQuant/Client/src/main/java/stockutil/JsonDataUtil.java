package stockutil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import config.ExchangePlace;
import config.State;
import config.StringMessage;
import main.Main;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	  * @Title: getAllStocks
	  * @Description: 获取所有的股票信息
	  * @param year 传入年份，如2014
	  * @param place enum类型，只能选择ExchangePlace.sh,ExchangePlace.sz
	  * @return
	  * StringMessage
	  * @throws
	  */
	public  StringMessage getAllStocks(int year, ExchangePlace place) {

		String url = "http://121.41.106.89:8010/api/stocks";

		List<UrlValueBean> require = new ArrayList<UrlValueBean>();
		require.add(new UrlValueBean("year", year+""));
		require.add(new UrlValueBean("exchange", place.toString()));

		String result = getHttpResponse(url, require.iterator());

		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);

	}

	/**
	  * @Title: getStock
	  * @Description: 获取某一具体股票信息
	  * @param start 起始日期  格式2015-11-11
	  * @param end   截止日期 格式2015-11-12
	  * @param stock_code 股票代码，只接收上证或者深证
	  * @param field 筛选条件
	  *              	open: 开盘价
	  *					high: 最高价
	  *					low: 最低价
	  *					close: 收盘价
	  *					adj_price: 后复权价
	  *					volume: 成交量
	  *					turnover: 换手率
	  *					pe: 市盈率
	  *					pb: 市净率
	  *              格式示范 open+high+low 若传入参数为null，默认采取条件open+high+low
	  * @return
	  * StringMessage
	  * @throws
	  */
	@SuppressWarnings("unused")
	public  StringMessage getStock(String start, String end,
			String stock_code,String field) {

		String url = "http://121.41.106.89:8010/api/stock/";

		// ^(?i)s[hz]\\d{6}$为上证和深证股票代码的正则表达式
		if (!stock_code.matches("^(?i)s[hz]\\d{6}$"))
			return new StringMessage(State.ERROR, "股票代码错误");

		url += stock_code+"/";
		String startDate, endDate;

			
		if((!start.matches("^\\d{4}-(\\d{2})-(\\d{2})$"))
				||(!end.matches("^\\d{4}-(\\d{2})-(\\d{2})$")))
			return new StringMessage(State.ERROR, "日期错误");


		List<UrlValueBean> require = new ArrayList<UrlValueBean>();
		require.add(new UrlValueBean("start", start));
		require.add(new UrlValueBean("end", end));
		if(null == field)
		   require.add(new UrlValueBean("fields", "open+high+close"));
		else
		   require.add(new UrlValueBean("fields", field));
		String result = getHttpResponse(url, require.iterator());

		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);

	}

	/**
	  * @Title: getBenchmark
	  * @Description: 获取大盘信息
	  * @param field 筛选条件
	  *              	open: 开盘价
	  *					high: 最高价
	  *					low: 最低价
	  *					close: 收盘价
	  *					adj_price: 后复权价
	  *					volume: 成交量
	  *					turnover: 换手率
	  *					pe: 市盈率
	  *					pb: 市净率
	  *              格式示范 open+high+low 若传入参数为null，默认采取条件open+close
	  * @return
	  * StringMessage
	  * @throws
	  */
	public  StringMessage getBenchmark(String field) {

		String url = "http://121.41.106.89:8010/api/benchmark/all";
		
		List<UrlValueBean> require = new ArrayList<UrlValueBean>();
		if(null == field)
			   require.add(new UrlValueBean("fields", "open+close"));
			else
			   require.add(new UrlValueBean("fields", field));
		
		String result = getHttpResponse(url, require.iterator());

		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);

	}
	
	
	/**
	  * @Title: getBench
	  * @Description: 获取指定大盘信息
	  * @param field 筛选条件
	  *              	open: 开盘价
	  *					high: 最高价
	  *					low: 最低价
	  *					close: 收盘价
	  *					adj_price: 后复权价
	  *					volume: 成交量
	  *					turnover: 换手率
	  *					pe: 市盈率
	  *					pb: 市净率
	  *              格式示范 open+high+low 若传入参数为null，默认采取条件open+close
	  * @return
	  * StringMessage
	  * @throws
	  */
	public  StringMessage getBench(String start, String end,
			String bench_code,String field) {

		String url = "http://121.41.106.89:8010/api/benchmark/";
		
		url+=bench_code;
		
		
		if((!start.matches("^\\d{4}-(\\d{2})-(\\d{2})$"))
				||(!end.matches("^\\d{4}-(\\d{2})-(\\d{2})$")))
			return new StringMessage(State.ERROR, "日期错误");
		
		List<UrlValueBean> require = new ArrayList<UrlValueBean>();
		if(null == field)
			   require.add(new UrlValueBean("fields", "open+close"));
			else
			   require.add(new UrlValueBean("fields", field));
		
		require.add(new UrlValueBean("start", start));
		require.add(new UrlValueBean("end", end));
		String result = getHttpResponse(url, require.iterator());

		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);

	}
	
	
	/**
	  * @Title: getFields
	  * @Description: 获取可采取的股票类型筛选条件
	  * @return
	  * StringMessage
	  * @throws
	  */
	private StringMessage getFields(){
		
		String url = "http://121.41.106.89:8010/api/stock/fields";
		String result = getHttpResponse(url, null);
		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);
	}

	public String analysisFields(){
		String jsonField=null;
		StringBuffer stringBuffer=new StringBuffer();
		StringMessage stringMessage=getFields();
		if(stringMessage.getResult()==State.OK){
			jsonField=stringMessage.getData();
			JSONArray jsonArray= JSONObject.fromObject(jsonField).getJSONArray("data");
			for(int i=0;i<jsonArray.size();i++){
				stringBuffer.append(jsonArray.getString(i)+"+");
			}
			String result=stringBuffer.substring(0,stringBuffer.length()-1);
			return result;

		}
		return null;
	}
	/**
	  * @Title: getFields
	  * @Description: 获取可采取的股票类型筛选条件
	  * @return
	  * StringMessage
	  * @throws
	  */
	public  StringMessage getAvailibleBenchmarks(){

		String url = "http://121.41.106.89:8010/api/benchmark/all";
		String result = getHttpResponse(url, null);
		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);
	}
	/**
	  * @Title: getConnectResult
	  * @Description: 获取网络连接状态
	  * @return
	  * StringMessage
	  * @throws
	  */
	public  StringMessage getConnectResult(){

		String url = "http://121.41.106.89:8010/";
		String result = getHttpResponse(url, null);
		if(null == result)
			return new StringMessage(State.ERROR,"服务器连接错误");
		return new StringMessage(State.OK, result);
	}

	/**
	 * @Title: getHttpResponse
	 * @Description: 通用性的数据请求
	 * @param allConfigUrl
	 *            获取数据的url iter 存储筛选条件
	 * @return json格式数据
	 * @throws
	 */

	private static String getHttpResponse(String allConfigUrl, Iterator iter) {
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
			connection.setRequestProperty("X-Auth-Code",
					"975d83e2bfde1056648695bd1745ff08");
			
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
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return null;

	}

}
