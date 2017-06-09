package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIITreeNode;

public interface ItemCatService {

	List<EasyUIITreeNode> getItemCatList(Long parentId);
}
