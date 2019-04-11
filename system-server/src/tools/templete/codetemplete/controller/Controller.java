package ##{srcPack}##.controller;


import com.zmyjn.core.log.LogUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmyjn.core.base.controller.ResultData;
import com.zmyjn.core.page.Page;
import com.zmyjn.core.util.StringUtils;
import ##{srcPack}##.service.##{domainObjectName}##Service;
import ##{srcPack}##.entity.##{domainObjectName}##;


/**
 * @Description: ##{description}##
 * @author: ##{author}##
 * @date: ##{date}##
 */
@RestController
@Api(value = "##{description}##",tags = "##{description}##接口")
@RequestMapping("/##{requestMappingName}##")
public class ##{domainObjectName}##Controller{
	
	private final  LogUtil logger = LogUtil.getLogger(this.getClass());
	
	@Autowired
	private ##{domainObjectName}##Service ##{firstLowerDomainObjectName}##Service;
	

	@GetMapping(value="/list")
	@ApiOperation(value = "##{description}##列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "searchKeys",value = "关键词",dataType = "string", paramType = "query",required = false)
	})
	public ResultData list(@ModelAttribute Page<##{domainObjectName}##> page,String searchKeys){
		ResultData result=new ResultData();
		##{firstLowerDomainObjectName}##Service.list(result,page,searchKeys);

		return result;
	}


	@GetMapping(value="/init")
	@ApiOperation(value = "##{description}##新增/修改初始化")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "主键id",dataType = "##{tablePrimaryKeyType}##", paramType = "query",required = true)
	})
	public ResultData init(##{tablePrimaryKeyType}## id){
		ResultData result=new ResultData();
		##{firstLowerDomainObjectName}##Service.init(result,id);
		return result;
	}
	

	@PostMapping(value="/addSave")
	@ApiOperation(value = "##{description}##添加保存")
	public ResultData addSave(@ModelAttribute ##{domainObjectName}## entity){
		ResultData result=new ResultData();
		##{firstLowerDomainObjectName}##Service.addSave(result,entity);
		return result;
	}
	

	@PostMapping(value="/updateSave")
	@ApiOperation(value = "##{description}##修改保存")
	public ResultData updateSave(@ModelAttribute ##{domainObjectName}## entity){
		ResultData result=new ResultData();
		##{firstLowerDomainObjectName}##Service.updateSave(result,entity);
		return result;
	}
	

	@GetMapping(value="/findById")
	@ApiOperation(value = "##{description}##详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "主键id",dataType = "##{tablePrimaryKeyType}##", paramType = "query",required = true)
	})
	public ResultData findById(##{tablePrimaryKeyType}## id){		
		ResultData result=new ResultData();
		##{firstLowerDomainObjectName}##Service.findById(result,id);
		return result;
	}
	

	@PostMapping(value="/deleteById")
	@ApiOperation(value = "##{description}##根据id删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "主键id",dataType = "##{tablePrimaryKeyType}##", paramType = "query",required = true)
	})
	public ResultData deleteById(##{tablePrimaryKeyType}## id){		
		ResultData result=new ResultData();
		##{firstLowerDomainObjectName}##Service.deleteById(result,id);
		return result;
	}

	@PostMapping(value="/deleteByIds")
    @ApiOperation(value = "##{description}##根据id删除多个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "主键id",dataType = "##{tablePrimaryKeyType}##", paramType = "query",required = true)
    })
	public ResultData deleteByIds(String ids){		
		ResultData result=new ResultData();
		##{tablePrimaryKeyType}##[] splitIds=StringUtils.##{splitMethod}##(ids," ");
		##{firstLowerDomainObjectName}##Service.deleteByIds(result,splitIds);
		return result;
	}
	
	

}
