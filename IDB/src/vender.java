import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class vender extends JFrame  implements ActionListener
{
private JRadioButton book_selection,vender_detail,order_vender_detail;
private JButton next;
//private vender ref=null;
//private ButtonGroup group;



public vender()
{

	    setTitle("Welcome to Ordering section");
		setSize(1024,768);
		setResizable(true);
		vender_selecter_gui();
		setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

public static void main(String args[])
{
	vender vender_selecter=new vender();
  //  ref=vender_selecter;
 }

public void vender_selecter_gui()
{
	JPanel jp1=new JPanel(){
public void paintComponent(Graphics n)
  {
ImageIcon img = new ImageIcon("bg3.jpg");
n.drawImage(img.getImage(),0,0,null);
super.paintComponent(n);
  }
};

jp1.setOpaque(false);
jp1.setLayout(null);

add(jp1);

//-----------------JPanel settings-------------------------------------------------------------------



//-----------------------------------------set font class--------------------------------------------------------------------------------

Font f=new Font("Tahoma",Font.BOLD,20);
Color ctf1=new Color(255,255,0);



//---------------------------------------------------------------------------------------------------------------------------------------




 vender_detail=new JRadioButton("",true);
vender_detail.setBounds(200,150,20,25);
vender_detail.setFont(f);
//String name=vender_detail.getUIClassID();
jp1.add(vender_detail);
//System.out.println(name);

JLabel venderdetails_label=new JLabel("vender details");
venderdetails_label.setBounds(250,150,150,25);
venderdetails_label.setFont(f);
venderdetails_label.setForeground(ctf1);
jp1.add(venderdetails_label);

//-------------------------------------------------

 book_selection=new JRadioButton("",true);
book_selection.setBounds(200,200,20,25);
book_selection.setFont(f);
//String name2=book_selection.getUIClassID();
jp1.add(book_selection);
//System.out.println(name2);

JLabel book_selection_label=new JLabel("Book selection");
book_selection_label.setBounds(250,200,150,25);
book_selection_label.setFont(f);
book_selection_label.setForeground(ctf1);
jp1.add(book_selection_label);

//-------------------------------------------------

order_vender_detail=new JRadioButton("",true);
order_vender_detail.setBounds(200,250,20,25);
order_vender_detail.setFont(f);
//order_vender_detail.setActionCommand("x");
//String name3=order_vender_detail.getUIClassID();
jp1.add(order_vender_detail);
//System.out.println(name3);

JLabel order_vender_detail_label=new JLabel("Order details and vender history");
order_vender_detail_label.setBounds(250,250,350,25);
order_vender_detail_label.setFont(f);
order_vender_detail_label.setForeground(ctf1);
jp1.add(order_vender_detail_label);

ButtonGroup group=new ButtonGroup();
group.add(order_vender_detail);
group.add(book_selection);
group.add(vender_detail);

//---------------------------------------------------------------------------------------
ImageIcon button_img_edit = new ImageIcon("next.jpg");
next=new JButton(button_img_edit);
next.setBounds(800,600,200,35);
jp1.add(next);
next.addActionListener(this);

//---------------------------------------------------------------------------------------

//System.out.println(group.getSelection());
//System.out.println(order_vender_detail.isSelected());

 }
//------------------------------------------------------------------Action Listener
	public void actionPerformed(ActionEvent e)
	{


	    if(e.getSource()==next)
				    {

						if(vender_detail.isSelected())
						{
							vender_details vender_selecter=new vender_details();
							this.setVisible(false);

							}

							if(book_selection.isSelected())
													{
														Book_selecter book=new Book_selecter();
														//book.pass_ref(ref);
														this.setVisible(false);

						                      	    }

							if(order_vender_detail.isSelected())
													{
															System.out.println("sankadil");
							                           }


					}
      }
      //----------------------------------------------------------------



}