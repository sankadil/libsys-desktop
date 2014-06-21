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


public class LybAditional implements ActionListener,MouseListener,KeyListener
{
private JButton clear,quit,search,detail,reserve;
private JTextField keyword_author,keyword_title,keyword_suject;
private JComboBox field1,field2,field3,words_connecter1,words_connecter2,words_connecter3,database_selecter;
private String title[]={"title","Author","subject","ISBN"};
private String connection[]={"AND","OR","PHRASE"};
private String heading[]={"author","title","classno"};
private String data_bases[]={"Books","Theses","journal","CD","DVD"};
private String [][] data=new String[100][3];
private String [][] results=new String[100][6];
private JPanel jp4;
private JTable table ;
private int length;
private static Opac l;

public void guiaditional(JTabbedPane jTabbedPane1, Opac lref)
{
l=lref;
//l.setComponentZOrder(keyword_title, 1);

 jp4=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp4.setOpaque(false);
jp4.setLayout(null);
//jp4.setLayout(null);
//jp4.setBackground(Color.green);
//1----------------------------------------------------

//--buttton set--------------------------------

reserve=new JButton("reservation");
reserve.setBounds(900,545,100,35);
jp4.add(reserve);
reserve.addActionListener(this);

ImageIcon img = new ImageIcon("search.jpg");
search=new JButton("search");
search.setBounds(900,505,100,35);
jp4.add(search);
search.addActionListener(this);

clear=new JButton("Clear");
clear.setBounds(900,585,100,35);
jp4.add(clear);
clear.addActionListener(this);

quit=new JButton("Quit");
quit.setBounds(900,665,100,35);
jp4.add(quit);
quit.addActionListener(this);

detail=new JButton("Print");
detail.setBounds(900,625,100,35);
jp4.add(detail);
detail.addActionListener(this);

//---------------------------------------------

//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,14);
Color ctf1=new Color(255,255,0);
Font tf=new Font("Tahoma",Font.BOLD,14);


//---------------------------------------------------------------------------------------------------------------------------------------



JLabel lable_author=new JLabel("Search by Author(s)");
lable_author.setBounds(10,50,150,20);
lable_author.setFont(f);
lable_author.setForeground(ctf1);
jp4.add(lable_author);

keyword_author=new JTextField("Martin");
keyword_author.setBounds(175,50,200,20);
keyword_author.setFont(tf);
jp4.add(keyword_author);
keyword_author.addKeyListener(this);
 keyword_author.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

l.repaint();
clear_table();
db(keyword_author.getText().split("\\ "),keyword_title.getText().split("\\ "),keyword_suject.getText().split("\\ "));

            }
        });


JLabel jl2=new JLabel("connect words as");
jl2.setBounds(450,50,150,20);
jl2.setFont(f);
jl2.setForeground(ctf1);
jp4.add(jl2);
words_connecter1=new JComboBox(connection);
words_connecter1.setBounds(600,50,75,20);
words_connecter1.setFont(tf);
jp4.add(words_connecter1);
words_connecter1.addActionListener(this);

//2-----------------------------------------------------
JLabel lable_title=new JLabel("For Title(s)");
lable_title.setBounds(10,100,180,20);
lable_title.setFont(f);
lable_title.setForeground(ctf1);
jp4.add(lable_title);

keyword_title=new JTextField("NETWORK ROUTERS");
keyword_title.setBounds(175,100,200,20);
keyword_title.setFocusable(true);
keyword_title.setFont(tf);
jp4.add(keyword_title);
keyword_title.addKeyListener(this);
 keyword_title.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

l.repaint();
clear_table();
db(keyword_author.getText().split("\\ "),keyword_title.getText().split("\\ "),keyword_suject.getText().split("\\ "));

            }
        });


JLabel keyword_conect2=new JLabel("connect words as");
keyword_conect2.setBounds(450,100,150,20);
keyword_conect2.setFont(f);
keyword_conect2.setForeground(ctf1);
jp4.add(keyword_conect2);
words_connecter2=new JComboBox(connection);
words_connecter2.setBounds(600,100,75,20);
words_connecter2.setFont(tf);
jp4.add(words_connecter2);
words_connecter2.addActionListener(this);

//3-----------------------------------------------------
JLabel lable_subject=new JLabel("For Subject(s)");
lable_subject.setBounds(10,150,180,20);
lable_subject.setFont(f);
lable_subject.setForeground(ctf1);
jp4.add(lable_subject);

keyword_suject=new JTextField("COMPUTER NETWORKING");
keyword_suject.setBounds(175,150,200,20);
keyword_suject.setFont(tf);
jp4.add(keyword_suject);
keyword_suject.addKeyListener(this);
 keyword_suject.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

l.repaint();
clear_table();
db(keyword_author.getText().split("\\ "),keyword_title.getText().split("\\ "),keyword_suject.getText().split("\\ "));

            }
        });


JLabel words_connecter3_lable=new JLabel("connect words as");
words_connecter3_lable.setBounds(450,150,150,20);
words_connecter3_lable.setFont(f);
words_connecter3_lable.setForeground(ctf1);
jp4.add(words_connecter3_lable);
words_connecter3=new JComboBox(connection);
words_connecter3.setBounds(600,150,75,20);
words_connecter3.setFont(tf);
jp4.add(words_connecter3);
words_connecter3.addActionListener(this);

JLabel jl4=new JLabel("Database");
jl4.setBounds(800,50,100,25);
jl4.setFont(f);
jl4.setForeground(ctf1);
jp4.add(jl4);

database_selecter=new JComboBox(data_bases);
database_selecter.setBounds(875,50,75,25);
database_selecter.setFont(f);
jp4.add(database_selecter);
database_selecter.addActionListener(this);

display();
jTabbedPane1.addTab("Aditional", jp4);


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

					if(e.getSource()==quit)
					{

					int ret=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?..", "alert", JOptionPane.OK_CANCEL_OPTION);

					if(ret==JOptionPane.OK_OPTION)
					{
					System.exit(0);
					}


					}

					//keyword_author,keyword_title,keyword_suject

					if(e.getSource()==clear)
					{
					clear_table();
					keyword_author.setText("");
					keyword_title.setText("");
					keyword_suject.setText("");

					}

					if(e.getSource()==search ||e.getSource()==words_connecter1 ||e.getSource()==words_connecter2 || e.getSource()==words_connecter3 || e.getSource()==database_selecter)
					{


					l.repaint();
					clear_table();
					db(keyword_author.getText().split("\\ "),keyword_title.getText().split("\\ "),keyword_suject.getText().split("\\ "));

					}


					if(e.getSource()==detail)
					{
					printn();
					}

	}
//------------------------------------------------------------------------------------------------------
   public void keyReleased(KeyEvent ke)
	{
	}
	public void keyPressed(KeyEvent ke)
		{
			if(ke.getKeyCode()==KeyEvent.VK_ENTER)
			{

l.repaint();
clear_table();
db(keyword_author.getText().split("\\ "),keyword_title.getText().split("\\ "),keyword_suject.getText().split("\\ "));


			}
		}
	public void keyTyped(KeyEvent ke)
			{
			}
//----------------------------------------------------------------------------------------------------------

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









//--------------------------------------------------------------------------------------------------------------------------------------
public void clear_table()
{


	for(int i=0;i != length;i++)

		{
data[i][0]=null;
data[i][1]=null;
data[i][2]=null;

results[i][0]=null;
results[i][1]=null;
results[i][2]=null;
results[i][3]=null;
results[i][4]=null;
results[i][5]=null;

	    }


	}
//------------------------------------------------------------------------------------------------------------------------------------------








//-----------------------------------------------set data to table---------------------------------------------------------------

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


public void display()
{

 table = new JTable(data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(100,225,675,200);
table.setDragEnabled(false);
jp4.add(scrollpane);
 table.addMouseListener(this);


}

//---------------------------------------------finish set data to table----------------------------------------------------------------



	//----------------------------DATABASE CONNECTING------------------------------------------------


	public void db(String  keyword_collection_author[],String  keyword_collection_title[],String  keyword_collection_suject[] )
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
String q2=null;
String q3=null;
String q4=null;



//-----------------------------------------------------------author-------------------------------------------------------------------------------------
 if((String) words_connecter1.getSelectedItem() != "PHRASE")
	{

	 q2="("+"author"+"  LIKE '"+  keyword_collection_author[0] +"%'" +"  OR "+ "author"+"  LIKE '"+"%"+" "+ keyword_collection_author[0]+"%'"+")";

	for(int i=1;i<keyword_collection_author.length;i++)
	{

	q2=q2+(String) words_connecter1.getSelectedItem()+"("+" "+"author"+"  LIKE '"+  keyword_collection_author[i] +"%'" +"  OR "+ "author"+"  LIKE '"+"%"+" "+ keyword_collection_author[i]+"%'"+")";

	}
	}
//-----------------------
	else if((String) words_connecter1.getSelectedItem() == "PHRASE")
	{

q2="author = "+keyword_author.getText()+" ";

	}


//-------------------------------------------------------finish author query-------------------------------------------------------------------------




//-----------------------------------------------------------title-------------------------------------------------------------------------------------
 if((String) words_connecter2.getSelectedItem() != "PHRASE")
	{

	 q3="("+"title"+"  LIKE '"+   keyword_collection_title[0] +"%'" +"  OR "+ "title"+"  LIKE '"+"%"+" "+  keyword_collection_title[0]+"%'"+")";

	for(int i=1;i< keyword_collection_title.length;i++)
	{

	q3=q3+(String) words_connecter2.getSelectedItem()+"("+" "+"title"+"  LIKE '"+   keyword_collection_title[i] +"%'" +"  OR "+ "title"+"  LIKE '"+"%"+" "+  keyword_collection_title[i]+"%'"+")";

	}
	}
//---------------
	else if((String) words_connecter2.getSelectedItem() == "PHRASE")
	{

q3="title = "+keyword_title.getText()+" ";

	}

//-------------------------------------------------------finish title query-------------------------------------------------------------------------


//-----------------------------------------------------------suject-------------------------------------------------------------------------------------
 if((String) words_connecter3.getSelectedItem() != "PHRASE")
	{

	 q4="("+"subject"+"  LIKE '"+   keyword_collection_suject[0] +"%'" +"  OR "+ "subject"+"  LIKE '"+"%"+" "+  keyword_collection_suject[0]+"%'"+")";

	for(int i=1;i< keyword_collection_title.length;i++)
	{

	q4=q4+(String) words_connecter3.getSelectedItem()+"("+" "+"subject"+"  LIKE '"+   keyword_collection_suject[i] +"%'" +"  OR "+ "subject"+"  LIKE '"+"%"+" "+  keyword_collection_suject[i]+"%'"+")";

	}
	}
//---------------
	else if((String) words_connecter3.getSelectedItem() == "PHRASE")
	{

q4="subject = "+keyword_suject.getText()+" ";

	}

//-------------------------------------------------------finish title query-------------------------------------------------------------------------





System.out.println("executed query is following \n \t"+q1+q2+" AND "+q3+" AND "+q4);
reset=stm.executeQuery(q1+q2+" AND "+q3+" AND "+q4);


//System.out.println("sanka");

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


}