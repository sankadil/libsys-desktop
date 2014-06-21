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












public class Order_history extends JFrame  implements ActionListener
{
	private String heading[]={"Order-ID","Title","Author","Edition","ISBN","Publisher","Quentity","Price"};
    private String [][]rest_data=new String[1000][8];
   private String []selected_data=new String[200];
   private String []selected_data2=new String[200];

    private JButton vote,recomand,select,edit;
    private JTable table;
    public JComboBox vendername_selecter,orderid_selecter;
    private ResultSet reset=null;
    private Statement stm=null;



public Order_history()
{

	    setTitle("Order History - Select by order number and vender");
		setSize(1024,768);
		setResizable(true);
		 db();
		Order_history_gui();
		 select_vender();
		 setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


 }




public static void main(String args[])
{
	Order_history vender_selecter=new Order_history();

 }









 void Order_history_gui()
 {
	JPanel jp1=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg3.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp1.setOpaque(false);
jp1.setLayout(null);



//-----------------JPanel settings-------------------------------------------------------------------

//-----------------JPanel settings-------------------------------------------------------------------
//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,14);
Color ctf1=new Color(255,255,0);
Font tf=new Font("Tahoma",Font.BOLD,14);
Color cb=new Color(0,0,255);

//---------------------------------------------------------------------------------------------------------------------------------------

 table = new JTable(rest_data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(50,100,915,300);
table.setFont(f);
//table.setAutoResizeMode(9);
//table.setDragEnabled(false);


jp1.add(scrollpane);

//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------

edit=new JButton("Edit");
edit.setBounds(400,600,100,35);
edit.setFont(f);
edit.setForeground(cb);
//jp1.add(edit);
edit.addActionListener(this);

//ImageIcon button_img_v = new ImageIcon("edit.jpg");

vote=new JButton("Print");
vote.setBounds(550,600,100,35);
vote.setFont(f);
vote.setForeground(cb);
jp1.add(vote);
vote.addActionListener(this);

//ImageIcon button_imgr = new ImageIcon("addnew.jpg");
recomand=new JButton("Save");
recomand.setBounds(700,600,100,35);
recomand.setFont(f);
recomand.setForeground(cb);
jp1.add(recomand);
recomand.addActionListener(this);


//ImageIcon button_imgs = new ImageIcon("addnew.jpg");
select=new JButton("Show");
select.setBounds(850,600,100,35);
jp1.add(select);
select.setFont(f);
select.setForeground(cb);
select.addActionListener(this);


//----------------------------------------------------------------------------------------------------

JLabel jl3=new JLabel("            vender name");
jl3.setBounds(50,30,200,25);
jl3.setFont(f);
jl3.setForeground(ctf1);
jp1.add(jl3);

 vendername_selecter =new JComboBox(selected_data);
vendername_selecter.setBounds(50,60,200,25);
vendername_selecter.setFont(f);
jp1.add(vendername_selecter);
vendername_selecter.addActionListener(this);

JLabel jl4=new JLabel("            Order-ID");
jl4.setBounds(650,30,200,25);
jl4.setFont(f);
jl4.setForeground(ctf1);
jp1.add(jl4);

 orderid_selecter =new JComboBox(selected_data2);
orderid_selecter.setBounds(650,60,200,25);
orderid_selecter.setFont(f);
jp1.add(orderid_selecter);
orderid_selecter.addActionListener(this);

add(jp1);

 }

//---------------------------------------------------------------------------------------------------------------------------------------------
public void actionPerformed(ActionEvent e)
 	{


			if(e.getSource()==select || e.getSource()==vendername_selecter || e.getSource()==orderid_selecter)
			{

			    clear();
				select_vender();
				repaint();
			}
			if(e.getSource()==edit)
			{

//	DetailAdder form=new DetailAdder();

			}

			if(e.getSource()==recomand)
			{
				OrderList orderlist=new OrderList((String)vendername_selecter.getSelectedItem(),(String)orderid_selecter.getSelectedItem());

			}
				if(e.getSource()==vote)
			{
			printn();
		}
   }
//--------------------------------------------------------------------------------------------------------------------------------------------






//-----------------------------------------------------------------------------------------------------------------------------------------
public void select_vender()
{

try{
String query="SELECT bookid,title,author1 ,edition,isbn,publisher,copy,price FROM select_to_order where status='1' and vender='"+vendername_selecter.getSelectedItem()+"'"+"and orderid='"+orderid_selecter.getSelectedItem()+"'";
 System.out.println(query);
 reset=stm.executeQuery(query);

// reset=stm.executeQuery("SELECT distinct vender FROM select_to_order");

ResultSetMetaData meta=reset.getMetaData(); // data about table head

					int i=0;
					while(reset.next()) // goes from row to row
					{


					rest_data[i][0]=reset.getString(1);
					rest_data[i][1]=reset.getString(2);
					rest_data[i][2]=reset.getString(3);
					rest_data[i][3]=reset.getString(4);
					rest_data[i][4]=reset.getString(5);
					rest_data[i][5]=reset.getString(6);
					rest_data[i][6]=reset.getString(7);
					rest_data[i][7]=reset.getString(8);



					i++;

					}




}

								catch(SQLException eq)
								{
								System.out.println(" \n "+eq);
								}

								finally
								{

								}




	}

//------------------------------------------------------------------------------------------------------------------------------------------

public void printn()
{


try
{
table.print();
}

catch(Exception e)
{

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

 reset=stm.executeQuery("SELECT distinct vender FROM select_to_order");
// reset1=stm.executeQuery("SELECT distinct orderid FROM select_to_order");

ResultSetMetaData meta=reset.getMetaData(); // data about table head

					int i=0;
					while(reset.next()) // goes from row to row
					{

selected_data[i]=reset.getString(1);


					i++;

					}



ResultSet reset2=stm.executeQuery("SELECT distinct orderid FROM select_to_order");
// reset1=stm.executeQuery("SELECT distinct orderid FROM select_to_order");

//ResultSetMetaData meta=reset2.getMetaData(); // data about table head

					int j=0;
					while(reset2.next()) // goes from row to row
					{


selected_data2[j]=reset2.getString(1);

					j++;

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

public void clear()
{
	for(int i=0;i<1000;i++)
	{
					rest_data[i][0]="";
					rest_data[i][1]="";
					rest_data[i][2]="";
					rest_data[i][3]="";
					rest_data[i][4]="";
					rest_data[i][5]="";
					rest_data[i][6]="";
					rest_data[i][7]="";

}

	}

//------------------------------------------------------------------------------------------------------------------------------------------------

 }