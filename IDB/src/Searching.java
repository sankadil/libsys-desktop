/*

todo clear a table
change sizes


*/
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

public class Searching implements ActionListener,MouseListener,KeyListener
{


private JButton clear,quit,search,detail,reserve;
private JTextField keyword_field;
private JComboBox field_type_selecter,words_connecter_selecter,database_selecter;
private String title[]={"author","title","subject","ISBN","series","place","publisher","any field"};
private String connection[]={"OR","PHRASE","AND"};
private String data_bases[]={"Books","Theses","journal","CD","DVD"};
private String heading[]={"author","title","class no"};
private String [][] data=new String[100][3];
private String [][] results=new String[100][6];
private JTable table;
private JPanel jp1;
private int length;
private static Opac l;
public void guimethodSearch(JTabbedPane jTabbedPane1, Opac lref)
{
l=lref;
jp1=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp1.setOpaque(false);
jp1.setLayout(null);


reserve=new JButton("reservation");
reserve.setBounds(900,545,100,35);
jp1.add(reserve);
reserve.addActionListener(this);

ImageIcon img = new ImageIcon("search.jpg");
search=new JButton("search");
search.setBounds(900,505,100,35);
jp1.add(search);
search.addActionListener(this);

ImageIcon imgc = new ImageIcon("clear.jpg");
clear=new JButton("clear");
clear.setBounds(900,585,100,35);
jp1.add(clear);
clear.addActionListener(this);

quit=new JButton("Quit");
quit.setBounds(900,665,100,35);
jp1.add(quit);
quit.addActionListener(this);

detail=new JButton("Print");
detail.setBounds(900,625,100,35);
jp1.add(detail);
detail.addActionListener(this);


//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,14);
Color ctf1=new Color(255,255,0);
Font tf=new Font("Tahoma",Font.BOLD,14);


//---------------------------------------------------------------------------------------------------------------------------------------


JLabel jl3=new JLabel("Field");
jl3.setBounds(10,50,50,25);
jl3.setFont(f);
jl3.setForeground(ctf1);
jp1.add(jl3);

 field_type_selecter =new JComboBox(title);
field_type_selecter.setBounds(50,50,85,25);
field_type_selecter.setFont(f);
jp1.add(field_type_selecter);
field_type_selecter.addActionListener(this);


JLabel jl1=new JLabel("Search keyword(s)");
jl1.setBounds(165,50,150,25);
jl1.setFont(f);
jl1.setForeground(ctf1);
jp1.add(jl1);

keyword_field=new JTextField("martin");
keyword_field.setBounds(300,50,180,25);
keyword_field.setFont(tf);
jp1.add(keyword_field);
keyword_field.addKeyListener(this);
keyword_field.addActionListener(this);

 keyword_field.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

				String field_type = (String)field_type_selecter.getSelectedItem();
		       String words_connecter = (String)words_connecter_selecter.getSelectedItem();

				String [] keyword_collection= keyword_field.getText().split("\\ ");

					l.repaint();
					clear_table();
					db((String)field_type_selecter.getSelectedItem(),keyword_field.getText().split("\\ "), (String)words_connecter_selecter.getSelectedItem());


            }
        });


JLabel jl2=new JLabel("Connect words as");
jl2.setBounds(520,50,150,25);
jl2.setFont(f);
jl2.setForeground(ctf1);
jp1.add(jl2);

words_connecter_selecter=new JComboBox(connection);
words_connecter_selecter.setBounds(650,50,75,25);
words_connecter_selecter.setFont(f);
jp1.add(words_connecter_selecter);
words_connecter_selecter.addActionListener(this);

JLabel jl4=new JLabel("Database");
jl4.setBounds(800,50,100,25);
jl4.setFont(f);
jl4.setForeground(ctf1);
jp1.add(jl4);

database_selecter=new JComboBox(data_bases);
database_selecter.setBounds(875,50,75,25);
database_selecter.setFont(f);
jp1.add(database_selecter);
database_selecter.addActionListener(this);

display();
jTabbedPane1.addTab("simplesearching", jp1);

}

void more_details()
{

int rnumber=table.getSelectedRow();
if(rnumber>-1)
{
//System.out.println(rnumber);author, title,classno,isbn,subject,assno
String a=results[ rnumber][0];
String t=results[ rnumber][1];
String c=results[ rnumber][2];
String i=results[ rnumber][3];
String s=results[ rnumber][4];
String k=results[ rnumber][5];

Bookdetailer book=new Bookdetailer();
 book.detail_visible(a,t,c,i,s,k);
}

//else
		//			JOptionPane.showMessageDialog(null, "Please select a book \n \t to get information about book", "alert", JOptionPane.ERROR_MESSAGE);

	}




public void display()
{

 table = new JTable(data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(100,130,675,200);
table.setDragEnabled(false);
jp1.add(scrollpane);
 table.addMouseListener(this);

}


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



//-------------------------------------------------------
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
          // clear();
			keyword_field.setText("");

	                        }
//keyword_field field_type_selecter
	    if(e.getSource()==search || e.getSource()==words_connecter_selecter || e.getSource()==database_selecter || e.getSource()==field_type_selecter || e.getSource()==keyword_field)
			    {

		String field_type = (String)field_type_selecter.getSelectedItem();
       String words_connecter = (String)words_connecter_selecter.getSelectedItem();

		String [] keyword_collection= keyword_field.getText().split("\\ ");

			l.repaint();
			clear_table();
			db((String)field_type_selecter.getSelectedItem(),keyword_field.getText().split("\\ "), (String)words_connecter_selecter.getSelectedItem());

	            }

	    if(e.getSource()==detail)
			    {
					more_details();
				}

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

//---------------------------------------------------------------------------
   public void keyReleased(KeyEvent ke)
	{
	}
	public void keyPressed(KeyEvent ke)
		{
			if(ke.getKeyCode()==KeyEvent.VK_ENTER)
			{

			l.repaint();
			clear_table();
			db((String)field_type_selecter.getSelectedItem(),keyword_field.getText().split("\\ "), (String)words_connecter_selecter.getSelectedItem());


			}
		}
	public void keyTyped(KeyEvent ke)
			{
			}
//---------------------------------------------------------------------------



//------------------------------------------------------------




public void db(String field_type,String keyword_collection[],String words_connecter )
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";
// String url="jdbc:mysql://localhost/"+(String)database_selecter.getSelectedItem();


try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

Statement stm=conn.createStatement();

ResultSet reset=null;




if(words_connecter != "PHRASE")
{


String q1="SELECT author, title,classno,isbn,subject,assno FROM books WHERE ";

String q2="("+field_type+"  LIKE '"+  keyword_collection[0] +"%'" +"  OR "+ field_type +"  LIKE '"+"%"+" "+ keyword_collection[0]+"%'"+")";

for(int i=1;i<keyword_collection.length;i++)
{

q2=q2+words_connecter+"("+" "+field_type+"  LIKE '"+  keyword_collection[i] +"%'" +"  OR "+ field_type +"  LIKE '"+"%"+" "+ keyword_collection[i]+"%'"+")";


}

System.out.println("executed query is following \n \t"+q1+q2);
reset=stm.executeQuery(q1+q2);


}





else if(words_connecter == "PHRASE")
{
	// use keyword_field.getText()  for query
 reset=stm.executeQuery("SELECT author, title,classno,isbn,subject,assno FROM books WHERE "+field_type+"='"+ keyword_field.getText()+"'  ");

}

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
//assno[i]=reset.getString(3);

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
