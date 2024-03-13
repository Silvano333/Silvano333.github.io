/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mwimola
 */
public class HeaderColumnModel extends AbstractTableModel{

    private String[] variabili = null;
    public HeaderColumnModel(String[] v)
    {
        super();
        variabili = v;
    }
    
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return variabili.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return variabili[columnIndex];
    }
    
}
