package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCatgoryService;
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	@Autowired
	private TbContentCategoryMapper  tbContentCategoryMapper;
	
	@Override
	public List<EasyUIITreeNode> getContentCatList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(example);
		// 
		List<EasyUIITreeNode> resultList = new ArrayList<>(); 
		for (TbContentCategory tbContentCategory : tbContentCategoryList) {
			//创建一个EasyUITree结点
			EasyUIITreeNode node = new EasyUIITreeNode() ;
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			node.setText(tbContentCategory.getName());
			//添加到列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentNode(Long parentId, String name) {
		TbContentCategory category = new TbContentCategory() ;
		category.setName(name);
		category.setParentId(parentId);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		category.setIsParent(false);
		//'排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
		category.setSortOrder(1);
		// '状态。可选值:1(正常),2(删除)',
		category.setStatus(1);
		tbContentCategoryMapper.insert(category);
		Long id = category.getId();
		//查询父节点
		TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		//判断父节点是不是叶子节点
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			//更新数据库
			tbContentCategoryMapper.updateByPrimaryKeySelective(parentNode);
		}
		//将id包装成TaoTaoResult
		return 	TaotaoResult.ok(id);
	}
	
	@Override
	public TaotaoResult updateContentNode(Long id, String name) {
		//根据Id查询到对应节点
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		System.out.println("service中的name:" + contentCategory.getName());
		//同步数据库
		tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContentNode(Long id) {
		//查询到对应id的TbContentCategory
		TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
		//判断是否是叶子节点
		if(!category.getIsParent()){// 是
			tbContentCategoryMapper.deleteByPrimaryKey(id);
			System.out.println("id:" + id );
		}else{ //不是
//			deleteContentNode(category) ;
		}
		return TaotaoResult.ok();
	}
	
	//递归删除某个父节点下的所有子节点
	public void deleteParentNode(TbContentCategory category){
	}

}
