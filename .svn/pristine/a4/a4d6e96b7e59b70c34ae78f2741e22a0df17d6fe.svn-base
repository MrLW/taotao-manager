package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatgoryService {
	/**
	 *  显示内容分类页面的列表
	 * @param parentId 最上层的id,默认为0
	 * @return 节点集合
	 */
	List<EasyUIITreeNode> getContentCatList(Long parentId);
	/**
	 *  添加一个内容分类节点
	 * @param parentId 父Id
	 * @param name 新添加节点的name
	 * @return TaotaoResult
	 */
	TaotaoResult insertContentNode(Long parentId,String name); 
	/**
	 *  重命名内容分类节点
	 * @param id 重命名节点的id
	 * @param name 重命名节点的name
	 * @return TaotaoResult
	 */
	TaotaoResult updateContentNode(Long id,String name);
	/**
	 *  删除节点 
	 * @param id 删除节点的ID
	 * @return TaotaoResult
	 */
	TaotaoResult deleteContentNode(Long id) ;
}
