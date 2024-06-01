package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.Template;
import org.apache.ibatis.session.SqlSession;

import java.awt.*;
import java.util.List;

public class ElementTestService {

    private ElementTestMapper mapper;

    public void selectCacheTest() {

        SqlSession sqlSession = Template.getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        /* 필기. 최초 요청 시에는 시간이 걸리지만 그 이후에는 캐싱된 데이터를 불러오기 때문에 속도가 빠르다. */
        for(int i = 0; i < 10; i++) {
            Long startTime = System.currentTimeMillis(); // 시작시간

            List<String> nameList = mapper.selectCacheTest();
            System.out.println(nameList);

            Long endTime = System.currentTimeMillis();  // 종료시간

            Long interval = endTime - startTime;
            System.out.println("수행 시간 : " + interval + "(ms)");
        }

        sqlSession.close();
    }

    public void selectResultMapTest() {
        SqlSession sqlSession = Template.getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapTest();

        for(MenuDTO menu : menuList){
            System.out.println(menu);
        }
        sqlSession.close();

    }
}
