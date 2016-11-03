public class XYScatterTest
{
	public static void main(String[] args)
	{
		
		double[] a = new double[10];
		double[] b = new double[10];
		
		for(int i = 0; i < a.length; i++)
		{
			a[i] = 10*i;
			b[i] = (i+1)*(i+1);
		}
		
		XYScatter plot = new XYScatter(a,b);
		plot.setXAxisTitle("                                         X");
		plot.setYAxisTitle("Y");
		plot.setTitle("Variogram Model");
		plot.display("");
		
	}
}
