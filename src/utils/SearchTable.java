package utils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SearchTable {

    private TableRowSorter<TableModel> sorter;
    private JTable table;
    private JTextField textField;

    public SearchTable() {
    }

    public void activate(JTable table, JTextField textField, int... indeces) {
        this.textField = textField;
        this.table = table;

        activateTableSearching(indeces);
    }

    public void activate(JTable table) {
        this.table = table;
        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
    }

    public void newFilter(String str, int... indeces) {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            // the (?!) sign is to ignorecase
            rf = RowFilter.regexFilter("(?i)" + str, indeces);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void activateTableSearching(final int... indeces) {
        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        textField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(textField.getText(), indeces);
                    }

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        newFilter(textField.getText(), indeces);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        newFilter(textField.getText(), indeces);
                    }
                });
    }
}
