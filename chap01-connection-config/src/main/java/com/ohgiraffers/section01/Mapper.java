package com.ohgiraffers.section01;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper {

    //날짜를 출력해주는 쿼리
    @Select("SELECT CURDATE() FROM DUAL")
    Date selectSysDate();


}
