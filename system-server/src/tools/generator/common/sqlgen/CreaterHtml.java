package common.sqlgen;

import common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaterHtml {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public static String list(Connection conn, String tabName) throws SQLException, IOException, ClassNotFoundException {
        StringBuffer sb = new StringBuffer();

        ResultSet rs = conn.createStatement().executeQuery("show full columns from " + tabName + "");

        ResultSetMetaData rsmd = rs.getMetaData();

        Map<String, String> mapType = new HashMap<String, String>();
        Map<String, String> mapRemark = new HashMap<String, String>();

        List<String> strs = new ArrayList<String>();

        while (rs.next()) {
            strs.add(rs.getString("Field"));
            mapType.put(rs.getString("Field"), rs.getString("Type"));
            mapRemark.put(rs.getString("Field"), rs.getString("Comment"));
        }

        String javaType = "";
        String javaName = "";
        String javaReamek = "";

        for (String str : strs) {

            javaType = sqlType2JavaType(str.toLowerCase(), mapType.get(str));
            javaName = StringUtil.lineToHump(str.toLowerCase());
            javaReamek = mapRemark.get(str);

            //{ field: "id", width: 100, title: "商品ID", sort: !0 },
            //{ field: "javaName", width: 100, title: "javaReamek", sort: !0 },

            if(javaName.equals("sort")){
                sb.append("\t\t\t\t{ field: \""+javaName+"\", width: 100, title: \""+javaReamek+"\", sort: !0 },\n");
            }else{
                sb.append("\t\t\t\t{ field: \""+javaName+"\", width: 100, title: \""+javaReamek+"\" },\n");
            }
        }

        return sb.toString();
    }
    public static String editHtml(Connection conn, String tabName) throws SQLException, IOException, ClassNotFoundException {
        StringBuffer sb = new StringBuffer();

        ResultSet rs = conn.createStatement().executeQuery("show full columns from " + tabName + "");

        Map<String, String> mapType = new HashMap<String, String>();
        Map<String, String> mapRemark = new HashMap<String, String>();

        List<String> strs = new ArrayList<String>();

        while (rs.next()) {
            strs.add(rs.getString("Field"));
            mapType.put(rs.getString("Field"), rs.getString("Type"));
            mapRemark.put(rs.getString("Field"), rs.getString("Comment"));
        }

        String javaType = "";
        String javaName = "";
        String javaReamek = "";
        sb.append("\t\n");
        for (String str : strs) {

            javaType = sqlType2JavaType(str.toLowerCase(), mapType.get(str));
            javaName = StringUtil.lineToHump(str.toLowerCase());
            javaReamek = mapRemark.get(str);

            sb.append("\t<div class=\"layui-form-item\">\n");
            sb.append("\t\t<label class=\"layui-form-label\">"+javaReamek+"</label>\n");
            sb.append("\t\t<div class=\"layui-input-inline\">\n");

            if(javaType.equals("Integer")){
                sb.append("\t\t\t<input type=\"text\" name=\""+javaName+"\" lay-verify=\"required\" placeholder=\""+javaReamek+"\" lay-verify=\"number\" autocomplete=\"off\" class=\"layui-input\">\n");
            }else if("text".equals(javaType)) {
                sb.append("\t\t\t<input type=\"text\" name=\""+javaName+"\" lay-verify=\"required\" placeholder=\""+javaReamek+"\" autocomplete=\"off\" class=\"layui-input\">\n");
            }else if("status1".equals(javaName)){
                // 状态
                sb.append("\t\t\t<input type=\"checkbox\" checked=\"\" name=\"status1\" lay-skin=\"switch\" lay-filter=\"switchStatus1\" lay-text=\"已发布|待发布\">\n");
            } else{
                sb.append("\t\t\t<input type=\"text\" name=\""+javaName+"\" lay-verify=\"required\" placeholder=\""+javaReamek+"\" autocomplete=\"off\" class=\"layui-input\">\n");
            }
            sb.append("\t\t</div>\n");
            sb.append("\t</div>\n");
        }

        return sb.toString();
    }
    public static String editJs(Connection conn, String tabName) throws SQLException, IOException, ClassNotFoundException {
        StringBuffer sb = new StringBuffer();

        ResultSet rs = conn.createStatement().executeQuery("show full columns from " + tabName + "");

        Map<String, String> mapType = new HashMap<String, String>();
        Map<String, String> mapRemark = new HashMap<String, String>();

        List<String> strs = new ArrayList<String>();

        while (rs.next()) {
            strs.add(rs.getString("Field"));
            mapType.put(rs.getString("Field"), rs.getString("Type"));
            mapRemark.put(rs.getString("Field"), rs.getString("Comment"));
        }

        String javaType = "";
        String javaName = "";
        String javaReamek = "";

        for (String str : strs) {

            javaType = sqlType2JavaType(str.toLowerCase(), mapType.get(str));
            javaName = StringUtil.lineToHump(str.toLowerCase());
            javaReamek = mapRemark.get(str);

            //sb.append("$(\"input[name='"+javaName+"']\").val(data.data."+javaName+");//"+javaReamek+" \n ");
            sb.append("\t\t\t\t\t\""+javaName+"\" : data.data."+javaName+" ,// "+javaReamek+" \n ");

        }
        return sb.toString();
    }
    /**
     *
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String key, String sqlType) {
        if (sqlType.toLowerCase().startsWith("double")) {
            return "double";
        } else if (sqlType.toLowerCase().startsWith("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (sqlType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2")
                || sqlType.equalsIgnoreCase("varchar2")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")
                || sqlType.equalsIgnoreCase("timestamp with local time zone")
                || sqlType.equalsIgnoreCase("timestamp with time zone")
                || sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.toLowerCase().startsWith("number")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("varchar")) {
            return "String";
        } else if (sqlType.toLowerCase().startsWith("int")) {
//			if (key.toLowerCase().endsWith("_code") || key.toLowerCase().endsWith("_id")
//					|| key.toLowerCase().equals("id"))
            return "Integer";
//			else
//				return "int";
        }

        return "String";
    }

    public static String getFirstLower(String str) {

        return str.subSequence(0, 1).toString().toLowerCase() + str.subSequence(1, str.length());

    }

    public static String getFirstUpper(String str) {

        return str.subSequence(0, 1).toString().toUpperCase() + str.subSequence(1, str.length());

    }

}
