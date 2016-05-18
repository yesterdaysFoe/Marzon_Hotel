/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gui.HotelMain;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class Messages {

    public static void showErrorMessage(Component parrentComponent, String message) {
        JOptionPane.showMessageDialog(parrentComponent, message, "Oops!", JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccessMessage(Component parrentComponent, String message) {
        Image image = Toolkit.getDefaultToolkit().getImage(HotelMain.class.getClass().getResource("/images/done.png"));
        ImageIcon icon = new ImageIcon(image);
        JOptionPane.showMessageDialog(parrentComponent, message, null, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static void showInfoMessage(Component parrentComponent, String message) {
        JOptionPane.showMessageDialog(parrentComponent, message, "Oops!", JOptionPane.INFORMATION_MESSAGE);
    }
}
