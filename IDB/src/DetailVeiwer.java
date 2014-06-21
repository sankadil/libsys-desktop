import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DetailVeiwer   implements ActionListener
{

 private JButton finish,next,exit,save,back;
 private JPanel jp1;
 private JTextField request,year,city,publisher,isbn,author1,author2,author3,title,id,edition,vender,number_copy,price;
 private String [][] data=new String[100][14];
 private static int i=0, h=0;
//private String [][] edata=new String[100][9];
//private int rnumber;
private ResultSet reset=null;
private Statement stm=null;
         JPanel jp2;
public DetailVeiwer(JPanel jp3)
{
jp2=jp3;
	   // setTitle("Show Author,Title,Book ID");
		//setSize(500,768);
	//	setResizable(true);
	    DetailAdder_gui();
	    db();
	    get_data();
	    //setVisible(true);
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

public static void main(String args[])
{

	//DetailVeiwer form=new DetailVeiwer();

 }










void DetailAdder_gui()
{
	 jp1=new JPanel(){
	public void paintComponent(Graphics n)
	  {
	ImageIcon img = new ImageIcon("bg2.jpg");
	n.drawImage(img.getImage(),0,0,null);
	super.paintComponent(n);
	  }
	};

	jp1.setOpaque(false);
	jp1.setLayout(null);

//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,17);
Color ctf1=new Color(255,255,0);
Color cb=new Color(0,0,255);


//---------------------------------------------------------------------------------------------------------------------------------------



//-----------------JPanel settings-------------------------------------------------------------------

JLabel full_lable=new JLabel("Ordered Books Details");
full_lable.setBounds(10,50,350,25);
full_lable.setFont(f);
full_lable.setForeground(ctf1);
jp1.add(full_lable);

JLabel id_label=new JLabel("Book ID");
id_label.setBounds(10,100,150,25);
id_label.setFont(f);
id_label.setForeground(ctf1);
jp1.add(id_label);

id=new JTextField("");
id.setBounds(150,100,280,25);
id.setEditable(false);
id.setFont(f);
jp1.add(id);

//>>>>>>>>>>>>>>>>>
JLabel title_label=new JLabel("Title");
title_label.setBounds(10,135,150,25);
title_label.setFont(f);
title_label.setForeground(ctf1);
jp1.add(title_label);

title=new JTextField("");
title.setBounds(150,135,280,25);
title.setFont(f);
jp1.add(title);


//>>>>>>>>>>>>>>>>>>>

JLabel a1_label=new JLabel("Author1");
a1_label.setBounds(10,170,150,25);
a1_label.setFont(f);
a1_label.setForeground(ctf1);
jp1.add(a1_label);

author1=new JTextField("");
author1.setBounds(150,170,280,25);
author1.setFont(f);
jp1.add(author1);
//>>>>>>>>>>>>>>>>>>>>>>

JLabel a2_label=new JLabel("Author2");
a2_label.setBounds(10,205,150,25);
a2_label.setFont(f);
a2_label.setForeground(ctf1);
jp1.add(a2_label);

author2=new JTextField("");
author2.setBounds(150,205,280,25);
author2.setFont(f);
jp1.add(author2);
//>>>>>>>>>>>>>>>>>>>.>>>>>>>

JLabel a3_label=new JLabel("Author3");
a3_label.setBounds(10,240,150,25);
a3_label.setFont(f);
a3_label.setForeground(ctf1);
jp1.add(a3_label);

author3=new JTextField("");
author3.setBounds(150,240,280,25);
author3.setFont(f);
jp1.add(author3);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel edition_label=new JLabel("Edition");
edition_label.setBounds(10,275,150,25);
edition_label.setFont(f);
edition_label.setForeground(ctf1);
jp1.add(edition_label);


edition=new JTextField("");
edition.setBounds(150,275,280,25);
edition.setFont(f);
jp1.add(edition);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel isbn_label=new JLabel("ISBN");
isbn_label.setBounds(10,310,150,25);
isbn_label.setFont(f);
isbn_label.setForeground(ctf1);
jp1.add(isbn_label);

isbn=new JTextField("");
isbn.setBounds(150,310,280,25);
isbn.setFont(f);
jp1.add(isbn);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel publisher_label=new JLabel("Publisher");
publisher_label.setBounds(10,345,150,25);
publisher_label.setFont(f);
publisher_label.setForeground(ctf1);
jp1.add(publisher_label);

publisher=new JTextField("");
publisher.setBounds(150,345,280,25);
publisher.setFont(f);
jp1.add(publisher);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel city_label=new JLabel("City");
city_label.setBounds(10,380,150,25);
city_label.setFont(f);
city_label.setForeground(ctf1);
jp1.add(city_label);

city=new JTextField("");
city.setBounds(150,380,280,25);
city.setFont(f);
jp1.add(city);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel year_label=new JLabel("Year");
year_label.setBounds(10,415,150,25);
year_label.setFont(f);
year_label.setForeground(ctf1);
jp1.add(year_label);

year=new JTextField("");
year.setBounds(150,415,280,25);
year.setFont(f);
jp1.add(year);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel request_label=new JLabel("Request");
request_label.setBounds(10,450,150,25);
request_label.setFont(f);
request_label.setForeground(ctf1);
jp1.add(request_label);

request=new JTextField("");
request.setEditable(false);
request.setBounds(150,450,280,25);
request.setFont(f);
jp1.add(request);

JLabel vender_label=new JLabel("Vender");
vender_label.setBounds(10,485,150,25);
vender_label.setFont(f);
vender_label.setForeground(ctf1);
jp1.add(vender_label);

vender=new JTextField("");
vender.setBounds(150,485,280,25);
vender.setFont(f);
jp1.add(vender);

JLabel number_ofcopy_label=new JLabel("Quentity");
number_ofcopy_label.setBounds(10,520,150,25);
number_ofcopy_label.setFont(f);
number_ofcopy_label.setForeground(ctf1);
jp1.add(number_ofcopy_label);

number_copy=new JTextField("");
number_copy.setBounds(150,520,280,25);
number_copy.setFont(f);
jp1.add(number_copy);

JLabel price_label=new JLabel("Price");
price_label.setBounds(10,555,150,25);
price_label.setFont(f);
price_label.setForeground(ctf1);
jp1.add(price_label);

price=new JTextField("");
price.setBounds(150,555,280,25);
price.setFont(f);
jp1.add(price);

//----------------------------------------------------------------------------------------------------
//ImageIcon button_img_finish = new ImageIcon("finish.jpg");

//ImageIcon button_img = new ImageIcon("next.jpg");
next=new JButton("Next>");
next.setBounds(150,625,100,35);
jp1.add(next);
next.setFont(f);
next.setForeground(cb);
next.addActionListener(this);


back=new JButton("<Back");
back.setBounds(330,625,100,35);
jp1.add(back);
back.setFont(f);
back.setForeground(cb);
back.addActionListener(this);


//ImageIcon button_img_exit= new ImageIcon("exit.jpg");
exit=new JButton("Hide");
exit.setBounds(330,50,100,35);
//jp1.add(exit);
exit.setFont(f);
exit.setForeground(cb);
exit.addActionListener(this);

//----------------------------------------------------------------------------------------------------

jp1.setBounds(500,0,524,768);

jp2.add(jp1);


	}

//------------------------Establish-Database connectivity---------------------------------------------------------------------------------------------------------
public void db()
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";
// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();

try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

stm=conn.createStatement();

		}
	 catch(SQLException eq)
		{
		 System.out.println(" \n "+eq);
		}
	 catch(ClassNotFoundException e)
		{
		}
	 finally
		{

		}
}
//------------------------------------finish db conectivity-----------------------------------------------------------------------------------------








//------------------------------setdata---------------------
void set_data()
{


	if(i>h)
	{
id.setText(data[h][0]);
title.setText(data[h][1]);
author1.setText(data[h][2]);
author2.setText(data[h][3]);
author3.setText(data[h][4]);
edition.setText(data[h][5]);
isbn.setText(data[h][6]);
publisher.setText(data[h][7]);
city.setText(data[h][8]);
year.setText(data[h][9]);
request.setText(data[h][10]);
vender.setText(data[h][11]);
price.setText(data[h][12]);
number_copy.setText(data[h][13]);

h++;
}

	}
//---------------------set data finish------------------------------------------------------




//----------------------------------------------------------------------------------------------------------------------------------------------------

void get_data()
{
try{

reset=stm.executeQuery("SELECT bookid,title,author1 ,author2,author3,edition,isbn,publisher,city,year, request,vender,price,copy FROM select_to_order ");

ResultSetMetaData meta=reset.getMetaData(); // data about table head

	 i=0;
	while(reset.next()) // goes from row to row
	{

data[i][0]=reset.getString(1);
data[i][1]=reset.getString(2);
data[i][2]=reset.getString(3);
data[i][3]=reset.getString(4);
data[i][4]=reset.getString(5);
data[i][5]=reset.getString(6);
data[i][6]=reset.getString(7);
data[i][7]=reset.getString(8);
data[i][8]=reset.getString(9);
data[i][9]=reset.getString(10);
data[i][10]=reset.getString(11);
data[i][11]=reset.getString(12);
data[i][12]=reset.getString(13);
data[i][13]=reset.getString(14);
	i++;
    }//while




   System.out.print(i+"\n"+"\n");
 /*    System.out.print("\n"+"\n");

for(int r=0;r<11;r++)
{
	for(int t=0;t<11;t++)
{
System.out.print(data[r][t]+"\n");
}
System.out.print("\n"+"\n");

}
*/


}//try

catch(SQLException eq)
{
System.out.println(" \n "+eq);
}
finally
{

}

	}


//------------------------------------------Action performed method----------------------------------------------------------------------------------
public void actionPerformed(ActionEvent e)
 	{


			if(e.getSource()==next)
			{
				set_data();

				}

			if(e.getSource()==back)
			{
						if(h>1)
						  {
						h--;
						h--;
						get_data();
						set_data();
								   }

				}



		/*	if(e.getSource()==exit)
			{
                 //System.exit(0);
                 //this.setVisible(false);
				}*/

}


}