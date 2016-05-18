/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author kurtz
 */
public enum ProductEnum {

    HOTEL_SERVICE("Hotel Service"), RESTAURANT_MENU("Restaurant Menu");
    private final String name;

    private ProductEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
