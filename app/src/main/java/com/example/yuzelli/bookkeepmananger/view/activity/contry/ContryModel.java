package com.example.yuzelli.bookkeepmananger.view.activity.contry;

public class ContryModel {

	private String englishName; // 英文名字
	private String chineseName; // 中文名字
	private String contryCode; // 手机号code +86
	private String sortLetters; // 显示数据拼音的首字母

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String name) {
		this.englishName = name;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getContryCode() {
		return contryCode;
	}

	public void setContryCode(String contryCode) {
		this.contryCode = contryCode;
	}
	
	
}
