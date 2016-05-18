package utils;

import gui.HotelMain;
import gui.LoginForm;
import javax.swing.JFrame;

public class Hotel {

    public static final String HOTEL_DISCOUNT = "HOTEL_DISCOUNT";
    public static final String RESTAURANT_DISCOUNT = "RESTAURANT_DISCOUNT";

    public static void main(String[] args) {
        HotelMain.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
        HotelMain.getInstance().setVisible(true);
        LoginForm.getInstance().setLocationRelativeTo(HotelMain.getInstance());
        LoginForm.getInstance().setVisible(true);
    }

}
