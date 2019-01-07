package com.graduation.project.dao.mapper;

import com.graduation.project.dao.entity.AdMaterial;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdMaterialMapper {
    @Update("UPDATE tb_ad_material SET del_flag = 1, update_by = #{updateBy}, update_date = now() WHERE id = #{id}")
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("updateBy") String updateBy);

    int insert(AdMaterial record);

    int insertSelective(AdMaterial record);

    AdMaterial selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM tb_ad_material WHERE del_flag = 0 ORDER BY create_date DESC")
    List<AdMaterial> selectList();

    int updateByPrimaryKeySelective(AdMaterial record);

    int updateByPrimaryKey(AdMaterial record);
}