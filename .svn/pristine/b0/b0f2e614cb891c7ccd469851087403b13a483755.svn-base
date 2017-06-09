package com.taotao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;


public interface TbContentCategoryMapper {
    int countByExample(TbContentCategoryExample example);

    int deleteByExample(TbContentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    List<TbContentCategory> selectByExample(TbContentCategoryExample example);

    TbContentCategory selectByPrimaryKey(Long id);
    
    //自己添加的,查询所有parentId==id的记录
    List<TbContentCategory> selectByIdEqualsParentId(Long id);

    int updateByExampleSelective(@Param("record") TbContentCategory record, @Param("example") TbContentCategoryExample example);

    int updateByExample(@Param("record") TbContentCategory record, @Param("example") TbContentCategoryExample example);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);
    
}