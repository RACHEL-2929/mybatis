package com.ohgiraffers.section02.javaconfig.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section02.javaconfig.model.MenuMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getJavaSqlSession;

public class MenuService {


    private MenuMapper menuMapper;

    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getJavaSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menuList = menuMapper.selectAllMenu();

        sqlSession.close();

        return menuList;
    }


    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getJavaSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);

        MenuDTO menu = menuMapper.selectMenuByCode(code);

        sqlSession.close();

        return menu;

    }

    public boolean registMenu(MenuDTO menu) {

        SqlSession sqlSession = getJavaSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.registMenu(menu);

        if (result > 0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true:false;
    }

    public boolean modifyMenu(MenuDTO menu) {

        SqlSession sqlSession = getJavaSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int result = menuMapper.modifyMenu(menu);

        if (result>0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true:false;

    }

    public boolean deleteMenu(int code) {

        SqlSession sqlSession = getJavaSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        int result = menuMapper.deleteMenu(code);

        if (result>0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0? true:false;

    }
}











