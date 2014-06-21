import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;


class GpaCounter extends JFrame implements ActionListener {
	JLabel head,sem,sub,pas,suboop,subdc,submath,subict,subweb,subnet,submanag;
	ImageIcon icon;
	JPanel pane1;
	Color col1,col2;
	JComboBox oop,ict,dc,math,web,net,manag;
    String result[]={"...","A+","A","A-","B+","B","B-","C+","C","C-","D","I"};
    JButton ok,exit ;	
	
 public GpaCounter(){
 	super("FACULTY OF INFOMATION TECHNOLOGY");
    setSize(500,400);
    setLocation(180,120);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
   // setUndecorated(true);
    guiCreat();
    
    }
    
 public void guiCreat(){
   
    col1 =new Color(000,000,255);
    col2 =new Color(000,220,000);
    
    
       
    icon = new ImageIcon("123.gif");
    head = new JLabel(icon);
    head.setBounds(5,5,480,70);
    
    
    Font f=new Font("Arial",Font.BOLD,20);
    sem  = new JLabel("BATCH 06 GPA COUNTER");
    sem.setForeground(new Color(250,30,50));//change font color
    sem.setBounds(170,60,300,60);
    sem.setFont(f);
    
    Font f2 =new Font("Courier",Font.BOLD,18);
    sub = new JLabel("SUBJECT");
    sub.setBounds(60,100,200,40);
    sub.setFont(f2);
    sub.setForeground(col1);
    
    pas = new JLabel("RESELT");
    pas.setBounds(350,100,200,40);
    pas.setFont(f2);
    pas.setForeground(col1);
    
    Font f3 =new Font("Times New Roman",Font.BOLD,18);
    suboop = new JLabel("Data Structure");
    suboop.setFont(f3);
    suboop.setBounds(20,130,300,40);
    suboop.setForeground(col2);
    oop =new JComboBox(result);
    oop.setBounds(350,142,60,20);
    oop.setMaximumRowCount(5);
    oop.addActionListener(this);
    
    subict = new JLabel("Multimedea");
    subict.setFont(f3);
    subict.setBounds(20,160,300,40);
    subict.setForeground(col2);
    ict =new JComboBox(result);
    ict.setBounds(350,170,60,20);
    ict.setMaximumRowCount(5);
    
    subdc = new JLabel("Mathamatics");
    subdc.setFont(f3);
    subdc.setBounds(20,190,300,40);
    subdc.setForeground(col2);
    dc =new JComboBox(result);
    dc.setBounds(350,200,60,20);
    dc.setMaximumRowCount(5);
 
    subnet = new JLabel("Computer Organization");
    subnet.setFont(f3);
    subnet.setBounds(20,220,300,40);
    subnet.setForeground(col2);
    net =new JComboBox(result);
    net.setBounds(350,230,60,20);
    net.setMaximumRowCount(5);
 
    subweb = new JLabel("Project");
    subweb.setFont(f3);
    subweb.setBounds(20,250,300,40);
    subweb.setForeground(col2);
    web=new JComboBox(result);
    web.setBounds(350,260,60,20);
    web.setMaximumRowCount(5);
    
    submath = new JLabel("M I S");
    submath.setFont(f3);
    submath.setBounds(20,280,300,40);
    submath.setForeground(col2);
    math =new JComboBox(result);
    math.setBounds(350,290,60,20);
    math.setMaximumRowCount(5);
    
    submanag = new JLabel("Managment");
    submanag.setFont(f3);
    submanag.setBounds(20,310,300,40);
    submanag.setForeground(col2);
    manag =new JComboBox(result);
    manag.setBounds(350,320,60,20);
    manag.setMaximumRowCount(5);
    
    ok = new JButton("OK");
    ok.setBounds(375,355,70,30);
    ok.addActionListener(this);
    
    exit = new JButton("EXIT");
    exit.setBounds(265,355,70,30);
    exit.addActionListener(this);
 
 
    
    //jlist1.setBounds(10,120,200,20);
    
    pane1= new JPanel();
    pane1.setLayout(null);
    EtchedBorder br = new EtchedBorder(2,Color.red,Color.green);
    
    
    
   
    
    head.setBorder(br);
    pane1.add(head);
    pane1.add(sem);
    pane1.add(pas);
    pane1.add(sub);
    pane1.add(subict);
    pane1.add(suboop);
    pane1.add(subdc);
    pane1.add(subnet);
    pane1.add(subweb);
    pane1.add(submath);
    pane1.add(submanag);
    pane1.add(oop);
    pane1.add(ict);
    pane1.add(net);
    pane1.add(dc);
    pane1.add(web);
    pane1.add(math);
    pane1.add(manag);
    pane1.add(ok);
    pane1.add(exit);
    
    //pane1.add(jlist1);
    getContentPane().add(pane1);
    pane1.setBackground(new Color(230,220,200));
    setResizable(false);
    setVisible(true);
    
 }
 
 public void actionPerformed(ActionEvent event){
 	
 
 	String s1  = (String)oop.getSelectedItem();
 	String s2  = (String)ict.getSelectedItem();
 	String s3  = (String)dc.getSelectedItem();
 	String s4  = (String)net.getSelectedItem();
 	String s5  = (String)web.getSelectedItem();
 	String s6  = (String)math.getSelectedItem();
 	String s7  = (String)manag.getSelectedItem();
 	
 	if(event.getSource()==ok)
 	 print(s1,s2,s3,s4,s5,s6,s7);
 	 
 	if(event.getSource()==exit) 
	 	 System.exit(0);
 	}
 	
 public void print(String c1,String c2,String c3,String c4,String c5,String c6,String c7){
 		
 	double sc1 = resultCatch(c1);
 	double sc2 = resultCatch(c2);
	double sc3 = resultCatch(c3);
 	double sc4 = resultCatch(c4);
 	double sc5 = resultCatch(c5);
 	double sc6 = resultCatch(c6);
 	double sc7 = resultCatch(c7);
 	
	double gpa=((sc1*3)+(sc2*3)+(sc3*2.5)+(sc4*2.5)+(sc5*2.5)+(sc6*2)+(sc7*2))/17.5;
 	JOptionPane.showMessageDialog(null,"Your GPA value is :"+gpa," ",JOptionPane.PLAIN_MESSAGE);
 	
 	}
 	
 	
 
 public double resultCatch(String res){
 	 String a = res;
 	if(res=="A+")
 	 return 4.2;
 	
 	 else if(res=="A")
 	   return 4.0;
 	
 	 else if(res=="A-")
 	   return 3.7;
 	
 	 else if(res=="B+")
 	   return 3.3;
 	
 	 else if(res=="B")
 	   return 3.0;
 	
 	 else if(res=="B-")
 	   return 2.7;
 	   
 	 else if(res=="C+")
 	   return 2.3;
 	   
 	 else if(res=="C")
 	   return 2;
 	   
 	 else if(res=="C-")
 	   return 1.5;
 	   
 	 else if(res=="D")
 	   return 1;
 	   
 	 else if(res=="I")
 	   return 0;
 	 else  
 return 0;	
 }
  

 public static void main (String args[]){
 //	GpaCounter cal=new GpaCounter();
 }
 	
}
