package com.graduation.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.Ad;
import com.graduation.project.dao.mapper.AdMapper;
import com.graduation.project.service.AdService;
import com.graduation.project.util.ResponseEntity;
import com.graduation.project.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Resource
    private AdMapper adMapper;


    @Override
    public ResponseEntity<Ad> selectByPrimaryKey(Integer id) {
        Ad validResponse = adMapper.selectByPrimaryKey(id);
        if (validResponse == null) {
            return ResponseEntityUtil.fail("广告不存在");
        }
        return ResponseEntityUtil.success(validResponse);
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ad> mapsList = adMapper.selectList();
        PageInfo pageInfo = new PageInfo(mapsList);
        pageInfo.setList(mapsList);

        PageResponseBean page = new PageResponseBean<AdMapper>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public PageResponseBean selectListBySearch(String name, String groupid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ad> mapsList;
        if (groupid.equals("")) {
            mapsList = adMapper.selectListByName(name);
        } else {
            mapsList = adMapper.selectListBySearch(name, groupid);
        }
        PageInfo pageInfo = new PageInfo(mapsList);
        pageInfo.setList(mapsList);

        PageResponseBean page = new PageResponseBean<Ad>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

}
