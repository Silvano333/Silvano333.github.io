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
public class Tableau extends AbstractTableModel{
	
	private Frazione[][] _tableau;
	private int[] _colonneBase;
                
	public Tableau(Frazione[][] t)
	{
                super();
		_tableau = t;
                _colonneBase = new int[t[0].length - 1];
                for(int indice : _colonneBase)
                    indice = 0;
	}
        
        public Frazione[][] getTableau()
        {
            return _tableau;
        }
        
        public void setTableau(Frazione[][] tab)
        {
            _tableau = tab;
        }
        
        public int[] getColonneBase()
        {
            return _colonneBase;
        }
        
        public void setColonneBase(int[] base)
        {
            _colonneBase = base;
        }
        
        
        public void entraColonnaInBase(int row,int column)
        {
            //prima tolgo l'indice riga della colonna che esce di base; poi setto la nuova
             for(int IndicePerColonne = 0; IndicePerColonne < _colonneBase.length; IndicePerColonne++)
            {
                if(_colonneBase[IndicePerColonne] == row)
                       _colonneBase[IndicePerColonne] = 0;
            }
            
            for(int IndicePerColonne = 0; IndicePerColonne < _colonneBase.length; IndicePerColonne++)
            {
                if(IndicePerColonne == column - 1)
                {
                    _colonneBase[IndicePerColonne] = row;
                }
            }
        }
    @Override
        public int getRowCount()
        {
            return _tableau.length;
        }
        
    @Override
        public int getColumnCount()
        {
            return _tableau[0].length;
        }
        
    @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return _tableau[rowIndex][columnIndex];
        }
        
        public Frazione getZeta0()
        {
            return (Frazione) getValueAt(0,0);
        }
	
        @Override
	public String toString()
	{
		String result = "";
		for(Frazione[] riga : _tableau)
		{
			for(Frazione f : riga)
			{
				result = result + f + "\t";
			}
			result = result + "\n"; 
		}
		
		return result;
	}
	
	public int doDantzig()
	{
               int counter = 1;
               boolean foundcj = false;
               Frazione cj = new Frazione(0);
               int columnIndex = 0;
               
               while (counter < _tableau[0].length)
               {
                   if(_tableau[0][counter].compareTo(cj) < 0)
                   {
                       foundcj = true;
                       cj = _tableau[0][counter];
                       columnIndex = counter;
                   }
                   
                   counter++;
               }
               
               if (foundcj == false)
                       return -1;
               else
                   return columnIndex;
	}
	
	public int doBland()
	{
               int columnIndex = 1;
               boolean foundcj = false;
               while (columnIndex < _tableau[0].length && foundcj == false )
               {
                   if(_tableau[0][columnIndex].compareTo(new Frazione(0)) < 0)
                   {
                       foundcj = true;
                       break;
                   }
                   columnIndex++;
               }
               
               if (foundcj == false)
                       return -1;
               else
                   return columnIndex;
	}
	
	 public int FindPivotRow(int columnIndex)
	{
                int row = -1;
		Frazione yij, yi0, teta0; 
		Frazione result = new Frazione(Integer.MAX_VALUE);
		boolean unbounded = true;
                
		for(int i=1; i<_tableau.length; i++) 			// i=1, la riga 0 non la devo considerare
		{
			if(_tableau[i][columnIndex].compareTo(new Frazione(0)) > 0)
			{
				unbounded = false;
                                
				
                                yij = _tableau[i][columnIndex];
				yi0 = _tableau[i][0];
				teta0 = yi0.Divisione(yij);
				
                                
                                if(teta0.compareTo(result) == -1 )
                                {
                                        result = new Frazione(0);
					result = teta0;
                                        row = i;                                     
                                } 
			}
                }
                
		for(int i=1; i<_tableau.length; i++) 			// i=1, la riga 0 non la devo considerare
		{
			if(_tableau[i][columnIndex].compareTo(new Frazione(0)) == 1 )
			{
				unbounded = false;
				
				yij = _tableau[i][columnIndex];
				yi0 = _tableau[i][0];
				teta0 = yi0.Divisione(yij);
				if(teta0.compareTo(result) == -1 )
                                {
					result = teta0;
                                        row = i;
                                }
			}
		}
		
		if(unbounded)
			return -1;				// violata assunzione 3
                else
			return row;		//restituisco  la riga corrispondente al pivot
		
	}  
        
   	 public int FindPivotRowProva(int columnIndex)
	{
                int row = -1;
		Frazione yij, yi0, teta0; 
		Frazione result = new Frazione(Integer.MAX_VALUE);
		boolean unbounded = true;
                
		for(int i=1; i<_tableau.length; i++) 			// i=1, la riga 0 non la devo considerare
		{
                    
			if(_tableau[i][columnIndex].compareTo(new Frazione(0))==1)
			{
                            	unbounded = false;
                                yij = _tableau[i][columnIndex];
                                yi0 = _tableau[i][0];
                                teta0 = yi0.Divisione(yij);
                                row = i;
                                break;
                        }
                }
                if(unbounded)
                    return row;
                else
                {
                    for(int riga=1; riga<_tableau.length; riga++) 			// i=1, la riga 0 non la devo considerare
                    {
			if(_tableau[riga][columnIndex].compareTo(new Frazione(0)) == 1 )
			{
				yij = _tableau[riga][columnIndex];
				yi0 = _tableau[riga][0];
				teta0 = yi0.Divisione(yij);
				if(teta0.compareTo(result) == -1 )
                                {
                                    	result = teta0;
                                        row = riga;
                                }
			}
                    }
                return row;
                
                }

					//restituisco  la riga corrispondente al pivot
		
	
        }
       public void initRigaCosto()
       {
           int row;
            for(int j = 0; j< _colonneBase.length; j++)
            {
                if((_colonneBase[j] !=0) && (_tableau[0][j+1].compareTo(new Frazione(0)) != 0))
                {
                    row = _colonneBase[j];
                    SommaMultiploRiga(0, row, j+1);          
                }        
            }
       }
       
       private void TrasformaRigaPivot(int rowIndex, int columnIndex)
       {
           DividiRigaPerCostante(_tableau[rowIndex], _tableau[rowIndex][columnIndex]);
           fireTableDataChanged();
       }
       
       private static void MoltiplicaRigaPerCostante(Frazione[] riga, Frazione costante)
       {
           for(int j = 0; j < riga.length ; j++)
               riga[j] = riga[j].Moltiplicazione(costante);
       }
       
       private static void DividiRigaPerCostante(Frazione[] riga, Frazione costante)
       {
           for(int j = 0; j < riga.length ; j++)
               riga[j] = riga[j].Divisione(costante);
       }
       
       private void SommaMultiploRiga(int rowIndex, int rigaPivot, int colonnaPivot)        
       {
           Frazione[] temp = new Frazione[_tableau[0].length];
           System.arraycopy(_tableau[rigaPivot], 0 , temp, 0, temp.length);
        
           MoltiplicaRigaPerCostante(temp, _tableau[rowIndex][colonnaPivot]);
           
           for(int j = 0; j < _tableau[rowIndex].length ; j++)
           {
              
               _tableau[rowIndex][j] = _tableau[rowIndex][j].Sottrazione(temp[j]);
           }
           fireTableDataChanged();
       }
       
    public void initPhase1()
    {
        for(int j = 1; j< _tableau[0].length; j++)
        {
            if(_tableau[0][j].compareTo(new Frazione(1)) == 0)
            {
                for(int i = 1; i < _tableau.length ; i++)
                {
                    if(_tableau[i][j].compareTo(new Frazione(1)) == 0)
                        SommaMultiploRiga(0, i, j);
                }
             }        
        }
            
    }
    
    public void doPivoting(int row, int column)
    {
            entraColonnaInBase(row, column);
            for(int i = 0; i< getRowCount(); i++)
            {
                if(i == row)
                    TrasformaRigaPivot(row, column);
            }
         
            for(int i = 0; i< getRowCount(); i++)
            {
                if(i != row)
                    SommaMultiploRiga(i, row, column);
            }
    }
       
    public void DeleteRow(int row)
    {
        Frazione[][] temp = new Frazione[_tableau.length - 1][_tableau[0].length];
        int j = 0;
        for(int i = 0; i<_tableau.length; i++)
        {
            if(i != row)
            {
             System.arraycopy(_tableau[i], 0, temp[j], 0, _tableau[i].length);
             j++;
            }
        }
        _tableau = temp;
        for(int i = 0; i<_colonneBase.length;i++)
        {
            if(_colonneBase[i] > row)
                _colonneBase[i] = _colonneBase[i] - 1;
        }
        fireTableDataChanged();
    }
    
    public String printSolution()
    {
        String solution = "(  ";
        for(int i = 0; i < _colonneBase.length; i++)
        {
            if(_colonneBase[i] == 0)
                solution = solution + "0  ";
            else
                solution = solution + _tableau[_colonneBase[i]][0] + "  ";
        }
        
        solution = solution + ")";

        return solution; 
    }
    
    public boolean isOptimal()
    {
        boolean optimal = true;
        for(int column=1; column< _tableau[0].length; column++)
        {
            if(_tableau[0][column].compareTo(new Frazione(0)) < 0)
                optimal = false;
        }
        
        return optimal;
    }
    
        public String printSolutionForPhase1(int columns)
    {
        String solution = "(  ";
        for(int i = 0; i < _colonneBase.length && i< columns; i++)
        {
            if(_colonneBase[i] == 0)
                solution = solution + "0  ";
            else
                solution = solution + _tableau[_colonneBase[i]][0] + "  ";
        }
        
        solution = solution + ")";

        return solution; 
    }
}

