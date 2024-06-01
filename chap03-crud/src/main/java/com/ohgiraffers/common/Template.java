package com.ohgiraffers.common;

import com.ohgiraffers.section03.remix.model.MenuMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Template {

    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSession getSqlSession(){

        if(sqlSessionFactory == null){
            try {
                InputStream inputStream = Resources.getResourceAsStream("xmlconfig/mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory.openSession(false);

    }

    public static SqlSession getJavaSqlSession(){
        if(sqlSessionFactory == null){

            Properties properties = new Properties();
            try {
                properties.load(new FileReader("src/main/resources/javaconfig/javaconfig.properties"));
                String driver = properties.getProperty("Driver");
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                Environment environment = new Environment(
                        "dev",
                        new JdbcTransactionFactory(),
                        new PooledDataSource(driver,url,user,password)
                );

                Configuration configuration = new Configuration(environment);
                configuration.addMapper(com.ohgiraffers.section02.javaconfig.model.MenuMapper.class);


                sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        return sqlSessionFactory.openSession(false);
    }

    public static SqlSession getRemixSqlSession(){
        if(sqlSessionFactory == null){

            Properties properties = new Properties();
            try {
                properties.load(new FileReader("src/main/resources/javaconfig/javaconfig.properties"));
                String driver = properties.getProperty("Driver");
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");

                Environment environment = new Environment(
                        "dev",
                        new JdbcTransactionFactory(),
                        new PooledDataSource(driver,url,user,password)
                );

                Configuration configuration = new Configuration(environment);
                configuration.addMapper(MenuMapper.class);


                sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        return sqlSessionFactory.openSession(false);
    }





}
