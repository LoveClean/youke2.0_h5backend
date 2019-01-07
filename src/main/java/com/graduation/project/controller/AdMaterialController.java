package com.graduation.project.controller;

import com.graduation.project.controller.request.AdMaterialInsertSelective;
import com.graduation.project.controller.request.AdMaterialUpdateByPrimaryKeySelective;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.AdMaterial;
import com.graduation.project.service.AdMaterialService;
import com.graduation.project.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "广告素材操作接口", produces = "application/json")
@RestController
@RequestMapping("/AdMaterial/")
public class AdMaterialController extends BaseController {
    @Autowired
    private AdMaterialService adMaterialService;


    @ApiOperation(value = "查看广告素材", notes = "根据主键查看广告素材")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<AdMaterial> selectByPrimaryKey(@RequestParam Integer id) {
        return adMaterialService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看广告素材列表", notes = "查看广告素材列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adMaterialService.selectList(pageNum, pageSize);
    }

}
