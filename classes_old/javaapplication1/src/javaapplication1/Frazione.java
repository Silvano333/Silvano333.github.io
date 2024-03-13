/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author mwimola
 */

public class Frazione {
	
	private int num, den;

	public Frazione(int n, int d)
	{	
		 if(d < 0)
		 {
			 n = -n;
			 d = -d;
		 }
		 
		 this.setNum(n);
		 this.setDen(d);
		 this.Semplifica();
	}

	public Frazione(int n)
	{
		this.setNum(n);
		this.setDen(1);
		this.Semplifica();
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

	public int getDen() {
		return den;
	}

	public void setDen(int den) {
		this.den = den;
	}

	public Frazione Somma(Frazione b)
	{ 	
		Frazione r=new Frazione(0,1);
		int m;
		m=mcm(this.getDen(),b.getDen());			//trovo mcm per sapere il denominatore
		r.setDen(m);
		r.setNum((((m/this.getDen())*this.getNum())+((m/b.getDen())*b.getNum()))); //calcolo il numeratore
		r.Semplifica();									   //utilizzo il metodo semplifica per Ridurre, se possibile, la frazione
		return r;
	}

	public Frazione Sottrazione(Frazione b)
	{		
		Frazione r=new Frazione(0,1);
		int m;
		m=mcm(this.getDen(),b.getDen());			//trovo mcm per sapere denominatore
		r.setDen(m);
		r.setNum((((m/this.getDen())*this.getNum())-((m/b.getDen())*b.getNum())));	//calcolo numeratore
		r.Semplifica();										//semplifico la frazione
		return r;
	}

	public Frazione Divisione(Frazione b)
	{	
	 	Frazione r=new Frazione(0,1);
	 	if(b.getNum() >= 0)
	 	{
	 		r.setNum(this.getNum() * b.getDen());		//moltiplico il numeratore della prima per il denominatore della seconda frazione
	 		r.setDen(this.getDen() * b.getNum());	//moltiplico il denominatore della prima per il numeratore della seconda frazione
	 	}
	 	else
	 		{
	 			int temp = (- b.getNum());
	 			r.setNum(-(this.getNum() * b.getDen()));
	 			r.setDen(this.getDen() * temp);
	 		}
	 	r.Semplifica();
										//semplifico
		return r;
	}

	public Frazione Moltiplicazione(Frazione b)
	{ 
		Frazione r =new Frazione(0,1);
		r.setNum(this.getNum() * b.getNum());		//moltiplico il numeratore della prima per il numeratore della seconda frazione
		r.setDen(this.getDen() * b.getDen());		//moltiplico il denominatore della prima per il denominatore della seconda frazione

		r.Semplifica();					//semplifico
	
		return r;
	}


	public String toString() 
	{
		if (this.getDen() == 1)
			return this.getNum() + "";
		else
			return this.getNum() + "/" + this.getDen();
	}


	public boolean equals(Frazione a) {
		this.Semplifica();
		a.Semplifica();
		return (this.getNum()==a.getNum()) && (this.getDen()==a.getDen());
	}

	public int compareTo(Frazione a)
	{
		a.Semplifica();
		int denCom = mcm(this.getDen(),a.getDen());
		int numNorm1 = this.getNum()*(denCom/this.getDen());
		int numNorm2 = a.getNum()*(denCom/a.getDen());
		if (numNorm1 == numNorm2) 
			return 0;
		else 
			if (numNorm1 < numNorm2) 
				return -1;
		else 
			return 1;
	}

	 private static int MCD(int a,int b)	  //calcola il massimo comun divisore
         {		
	 	int r= b;
	 	while(a%b!=0){
	 		r=a%b;
	 		if(r==0)
	 			r=b;
	 		else {
	 			a=b;
	 			b=r;
	 			}
	 	}
	 	return Math.abs(r);
	 }

	 private static int mcm(int a, int b)     //calcola il minimo comun multiplo
	 {
		 int mcm2;
		 mcm2=(a*b)/MCD(a,b);
		 return mcm2;
	}

	private void Semplifica() 		//metodo che semplifica le frazioni
	{
		int m=MCD(this.getNum(),this.getDen());
		this.setNum(this.getNum() / m);
		this.setDen(this.getDen() / m);
	}

}