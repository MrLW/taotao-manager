package com.taotao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.hat.internal.server.HttpReader;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService ;
	/**
	 *  根据查询对应商品
	 * @param itemId 商品Id
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItem(@PathVariable Long itemId){
		TbItem item = itemService.getItemById(itemId);
		return item ;
	}
	
	/**
	 * 查询商品列表
	 * @param page
	 * @param rows
	 * @return lw 
	 */
	@RequestMapping("/item/list")
	@ResponseBody //返回json 注意：easyui这里的page和rows变量名必须是这个！
	public EasyUIDataGridResult getItemList(Integer page, Integer rows,HttpServletRequest request ,HttpServletResponse response) { // page:默认为第一页 
		//调用service查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		System.out.println("result:" + result.gestTotal() );
		// 测试前台不能分页的原因,排除@ResponseBody的结果
		 String object2Json = JsonUtils.object2Json(result);
		 // 1、手动测试,json一定要用双引号
		//  String json = "{ \"rows\": [ { \"cid\": 560,  \"created\": 1425821324000, \"id\": 816448, \"image\": \"http://image.taotao.com/jd/5a45e88aeca046ec88d7b7ffbc47092a.jpg\", \"num\": 99999, \"price\": 169900, \"status\": 1, \"title\": \"三星 Note II (N7100) 钻石粉 联通3G手机\", \"updated\": 1425821324000 } ],  \"total\": 15}";
		 
		 // 2、使用fastjson解析json,也是没有解析出total
		 // Object json = JSONObject.toJSON(result);
		 // 3、手动添加total字段
		 String json = JSONObject.toJSONString(result);
		 json = json.substring(1, json.length()-1);
		 String newJson = "{\"total\" : "+result.gestTotal() + "," + json +"}" ;
		 
		 System.out.println("哈哈：" + newJson );
		 
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(newJson); // 不能同时调用print和@ResponseBody
			
			System.out.println("json->>>" + object2Json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
/*		修改easyui-lang-zh的源码,不能解决
 * request.getSession().setAttribute("pages", 10);
		request.getSession().setAttribute("total", 1000000);
*/		//返回结果
		return null;
		
	}
	/**
	 *  商品添加
	 * @param item 商品条目
	 * @param desc 描述
	 * @param itemParams 规格参数
	 * @return TaotaoResult 
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc,String itemParams){
		System.out.println("item:" + item + ";desc:" + desc );
		TaotaoResult result = itemService.createItem(item, desc,itemParams);
		return result ;
	}
	
	/**
	 *  展示商品规格参数
	 * @param itemId 商品ID
	 * @param model ModelAndView
	 * @return html
	 */
	@RequestMapping("/page/item/{itemId}")
	public String showItemParam(@PathVariable Long itemId,Model model){
		String html = itemService.getItemParamHtml(itemId) ;
		model.addAttribute("myhtml", html);
		System.out.println("html:" + html);
		return "itemparam" ;
	}
	
	
}
