
//table name wrong remondation should be recomendation







import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Book_selecter extends JFrame  implements ActionListener
{
	private String heading[]={"Request-ID","Title","Author","Edition","vote/name"};
    private String [][] select_data=new String[200][6];
    private String select_heading[]={"Title","Author","Edition","ISBN","Request-ID","From "};
    private String [][] data=new String[200][5];
      private String [][] fulldata=new String[200][12];
      private String [][] rest_data=new String[200][12];
	private JButton vote,recomand,select,remove,save,edit;
	private JTable table,selected_table;
    private ResultSet reset=null;
    private Statement stm=null;
    private static int k=0;
    private static String from="vote";


public Book_selecter()
{

	    setTitle("Select Books to Order");
		setSize(1024,768);
		setResizable(true);
		db();
		Book_selecter_gui();
		vote();
		setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


 }



















public static void main(String args[])
{
	Book_selecter book=new Book_selecter();

 }

 void Book_selecter_gui()
 {
	JPanel jp1=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp1.setOpaque(false);
jp1.setLayout(null);
//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,16);
Color ctf1=new Color(255,255,0);
Font ff=new Font("Tahoma",Font.BOLD,25);
Color c=new Color(0,0,255);

//---------------------------------------------------------------------------------------------------------------------------------------
JLabel jl3=new JLabel(" Select Books to Order ");
jl3.setBounds(350,30,400,35);
jl3.setFont(ff);
jl3.setForeground(ctf1);
jp1.add(jl3);

//----------------------------------------------------------------------------------------------------
ImageIcon button_img_edit = new ImageIcon("edit.jpg");
recomand=new JButton("Recomended");
recomand.setBounds(765,110,200,35);
jp1.add(recomand);
recomand.addActionListener(this);
recomand.setFont(f);
recomand.setForeground(c);

ImageIcon button_img = new ImageIcon("addnew.jpg");
vote=new JButton("Vote");
vote.setBounds(100,110,200,35);
jp1.add(vote);
vote.addActionListener(this);
vote.setFont(f);
vote.setForeground(c);

//ImageIcon button_img_select = new ImageIcon("addnew.jpg");
remove=new JButton("remove");
remove.setBounds(765,355,200,35);
jp1.add(remove);
remove.addActionListener(this);
remove.setFont(f);
remove.setForeground(c);

select=new JButton("select");
select.setBounds(100,355,200,35);
jp1.add(select);
select.addActionListener(this);
select.setFont(f);
select.setForeground(c);

save=new JButton("finish");
save.setBounds(100,600,200,35);
jp1.add(save);
save.addActionListener(this);
save.setFont(f);
save.setForeground(c);

edit=new JButton("Add detail");
edit.setBounds(765,600,200,35);
jp1.add(edit);
edit.addActionListener(this);
edit.setFont(f);
edit.setForeground(c);


//----------------------------------------------------------------------------------------------------

//-----------------JPanel settings-------------------------------------------------------------------

table = new JTable(data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(100,150,865,200);
table.setFont(f);

//table.setAutoResizeMode(9);
//table.setDragEnabled(false);


jp1.add(scrollpane);

 selected_table = new JTable(select_data, select_heading);
JScrollPane scrollpane_selected_table = new JScrollPane(selected_table);

selected_table.setCellSelectionEnabled(true);
selected_table.setColumnSelectionAllowed(true);
scrollpane_selected_table.setBounds(100,395,865,200);
selected_table.setFont(f);
jp1.add(scrollpane_selected_table);

add(jp1);
 }


















//-----------------------------------------book selecting and dislay---------------------------------------------------------------------------------
void select_book()
{

int i=table.getSelectedRow();
int j=table.getSelectedColumn();
boolean status=true;


try
{

//------------------------------------------------------------------------>
for(int a=k;a>-1;a--)
{
if(select_data[a][4]==fulldata[i][0] && select_data[a][5]==from)
{
status=false;
JOptionPane.showMessageDialog(null, "Book is selected early.\nSelect another one", "alert", JOptionPane.ERROR_MESSAGE);
}
}
//------------------------------------------------------------------------->


if(data[i][j] !=null &&  i !=-1 && status)
{

//"Title","Author","Edition","ISBN","requestid"," from "
select_data[k][0]=fulldata[i][1];
select_data[k][1]=fulldata[i][2];
select_data[k][2]=fulldata[i][3];
select_data[k][3]=fulldata[i][7];
select_data[k][4]=fulldata[i][0];
select_data[k][5]=from;

k++;
}
}

catch(Exception e)
{
JOptionPane.showMessageDialog(null, "Please select a book \n \t which you want order", "alert", JOptionPane.ERROR_MESSAGE);

	}

/*
following datas are get all data from query
fulldata[i][0];
fulldata[i][1];
fulldata[i][2];
fulldata[i][3];
fulldata[i][4];
fulldata[i][5];
fulldata[i][6];
fulldata[i][7];
fulldata[i][8];
fulldata[i][9];
fulldata[i][10];
fulldata[i][11];
*/

	}
//------------------------------------------displaying the selecting book-----------------------------------------------------------------------------

















//-------------------remove book----------------------------------------------------------------------
void remove_book()
{

int i=selected_table.getSelectedRow();
int c=table.getSelectedColumn();

try{


if(select_data[i][c] !=null  &&  i !=-1)
{
for(int j=0;j<6;j++)
{
select_data[i][j]=null;

}

while(i<k)
{
for(int j=0;j<6;j++)
{
select_data[i][j]=select_data[i+1][j];
	}
	i++;
}//while

k--;
}
}//try

catch(Exception e)
{
	JOptionPane.showMessageDialog(null, "Please select a book \n \t which you want remove in order list", "alert", JOptionPane.ERROR_MESSAGE);

	}
}

//----------------------------------------removed book--------------cleared array--------------------

//---------------------------------------------------clear a table----------------------------------------------------------------------------------------
void clear()
{

int i=0;
	while(i !=100) // goes from row to row
	{

	data[i][0]="";
	data[i][1]="";
	data[i][2]="";
	data[i][3]="";
	data[i][4]="";


	i++;

	}

	}
//----------------------------------------------------------end of crear table ---------------------------------------------------------






























//---------------------------------------------- selected books save on database-------------------------------------------------------
void insert_order()
{


try{

int t=k-1;
while(t>-1)

{
String query1=null;
if(from=="vote")
{
query1="SELECT title,author1,author2,author3,edition,isbn,publisher,city,year  FROM request where requestid= "+select_data[t][4];
System.out.println("\n"+query1);
}

if(from=="recomand")
{
 query1="SELECT title,author1,edition,author2,author3,isbn,publisher,city,year FROM recommendation where id="+select_data[t][4];
 System.out.println("\n"+query1);
}

 reset=stm.executeQuery(query1);

ResultSetMetaData meta=reset.getMetaData(); // data about table head

int numcol=meta.getColumnCount(); // gets the number of columns of the table
System.out.println();



					int i=0;
					while(reset.next()) // goes from row to row
					{

					//rest_data[k][1]=fulldata[i][5];
					rest_data[i][0]=reset.getString(1);//title
					rest_data[i][1]=reset.getString(2);//author1
					rest_data[i][2]=reset.getString(3);//a2
					rest_data[i][3]=reset.getString(4);//a3
					rest_data[i][4]=reset.getString(5);//edition
					rest_data[i][5]=reset.getString(6);//isbn
					rest_data[i][6]=reset.getString(7);//publisher
					rest_data[i][7]=reset.getString(8);//city
					rest_data[i][8]=reset.getString(9);//year

					i++;

					}
					//---------result cheker--------
					for(int l=0;l<1;l++)
					{
					for(int o=0;o<9;o++){
					System.out.println(rest_data[l][o]);
					}
					}

					String query="INSERT INTO  select_to_order(title,author1,author2,author3,edition,isbn,publisher,city,year,request) VALUES( '"+rest_data[0][0]+"' , '"+rest_data[0][1]+"','"+rest_data[i][2]+"' , '"+rest_data[0][3]+"' , '"+rest_data[0][4]+"' , '"+rest_data[0][5]+"' , '"+rest_data[0][6]+"' , '"+rest_data[0][7]+"' , '"+rest_data[0][8]+"' , '"+select_data[0][5]+"' );";

					System.out.print("insert query \n"+query);

					stm.executeUpdate(query);
t--;

}//end of while







//---------print cheaker result--

}//try

	 catch(SQLException eq)
		{
		 System.out.println(" \n "+eq);
		}

	 finally
		{

		}


	}
//----------------------------------------- selected books saved on database-------------------------------------------------------





































































//---------------------------------------set------vote----list-----------------------------------------------------------------------
void vote()
{
try{

 reset=stm.executeQuery("SELECT requestid,title,author1 ,edition,votes,author2,author3,isbn,publisher,city,year, studentID  FROM request ");

ResultSetMetaData meta=reset.getMetaData(); // data about table head

int numcol=meta.getColumnCount(); // gets the number of columns of the table
System.out.println();


for(int i=1; i<=numcol; i++)

System.out.print("\t"+meta.getColumnName(i)+"\n");

for(int i=0; i<20; i++)
{

while(reset.next()) // goes from row to row
{

data[i][0]=reset.getString(1);
data[i][1]=reset.getString(2);
data[i][2]=reset.getString(3);
data[i][3]=reset.getString(4);
data[i][4]=reset.getString(5);
/*data[i][5]=reset.getString(6);
data[i][6]=reset.getString(7);
data[i][7]=reset.getString(8);
data[i][8]=reset.getString(9);*/


fulldata[i][0]=reset.getString(1);
fulldata[i][1]=reset.getString(2);
fulldata[i][2]=reset.getString(3);
fulldata[i][3]=reset.getString(4);
fulldata[i][4]=reset.getString(5);
fulldata[i][5]=reset.getString(6);
fulldata[i][6]=reset.getString(7);
fulldata[i][7]=reset.getString(8);
fulldata[i][8]=reset.getString(9);
fulldata[i][9]=reset.getString(10);
fulldata[i][10]=reset.getString(11);
fulldata[i][11]=reset.getString(12);

i++;

}

}

}//try

	 catch(SQLException eq)
		{
		 System.out.println(" \n "+eq);
		}

	 finally
		{

		}

	}
//----------------------------------------------------------------------------------------------------------------------------------------------------

void recomend()
{
try{

//"SELECT id,title,author1 ,edition,name  FROM remonendation "
reset=stm.executeQuery("SELECT id,title,author1 ,edition,name,author2,author3,isbn,publisher,city,year, recomendedby FROM recommendation ");
 // reset=stm.executeQuery("SELECT id,title,author1 ,edition,name  FROM remonendation ");
ResultSetMetaData meta=reset.getMetaData(); // data about table head

int numcol=meta.getColumnCount(); // gets the number of columns of the table
System.out.println();


for(int i=1; i<=numcol; i++)

System.out.print("\t"+meta.getColumnName(i)+"\n");

for(int i=0; i<20; i++)
{

while(reset.next()) // goes from row to row
{

data[i][0]=reset.getString(1);
data[i][1]=reset.getString(2);
data[i][2]=reset.getString(3);
data[i][3]=reset.getString(4);
data[i][4]=reset.getString(5);
/*data[i][5]=reset.getString(6);
data[i][6]=reset.getString(7);
data[i][7]=reset.getString(8);
data[i][8]=reset.getString(9);*/

fulldata[i][0]=reset.getString(1);
fulldata[i][1]=reset.getString(2);
fulldata[i][2]=reset.getString(3);
fulldata[i][3]=reset.getString(4);
fulldata[i][4]=reset.getString(5);
fulldata[i][5]=reset.getString(6);
fulldata[i][6]=reset.getString(7);
fulldata[i][7]=reset.getString(8);
fulldata[i][8]=reset.getString(9);
fulldata[i][9]=reset.getString(10);
fulldata[i][10]=reset.getString(11);
fulldata[i][11]=reset.getString(12);


i++;

}

}

}//try

	 catch(SQLException eq)
		{
		 System.out.println(" \n "+eq);
		}

	 finally
		{

		}

	}


//----------------------------------------------------------------------------------------------------------------------------
public void actionPerformed(ActionEvent e)
 	{


							if(e.getSource()==vote)
							{
							from="vote";
							clear();
							vote();
							repaint();
							}

							if(e.getSource()==recomand)
							{
							from="recomand";
							clear();
							recomend();
							repaint();
							}

							if(e.getSource()==select)
							{
							select_book();
							repaint();
							}

							if(e.getSource()==remove)
							{
							repaint();
							remove_book();
							repaint();
							}

							if(e.getSource()==save)
							{
							insert_order();
							}

							if(e.getSource()==edit)
							{
							DetailAdder form=new DetailAdder();
							}
}
//---------------------------------------------------------------------------------------------------------------------------








//--------------------------------------------------------------------------------------------------------------------------------------------
public void db()
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";
// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();

try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

stm=conn.createStatement();

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
//-------------------------------------------------------------------------------------------------------------------------------------------------

void seleccted()
{
		selected_table= new JTable(data, heading);
		JScrollPane scrollpane = new JScrollPane(selected_table);

	  selected_table.setCellSelectionEnabled(true);
		selected_table.setColumnSelectionAllowed(true);
		scrollpane.setBounds(50,400,915,250);

	}



 }