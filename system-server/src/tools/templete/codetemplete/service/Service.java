package ##{srcPack}##.service;


import com.zmyjn.core.base.controller.ResultData;
import com.zmyjn.core.base.service.BaseService;
import com.zmyjn.core.page.Page;
import ##{srcPack}##.entity.##{domainObjectName}##;

/**
 * @Description: ##{description}##
 * @author: ##{author}##
 * @date: ##{date}##
 */
public interface ##{domainObjectName}##Service extends BaseService<##{domainObjectName}##, ##{tablePrimaryKeyType}##> {
	
	/**
	 * 列表
	 * @param page
	 * @return
	 */
	void list(ResultData result, Page<##{domainObjectName}##> page,String searchKeys);
	
	/**
	 * 新增/修改初始化
	 * @param result
	 * @param id
	 * @return
	 */
	void init(ResultData result,Integer id);

	/**
	 * 添加保存
	 * @param result
	 * @param entity
	 * @return
	 */
	void addSave(ResultData result, ##{domainObjectName}## entity);
	
	/**
	 * 修改保存
	 * @param result
	 * @param entity
	 * @return
	 */
	void updateSave(ResultData result, ##{domainObjectName}## entity);
	
	/**
	 * 详情
	 * @param result
	 * @param id
	 * @return
	 */
	void findById( ResultData result, ##{tablePrimaryKeyType}## id);
	
	/**
	 * 根据ids删除
	 * @param result
	 * @param ids
	 * @return
	 */
	void deleteByIds(ResultData result, ##{tablePrimaryKeyType}##[] ids);
	
	/**
	 * 根据id删除
	 * @param result
	 * @param id
	 * @return
	 */
	void deleteById(ResultData result, ##{tablePrimaryKeyType}## id);
}
