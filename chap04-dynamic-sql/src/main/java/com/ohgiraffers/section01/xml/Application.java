package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.SearchCriteria;

import java.util.*;


public class Application {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        do {
            System.out.println("====== 마이바티스 동적 SQL ======");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose(when,otherwise) 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim(where,set) 확인하기");
            System.out.println("9. 종료 하기");
            System.out.println("메뉴를 선택해주세요 : ");
            int no = scr.nextInt();

            switch (no) {
                case 1:
                    ifSubMenu();
                    break;
                case 2:
                    chooseSubMenu();
                    break;
                case 3:
                    foreachSubMenu();
                    break;
                case 4:
                    trimSubMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 번호입니다.");
                    break;
            }


        } while (true);
    }

    private static void ifSubMenu() {
        Scanner scr = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("========= if 메뉴 =========");
            System.out.println("1. 원하는 금액대의 적합한 추천 메뉴 목록 보여주기");
            System.out.println("2. 메뉴이름 혹은 카테고리 명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력 해주세요 : ");
            int no = scr.nextInt();

            switch (no) {
                case 1:
                    menuService.selectMenuByPrice(inputPrice());
                    break;
                case 2:
                    menuService.searchMenu(inputSearchChriteria());
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }


        } while (true);


    }

    private static int inputPrice() {
        Scanner scr = new Scanner(System.in);
        System.out.println("검색하실 가격의 최대 금액을 입력 해주세요 :");
        int price = scr.nextInt();

        return price;
    }

    private static SearchCriteria inputSearchChriteria() {
        Scanner scr = new Scanner(System.in);
        System.out.println("검색 기준을 입력 해주세요 (name or category) : ");
        String condition = scr.nextLine();
        System.out.println("검색어를 입력 해주세요");
        String value = scr.nextLine();

        return new SearchCriteria(condition, value);

    }

    private static void chooseSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("====== choose 서브 메뉴");
            System.out.println("카테고리 상위 분류별 메뉴 보여주기(식사,음료,디저트)");
            System.out.println("9. 이전 메뉴로");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuBySupCategory(inputSupCategory());
                    break;
                case 9:
                    return;
            }
        } while (true);


    }

    private static SearchCriteria inputSupCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("상위 분류를 입력해주세요(식사,음료,디저트) ");
        String value = sc.nextLine();

        return new SearchCriteria("category", value);
    }

    private static void foreachSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("====== foreach 서브 메뉴 =====");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴번호를 입력해주세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuByRandomMenuCode(createRandomMenuCodeList());
                    break;
                case 9:
                    return;
            }
        } while (true);


    }

    private static List<Integer> createRandomMenuCodeList() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 5) {
            int temp = ((int) (Math.random() * 21) + 1);
            set.add(temp);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list); // 오름차순 정렬
        return list;
    }

    private static void trimSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("====== trim 서브 메뉴 =====");
            System.out.println("1. 검색 조건이 있는 경우 메뉴코드로 조회, 단 없으면 전체 조회");
            System.out.println("2. 메뉴 혹은 카테고리 코드로 검색, 단 메뉴와 카테고리 둘 다 일치하는 경우도 검색하며, " +
                    "검색조건이 없는 경우 전체 검색");
            System.out.println("3. 원하는 메뉴 정보만 수정하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴번호를 입력해주세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuByCodeOrSearchAll(inputAllOrOne());
                    break;
                case 2:
                    menuService.searchMenuByNameOrCategory(inputSearchChriteriaMap());
                    break;
                case 3:
                    menuService.modifyMenu(inputChangeInfo());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }


    private static SearchCriteria inputAllOrOne() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색 조건을 입력하시겠습니까? (예,아니오) : ");
        boolean hasSearchValue = "예".equals(sc.nextLine()) ? true : false;

        SearchCriteria searchCriteria = new SearchCriteria();

        if (hasSearchValue) {
            System.out.println("검색할 메뉴 코드를 입력 해주세요 : ");
            String code = sc.nextLine();
            searchCriteria.setCondition("menuCode");
            searchCriteria.setValue(code);
        }
        return searchCriteria;
    }

    private static Map<String, Object> inputSearchChriteriaMap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색할 조건을 입력해주세요 (category,name,both )");
        String condition = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();

        if ("category".equals(condition)) {
            System.out.println("검색할 카테고리 코드를 입력 해주세요 : ");
            int categoryValue = sc.nextInt();

            criteria.put("categoryValue", categoryValue);
        } else if ("name".equals(condition)) {
            System.out.println("검색할 이름을 입력 해주세요 : ");
            String nameValue = sc.nextLine();

            criteria.put("nameValue", nameValue);

        } else if ("both".equals(condition)) {
            System.out.println("검색할 이름을 입력 해주세요 : ");
            String nameValue = sc.nextLine();
            System.out.println("검색할 카테고리 코드를 입력 해주세요 : ");
            int categoryValue = sc.nextInt();

            criteria.put("nameValue", nameValue);
            criteria.put("categoryValue", categoryValue);

        }
        return criteria;
    }

    private static Map<String, Object> inputChangeInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 메뉴 코드를 입력해주세요 : ");
        int code = sc.nextInt();
        System.out.println("변경할 메뉴 이름을 입력해주세요 : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("변경할 카테고리 코드를 입력해주세요 : ");
        int categoryCode = sc.nextInt();
        System.out.println("판매 여부를 입력해주세요 : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("code", code);
        criteria.put("name", name);
        criteria.put("categoryCode", categoryCode);
        criteria.put("orderableStatus", orderableStatus);

        return criteria;


    }


}
