package com.example.mvc.meal.services;
/**
 * 该类为Type的“服务控制层”
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

import com.example.mvc.meal.daos.TypeDao;

public class TypeService {

    TypeDao typeDao = new TypeDao();

    //获取类型清单(基于搜索)
    public Map getTypes(String name, int no) {
        return typeDao.getTypes(name,no);
    }

    //添加类型
    public boolean addType(Map<String, String> type) {
        return typeDao.addType(type);
    }

    //删除类型
    public boolean deleteTypeById(int id) {
        return typeDao.deleteTypeById(id);
    }

    //更新类型
    public boolean updateType(Map<String, String> type) {
        return typeDao.updateType(type);
    }

    //寻找类型基于ID
    public Map findTypeById(int id) {
        return typeDao.findTypeById(id);
    }

    //获取全部类型清单
    public List getAllType() {
        return typeDao.getAllType();
    }
}
