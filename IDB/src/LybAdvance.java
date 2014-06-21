import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.image.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LybAdvance implements ActionListener,MouseListener,KeyListener
{
private JButton clear,quit,search,detail,reserve;
private JTextField keyword1,keyword2,keyword3;
private JComboBox field_type_selecter1,field_type_selecter2,field_type_selecter3,words_connecter_selecter1,words_connecter_selecter2,words_connecter_selecter3,inter_wordconnecter1,inter_wordconnecter2,database_selecter;
private String title[]={"title","author","subject","ISBN"};
private String connection[]={"AND","OR","PHRASE"};
private String inter_connection[]={"AND","OR"};
private String heading[]={"author","title","classno"};
private String data_bases[]={"Books","Theses","journal","CD","DVD"};
private JPanel jp3;
private String [][] data=new String[100][3];
private String [][] results=new String[100][6];
private JTable table;
private int length;
private static Opac l;
public LybAdvance()
{

//super("OPAC-Open Public Access Catalog");

	}

public void guiadsearch(JTabbedPane jTabbedPane1, Opac lref)
{
l=lref;
 jp3=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp3.setOpaque(false);
jp3.setLayout(null);
//jp3.setLayout(null);
//jp3.setBackground(Color.pink);
//--buttton set--------------------------------

reserve=new JButton("reservation");
reserve.setBounds(900,545,100,35);
jp3.add(reserve);
reserve.addActionListener(this);

ImageIcon img = new ImageIcon("search.jpg");
search=new JButton("search");
search.setBounds(900,505,100,35);
jp3.add(search);
search.addActionListener(this);

clear=new JButton("Clear");
clear.setBounds(900,585,100,35);
jp3.add(clear);
clear.addActionListener(this);

quit=new JButton("Quit");
quit.setBounds(900,665,100,35);
jp3.add(quit);
quit.addActionListener(this);

detail=new JButton("Print");
detail.setBounds(900,625,100,35);
jp3.add(detail);
detail.addActionListener(this);


//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,14);
Color ctf1=new Color(255,255,0);
Font tf=new Font("Tahoma",Font.BOLD,14);


//---------------------------------------------------------------------------------------------------------------------------------------



//---------------------------------------------
JLabel feild_lable1=new JLabel("Field");
feild_lable1.setBounds(10,50,100,25);
feild_lable1.setFont(f);
feild_lable1.setForeground(ctf1);
jp3.add(feild_lable1);

field_type_selecter1 =new JComboBox(title);
field_type_selecter1.setBounds(50,50,75,25);
field_type_selecter1.setFont(tf);
jp3.add(field_type_selecter1);
field_type_selecter1.addActionListener(this);

JLabel jl1=new JLabel("Search keyword(s)");
jl1.setBounds(150,50,180,25);
 jl1.setFont(f);
 jl1.setForeground(ctf1);
jp3.add(jl1);
keyword1=new JTextField("martin");
keyword1.setBounds(300,50,180,25);
keyword1.setFont(tf);
jp3.add(keyword1);
keyword1.addKeyListener(this);
 keyword1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

 getsearch();
            }
        });

JLabel jl2=new JLabel("connect words as");
jl2.setBounds(500,50,150,25);
jl2.setFont(f);
jl2.setForeground(ctf1);
jp3.add(jl2);
words_connecter_selecter1=new JComboBox(connection);
words_connecter_selecter1.setBounds(650,50,75,25);
words_connecter_selecter1.setFont(tf);
jp3.add(words_connecter_selecter1);
words_connecter_selecter1.addActionListener(this);

//-----------------------------------------------

inter_wordconnecter1=new JComboBox(inter_connection);
inter_wordconnecter1.setBounds(300,100,75,25);
inter_wordconnecter1.setFont(tf);
jp3.add(inter_wordconnecter1);
inter_wordconnecter1.addActionListener(this);

//-----------------------------------------------
JLabel jl3=new JLabel("Field");
jl3.setBounds(10,150,100,25);
jl3.setFont(f);
jl3.setForeground(ctf1);
jp3.add(jl3);
field_type_selecter2 =new JComboBox(title);
field_type_selecter2.setBounds(50,150,75,25);
field_type_selecter2.setFont(tf);
jp3.add(field_type_selecter2);
field_type_selecter2.addActionListener(this);


JLabel keyword_label1=new JLabel("Search keyword(s)");
keyword_label1.setBounds(150,150,180,25);
keyword_label1.setFont(f);
keyword_label1.setForeground(ctf1);
jp3.add(keyword_label1);
keyword2=new JTextField("network");
keyword2.setBounds(300,150,180,25);
keyword2.setFont(tf);
jp3.add(keyword2);
keyword2.addKeyListener(this);
 keyword2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

 getsearch();
            }
        });


JLabel keyword_conect2=new JLabel("connect words as");
keyword_conect2.setBounds(500,150,150,25);
keyword_conect2.setFont(f);
keyword_conect2.setForeground(ctf1);
jp3.add(keyword_conect2);
words_connecter_selecter2=new JComboBox(connection);
words_connecter_selecter2.setBounds(650,150,75,25);
words_connecter_selecter2.setFont(f);
jp3.add(words_connecter_selecter2);
words_connecter_selecter2.addActionListener(this);

//-----------------------------------------------------------
inter_wordconnecter2=new JComboBox(inter_connection);
inter_wordconnecter2.setBounds(300,200,75,25);
inter_wordconnecter2.setFont(f);
jp3.add(inter_wordconnecter2);
inter_wordconnecter2.addActionListener(this);

//-----------------------------------------------
JLabel field_label3=new JLabel("Field");
field_label3.setBounds(10,250,100,25);
field_label3.setFont(f);
field_label3.setForeground(ctf1);

jp3.add(field_label3);
field_type_selecter3 =new JComboBox(title);
field_type_selecter3.setBounds(50,250,75,25);
field_type_selecter3.setFont(tf);
jp3.add(field_type_selecter3);
field_type_selecter3.addActionListener(this);

JLabel keyword_label3=new JLabel("Search keyword(s)");
keyword_label3.setBounds(150,250,180,25);
 keyword_label3.setFont(f);
 keyword_label3.setForeground(ctf1);
jp3.add(keyword_label3);


keyword3=new JTextField("computer");
keyword3.setBounds(300,250,180,25);
keyword3.setFont(tf);
jp3.add(keyword3);
keyword3.addKeyListener(this);
 keyword3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

 getsearch();
            }
        });

JLabel jl222=new JLabel("connect words as");
jl222.setBounds(500,250,150,25);
jl222.setFont(f);
jl222.setForeground(ctf1);
jp3.add(jl222);
words_connecter_selecter3=new JComboBox(connection);
words_connecter_selecter3.setBounds(650,250,75,25);
words_connecter_selecter3.setFont(f);
jp3.add(words_connecter_selecter3);
words_connecter_selecter3.addActionListener(this);
//------------------------------------------------------------

JLabel jl4=new JLabel("Database");
jl4.setBounds(800,50,100,25);
jl4.setFont(f);
jl4.setForeground(ctf1);
jp3.add(jl4);

database_selecter=new JComboBox(data_bases);
database_selecter.setBounds(875,50,75,25);
database_selecter.setFont(f);
jp3.add(database_selecter);
database_selecter.addActionListener(this);

//getob.add(jp3);
display();
jTabbedPane1.addTab("Advance Searching", jp3);


	}

public void mouseClicked(MouseEvent eq)
{
	//more_details();
	}
public void mouseExited(MouseEvent ew)
{
	//more_details();
	}
//mouseEntered(MouseEvent e) mousePressed(MouseEvent e) mouseReleased(MouseEvent e)

public void mousePressed(MouseEvent ee)
{
	more_details();
	}
public void mouseReleased(MouseEvent er)
{
	//more_details();
	}
public void mouseEntered(MouseEvent et)
{
	//more_details();
	}
//----------------------------------------------------------------------------------------------------------------------------
public void clear_table()
{


	for(int i=0;i != length;i++)

		{
data[i][0]=null;
data[i][1]=null;
data[i][2]=null;
//data[i][3]=null;

results[i][0]=null;
results[i][1]=null;
results[i][2]=null;
results[i][3]=null;
results[i][4]=null;
results[i][5]=null;

	    }


	}



//-------------------------------------------------------------------------------------------------------------------------------

public void display()
{

table = new JTable(data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(100,325,675,200);
table.setDragEnabled(false);
jp3.add(scrollpane);
 table.addMouseListener(this);


}

//-------------------------------------------------------------------------------------------------------------------------------

void more_details()
{

int rnumber=table.getSelectedRow();
//System.out.println(rnumber);author, title,classno,isbn,subject,assno
String a=results[ rnumber][0];
String t=results[ rnumber][1];
String c=results[ rnumber][2];
String i=results[ rnumber][3];
String s=results[ rnumber][4];
String k=results[ rnumber][5];

if(rnumber>-1)
{
Bookdetailer book=new Bookdetailer();
 book.detail_visible(a,t,c,i,s,k);
}

else
					JOptionPane.showMessageDialog(null, "Please select a book \n \t to get information about book", "alert", JOptionPane.ERROR_MESSAGE);

	}
public void printn()
{


try
{
table.print();
}

catch(Exception e)
{

	}
	}


	public void actionPerformed(ActionEvent e)
		{

					if(e.getSource()==reserve)
					{
					StudentLogin ob = new StudentLogin();
					}


					if(e.getSource()==detail)
					{
					printn();
					}


					if(e.getSource()==quit)
					{
					int ret=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?..", "alert", JOptionPane.OK_CANCEL_OPTION);

					if(ret==JOptionPane.OK_OPTION)
					{
					System.exit(0);
					}


					}

					if(e.getSource()==clear)
					{
					clear_table();
					keyword1.setText("");
					keyword2.setText("");
					keyword3.setText("");


					}


					if(e.getSource()==search ||e.getSource()==database_selecter ||e.getSource()==words_connecter_selecter3 ||e.getSource()==field_type_selecter3 ||e.getSource()==words_connecter_selecter2 ||e.getSource()==field_type_selecter2 ||e.getSource()==  inter_wordconnecter1 ||e.getSource()==field_type_selecter1 ||e.getSource()==words_connecter_selecter1)
					{
					getsearch();
					}


					if(e.getSource()==detail)
					{
					more_details();
					}
	}
void getsearch()
{
	        clear_table();
			String field_type1 = (String)field_type_selecter1.getSelectedItem();
			String field_type2 = (String)field_type_selecter2.getSelectedItem();
			String field_type3 = (String)field_type_selecter3.getSelectedItem();

	       String words_connecter1 = (String)words_connecter_selecter1.getSelectedItem();
	       String words_connecter2 = (String)words_connecter_selecter2.getSelectedItem();
	       String words_connecter3 = (String)words_connecter_selecter3.getSelectedItem();

			String [] keyword_collection1= keyword1.getText().split("\\ ");
			String [] keyword_collection2= keyword2.getText().split("\\ ");
			String [] keyword_collection3= keyword3.getText().split("\\ ");


	l.repaint();
	clear_table();
	db(field_type1,field_type2,field_type3,words_connecter1,words_connecter2,words_connecter3, keyword_collection1, keyword_collection2, keyword_collection3);


	}

//---------------------------------------------------------------------------
   public void keyReleased(KeyEvent ke)
	{
	}
	public void keyPressed(KeyEvent ke)
		{
			if(ke.getKeyCode()==KeyEvent.VK_ENTER)
			{
       clear_table();
		String field_type1 = (String)field_type_selecter1.getSelectedItem();
		String field_type2 = (String)field_type_selecter2.getSelectedItem();
		String field_type3 = (String)field_type_selecter3.getSelectedItem();

       String words_connecter1 = (String)words_connecter_selecter1.getSelectedItem();
       String words_connecter2 = (String)words_connecter_selecter2.getSelectedItem();
       String words_connecter3 = (String)words_connecter_selecter3.getSelectedItem();

		String [] keyword_collection1= keyword1.getText().split("\\ ");
		String [] keyword_collection2= keyword2.getText().split("\\ ");
		String [] keyword_collection3= keyword3.getText().split("\\ ");


l.repaint();
clear_table();
db(field_type1,field_type2,field_type3,words_connecter1,words_connecter2,words_connecter3, keyword_collection1, keyword_collection2, keyword_collection3);

			}
		}
	public void keyTyped(KeyEvent ke)
			{
			}
//---------------------------------------------------------------------------










































//-----------------------------data base connectivity and retrive data by following data-----------------------------------------------------


public void db(String field_type1,String field_type2,String field_type3,String words_connecter1,String words_connecter2,String words_connecter3,String keyword_collection1[],String  keyword_collection2[],String  keyword_collection3[] )
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";



try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

Statement stm=conn.createStatement();

ResultSet reset=null;





String q1="SELECT author, title,classno,isbn,subject,assno FROM books WHERE ";
String q2=null ;
String q3=null ;
String q4=null ;





//------------------------------------------------------------------------------------------------------------------------------
if(words_connecter1 != "PHRASE")
{

 q2="("+field_type1+"  LIKE '"+  keyword_collection1[0] +"%'" +"  OR "+ field_type1 +"  LIKE '"+"%"+" "+ keyword_collection1[0]+"%'"+")";

for(int i=1;i<keyword_collection1.length;i++)
{

q2=q2+words_connecter1+"("+" "+field_type1+"  LIKE '"+  keyword_collection1[i] +"%'" +"  OR "+ field_type1+"  LIKE '"+"%"+" "+ keyword_collection1[i]+"%'"+")";


}


}


//System.out.println("executed query is following \n \t"+q1+q2);



if(words_connecter1 == "PHRASE")
{

// reset=stm.executeQuery("SELECT author, title,assno,isbn FROM books WHERE "+field_type1+"='"+ keyword1.getText()+"'  ");
q2="("+field_type1+"="+ keyword1.getText()+")";

}

//----------------------------------------------------------------------------------------------------------------------------------








//------------------------------------------------------------------------------------------------------------------------------
if(words_connecter2 != "PHRASE")
{

 q3="("+field_type2+"  LIKE '"+  keyword_collection2[0] +"%'" +"  OR "+ field_type2 +"  LIKE '"+"%"+" "+ keyword_collection2[0]+"%'"+")";

for(int i=1;i<keyword_collection2.length;i++)
{

q3=q3+words_connecter2+"("+" "+field_type2+"  LIKE '"+  keyword_collection2[i] +"%'" +"  OR "+ field_type2+"  LIKE '"+"%"+" "+ keyword_collection2[i]+"%'"+")";

}

}



else if(words_connecter2 == "PHRASE")
{

// reset=stm.executeQuery("SELECT author, title,assno,isbn FROM books WHERE "+field_type2+"='"+ keyword2.getText()+"'  ");
q3="("+field_type1+"="+ keyword2.getText()+")";
}



//----------------------------------------------------------------------------------------------------------------------------------










//------------------------------------------------------------------------------------------------------------------------------
if(words_connecter3 != "PHRASE")
{

 q4="("+field_type3+"  LIKE '"+  keyword_collection3[0] +"%'" +"  OR "+ field_type3 +"  LIKE '"+"%"+" "+ keyword_collection3[0]+"%'"+")";

for(int i=1;i<keyword_collection3.length;i++)
{

q4=q4+words_connecter3+"("+" "+field_type3+"  LIKE '"+  keyword_collection3[i] +"%'" +"  OR "+ field_type3+"  LIKE '"+"%"+" "+ keyword_collection3[i]+"%'"+")";

}

}





else if(words_connecter3 == "PHRASE")
{

// reset=stm.executeQuery("SELECT author, title,assno,isbn FROM books WHERE "+field_type3+"='"+ keyword3.getText()+"'  ");
q4="("+field_type1+"="+ keyword3.getText()+")";
}


//----------------------------------------------------------------------------------------------------------------------------------









//System.out.println(q1+q2+q3+q4);
//reset=stm.executeQuery(q1+q2+" "+ (String)inter_wordconnecter1.getSelectedItem()+" "+q3++" "+(String)inter_wordconnecter2.getSelectedItem()+" "+q4);

String query=q1+q2+" "+(String)inter_wordconnecter1.getSelectedItem()+" "+q3+" "+(String)inter_wordconnecter2.getSelectedItem()+" "+q4 ;

System.out.println(query);
reset=stm.executeQuery(query);








































ResultSetMetaData meta=reset.getMetaData(); // data about table head

int numcol=meta.getColumnCount(); // gets the number of columns of the table
System.out.println();




for(int i=1; i<=numcol; i++)

System.out.print("\t"+meta.getColumnName(i)+"\n");




int i=0;
while(reset.next()) // goes from row to row
{

String[] title=new String[20];
String[] author=new String[20];
String[] assno=new String[20];

title[i]=reset.getString(1);
author[i]=reset.getString(2);
assno[i]=reset.getString(3);

data[i][0]=reset.getString(1);
data[i][1]=reset.getString(2);
data[i][2]=reset.getString(3);
//data[i][3]=reset.getString(4);

results[i][0]=reset.getString(1);
results[i][1]=reset.getString(2);
results[i][2]=reset.getString(3);
results[i][3]=reset.getString(4);
results[i][4]=reset.getString(5);
results[i][5]=reset.getString(6);

System.out.println("\t"+author[i] +"\t"+title[i]+"\t"+assno[i]+"\n");
i++;

}

 length=i;



		}
	 catch(SQLException eq)
		{
		 System.out.println(" \n "+eq);
		}
	 catch(ClassNotFoundException e)
		{
		}
	 finally
		{

		}
	}

//------------------------------------------------------------------------------------------------------------------------------------------------







}