import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class ChangePassword extends JFrame implements ActionListener
{
	JPanel p1;
	JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
	JTable jt;
	JPasswordField tf1;
	JPasswordField tf2;
	JPasswordField tf3;
	JScrollPane jsp;
	JLabel la1,la2,la3;
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);

	String userID,name;

	
	private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";
  

 public ChangePassword(String un,String fname)
	{
		super("...::::"+fname+ "   IDB LibSys ");
		userID= un;
		name = fname;
        makecomponent();
        setSize(1024,768);
        setResizable(false);
		setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

public void makecomponent()
{
      p1 = new JPanel(){
			public void paintComponent(Graphics g)
			{
				ImageIcon img = new ImageIcon("bg.jpg");
				g.drawImage(img.getImage(), 0, 0, null);
				super.paintComponent(g);
			}
		};
		p1.setOpaque(false);
        p1.setLayout(null);


      /////*******************************\\\\\\
     
      
      la1 = new JLabel("ID  : "+userID+"");
      la1.setBounds(300,20,200,100);
      la1.setForeground(new Color(35,6,255));
      la1.setFont(fo);
      p1.add(la1);

      la2 = new JLabel("Name  : "+name+"");
      la2.setBounds(300,45,500,100);
      la2.setForeground(new Color(35,6,255));
      la2.setFont(fo);
      p1.add(la2);
      
      
      la1 = new JLabel("Password");
      la1.setBounds(330,205,220,40);
      la1.setForeground(new Color(0,255,255));
      la1.setFont(fo);
      p1.add(la1);
      
      la2 = new JLabel("New Password ");
      la2.setBounds(330,245,220,40);
      la2.setForeground(new Color(0,255,255));
      la2.setFont(fo);
      p1.add(la2);
      
      la3 = new JLabel("Confirm Password ");
      la3.setBounds(330,285,220,40);
      la3.setForeground(new Color(0,255,255));
      la3.setFont(fo);
      p1.add(la3);
      
      tf1 = new JPasswordField();
      tf1.setBounds(560,210,170,25);
      tf1.setFont(fo1);
      tf1.setForeground(new Color(255,0,0));
      tf1.setHorizontalAlignment( JTextField.RIGHT);
      p1.add(tf1);
      
      tf2 = new JPasswordField();
      tf2.setBounds(560,250,170,25);
      tf2.setFont(fo1);
      tf2.setForeground(new Color(255,0,0));
      tf2.setHorizontalAlignment( JTextField.RIGHT);
      p1.add(tf2);
      
      tf3 = new JPasswordField();
      tf3.setBounds(560,290,170,25);
      tf3.setFont(fo1);
      tf3.setForeground(new Color(255,0,0));
      tf3.setHorizontalAlignment( JTextField.RIGHT);
      p1.add(tf3);
    
      add(p1);
     
      bt5 = new JButton("Logout");
      bt5.setBounds(880,25,80,30);
      bt5.addActionListener(this);
      p1.add(bt5);
      
       
      bt1 = new JButton("Clear");
      bt1.setBounds(540,350,90,30);
      p1.add(bt1);
      bt1.addActionListener(this);
      
      
      bt6 = new JButton("Change");
      bt6.setBounds(640,350,90,30);
      bt6.addActionListener(this);
      p1.add(bt6);

      bt7 = new JButton("Home");
      bt7.setBounds(790,25,80,30);
      bt7.addActionListener(this);
      p1.add(bt7);
  
    add(p1);


}
public void actionPerformed(ActionEvent e)
 	{
 	if(e.getSource()==bt5)
 		{
 		 StudentLogin ob = new StudentLogin();
 		 this.setVisible(false);
		  System.out.println("Check password");

 	    }
 	if(e.getSource()==bt7)
 		{
 		  Student ob1 = new Student(userID,name);
 		  this.setVisible(false);
		  System.out.println("Home");

 		}

 	if(e.getSource()==bt1)
 		{
  	     tf1.setText("");
  	     tf2.setText("");
  	     tf3.setText("");
 		}
    if(e.getSource()==bt6)
 		{
  	    passwordChange(); 
 		}			
    					    
 	}
 
 public void passwordChange()
 {
 	
  String pw = tf1.getText();
 
  if(CheckPassword(pw))
  {
  String Npw = tf2.getText();  	
  String ConNpw = tf3.getText(); 
     
      if(Npw.equals(ConNpw))
      {
        DatabaseUpdate(Npw);	
      	
      } 
      else
      {
      JOptionPane.showMessageDialog(this,"You Passwords are not match","Error",JOptionPane.ERROR_MESSAGE);	
         tf1.setText("");
  	     tf2.setText("");
  	     tf3.setText("");
      }	
  }	
 else
 {
 JOptionPane.showMessageDialog(this,"You Password is Wrong","Error",JOptionPane.ERROR_MESSAGE);
         tf1.setText("");
  	     tf2.setText("");
  	     tf3.setText("");
			
 }	
 	
 	
 	
 }
 	
 public boolean CheckPassword(String pass)
 {
 	String password = pass;
   try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM student where UserName='"+userID+"' AND password = '"+password+"' ");
		   
		   while(res.next())
		   {
		   	
		    if(userID.equalsIgnoreCase(res.getString(2)) && password.equalsIgnoreCase(res.getString(3)))
			{
				return true;
			}
			else
			{
		    	return false;		
			}		
		 
         
	    }
     }
	      
	  catch(Exception gg)
	  {
//		JOptionPane.showMessageDialog(this,"System failure","Error",JOptionPane.QUESTION_MESSAGE);
		 System.out.println("System failure"+gg);
	  }
return false;	  
 	
 }
 
  public void DatabaseUpdate(String pass)
 {
 	String password = pass;
   try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm1=conn.createStatement();
		  
		   stm1.executeUpdate("UPDATE  student SET password='"+password+"' WHERE UserName  = '"+userID+"' ");
		   
		   
		  System.out.println("Passwordchange");
		  JOptionPane.showMessageDialog(this,"Your Password has been changed","Message",JOptionPane.QUESTION_MESSAGE);
		  
		   StudentLogin ob = new StudentLogin();
 		   this.setVisible(false);
		   System.out.println("Check password");
 
		
	    }
     
	      
	  catch(Exception gg)
	  {
	//	JOptionPane.showMessageDialog(this,"System failure","Error",JOptionPane.QUESTION_MESSAGE);
		 System.out.println("System failure"+gg);
	  }	
  }  
 	
 }		


 
