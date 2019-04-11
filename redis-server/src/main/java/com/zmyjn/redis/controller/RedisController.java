package com.zmyjn.redis.controller;

import com.zmyjn.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Redis信息",tags = "Redis信息接口")
@RequestMapping("/sys/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/setRedis")
    @ApiOperation(value = "赋值redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "string", paramType = "query",required = false),
            @ApiImplicitParam(name = "name",value = "名称",dataType = "string", paramType = "query",required = false)
    })
    public String setRedis(String id,String name){
        redisService.set(id+"",name);
        return "success";
    }

    @GetMapping(value = "/getRedis")
    @ApiOperation(value = "获取redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "string", paramType = "query",required = false)
    })
    public String getRedis(String id){

        return redisService.get(id).toString();
    }

}
