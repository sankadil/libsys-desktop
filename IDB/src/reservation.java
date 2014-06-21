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


class reservation extends JFrame implements ActionListener
{
	JPanel p1;  JPanel p2;  JPanel p3;
	JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
	JTable jt;
	JLabel la1,la2,la3,la4,la5,la6,la7,la8,la9;
	JScrollPane jsp;
	JComboBox cb;
	JTextField tf1,tf2,tf3,tf4,tf5;
	JTextField tf6,tf7,tf8,tf9,tf10,tf11,tf12;
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);

	String header[]=new String[]{"Title","Date","Return Date","Renewe Counter","Fine"};
	String data[][]=new String[4][5];
	String userID,name;
	
	private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";

 public reservation(String un,String fname)
	{
		super("...::::"+fname+ "   IDB LibSys ");
		userID= un;
		name = fname;
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


 //     borlabel1 = new JLabel();
 //     EtchedBorder bor1 = new EtchedBorder(2,Color.red,Color.green);

 //     borlabel1.setBorder(bor1);
//      p1.add(borlabel1);

      /////*******************************\\\\\\
      bt1 = new JButton(" Request Book ");
      bt1.addActionListener(this);
      bt1.setBounds(35,65,120,40);
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
      bt4.setEnabled(false);
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
      
      la3 = new JLabel("Title          ");
      la3.setBounds(220,117,500,100);
      la3.setFont(fo);
      p1.add(la3);
      
      la4 = new JLabel("Auther       ");
      la4.setBounds(220,155,500,100);
      la4.setFont(fo);
      p1.add(la4);
      
      la5 = new JLabel("Edition        ");
      la5.setBounds(220,225,500,100);
      la5.setFont(fo);
      p1.add(la5);
      
      la6 = new JLabel("ISBN           ");
      la6.setBounds(220,265,500,100);
      la6.setFont(fo);
      p1.add(la6);
      
      la7 = new JLabel("Publisher      ");
      la7.setBounds(220,305,500,100);
      la7.setFont(fo);
      p1.add(la7);
      

      bt5 = new JButton("Logout");
      bt5.setBounds(880,25,80,30);
      bt5.addActionListener(this);
      p1.add(bt5);
      
      bt6 = new JButton("Clear");
      bt6.setBounds(770,570,90,40);
      bt6.addActionListener(this);
      p1.add(bt6);

      
      bt7 = new JButton("Home");
      bt7.setBounds(790,25,80,30);
      bt7.addActionListener(this);
      p1.add(bt7);
      
      
      bt8 = new JButton("Reserve");
      bt8.setBounds(870,570,90,40);
      bt8.addActionListener(this);
      p1.add(bt8);


      tf1 = new JTextField();
      tf1.setBounds(350,150,300,25);
      tf1.setFont(fo1);
      p1.add(tf1);
      
      tf2 = new JTextField();
      tf2.setBounds(350,190,300,25);
      tf2.setFont(fo1);
      p1.add(tf2);
      
      tf3 = new JTextField();
      tf3.setBounds(350,220,300,25);
      tf3.setFont(fo1);
      p1.add(tf3);
      
      tf4 = new JTextField();
      tf4.setBounds(655,190,300,25);
      tf4.setFont(fo1);
      p1.add(tf4);
      
      String Edi[] = new String[]{"Select","1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
      cb = new JComboBox(Edi);
      cb.setBounds(350,260,80,25);
      p1.add(cb);
      
      tf5 = new JTextField();
      tf5.setBounds(350,300,130,25);
      tf5.setFont(fo1);
      p1.add(tf5);
      
      tf6 = new JTextField();
      tf6.setBounds(350,340,300,25);
      tf6.setFont(fo1);
      p1.add(tf6);
      

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
 	if(e.getSource()==bt8)
 		{
 		  String title=tf1.getText();
 		  String edi = (String)cb.getSelectedItem();
 		  String auther1=tf2.getText();
 		  String auther2=tf3.getText();
 		  String auther3=tf4.getText();

 		  if(title==null || edi.equals("Select"))
 		  {
 			JOptionPane.showMessageDialog(this,"Yo should fill atleast title,auther and edition","Error",JOptionPane.ERROR_MESSAGE);  	
 		  }
 		  else if((auther1==null)&&(auther2==null)&&(auther3==null))
 		  {
 		  	JOptionPane.showMessageDialog(this,"Yo should fill atleast title,auther and edition","Error",JOptionPane.ERROR_MESSAGE);
 		  }
 		else
 		  {
 		  	String noBook = getAss_No_For_reservation(title);
 		  	dataBaseUpdate(noBook);
 		  }

 		}
 	if(e.getSource()==bt6)
 		{
 		 tf1.setText("");
 		 tf2.setText("");
 		 tf3.setText("");
 		 tf4.setText("");
 		 tf5.setText("");
 		 tf6.setText("");
 		 cb.setSelectedIndex(0);
 		}
 		
 	if(e.getSource()==bt1)
 		{
 	      requestBook rq = new	requestBook(userID,name);
 	      this.setVisible(false);
 	      System.out.println("Requesting books");	 
 		}
 		
 	if(e.getSource()==bt2)
 		{
 		  AddVote ob1 = new AddVote(userID,name);
 		  this.setVisible(false);
		  System.out.println("vote");

 		}
 	if(e.getSource()==bt3)
 		{
 		  StuHistory ob1 = new StuHistory(userID,name);
 		  this.setVisible(false);
		  System.out.println("History");
 		}		
  					
 	}
 	
 	public void dataBaseUpdate(String no)
 	{
 		String No = no;
 	 try{
 			 Class.forName(driver);

			 Connection conn=DriverManager.getConnection(url,"root","");

			 Statement stm=conn.createStatement();
	         stm.executeUpdate("INSERT INTO reservation (ID,Title,author ,Auther2,Auther3,Edition,ISBN,Publisher,StudentID,StudentName) VALUE('"+No+"','" + tf1.getText() + "','"+tf2.getText()+"','" + tf3.getText() + "','"+ tf4.getText() +"','"+(String)cb.getSelectedItem()+"','" + tf5.getText()+ "','"+tf6.getText()+"','"+userID+"','"+name+"')");
			 
		     JOptionPane.showMessageDialog(this,"Your request is accept","Message",JOptionPane.INFORMATION_MESSAGE);	
   } 
		  
catch(Exception e)
		{
     //      JOptionPane.showMessageDialog(this,"System error","Error",JOptionPane.QUESTION_MESSAGE);
		   System.out.println(e);

		}	
 	}
 	
 	public String getAss_No_For_reservation(String tit)
 	{
 		String title = tit;
 		String date = "";
 		String bookNoto_reservation="";
 		try
 		{
		   Class.forName(driver);
		   Connection conn=DriverManager.getConnection(url,"root","");
		   Statement stm=conn.createStatement();
		   ResultSet res=stm.executeQuery("SELECT  min(Date) from lending where title  = '"+tit+"' ");
		   
		   while(res.next())
		   {
		   	 date = res.getString(1);
		   	
		   }
		   res.close();
		   
		   Statement stm1=conn.createStatement();
		   ResultSet res1=stm1.executeQuery("SELECT * from lending where Date  = '"+date+"' ");
		   while(res1.next())
		   {
		   	 bookNoto_reservation = res1.getString(1);
		   	
		   }
		   res1.close();
		   
 		}
 		catch(Exception e)
		{
     //      JOptionPane.showMessageDialog(this,"System error","Error",JOptionPane.QUESTION_MESSAGE);
		   System.out.println(e);

		}
 	   return bookNoto_reservation; 	
 	}
 	
 }
 
