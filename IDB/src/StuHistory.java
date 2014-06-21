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


public class StuHistory extends JFrame implements ActionListener
{
	JPanel p1;
	JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
	JTable jt;
	JScrollPane jsp;
	JLabel la1,la2;
	Font fo1 = new Font("Times New Roman",Font.BOLD,16);
	Font fo = new Font("Courier New",Font.BOLD,18);

	String userID,name;
	String header[]=new String[]{"Book No","Title","Data","Return date","Submited date","Fine"};
	String data[][];
	
	private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";
    int row;

 public StuHistory(String un,String fname)
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
      bt2.addActionListener(this);
      p1.add(bt2);

      bt3 = new JButton(" View History ");
      bt3.setBounds(35,175,120,40);
      bt3.setEnabled(false);
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
      
      bt6 = new JButton("Refresh");
      bt6.setBounds(870,570,90,40);
      bt6.addActionListener(this);
      p1.add(bt6);

      bt7 = new JButton("Home");
      bt7.setBounds(790,25,80,30);
      bt7.addActionListener(this);
      p1.add(bt7);
      
     
      jt = new JTable(data,header);
      jsp = new JScrollPane(jt);
      jt.setSelectionBackground(new Color(255,255,100));
      jt.setForeground(new Color(35,6,255));
      jt.setBackground(new Color(165,248,255));
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
 	if(e.getSource()==bt2)
 		{
 	     	AddVote ob1 = new AddVote(userID,name);
 		  this.setVisible(false);
		  System.out.println("vote");
		
 		}
 	if(e.getSource()==bt6)
 		{
 	   	viewDetail();
 	    System.out.println("Refreshing....."); 
 		}	
    if(e.getSource()==bt4)
 		{
 		  reservation ob1 = new reservation(userID,name);
 		  this.setVisible(false);
		  System.out.println("vote");

 		}					    
 	}
 	
 public void viewDetail()
 {
   try
	  {
		   Class.forName(driver);

		   Connection conn=DriverManager.getConnection(url,"root","");

		   Statement stm=conn.createStatement();

		   ResultSet res=stm.executeQuery("SELECT * FROM Histroy where ID ='"+userID+"' ");
		   res.last();
		   row=res.getRow();
		   res.first();
          
          data=new String[row][7];
          
          int j=0;
  
 if(row>0){
         
          do{

					 data[j][0] = res.getString(1);
					 data[j][1] = res.getString(2);
					 data[j][2] = res.getString(3);
					 data[j][3] = res.getString(4);
					 data[j][4] = res.getString(5);
					 data[j][5] = res.getString(6);
					 data[j][6] = res.getString(7);
				   	j++;
				   	
				   	} 
				   	
		   while(res.next());
     } 
         
	}

	      
	  catch(Exception gg)
	  {
//		JOptionPane.showMessageDialog(this,"System failure","Error",JOptionPane.QUESTION_MESSAGE);
		 System.out.println("System failure"+gg);
	  }
 	
 }	

}
 
