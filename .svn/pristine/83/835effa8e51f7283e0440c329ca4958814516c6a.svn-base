package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper; 
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content) ;
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> queryContentById(Long categoryId) {
		
		TbContentExample example = new TbContentExample();
		
		Criteria criteria = example.createCriteria();
		//设置查询条件id
		criteria.andCategoryIdEqualTo(categoryId);
		
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		
		return list;
	}
	
	@Override
	public TaotaoResult deleteContentByIds(Long[] ids) {
		if(ids != null && ids.length >0 ){
			for (Long id : ids) {
				contentMapper.deleteByPrimaryKey(id);
			}
		}
		return TaotaoResult.ok();
	}

}
