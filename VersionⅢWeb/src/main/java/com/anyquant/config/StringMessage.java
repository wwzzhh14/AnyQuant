package com.anyquant.config;
/**
 * Created by JiayiWu on 2016/2/28.
 */
public class StringMessage {

	State result;
	String data;
	
	public StringMessage(State result, String data){
		this.result =	result;
		this.data =	data;
	}

	public State getResult() {
		return result;
	}

	public void setResult(State result) {
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
