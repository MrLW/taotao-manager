package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);
    
    List<TbItem> getAll() ;

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    /**
     *  分页
     * @param start
     * @param limit
     * @return
     */
	List<TbItem> getItemListByFy(@Param("start")int start, @Param("rows")int limit);
	
	Integer getCount() ;
}