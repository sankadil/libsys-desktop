//package BlockTree;

import java.awt.*;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


/**
 * @version 1.1 01/15/99
 */
 
public class CheckNodeTreeExample extends JFrame {
	
	private String url="jdbc:mysql://localhost/student";
    private String driver="com.mysql.jdbc.Driver";

  
  public CheckNodeTreeExample() {
    super("IDB Libsys Administration privileges");
    
//create check nodes   
    String[] strs = { "IDB LIB SYS", // 0
        "Odering",        // 1
        "Classification", // 2
        "Circulation",    // 3
        "Administrator",  // 4
        "Service",          // 5
        "Opac",       // 6
        
        "Vender Details",          // 7.1
        "Simple Search" , //8.6
        "Advance Search" , //9.6
        "Aditional Search", //10.6
        "Book Lending", //11.3
        "Book Receive",//12.3
        "Set User Privileges", //13.4
        "Change Password", //14.4
        "User Registration", //15.5
        "Staff Registration",//16.5
        "Apply Classify Number", //17.2
        "Remove Classify Number", //18.2
        "Book Selection",		   //19.1
        "Selected Orders",        //20.1
        "Order History",            //21.1
        "Acquisition",         // 22.1  
        "Set Keyword for Search",    // 23.2  
        "Books Lost",    // 24.3  
        "Books Fine" ,    // 25.3  
        "Change System Information",    // 26.4  
        "Admin4" ,    // 27.4  
        "Performance Evaluation" ,    // 28.5  
        "Recommendations" ,    // 29.5
        "Browse" ,    // 30.6
        "ISBN Search"     // 31.6
          
     
        };
                                             
    CheckNode[] nodes = new CheckNode[strs.length+1];
    for (int i=0;i<strs.length;i++) {
      nodes[i] = new CheckNode(strs[i]); 
    }
    
    nodes[0].add(nodes[1]);
    nodes[0].add(nodes[2]);
    nodes[0].add(nodes[3]);
    nodes[0].add(nodes[4]);
    nodes[0].add(nodes[5]);
    nodes[0].add(nodes[6]);
    nodes[1].add(nodes[7]);
    nodes[6].add(nodes[8]);
    nodes[6].add(nodes[9]);
    nodes[6].add(nodes[10]);
    
    nodes[3].add(nodes[11]);
    nodes[3].add(nodes[12]);
    nodes[4].add(nodes[13]);
    nodes[4].add(nodes[14]);
    nodes[5].add(nodes[15]);
    nodes[5].add(nodes[16]);
    nodes[2].add(nodes[17]);
    nodes[2].add(nodes[18]);
    nodes[1].add(nodes[19]);
    nodes[1].add(nodes[20]);
    nodes[1].add(nodes[21]);
    nodes[1].add(nodes[22]);
    nodes[2].add(nodes[23]);
    nodes[3].add(nodes[24]);
    nodes[3].add(nodes[25]);
    nodes[4].add(nodes[26]);
    nodes[4].add(nodes[27]);
    nodes[5].add(nodes[28]);
    nodes[5].add(nodes[29]);
    nodes[6].add(nodes[30]);
    nodes[6].add(nodes[31]);
   
   
// create tree and add to node to it;
    
    JTree tree = new JTree( nodes[0] );
    tree.setCellRenderer(new CheckRenderer());
    tree.getSelectionModel().setSelectionMode(
      TreeSelectionModel.SINGLE_TREE_SELECTION
    );
    tree.putClientProperty("JTree.lineStyle", "Angled");
    tree.addMouseListener(new NodeSelectionListener(tree));
    JScrollPane sp = new JScrollPane(tree);
    
    ModePanel mp = new ModePanel(nodes);
    JTextArea textArea = new JTextArea(3,10);
    JScrollPane textPanel = new JScrollPane(textArea);
    
    JButton button = new JButton("Set Privilages");
    button.addActionListener(
      new ButtonActionListener(nodes[0], textArea));
      
    JButton createAcc = new JButton("Create Account");  
    JPanel panebut = new JPanel(); 
    panebut.add(createAcc);
    panebut.add(button); 
    
 //set panel to tree
    JPanel panel = new JPanel(new BorderLayout()); 
    panel.add(mp,     BorderLayout.CENTER);
    panel.add(panebut , BorderLayout.SOUTH);
    
    getContentPane().add(sp,    BorderLayout.CENTER);
    getContentPane().add(panel, BorderLayout.EAST);
    getContentPane().add(textPanel, BorderLayout.SOUTH);
  }

// nodes add selection listener
  class NodeSelectionListener extends MouseAdapter {
    JTree tree;
    
    NodeSelectionListener(JTree tree) {
      this.tree = tree;
    }
    
    public void mouseClicked(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      int row = tree.getRowForLocation(x, y);
      TreePath  path = tree.getPathForRow(row);
      //TreePath  path = tree.getSelectionPath();
      if (path != null) {
        CheckNode node = (CheckNode)path.getLastPathComponent();
        boolean isSelected = ! (node.isSelected());
        node.setSelected(isSelected);
        if (node.getSelectionMode() == CheckNode.DIG_IN_SELECTION) {
          if ( isSelected) {
            tree.expandPath(path);
          } else {
            tree.collapsePath(path);
          }
        }
        ((DefaultTreeModel) tree.getModel()).nodeChanged(node);
        // I need revalidate if node is root.  but why?
        if (row == 0) {
          tree.revalidate();
          tree.repaint();
        }
      }
    }
  }
  
//......................................................

  class ModePanel extends JPanel implements ActionListener {
    CheckNode[] nodes;
    

    JRadioButton b_single, b_dig_in;

    ModePanel(CheckNode[] nodes) {
      this.nodes = nodes;
      setLayout(new BorderLayout());
      ButtonGroup group = new ButtonGroup();
      b_dig_in = new JRadioButton("Multi  ");
      b_single = new JRadioButton("SINGLE  ");
      JPanel checkBox = new JPanel(new BorderLayout());
      checkBox.setBorder(new TitledBorder("Check Mode"));
      JTextArea textArea2 = new JTextArea(1,40);
      JScrollPane textPanel2 = new JScrollPane(textArea2);
      checkBox.add(b_dig_in ,BorderLayout.EAST);
      checkBox.add(b_single ,BorderLayout.WEST);
      add(checkBox , BorderLayout.NORTH);
      StaffAdmin sadmin = new StaffAdmin();
      add(sadmin ,BorderLayout.CENTER);
      add(textArea2 ,BorderLayout.SOUTH);
      
      group.add(b_dig_in);
      group.add(b_single);
      b_dig_in.addActionListener(this);
      b_single.addActionListener(this);
      b_dig_in.setSelected(true);
    }

    public void actionPerformed(ActionEvent e) {
      int mode;
      if (b_single == e.getSource()) {
        mode = CheckNode.SINGLE_SELECTION;
      } else {
        mode = CheckNode.DIG_IN_SELECTION;
      }
      for (int i = 0; i < nodes.length; i++) {
        nodes[i].setSelectionMode(mode);
      }
    }
  }
  
// assign button action
  class ButtonActionListener implements ActionListener {
  	int a=0;
    CheckNode root;
    String[] privilage=new String[30];
    String pri;
    JTextArea textArea;
    GetData prive = new GetData( );
    ButtonActionListener(final CheckNode root, final JTextArea textArea) {
      this.root = root;
      this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent ev) {
    	
      Enumeration e = root.breadthFirstEnumeration();
      while (e.hasMoreElements()) {
      	
        CheckNode node = (CheckNode) e.nextElement();
        if (node.isSelected()) {
          TreeNode[] nodes = node.getPath();
          textArea.append("\n" + nodes[0].toString());
       
          for (int i = 1; i < nodes.length; i++) {
            textArea.append("/" + nodes[i].toString());
            //System.out.ln(nodes[i].toString());
             //System.out.println(i);
            
            if(i==2){
            	
            	privilage[a] = nodes[2].toString();
            	a++;
            }
            
            
          }
         
        }
        
      }
        prive.savePrivilage(privilage);
    }
  }

  public static void main(String args[]) {
//    /try {
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//    } catch (Exception evt) {}
  
    CheckNodeTreeExample frame = new CheckNodeTreeExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(1024,800);
    frame.setVisible(true);
  }
}

class CheckRenderer extends JPanel implements TreeCellRenderer {
  protected JCheckBox check;
  protected TreeLabel label;

  public CheckRenderer() {
    setLayout(null);
    add(check = new JCheckBox());
    add(label = new TreeLabel());
    check.setBackground(UIManager.getColor("Tree.textBackground"));
    label.setForeground(UIManager.getColor("Tree.textForeground"));
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean isSelected, boolean expanded, boolean leaf, int row,
      boolean hasFocus) {
    String stringValue = tree.convertValueToText(value, isSelected,
        expanded, leaf, row, hasFocus);
    setEnabled(tree.isEnabled());
    check.setSelected(((CheckNode) value).isSelected());
    label.setFont(tree.getFont());
    label.setText(stringValue);
    label.setSelected(isSelected);
    label.setFocus(hasFocus);
    if (leaf) {
      label.setIcon(UIManager.getIcon("images/middle.gif"));
    } else if (expanded) {
      label.setIcon(UIManager.getIcon("Tree.openIcon"));
    } else {
      label.setIcon(UIManager.getIcon("Tree.closedIcon"));
    }
    return this;
  }

  public Dimension getPreferredSize() {
    Dimension d_check = check.getPreferredSize();
    Dimension d_label = label.getPreferredSize();
    return new Dimension(d_check.width + d_label.width,
        (d_check.height < d_label.height ? d_label.height
            : d_check.height));
  }

  public void doLayout() {
    Dimension d_check = check.getPreferredSize();
    Dimension d_label = label.getPreferredSize();
    int y_check = 0;
    int y_label = 0;
    if (d_check.height < d_label.height) {
      y_check = (d_label.height - d_check.height) / 2;
    } else {
      y_label = (d_check.height - d_label.height) / 2;
    }
    check.setLocation(0, y_check);
    check.setBounds(0, y_check, d_check.width, d_check.height);
    label.setLocation(d_check.width, y_label);
    label.setBounds(d_check.width, y_label, d_label.width, d_label.height);
  }

  public void setBackground(Color color) {
    if (color instanceof ColorUIResource)
          color = null;
    super.setBackground(color);
  }

  public class TreeLabel extends JLabel {
    boolean isSelected;

    boolean hasFocus;

    public TreeLabel() {
    }

    public void setBackground(Color color) {
      if (color instanceof ColorUIResource)
        color = null;
      super.setBackground(color);
    }

    public void paint(Graphics g) {
      String str;
      if ((str = getText()) != null) {
        if (0 < str.length()) {
          if (isSelected) {
            g.setColor(UIManager
                .getColor("Tree.selectionBackground"));
          } else {
            g.setColor(UIManager.getColor("Tree.textBackground"));
          }
          Dimension d = getPreferredSize();
          int imageOffset = 0;
          Icon currentI = getIcon();
          if (currentI != null) {
            imageOffset = currentI.getIconWidth()
                + Math.max(0, getIconTextGap() - 1);
          }
          g.fillRect(imageOffset, 0, d.width - 1 - imageOffset,
              d.height);
          if (hasFocus) {
            g.setColor(UIManager.getColor("Tree.selectionBorderColor"));
            g.drawRect(imageOffset, 0, d.width - 1 - imageOffset,
                d.height - 1);
          }
        }
      }
      super.paint(g);
    }

    public Dimension getPreferredSize() {
      Dimension retDimension = super.getPreferredSize();
      if (retDimension != null) {
        retDimension = new Dimension(retDimension.width + 3,
            retDimension.height);
      }
      return retDimension;
    }

    public void setSelected(boolean isSelected) {
      this.isSelected = isSelected;
    }

    public void setFocus(boolean hasFocus) {
      this.hasFocus = hasFocus;
    }
  }
}

class CheckNode extends DefaultMutableTreeNode {

  public final static int SINGLE_SELECTION = 0;

  public final static int DIG_IN_SELECTION = 4;

  protected int selectionMode;

  protected boolean isSelected;

  public CheckNode() {
    this(null);
  }

  public CheckNode(Object userObject) {
    this(userObject, true, false);
  }

  public CheckNode(Object userObject, boolean allowsChildren,
      boolean isSelected) {
    super(userObject, allowsChildren);
    this.isSelected = isSelected;
    setSelectionMode(DIG_IN_SELECTION);
  }

  public void setSelectionMode(int mode) {
    selectionMode = mode;
  }

  public int getSelectionMode() {
    return selectionMode;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;

    if ((selectionMode == DIG_IN_SELECTION) && (children != null)) {
      Enumeration e = children.elements();
      while (e.hasMoreElements()) {
        CheckNode node = (CheckNode) e.nextElement();
        node.setSelected(isSelected);
      }
    }
  }

  public boolean isSelected() {
    return isSelected;
  }

  // If you want to change "isSelected" by CellEditor,
  /*
   public void setUserObject(Object obj) { if (obj instanceof Boolean) {
   * setSelected(((Boolean)obj).booleanValue()); } else {
   * super.setUserObject(obj); } }
   */

}