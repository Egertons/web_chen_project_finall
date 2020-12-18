package com.example.mvc.meal.daos;
/**
 * 该类为Type的“数据操纵层”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.util.List;
import java.util.Map;

import com.example.mvc.framework.db.JDBCTemplate;

public class TypeDao {

    //搜索相关逻辑
    public Map getTypes(String name, int no) {
        String sql="select * from foodtype where typename like ? ";
        try {
            return JDBCTemplate.getPage(sql, new String[]{name}, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加菜品类型
    public boolean addType(Map<String, String> type) {
        try {
            String sql="insert into foodtype(typename) values(?)";
            return JDBCTemplate.update(sql, type.get("tn"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //删除菜品类型
    public boolean deleteTypeById(int id) {
        try {
            String sql="delete from foodtype where id = ?";
            return JDBCTemplate.update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //修改菜品类型
    public boolean updateType(Map<String, String> type) {
        try {
            String sql="update foodtype set typename = ? where id = ?";
            return JDBCTemplate.update(sql, type.get("tn"),type.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //通过ID寻找菜品类型
    public Map findTypeById(int id) {
        Map result = null;
        String sql="select * from foodtype where id=?";
        try {
            result= JDBCTemplate.queryForMap(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取全部菜品类型
    public List getAllType() {
        String sql="select * from foodtype";
        return JDBCTemplate.queryForList(sql);
    }
}
