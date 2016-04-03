/**
 * 
 */
package com.best.util;

import java.io.Reader;
import java.sql.Clob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.FieldError;


public class BasicUtil {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		java.util.Map<String,String> temp = new  java.util.HashMap<String,String>();
//		temp.put("aa", "asdf");
//		java.util.List<String> temp = new  java.util.ArrayList< String>();
//		temp.add("a");
		//System.out.println( "isEmpty:"+ isEmpty(temp) );
		System.out.println( isNumber("500.1a") );
	}
	
	public static String generateId(){
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	
	public static boolean checkChannel(String channel){
		if(isEmpty(channel)){
			return false;
		}
		if("WEB,WX,APP,AN_XIN".contains(channel)){
			return true;
		}
		return false;
	}
	
	public static boolean checkOrderFrom(String orderForm){
		if(isEmpty(orderForm)){
			return false;
		}
		if("XZH_WEB_MALL,XZH_WX_MALL,XZH_APP_MALL,IDEAL_FINANCE".contains(orderForm)){
			return true;
		}
		return false;
	}
	
	public static boolean isIdcard( String idcard ) {
		if (  idcard == null || !(idcard.length()==15||idcard.length()==18) ) {
			return false;
		}
		IdcardValidator validator = new IdcardValidator();
		return validator.isValidatedAllIdcard(idcard);
	}
	
	public static boolean isMail( String mailAddress ) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";   
		if (  mailAddress == null || mailAddress.length() < 5) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mailAddress);
		return m.matches();
	}
	
	public static boolean isMobile(String str){
		Pattern p = Pattern.compile("^((13[0-9])|(14[57])|(15[^4,\\D])|(17[05678])|(18[0-9]))\\d{8}$");  
		Matcher m = p.matcher(str);
		return m.matches();  
	}
	
	public static boolean isNumber(String str){   
		return java.util.regex.Pattern.matches("^(-)?\\d+(\\.\\d+)?$", str.trim());   
	}

	public static boolean isValidDate(String str,String dateFormat) {
		boolean result=true;
	    SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	    try {
	    	//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
	    	format.setLenient(false);
	        format.parse(str);
		} catch (ParseException e) {
			result=false;
		}
	    return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if( null == obj){
			return true;
		}else {
			if( obj instanceof java.lang.String ){
				String temp = (String) obj;
				if( temp.trim().length() == 0 ){
					return true;
				}
				return false;
			}
			else if( obj instanceof java.util.Collection ){
				java.util.Collection temp = (java.util.Collection) obj;
				return temp.isEmpty();
			}
			else if( obj instanceof java.util.Map  ){
				java.util.Map temp = (java.util.Map) obj;
				return temp.isEmpty();
			}
			else if( obj instanceof java.util.Set  ){
				java.util.Set temp = (java.util.Set) obj;
				return temp.isEmpty();
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object obj){
		if(isEmpty(obj)){
			return false;
		}
		return true;
	}
	
	public static String toStr(Object para){
		if(isEmpty(para)){
			return "";
		}
		else {
			return para.toString().trim();
		}
	}
	
	public static java.util.StringTokenizer getPartStr(String str,String separatedStr){
		java.util.StringTokenizer s;
		if(  isEmpty(separatedStr) ){
			if(str.indexOf(";")>0)
				s= new java.util.StringTokenizer(str, ";");
			else {
				s= new java.util.StringTokenizer(str, ",");
			}
		}
		else{
			s= new java.util.StringTokenizer(str, separatedStr);
		}
		return s;
	}
	
	public static String clobToString(Clob clob) {
		if (clob == null)
			return null;
		StringBuffer sb = new StringBuffer(65535);// 64K
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[60000];// 每次获取60K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
		} catch (Exception ex) {
			sb = null;
			ex.printStackTrace();
		} finally {
			try {
				if (clobStream != null) {
					clobStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (sb == null)
			return null;
		else
			return sb.toString();
	}
	
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	public static String generateRandomNum(int length){
		Random random = new Random();
		char[] codeSequence = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
    	StringBuffer str = new StringBuffer();
    	for (int i = 0; i<length; i++) {
    		str.append(String.valueOf(codeSequence[random.nextInt(9)]));
	    }
    	return str.toString();
	}
	
	public static String extractErrors(List<FieldError> errors){
		StringBuffer msg = new StringBuffer();
		for(FieldError error:errors){
			msg.append(error.getDefaultMessage()+",");
		}
		return msg.substring(0, msg.length()-1);
	}
	
	public static String extractErrors(StringBuffer errorMsg){
		if(errorMsg.length()==0){
			return null;
		}
		return errorMsg.substring(0, errorMsg.length()-1);
	}
}
