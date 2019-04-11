package ##{srcPack}##.dao.mapper;

import ##{srcPack}##.entity.##{domainObjectName}##;
import com.zmyjn.core.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: ##{description}##
 * @author: ##{author}##
 * @date: ##{date}##
 */
@Mapper
public interface ##{domainObjectName}##Mapper extends BaseMapper<##{domainObjectName}##,##{tablePrimaryKeyType}##>{
	
}
