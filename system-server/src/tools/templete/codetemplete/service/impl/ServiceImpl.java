package ##{srcPack}##.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zmyjn.core.log.LogUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zmyjn.core.base.mapper.BaseMapper;
import com.zmyjn.core.base.service.impl.BaseServiceImpl;
import com.zmyjn.core.page.Page;
import com.zmyjn.core.page.PageUtil;
import com.zmyjn.core.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.zmyjn.core.base.controller.ResultData;

import ##{srcPack}##.dao.mapper.##{domainObjectName}##Mapper;
import ##{srcPack}##.entity.##{domainObjectName}##;
import ##{srcPack}##.service.##{domainObjectName}##Service;

/**
 * @Description: ##{description}##
 * @author: ##{author}##
 * @date: ##{date}##
 */
@Service
public class ##{domainObjectName}##ServiceImpl extends BaseServiceImpl<##{domainObjectName}##, ##{tablePrimaryKeyType}##> implements ##{domainObjectName}##Service{

	@SuppressWarnings("unused")
	private final LogUtil logger = LogUtil.getLogger(this.getClass());
	
	@Autowired
	private ##{domainObjectName}##Mapper ##{firstLowerDomainObjectName}##Mapper;
	
	@Override
	public BaseMapper<##{domainObjectName}##, ##{tablePrimaryKeyType}##> getMapper(){
		return ##{firstLowerDomainObjectName}##Mapper;
	}
	
	/**
	 * 列表
	 * @param page
	 * @param searchKeys
	 */
	public void list(ResultData result,Page<##{domainObjectName}##> page,String searchKeys){
		Map<String, Object> mapSql=new HashMap<String, Object>();
		mapSql.put("searchKeys", StringUtils.searchKeys(searchKeys));
		
		PageHelper.startPage((int)page.getPage(), (int)page.getLimit());
		List<##{domainObjectName}##> list = ##{firstLowerDomainObjectName}##Mapper.list(mapSql);
		
		PageUtil.setPageInfo(page, list);

		result.setData(list);
		result.setCount((int) page.getTotal());
	}

	/**
	 * 新增/修改初始化
	 * @param result
	 * @param id
	 */
	public void init(ResultData result,Integer id){
		
		if(id!=null) {
			result.setData(##{firstLowerDomainObjectName}##Mapper.findById(id));
		}
	}

	/**
	 * 添加保存
	 * @param result
	 * @param entity
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addSave(ResultData result,##{domainObjectName}## entity){
		##{firstLowerDomainObjectName}##Mapper.insert(entity);
	}
	
	/**
	 * 修改保存
	 * @param result
	 * @param entity
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateSave(ResultData result,##{domainObjectName}## entity){
		##{firstLowerDomainObjectName}##Mapper.updateWithIf(entity);
	}

	/**
	 * 详情
	 * @param result
	 * @param id
	 */
	public void findById(ResultData result,##{tablePrimaryKeyType}## id){
		##{domainObjectName}## entity=##{firstLowerDomainObjectName}##Mapper.findById(id);
		result.setData(entity);
	}
	
	/**
	 * 根据id删除
	 * @param result
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteById(ResultData result,##{tablePrimaryKeyType}## id){
		##{firstLowerDomainObjectName}##Mapper.logicDelete(id);
	}
	
	/**
	 * 根据ids删除
	 * @param result
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteByIds(ResultData result,##{tablePrimaryKeyType}##[] ids){
		##{firstLowerDomainObjectName}##Mapper.logicDeleteByIds(ids);
	}

}
