package controllers;

import metier.Catalogue;
import metier.I_Catalogue;

public class ctrl_stock 
{
	static I_Catalogue leCata = Catalogue.getInstance();
	 public static  String AfficherStock()
	 {
		 return leCata.toString();
	 }
	 
}
