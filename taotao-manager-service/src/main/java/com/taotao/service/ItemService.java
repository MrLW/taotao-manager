package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	TaotaoResult createItem(TbItem item , String desc,String itemParams);
	String getItemParamHtml(Long itemId) ; 
}
