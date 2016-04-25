package com.searchinner.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.searchinner.model.Comment;
import com.searchinner.model.MessageInfo;

public class Util {
	/**
	 * 启动tomcat，访问
	 * http://localhost:8080/solr/dataimport?command=full-import   将数据全部导入solr服务器进行索引

‍		http://localhost:8080/solr/dataimport?command=delta-import     增量导入数据

		http://localhost:8080/solr/dataimport?command=status可以查看运行状态
		<delete><query>*:*</query></delete>
		<commit/>
		last_modifiedtime:*
		text_search:*
		刪除
	*/
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public static String getQueryTypeByNumber(int number) {
		if (number == 0) {
			return "text_search";
		} else if (number == 1) {
			return "articleTitle";
		} else if (number == 2) {
			return "articleContent";
		} else if (number == 3) {
			return "articleAuthor";
		}
		return "text";
	}

	public static String dateToString(Date date) {
		String str = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// 2007年1月18日 星期四
		format = DateFormat.getDateInstance(DateFormat.FULL);
		str = format.format(date);
		return str;
	}

	/**
	 * 获得uuid
	 * @return 去掉短横线的uuid
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉"-"符号
		return uuid.replace("-", "");
	}
	public static void main(String[] args) {
	System.out.println(	Util.encrypt_MD5("houxp"+"111111"));
	}
	
	public static String encrypt_MD5(String str) {
		StringBuffer buf = new StringBuffer();
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	/**
	 * 转义字符
	 * @param s
	 * @return
	 */
	public static String escapeQueryChars(String s) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < s.length(); i++) {
	      char c = s.charAt(i);
	      // These characters are part of the query syntax and must be escaped
	      if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
	        || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
	        || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
	        /*|| Character.isWhitespace(c)*/) {
	        sb.append('\\');
	      }
	      sb.append(c);
	    }
	    return sb.toString();
	  }
	
	public static MessageInfo commentToMessage(Comment comment){
		MessageInfo info = new MessageInfo();
		info.setArticleId(comment.getArticleId());
		info.setCommentcontext(comment.getCommentcontext());
		info.setCommentedUserId(comment.getCommentedUserId());
		info.setCommentedUserName(comment.getCommentedUserName());
		info.setCommentUserId(comment.getCommentUserId());
		info.setCommentUserName(comment.getCommentUserName());
		return info;
	}
}
