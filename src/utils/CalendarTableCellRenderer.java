package utils;

import entities.RoomStatus;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class CalendarTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                RoomStatus r = (RoomStatus) value;
                if (r instanceof RoomStatus) {
                    switch (r.getStatus()) {
                        case Status.UNAVAILABLE:
                            super.setBackground(new Color(255, 255, 102));
                            super.setToolTipText("Unavailable");
                            break;
                        case Status.RESRVE:
                            super.setBackground(new Color(255, 102, 102));
                            super.setToolTipText("Reserve");
                            break;
                        case Status.OCCUPIED:
                            super.setBackground(new Color(0, 204, 204));
                            super.setToolTipText("Occupied");
                            break;
                        case Status.UNDER_MAINTANANCE:
                            super.setBackground(new Color(51, 51, 255));
                            super.setToolTipText("Occupied");
                            break;
                        default:

                            throw new AssertionError();
                    }
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return dtcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
