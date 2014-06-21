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


public class vender_details extends JFrame  implements ActionListener
{
	private String heading[]={"V-ID","Vender name","Address","T.P.No","M.No","Email","Location","Date","web"};
    private String [][] data=new String[100][9];
    private JButton addnew,edit;
    private JTable table;

public vender_details()
{

	    setTitle("logName");
		setSize(1024,768);
		setResizable(true);
		vender_details_gui();
		db();
		setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

public static void main(String args[])
{
	vender_details vender_selecter=new vender_details();

 }


 void vender_details_gui()
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

 table = new JTable(data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(50,50,915,350);
//table.setAutoResizeMode(9);
//table.setDragEnabled(false);


jp1.add(scrollpane);

//----------------------------------------------------------------------------------------------------
ImageIcon button_img_edit = new ImageIcon("edit.jpg");
edit=new JButton(button_img_edit);
edit.setBounds(800,575,200,35);
jp1.add(edit);
edit.addActionListener(this);

ImageIcon button_img = new ImageIcon("addnew.jpg");
addnew=new JButton(button_img);
addnew.setBounds(50,575,200,35);
jp1.add(addnew);
addnew.addActionListener(this);



add(jp1);
 }


//------------------------------------------------------------------------Action listener----------------------------
 public void actionPerformed(ActionEvent e)
 	{


 	    if(e.getSource()==edit)
				    {
                      // new vender_form().remove(new vender_form().finish);
                  //     edit_data();
                  if(table.getSelectedRow()>-1){
                  	vender_form_Editer form=new vender_form_Editer(table.getSelectedRow());
                  	this.setVisible(false);
							}

					else
					   JOptionPane.showMessageDialog(null, "You havent select a row,\tselect a row", "alert", JOptionPane.ERROR_MESSAGE);


					}



					 	    if(e.getSource()==addnew)
									    {
											vender_form form=new vender_form();
											this.setVisible(false);
				                    	}
}



//---------------database connectivity-------------------------------------------------------------------


public void db()
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";
// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();


try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

Statement stm=conn.createStatement();

ResultSet reset=null;

 reset=stm.executeQuery("SELECT vender_id,vender_name,vender_address ,vender_telephone ,vender_mobile ,vender_email ,vender_location ,vender_date , vender_web   FROM vender ");

ResultSetMetaData meta=reset.getMetaData(); // data about table head

int numcol=meta.getColumnCount(); // gets the number of columns of the table
System.out.println();



for(int i=0; i<20; i++)
{

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


i++;

}

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

	//-----------------------------------------------------------------------------------
 }