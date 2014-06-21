import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

 class IssueBook extends JFrame implements ActionListener,KeyListener
{
	JPanel p1,p2;
	JButton bt1,bt2,bt3,bt12,bt22,bt4;
	ImageIcon i1;
	/////////////////////////////
    JLabel la1,la2,la3,la4,la5;
    JLabel la6,la7,la8,la9,la10,img1;
    JLabel lab4,lab5,lab6,lab7,lab8,lab9,lab10;
	JTextField tf1,tf2,tf3,tf4;
	JTextField tf6,tf7,tf8,tf9,tf10;
	JTextField tfb1,tfb2,tfb3,tfb4,tfb5;
	JTextArea tf5;
	////////////////////////////
	
	////////////////////////////
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);
	String StuID;
	String StuName;
	int Maximum_Book;
	Double max_fine;
	Double fine;
	boolean TF2Set;
	boolean TF1Set=true;
	int Book_has_got;
	int weeks_Issue;
	
	
    private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";

	
 public IssueBook()
	{
		super("ISSUE Books");
        makecomponent();
        setSize(1024,768);
		setVisible(true);
		setResizable(false);
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
    
      la1 = new JLabel("Fines ");
      la1.setBounds(830,270,500,100);
      la1.setFont(fo);
      p1.add(la1);
      
       la3 = new JLabel("ID          ");
      la3.setBounds(25,200,500,100);
      la3.setFont(fo);
      p1.add(la3);
      
      la4 = new JLabel("Name       ");
      la4.setBounds(220,17,500,100);
      la4.setFont(fo);
      p1.add(la4);
      
      la5 = new JLabel("NIC Number        ");
      la5.setBounds(220,55,500,100);
      la5.setFont(fo);
      p1.add(la5);
      
      la6 = new JLabel("E-Mail Address");
      la6.setBounds(220,96,500,100);
      la6.setFont(fo);
      p1.add(la6);
      
      la7 = new JLabel("Address");
      la7.setBounds(220,134,500,100);
      la7.setFont(fo);
      p1.add(la7);
      
      la8 = new JLabel("Tel No");
      la8.setBounds(220,235,500,100);
      la8.setFont(fo);
      p1.add(la8);
      
      la9 = new JLabel("Institiute ");
      la9.setBounds(220,271,500,100);
      la9.setFont(fo);
      p1.add(la9);
      
      la10 = new JLabel("Date of Birth ");
      la10.setBounds(220,195,500,100);
      la10.setFont(fo);
      p1.add(la10);
      
           
      tf1 = new JTextField();
      tf1.setBounds(60,237,120,25);
      tf1.addKeyListener(this);
      tf1.setEditable(true);
      tf1.setForeground(new Color(35,6,255));
      tf1.setFont(fo1);
      tf1.selectAll();
      p1.add(tf1);
      
      tf2 = new JTextField();
      tf2.setBounds(400,50,400,25);
      tf2.setEditable(false);
      tf2.setForeground(new Color(35,6,255));
      tf2.setFont(fo1);
      p1.add(tf2);
      
      tf3 = new JTextField();
      tf3.setBounds(400,90,400,25);
      tf3.setEditable(false);
      tf3.setForeground(new Color(35,6,255));
      tf3.setFont(fo1);
      p1.add(tf3);
      
      tf4 = new JTextField();
      tf4.setBounds(400,130,400,25);
      tf4.setEditable(false);
      tf4.setForeground(new Color(35,6,255));
      tf4.setFont(fo1);
      p1.add(tf4);
      
     
      tf5 = new JTextArea();
      tf5.setBounds(400,170,400,40);
      tf5.setEditable(false);
      tf5.setForeground(new Color(35,6,255));
      tf5.setFont(fo1);
      p1.add(tf5);
      
      tf6 = new JTextField();
      tf6.setBounds(400,230,400,25);
      tf6.setEditable(false);
      tf6.setForeground(new Color(35,6,255));
      tf6.setFont(fo1);
      p1.add(tf6);
      
      tf7 = new JTextField();
      tf7.setBounds(400,270,400,25);
      tf7.setEditable(false);
      tf7.setForeground(new Color(35,6,255));
      tf7.setFont(fo1);
      p1.add(tf7);
      
      tf8 = new JTextField();
      tf8.setBounds(400,310,400,25);
      tf8.setEditable(false);
      tf8.setForeground(new Color(35,6,255));
      tf8.setFont(fo1);
      p1.add(tf8);
      
      tf9 = new JTextField();
      tf9.setBounds(890,310,100,25);
      tf9.setEditable(false);
      tf9.setForeground(new Color(35,6,255));
      tf9.setFont(fo1);
      p1.add(tf9);
      
      
      ///////////////////************
      
      tfb1 = new JTextField();
      tfb1.setBounds(400,420,120,25);
      tfb1.setEditable(false);
      tfb1.setForeground(new Color(35,6,255));
      tfb1.setFont(fo1);
      p1.add(tfb1);
      
      tfb2 = new JTextField();
      tfb2.setBounds(400,460,400,25);
      tfb2.setEditable(false);
      tfb2.setForeground(new Color(35,6,255));
      tfb2.setFont(fo1);
      p1.add(tfb2);
      
      tfb3 = new JTextField();
      tfb3.setBounds(400,500,400,25);
      tfb3.setEditable(false);
      tfb3.setForeground(new Color(35,6,255));
      tfb3.setFont(fo1);
      p1.add(tfb3);
      
      tfb4 = new JTextField();
      tfb4.setBounds(400,540,400,25);
      tfb4.setEditable(false);
      tfb4.setForeground(new Color(35,6,255));
      tfb4.setFont(fo1);
      p1.add(tfb4);
      
     
      tfb5 = new JTextField();
      tfb5.setBounds(400,590,400,25);
      tfb5.setEditable(false);
      tfb5.setForeground(new Color(35,6,255));
      tfb5.setFont(fo1);
      p1.add(tfb5);
      
     
      lab4 = new JLabel("Title ");
      lab4.setBounds(220,425,500,100);
      lab4.setFont(fo);
      p1.add(lab4);
      
      lab5 = new JLabel("Book No");
      lab5.setBounds(220,385,500,100);
      lab5.setFont(fo);
      p1.add(lab5);
      
      lab6 = new JLabel("Auther");
      lab6.setBounds(220,465,500,100);
      lab6.setFont(fo);
      p1.add(lab6);
      
      lab7 = new JLabel("Edition");
      lab7.setBounds(220,505,500,100);
      lab7.setFont(fo);
      p1.add(lab7);
      
      lab8 = new JLabel("ISBN");
      lab8.setBounds(220,550,500,100);
      lab8.setFont(fo);
      p1.add(lab8);
      
      lab9 = new JLabel("Already Issued ");
      lab9.setBounds(550,385,500,100);
      lab9.setFont(fo);
      p1.add(lab9);
      
      lab10 = new JLabel("0");
      lab10.setBounds(750,385,500,100);
      lab10.setForeground(new Color(0,0,255));
      lab10.setFont(fo);
      p1.add(lab10);
      
      bt1 = new JButton("Clear Fines");
      bt1.setBounds(880,350,120,40);
      bt1.addActionListener(this);
      p1.add(bt1);

      bt2 = new JButton("ISSUE");
      bt2.setBounds(880,580,120,40);
      bt2.setEnabled(false);
      bt2.addActionListener(this);
      p1.add(bt2);
       
      bt3 = new JButton("End");
      bt3.setBounds(880,520,120,40);
      bt3.addActionListener(this);
      p1.add(bt3);  

  	  bt4 = new JButton("Logout");
      bt4.setBounds(880,25,80,30);
      bt4.addActionListener(this);
      p1.add(bt4);

	    
     
      
      add(p1);
    
     
}

//******************EnterKey Event**********************//
   public void keyReleased(KeyEvent ke)
	{
	}
	public void keyPressed(KeyEvent ke)
		{
		
			if(ke.getKeyCode()==KeyEvent.VK_ENTER)
			{
		
		   
				if(TF1Set)
				{
				
			    	System.out.println("Key 1");
				
			    	findStudentName();
	     	    	tf1.setEnabled(false);
	     	     	updateStudentDetail();
			    	getAdminDetail();
				    getStudentDetail();
			    	viewDetail();
			    	tfb1.setEnabled(true);
			        tfb1.addKeyListener(this);
			        TF2Set = false;
			        TF1Set = false;
				}
			
			if(tfb1.getText().equals(""))
			{
			  TF2Set = false;	
			}
			else
			{
			  TF2Set = true;	
			}	
				
				if(TF2Set)
				{
			 	getBookDetail();
			 			    
		     	}
			}
			
		}
	public void keyTyped(KeyEvent ke)
			{
			}
	//****************************************//


public void actionPerformed(ActionEvent e)
 	{
 	if(e.getSource()==bt2)	
 		{
         
             issueBooks();	
            
		}
   	if(e.getSource()==bt3)	
 		{
          
          IssueBook ob1  = new  IssueBook();
          this.setVisible(false);	
            
		}
		if(e.getSource()==bt1)	
 		{
           ClearFines();
           tf9.setText("");
		}
		
		if(e.getSource()==bt4)
 		{
 			IDB_Circulation ob = new IDB_Circulation();
 			this.setVisible(false);
 		}			
 	}




 
public void findStudentName()
{
	StuID = tf1.getText();
	
	try
	{
		 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
 	     	
          
        ResultSet res=stm.executeQuery("SELECT * FROM student  WHERE UserName  = '"+StuID+"' ");
        
        while(res.next())
        {
        	StuName = res.getString(1);
        }
    	System.out.println("name is "+StuName+ " AND User ID is"+StuID);
      
      	 
      i1=new ImageIcon("public_picture/"+StuID+".jpg");
      img1 = new JLabel(i1);
      img1.setBounds(40,60,125,130);
      p1.add(img1);
      repaint();
      
    
    	
	 if(StuName==null)
     {
     JOptionPane.showMessageDialog(this,"Invalied ID ","Error",JOptionPane.QUESTION_MESSAGE);
	  this.setVisible(false);	
	   IssueBook ob = new IssueBook();
     } 
     		
	}
	catch(Exception e)
	{
    		
	 System.out.println(e);	
	}
	
} 


public void updateStudentDetail()
{
	Student ob = new Student(StuID,StuName);
	ob.setVisible(false);
	
}

public void getAdminDetail()
{
	try
	{
		 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
		  	     	
          
         ResultSet res=stm.executeQuery("SELECT * FROM adminupdates  WHERE fild  = 'MaximumBookOffer' ");
        
         while(res.next())
         {
               String SMaximum_Book = res.getString(2);
                      Maximum_Book = Integer.parseInt(SMaximum_Book);
                      System.out.println("############-----------------###########"+Maximum_Book);
                      res.close();    
	     }
	 }
	   catch(Exception dd)
	{
		
	}  
	 try
	 {
	 
          Class.forName(driver);

		  Connection conn=DriverManager.getConnection(url,"root","");
 
          Statement stm1=conn.createStatement();
	      ResultSet res1=stm1.executeQuery("SELECT * FROM adminupdates  WHERE fild  = 'Maximum_fine' ");
        
         while(res1.next())
         {
               String SMaximum_Fine = res1.getString(2);
               max_fine = Double.parseDouble(SMaximum_Fine);
               System.out.println("############----------------###########"+SMaximum_Fine);
               res1.close();    
	     } 
 }
catch(Exception dd)
	{
		
	}
	
 try
 {
 	  Class.forName(driver);

	  Connection conn=DriverManager.getConnection(url,"root","");
 
      Statement stm1=conn.createStatement();
	  ResultSet res1=stm1.executeQuery("SELECT * FROM adminupdates  WHERE fild  = 'weeks_Issue' ");
        
     while(res1.next())
         {
               String Sweek_Issue = res1.getString(2);
               weeks_Issue = Integer.parseInt(Sweek_Issue);
               System.out.println("############----------------*****************"+Sweek_Issue);
               res1.close();    
	     }     
 	
 	
 }	
 catch(Exception e)
 {
 	
 }
	
}

public void getStudentDetail()
{
	try
	{
		 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
		 Statement stm1=conn.createStatement();
 	     	
          
      /*   ResultSet res=stm.executeQuery("SELECT * FROM student  WHERE  UserName  = '"+StuID+"' ");
        
         while(res.next())
         {
                      String S_Stu_Fine = res.getString(10);
                      fine = Double.parseDouble(S_Stu_Fine);
                      System.out.println("+++++++++++++++++++++++++++++"+fine);
                      res.close();    
	     } */
	      
	}
	catch(Exception dd)
	{
		
	}

	try
	{
	
	     Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
 	     	
          
         ResultSet res=stm.executeQuery("SELECT count(*) FROM lending  WHERE StudentID   = '"+StuID+"' ");
         
         while(res.next())
         {
         	String SBook_has_got = res.getString(1);
         	Book_has_got = Integer.parseInt(SBook_has_got);
         	
         	lab10.setText(SBook_has_got);
         	
         	if(Book_has_got<=Maximum_Book)
         	{
         	  lab10.setForeground(new Color(255,0,0));	
         	}
         	
         	System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQ  no of Row"+Book_has_got);
         }
		
	}
	catch(Exception e)
	{
	System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+e);	
	}	
	
}

public void viewDetail()
{
	
	try
	{
		 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
 	     	
          
         ResultSet res=stm.executeQuery("SELECT * FROM student  WHERE UserName  = '"+StuID+"' ");
        
         while(res.next())
         {
         	tf2.setText(res.getString(1));
         	tf3.setText(res.getString(4));
         	tf4.setText(res.getString(5));
         	tf5.setText(res.getString(7));
         	tf6.setText(res.getString(6));
         	tf7.setText(res.getString(8));
         	tf8.setText(res.getString(9));
        
      
       }
       
         res.close();
         


    Class.forName(driver);
	Connection conn1=DriverManager.getConnection(url,"root","");
	Statement stm2=conn1.createStatement();

    String fine2="";
	

  ResultSet res3=stm2.executeQuery("SELECT * FROM student_not_pay   WHERE ID = '"+StuID+"' ");
	while(res3.next())
	{
	
	   fine2=res3.getString(2);  //Fine2.......
     
	}
	res3.close();
	  if(fine2.equals(""))
	     fine2 = "0.0";
	
	System.out.println("Fine 01..............."+fine2);
	
	double x,y,z;
	 y = Double.parseDouble(fine2);
	
	String totFine = Double.toString(y);
	System.out.println(totFine);
	tf9.setText(totFine);  //Fines.......
	 
       fine =y;  
       tfb1.setEditable(true);
       
       if(max_fine<=fine)
       {
       	 JOptionPane.showMessageDialog(this,"Can't Issue books pay your fines  ","Error",JOptionPane.QUESTION_MESSAGE);
       	
       }
        System.out.println("999999999999999999999999999999  "+Maximum_Book);
        System.out.println("999999999999999999999999999999  "+Book_has_got);
      
       if(Maximum_Book==Book_has_got)
       {
       	 JOptionPane.showMessageDialog(this,"Can't Issue books \n Already  "+Maximum_Book+"  Books  has beenissued  ","Error",JOptionPane.QUESTION_MESSAGE);
       	
       }
         
	}
	catch(Exception e)
	{
	System.out.println(e);	
	}

	
}

public void Clear()
{	 
 tfb2.setText("");	 
 tfb3.setText("");	 
 tfb4.setText("");	 
 tfb5.setText("");	 
}

public void getBookDetail()
{
	
	System.out.println("Get book details////////////////////");
	String bookNo = tfb1.getText();
	
	try
	{
	     Clear();
	     Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
 	     	
          
         ResultSet res=stm.executeQuery("SELECT * FROM Books  WHERE assno  = '"+bookNo+"' ");
         
       String state="";  
          
         while(res.next())
         {
         	tfb2.setText(res.getString(2));
         	tfb3.setText(res.getString(3));
         	tfb4.setText(res.getString(7));
         	tfb5.setText(res.getString(6));
         	state = res.getString(13);
         	System.out.println(state);
         }
         String Stit = tfb2.getText();
         tfb1.selectAll(); 
         
         
         if(state.equals("NOT AVALABLE"))
         {
         JOptionPane.showMessageDialog(this,"Wrong Book Number.\n This book is already issued ","Message",JOptionPane.QUESTION_MESSAGE); 	
         bt2.setEnabled(false);
         Clear();
         tfb1.setText("");
         }
       
         else if(Stit.equals(""))
          {
         JOptionPane.showMessageDialog(this,"Invalied Book Number  ","Error",JOptionPane.QUESTION_MESSAGE); 	
         tfb1.setText("");
		  }
		  else
		  {
		  	bt2.setEnabled(true);
		  
		  }
		  
	  
	  	
	}
	catch(Exception e)
	{
	System.out.println(e);	
	}
	
	
}	

public void issueBooks()
{
	/////////Book validation////////////
	
	
	////////////////////////////////////
	 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
	     Date date= new Date();
         
         long currentTime = date.getTime();
         
         String IssueDate = bartDateFormat.format(date);
  
  
  ////Add Someadditional time         
         long submitTime = currentTime + (1000*3600*24*7*weeks_Issue) + (1000*3600*8);
         
         
         date.setTime(submitTime); 
         
         String SubmitDate = bartDateFormat.format(date);
         
         System.out.println("Issue date  "+IssueDate);
         System.out.println("Return date  "+SubmitDate);
         
         /////////Data
         
         String feild1 = tfb1.getText();
         String feild2 = tfb2.getText();
         String feild3 = IssueDate;
         String feild4 = SubmitDate;
         String feild5 = "";
         String feild6 = "0";
         String feild7 = "0";
         String feild8 = StuID;
         String feild9 = Long.toString(submitTime);
         String feild10 = tfb4.getText();

   try
   {	   
   
       	Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
		 System.out.println("*********************Insert ********************");
		 stm.executeUpdate("INSERT INTO lending (assno,title,Date,returnDate,SumitedDate,Renewcounter,fine,StudentID,oldTime,Edition ) VALUE('"+feild1+"','"+feild2+"','"+feild3+"','"+feild4+"','"+feild5+"','"+feild6+"','"+feild7+"','"+feild8+"','"+feild9+"','"+feild10+"')");                                                
   }
   catch(Exception e)
   {
   	System.out.println(""+e);
   }
   
    try
   {
   	
   	     Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm1=conn.createStatement();
		 
		 stm1.executeUpdate("UPDATE  books SET Status='NOT AVALABLE' WHERE assno = '"+feild1+"' ");
		 
		 System.out.println("Updating....................");
		 JOptionPane.showMessageDialog(this," Book  has beenissued  ","Error",JOptionPane.QUESTION_MESSAGE); 	
		 
		 Clear();
		 tfb1.setText("");
		 
		 Book_has_got = Book_has_got+1;
		 
		 String SBook_has_got =Integer.toString(Book_has_got);
		 lab10.setText(SBook_has_got);
		 
		 if(Book_has_got==Maximum_Book)
		 {
		   JOptionPane.showMessageDialog(this,"Can't Issue books \n Already  "+Maximum_Book+"  Books  has been issued  ","Error",JOptionPane.QUESTION_MESSAGE); 	
		 }
		   
   	
   }
   catch(Exception e)
   {
   	System.out.println(""+e);
   }
	
}

public void ClearFines()
{
	
    try
    {
      Class.forName(driver);

	  Connection conn=DriverManager.getConnection(url,"root","");

      Statement stm=conn.createStatement();
		 
      stm.executeUpdate("UPDATE  student_not_pay   SET Fine  ='0.0' where ID  = '"+StuID+"' ");
		  	
    }	 	
	catch(Exception e)
	{
		
	}
}

 }

 class Issue

{
	public static void main(String args[])
	{
	  IssueBook ob = new IssueBook();
	  
	}

}