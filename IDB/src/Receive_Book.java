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


public class Receive_Book extends JFrame  implements ActionListener
{

				public JButton finish,next,exit,save,show;
				public JPanel jp1;
				private JTextField name,address,location,tno,email,web,id,date;
				private JTextArea mno;
				private String [][] data=new String[100][2];
				private int gate=0;
				private String [][] edata=new String[100][9];
				private int rnumber;
				private ResultSet reset=null;
                private Statement stm=null;

public Receive_Book()
{

	    setTitle("Aquaisetion-Enter details when book arrive");
		setSize(1024,768);
		setResizable(true);
		db();
	    Form_gui();
	    setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

public static void main(String args[])
{

	Receive_Book form=new Receive_Book();


 }










void Form_gui()
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

Font f=new Font("Tahoma",Font.BOLD,17);
Color ctf1=new Color(255,255,0);
Color cb=new Color(0,0,255);
Font ff=new Font("Tahoma",Font.BOLD,20);
Font f1=new Font("Tahoma",Font.BOLD,14);


//---------------------------------------------------------------------------------------------------------------------------------------



//-----------------JPanel settings-------------------------------------------------------------------

JLabel full_lable=new JLabel("Enter Book Detail");
full_lable.setBounds(50,50,350,25);
full_lable.setFont(ff);
full_lable.setForeground(ctf1);
jp1.add(full_lable);

JLabel Name_label=new JLabel("Acssetion No:");
Name_label.setBounds(50,100,150,25);
Name_label.setFont(f);
Name_label.setForeground(ctf1);
jp1.add(Name_label);

name=new JTextField("4567");
name.setBounds(220,100,280,25);
name.setFont(f);
jp1.add(name);

//>>>>>>>>>>>>>>>>>
JLabel address_label=new JLabel("Order-ID");
address_label.setBounds(50,135,150,25);
address_label.setFont(f);
address_label.setForeground(ctf1);
jp1.add(address_label);

address=new JTextField("1");
address.setBounds(220,135,280,25);
address.setFont(f);
jp1.add(address);


//>>>>>>>>>>>>>>>>>>>

JLabel location_label=new JLabel("Bill No:");
location_label.setBounds(50,170,150,25);
location_label.setFont(f);
location_label.setForeground(ctf1);
jp1.add(location_label);

location=new JTextField("2500");
location.setBounds(220,170,280,25);
location.setFont(f);
jp1.add(location);
//>>>>>>>>>>>>>>>>>>>>>>

JLabel tno_label=new JLabel("Price");
tno_label.setBounds(50,205,150,25);
tno_label.setFont(f);
tno_label.setForeground(ctf1);
jp1.add(tno_label);

tno=new JTextField("2500");
tno.setBounds(220,205,280,25);
tno.setFont(f);
jp1.add(tno);

//>>>>>>>>>>>>>>>>>>>.>>>>>>>

JLabel mno_label=new JLabel("Date");
mno_label.setBounds(50,240,150,25);
mno_label.setFont(f);
mno_label.setForeground(ctf1);
jp1.add(mno_label);



id=new JTextField("2005.12.31");
id.setBounds(220,240,280,25);
id.setFont(f);
jp1.add(id);

JLabel web_label=new JLabel("Book ID(Order)");
web_label.setBounds(50,275,150,25);
web_label.setFont(f);
web_label.setForeground(ctf1);
jp1.add(web_label);

web=new JTextField("12");
web.setBounds(220,275,280,25);
web.setFont(f);
jp1.add(web);

JLabel id_label=new JLabel("Description");
id_label.setBounds(50,310,150,25);
id_label.setFont(f);
id_label.setForeground(ctf1);
jp1.add(id_label);


mno=new JTextArea("120 books");
mno.setFont(f1);
JScrollPane scrollpane = new JScrollPane(mno);
scrollpane.setBounds(220,310,280,50);
jp1.add(scrollpane);



//----------------------------------------------------------------------------------------------------
//ImageIcon button_img_finish = new ImageIcon("finish.jpg");
finish=new JButton("Save");
finish.setBounds(50,625,100,35);
finish.setFont(f);
finish.setForeground(cb);
jp1.add(finish);
finish.addActionListener(this);

//ImageIcon button_img = new ImageIcon("next.jpg");
next=new JButton("Cancel");
next.setBounds(200,625,100,35);
next.setFont(f);
next.setForeground(cb);
jp1.add(next);
next.addActionListener(this);

//ImageIcon button_img_exit= new ImageIcon("exit.jpg");
exit=new JButton("Clear");
exit.setBounds(350,625,100,35);
exit.setFont(f);
exit.setForeground(cb);
jp1.add(exit);
exit.addActionListener(this);

show=new JButton("More");
show.setBounds(500,625,100,35);
show.setFont(f);
show.setForeground(cb);
jp1.add(show);
show.addActionListener(this);

//----------------------------------------------------------------------------------------------------



add(jp1);


	}




	public void actionPerformed(ActionEvent e)
	 	{


			if(e.getSource()==exit)
			{
                 name.setText("");
                 tno.setText("");
                 id.setText("");
                 mno.setText("");
                address.setText("");
                 web.setText("");
               location.setText("");


			}


			if(e.getSource()==next)
            {

			}


			if(e.getSource()==finish)
			{

  update_data();
  insert_data();
			}


			if(e.getSource()==save)
			{


			}

			if(e.getSource()==show)
			{
          this.repaint();
          DetailVeiwer form=new DetailVeiwer(jp1);
          this.repaint();

			}



	}
//_____________________________________>

void insert_data()
{
	try{


	String query="INSERT INTO  received_books(assno,orderid,billno,price,date,description,bookid) VALUES( '"+name.getText()+"' , '"+address.getText()+"','"+location.getText()+"' , '"+tno.getText()+"' , '"+id.getText()+"' , '"+web.getText()+"' , '"+mno.getText()+"' );";
	System.out.print("insert query \n"+query);
	stm.executeUpdate(query);
	}


	catch(SQLException eq)
	{
	System.out.println(" \n "+eq);
	JOptionPane.showMessageDialog(null, "Data Update failed.Book ID is does not exist", "alert", JOptionPane.ERROR_MESSAGE);

	}
	finally
	{
	}



	}

//------------------------------------------edit and update data-------------------------------------------------------
void update_data()
{

try
{
String query1="Update select_to_order set status='1', orderid='"+address.getText()+"' WHERE bookid='"+web.getText()+"'";
System.out.println("\n"+query1+"\n");
stm.executeUpdate(query1);
JOptionPane.showMessageDialog(null, "Data Update successfully", "alert", JOptionPane.ERROR_MESSAGE);

}

catch(SQLException eq)
{
System.out.println(" \n "+eq);
JOptionPane.showMessageDialog(null, "Data Update failed.Book ID is does not exist", "alert", JOptionPane.ERROR_MESSAGE);

}
finally
{
}

	}
//----------------------------------------edit and updated succsesfully--------------------------------------------------------------------------------

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







}