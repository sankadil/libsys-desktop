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

 class ReturnBook extends JFrame implements ActionListener,KeyListener
{
	JPanel p1,p2;
	JButton bt1,bt2,bt3,bt12,bt22,bt4,bt5;
    ImageIcon i1;
	/////////////////////////////
    JLabel la1,la2,la3,la4,la5;
    JLabel la6,la7,la8,la9,la10;
    JLabel img2;
    JLabel lab4,lab5,lab6,lab7,lab8,lab9,lab10;
	JTextField tf1,tf2,tf3,tf4;
	JTextField tf5,tf6,tf7,tf8,tf9,tf10;
	JTextField tfb1,tfb2,tfb3,tfb4,tfb5;
	////////////////////////////
	
	////////////////////////////
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);
	static String StuID;
	String StuName;
	String BookNo;
	
    private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";

	
 public ReturnBook()
	{
		super("Return Unit");
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
    
       la1 = new JLabel("Fines ");
      la1.setBounds(830,250,500,100);
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
      
      la7 = new JLabel("Issue Date");
      la7.setBounds(220,134,500,100);
      la7.setFont(fo);
      p1.add(la7);
      
      la8 = new JLabel("Return Date");
      la8.setBounds(220,215,500,100);
      la8.setFont(fo);
      p1.add(la8);
      
      la9 = new JLabel("Fine ");
      la9.setBounds(220,255,500,100);
      la9.setFont(fo);
      p1.add(la9);
      
      la10 = new JLabel("Renew Counter");
      la10.setBounds(220,175,500,100);
      la10.setFont(fo);
      p1.add(la10);
      
           
      tf1 = new JTextField();
      tf1.setBounds(60,237,120,25);
      tf1.setEditable(false);
      tf1.setForeground(new Color(35,6,255));
      tf1.setFont(fo1);
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
      
     
      tf5 = new JTextField();
      tf5.setBounds(400,170,400,25);
      tf5.setEditable(false);
      tf5.setForeground(new Color(35,6,255));
      tf5.setFont(fo1);
      p1.add(tf5);
      
      tf6 = new JTextField();
      tf6.setBounds(400,210,400,25);
      tf6.setEditable(false);
      tf6.setForeground(new Color(35,6,255));
      tf6.setFont(fo1);
      p1.add(tf6);
      
      tf7 = new JTextField();
      tf7.setBounds(400,250,400,25);
      tf7.setEditable(false);
      tf7.setForeground(new Color(35,6,255));
      tf7.setFont(fo1);
      p1.add(tf7);
      
      tf8 = new JTextField();
      tf8.setBounds(400,290,400,25);
      tf8.setEditable(false);
      tf8.setForeground(new Color(35,6,255));
      tf8.setFont(fo1);
      p1.add(tf8);
      
      tf9 = new JTextField();
      tf9.setBounds(890,290,100,25);
      tf9.setEditable(false);
      tf9.setForeground(new Color(35,6,255));
      tf9.setFont(fo1);
      p1.add(tf9);
      
      
      ///////////////////************
      
      tfb1 = new JTextField();
      tfb1.setBounds(400,420,120,25);
      tfb1.setEditable(true);
      tfb1.setForeground(new Color(35,6,255));
      tfb1.addKeyListener(this);
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
      
      bt1 = new JButton("Clear Fines");
      bt1.setBounds(880,350,120,40);
      bt1.addActionListener(this);
      p1.add(bt1);
      
     
      bt2 = new JButton("Receive");
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
					Clear();
					StuID=null;
					StuName=null;
			    	System.out.println("Key 1");
			    	
				
				       viewBookDetail();
				       
				          if(StuName==null)
                               {
                             if(tfb2.getText().equals(""))
                               JOptionPane.showMessageDialog(this,"Invalied Book Number.. ","Warning",JOptionPane.QUESTION_MESSAGE); 		
                             if(!tfb2.getText().equals(""))
                               JOptionPane.showMessageDialog(this,"This Book hasn't been issued ","Message",JOptionPane.QUESTION_MESSAGE); 		 
                               
                                Clear();
				     	
                               }
                          else{ 
                               
	     	                  updateStudentDetail(StuID,StuName);//find get Name and ID ,Update Student Accunt
	     	     	          ViewLendingDetail();
			    	          VeiwStudentDetail();
			    	          bt2.setEnabled(true);
			    	          
			    	 		  }
			    //	tfb1.setEnabled(false);   
			        tfb1.selectAll();
			        
				}
			
			}
	public void keyTyped(KeyEvent ke)
			{
			}
	//****************************************//


public void actionPerformed(ActionEvent e)
 	{
 		if(e.getSource()==bt3)	
 		{
         
          img2.setVisible(false);
          tfb1.setText("");
          Clear();
          tfb1.setEnabled(true);
          tfb1.selectAll();
            
		}
		if(e.getSource()==bt2)	
 		{
          
           if(tf8.getText().equals(""))
           {
           	
           }
           else
           {
           	 add_fine();
           	 System.out.println("Add fine");  
           }
          
          img2.setVisible(false);
          tfb1.setText("");
          Clear();
          tfb1.setEnabled(true);
          tfb1.selectAll();
          updateDataBases();
          update_Profomance();
     
          
       	}
       	if(e.getSource()==bt1)	
 		{
           ClearFines();
           tf9.setText("");
         //  clearBookFine();
           
		}
		if(e.getSource()==bt4)
 		{
 			IDB_Circulation ob = new IDB_Circulation();
 			this.setVisible(false);
 		}
 		if(e.getSource()==bt5)
 		{
 		 //  clearBookFine();
 		}				
 	} 
public void Clear()
{
		 
 tfb2.setText("");	 
 tfb3.setText("");	 
 tfb4.setText("");	 
 tfb5.setText("");	 
 tf1.setText("");	 
 tf2.setText("");	 
 tf3.setText("");	 
 tf4.setText("");	 
 tf5.setText("");	 
 tf6.setText("");	 
 tf7.setText("");	 
 tf8.setText("");	 
 tf9.setText("");
 tfb1.selectAll();	 
}

public void viewBookDetail()
{
	BookNo = tfb1.getText();
	
	try{
	
    Class.forName(driver);

	Connection conn=DriverManager.getConnection(url,"root","");

	Statement stm=conn.createStatement();
 	     	
          
    ResultSet res=stm.executeQuery("SELECT * FROM lending  WHERE assno = '"+BookNo+"' ");
      
      while(res.next())
      {
      	   StuID = res.getString(8);
      }
      res.close();
    
    ResultSet res1=stm.executeQuery("SELECT * FROM student  WHERE UserName = '"+StuID+"' ");
    
    while(res1.next())
      {
      	   StuName = res1.getString(1);
      }
      res1.close();
      
      System.out.println("ID " +StuID+"\nName "+StuName);
      
      System.out.println("************################*******  "+StuID);
      
       
  
       i1=new ImageIcon("public_picture/"+StuID+".jpg");
       img2 = new JLabel(i1);
      img2.setBounds(40,60,125,130);
      p1.add(img2);
      repaint();
      
      
      
      System.out.println("************#######***********#########*******  "+StuID);
     
      
      ResultSet res2=stm.executeQuery("SELECT * FROM Books  WHERE assno  = '"+BookNo+"' ");   
    
    while(res2.next())
    {
    	tfb2.setText(res2.getString(2));
        tfb3.setText(res2.getString(3));
        tfb4.setText(res2.getString(7));
        tfb5.setText(res2.getString(6));
         
        is_this_reserve(res2.getString(2),res2.getString(7)); 
        
    }
    
    res2.close(); 
    
    }
    catch(Exception dd)
    {
    	System.out.println(dd);
    }
        
}

public void updateStudentDetail(String ID,String name)
{
	Student ob1 = new Student(ID,name);
	ob1.setVisible(false);
	
}

public void ViewLendingDetail()
{
	
  try
  {
     
     	
    Class.forName(driver);

	Connection conn=DriverManager.getConnection(url,"root","");

	Statement stm=conn.createStatement();
	
	ResultSet res1=stm.executeQuery("SELECT * FROM lending  WHERE StudentID = '"+StuID+"' ");
	
	while(res1.next())
	{
		tf5.setText(res1.getString(3));
        tf6.setText(res1.getString(6));
        tf7.setText(res1.getString(4));
        tf8.setText(res1.getString(7));
	}
    
     	
  }
  catch(Exception e)
  {
  	
  }	
	
}

public void VeiwStudentDetail()
{

 try
  {
     
     	
    Class.forName(driver);

	Connection conn=DriverManager.getConnection(url,"root","");

	Statement stm=conn.createStatement();
	
	ResultSet res1=stm.executeQuery("SELECT * FROM student  WHERE UserName = '"+StuID+"' ");
	
	while(res1.next())
	{
		tf1.setText(res1.getString(2));
        tf2.setText(res1.getString(1));
        tf3.setText(res1.getString(4));
        tf4.setText(res1.getString(5));
    //    tf9.setText(res1.getString(10));  //Fines.......
       
     
	}
	res1.close();
    Class.forName(driver);
	Connection conn1=DriverManager.getConnection(url,"root","");
	Statement stm1=conn1.createStatement();
	Statement stm2=conn1.createStatement();

String fine1="";
String fine2="";
	
	ResultSet res2=stm1.executeQuery("SELECT * FROM Student_Total_Fines  WHERE ID = '"+StuID+"' ");
	
	while(res2.next())
	{
	
	   fine1=res2.getString(2);  //Fine1.......
     
	}
	res2.close();
	if(fine1.equals(""))
	     fine1 = "0.0";
	     
	     System.out.println("Fine 01..............."+fine1);
	
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
	 x = Double.parseDouble(fine1);
	 y = Double.parseDouble(fine2);
	
	 z= x+y;
	String totFine = Double.toString(z);
	System.out.println(totFine);
	tf9.setText(fine2);  //Fines.......
	 	      	
  }
  catch(Exception e)
  {
  System.out.println("************"+e);  	
  }	
	
}

public void updateDataBases()
{
  try
  {
  	
  	     Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm1=conn.createStatement();
		 
		 stm1.executeUpdate("UPDATE  books SET Status='AVALABLE' WHERE assno = '"+BookNo+"' ");
		 
		 System.out.println("Updating....................");
		 JOptionPane.showMessageDialog(this," Book  has Recieved  ","Information",JOptionPane.INFORMATION_MESSAGE); 	
	
	//----------------------------------------------------------------------------
	String data1;
	String data2;
	String data3;
	String data4;
	String data5;
	String data6;
	String data7;
	String data8;
	String data9;
	
	SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
    Date date= new Date();
    
    long currentTime = date.getTime();
         
    String IssueDate = bartDateFormat.format(date);
    
    data5 = IssueDate;
	
	
	     Statement stm2=conn.createStatement();	
	     ResultSet res=stm2.executeQuery("SELECT * FROM lending  WHERE assno   = '"+BookNo+"' ");
	     
	     while(res.next())
	     {
	     	data1=res.getString(1);
	     	data2=res.getString(2);
	     	data3=res.getString(3);
	     	data4=res.getString(4);
	  //    	data5=res.getString(5);
	     	data6=res.getString(6);
	     	data7=res.getString(7);
	     	data8=res.getString(8);
	 //    	data9=res.getString(9);
	     	
	    Statement stm=conn.createStatement();
  	
    	stm.executeUpdate("INSERT INTO histroy (assno,Title,Date,returndate,Submitdate,renewCounter,Fine,ID) VALUE('"+data1+"','"+data2+"','"+data3+"','"+data4+"','"+data5+"','"+data6+"','"+data7+"','"+data8+"')");                                                
  	
  	    Statement stm4=conn.createStatement();
  	
    	stm4.executeUpdate("DELETE FROM lending where assno = '"+BookNo+"'");  
    	
    	System.out.println("Ok.........................");                                              
  	
	     	
	     }
       res.close();   
  	
  
  	
   	
  	
  	
  }	
  catch(Exception e)
  {
  System.out.println("Finsh 000000000000000000000000000"+e);	
  }
	
	
}

public void ClearFines()
{
	
    try
    {
      Class.forName(driver);

	  Connection conn=DriverManager.getConnection(url,"root","");

      Statement stm=conn.createStatement();
		 
      stm.executeUpdate("UPDATE  student_not_pay   SET Fine   ='0.0' where  ID  = '"+StuID+"' ");
	  //******************
	      img2.setVisible(false);
          tfb1.setText("");
          Clear();
          tfb1.setEnabled(true);
          tfb1.selectAll();
          updateDataBases();
          update_Profomance();	  	
	  //******************	  	
    }	 	
	catch(Exception e)
	{
	System.out.println(e);	
	}
  	
	
}
public void clearBookFine()
{
   tf8.setText("");	
}


public void update_Profomance()
{
String subject = "";
String subject_fromDemane = "";
int demand  = 0; 
   System.out.println("update_Profomance()");
   	try
   	{
   	Class.forName(driver);

	Connection conn=DriverManager.getConnection(url,"root","");

	Statement stm=conn.createStatement();
	
	ResultSet res1=stm.executeQuery("SELECT * FROM books  WHERE assno  = '"+BookNo+"' ");

	
	while(res1.next())
	{
		subject = res1.getString(14);
		System.out.println(subject);
	}
	res1.close();
//***************************************

  ResultSet res3=stm.executeQuery("SELECT * FROM Subject_Demand  WHERE subject  = '"+subject+"' ");
	
	while(res3.next())
	{
		subject_fromDemane = res3.getString(1);
		System.out.println(subject_fromDemane);
	}
		
	   if(subject_fromDemane.equals(""))
	   {
	   	stm.executeUpdate("Insert into  Subject_Demand (subject,demand)VALUE('"+subject+"','1')");
	   	System.out.println("Insert Demand");
	   }
	   else
	   {
	   	ResultSet res4=stm.executeQuery("SELECT * FROM Subject_Demand  WHERE subject  = '"+subject+"' ");
	   	while(res4.next())
	   	{
	   		String Svote  = res4.getString(2);
	   		demand = Integer.parseInt(Svote);
	   	}
        demand = demand + 1;
	   	stm.executeUpdate("UPDATE  Subject_Demand SET demand ='"+demand+"' where subject = '"+subject+"' ");
	   	System.out.println("Update Demand");
	   }
	
	}
   	catch(Exception e)
   	{
   		System.out.println(e);
   	}
}

public void add_fine()
{
 String student="";
 String amount="";
 
 try{
 
	Class.forName(driver);
	Connection conn=DriverManager.getConnection(url,"root","");
	Statement stm=conn.createStatement();
	
	ResultSet res1=stm.executeQuery("SELECT * FROM student_not_pay  WHERE ID  = '"+StuID+"' ");
	
	while(res1.next())
	{
		student = res1.getString(1);
		amount = res1.getString(2);
	}
   res1.close();
   
   if(student.equals(""))
   {
   	 String fi = tf8.getText();
   	 double fine_to_book = Double.parseDouble(fi);
   	 String Snew_fine = Double.toString(fine_to_book);
   	 stm.executeUpdate("insert  student_not_pay (ID,Fine)VALUE('"+StuID+"','"+Snew_fine+"') ");
	 System.out.println("Insert fine");
	   
   }
   else
   {
   	    String fi = tf8.getText();
   	    double fine_to_book = Double.parseDouble(fi);
   	    double old_fine = Double.parseDouble(amount);
   	    double new_find = fine_to_book+old_fine;
   	    String Snew_fine = Double.toString(new_find);
   	    stm.executeUpdate("UPDATE  student_not_pay  SET Fine  ='"+Snew_fine+"' where ID = '"+student+"' ");
	   	System.out.println("Update fine");
	   
     }	
 }
	catch(Exception e)
   	{
   		System.out.println("^^^^^^^^^^^^^"+e);
   	}

	
}

public void is_this_reserve(String tit,String edi)
{
	String title=tit;
	String edition=edi;
	String isReserved="";
	
	try
	{
    	Class.forName(driver);
	    Connection conn=DriverManager.getConnection(url,"root","");
    	Statement stm=conn.createStatement();
	
	    ResultSet res1=stm.executeQuery("SELECT * FROM reservation  WHERE Title = '"+title+"' AND Edition = '"+edition+"' ");
	    while(res1.next())
	    {
	        isReserved = res1.getString(9);
	    	res1.first();
	    	System.out.println("");
	    	System.out.println("^^^^^^chack it is reserved^^^^^^"+isReserved);
	    	break;
	    ///	isReserved = Integer.parseInt(isresve);   	
	    }
	    	
	    	if(isReserved.equals(""))
	    	{
	    		System.out.println("@@@@@@@@@@@@@@@@@  No any reservation @@@@@@@@@@@@@");
	    	}
	    	else
	    	{
	    	   System.out.println("@@@@@@@@@@@@@@@@@  Sent a SMS or E-mail @@@@@@@@@@@@@");
	    	   System.out.println("@@@@@@@@@@@@@@@@@  Delete Reservation records @@@@@@@@@@@@@");
	    	   stm.executeUpdate("DELETE FROM reservation  where StudentID = '"+isReserved+"'");
	    	   System.out.println("DELETE reservation Record");
	   
	    	}
		
	}
	catch(Exception e)
   	{
   		System.out.println("^^^^^^^^^^^^^"+e);
   	}
	
}


 }

 class Return

{
	public static void main(String args[])
	{
	  ReturnBook ob = new ReturnBook();
	  
	}

}