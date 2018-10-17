package com.web.form;

import java.util.HashMap;
import java.util.Map;

import com.web.global.EnumReturnCode.ReturnCode;

/**
 *  结果集对象，返回给客户端的结果集
 *
 *
 */
public class ResultSetForm implements java.io.Serializable{

	private static final long serialVersionUID = 8617625226493150460L;
	private Boolean isSuccess;
	private int returnCode; //default =200;info=300~399;warn=400~499;error=500~599;
	private String message;
	private String exception;
	private Map<String, Object> params;
	 
	
	public ResultSetForm(){
		params=new HashMap<String,Object>();
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void addParam(String key,Object value){
		this.getParams().put(key, value);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	//New:
	/**
	 * 设置返回结果
	 * @param isSuccess
	 * @param returnCode
	 * @param message
	 */
	public void setResult(Boolean isSuccess,ReturnCode returnCode,String message){
		this.setIsSuccess(isSuccess);
		this.setReturnCode(returnCode.getValue());
		this.setMessage(message);
	}
	
	/**
	 * 设置返回结果
	 * @param isSuccess
	 * @param returnCode
	 * @param message
	 */
	public void setResult(Boolean isSuccess,ReturnCode returnCode,String message,Map<String, Object> params){
		this.setIsSuccess(isSuccess);
		this.setReturnCode(returnCode.getValue());
		this.setMessage(message);
		this.setParams(params);
	}
	
	/**
	 * 设置返回结果
	 * @param isSuccess
	 * @param returnCode
	 * @param message
	 */
	public void setResult(Boolean isSuccess,ReturnCode returnCode,String message,Map<String, Object> params,String exception){
		this.setIsSuccess(isSuccess);
		this.setReturnCode(returnCode.getValue());
		this.setMessage(message);
		this.setParams(params);
		this.setException(exception);
	}
}
