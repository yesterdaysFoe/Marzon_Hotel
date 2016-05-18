package gui;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class CalendarTableModel extends DefaultTableModel {

    public CalendarTableModel(Object[] headerName) {
        super(new Object[][]{}, headerName);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return java.lang.Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
}
