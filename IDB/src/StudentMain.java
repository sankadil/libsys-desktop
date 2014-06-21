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


class Student extends JFrame implements ActionListener
{
	JPanel p1;  JPanel p2;  JPanel p3;
	JButton bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7;
	JTable jt;
	JLabel la1,la2,la3,la4,img1;
	JScrollPane jsp;
	JTextField tf1;
	Font fo1 = new Font("Times New Roman",Font.BOLD,18);
	Font fo2 = new Font("Lucida Bright",Font.CENTER_BASELINE,16);
	Font fo = new Font("Courier New",Font.BOLD,18);
	Date dat;

	private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";


	String header[]=new String[]{"Book Number"," Title","Date","Return Date","Renewe Counter","Fine"};
	String data[][];
	String userID,name;
	int row;
	String AmountOfFine;
	int renewTime;
	int renewWeks;
	int fineRate;
	double bookFine;


 public Student(String un,String fname)
	{
		super("...::::"+fname+ "   IDB LibSys ");
		userID= un;
		name = fname;
		getAdminDetail();
		Systemupdate();
		viewDetail();
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


 //     borlabel1 = new JLabel();
 //     EtchedBorder bor1 = new EtchedBorder(2,Color.red,Color.green);

 //     borlabel1.setBorder(bor1);
 //      p1.add(borlabel1);

      /////*******************************\\\\\\
      ImageIcon i1=new ImageIcon("public_picture/"+userID+".jpg");
      img1 = new JLabel(i1);
      img1.setBounds(835,60,125,130);
      p1.add(img1);

      bt1 = new JButton(" Request Book ");
      bt1.setBounds(35,65,120,40);
      bt1.addActionListener(this);
      p1.add(bt1);

      bt2 = new JButton("Vote for Books");
      bt2.setBounds(35,120,120,40);
      bt2.addActionListener(this);
      p1.add(bt2);

      bt3 = new JButton(" View History ");
      bt3.setBounds(35,175,120,40);
      bt3.addActionListener(this);
      p1.add(bt3);

      bt4 = new JButton(" Reservation  ");
      bt4.setBounds(35,230,120,40);
      bt4.addActionListener(this);
      p1.add(bt4);

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

      bt5 = new JButton("Logout");
      bt5.setBounds(880,25,80,30);
      bt5.addActionListener(this);
      p1.add(bt5);

      bt0 = new JButton("Change Password");
      bt0.setBounds(730,25,140,30);
      bt0.addActionListener(this);
      p1.add(bt0);


      bt6 = new JButton(" renew ");
      bt6.setBounds(870,420,90,40);
      bt6.addActionListener(this);
      p1.add(bt6);

      bt7 = new JButton("Refresh");
      bt7.setBounds(760,420,90,40);
      bt7.addActionListener(this);
      p1.add(bt7);



      tf1 = new JTextField();
      tf1.setBounds(825,370,135,25);
      tf1.setFont(fo1);
      tf1.setForeground(new Color(255,0,0));
      tf1.setEditable(false);
      tf1.setHorizontalAlignment( JTextField.RIGHT);

      p1.add(tf1);


      jt = new JTable(data,header);
      jt.setSelectionBackground(new Color(255,255,100));
      jt.setForeground(new Color(35,6,255));
      jt.setBackground(new Color(165,248,255));
      jsp = new JScrollPane(jt);
      jsp.setBounds(300,200,660,150);
      p1.add(jsp);

      la3 = new JLabel("Amount of Fines");
      la3.setBounds(650,330,200,100);
      la3.setForeground(new Color(3,3,153));
      la3.setFont(fo1);
      p1.add(la3);

      la4 = new JLabel("All books currently you have lent ");
      la4.setBounds(300,125,400,100);
      la4.setForeground(new Color(3,3,153));
      la4.setFont(fo2);
      p1.add(la4);


      tf1.setText(AmountOfFine);

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
 	 if(e.getSource()==bt6)
 		{

          int x = jt.getSelectedRow();
          System.out.println("Selected Row"+x);
          if(x==-1)
          {
          	JOptionPane.showMessageDialog(this,"Book hasn't been selected","Error",JOptionPane.QUESTION_MESSAGE);
          }
         else
 	      renewBooks(x);


 		}
 	if(e.getSource()==bt1)
 		{
			try{

 	      requestBook rq = new	requestBook(userID,name);
 	      this.setVisible(false);
 	      System.out.println("Requesting books");
	  }
	  catch(Exception rr)
	  {
		System.out.println("Requesting error");
	}

 		}
 	if(e.getSource()==bt7)
 		{
 		  this.setVisible(false);
          Student ob1 = new Student(userID,name);
		  System.out.println("refresh");



 		}
 	if(e.getSource()==bt2)
 		{
 		  AddVote ob1 = new AddVote(userID,name);
 		  this.setVisible(false);
		  System.out.println("vote");

 		}
    if(e.getSource()==bt4)
 		{
 		  reservation ob1 = new reservation(userID,name);
 		  this.setVisible(false);
		  System.out.println("vote");

 		}
   if(e.getSource()==bt3)
 		{
 		  StuHistory ob1 = new StuHistory(userID,name);
 		  this.setVisible(false);
		  System.out.println("History");
 		}
   if(e.getSource()==bt0)
 		{
 		  ChangePassword ob1 = new ChangePassword(userID,name);
 		  this.setVisible(false);

 		}
 	}



 public void getAdminDetail()
 {
 	try
 	{      Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();
 		   ResultSet res1=stm.executeQuery("SELECT * FROM adminupdates  WHERE fild = 'fine_per_date' ");

		  while(res1.next())
		  {


		   String SfineRate = res1.getString(2);
	//	   String SRenewTime = res1.getString(2);

		   fineRate = Integer.parseInt(SfineRate);
           System.out.println("****************************"+fineRate);



          }
           res1.close();
     }
     catch(Exception gg)
	  {
	  	  System.out.println("AdminDetail Erroe************"+gg);
	  }

try{
	      Class.forName(driver);

		  Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();


          ResultSet res2=stm.executeQuery("SELECT * FROM adminupdates  WHERE fild = 'muximum_renew_week' ");


		  while(res2.next())
		  {

		   String SRenewTime = res2.getString(2);

		   renewTime = Integer.parseInt(SRenewTime);
           System.out.println("****************************"+renewTime);



          ///////////////System need to refresh to update dtabase

        }
          res2.close();
 	}

 	 catch(Exception gg)
	  {
	  	  System.out.println("AdminDetail Erroe************"+gg);
	  }
	  try{
	      Class.forName(driver);

		  Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();


          ResultSet res3=stm.executeQuery("SELECT * FROM adminupdates  WHERE fild = 'renewWeek' ");


		  while(res3.next())
		  {

		   String SrenewWeks = res3.getString(2);

		   renewWeks = Integer.parseInt(SrenewWeks);
           System.out.println("****************************"+renewWeks);



          ///////////////System need to refresh to update dtabase

        }
       res3.close();
 	}

 	 catch(Exception gg)
	  {
	  	  System.out.println("AdminDetail Erroe************"+gg);
	  }


 }

 public void  Systemupdate()
 {
 	try{
 	 double fin = 0;
           Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM lending  WHERE StudentID = '"+userID+"' ");

	int j = 0;
		   while(res.next())
		   {
		   	 dat = new Date();
                    long CurrentTime  = dat.getTime();



                    String SOldTime  = res.getString(9);



                    long OldTime = Long.parseLong(SOldTime);

                    long late = CurrentTime - OldTime;


                    System.out.println("########################"+late);

                    if(late>0)
                    {
                    	///////////////////////////////////////////////////////


                    	///////////////////////////////////////////////////////

                  	bookFine = fineRate*late/(1000*3600*24);
                   	System.out.println("Fine is  Rs  " +bookFine);


		             Statement stm1=conn.createStatement();

		             stm1.executeUpdate("UPDATE  lending  SET fine='"+bookFine+"' WHERE assno   = '"+res.getString(1)+"' ");


		           }
		           else
		           {

		            Statement stm1=conn.createStatement();
		            stm1.executeUpdate("UPDATE  lending  SET fine='0' WHERE assno   = '"+res.getString(1)+"' ");
		   	}

		   j++;
	}
	}

	catch(Exception hh)
	{

	}
 }


 public void viewDetail()
 {
 		  boolean namefound;
	  try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM lending  WHERE StudentID = '"+userID+"' ");


           res.last();
		   row=res.getRow();
		   res.first();

          data=new String[row][8];

           int j=0;
           double fin = 0;

  if(row>0)
  {

         do
         {


//////////////////////////////////////
//////////Display Data//////\\\\\\\\\

		           	data[j][0] = res.getString(1);
					 data[j][1] = res.getString(2);
					 data[j][2] = res.getString(3);
					 data[j][3] = res.getString(4);
					 data[j][4] = res.getString(6);
					 data[j][5] = res.getString(7);

				     System.out.println( data[j][0] );
				     System.out.println( data[j][1] );
					 System.out.println( data[j][2] );
					 System.out.println( data[j][3] );
					 System.out.println( data[j][4] );
					 System.out.println( data[j][5] );

////////////////////////////////////

/////////Calculate finn amount////////

                   fin = fin + (Double.parseDouble(data[j][5]));
                   AmountOfFine = Double.toString(fin);

	           	 j++;
                     }

		 while(res.next());


		   Class.forName(driver);
           Connection conn1=DriverManager.getConnection(url,"root","");
           Statement stm1=conn1.createStatement();
           ResultSet res1=stm1.executeQuery("SELECT * FROM Student_Total_Fines  WHERE ID = '"+userID+"' ");

          boolean nameFound = false;
           while(res1.next())
           {
           	 String nameThere =  res1.getString(1);
           	 if(nameThere.equals(userID))
           	      nameFound = true;
           	 else
           	      nameFound = false;
           }

		 if(nameFound==false)
		      {
		  stm.executeUpdate("Insert into Student_Total_Fines (ID,Amount) values ('"+userID+"','0') ");
		  stm.executeUpdate("UPDATE  Student_Total_Fines  SET Amount ='"+AmountOfFine+"' where ID = '"+userID+"' ");
		      }
		 else if(nameFound==true)
		 {
		 stm.executeUpdate("UPDATE  Student_Total_Fines  SET Amount ='"+AmountOfFine+"' where ID = '"+userID+"' ");

		 }

    }

	   }

	  catch(Exception gg)
	  {
	 ///   	JOptionPane.showMessageDialog(this,"System failure ","Error",JOptionPane.QUESTION_MESSAGE);
		  System.out.println("System failure"+gg);
	  }

}

public void renewBooks(int rowIndex)
{
 int row = rowIndex;
 String bookno = data[row][0];
 String renewCounter;


   try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM lending WHERE assno = '"+bookno+"'");



		   while(res.next())
		   {
		     renewCounter=res.getString(6);

		     dat = new Date();
                    long CurrentTime  = dat.getTime();



                    String SOldTime  = res.getString(9);



                    long OldTime = Long.parseLong(SOldTime);

                    long late = CurrentTime - OldTime;


                    System.out.println("########################"+late);


		   	int NoOfrenew = Integer.parseInt(renewCounter);


		   	if(NoOfrenew==renewTime)
		   	{
		    JOptionPane.showMessageDialog(this,"You have renewed this book  " +renewTime+" time\n So you can't renew any more  \n Please return the book.. ","Message",JOptionPane.QUESTION_MESSAGE);
		   	}

		   	else if(late>0)
		   	{

		   	 JOptionPane.showMessageDialog(this,"You can't Renew this book.\nBecause you have a fine for this book","Message",JOptionPane.QUESTION_MESSAGE);

		    }
		    else if(isReserve(rowIndex))
		    {
		   //  boolean getResponce=isReserve(rowIndex);
		     JOptionPane.showMessageDialog(this,"You can't Renew this book.\nSomebody has reserved this book","Message",JOptionPane.QUESTION_MESSAGE);

		    }

		   else
		   {
		   	System.out.println("book renewed");
		   	NoOfrenew=NoOfrenew+1;

		   Class.forName(driver);

		   Connection conn1=DriverManager.getConnection(url,"root","");

		   Statement stm1=conn.createStatement();

//////////////////////////////////////////////////////////////////////////////////
///////////////////////Set the Rerurn Date/////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

		   SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		    Date date = new Date();

		     long CurrentTime1 = date.getTime();

		     long SubmitTime =  (1000*3600*24*7*renewWeks) + CurrentTime1 ;

		     date.setTime(SubmitTime);

             String returndate = bartDateFormat.format(date);

             System.out.println("Submitdate  "+returndate);

//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

		    stm1.executeUpdate("UPDATE  lending SET Renewcounter='"+NoOfrenew+"' where assno = '"+bookno+"' ");
		    stm1.executeUpdate("UPDATE  lending SET returnDate='"+returndate+"' where assno = '"+bookno+"' ");
		    stm1.executeUpdate("UPDATE  lending SET oldTime='"+SubmitTime+"' where assno = '"+bookno+"' ");


	    	JOptionPane.showMessageDialog(this,"Book has been Renewed\n Refersh and see your new state","Message",JOptionPane.INFORMATION_MESSAGE);


		   }
      }

}
 catch(Exception gg)
	  {
	//     JOptionPane.showMessageDialog(this,"System failure ","Error",JOptionPane.QUESTION_MESSAGE);
		  System.out.println("System failure"+gg);
	  }


}

 public boolean isReserve(int index)
 {
 	String bookno = data[index][0];
 	String hasReserved="";
 	System.out.println("-------------Call is reserved------------------");
 	System.out.println(bookno);
   try
   {
   	 Class.forName(driver);
     Connection conn1=DriverManager.getConnection(url,"root","");
     Statement stm1=conn1.createStatement();
     ResultSet res1=stm1.executeQuery("SELECT * FROM reservation  WHERE ID  = '"+bookno+"'  " );

     while(res1.next())
     {
         hasReserved = res1.getString(2);
         System.out.println(hasReserved+"-------------------------------");
     }
     res1.close();
     if(hasReserved.equals(""))
     {
     	return false;
     }
     else
     {
     	return true;
     }
   }
   catch(Exception g)
   {
   	 System.out.println("System failure"+g);
   }
  return false;
 }



}

