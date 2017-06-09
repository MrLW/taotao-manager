package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper; 
	
	@Override
	public List<EasyUIITreeNode> getItemCatList(Long parentId) {
		
		TbItemCatExample example = new TbItemCatExample() ;
		//设置查询标准
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		//转换
		List<EasyUIITreeNode> resultList = new ArrayList<>() ;
		//遍历设置
		for (TbItemCat tbItemCat : list) {
			EasyUIITreeNode node = new EasyUIITreeNode() ;
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent() ? "closed":"open");
			resultList.add(node) ;
		}
		return resultList ;
	}

}
