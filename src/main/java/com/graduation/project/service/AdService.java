package com.graduation.project.service;

import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.Ad;
import com.graduation.project.util.ResponseEntity;

public interface AdService {

    ResponseEntity<Ad> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListBySearch(String name, String groupid, Integer pageNum, Integer pageSize);


}
