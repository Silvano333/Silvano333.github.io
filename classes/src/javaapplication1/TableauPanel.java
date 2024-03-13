/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TableauPanel.java
 *
 * Created on 23-nov-2011, 15.19.30
 */
package javaapplication1;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mwimola
 */
public class TableauPanel extends javax.swing.JPanel {
    
    private int stepInit = 0;
    private int stepPhase1 = 0;
    private int stepPhase2 = 0;
    
    private int colonnaMem = -1;
    private int rigaMem = -1;
    
    private Frazione frazioneMem;
    
    private Tableau _tab = new Tableau(new Frazione[1][1]); 
    private Tableau _tabPhase1 = new Tableau(new Frazione[1][1]);
    
    private String[] _nomicolonne1 = {" ","x1","x2","x3"};
    private String[] _nomicolonne2= {" ","x1","x2","x3"};
    private HeaderColumnModel _headersCol2 = new HeaderColumnModel(_nomicolonne1);
    private HeaderColumnModel _headersCol1 = new HeaderColumnModel(_nomicolonne2);
    
    private HeaderRowModel _solution1;
    private HeaderRowModel _solution2;
 
    private boolean _min = true;
    
    private PivotRenderer _pivotRenderer = new PivotRenderer(); 
    /** Creates new form TableauPanel */
    
    public TableauPanel() {
        initComponents();
     //   setVisible(false);
        _tabella.setModel(_tab);
        _headers.setModel(_headersCol2);
        
        _tabella.setTableHeader(null);
        _headers.setTableHeader(null);
        _tabSolution.setTableHeader(null);
        
        _buttonGroup.add(_radioBland);
        _buttonGroup.add(_radioDantzig);
        _radioDantzig.setSelected(true);
        _pivoting1.setVisible(false);
        _pivoting2.setVisible(false);
    }
       private void initHeaders2()
   {
        _nomicolonne1 = new String[_tab.getColumnCount()];
        _nomicolonne1[0] = "  ";
        for(int i =1; i<_nomicolonne1.length; i++)
        {
            _nomicolonne1[i] = "x " + i;
        }
        _headersCol2 = new HeaderColumnModel(_nomicolonne1);
        _headers.setModel(_headersCol2);
   }
   
   private void initHeaders1()
   {
       _nomicolonne2 = new String[_tabPhase1.getColumnCount()];
       System.arraycopy(_nomicolonne1, 0, _nomicolonne2, 0, _nomicolonne1.length);
       int i,j;
       for(i = _tab.getColumnCount(), j = 1; i<_tabPhase1.getColumnCount();i++,j++)
       {
           _nomicolonne2[i] = "x(a)" + j;
       }
       _headersCol1 = new HeaderColumnModel(_nomicolonne2);
       _headers.setModel(_headersCol1);
   }
  /* private void setTab(Tableau t)
    {
        setVisible(true);
        _tab = t;
        _tabella.setModel(_tab);
    } */
   
   public void setTableau(Tableau t, boolean min)
   {
        stepInit = 0;
        stepPhase1 = 0;
        stepPhase2 = 0;
        colonnaMem = -1;
        rigaMem = -1;
        
       _comunicazione.setText("");
       _soluzione.setText("");
             
        _tab = t;
        _tabella.setModel(_tab);
        _min = min;       
        initHeaders2();
        _tabPhase1 = Phase1Manager.getTabPhase1(_tab.getTableau());
        _initButton.setVisible(true);
        _initButton.setText("Begin Phase 1");
        _pivoting1.setVisible(false);
        _pivoting2.setVisible(false);
        _tabSolution.setVisible(false);
        
        if (!Phase1Manager.isPhase1Needed(t))
       {
            _tab = Phase1Manager.getTabPhase2(_tabPhase1, _tab.getTableau()[0]);
            _initButton.setText("Init costs' row");
            _pivoting1.setVisible(false);
            stepInit = 3;
            _tabella.setModel(_tab);

       }

   
   }
   
   private class PivotRenderer extends DefaultTableCellRenderer{

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column)
    {
        
        Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row % 2 == 0)
        {
            result.setBackground(Color.white);
        }
        else
            result.setBackground(new Color(244, 244, 244));
        if(row == rigaMem && column == colonnaMem)
        {            
            result.setBackground(Color.YELLOW);
        }   
        return result;
    }

}
      private class MyDefaultRenderer extends DefaultTableCellRenderer{

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column)
    {
        
        Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row % 2 == 0)
        {
            result.setBackground(Color.white);
        }
        else
            result.setBackground(new Color(244, 244, 244));
        return result;
    }

}
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _buttonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        _tabella = new javax.swing.JTable();
        _initButton = new javax.swing.JButton();
        _soluzione = new javax.swing.JLabel();
        _radioDantzig = new javax.swing.JRadioButton();
        _radioBland = new javax.swing.JRadioButton();
        _pivoting1 = new javax.swing.JButton();
        _comunicazione = new javax.swing.JLabel();
        _pivoting2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        _headers = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        _tabSolution = new javax.swing.JTable();

        _tabella.setModel(_tab);
        _tabella.setRowSelectionAllowed(false);
        _tabella.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(_tabella);

        _initButton.setText("Begin Phase 1");
        _initButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _initButtonActionPerformed(evt);
            }
        });

        _soluzione.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        _soluzione.setForeground(new java.awt.Color(0, 153, 153));
        _soluzione.setText("           ");

        _radioDantzig.setText("Dantzig");

        _radioBland.setText("Bland");

        _pivoting1.setText("search for pivot");
        _pivoting1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _pivoting1ActionPerformed(evt);
            }
        });

        _comunicazione.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18));
        _comunicazione.setForeground(new java.awt.Color(204, 0, 0));
        _comunicazione.setText("       ");

        _pivoting2.setText("search for pivot");
        _pivoting2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _pivoting2ActionPerformed(evt);
            }
        });

        _headers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(_headers);

        _tabSolution.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(_tabSolution);
        _tabSolution.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_soluzione)
                    .addComponent(_comunicazione)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(_pivoting1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(_pivoting2))
                            .addComponent(_radioBland)
                            .addComponent(_radioDantzig)
                            .addComponent(_initButton))))
                .addContainerGap(279, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(_initButton)
                                .addGap(41, 41, 41)
                                .addComponent(_radioDantzig)
                                .addGap(3, 3, 3)
                                .addComponent(_radioBland)
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(_pivoting1)
                                    .addComponent(_pivoting2)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(_comunicazione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_soluzione)
                .addContainerGap(417, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void _initButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__initButtonActionPerformed
        if(stepInit == 0)
        {
            _tabPhase1 = Phase1Manager.getTabPhase1(_tab.getTableau());
            initHeaders1();
            /*if(_tab.getColumnCount() == _tabPhase1.getColumnCount())
            {
                _tab = Phase1Manager.getTabPhase2(_tabPhase1, _tab.getTableau()[0]);
                _tabella.setModel(_tab);
                _initButton.setVisible(false);
                _pivoting1.setVisible(false);
                _pivoting2.setVisible(true);
                _pivoting2.setText("search for pivot");
                return;
                
            } */
            _tabella.setModel(_tabPhase1);
            stepInit++;
            _initButton.setText("Init costs' row");
            return;
        }
        if(stepInit == 1)
        {
            _tabPhase1.initPhase1();
            _solution1 = new HeaderRowModel(true, true, _tabPhase1.getColonneBase(), _headersCol1);
            _tabSolution.setVisible(true);
            _tabSolution.setModel(_solution1);
            //_tabPhase1.initRigaCosto();
            
            _initButton.setText("Insert original objective function");
            _initButton.setVisible(false);
            _pivoting1.setVisible(true);
            
            _soluzione.setText("Current Solution: " + _tabPhase1.printSolutionForPhase1(_tab.getColumnCount() - 1));
            _pivoting1.setText("search for Pivot");
            stepInit++;
            return;
        }
        if(stepInit == 2)
        {
            _comunicazione.setText("");
            
            _tab = Phase1Manager.getTabPhase2(_tabPhase1, _tab.getTableau()[0]);
            initHeaders2();
            _tabella.setModel(_tab);
            _pivoting1.setVisible(false);
            _tabSolution.setVisible(false);
            _initButton.setText("Init costs' row");
            stepInit++;
            return;
        }
        
        if(stepInit == 3)
        {
            _initButton.setVisible(false);
            _tab.initRigaCosto();
                        
            _solution2 = new HeaderRowModel(false, _min, _tab.getColonneBase(), _headersCol2);
            _tabSolution.setModel(_solution2);
            _tabSolution.setVisible(true);
            stepInit++;
 
            if(_tab.isOptimal())
            {
                // _comunicazione.setText("Optimal value found");
                _soluzione.setText("Optimal Basic Feasible Solution: " + _tab.printSolution());
                _pivoting2.setVisible(false);
            }
            else
            {
                _soluzione.setText("Initial Basic Feasible Solution: " + _tab.printSolution());
                _pivoting2.setVisible(true);
            }
            return;
        }
    }//GEN-LAST:event__initButtonActionPerformed

    private void _pivoting1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__pivoting1ActionPerformed
        if(stepPhase1 == 0)
        {
            if(_radioBland.isSelected())
                colonnaMem = _tabPhase1.doBland();
            else
                colonnaMem = _tabPhase1.doDantzig();
            if(colonnaMem == -1)    //tutti cosi maggiori di zero
            {
                if(_tabPhase1.getZeta0().compareTo(new Frazione(0)) < 0)
                {
                    _comunicazione.setText("The feasible region for the original problem is empty: no solution");
                    _soluzione.setText("");
                    _pivoting1.setVisible(false);
                    return;
                    //STOP E SI RICOMINCIA TUTTO DA CAPO.
                }
                else
                {
                    // CONTROLLARE SITUAZIONI DEGENERI O RIDONDANTI
                    if(Phase1Manager.verificaVariabiliArtificialiInBase(_tabPhase1, _tab.getColumnCount()))
                    {
                        _soluzione.setText("");
                        _comunicazione.setText("Artificial variables still in Base. Do special Pivoting");
                        _pivoting1.setText("Special Pivoting");
                        stepPhase1 = 2;
                        return;
                    }
                    else    //PROCEDO A FASE 2
                    {
                        _comunicazione.setText("You can proceed to Phase 2");
                        _soluzione.setText("Initial Basic Feasible Solution:" + _tabPhase1.printSolutionForPhase1(_tab.getColumnCount() -1));
                        _pivoting1.setVisible(false);
                        _initButton.setVisible(true);
                        stepPhase1 = 4;
                        return;
                    }
                }
                
            }
            else          //CERCO IL PIVOT
            {
                _soluzione.setText("");
                rigaMem = _tabPhase1.FindPivotRow(colonnaMem);
                frazioneMem = (Frazione) _tabPhase1.getValueAt(rigaMem, colonnaMem);
                _tabella.setDefaultRenderer(Object.class, _pivotRenderer);
                _comunicazione.setText("Element " +rigaMem + " of column " + colonnaMem + ". Pivot: " + frazioneMem.toString());
                _pivoting1.setText("Do pivoting");
                stepPhase1++;
            }
            return;
        }
        if(stepPhase1 == 1)   //PASSO AL TABLEU SUCCESSIVO
        {
            _comunicazione.setText("");
            _tabella.setDefaultRenderer(Object.class, new MyDefaultRenderer());
            _tabPhase1.doPivoting(rigaMem, colonnaMem);
            _solution1.setBase(_tabPhase1.getColonneBase(), _headersCol1);
            _soluzione.setText("Current Solution:" + _tabPhase1.printSolutionForPhase1(_tab.getColumnCount() -1));
            stepPhase1 = 0;
            _pivoting1.setText("Search for pivot");
            
            return;
        }
       
        if(stepPhase1 == 2)   //VARIABILI ARTIFICIALI ANCORA IN BASE
        {
            boolean ok = Phase1Manager.DoSpecialPivoting(_tabPhase1, _tab.getColumnCount());
            _solution1.setBase(_tabPhase1.getColonneBase(), _headersCol1);
            if(ok)   //LE VARIABILI ARTIFICIALI SONO USCITE TUTTE
            {
                        _comunicazione.setText("You can proceed to Phase 2");
                        _soluzione.setText("Initial Basic Feasible Solution: " + _tabPhase1.printSolutionForPhase1(_tab.getColumnCount() -1));
                        _solution1.setBase(_tabPhase1.getColonneBase(), _headersCol1);
                                                
                        _pivoting1.setVisible(false);
                        _initButton.setVisible(true);
                        stepPhase1 = 4;
                        
            }
            else                   //ELIMINO RIGHE RIDONDANTI
            {                
                _comunicazione.setText("The Tableau contains redudant rows. Proceed to delete them.");
                _pivoting1.setText("Delete redudant rows");
                stepPhase1++;                
            }
            return;
        }
        
        if(stepPhase1 == 3)
        {
            Phase1Manager.DeleteRedudantRows(_tabPhase1, _tab.getColumnCount());
            _comunicazione.setText("You can proceed to Phase 2");
            _solution1.deleteBase(Phase1Manager.getTabPhase2(_tabPhase1,_tab.getTableau()[0]).getColonneBase(), _headersCol2);
            _soluzione.setText("Initial Basic Feasible Solution: " + Phase1Manager.getTabPhase2(_tabPhase1,_tab.getTableau()[0]).printSolution());
       //   _soluzione.setText("Initial Basic Feasible Solution:" + _tabPhase1.printSolutionForPhase1(_tab.getColumnCount() -1));
            _pivoting1.setVisible(false);
            _initButton.setVisible(true);
            stepPhase1 = 4;
            return;
        
        }
        
        if(stepPhase1 == 4)
        {
            return;
        
        }
    }//GEN-LAST:event__pivoting1ActionPerformed

    private void _pivoting2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__pivoting2ActionPerformed
        if(stepPhase2 == 0)
        {
            if(_radioBland.isSelected())
                colonnaMem = _tab.doBland();
            else
                colonnaMem = _tab.doDantzig();
            
                _soluzione.setText("");
                rigaMem = _tab.FindPivotRowProva(colonnaMem);
                if(rigaMem < 0)
                {
                    _comunicazione.setText("The feasible region is unbounded!");
                    _pivoting2.setVisible(false);
                }
                else
                {                    
                    frazioneMem = (Frazione) _tab.getValueAt(rigaMem, colonnaMem);
                    _comunicazione.setText("Element " +rigaMem + " of column " + colonnaMem + ". Pivot: " + frazioneMem.toString());
    
                    _tabella.setDefaultRenderer(Object.class, _pivotRenderer);
                    
                    _pivoting2.setText("do pivoting");
                    stepPhase2++;
                }
            
            return;
        } 
        
        if(stepPhase2 == 1)
        {
            _comunicazione.setText("");
            _tab.doPivoting(rigaMem, colonnaMem);
            _solution2.setBase(_tab.getColonneBase(), _headersCol2);
            _pivoting2.setText("search for pivot");
            _tabella.setDefaultRenderer(Object.class, new MyDefaultRenderer());
            if(_tab.isOptimal())
            {
                // _comunicazione.setText("Optimal value found");
                 _soluzione.setText("Optimal Basic Feasible Solution: " + _tab.printSolution());
                _pivoting2.setVisible(false);
                return;
            }
            else
            {
                _soluzione.setText("Current Basic Feasible Solution: " + _tab.printSolution());
                stepPhase2 = 0;
                return;
            }
        } 
        
        if(stepPhase2 == 2)
        {
            
        } // TODO add your handling code here:
    }//GEN-LAST:event__pivoting2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup _buttonGroup;
    private javax.swing.JLabel _comunicazione;
    private javax.swing.JTable _headers;
    private javax.swing.JButton _initButton;
    private javax.swing.JButton _pivoting1;
    private javax.swing.JButton _pivoting2;
    private javax.swing.JRadioButton _radioBland;
    private javax.swing.JRadioButton _radioDantzig;
    private javax.swing.JLabel _soluzione;
    private javax.swing.JTable _tabSolution;
    private javax.swing.JTable _tabella;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
