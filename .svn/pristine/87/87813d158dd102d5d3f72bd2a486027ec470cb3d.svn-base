package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
//./redis-trib.rb create --replicas 1 192.168.154.128:7001 192.168.154.128:7002 192.168.154.128:7003 192.168.154.128:7004 192.168.154.128:7005  192.168.154.128:7006
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService ;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile){
		System.out.println("multipartFile:" + uploadFile);
		PictureResult pictureResult = pictureService.uploadPic(uploadFile);
		//一定要注意：这里需要手动将其转换成json才能返回，如果是google则不用手动转换，
		//但是IE、火狐则需要
		String jsonPic = JsonUtils.object2Json(pictureResult);
		return jsonPic ;
	}
}
