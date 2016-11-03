import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;




public class XYScatter extends JFrame
{
	
	private double[] x;
	private double[] y;
	
	private int n;
	private double xMax, xMin, yMax, yMin;
	
	private int width = 500;
	private int height = 400;
	
	private int graphHeight = 400;
	private int graphWidth = 350;
	
	
	private Graph g;
	
	//swing components
	private JPanel content = new JPanel();
	private JPanel graphPanel = new JPanel();
	private JLabel title = new JLabel("");
	private JLabel xAxisTitle = new JLabel("");
	private JLabel yAxisTitle = new JLabel("");
	
	
	
	
	//create graphics objects
	
	
	public XYScatter(double[] x,double[] y)
	{
		
		
		this.x = x;
		this.y = y;
		this.setTitle("Variogram");
		
		this.setMaxMin();
		
		g = new Graph();
		g.setSize(graphHeight, graphWidth);
		display("Variogram");
	}
	
	public void setMaxMin()
	{
		if(x.length <= y.length) 
			n = x.length;
		else
			n = y.length;
		
		xMax = Double.MIN_VALUE;
		xMin = Double.MAX_VALUE;
		yMax = Double.MIN_VALUE;
		yMin = Double.MAX_VALUE;
		
		for( int i = 0; i < n; i++)
		{
			if(x[i] > xMax)
				xMax = x[i];
			else if(x[i] < xMin)
				xMin = x[i];
			if(y[i] > yMax)
				yMax = y[i];
			else if(y[i] < yMin)
				yMin = y[i];
		}
	}
	
	public void setTitle(String s)
	{
		title = new JLabel(s);
	}
	
	public void setXAxisTitle(String s)
	{
		xAxisTitle = new JLabel(s);
	}
	
	public void setYAxisTitle(String s)
	{
		yAxisTitle = new JLabel(s);
	}
	
	
	
	public void display(String s)
	{
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.X_AXIS));
		
		graphPanel.add(yAxisTitle);
		graphPanel.add(g);
		
		content.add(title);
		content.add(graphPanel);
		content.add(xAxisTitle);
		
		this.add(content);
		this.setTitle(s);
		
		
		this.setSize(width, height);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class Graph extends JComponent
	{		
		private double xGraphMax, xGraphMin;
		private double yGraphMax, yGraphMin;
		private int xGridSpacing;
		private int yGridSpacing;
		
		
		Graph()
		{
			xGraphMax = 1.1*xMax;
			xGraphMin = 0;
			yGraphMax = yMax*1.1;
			yGraphMin = 0;
			
			xGridSpacing = (int) Math.floor(xMax/6.0);
			yGridSpacing = (int) Math.floor(yMax/5.0);
			System.out.println(xGridSpacing + ", " + yGridSpacing);
			
		}
		
		
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setBackground(Color.WHITE);
			
			g2.setColor(Color.BLACK);
			g2.draw(new Line2D.Double(40,graphHeight-40,graphWidth-20,graphHeight-40));
			g2.draw(new Line2D.Double(40,graphHeight-40,40,20));
			
			//limits of actual graph
			//X: 40 to graphWidth-60
			//Y: graphHeight-60 to 20
			
			//X Axis
			for(int i = 0; i <= 6; i++)
				g2.drawString("" + Math.floor(xGraphMax/6*i), (int) (40+graphWidth/xGraphMax*xGridSpacing*i), graphHeight-20);
			//YAxis
			for(int i = 0; i <= 5; i++)
				g2.drawString("" + Math.floor(yGraphMax/5*i), 0, (int) ((graphHeight-40)-graphHeight/yGraphMax*xGridSpacing*i));
			
			g2.setColor(Color.RED);
			for (int i = 0; i < n; i++)
			{
				g2.fillOval( (int) (40+(graphWidth-60)/xGraphMax*x[i]) , (int) (graphHeight-40 - (graphHeight-60)/yGraphMax*y[i]), 5, 5);
				System.out.println((int) (40+xGraphMax/(graphWidth-60)*x[i]) + " ," + (int) (graphHeight-40 - yGraphMax/(graphHeight-60)*y[i]));
			}
			
			
		}
		
		
		
	}
	
	
}




