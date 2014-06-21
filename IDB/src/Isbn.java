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

public class Isbn implements ActionListener,MouseListener,KeyListener{

private JTextField keyword_field;
private JButton clear,quit,search,detail,reserve;
//private String heading[]={"author","title","assno"};
private String heading[]={"author","title","class no"};
private String [][] data=new String[100][3];
private String [][] results=new String[100][6];
private JPanel jp5;
private JTable table;
private int length;
private static Opac l;
public void guimethodIsbn(JTabbedPane jTabbedPane1, Opac lref)
{
l=lref;
jp5=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp5.setOpaque(false);
jp5.setLayout(null);

reserve=new JButton("reservation");
reserve.setBounds(900,545,100,35);
jp5.add(reserve);
reserve.addActionListener(this);

ImageIcon img = new ImageIcon("search.jpg");
search=new JButton("search");
search.setBounds(900,505,100,35);
jp5.add(search);
search.addActionListener(this);

clear=new JButton("Clear");
clear.setBounds(900,585,100,35);
jp5.add(clear);
clear.addActionListener(this);

quit=new JButton("Quit");
quit.setBounds(900,665,100,35);
jp5.add(quit);
quit.addActionListener(this);

detail=new JButton("Print");
detail.setBounds(900,625,100,35);
jp5.add(detail);
detail.addActionListener(this);

//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,14);
Color ctf1=new Color(255,255,0);
Font tf=new Font("Tahoma",Font.BOLD,14);


//---------------------------------------------------------------------------------------------------------------------------------------


JLabel jl1=new JLabel("ISBN No:");
jl1.setBounds(300,50,100,25);
jl1.setFont(f);
jl1.setForeground(ctf1);
jp5.add(jl1);

keyword_field=new JTextField("1234567891011");
keyword_field.setBounds(400,50,180,25);
keyword_field.setFont(tf);
jp5.add(keyword_field);
keyword_field.addKeyListener(this);

 keyword_field.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {

getsearch();
            }
        });

display();
jTabbedPane1.addTab("ISBN searching", jp5);




}
public void display()
{

 table = new JTable(data, heading);
JScrollPane scrollpane = new JScrollPane(table);

table.setCellSelectionEnabled(true);
table.setColumnSelectionAllowed(true);
scrollpane.setBounds(100,130,675,200);
jp5.add(scrollpane);
 table.addMouseListener(this);
}

//----------------------------------------------------------------------------------------------------------
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
//--------------------------------------------------------------------------------------------------------


void getsearch()
{


	//clear_table();
	String keywordfield=keyword_field.getText();
	int length=keywordfield.length();
	try
	{
	//	int isbn_num=Integer.parseInt(keyword_field.getText());
	double isbn_num=Double.parseDouble(keyword_field.getText());


	if(keywordfield.length()==13 || keywordfield.length()==10)
	{
	l.repaint();
	clear_table();
	db(keywordfield);
	}
	else{

	JOptionPane.showMessageDialog(null, "ISBN Number has 10 or 13 digit ", "alert", JOptionPane.ERROR_MESSAGE);
	}

	}

	catch(Exception ex)
	{

		JOptionPane.showMessageDialog(null, "ISBN Number has no \ncharacters. It hasonly digit ", "alert", JOptionPane.ERROR_MESSAGE);
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


					if(e.getSource()==quit)
					{

					int ret=JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?..", "alert", JOptionPane.OK_CANCEL_OPTION);

					if(ret==JOptionPane.OK_OPTION)
					{
					System.exit(0);
					}


					}

					if(e.getSource()==search)
					{
					getsearch();

					}
					//--------------------
					if(e.getSource()==clear)
					{
					clear_table();
					keyword_field.setText("");

					}

					if(e.getSource()==detail)
					{
					printn();
					}

}

//---------------------------------------------------------------------------
   public void keyReleased(KeyEvent ke)
	{
	}
	public void keyPressed(KeyEvent ke)
		{
			if(ke.getKeyCode()==KeyEvent.VK_ENTER)
			{


//clear_table();
String keywordfield=keyword_field.getText();
int length=keywordfield.length();
try
{
//	int isbn_num=Integer.parseInt(keyword_field.getText());
double isbn_num=Double.parseDouble(keyword_field.getText());


if(keywordfield.length()==13 || keywordfield.length()==10)
{
l.repaint();
clear_table();
db(keywordfield);
}
else{

JOptionPane.showMessageDialog(null, "ISBN Number has 10 or 13 digit ", "alert", JOptionPane.ERROR_MESSAGE);
}

}

catch(Exception ex)
{

	JOptionPane.showMessageDialog(null, "ISBN Number has no \ncharacters. It hasonly digit ", "alert", JOptionPane.ERROR_MESSAGE);
	}

			}
		}
	public void keyTyped(KeyEvent ke)
			{
			}
//---------------------------------------------------------------------------




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









// data base connecing

public void db(String keyword_isbn )
{

	 String driver="com.mysql.jdbc.Driver";
	 String url="jdbc:mysql://localhost/student";

try
{
Class.forName(driver);

Connection conn=DriverManager.getConnection(url,"root","");

Statement stm=conn.createStatement();




ResultSet reset=stm.executeQuery("SELECT author,title,assno FROM books WHERE isbn='"+keyword_isbn+"'  ");




ResultSetMetaData meta=reset.getMetaData(); // data about table head

int numcol=meta.getColumnCount(); // gets the number of columns of the table
System.out.println();




for(int i=1; i<=numcol; i++)

System.out.print("\t"+meta.getColumnName(i)+"\n");
//print column names---index   number  then end  forloop



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
		 System.out.println(eq);
		}
	 catch(ClassNotFoundException e)
		{
		}
	 finally
		{

		}
	}


}