package cn.funion.common.utils;

import com.github.promeg.pinyinhelper.Pinyin;

public class TinyPinyinUtils {
	
	//汉字转拼音
	
	public static String  coverToPinyin(String hanzi){
		char[] hanziChars = hanzi.toCharArray();
		StringBuffer stringbuf= new StringBuffer();
		for(char c:hanziChars){
			stringbuf.append(Pinyin.toPinyin(c));
		}
		return stringbuf.toString();
		
	}
	
	public static void main(String[] args) {
		System.err.println(TinyPinyinUtils.coverToPinyin("dd鴎鵉钂"));
	}
}
