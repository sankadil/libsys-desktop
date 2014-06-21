import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.*;


public class OrderList extends JFrame  implements ActionListener
{

		private JButton save,back,mail;
		private JTextArea order;
		private String filename;
		private String s=" ", s1, str, str1;
		private String line="";
		private static ResultSet reset=null, reset1=null;
		private Statement stm=null;
		private static String vendername,email_address,orderid;
		private JTextPane jtp;
		private JPanel jp1;
	//	private String [][]rest_data=new String[1000][7];
	//	private String []selected_data=new String[200];
	//	private String heading[]={"Title","Author","Edition","ISBN","Publisher","Quentity","Price"};















    public OrderList(String x)
    {
vendername=x;
//reset= pass_ref;
setTitle(" Email ");
setSize(1024,768);
setResizable(true);
db();
OrderList_gui();
set_email();
set_data();
db_vender();
setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public OrderList(String x,String y)
    {
vendername=x;
orderid=y;
//reset= pass_ref;
setTitle(" Email ");
setSize(1024,768);
setResizable(true);
db_to_history();
OrderList_gui();

set_data();
db_vender();
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

     void OrderList_gui()
     {


    jp1=new JPanel(){
    public void paintComponent(Graphics n)
      {
    ImageIcon img = new ImageIcon("bg3.jpg");
    n.drawImage(img.getImage(),0,0,null);
    super.paintComponent(n);
      }
    };

    jp1.setOpaque(false);
    jp1.setLayout(null);

    //-----------------------------------------set font class--------------------------------------------------------------------------------

    Font f=new Font("Tahoma",Font.BOLD,14);
    Color ctf1=new Color(255,255,0);
    Font tf=new Font("Tahoma",Font.BOLD,14);
    Color cb=new Color(0,0,255);

    //---------------------------------------------------------------------------------------------------------------------------------------

    save=new JButton("Save");
    save.setBounds(850,600,100,35);
    jp1.add(save);
    save.setFont(f);
    save.setForeground(cb);
    save.addActionListener(this);

    back=new JButton("Back");
    back.setBounds(700,600,100,35);
    jp1.add(back);
    back.setFont(f);
    back.setForeground(cb);
    back.addActionListener(this);

    mail=new JButton("Email");
    mail.setBounds(550,600,100,35);
    mail.setFont(f);
    mail.setForeground(cb);
    mail.addActionListener(this);

//??????????????????????????????????????????????????????????
	order=new JTextArea();
	JScrollPane jsp1=new JScrollPane(order);
	jsp1.setBounds(10,10,994,550);
	//jp1.add(jsp1);
	order.setFont(f);
	//order.setEditable(false);
	Color c=new Color(0,192,255);
	order.setForeground(c);
//?????????????????????????????????????????????????????????????


//?????????????????????????????????????textPane>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		jtp = new JTextPane();





//?????????????????????????????????????>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    add(jp1);


}

public void set_email()
{

	 jp1.add(mail);
	}






//----------------------------------------------------------------------------------------------------------------------------------------------
public void set_data()
{
	//"Title","Author","Edition","ISBN","Publisher","Quentity","Price"
//String format1="\t\t\t\t  Book Order \nIDB Lybrary System,\n  Katubedda,\n   Moratuwa\n\n";
//String format2="  Title\t\t Author\t\t Edition\t ISBN\t Publisher\t Quentity\t Price\n";
//String format3="";

String format1="\t\t\t\t  Book Order \n";
String format2="  Title\t\t Author\t\t Edition\t ISBN\t Publisher\t Quentity\t Price\n";
String format3="";
int amount=0;

try{
					while(reset.next())
					{
String space="\t";
//-----------------------------------------------------
if( (reset.getString(2)).length()>=11 )
{
	space="";
			// System.out.println("	space>=below not");

}
//-----------------------------------------------------
else if((reset.getString(2)).length()<8)
{
space="\t";
 //System.out.println("	space=8<below");
}
//----------------------------------------------------
 format3=format3+"\n"+reset.getString(1)+"\t\t"+reset.getString(2)+space+reset.getString(3)+"\t"+reset.getString(4)+"\t"+reset.getString(5)+"\t"+reset.getString(6)+"\t"+reset.getString(7);

 amount= amount+Integer.parseInt(reset.getString(6))*Integer.parseInt(reset.getString(7));

					}
					System.out.println(format3);
}

	 catch(SQLException eq)
		{
		 System.out.println(" \n "+eq);
		}


String format4="\n\n\t\t\t\t\t\t\tTotal price =Rs  "+amount+" /=";

//db_vender();
String format5="\n\n\t\tTo-email address-"+email_address;
String format=format1+format2+ format3+format4+format5;

	order.setText(format);


//OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO

	try{
	    StyledDocument doc=jtp.getStyledDocument();
            Style style=doc.addStyle("Red Underline",null);

//---------------------------------------------------------------------
			StyleConstants.setForeground(style,new Color(0,0,255));
			//StyleConstants.setUnderline(style,true);
			StyleConstants.setFontSize(style, 24);
			doc.insertString(doc.getLength(),format1,style);
//---------------------------------------------------------------------
            Style style1=doc.addStyle("Red Underline",null);
         StyleConstants.setForeground(style1,new Color(0,0,255));
            StyleConstants.setUnderline(style,true);
             StyleConstants.setFontSize(style1, 20);

            doc.insertString(doc.getLength(),format2,style1);
//----------------------------------------------------------------------


			Style style2=doc.addStyle("14pts",null);
			StyleConstants.setForeground(style2,new Color(255,0,0));
			StyleConstants.setFontSize(style2, 14);
			doc.insertString(doc.getLength(),format3+format4,style2);//parent style

            jtp.setStyledDocument(doc);
            jtp.setBounds(10,10,994,550);

            jp1.add(jtp);
	}

catch( Exception e){
System.out.println(e);
}



//OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO

	}
	//----------------------------------------------------------------------------------------------------------------------------------------------










public void actionPerformed(ActionEvent e)
 	{


			if(e.getSource()==save)
			{

				   		try {
				     			FileDialog fd=new FileDialog(this,"Save As",FileDialog.SAVE);
				     			fd.setVisible(true);
				     			s=fd.getFile();
				     			filename=s;
				     			str1=fd.getDirectory()+fd.getFile();

						     	FileWriter fw=new FileWriter(str1);
						     	StringReader sr=new  StringReader(jtp.getText());
						     	BufferedReader br=new BufferedReader(sr);
						     	String lr="";

						     	while((lr=br.readLine())!=null) {
						      	fw.write(lr+"\r\n");
						     }
						     fw.close();
				       	}

				        catch(Exception pe){

						   	setTitle("UNTITLED.txt");
		}
			}

						if(e.getSource()==back)
			{

			       this.setVisible(false);

			}

						if(e.getSource()==mail)
			{
				try
				{

String message=jtp.getText();
String receiver=email_address;
MailtoVender mailutils = new MailtoVender();
mailutils.sendMail("subject", message, "idbkatubadda@gmail.com",receiver );

			}
			catch(Exception mailexce)
			{
			System.out.println("Cannot find server !");
				}

				}


	}

//--------------------------------------------------------------------------------------------------------------------------------------------
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


reset=stm.executeQuery("SELECT title,author1,edition,isbn,publisher,copy,price FROM select_to_order where status='0' and vender='"+vendername+"'");



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
//-------------------------------------------------------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------------------------------------------------
public void db_to_history()
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";
// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();

try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

stm=conn.createStatement();


reset=stm.executeQuery("SELECT title,author1,edition,isbn,publisher,copy,price FROM select_to_order where status='1' and vender='"+vendername+"' and orderid='"+orderid+"'");



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
//-------------------------------------------------------------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------------------------------------------------------------
public void db_vender()
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";
// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();

try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

Statement stm2=conn.createStatement();


//reset=stm.executeQuery("SELECT title,author1,edition,isbn,publisher,copy,price FROM select_to_order where status='0' and vender='"+vendername+"'");
 reset1=stm.executeQuery("SELECT  vender_email FROM vender where vender_name='"+vendername+"'");
 					while(reset1.next()) // goes from row to row
					{

email_address=reset1.getString(1);
System.out.println("\n\n\n\n\n"+email_address+"\n\n\n\n\n");
				}

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
//-------------------------------------------------------------------------------------------------------------------------------------------------





	public static void main(String args[])
	{
		OrderList orderlist=new OrderList((String)(new Order_setings().vendername_selecter).getSelectedItem());

    }
	}
