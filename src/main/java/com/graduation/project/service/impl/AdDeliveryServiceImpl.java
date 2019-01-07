package com.graduation.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.AdDelivery;
import com.graduation.project.dao.mapper.AdDeliveryMapper;
import com.graduation.project.service.AdDeliveryService;
import com.graduation.project.util.ResponseEntity;
import com.graduation.project.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdDeliveryServiceImpl implements AdDeliveryService {
    @Resource
    private AdDeliveryMapper adDeliveryMapper;


    @Override
    public ResponseEntity<AdDelivery> selectByPrimaryKey(Integer id) {
        AdDelivery validResponse = adDeliveryMapper.selectByPrimaryKey(id);
        if (validResponse == null) {
            return ResponseEntityUtil.fail("投放记录不存在");
        }
        return ResponseEntityUtil.success(validResponse);
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdDelivery> mapsList = adDeliveryMapper.selectList();
        PageInfo pageInfo = new PageInfo(mapsList);
        pageInfo.setList(mapsList);

        PageResponseBean page = new PageResponseBean<AdDelivery>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

}
