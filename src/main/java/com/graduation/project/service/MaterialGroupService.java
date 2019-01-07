package com.graduation.project.service;

import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.MaterialGroup;
import com.graduation.project.util.ResponseEntity;

public interface MaterialGroupService {

    ResponseEntity<MaterialGroup> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListBySearch(String name, Integer pageNum, Integer pageSize);

}
