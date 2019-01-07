package com.graduation.project.controller;

import com.graduation.project.controller.request.AdDeliveryInsertSelective;
import com.graduation.project.controller.request.AdDeliveryUpdateByPrimaryKeySelective;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.AdDelivery;
import com.graduation.project.service.AdDeliveryService;
import com.graduation.project.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "广告投放操作接口", produces = "application/json")
@RestController
@RequestMapping("/AdDelivery/")
public class AdDeliveryController extends BaseController {
    @Autowired
    private AdDeliveryService adDeliveryService;

    @ApiOperation(value = "查看广告投放", notes = "根据主键查看广告投放")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<AdDelivery> selectByPrimaryKey(@RequestParam Integer id) {
        return adDeliveryService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看广告投放列表", notes = "查看广告投放列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adDeliveryService.selectList(pageNum, pageSize);
    }

}
