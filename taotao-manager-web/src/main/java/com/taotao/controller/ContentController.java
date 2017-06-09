package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
public class ContentController {
	// 两种使用@value的注入
	@Value("#{configProperties[REDIS_CONTENT_URL]}")
	private String REDIS_CONTENT_URL ;
	
	@Value("${REDIS_CONTENT_SYNC}")
	private String REDIS_CONTENT_SYNC ;
	
	@Autowired
	private ContentService contentService ;
	
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		System.out.println("content:" + content.getContent());
		TaotaoResult result = contentService.insertContent(content);
		//使用HttpClientUitl调用远程服务：同步索引库
		HttpClientUtil.doGet(REDIS_CONTENT_URL + REDIS_CONTENT_SYNC + content.getCategoryId());
		return result; 
	}
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public List<TbContent> queryContentById(Long categoryId ){
		
		List<TbContent> list = contentService.queryContentById(categoryId);
		for (TbContent tbContent : list) {
			System.out.println("content:" + tbContent.getContent());
		}
		System.out.println("contentList:" + list);
		return list ;
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteById(Long[] ids ){
		TaotaoResult result = contentService.deleteContentByIds(ids);
		return result ;
	}
	
}
