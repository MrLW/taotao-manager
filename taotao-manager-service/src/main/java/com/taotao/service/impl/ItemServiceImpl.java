package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public TbItem getItemById(Long itemId) {

		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

		return tbItem;
	}
	
	public List<TbItem> getList() {
		return itemMapper.getAll();
	}
	/**
	 *  自定义分页效果
	 */
	public EasyUIDataGridResult getItemList(int page, int rows) {
		
		EasyUIDataGridResult result = new EasyUIDataGridResult() ;
		
		int start = (page - 1) * rows ;
		
		int limit = rows ;
		// 获取当前页总记录数
		List<TbItem> pagelist = itemMapper.getItemListByFy(start,limit);
		// 总记录数
		Integer count = itemMapper.getCount();
		result.setRows(pagelist);
		result.setTotal(count);
		System.out.println("count:" + count );
		System.out.println("pageList" + pagelist);
		
		return result ;
	}
	

	/*@Override
	public EasyUIDataGridResult getItemList2(int page, int rows) {
		// 分页处理
		PageHelper.startPage(page, rows);
		
		// 执行查询
		TbItemExample example = new TbItemExample();
		// 添加条件
		// Criteria criteria = example.createCriteria();
		// criteria.andIdEqualTo(123l);
		List<TbItem> pagelist = itemMapper.selectByExample(example);
		
		// 取total
		PageInfo pageInfo = new PageInfo(pagelist);
		
		long total = pageInfo.getTotal();
		
		System.out.println("total:" + total);
		System.out.println("pageInfo:" + pageInfo);
		// 创建返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult(total, pageInfo.getList());
		
		System.out.println("pageInfo-list:" + pageInfo.getList());
		System.out.println("list1:" + result.getRows());
		return result;
	}
*/
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) {

		// 生成商品ID
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 1=正常；2=下架；3=删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		System.out.println("service-item:" + item);
		// 插入商品表
		itemMapper.insert(item);

		// 商品描述表
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setUpdated(date);
		tbItemDesc.setCreated(date);
		tbItemDescMapper.insert(tbItemDesc);

		// 创建商品规格的pojo(主键自增长)
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParams);
		tbItemParamItemMapper.insert(itemParamItem);

		return TaotaoResult.ok();
	}

	@Override
	public String getItemParamHtml(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// 根据商品Id查询规格参数
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		// 去除规格参数
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
		// 开始手工生成html
		StringBuffer sb = new StringBuffer();
		sb.append(
				"<table border=\"1\" cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : mapList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
			sb.append("		</tr>\n");
			// 取规格项
			List<Map> mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
				sb.append("			<td>" + map2.get("v") + "</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

}
