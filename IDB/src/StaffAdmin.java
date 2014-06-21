import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

class StaffAdmin extends JPanel{

	
	    JLabel photoes;
		JLabel lname,lstatus,laddress,lemail,ljoindate;
    	JTextField tname,tstatus,taddress,temail,tjoindate,tid2;
    	JLabel head;
		JLabel lsearchId,laddidetails,laddcoment,lpassword,admin,service;
		JTextField tsearchId,taddcoment;
		JButton bsearch,baddcoment,reset;
		JTextArea taaddidetails;
		JPasswordField password;
		String st[];
		
	public StaffAdmin(){
		
		setLayout(null);
    	setBorder(new TitledBorder("Details Mode"));
  
    	photoes = new JLabel(new ImageIcon("user.gif"));
    	photoes.setBounds(300,10,140,140);
    	add(photoes);
    	
    	lname = new JLabel("Name");
    	lname.setBounds(20,20,55,10);
    	add(lname);
    	
    	tname = new JTextField(10);
    	tname.setBounds(100,15,150,25);
    	add(tname);
    	
    	lstatus = new JLabel("Status");
    	lstatus.setBounds(20,45,50,10);
    	add(lstatus);
    	
    	tstatus= new JTextField(10);
    	tstatus.setBounds(100,40,150,25);
    	add(tstatus);
    	
    	laddress = new JLabel("Address");
    	laddress.setBounds(20,70,50,10);
    	add(laddress);
    	
    	taddress= new JTextField(10);
    	taddress.setBounds(100,65,150,25);
    	add(taddress);
    	
    	lemail = new JLabel("E-Mail");
    	lemail.setBounds(20,95,50,10);
    	add(lemail);
    	
    	temail= new JTextField(10);
    	temail.setBounds(100,90,150,25);
    	add(temail);
    	
    	ljoindate = new JLabel("Join Date");
    	ljoindate.setBounds(20,120,150,10);
    	add(ljoindate);
    	
    	tjoindate= new JTextField(10);
    	tjoindate.setBounds(100,115,150,25);
    	add(tjoindate);
  //.............................................. 
   	
    	head = new JLabel("Search User");
		head.setBounds(155,150,200,50);
		add(head);
		
		lsearchId = new JLabel("Staff ID NO");
		lsearchId.setBounds(30,160,100,100);
		add(lsearchId);
		
		tsearchId = new JTextField(10);
    	tsearchId.setBounds(120,200,100,20);
    	add(tsearchId);
    	
    	bsearch = new JButton("Search");
    	bsearch.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			
    			String id = tsearchId.getText();
                GetData obj =  new GetData();
                st=  obj.retData(id);
                setValue();
    		}
    	});
    	
		bsearch.setBounds(250,200,90,20);
		add(bsearch);
		
		laddidetails = new JLabel("Aditional Details");
		laddidetails.setBounds(20,240,100,100);
		add(laddidetails);
		
		taaddidetails = new JTextArea("He is preson that has responsible to adminstor/Circulation sectoin");
		JScrollPane spane = new JScrollPane(taaddidetails); 
		spane.setBounds(160,280,240,100);
		add(spane);
		
		laddcoment = new JLabel("Add Comment");
		laddcoment.setBounds(20,348,100,100);
		add(laddcoment);
		
		taddcoment= new JTextField(10);
    	taddcoment.setBounds(170,390,200,20);
    	add(taddcoment);
    	
    	baddcoment = new JButton("Add ");
    	baddcoment.setBounds(160,430,90,20);
    	add(baddcoment);
				
		JPanel pae = new JPanel();
		pae.setLayout(null);
		pae.setBorder(new TitledBorder("Change Password"));
		
		lsearchId.setBounds(20,28,100,10);
    	pae.add(lsearchId);
    	
	    tid2 = new JTextField(10);
		tid2.setBounds(130,25,150,25);
    	pae.add(tid2);
		
		lpassword = new JLabel("Password");
		lpassword.setBounds(20,50,100,40);
		pae.add(lpassword);
		
		password = new JPasswordField(10);
		password.setBounds(130,55,150,25);
		pae.add(password);
		
		reset = new JButton("Reset");
    	reset.setBounds(310,40,70,20);
    	pae.add(reset);
    	pae.setBounds(0,480,400,100);
    	add(pae);
    	
	}
	
	public void setValue(){
		tname.setText(st[1]);
		tstatus.setText(st[2]);
		taddress.setText(st[3]);
		temail.setText(st[4]);
		tjoindate.setText(st[5]);
		taaddidetails.setText(st[6]);
	}
	
//	public String sendId(){
		
		
//	}	


}

class GetData{
		String url = "jdbc:mysql://localhost/student";
		String driver = "com.mysql.jdbc.Driver";
		Connection con= null;
		ResultSet res =null;
		Statement s= null;
		static String getid;
	
//*****************		
//	public String[] m(){
//		
//		return data;
//	}
//*********************	*/
	
	public GetData( ){
				
		try{
			Class.forName(driver).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		try{
			con = DriverManager.getConnection(url,"root","");
			System.out.println("Connection Succesfuly");
		}catch(SQLException e2){
			e2.printStackTrace();
		}
		
		try{
		    s = con.createStatement();
		//	int val= s.executUpdate("INSERT INTO);
		}catch(SQLException e3){
			e3.printStackTrace();
		}
		
		
	}
	
	
		
		
		public String[] retData( String id){
			
			String data[]=new String[9];
		    getid = id;
			//System.out.println("id value is"+getid);
			try{
			
			res = s.executeQuery("SELECT * FROM staff WHERE UserName ='"+getid+"'");
		//  ResultSetMetaData metadata = res.getMetaData();
	        
		
		    while(res.next()){
			
			data[0]=res.getString(2);
			data[1]=res.getString(1);
			data[2]=res.getString(13);
			data[3]=res.getString(7);
			data[4]=res.getString(5);
			data[5]=res.getString(8);
			data[6]=res.getString(3);
		//	data[7]=res.getString(8);
			//data[8]=res.getString(9);
	
		}
	        	
		}catch(SQLException e4){
			e4.printStackTrace();
		}
		
		finally{
			if(con!=null){
				try{
					res.close();
					s.close();
					con.close();
					
				}catch(SQLException ee){
					ee.printStackTrace();
				}
			}
		}
		return data;
		
	}
	
	
/*	public void addComent(){
		try{
			
			res.ExecuteQuery("SELECT additional_data FROM staff_details)
		}catch(SQLException e6){
			e6.printStackTrace()
		}
	}*/
	
	public void savePrivilage(String[] absob){
		int databse[] = new int[30];
		String[] tree ={"Vender Details","Book Selection","Selected Orders","Order History","Acquisition",
		"Apply Classify Number","Remove Classify Number","Set Keyword for Search","Book Lending","Book Receive","Books Lost","Books Fine","Set User Privileges","Change Password","Change System Information","Admin4","User Registration","Staff Registration","Performance Evaluation","Recommendations","Simple Search","Advance Search","Aditional Search","Browse","ISBN Search","","","","",""};
	    for(int a=0; a<26; a++){
	    	//System.out.println(absob[a]);
	    	for(int b=0; b<26; b++){
	    		System.out.println(absob[b]);
	    		if(tree[a]==absob[b]){
	    			databse[a]=1;
	    		}
	    	}
	    	//System.out.println(databse[a]);
	    }
		
		storeTreeData(databse);
	}
	
	
	public void storeTreeData(int stdata[] ){
			System.out.println("id value is"+getid);
			reset();
//			for(int k=0;k<30;k++){
//				String s="a";
//				s=s+""+k;
//				s =Integer.toString((stdata[k]));
//				
//				System.out.println(s);
//			}
			  	
			  try{
			  	
		    		s = con.createStatement();
				   // res = s.executeQuery("UPDATE idb.staff_pri SET id = '2',1 = '1',2 = '1',3 = '1',4 = '1',5 = '1',6 = '1',7 = '1',8 = '1',9 = '1' WHERE staff_pri.id =1 LIMIT 1 ;");
				     System.out.println("8*********************");
				     s.executeUpdate("UPDATE student.staff_priv SET a = '"+stdata[0]+"',b = '"+stdata[1]+"',c = '"+stdata[2]+"',d = '"+stdata[3]+"',e = '"+stdata[4]+"',f = '"+stdata[5]+"',g = '"+stdata[6]+"',h = '"+stdata[7]+"',i = '"+stdata[8]+"',j = '"+stdata[9]+"',k = '"+stdata[10]+"',l = '"+stdata[11]+"',m = '"+stdata[12]+"',n = '"+stdata[13]+"',o = '"+stdata[14]+"',p = '"+stdata[15]+"',q = '"+stdata[16]+"',r = '"+stdata[17]+"',s = '"+stdata[18]+"',t = '"+stdata[19]+"',u = '"+stdata[20]+"',v = '"+stdata[21]+"',w = '"+stdata[22]+"',x = '"+stdata[23]+"',y = '"+stdata[24]+"',z = 'RR' WHERE staff_priv.id ='"+getid+"'");
				     System.out.println("id value is***-----------");
		     }catch(SQLException e3){
		      
			  e3.printStackTrace();
		}	
			  	
			 
	}
	
	public void reset()
	{
	
	
	
	try{	 
    	 Class.forName(driver);

		 Connection conn=DriverManager.getConnection(url,"root","");

		 Statement stm=conn.createStatement();
    	   stm.executeUpdate("INSERT INTO `staff_priv` VALUES ('"+getid+"', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0')");
      	}
      	catch(Exception rr)
      	{
      	System.out.println("Refrashing.................."+rr);	
      	}
     } 	////////////////////////////
     }
