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

 class Lost extends JFrame implements ActionListener,KeyListener
{
	JPanel p1,p2;
	JButton bt1,bt2,bt3,bt12,bt22,bt4;
	ImageIcon i1;
	JTable jt;
	JScrollPane jsp;
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
	String bookno;
	String StuName;
	String price;
	String Edit;
	String Tite;

	Double fine;


	String header[]=new String[]{"Book Number"," Title","Date","Return Date","Renewe Counter","Fine"};
	String data[][];
	int row;
    private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";


 public Lost()
	{
		super("Lost Books");
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




      ///////////////////************





      bt2 = new JButton("LOST");
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




			    	System.out.println("Key 1");

			    	findStudentName();
	     	    	tf1.setEnabled(false);
	     	     	updateStudentDetail();
	//		    	getAdminDetail();

			 	getBookDetail();

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
          int x = jt.getSelectedRow();
          System.out.println("Selected Row"+x);
          if(x==-1)
          {
          	JOptionPane.showMessageDialog(this,"Book hasn't been selected","Error",JOptionPane.QUESTION_MESSAGE);
          }
         else
             LostBooks(x);

		}
   	if(e.getSource()==bt3)
 		{

          Lost ob1  = new  Lost();
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
        	tf2.setText(res.getString(1));
			         	tf3.setText(res.getString(4));
			         	tf4.setText(res.getString(5));
			         	tf5.setText(res.getString(7));
			         	tf6.setText(res.getString(6));
			         	tf7.setText(res.getString(8));
         	tf8.setText(res.getString(9));
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






public void Clear()
{
 tfb2.setText("");
 tfb3.setText("");
 tfb4.setText("");
 tfb5.setText("");
}

public void getBookDetail()
{
		  boolean namefound;
	  try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM lending  WHERE StudentID = '"+StuID+"' ");


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


	           	 j++;
                     }

		 while(res.next());

		 jt = new JTable(data,header);
         jt.setSelectionBackground(new Color(255,255,100));
         jt.setForeground(new Color(35,6,255));
         jt.setBackground(new Color(165,248,255));
         jsp = new JScrollPane(jt);
         jsp.setBounds(210,450,660,120);
         p1.add(jsp);
		 bt2.setEnabled(true);
       }
   }

	catch(Exception e)
	{
	System.out.println(e);
	}


}

public void LostBooks(int rowIndex)
{
	int row = rowIndex;
     bookno = data[row][0];
    add_fine(row);
    Enter_Lost_Book();
    try
   {

   	     Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm1=conn.createStatement();

		 stm1.executeUpdate("UPDATE  books SET Status='LOST' WHERE assno = '"+bookno+"' ");

		 Statement stm4=conn.createStatement();

    	 stm4.executeUpdate("DELETE FROM lending where assno = '"+bookno+"'");

		 System.out.println("Updating....................");

		 JOptionPane.showMessageDialog(this," Book  has Lost ","Message",JOptionPane.INFORMATION_MESSAGE);

		 if_this_has_reserve(bookno);

   }
   catch(Exception e)
   {
   	System.out.println(""+e);
   }
 	/// Check wether this book has been reserve by someone
}

public void ClearFines()
{

    try
    {
      Class.forName(driver);

	  Connection conn=DriverManager.getConnection(url,"root","");

      Statement stm=conn.createStatement();

      stm.executeUpdate("UPDATE  student SET Fines ='0.0' where UserName = '"+StuID+"' ");

    }
	catch(Exception e)
	{

	}
}

public void add_fine(int row)
{
	int rowIndex = row;
	String amount = data[row][5];
    String student="";
    String old_amount="";

 try{

	Class.forName(driver);
	Connection conn=DriverManager.getConnection(url,"root","");
	Statement stm=conn.createStatement();

	ResultSet res1=stm.executeQuery("SELECT * FROM student_not_pay  WHERE ID  = '"+StuID+"' ");

	while(res1.next())
	{
		student = res1.getString(1);
		old_amount = res1.getString(2);
	}
   res1.close();

   if(student.equals(""))
   {
   	 stm.executeUpdate("insert  student_not_pay (ID,Fine)VALUE('"+StuID+"','"+amount+"') ");
	 System.out.println("Insert fine");

   }
   else
   {
   	    double fine_to_book = Double.parseDouble(amount);
   	    double old_fine = Double.parseDouble(old_amount);
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

 public void getPrice()
 {
 	try
 	{
    Class.forName(driver);
	Connection conn=DriverManager.getConnection(url,"root","");
	Statement stm=conn.createStatement();
	ResultSet res=stm.executeQuery("SELECT * FROM books  WHERE assno  = '"+bookno+"' ");

	while(res.next())
	{
	  price = res.getString(9);
	  Edit = res.getString(7);
	  Tite = res.getString(2);
	}
   }
   catch(Exception e)
 {
   System.out.println(e);
 }

 }

 public void Enter_Lost_Book()
 {
 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
 Date date = new Date();
 String CuurentDate = bartDateFormat.format(date);
 getPrice();


 try{
    Class.forName(driver);
	Connection conn=DriverManager.getConnection(url,"root","");
	Statement stm=conn.createStatement();
	stm.executeUpdate("insert into Lost_Book (BookID,StudentID,Price,Date) Value ('"+bookno+"','"+StuID+"','"+price+"','"+CuurentDate+"') ");
	}
 catch(Exception e)
 {
   System.out.println(e);
 }

 }

 public void if_this_has_reserve(String bookNumber)
 {
 String NewAssNo="";
 String bookNo = bookNumber;
  try
 	{
 	Class.forName(driver);
	Connection conn=DriverManager.getConnection(url,"root","");
	Statement stm=conn.createStatement();

	ResultSet res=stm.executeQuery("SELECT * FROM lending WHERE title  = '"+Tite+"' AND Edition = '"+Edit+"'  ");

	while(res.next())
	{
	  NewAssNo = res.getString(1);
	  res.first();
	  break;
	}
	res.close();
	Class.forName(driver);
	Connection conn1=DriverManager.getConnection(url,"root","");
	Statement stm1=conn1.createStatement();
    stm1.executeUpdate("UPDATE  reservation   SET ID   ='"+NewAssNo+"' where Title = '"+Tite+"' AND Edition = '"+Edit+"' ");

	}
catch(Exception e)
 {
   System.out.println(e);
 }


 }

 }

 class Lost_Book

{
	public static void main(String args[])
	{
	  Lost ob = new Lost();

	}

}