package com.ohgiraffers.section01.xmlconfig.service;

import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.model.MenuDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {
    // DAO에서 받아온 결과들을 다루는 클래스

    private final MenuDAO menuDAO;

    public MenuService() {
        menuDAO = new MenuDAO();
    }

    public List<MenuDTO> selectAllMenu() {
        SqlSession sqlSession = getSqlSession();

        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);
        // session으로 연결한

        sqlSession.close();

        return menuList;

    }


    public MenuDTO selectMenuByCode(int code) {
        SqlSession sqlSession = getSqlSession();
        MenuDTO menu = menuDAO.selectMenuByCode(sqlSession, code);
        sqlSession.close();


        return menu;
    }

    public boolean registMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.insertMenu(sqlSession, menu);

        if (result > 0) {
            // 정상등록
            sqlSession.commit();
        } else {
            // 등록되지 않음
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0 ? true : false;

    }

    public boolean updateMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, menu);

        if (result > 0) {
            // 정상등록
            sqlSession.commit();
        } else {
            // 등록되지 않음
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0 ? true : false;
    }


    public boolean deleteMenu(int code) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, code);

        if (result > 0) {
            // 정상등록
            sqlSession.commit();
        } else {
            // 등록되지 않음
            sqlSession.rollback();
        }
        sqlSession.close();
        return result > 0 ? true : false;
    }
}
