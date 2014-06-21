import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class Bookdetailer extends JFrame  implements ActionListener
{
private JPanel jp1;
private JButton exit;
//------------------------------construcer----------------------------------------------------------
public Bookdetailer()
{

	    setTitle("logName");
		setSize(400,350);
		setResizable(false);
		setUndecorated(true);
		setLocation(100,370);
		Bookdetailer_gui();
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }
//------------------------------construcer----------------------------------------------------------


public static void main(String args[])
{

Bookdetailer book=new Bookdetailer();
 book.detail_visible("1","2","3","4","5","6");

 }


//------------------------------------------------------------------------------------------------------
 void Bookdetailer_gui()
 {
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

ImageIcon img = new ImageIcon("close.jpg");
exit=new JButton(img);
exit.setBounds(350,0,50,50);
jp1.add(exit);
exit.addActionListener(this);


add(jp1);

//-----------------JPanel settings-------------------------------------------------------------------


 }
void detail_visible(String a,String t,String c,String i,String s,String k)
{
   String author="Author          "+a;
        String title="Title               "+t;
        String isbn="ISBN              "+i;
  String subject="subject          "+s;
String keyword="Assno        "+k;
  String classno="Class No:        "+c;

	//-----------------------------------------set font class--------------------------------------------------------------------------------
	Font f=new Font("Tahoma",Font.BOLD,14);
	Color ctf1=new Color(255,255,0);
	//Font tf=new Font("Tahoma",Font.BOLD,14);
	//---------------------------------------------------------------------------------------------------------------------------------------


	JLabel jl1=new JLabel(author);
	jl1.setBounds(10,50,450,25);
	jl1.setFont(f);
	jl1.setForeground(ctf1);
	jp1.add(jl1);

	JLabel jlt=new JLabel(title);
	jlt.setBounds(10,100,450,25);
	jlt.setFont(f);
	jlt.setForeground(ctf1);
	jp1.add(jlt);

	JLabel jl2=new JLabel(isbn);
	jl2.setBounds(10,150,450,25);
	jl2.setFont(f);
	jl2.setForeground(ctf1);
	jp1.add(jl2);

	JLabel jl3=new JLabel(subject);
	jl3.setBounds(10,200,450,25);
	jl3.setFont(f);
	jl3.setForeground(ctf1);
	jp1.add(jl3);

	JLabel jl4=new JLabel(keyword);
	jl4.setBounds(10,250,450,25);
	jl4.setFont(f);
	jl4.setForeground(ctf1);
	jp1.add(jl4);

	JLabel jl5=new JLabel(classno);
	jl5.setBounds(10,300,450,25);
	jl5.setFont(f);
	jl5.setForeground(ctf1);
	jp1.add(jl5);






	}


		public void actionPerformed(ActionEvent e)
		{


		    if(e.getSource()==exit)
					    {
	                         this.setVisible(false);
						//JOptionPane.showMessageDialog(null, "Bye....", "alert", JOptionPane.ERROR_MESSAGE);
				       // System.exit(0);

		    }


}


 }