
/*
cd C:\wamp\mysql\bin
mysql -h localhost -u root
CopyRight by Pramuka
Author Sanka Dilmadu Senvirathna
E-mail  sankadilmadu@gmail.com/pramuka1@gmail.com
2007.11.13
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.table.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Opac extends JFrame
{

private JTabbedPane jTabbedPane1;
public  static Opac l;


public Opac()
{
super("OPAC-Open Public Access Catalog");
setSize(1024,768);
setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
setVisible(true);

	}





public static void main(String args[])
{
   l=new Opac();
   l.conectall();


	}



public void conectall()
{

jTabbedPane1 = new JTabbedPane();
add(jTabbedPane1);

Searching pass_searching=new Searching();
pass_searching.guimethodSearch(jTabbedPane1,l);

LybAdvance pass_object=new LybAdvance();
pass_object.guiadsearch(jTabbedPane1,l);

LybAditional pass_aditional=new LybAditional();
pass_aditional.guiaditional(jTabbedPane1,l);

Browsing pass_browsing= new Browsing();
pass_browsing.guimethodBrowsing( jTabbedPane1,l);

Isbn pass_isbn=new Isbn();
pass_isbn.guimethodIsbn(jTabbedPane1,l);


	}



}