package gui;

import entities.User;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class UserUtil {

    private static final String FRONT_DESK = "Front Desk";
    private static final String RESTAURANT_CASHIER = "Restaurant Cashier";
    private static final String ALL_IN_ONE = "All-in-one";

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserUtil.user = user;
    }

    public static boolean isFrontDesk() {
        return getUser().getPosition().equals(FRONT_DESK);
    }

    public static boolean isRestaurantCashier() {
        return getUser().getPosition().equals(RESTAURANT_CASHIER);
    }

    public static boolean isAllInOne() {
        return getUser().getPosition().equals(ALL_IN_ONE);
    }

}
