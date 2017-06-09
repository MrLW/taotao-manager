package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService ;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParam(int page, int rows){
		System.out.println("page:" + page + ";rows:" + rows);
		EasyUIDataGridResult result = itemParamService.getItemParam(page, rows);
		System.out.println("规格参数:" + result);
		return result ;
	}
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatById(@PathVariable Long cid){
		TaotaoResult result = itemParamService.getItemCatById(cid);
		System.out.println("result:" + result);
		//如果返回null的话,前台将不会回反应
		return result ;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid , String paramData){
		TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
		return result ;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteItemParam(int[] ids){
		for (int i : ids) {
			System.out.println("id:" + i);
		}
		TaotaoResult result = itemParamService.deleteItemParamByIds(ids) ;
		return result ;
	}
	
	
}
