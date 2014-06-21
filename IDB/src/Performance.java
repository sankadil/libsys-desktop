import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.applet.Applet;

public class Performance extends JFrame  implements ActionListener
{
private JRadioButton book_selection,vender_detail,order_vender_detail,order_history;
private JButton next;

public Performance()
{

	    setTitle("Performance Evaluater");
		setSize(1024,768);
		setResizable(true);
		Performance_gui();
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

public static void main(String args[])
{
	Performance vender_selecter=new Performance();

 }


public void Performance_gui()
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

add(jp1);


//-----------------------------------------set font class--------------------------------------------------------------------------------
Font f=new Font("Tahoma",Font.BOLD,20);
Font f2=new Font("Tahoma",Font.BOLD,25);
Color ctf1=new Color(255,255,0);
Color ctf=new Color(0,0,255);
//-----------------------------------------set font class--------------------------------------------------------------------------------



//---------------------------------------------------------------------------------------------------------------------------------------
JLabel welcome_label=new JLabel("Welcome to IDB Lybsys Performance Evaluvation System");
welcome_label.setBounds(50,50,750,25);
welcome_label.setFont(f2);
welcome_label.setForeground(ctf1);
jp1.add(welcome_label);

 vender_detail=new JRadioButton("",true);
vender_detail.setBounds(200,250,20,25);
vender_detail.setFont(f);
jp1.add(vender_detail);

JLabel venderdetails_label=new JLabel("Least used subject of book");
venderdetails_label.setBounds(250,250,350,25);
venderdetails_label.setFont(f);
venderdetails_label.setForeground(ctf1);
jp1.add(venderdetails_label);

//-------------------------------------------------

 book_selection=new JRadioButton("",true);
book_selection.setBounds(200,200,20,25);
book_selection.setFont(f);
jp1.add(book_selection);

JLabel book_selection_label=new JLabel("Most used subject of book");
book_selection_label.setBounds(250,200,350,25);
book_selection_label.setFont(f);
book_selection_label.setForeground(ctf1);
jp1.add(book_selection_label);

//-------------------------------------------------


ButtonGroup group=new ButtonGroup();

group.add(book_selection);
group.add(vender_detail);


//---------------------------------------------------------------------------------------
ImageIcon button_img_edit = new ImageIcon("next.jpg");
next=new JButton("next");
next.setBounds(800,600,200,35);
jp1.add(next);
next.setFont(f);
next.setForeground(ctf);
next.addActionListener(this);

//---------------------------------------------------------------------------------------

 }
//------------------------------------------------------------------Action Listener
	public void actionPerformed(ActionEvent e)
	{


	    if(e.getSource()==next)
				    {

						if(vender_detail.isSelected())
						{
applet_veiw_low();
//this.setVisible(false);
							}

							if(book_selection.isSelected())
													{
                                                          applet_veiw_high();
														//this.setVisible(false);

						                      	    }


                      }

     }

      //----------------------------------------------------------------

void applet_veiw_low()
{
 Frame frame = new Frame("Book usage in subjectwise");
    frame.addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent event) {
                System.exit(0);
              }
            });

		   Applet applet = new Performance_evaluater_lower();
		   applet.init();
		   applet.setBounds(10,0,400,400);
		   frame .add(applet);
		  frame .setSize(1024, 750);
	      frame .show();

	}
void applet_veiw_high()
{
 Frame frame = new Frame("Book usage in subjectwise");
    frame.addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent event) {
                System.exit(0);
              }
            });
		   Applet applet = new Performance_evaluater();
		   applet.init();
		   applet.setBounds(400,0,400,400);
		   frame .add(applet);
		  frame .setSize(1024, 750);
	      frame .show();

	}
 }