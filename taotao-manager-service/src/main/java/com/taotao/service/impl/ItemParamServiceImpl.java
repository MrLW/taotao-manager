package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper itemParamMapper; 
	
	
	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		//根据cid查询规格参数模板
		TbItemParamExample example = new TbItemParamExample() ;
		
		Criteria criteria = example.createCriteria();
		
		criteria.andItemCatIdEqualTo(cid);
		
		//执行查询
		
		return null;
	}
	/**
	 *  查询对应cid这条记录中数据库中是否存在
	 * @param cid 
	 * @return
	 */
	public TaotaoResult getItemCatById(Long cid){
		
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		//真正查询,如果使用逆向工程查询的话,text类型必须使用withBolbs
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example );
		if(list != null && list.size() > 0 ){
			TbItemParam itemParam = list.get(0);
			return TaotaoResult.ok(itemParam) ;
		}
		return TaotaoResult.ok() ;
	}
	
	
	/**
	 *  查询所有规格参数
	 */
	@Override
	public EasyUIDataGridResult getItemParam(int page, int rows) {
		
		TbItemParamExample example = new TbItemParamExample();
		//设置分页
		PageHelper.startPage(page, rows);
		//一定需要注意的是:如果是调用selectByExample()则不会查询到大文本数据
		// 可以查看源码发现mybatis自动生成的sql中,把大文本的字段单独抽取出来,
		//如果是用selectByExample查询,则不会又这个字段!!!
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		
		System.out.println("从数据库中查询的数据为:");
		for (TbItemParam tbItemParam : list) {
			System.out.println(tbItemParam);
		}
		//获取分页的结果
		PageInfo pageInfo = new PageInfo(list); 
		//创建EasyUIDataGridResult对象
		EasyUIDataGridResult result = new EasyUIDataGridResult() ;
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}
	/**
	 *  添加商品规格
	 */
	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		//创建pojo
		TbItemParam itemParam = new TbItemParam() ;
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setUpdated(new Date());
		itemParam.setCreated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult deleteItemParamByIds(int[] ids) {
		if(ids != null && ids.length >0){
			for (int id : ids) {
				itemParamMapper.deleteByPrimaryKey((long) id);
			}
			return TaotaoResult.ok();
		}
		return null;
	}
	

}
