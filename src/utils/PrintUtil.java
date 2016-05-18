/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gui.HotelMain;
import java.awt.Component;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class PrintUtil {

    public static void print(List<?> list, String jrxml, String frametitle, Map<String, Object> parameters, Component frame) throws JRException {
        JasperPrint jasperPrint;
        jasperPrint = JasperFillManager.fillReport(jrxml, parameters, new JRBeanCollectionDataSource(list));

        JasperViewer jv = new JasperViewer(jasperPrint);
        JDialog viewer = new JDialog(HotelMain.getInstance(), true);
        viewer.setTitle(frametitle);
        viewer.getContentPane().add(jv.getContentPane());

        viewer.setBounds(0, 0, 800, 600);
        viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewer.setVisible(true);
        viewer.toFront();
    }
}
