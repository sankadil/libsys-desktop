import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*

Author sanka dilmadu senvirathna
sankadilmadu@gmail.com
pramuka1@google.com

*/

public class Performance_evaluater_lower extends Applet {
  private double[] _data = new double[]
          {5.5, 34.2, 47.4, 53.1, 69.9, 68.7, 81.1};
  private DataPlot_dataPlot;
  private String [] data;
  private int [] count;

  private Statement stm=null;
  private ResultSet reset=null;






  public void init() {
	 // System.out.println("init()");
    GraphicsPlotCanvas plotCanvas = createPlotCanvas();

    _dataPlot = new DataPlot();
    _dataPlot.addElement(new DataCurve(""));
    plotCanvas.connect(_dataPlot);

    setLayout(new BorderLayout());
    add(plotCanvas.getGraphicsCanvas(), BorderLayout.CENTER);
    add(createControlPanel(), BorderLayout.SOUTH);
  }

  private GraphicsPlotCanvas createPlotCanvas() {
		//call my db and set data------------------------------------------------------------------------------conect database----------------------->>>>
//System.out.println("GraphicsPlotCanvas()");

	db();
	//get_data_desending();
get_data_assending();
	calculate_data();



    Properties props = new Properties();
    ConfigParameters config
        = new ConfigParameters(new PropertiesBasedConfigData(props));
    props.put("plot/legendVisible", "false");
    props.put("plot/coordinateSystem/xAxis/minimum", "-0.5");
    props.put("plot/coordinateSystem/xAxis/maximum", "6.5");
    props.put("plot/coordinateSystem/xAxis/axisLabel", "");
    props.put("plot/coordinateSystem/xAxis/ticLabelFormat/className",
              "jcckit.plot.TicLabelMap");


String subjects="";
for(int i=0;i<7;i++)
{
	subjects=subjects+i+"="+data[i]+";";
	}
//    "0=machanical;1=maths;2=chemical;3=electrical;4=civil;5=material;6=IT"
    props.put("plot/coordinateSystem/xAxis/ticLabelFormat/map",
          subjects);

    props.put("plot/coordinateSystem/yAxis/axisLabel", "usage rate");
    props.put("plot/coordinateSystem/yAxis/maximum", "100");
    props.put("plot/coordinateSystem/yAxis/ticLabelFormat", "%d%%");
    props.put("plot/curveFactory/definitions", "curve");
    props.put("plot/curveFactory/curve/withLine", "false");
    props.put("plot/curveFactory/curve/symbolFactory/className",
              "jcckit.plot.BarFactory");
    props.put("plot/curveFactory/curve/symbolFactory/attributes/className",
              "jcckit.graphic.ShapeAttributes");
    props.put("plot/curveFactory/curve/symbolFactory/attributes/fillColor",
              "0x0085fe");
    props.put("plot/curveFactory/curve/symbolFactory/attributes/lineColor",
              "0");
    props.put("plot/curveFactory/curve/symbolFactory/size", "0.08");
    props.put("plot/initialHintForNextCurve/className",
              "jcckit.plot.PositionHint");
    props.put("plot/initialHintForNextCurve/position", "0 0.1");

    return new GraphicsPlotCanvas(config);
  }

  private Panel createControlPanel() {
    Panel controlPanel = new Panel();
    controlPanel.setBackground(Color.blue);

    Button startButton = new Button("Least used subject of book");
    						Font f=new Font("Tahoma",Font.BOLD,20);
							Color ctf1=new Color(0,0,255);
							startButton.setFont(f);
						startButton.setForeground(ctf1);
    startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  new Thread() {
                          public void run() {
                            animate();
                          }
                        }.start();
                }
              });

    controlPanel.add(startButton);

    return controlPanel;
  }


  private void animate()
  {

    DataCurve curve = new DataCurve("");
    for (int i = 0; i < _data.length; i++) {////////////////////////////////
      curve.addElement(new DataPoint(i, 0));
    }
    _dataPlot.replaceElementAt(0, curve);

    for (int i = 0; i < _data.length; i++) {///////////////////////
      double x = i;
      double y = 0;
      while (y < _data[i]) {/////////////////////////////
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {}
        y = Math.min(_data[i], y + 5);/////////////////////
        curve.replaceElementAt(i, new DataPoint(x, y));
      }
    }
  }






//------------------------------------------------------------------------------------------------------------------------
void get_data_desending()
{


  try
  {
	  reset=stm.executeQuery("SELECT subject,counter  FROM performance order by counter Desc");

	 reset. last();
	 int x= reset.getRow();
	  reset. first();
	  data=new String[x];
	  count=new int[x];
                                   int i=0;
								  while(reset.next()) // goes from row to row
								  {
								  data[i]=reset.getString(1);
								  count[i]=reset.getInt(2);
								  i++;

								  }

}
													 catch(SQLException eq)
														{
														 System.out.println(" \n "+eq);
														}
													 finally
														{

  		                                                           }

	}
//-----------------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------------------------
void get_data_assending()
{


  try
  {
	  reset=stm.executeQuery("SELECT subject,counter  FROM performance order by counter ");
	 reset. last();
	 int x= reset.getRow();
	  reset. first();
	  data=new String[x];
	  count=new int[x];
                                   int i=0;
								  while(reset.next()) // goes from row to row
								  {
								  data[i]=reset.getString(1);
								  count[i]=reset.getInt(2);
								  i++;

								  }

}
													 catch(SQLException eq)
														{
														 System.out.println(" \n "+eq);
														}
													 finally
														{

  		                                                           }

	}
//-----------------------------------------------------------------------------------------------------------------------


void calculate_data()
{
	int i=0;
	double temp=0;
	while(i<count.length)
	{
				temp=temp+count[i];
				i++;
		}
	int j=0;
	while(j<count.length)
	{
				 _data[j]=(count[j]*100)/temp;
				j++;
																  if(j==7)
																  {
																	 break;
									  }
		}

	}

  //---------------database connectivity-------------------------------------------------------------------


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

  	//-----------------------------------------------------------------------------------


  	  public static void main(String[] args) {
	//System.out.println("main method");
	    Frame frame = new Frame("Book usage in subjectwise");
	    frame.addWindowListener(new WindowAdapter() {
	              public void windowClosing(WindowEvent event) {
	                System.exit(0);
	             // setVisible(false);
	              }
	            });
	    Applet applet = new Performance_evaluater_lower();
	    applet.init();
	    frame.add(applet);
	    frame.setSize(1024, 700);
	       frame.show();

  }

}
