package Spillprosjekt;

import java.awt.Graphics;
import java.awt.Rectangle;

// Maa Extende Rectangle for aa gjore det lettere aa tegne i "draw"
public class Tower extends Rectangle{
	
	
//	Skyter naar timeren i "physics" kaller metoden
	private void shoot(){}
	
//	Antar du mener naar det plasseres? :) Vi maa vaere flinke til aa kommentere hvis de andre skal forstaa
//	public Tower();
	public void create(){}
	
//	Legge til en komponent?
	public void addComponent(Object component){}
	
//	Tar imot et grafikkobjekt og tegner taarnet
	public void draw(Graphics g){}
	
//	Alt av timere ol skal kjores fra denne (vil kalles av gameloopen)
	public void physics(){}
}