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


public class AddVote extends JFrame implements ActionListener
{
	JPanel p1;
	JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
	JTable jt;
	JScrollPane jsp;
	JLabel la1,la2;
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);

	String userID,name;
	String header[]=new String[]{"RequestID","Title","Auther","Edition","Vote"};
	String data[][];
	int row;

	private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";

 public AddVote(String un,String fname)
	{
		super("...::::"+fname+ "   IDB LibSys ");
		userID= un;
		name = fname;
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


      /////*******************************\\\\\\
      bt1 = new JButton(" Request Book ");
      bt1.setBounds(35,65,120,40);
      bt1.addActionListener(this);
      p1.add(bt1);

      bt2 = new JButton("Vote for Books");
      bt2.setBounds(35,120,120,40);
      bt2.setEnabled(false);
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

      bt7 = new JButton("Home");
      bt7.setBounds(790,25,80,30);
      bt7.addActionListener(this);
      p1.add(bt7);

      bt8 = new JButton("Vote");
      bt8.setBounds(770,570,90,40);
      bt8.addActionListener(this);
      p1.add(bt8);

 /*     bt9 = new JButton("More Detial");
      bt9.setBounds(740,570,120,40);
      bt9.addActionListener(this);
      p1.add(bt9);*/

      bt6 = new JButton("Refresh");
      bt6.setBounds(870,570,90,40);
      bt6.addActionListener(this);
      p1.add(bt6);

      jt = new JTable(data,header);
      jt.setSelectionBackground(new Color(255,255,100));
      jt.setForeground(new Color(35,6,255));
      jt.setBackground(new Color(165,248,255));
      jsp = new JScrollPane(jt);
      jsp.setBounds(300,140,660,400);
      p1.add(jsp);

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

 	      requestBook rq = new	requestBook(userID,name);
 	     this.setVisible(false);
 	      System.out.println("Requesting books");

 		}
 	if(e.getSource()==bt6)
 		{
		  AddVote ob1 = new AddVote(userID,name);
		  bt8.setEnabled(false);
 		  this.setVisible(false);
    	    System.out.println("Refreshing.....");
 		}
    if(e.getSource()==bt3)
 		{
	      StuHistory ob1 = new StuHistory(userID,name);
 		  this.setVisible(false);
		  System.out.println("History");
 		}
    if(e.getSource()==bt4)
 		{
 		  reservation ob1 = new reservation(userID,name);
 		  this.setVisible(false);
		  System.out.println("vote");

 		}
    if(e.getSource()==bt8)
 		{
 	             boolean selected = false;
 	             int x = 10000;

                  x = jt.getSelectedRow();
                   System.out.println("Selected Row"+x);
                 if(x>=0){
 	               selected  =  true;
 	            }

 	         if(selected)
 	         {
 	         	   voteBooks(x);
 	               bt8.setEnabled(false);
 	         }
 	            else
 	            {
 	       	JOptionPane.showMessageDialog(this,"Please Select Book ","Message",JOptionPane.ERROR_MESSAGE);

 	           	}


        }


  	}

 public void viewDetail()
 {
   try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM request");

           res.last();
		   row=res.getRow();
		   res.first();

           data =new String[row][5];
           int j=0;

   if(row>0)
   {

         do
            {

					 data[j][0] = res.getString(1);
					 data[j][1] = res.getString(2);
					 data[j][2] = res.getString(3);
					 data[j][3] = res.getString(6);
					 data[j][4] = res.getString(12);

				   	j++;

		  	}

		  while(res.next());
   }

	      }
	  catch(Exception gg)
	  {
	//	JOptionPane.showMessageDialog(this,"System failure ","Error",JOptionPane.QUESTION_MESSAGE);
		 System.out.println("System failure");
	  }

 }


public void voteBooks(int rowIndex)
{
 int row = rowIndex;
 String id = data[row][0];
 String voteCounter;

   try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM  request WHERE RequestID  = '"+id+"'");

		   while(res.next())
		   {
		     voteCounter=res.getString(12);
		   	System.out.println("****************"+voteCounter);

		   	int NoOfVote = Integer.parseInt(voteCounter);


		   	NoOfVote=NoOfVote + 1;

		    Class.forName(driver);

		    Connection conn1=DriverManager.getConnection(url,"root","");

		    Statement stm1=conn.createStatement();

		    stm1.executeUpdate("UPDATE  request SET votes='"+NoOfVote+"' WHERE RequestID  = '"+id+"' ");


	    	JOptionPane.showMessageDialog(this,"Your Vote has been added","Message",JOptionPane.INFORMATION_MESSAGE);

		   }
      }

 catch(Exception gg)
	  {
//	     JOptionPane.showMessageDialog(this,"System failure ","Error",JOptionPane.QUESTION_MESSAGE);
		  System.out.println("System failure"+gg);
	  }




}
}

