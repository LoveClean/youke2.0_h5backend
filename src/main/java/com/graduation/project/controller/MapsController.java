package com.graduation.project.controller;

import com.graduation.project.annotation.ACS;
import com.graduation.project.controller.request.MapsInsertSelective;
import com.graduation.project.controller.request.MapsUpdateByPrimaryKeySelective;
import com.graduation.project.controller.request.UserLoginRequestBean;
import com.graduation.project.controller.response.PageResponseBean;
import com.graduation.project.dao.entity.Maps;
import com.graduation.project.service.MapsService;
import com.graduation.project.service.MemcachedService;
import com.graduation.project.service.SmsService;
import com.graduation.project.util.ResponseEntity;
import com.graduation.project.util.ResponseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "数字地图操作接口", produces = "application/json")
@RestController
@RequestMapping("/maps/")
public class MapsController extends BaseController {
    @Autowired
    private MapsService mapsService;
    @Autowired
    private SmsService smsService;
    @Resource
    private MemcachedService memcachedService;

    @ApiOperation(value = "查看网点", notes = "根据主键查看网点")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<Maps> selectByPrimaryKey(@RequestParam Integer id) {
        return mapsService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看网点列表", notes = "查看网点列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return mapsService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "搜索网点列表", notes = "根据name和areaId查看网点列表")
    @GetMapping(value = "selectListBySearch")
    public PageResponseBean selectListBySearch(@RequestParam String name, @RequestParam String areaId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return mapsService.selectListBySearch(name, areaId, pageNum, pageSize);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "登录接口",notes = "用户登录，username为手机")
    @PostMapping(value="login.do")
    public ResponseEntity<Maps> login(@Valid @RequestBody UserLoginRequestBean bean,
                                      HttpServletRequest request) {

//		Boolean flag=false;
//		if (memcachedService.get(Const.VERIFY_CODE) != null) {
//			String randomCode = memcachedService.get(Const.VERIFY_CODE).toString();
//
//			if (StringUtil.isNotBlank(randomCode) && bean.getVerifyCode().toUpperCase().equals(randomCode.toUpperCase())) {
//				flag = true;
//				memcachedService.delete(Const.VERIFY_CODE);
//			}
//		}
//
//		if(!flag) {
//			return ResponseEntityUtil.fail("验证码错误");
//		}
//
        ResponseEntity<Maps> response=mapsService.userLogin(bean.getLoginName());
        if(response.isSuccess()) {
            Maps user= response.getData();
            //session.setAttribute(Const.CURRENT_USER, response.getData());
            // 创建访问token
            String accessToken = super.generateAccessToken(request);
            user.setAccessToken(accessToken);

            super.setAccessTokenAttribute(request, accessToken);
            super.sessionUser(request, user);

            return ResponseEntityUtil.success(user);
        }
        return response;

    }

    /**
     * 退出登录
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        deleteSessionUser(request);
        return ResponseEntityUtil.success();
    }
}
