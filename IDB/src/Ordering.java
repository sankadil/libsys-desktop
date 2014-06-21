import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class Ordering extends JFrame  implements ActionListener
{
private JRadioButton book_selection,vender_detail,order_vender_detail,order_history,received_order;
private JButton next;
//private vender ref=null;
//private ButtonGroup group;



public Ordering()
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
	Ordering vender_selecter=new Ordering();
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

JLabel order_vender_detail_label=new JLabel("Order details and vender details");
order_vender_detail_label.setBounds(250,250,350,25);
order_vender_detail_label.setFont(f);
order_vender_detail_label.setForeground(ctf1);
jp1.add(order_vender_detail_label);

//-------------------------------------------------

order_history=new JRadioButton("",true);
order_history.setBounds(200,300,20,25);
order_history.setFont(f);
//order_vender_detail.setActionCommand("x");
//String name3=order_vender_detail.getUIClassID();
jp1.add(order_history);
//System.out.println(name3);

JLabel order_history_label=new JLabel("Order and vender history");
order_history_label.setBounds(250,300,350,25);
order_history_label.setFont(f);
order_history_label.setForeground(ctf1);
jp1.add(order_history_label);


received_order=new JRadioButton("",true);
received_order.setBounds(200,350,20,25);
received_order.setFont(f);
//order_vender_detail.setActionCommand("x");
//String name3=order_vender_detail.getUIClassID();
jp1.add(received_order);
//System.out.println(name3);

JLabel received_order_label=new JLabel("Aquisetion");
received_order_label.setBounds(250,350,350,25);
received_order_label.setFont(f);
received_order_label.setForeground(ctf1);
jp1.add(received_order_label);



ButtonGroup group=new ButtonGroup();
group.add(order_vender_detail);
group.add(book_selection);
group.add(vender_detail);
group.add(order_history);
group.add(received_order);

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

						if(received_order.isSelected())
						{
                            Receive_Book form=new Receive_Book();
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

													Order_setings order=new Order_setings();

															}

							if(order_history.isSelected())
													{

	                                                Order_history vender_selecter=new Order_history();

															}


					}
      }
      //----------------------------------------------------------------



}