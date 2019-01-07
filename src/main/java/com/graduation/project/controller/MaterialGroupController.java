package com.graduation.project.controller;

import com.graduation.project.controller.request.MaterialGroupInsertSelective;
import com.graduation.project.controller.request.MaterialGroupUpdateByPrimaryKeySelective;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.MaterialGroup;
import com.graduation.project.service.MaterialGroupService;
import com.graduation.project.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "素材分组分组操作接口", produces = "application/json")
@RestController
@RequestMapping("/MaterialGroup/")
public class MaterialGroupController extends BaseController {
    @Autowired
    private MaterialGroupService materialGroupService;


    @ApiOperation(value = "查看素材分组", notes = "根据主键查看素材分组")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<MaterialGroup> selectByPrimaryKey(@RequestParam Integer id) {
        return materialGroupService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看素材分组列表", notes = "查看素材分组列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam(defaultValue = "1", required = false) Integer pageNum, @RequestParam(defaultValue = "999", required = false)  Integer pageSize) {
        return materialGroupService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "搜索素材分组列表", notes = "根据name查看素材分组列表")
    @GetMapping(value = "selectListBySearch")
    public PageResponseBean selectListBySearch(@RequestParam String name, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return materialGroupService.selectListBySearch(name, pageNum, pageSize);
    }

}