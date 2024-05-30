package com.ohgiraffers.section01;

import static com.ohgiraffers.section01.Template.getSqlSession;

public class Application {
    public static void main(String[] args) {
        System.out.println(getSqlSession().hashCode());
        System.out.println(getSqlSession().hashCode());
    }
}
