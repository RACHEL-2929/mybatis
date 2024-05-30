package com.ohgiraffers.section01.xmlconfig.controller;

import com.ohgiraffers.common.PrintResult;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section01.xmlconfig.service.MenuService;

import java.util.List;
import java.util.Map;

public class MenuController {
    //화면 부분
    private final PrintResult printResult;
    private final MenuService menuService;


    public MenuController() {
        printResult  = new PrintResult();
        menuService = new MenuService();
    }

    public void selectAllMenu() {
        List<MenuDTO> menuList = menuService.selectAllMenu();

        printResult.printMenuList(menuList);

    }

    public void selectMenuByCode(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));

        MenuDTO menu = menuService.selectMenuByCode(code);

        printResult.printMenu(menu);

    }

    public void registMenu(Map<String, String> parameter) {

        MenuDTO menu = new MenuDTO();
        menu.setName(parameter.get("name"));
        menu.setPrice(Integer.parseInt(parameter.get("price")));
        menu.setCategoryCode(Integer.parseInt(parameter.get("categoryCode")));

        if(menuService.registMenu(menu)){
            // 메뉴등록이 성공했을 때 동작
            printResult.printSuccessMessage("insert");
        }else{
            // 메뉴 등록이 실패했을 때 동작
            printResult.printErrorMessage("insert");
        }

    }


    public void updateMenu(Map<String, String> parameter) {
        MenuDTO menu = new MenuDTO();
        menu.setCode(Integer.parseInt(parameter.get("code")));
        menu.setName(parameter.get("name"));
        menu.setPrice(Integer.parseInt(parameter.get("price")));
        menu.setCategoryCode(Integer.parseInt(parameter.get("categoryCode")));

        if(menuService.updateMenu(menu)){
            // 메뉴등록이 성공했을 때 동작
            printResult.printSuccessMessage("update");
        }else{
            // 메뉴 등록이 실패했을 때 동작
            printResult.printErrorMessage("update");
        }

    }

    public void deleteMenu(Map<String,String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));
        if(menuService.deleteMenu(code)){
            // 메뉴등록이 성공했을 때 동작
            printResult.printSuccessMessage("delete");
        }else{
            // 메뉴 등록이 실패했을 때 동작
            printResult.printErrorMessage("delete");
        }
    }
}
