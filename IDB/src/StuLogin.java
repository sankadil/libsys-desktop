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

 class StudentLogin extends JFrame implements ActionListener
{
	JPanel p1;
	JButton bt1;
	JLabel la1,la2;
	JRadioButton jr1,jr2;
	JTextField tf1;
	JPasswordField tf2,tf3;
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);


    private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";


 public StudentLogin()
	{
		super("Student Login");
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
      bt1 = new JButton("Login");
      bt1.setBounds(540,335,90,30);
      p1.add(bt1);
      bt1.addActionListener(this);

      la1 = new JLabel("User Name");
      la1.setBounds(350,205,120,40);
      la1.setFont(fo);
      p1.add(la1);

      la2 = new JLabel("Password ");
      la2.setBounds(350,245,120,40);
      la2.setFont(fo);
      p1.add(la2);

      tf1 = new JTextField();
      tf1.setBounds(460,210,170,25);
      tf1.setFont(fo1);
      tf1.setHorizontalAlignment( JTextField.RIGHT);
      p1.add(tf1);

      tf2 = new JPasswordField();
      tf2.setBounds(460,250,170,25);
      tf2.setFont(fo1);
      tf2.setHorizontalAlignment( JTextField.RIGHT);
      p1.add(tf2);

      ButtonGroup bg = new ButtonGroup();

      jr1 = new JRadioButton("Loging as Student",true);
      jr1.setBounds(350,290,130,25);
      jr1.setForeground(new Color(255,0,0));
      jr1.setBackground(new Color(104,138,176));
      bg.add(jr1);
      p1.add(jr1);

      jr2 = new JRadioButton("Loging as Lecturer",false);
      jr2.setBounds(495,290,135,25);
      jr2.setForeground(new Color(255,0,0));
      jr2.setBackground(new Color(115,145,179));
      bg.add(jr2);
      p1.add(jr2);

      add(p1);


}

 public void actionPerformed(ActionEvent e)
 	{
 	 if(e.getSource()==bt1)
 		{
		  System.out.println("Check password");
		  login();
 		}
 	}

 	public void login()
 	{
 		boolean userFound = true;

 		String uName = tf1.getText();
 		if(uName.length()<6)
 		{
 		JOptionPane.showMessageDialog(this,"User Name is invalid","Error",JOptionPane.ERROR_MESSAGE);
 		userFound=false;
        }

        String pword = tf2.getText();
 	/*	if(pword.length()==0)
 		{
 		JOptionPane.showMessageDialog(this,"Password is invalid","Error",JOptionPane.ERROR_MESSAGE);
 		userFound=false;
        }
        */
        if(userFound==true)
        {
        	if(jr2.isSelected())
        	{
            System.out.println("Lecture loign");
        	connectDatabaseLecture(uName,pword);
        	}
        	else
        	connectDatabase(uName,pword);
        }

 	}

 public void connectDatabase(String un,String pw)
 {
   String username = un;
   String fullname;
   String password = pw;


   try
	{
		boolean namefound=false ;

		 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();

		 ResultSet res=stm.executeQuery("SELECT * FROM student");


		 while(res.next())
				{
			 	  if(username.equalsIgnoreCase(res.getString(2)))
					{
					 namefound=true;

					fullname = res.getString(1);
                     System.out.println("OK...........");


                     Student ob1 = new Student(username,fullname);
                     this.setVisible(false);

					}
			    }


			  	if(namefound==false)
			    	{
		        	JOptionPane.showMessageDialog(this,"Your user  password is not found","Error",JOptionPane.ERROR_MESSAGE);

			     	}

	}

	catch(Exception ee)
	{

    JOptionPane.showMessageDialog(this,"Cannot Access database","Error",JOptionPane.QUESTION_MESSAGE);
	System.out.println(ee);
	}

 }

 ///////////Connect Lecture Data base////////////

  public void connectDatabaseLecture(String un,String pw)
 {
   String username = un;
   String fullname;
   String password = pw;


   try
	{
		boolean namefound=false ;

		 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();

		 ResultSet res=stm.executeQuery("SELECT * FROM lecturere");


		 while(res.next())
				{
			 	  if(username.equalsIgnoreCase(res.getString(2)) && password.equalsIgnoreCase(res.getString(3)))
					{
					 namefound=true;

					fullname = res.getString(1);
                     System.out.println("OK...........");


                    LectureRecomendation ob1 = new LectureRecomendation(username,fullname);
                     this.setVisible(false);

					}
			    }


			  	if(namefound==false)
			    	{
		        	JOptionPane.showMessageDialog(this,"Your user  password is not found","Error",JOptionPane.ERROR_MESSAGE);

			     	}

	}

	catch(Exception ee)
	{

    JOptionPane.showMessageDialog(this,"Cannot Access database","Server fail",JOptionPane.QUESTION_MESSAGE);
	System.out.println(ee);
	}

 }

 ////////////////////////////////////////////////


 }



public class StuLogin

{
	public static void main(String args[])
	{
	  StudentLogin ob = new StudentLogin();

	}

}