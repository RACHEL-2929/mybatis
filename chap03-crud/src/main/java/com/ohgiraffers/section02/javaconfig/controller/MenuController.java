package com.ohgiraffers.section02.javaconfig.controller;

import com.ohgiraffers.common.PrintResult;
import com.ohgiraffers.dto.MenuDTO;
import com.ohgiraffers.section02.javaconfig.service.MenuService;

import java.util.List;
import java.util.Map;

public class MenuController {

    private final PrintResult printResult;

    private final MenuService menuService;

    public MenuController(){
        printResult = new PrintResult();
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

        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        if (menuService.registMenu(menu)){
            printResult.printSuccessMessage("insert");
        }else{
            printResult.printErrorMessage("insert");
        }


    }

    public void modifyMenu(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code"));
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));


        MenuDTO menu = new MenuDTO();
        menu.setCode(code);
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);


        if(menuService.modifyMenu(menu)){
            printResult.printSuccessMessage("update");
        }else {
            printResult.printErrorMessage("update");
        }

    }

    public void deleteMenu(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code"));


        if (menuService.deleteMenu(code)){
            printResult.printSuccessMessage("delete");
        }else{
            printResult.printErrorMessage("delete");
        }


    }
}
