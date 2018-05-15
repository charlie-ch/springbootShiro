package com.xm.shiro.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController<V, E, K> {

	
	@RequestMapping("getAll")
	public Object getAll(String rNumber){
		try {
			String url="http://localhost:8080/sqm/shareReport/shareReportFeedback";
			// String ip =
			 //String url = WebservicePropertiesUtil.getProperty("url.crm.shareReport.getReportDetail");

			//String url = "http://localhost:7070/product/getAll";
			// 创建一个httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 创建一个uri对象
			URIBuilder uriBuilder = new URIBuilder(url);
//			uriBuilder.addParameter("number", "123");
//			uriBuilder.addParameter("dealType", "你好");
//			uriBuilder.addParameter("feedback", "bb");
			
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("number", "123");
			map2.put("dealType", "你好");
			map2.put("feedback", "bb");
			
			HttpPost get = new HttpPost(uriBuilder.build());
			//装填请求参数
	        List<NameValuePair> list = new ArrayList<NameValuePair>();
	        for (Map.Entry<String, String> entry : map2.entrySet()) {
	                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	        }
	        //设置参数到请求对象中
	        get.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
			
			
			// 执行请求
			CloseableHttpResponse response = httpClient.execute(get);
			// 取响应的结果
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode!=200){
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("isBizSuccess", false);
				map.put("errMsg", "请求失败，系统间网络异常，请联系管理员");
				return map;
			}
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			System.out.println(string);
			// 关闭httpclient
			httpClient.close();
			
			return string;

		} catch (Exception e) {
			e.printStackTrace();
			return "请求失败，请联系管理员";
		}

	/*	
		HashMap<String, Object> res = new HashMap<String, Object>();
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("rNumber", "1010");
	map.put("rTitle", "我是标题");
	map.put("ShareReportContent", "我是报告内容");
	
	res.put("isBizSuccess"	, true);
	res.put("data", map);
	
		return res;*/
	}
	@RequestMapping("getLog")
	public Object getlog(String rNumber){
		System.out.println(rNumber);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("DEALER", "1010");
		map.put("DEALDEPARTMENT", "处理部门");
		list.add(map);
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("DEALER", "10102");
		map2.put("DEALDEPARTMENT", "处理部门2");
		list.add(map2);
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("status", "200");
		res.put("data", list);
		res.put("isBizSuccess", true);
		return res;
	}
}
