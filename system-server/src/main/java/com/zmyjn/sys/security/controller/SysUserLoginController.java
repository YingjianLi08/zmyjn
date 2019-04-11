package com.zmyjn.sys.security.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.zmyjn.core.base.controller.ResultData;
import com.zmyjn.core.entity.SysUserLogin;
import com.zmyjn.core.log.LogUtil;
import com.zmyjn.core.page.Page;
import com.zmyjn.core.util.JWTUtils;
import com.zmyjn.core.util.MD5Utils;
import com.zmyjn.sys.user.entity.SysUser;
import com.zmyjn.sys.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 系统用户登录
 * @author: Administrator
 * @date: 2019-03-31 11:16:34
 */
@RestController
@Api(value = "系统用户登录信息", tags = "系统用户登录信息接口")
@RequestMapping("/sys/sysUserLogin")
public class SysUserLoginController {

    private final LogUtil logger = LogUtil.getLogger(this.getClass());


    @Autowired
    private SysUserService sysUserService;


    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "string", paramType = "query", required = false)
    })
    public ResultData login(String userName, String password, String code) {
        ResultData result = new ResultData();


        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("userName", userName);
        password = MD5Utils.encrypt(password);
        paramMap.put("password", password);
        SysUser sysUser = sysUserService.findOneByMap(paramMap);

        if (null != sysUser) {

            SysUserLogin sysUserLogin = new SysUserLogin();

//            String sysUserJson = JSONUtils.toJSONString(sysUser);
//            sysUserLogin = (SysUserLogin) JSONUtils.parse(sysUserJson);
            sysUserLogin.setId(sysUser.getId());
            sysUserLogin.setUserName(sysUser.getUserName());
            sysUserLogin.setNickname(sysUser.getNickname());
            sysUserLogin.setSex(sysUser.getSex());
            sysUserLogin.setMobile(sysUser.getMobile());
            sysUserLogin.setEmail(sysUser.getEmail());
            sysUserLogin.setAvatar(sysUser.getAvatar());


            // 登录构建jwt
            String token = JWTUtils.createJWT(sysUserLogin);
            Map<String, Object> data = new HashMap<>();
            data.put("access_token", token);
            result.setData(data);
            result.setMsg("登录成功");
        }else{
            result.setCode(-1);
            result.setMsg("账号或密码错误，请重新输入");
        }



        return result;
    }
}
