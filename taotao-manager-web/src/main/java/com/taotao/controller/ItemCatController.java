package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.EasyUIITreeNode;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemService;
/**
 *  商品分类
 * @author lw
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService ;
	/**
	 *  注意：这里如果写@RequestParam，则会有个默认的require=true,必须要有个id
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUIITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUIITreeNode> itemCatList = itemCatService.getItemCatList(parentId);
		System.out.println("itemCatList:" + itemCatList);
		return itemCatList ;
	}
	

}
