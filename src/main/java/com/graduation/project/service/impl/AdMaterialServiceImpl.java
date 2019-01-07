package com.graduation.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.AdMaterial;
import com.graduation.project.dao.mapper.AdMaterialMapper;
import com.graduation.project.service.AdMaterialService;
import com.graduation.project.util.ResponseEntity;
import com.graduation.project.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdMaterialServiceImpl implements AdMaterialService {
    @Resource
    private AdMaterialMapper adMaterialMapper;


    @Override
    public ResponseEntity<AdMaterial> selectByPrimaryKey(Integer id) {
        AdMaterial validResponse = adMaterialMapper.selectByPrimaryKey(id);
        if (validResponse == null) {
            return ResponseEntityUtil.fail("广告素材不存在");
        }
        return ResponseEntityUtil.success(validResponse);
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdMaterial> mapsList = adMaterialMapper.selectList();
        PageInfo pageInfo = new PageInfo(mapsList);
        pageInfo.setList(mapsList);

        PageResponseBean page = new PageResponseBean<AdMaterialMapper>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }


}
