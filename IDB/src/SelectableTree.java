import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.event.*;

/** JTree that reports selections by placing their string values
 *  in a JTextField.
 *  1999 Marty Hall, http://www.apl.jhu.edu/~hall/java/
 */

public class SelectableTree extends JFrame {
//	GetValue kk = new GetValue();
  	String privilages[] ;
//= kk.getData();

 // 	System.out.println("");

//  public static void main(String[] args) {
//    new SelectableTree();
//
//  }

  private JTree tree;
  private JTextField currentSelectionField;

  public SelectableTree(String[] pri) {
    super("IDB Library System");
    privilages=pri;

    //WindowUtilities.setNativeLookAndFeel();
    //addWindowListener(new ExitListener());
    Container content = getContentPane();
    DefaultMutableTreeNode root =
      new DefaultMutableTreeNode("IDB Library System");

    DefaultMutableTreeNode ordering
          = new DefaultMutableTreeNode("Ordering");
          root.add(ordering);

    DefaultMutableTreeNode order1
    	  = new DefaultMutableTreeNode("Vendor Details");
    	  ordering.add(order1);

    DefaultMutableTreeNode order2
    	  = new DefaultMutableTreeNode("Book Selection");
    	  ordering.add(order2);

    DefaultMutableTreeNode order3
    	  = new DefaultMutableTreeNode("Selected Orders");
    	  ordering.add(order3);

    DefaultMutableTreeNode order4
    	  = new DefaultMutableTreeNode("Order Histry");
    	  ordering.add(order4);

    DefaultMutableTreeNode order5
    	  = new DefaultMutableTreeNode("Acquisition");
    	  ordering.add(order5);

    DefaultMutableTreeNode clasification
          = new DefaultMutableTreeNode("Classification");
          root.add(clasification);


	DefaultMutableTreeNode clasi1
          = new DefaultMutableTreeNode("Apply Classify Number");
          clasification.add(clasi1);

    DefaultMutableTreeNode clasi2
          = new DefaultMutableTreeNode("Remove Classify Number");
          clasification.add(clasi2);

    DefaultMutableTreeNode clasi3
          = new DefaultMutableTreeNode("Set Key word for searching");
          clasification.add(clasi3);

    DefaultMutableTreeNode circulation
    	  = new DefaultMutableTreeNode("Circulation");
    	  root.add(circulation);

    DefaultMutableTreeNode circu1
          = new DefaultMutableTreeNode("Books Lending");
          circulation.add(circu1);

    DefaultMutableTreeNode circu2
          = new DefaultMutableTreeNode("Books Receive");
          circulation.add(circu2);

    DefaultMutableTreeNode circu3
          = new DefaultMutableTreeNode("Book Lost");
          circulation.add(circu3);

    DefaultMutableTreeNode circu4
          = new DefaultMutableTreeNode("Book Fine");
          circulation.add(circu4);


    DefaultMutableTreeNode administrator
    	  = new DefaultMutableTreeNode("Administrator");
    	  root.add(administrator);

    DefaultMutableTreeNode admin1
    	  = new DefaultMutableTreeNode("Set User Privileges");
    	  administrator.add(admin1);

    DefaultMutableTreeNode admin2
    	  = new DefaultMutableTreeNode("Change Passwords");
    	  administrator.add(admin2);

    DefaultMutableTreeNode admin3
    	  = new DefaultMutableTreeNode("Change System Information");
    	  administrator.add(admin3);

    DefaultMutableTreeNode admin4
    	  = new DefaultMutableTreeNode("Admin4");
    	  administrator.add(admin4);

    DefaultMutableTreeNode service
      	  = new DefaultMutableTreeNode("Service");
      	  root.add(service);

    DefaultMutableTreeNode serv1
      	  = new DefaultMutableTreeNode("User Registration");
      	  service.add(serv1);

    DefaultMutableTreeNode serv2
      	  = new DefaultMutableTreeNode("Staff Registration");
      	  service.add(serv2);

    DefaultMutableTreeNode serv3
      	  = new DefaultMutableTreeNode("Performance  Evaluation");
      	  service.add(serv3);

    DefaultMutableTreeNode serv4
      	  = new DefaultMutableTreeNode("Recommendations");
      	  service.add(serv4);

    DefaultMutableTreeNode opac
      	  = new DefaultMutableTreeNode("Opac");
      	  root.add(opac);

    DefaultMutableTreeNode opac1
      	  = new DefaultMutableTreeNode("OPAC");
      	  opac.add(opac1);

//    DefaultMutableTreeNode opac2
//      	  = new DefaultMutableTreeNode("Advance Search");
//      	  opac.add(opac2);
//
//    DefaultMutableTreeNode opac3
//      	  = new DefaultMutableTreeNode("Additional Search");
//      	  opac.add(opac3);
//
//    DefaultMutableTreeNode opac4
//      	  = new DefaultMutableTreeNode("Browse");
//      	  opac.add(opac4);
//
//    DefaultMutableTreeNode opac5
//      	  = new DefaultMutableTreeNode("ISBN Search");
//      	  opac.add(opac5);


    tree = new JTree(root);

//******************************************
// double click action listener
  MouseListener ml = new MouseAdapter() {
     public void mousePressed(MouseEvent e) {
         int selRow = tree.getRowForLocation(e.getX(), e.getY());
         System.out.println(selRow+"Damith");
         if(selRow != -1) {
             if(e.getClickCount() == 1) {
               //  mySingleClick(selRow, selPath);
             }
             else if(e.getClickCount() == 2) {
             	open_page(selRow); // call to the file open
                System.out.println(selRow+"Resu");
             }
         }
     }
 };
 		tree.addMouseListener(ml);



  //******************************************




//    tree.addTreeSelectionListener(this);
    content.add(new JScrollPane(tree), BorderLayout.CENTER);
    currentSelectionField = new JTextField("Current Selection: NONE");
//    content.add(currentSelectionField, BorderLayout.SOUTH);


    ImageIcon leafIcon = createImageIcon("images/middle.gif");
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setLeafIcon(leafIcon);
            tree.setCellRenderer(renderer);
        } else {
            System.err.println("Leaf icon missing; using default.");
        }




    setSize(1024,840);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   // GetValue kk = new GetValue();
  //  System.out.println(privilages[0]);
  }

  //*******************************
  // create URL class object to get image icon from resource

  protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SelectableTree.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }




  //..................


/*
  public void treeCollapsed(TreeExpansionEvent e){
  	System.out.println("path");
  }

  public void valueChanged(TreeSelectionEvent event) {
	  String path=tree.getLastSelectedPathComponent().toString();
	  System.out.println(path);

       if(path.equals("order1")){
		   //GpaCounter cal=new GpaCounter();
		   }
  }*/

  public  void open_page(int path){
	int setValue=0;
	System.out.println("patha vallue"+path);
	System.out.println(privilages[2]);
  	if(privilages[path-1].equals("1")){
		   setValue=path;
		   }else{
		   	JOptionPane.showMessageDialog(null, "You don't have Privilages\nto Enter this Section  ", "Alert", JOptionPane.ERROR_MESSAGE);

		   	}

  switch(setValue){
  	case 2:


  	 	vender_details vender_selecter=new vender_details();
  	  break;

  	case 3:
  	  Book_selecter book=new Book_selecter();
  	  break;

  	case 4:
  	    Order_setings vender_selecter1=new Order_setings();
  	  break;

  	case 5:
  		Order_history vender_His=new Order_history();

  	  break;

  	case 6:
  	 	Receive_Book form=new Receive_Book();

  	  break;

  	 case 12:
  	   System.out.println("Issue......................"+setValue);
  	  IssueBook cal2=new IssueBook();

  	  break;

  	  case 13:
  	   System.out.println("Return......................"+setValue);
  	  ReturnBook cal3=new ReturnBook();
  	  break;

  	  case 14:
  	   System.out.println("Lost......................"+setValue);
  	  Lost cal14=new Lost();
  	  break;

////////////////////
  	  case 17:
  	   System.out.println("Return......................"+setValue);
  	  CheckNodeTreeExample frame = new CheckNodeTreeExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(1024,800);
    frame.setVisible(true);
  	  break;
///////////////////

  	  case 18:
  	  System.out.println("Return......................"+setValue);
  	  CheckNodeTreeExample sel18 = new CheckNodeTreeExample();
    sel18.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    sel18.setSize(1024,800);
    sel18.setVisible(true);
  	  break;


  	  case 19:
  	   System.out.println("Return......................"+setValue);
  	  CheckNodeTreeExample sel19 = new CheckNodeTreeExample();
    sel19.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    sel19.setSize(1024,800);
    sel19.setVisible(true);
  	  break;

  	    	  case 27:
	    	   System.out.println("Return....############.................."+setValue);
	    	 // CheckNodeTreeExample cal19=new CheckNodeTreeExample();
	    	   // Opac l=new Opac();
			   // l.conectall(l);
			   Opac k=new Opac();
               k.conectall(k);


  	  break;

  	   case 24:
  	   System.out.println("Return......................"+setValue);
  	  Performance pro=new Performance();
  	  break;

  }
  }

}


class GetValue{
	    String url = "jdbc:mysql://localhost/student";
		String driver = "com.mysql.jdbc.Driver";
		Connection con= null;
		ResultSet res =null;
		Statement s= null;
		String id;

	public GetValue(String ID){
		id = ID;
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
		getData();
	}

	public void getData(){

		String[] datapri=new String[30];
				try{
		    s = con.createStatement();
		//	int val= s.executUpdate("INSERT INTO);

			res = s.executeQuery("SELECT * FROM staff_priv WHERE id='"+id+"'");



		while(res.next()){
			for(int a=0;a<27;a++){
//			String id =res.getString(1);
//			String name=res.getString(2);
//			String dis=res.getString(3);
//			String uni=res.getString(4);
			datapri[a]=res.getString(a+1);

			//System.out.println(datapri[a]);
		}
	}
		}catch(SQLException e3){
			e3.printStackTrace();
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
	//	return datapri;
		SelectableTree ob1 = new SelectableTree(datapri);

	System.out.println("Calling GUI");
	}

}