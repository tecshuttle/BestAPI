package com.best.util;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AjaxUtil {

	public static final void sendText(HttpServletResponse response,Object object){
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final void sendJSON2(HttpServletResponse response, Object object){
		response.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();  
		try {
			String json = mapper.writeValueAsString(object);
			response.getWriter().write( json );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final void sendJSON(HttpServletResponse response, Object object){
		response.setContentType("application/json;charset=UTF-8");
		String json = JSON.toJSONString(object,SerializerFeature.WriteMapNullValue);
		try {
			response.getWriter().write( json );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final void sendJSONWithoutNull(HttpServletResponse response, Object object){
		response.setContentType("application/json;charset=UTF-8");
		String out=JSON.toJSONString(object);
		try {
			response.getWriter().write( out );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {			
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final void sendJSONByStatus(HttpServletResponse response, String status){
		HashMap<String, String> _map = new HashMap<String, String>();
		_map.put("status", status);
		sendJSON(response,_map);
	}
	
	public static final void sendJSONByKey(HttpServletResponse response,String key, String value){
		HashMap<String, String> _map = new HashMap<String, String>();
		_map.put(key, value);
		sendJSON(response,_map);
	}
}

