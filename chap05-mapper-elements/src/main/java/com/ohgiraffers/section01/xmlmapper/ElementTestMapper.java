package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.CategoryAndMenuDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;
import com.ohgiraffers.common.MenuDTO;

import java.util.List;

public interface ElementTestMapper {

    List<String> selectCacheTest();

    List<MenuDTO> selectResultMapTest();

    List<MenuDTO> selectResultMapConstructorTest();

    List<MenuAndCategoryDTO> selectResultMapAssociationTest();

    List<CategoryAndMenuDTO> selectResultMapCollectionTest();

    int insertMenuTest(MenuDTO menuDTO);

    int insertNewCategory(MenuAndCategoryDTO menuAndCategoryDTO);

    int insertNewMenu(MenuAndCategoryDTO menuAndCategoryDTO);
}
