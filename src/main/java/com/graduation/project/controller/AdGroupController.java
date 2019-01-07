package com.graduation.project.controller;

import com.graduation.project.controller.request.AdGroupInsertSelective;
import com.graduation.project.controller.request.AdGroupUpdateByPrimaryKeySelective;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.AdGroup;
import com.graduation.project.service.AdGroupService;
import com.graduation.project.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "广告分组操作接口", produces = "application/json")
@RestController
@RequestMapping("/AdGroup/")
public class AdGroupController extends BaseController {
    @Autowired
    private AdGroupService adGroupService;



    @ApiOperation(value = "查看广告分组", notes = "根据主键查看广告分组")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<AdGroup> selectByPrimaryKey(@RequestParam Integer id) {
        return adGroupService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看广告分组列表", notes = "查看广告分组列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adGroupService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "搜索广告分组列表", notes = "根据name查看广告分组列表")
    @GetMapping(value = "selectListBySearch")
    public PageResponseBean selectListBySearch(@RequestParam String name, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adGroupService.selectListBySearch(name, pageNum, pageSize);
    }

}
