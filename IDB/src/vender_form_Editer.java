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


public class vender_form_Editer extends JFrame  implements ActionListener
{

		public JButton back,exit;
		private JPanel jp1;
		private JTextField name,address,location,tno,mno,email,web,id,date;
		private String [][] data=new String[100][2];
		private int gate=0;
		private String [][] edata=new String[100][9];
		private static int rnumber=0;

public vender_form_Editer (int rownumber)
{
rnumber=rownumber;
	    setTitle("Vender Details");
		setSize(1024,768);
		setResizable(true);
	    Form_gui();
	    editting(rnumber);
	    setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

public static void main(String args[])
{

	vender_form_Editer form=new vender_form_Editer(rnumber);

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


//---------------------------------------------------------------------------------------------------------------------------------------



//-----------------JPanel settings-------------------------------------------------------------------

JLabel full_lable=new JLabel("Enter Vender Detail");
full_lable.setBounds(50,50,350,25);
full_lable.setFont(f);
full_lable.setForeground(ctf1);
jp1.add(full_lable);

JLabel Name_label=new JLabel("Name");
Name_label.setBounds(50,100,150,25);
Name_label.setFont(f);
Name_label.setForeground(ctf1);
jp1.add(Name_label);

name=new JTextField("Blackwell");
name.setBounds(300,100,280,25);
name.setFont(f);
jp1.add(name);

//>>>>>>>>>>>>>>>>>
JLabel address_label=new JLabel("Address");
address_label.setBounds(50,135,150,25);
address_label.setFont(f);
address_label.setForeground(ctf1);
jp1.add(address_label);

address=new JTextField("1/28,gawapalace,London");
address.setBounds(300,135,280,25);
address.setFont(f);
jp1.add(address);


//>>>>>>>>>>>>>>>>>>>

JLabel location_label=new JLabel("Location");
location_label.setBounds(50,170,150,25);
location_label.setFont(f);
location_label.setForeground(ctf1);
jp1.add(location_label);

location=new JTextField("London");
location.setBounds(300,170,280,25);
location.setFont(f);
jp1.add(location);
//>>>>>>>>>>>>>>>>>>>>>>

JLabel tno_label=new JLabel("T.No:");
tno_label.setBounds(50,205,150,25);
tno_label.setFont(f);
tno_label.setForeground(ctf1);
jp1.add(tno_label);

tno=new JTextField("0786173455");
tno.setBounds(300,205,280,25);
tno.setFont(f);
jp1.add(tno);
//>>>>>>>>>>>>>>>>>>>.>>>>>>>

JLabel mno_label=new JLabel("M.No:");
mno_label.setBounds(50,240,150,25);
mno_label.setFont(f);
mno_label.setForeground(ctf1);
jp1.add(mno_label);

mno=new JTextField("Blackwell");
mno.setBounds(300,240,280,25);
mno.setFont(f);
jp1.add(mno);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel email_label=new JLabel("E-mail");
email_label.setBounds(50,275,150,25);
email_label.setFont(f);
email_label.setForeground(ctf1);
jp1.add(email_label);


email=new JTextField("Blackwel@gmail.com");
email.setBounds(300,275,280,25);
email.setFont(f);
jp1.add(email);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel web_label=new JLabel("Web");
web_label.setBounds(50,310,150,25);
web_label.setFont(f);
web_label.setForeground(ctf1);
jp1.add(web_label);

web=new JTextField("www.Blackwell.com");
web.setBounds(300,310,280,25);
web.setFont(f);
jp1.add(web);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel id_label=new JLabel("ID");
id_label.setBounds(50,345,150,25);
id_label.setFont(f);
id_label.setForeground(ctf1);
jp1.add(id_label);

id=new JTextField("lyb-bw-001");
id.setBounds(300,345,280,25);
id.setFont(f);
jp1.add(id);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

JLabel date_label=new JLabel("Date");
date_label.setBounds(50,380,150,25);
date_label.setFont(f);
date_label.setForeground(ctf1);
jp1.add(date_label);

date=new JTextField("2007-11-18");
date.setBounds(300,380,280,25);
date.setFont(f);
jp1.add(date);


//----------------------------------------------------------------------------------------------------

ImageIcon button_img = new ImageIcon("next.jpg");
back=new JButton("back");
back.setBounds(450,625,200,35);
jp1.add(back);
back.setFont(f);
back.setForeground(cb);
back.addActionListener(this);

ImageIcon button_img_exit= new ImageIcon("exit.jpg");
exit=new JButton("update");
exit.setBounds(750,625,200,35);
jp1.add(exit);
exit.setFont(f);
exit.setForeground(cb);
exit.addActionListener(this);

//----------------------------------------------------------------------------------------------------



add(jp1);


	}




	public void actionPerformed(ActionEvent e)
	 	{
	 	    if(e.getSource()==exit)
	 	    {

				update();
				 }
	 	    if(e.getSource()==back)
	 	    {
	vender_details vender_selecter=new vender_details();
				this.setVisible(false);
				 }

                  }



void update()
{
			     String driver="com.mysql.jdbc.Driver";
				 String url="jdbc:mysql://localhost/student";
			// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();


			try
			{
			Class.forName(driver);

			Connection conn2=DriverManager.getConnection(url,"root","");

			Statement stm2=conn2.createStatement();

//String query="INSERT INTO vender VALUES"
	//ResultSet reset2=stm2.executeQuery("SELECT vender_id,vender_name,vender_address ,vender_telephone ,vender_mobile ,vender_email ,vender_location ,vender_date , vender_web   FROM vender ");

String query1="Update vender set  vender_id='"+id.getText()+"' , vender_name='"+name.getText()+"' ,vender_address='"+address.getText()+"',vender_telephone='"+tno.getText()+"' ,vender_mobile='"+mno.getText()+"' ,vender_email='"+email.getText()+"' ,vender_location='"+location.getText()+"' ,vender_date ='"+date.getText()+"' ,vender_web= '"+web.getText()+"' WHERE vender_id ='"+edata[rnumber][0]+"'";

//String query1="Update vender set   vender_name='"+name.getText()+"' ,vender_address='"+address.getText()+"' ,vender_location='"+location.getText()+"',vender_telephone='"+tno.getText()+"' ,vender_mobile='"+mno.getText()+"' ,vender_email='"+email.getText()+"' ,vender_web= '"+web.getText()+"' ,vender_date ='"+date.getText()+"vender_id='"+id.getText()+"' WHERE vender_id ='"+edata[rnumber][0]+"'";

System.out.println("\n"+query1+"\n");
stm2.executeUpdate(query1);

	JOptionPane.showMessageDialog(null, "Edit successfully,\n \t Saved your data", "alert", JOptionPane.ERROR_MESSAGE);

             }

		 catch(SQLException eq)
			{
			 System.out.println(" \n "+eq);
	JOptionPane.showMessageDialog(null, "mission failed,\n \t ID is exist \n "+eq, "alert", JOptionPane.ERROR_MESSAGE);

			}
		 catch(ClassNotFoundException e)
			{
			}
		 finally
			{

			}





	}








//-------------------------------------------------editing data of database--------------------------------------------------------------------------


public void editting(int rownumber)
{
//this.remove(finish);
//this.repaint();
rnumber=rownumber;
		 String driver="com.mysql.jdbc.Driver";
			 String url="jdbc:mysql://localhost/student";
		// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();


		try
		{
		Class.forName(driver);

		Connection conn1=DriverManager.getConnection(url,"root","");

		Statement stm1=conn1.createStatement();





	//----------validating data is exist or not----------------------------------------------------------------------------------------

	ResultSet reset1=stm1.executeQuery("SELECT vender_id,vender_name,vender_address ,vender_telephone ,vender_mobile ,vender_email ,vender_location, vender_web ,vender_date    FROM vender ");
	ResultSetMetaData meta=reset1.getMetaData();


	int i=0;
	while(reset1.next()) // goes from row to row
	{

edata[i][0]=reset1.getString(1);
edata[i][1]=reset1.getString(2);
edata[i][2]=reset1.getString(3);
edata[i][3]=reset1.getString(4);
edata[i][4]=reset1.getString(5);
edata[i][5]=reset1.getString(6);
edata[i][6]=reset1.getString(7);
edata[i][7]=reset1.getString(8);
edata[i][8]=reset1.getString(9);

	i++;

    }
                                             id.setText(edata[rownumber][0]);
                                             name.setText(edata[rownumber][1]);
	                                         address.setText(edata[rownumber][2]);
	                                         tno.setText(edata[rownumber][3]);
	                                         mno.setText(edata[rownumber][4]);
	                                         email.setText(edata[rownumber][5]);
	                                         location.setText(edata[rownumber][6]);
	                                         web.setText(edata[rownumber][6]);
                                             date.setText(edata[rownumber][8]);





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








}