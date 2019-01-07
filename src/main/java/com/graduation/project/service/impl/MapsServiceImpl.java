package com.graduation.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.graduation.project.contants.Errors;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.Maps;
import com.graduation.project.dao.mapper.MapsMapper;
import com.graduation.project.service.CityService;
import com.graduation.project.service.MapsService;
import com.graduation.project.util.MD5Util;
import com.graduation.project.util.ResponseEntity;
import com.graduation.project.util.ResponseEntityUtil;
import com.graduation.project.vo.AreaVo;
import com.graduation.project.vo.MapsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MapsServiceImpl implements MapsService {
    @Resource
    private MapsMapper mapsMapper;
    @Autowired
    private CityService cityService;

    @Override
    public ResponseEntity<Maps> selectByPrimaryKey(Integer id) {
        //校验
        Maps validResponse = mapsMapper.selectByPrimaryKey(id);
        if (validResponse == null) {
            return ResponseEntityUtil.fail("网点不存在");
        }
        return ResponseEntityUtil.success(validResponse);
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Maps> mapsList = mapsMapper.selectList();

        List<MapsVO> mapsVOList = Lists.newArrayList();
        for (Maps maps : mapsList) {
            ResponseEntity<AreaVo> areaAddress = cityService.selectByAreaId(maps.getAreaId());
            MapsVO mapsVO = new MapsVO(maps, areaAddress);
            mapsVOList.add(mapsVO);
        }
        PageInfo pageInfo = new PageInfo(mapsList);
        pageInfo.setList(mapsVOList);

        PageResponseBean page = new PageResponseBean<Maps>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public PageResponseBean selectListBySearch(String name, String areaId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Maps> mapsList = mapsMapper.selectListBySearch(name, areaId);
        PageInfo pageInfo = new PageInfo(mapsList);
        pageInfo.setList(mapsList);

        PageResponseBean page = new PageResponseBean<Maps>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public boolean mobileExist(String phone) {
        Maps maps = mapsMapper.selectByPhone(phone);
        if (maps == null) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<Maps> userLogin(String phone) {
        if(StringUtils.isBlank(phone)) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }

        //判断是邮箱还是手机号的正则表达式
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String ph = "^[1][34578]\\d{9}$";

        Maps maps=null;

        if(phone.matches(ph)) {
            maps= mapsMapper.selectByPhone(phone);
        }

        if(maps==null) {
            return ResponseEntityUtil.fail("账号或密码错误");
        }


//		if(this.checkUserStatus(user).isSuccess()) {
//			user.setPassword(StringUtils.EMPTY);
//			return ResponseEntityUtil.success(user);
//		}else {
//			return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
//		}

        return ResponseEntityUtil.success(maps);
    }

}
