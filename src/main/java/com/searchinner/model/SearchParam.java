package com.searchinner.model;

/**
 * 搜索参数
 * @author fujj
 *
 */
public class SearchParam {
	
	private String strParam;
	private int searchType;
	private String[] paramArr;
	
	public String getStrParam() {
		return strParam;
	}	
	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	public String[] getParamArr() {
		return paramArr;
	}
	public void setParamArr(String[] paramArr) {
		this.paramArr = paramArr;
	}
	
	
}
