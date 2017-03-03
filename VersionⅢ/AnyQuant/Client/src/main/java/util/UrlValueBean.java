package util;

/**
 * Created by JiayiWu on 2016/2/28.
 */
public class UrlValueBean {

	String value;
	String key;
	
	public UrlValueBean(String key,String value) {
		this.value = value;
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	
}
