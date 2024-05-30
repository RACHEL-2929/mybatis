package com.ohgiraffers.section01.xmlconfig.model;

import com.ohgiraffers.dto.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {
    // 서비스에서 명령 받은 것을 mapping 시킨다
    // 데이터베이스와 직접적으로 연결된 메소드들이 들어간 클래스
    public List<MenuDTO> selectAllMenu(SqlSession sqlSession){
        return sqlSession.selectList("MenuMapper.selectAllMenu");
    }

    public MenuDTO selectMenuByCode(SqlSession sqlSession, int code) {
        return sqlSession.selectOne("MenuMapper.selectMenuByCode",code);



    }

    public int insertMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.insert("MenuMapper.insertMenu",menu);



    }

    public int updateMenu(SqlSession sqlSession, MenuDTO menu) {
        return sqlSession.update("MenuMapper.updateMenu",menu);


    }

    public int deleteMenu(SqlSession sqlSession,int code ) {
        return sqlSession.delete("MenuMapper.deleteMenu",code);

    }
}
