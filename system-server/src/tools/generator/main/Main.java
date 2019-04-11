package main;
import common.Generator;
import common.GeneratorConfig;

/**
 * 代码生成器
 * @author huangga
 *
 */
public class Main {
	
	static String YES = "1";
	static String NO = "0";

	public static void main(String[] args) throws Exception {


		GeneratorConfig config = new GeneratorConfig();
		//数据表名
		config.tableName="sys_user";

		//类描述
		config.description="系统用户信息";

		//包路径
		config.packageUrl="com.zmyjn.sys.user";

		//类名称
		config.className="SysUser";

        // 请求链接前缀
		config.requestPrefix = "sys";

		config.listHtmlTitle = "商品管理 - 商品类型 - 列表页面 ";
		config.addHtmlTitle = "商品管理 - 商品类型 - 新增页面 ";
		config.editHtmlTitle = "商品管理 - 商品类型 - 编辑页面 ";

		config.controllerFlag = YES;
		config.serviceFlag = YES;
		config.entityFlag = YES;
		config.mapperDaoFlag = YES;
		config.mapperXmlFlag = YES;
		config.htmlFlag = YES;



		config.testFlag=NO;
		config.outFlag = NO;
		//计算其它参数
		config.refreshConfig();

		Generator generator = Generator.getInstance(config);
		generator.createCode();

	}
}