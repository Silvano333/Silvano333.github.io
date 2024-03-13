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
public class HeaderRowModel extends AbstractTableModel{

    String[] base = null;
    
    public HeaderRowModel(boolean phase1,boolean min,int[] colonnebase, HeaderColumnModel headerColonne)
    {
        super();
        int righe = 0;
        for(int i =0; i<colonnebase.length;i++)
        {
            if(colonnebase[i]>righe)
                righe = colonnebase[i];
        }
        
        base = new String[righe+1];
        
        if(phase1)
            base[0] = "-ξ";
        else
        {
            if(min)
                base[0] = "-z";
            else
                base[0] = "z";
        }
        
        String variabile = "";
        for(int i = 0; i<colonnebase.length; i++)
        {
            if(colonnebase[i] !=0)
            {
                variabile = (String) headerColonne.getValueAt(1, i+1);
                base[colonnebase[i]] = variabile;
            }
        }
    }
    
    public void setBase(int[] colonnebase, HeaderColumnModel headerColonne)
    {

        String variabile = "";
        for(int i = 0; i<colonnebase.length; i++)
        {
            if(colonnebase[i] !=0)
            {
                variabile = (String) headerColonne.getValueAt(1, i+1);
                base[colonnebase[i]] = variabile;
            }
        }
        fireTableDataChanged();
    }
    
    public void deleteBase(int[] colonnebase, HeaderColumnModel headerColonne)
    {
                int righe = 0;
        for(int i =0; i<colonnebase.length;i++)
        {
            if(colonnebase[i]>righe)
                righe = colonnebase[i];
        }
        
        base = new String[righe+1];
        base[0] = "-ξ";
        String variabile = "";
        for(int i = 0; i<colonnebase.length; i++)
        {
            if(colonnebase[i] !=0)
            {
                variabile = (String) headerColonne.getValueAt(1, i+1);
                base[colonnebase[i]] = variabile;
            }
        }
        fireTableDataChanged();
    }
        
    @Override
    public int getRowCount() {
        return base.length;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return base[rowIndex];
    }
    
}
