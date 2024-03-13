/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author mwimola
 */
public class Phase1Manager {
    
    public static Tableau getTabPhase1(Frazione[][] matrice)
    {
        Tableau result;
        
        int equazioni = matrice.length - 1; 
        int variabili = matrice[0].length - 1;
        int colonneInPiu = 0;
        int colonnePres = 0;
        int[] colonneUtili = new int[variabili];
        int[] colonneUtili2;
               
        for(int i = 0; i<colonneUtili.length; i++)
        {
            colonneUtili[i] = 0;
        }
        
        for(int j = 1; j < matrice[0].length; j++)
        {
            int indiceDaRicordare = 0;
            int zeri = 0;
            int count1 = 0;
            
            for(int i = 1; i < matrice.length; i++)
            {
                if(matrice[i][j].compareTo(new Frazione(0)) == 0)
                    zeri++;
                if(matrice[i][j].compareTo(new Frazione(1)) == 0)
                {
                    count1++;
                    indiceDaRicordare = i;
                }
            }

            if(count1 == 1 && (zeri == equazioni - 1))
            {
                boolean rigaPresente = false;
                for(int i = 0; i<colonneUtili.length; i++)
                {
                    if(colonneUtili[i] == indiceDaRicordare)
                        rigaPresente = true;
                }
                
                if(!rigaPresente)
                {
                    colonneUtili[j - 1] = indiceDaRicordare;
                }
            }
            
        }
        
        for(int i = 0; i< colonneUtili.length; i++)
        {
            if(colonneUtili[i] > 0)
                   colonnePres++;
        }
                
        colonneInPiu = equazioni - colonnePres; 
        
        Frazione[][] tabFase1 = new Frazione[matrice.length][matrice[0].length + colonneInPiu];
        Frazione[] tuttizeri = new Frazione[matrice[0].length];
        
        for (int i = 0 ; i < tuttizeri.length; i++)
        {
            tuttizeri[i] = new Frazione(0);
        }
        
        for(int i = 0; i < tabFase1.length ; i++)
        {
                System.arraycopy(matrice[i], 0, tabFase1[i], 0, matrice[i].length);
        }
        
        System.arraycopy(tuttizeri, 0, tabFase1[0], 0, tuttizeri.length);
        
        for (int i = matrice[0].length ; i < tabFase1[0].length; i++)
        {
            tabFase1[0][i] = new Frazione(1);
        }
        
        
        
        colonneUtili2 = new int[tabFase1[0].length -1];
        System.arraycopy(colonneUtili, 0, colonneUtili2, 0, colonneUtili.length);
        
        for(int j = matrice[0].length; j< tabFase1[0].length; j++)
        {
            boolean posto1 = false;
            for(int i = 1; i < matrice.length; i++)
            {
                boolean trovato = false;
                for(int k = 0; k < colonneUtili2.length; k++)
                {
                    if(colonneUtili2[k] == i)
                        trovato = true;
                }
                if(!trovato && !posto1)
                {
                    tabFase1[i][j] = new Frazione(1);
                    posto1 = true;
                    colonneUtili2[j - 1] = i;
                }
                else
                    tabFase1[i][j] = new Frazione(0);
            }
        }
        
        result = new Tableau(tabFase1);
        result.setColonneBase(colonneUtili2);
        
        return result;
    }
    
    public static boolean DoSpecialPivoting(Tableau tabPhase1, int dimTableauPhase2)   //dimTableuPhase2 inteso come numero di colonne
    {
        
        boolean artificialiFuoriBase = false;
        Frazione[] costi = tabPhase1.getTableau()[0];
        
        for(int j = dimTableauPhase2; j<costi.length; j++)
        {
            if(costi[j].compareTo(new Frazione(0)) == 0)
            {
                artificialiFuoriBase = false;
                int rigaIdentità = tabPhase1.getColonneBase()[j-1];
                for(int columnIndex = 1; columnIndex < dimTableauPhase2; columnIndex++)
                {
                   if(costi[columnIndex].compareTo(new Frazione(0)) != 0 && tabPhase1.getTableau()[rigaIdentità][columnIndex].compareTo(new Frazione(0)) != 0)
                   {
                           tabPhase1.doPivoting(rigaIdentità, columnIndex);
                           artificialiFuoriBase = true;
                           break;
                   }
                }
                
            }
        }
    
        return artificialiFuoriBase;
    }
    
    public static void DeleteRedudantRows(Tableau tabPhase1, int dimTableauPhase2)    //dimTableuPhase2 inteso come numero di colonne
    {
        for(int rowIndex = 1; rowIndex < tabPhase1.getRowCount(); rowIndex++)
        {
            boolean tuttiZeri = true;
            for(int columnIndex = 1; columnIndex < dimTableauPhase2; columnIndex++)
            {
                if(tabPhase1.getTableau()[rowIndex][columnIndex].compareTo(new Frazione(0)) != 0)
                    tuttiZeri = false;
            }
            
            if(tuttiZeri)
            {
                tabPhase1.DeleteRow(rowIndex);
                rowIndex = 1;
            }
        }
    
    }
    
    public static boolean verificaVariabiliArtificialiInBase(Tableau tabPhase1, int dimTableauPhase2)
    {
        boolean artificialiInBaseLivelloZero = false;
        for(int columnIndex = dimTableauPhase2 - 1; columnIndex < tabPhase1.getColonneBase().length ; columnIndex++)
        {
            if(tabPhase1.getColonneBase()[columnIndex] != 0)
                artificialiInBaseLivelloZero = true;
        }
        
        return artificialiInBaseLivelloZero;
    }
    
    public static Tableau getTabPhase2(Tableau tab1, Frazione[] costi)
    {
        Frazione[][] matrice = new Frazione[tab1.getRowCount()][costi.length];
        int[] colonnebase = new int[costi.length - 1];
        Tableau tabPhase2 = null;        
        
        matrice[0] = costi;
        for(int riga = 1; riga< tab1.getRowCount(); riga++)
        {
            System.arraycopy(tab1.getTableau()[riga], 0, matrice[riga], 0, matrice[riga].length);
        }
        
        System.arraycopy(tab1.getColonneBase(), 0, colonnebase, 0, colonnebase.length);
        
        tabPhase2 = new Tableau(matrice);
        tabPhase2.setColonneBase(colonnebase);
        
        return tabPhase2;
    }
    
    public static boolean isPhase1Needed(Tableau tabPhase2)
   {
       Tableau temp = Phase1Manager.getTabPhase1(tabPhase2.getTableau());
       if(temp.getColumnCount() == tabPhase2.getColumnCount())
           return false;
       else 
           return true;
   }
    
}
