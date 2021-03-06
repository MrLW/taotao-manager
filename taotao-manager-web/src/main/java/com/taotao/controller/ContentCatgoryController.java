package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCatgoryController {

	@Autowired
	private ContentCatgoryService contentCatgoryService ;
	
	// 显示列表
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUIITreeNode> getContentCatList( @RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUIITreeNode> list = contentCatgoryService.getContentCatList(parentId);
		
		return list; 
	}
	// 插入节点
	@RequestMapping("create")
	@ResponseBody
	public TaotaoResult insertContentNode(Long parentId,String name){
		System.out.println("parentID:" + parentId+";name:" + name );
		TaotaoResult taotaoResult = contentCatgoryService.insertContentNode(parentId, name);
		return taotaoResult ;
	}
	// 重命名节点
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentNode(Long id,String name){
		System.out.println("update== id:" + id + ";name:" + name);
		TaotaoResult taotaoResult = contentCatgoryService.updateContentNode(id, name);
		return taotaoResult ;
	}
	
	// 删除节点
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentNode(Long id){
		System.out.println("要删除的id:"+ id );
		TaotaoResult taotaoResult = contentCatgoryService.deleteContentNode(id);
		return taotaoResult ;
	}
}
