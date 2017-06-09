package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	 /**
	  *  添加广告内容 
	  * @param content 
	  * @return 
	  */
	 TaotaoResult insertContent(TbContent content) ;
	 /**
	  *  根据分类id查询广告
	  * @param categoryId 分类id
	  * @return 广告集合
	  */
	 List<TbContent> queryContentById(Long categoryId);
	 /**
	  *  根据数组删除广告内容
	  * @param ids
	  * @return
	  */
	 TaotaoResult deleteContentByIds(Long[] ids);
}
