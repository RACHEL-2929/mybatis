package com.ohgiraffers.section02;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Application {
    public static void main(String[] args) {

        String resource = "mybatis-config.xml";
        SqlSession session = null;

        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession(false);

            // 하나의 정보를 불러올거라 selectOne, 여러개 불러올거면 selectList를 사용해야함
            Date date = session.selectOne("mapper.selectSysDate");
            System.out.println(date + "section02 Application");



        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }


    }
}
