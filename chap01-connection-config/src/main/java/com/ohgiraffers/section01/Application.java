package com.ohgiraffers.section01;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.session.SqlSession;
import java.util.Date;


public class Application {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/menudb";
    private static String USER = "songpa";
    private static String PASS = "songpa";

    public static void main(String[] args) {
        /*
        * JdbcTransactionFactory : 수동 커밋
        * ManagedTransactionFactory : 오토 커밋 => mysql을 기본으로 오토커밋이라 바로 반영
        * --------------------------------
        * PooledDataSource : connectionPool 을 사용함 => 처음 시작에 다 만들어 놔서 느리지만 여러번 꺼내올 때는 빠르다.
        * UnPooledDataSource : 사용하지 않음
        *
        *
        * */



        //TransactionFactory 의 종류 중 (JDBC,Managed) 어떤 걸 사용할 건지 결정한거고
        Environment environment = new Environment(
                "dev",
                new JdbcTransactionFactory(),
                new PooledDataSource(DRIVER,URL,USER,PASS)
        );


        //생성한 환경 설정 정보로 MyBatis 설정 객체 생성
        Configuration config = new Configuration(environment);

        // Mapper를 보낼건데 자료형만 class 형태로 보냄
        // addMapper 가 매개변수로 클래스를 받기 때문이다.
        config.addMapper(Mapper.class);

        /*
         * SqlSessionFactory: sqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스
         * // 인터페이스이니까 바로 객체를 생성할 수 없기 때문에 Builder 를 사용함
         * SqlSessionFactoryBuilder : sqlSessionFactory 인터페이스 타입의 하위 구현 객체를 생성하기 위한 빌드 역할
         * build : 설정에 대한 정보를 담고 있는 Configuration 타입의 객체 혹은 외부 설정 파일과 연관된 stream 을
         * 매개변수로 전달하면 sqlSessionFactory 인터페이스 타입의 객체를 반환하는 메소드
         *
         * sqlSession : jdbc 의 connection 과 같은 역할을 하는 객체
         * */
        // 우리가 설정한 config  를 바탕으로 session 을 만들어 달라
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        System.out.println(sqlSessionFactory);

        /*
        * openSession() : sqlSession 인터페이스 타입의 객체를 반환하는 메소드 boolean 타입 인자 전달
        *
        * - false : connection 인터페이스 타입 객체 DML 수행 후 Auto Commit 에 대한 옵션을 false 로 저장 <권장>
          - true :  connection 인터페이스 타입 객체 DML 수행 후 Auto Commit 에 대한 옵션을 true 로 저장
        * */
        // JdbcTransactionFactory 를 실제로 사용할건지 여부를 묻는 것

        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        Mapper mapper = sqlSession.getMapper(Mapper.class);
        Date date = mapper.selectSysDate();
        System.out.println(date);
        sqlSession.close();





    }


}
