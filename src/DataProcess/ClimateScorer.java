package DataProcess;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//weather data model to determine a record's score 
public class ClimateScorer {
	private static double MAX_SCORE = 100;
	private static double MIN_SCORE = 0;
	public Model model;
	
	public ClimateScorer(String path)
	{
		model = new Model(path);
	}
	
	private double scoreUnit(double maxIndex, double minIndex, double value)
	{
		double tan = (MAX_SCORE - MIN_SCORE)/(maxIndex - minIndex);
		return MIN_SCORE + (value - minIndex)*tan;
	}
	
	private double scoreUnit(double maxIndex, double minIndex1, double minIndex2, double value)
	{
		if(value > maxIndex)
			return scoreUnit(maxIndex, minIndex2, value);
		else
			return scoreUnit(maxIndex, minIndex1, value);
	}
	
	public static List<Double> attributeFilter(String inPath, int index)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(inPath));
			List<Double> attribute = new ArrayList<Double>();
			
			String line = reader.readLine();
			int number = 1;
			
			while(line != null)
			{
				String[] attributes = line.split(",");
				attribute.add(Double.parseDouble(attributes[index]));
				
				line = reader.readLine();				
				if(number%100000 == 0)
					System.out.println("process the "+number+"th data");
			}
			reader.close();
			return attribute;
		}
		catch(IOException e)
		{
			
		}
		return null;
	}
	
	public static double getMax(List<Double> values)
	{
		return Collections.max(values);
	}
	
	public static double getMin(List<Double> values)
	{
		return Collections.min(values);
	}
	
	public static double getMean(List<Double> values)
	{
		double sum = 0;
		for(int i = 0; i < values.size(); i++)
			sum = sum + values.get(i);
		return sum/values.size();
	}
	
	public static void generateModel(String inPath, String outPath)
	{
		List<Double> tmpf = attributeFilter(inPath, 0);
		List<Double> rh = attributeFilter(inPath, 1);
		List<Double> vis = attributeFilter(inPath, 2);
		List<Double> cc = attributeFilter(inPath, 3);
		List<Double> pcpin = attributeFilter(inPath, 4);
		List<Double> sndpth = attributeFilter(inPath, 5);
		List<Double> wind = attributeFilter(inPath, 6);
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));	
			writer.write(getMean(tmpf)+","+getMin(tmpf)+","+getMax(tmpf));
			writer.newLine();
			writer.write(getMean(rh)+","+getMin(rh)+","+getMax(rh));
			writer.newLine();
			writer.write(getMax(vis)+","+getMin(vis));
			writer.newLine();
			writer.write(getMin(cc)+","+getMax(cc));
			writer.newLine();
			writer.write(getMin(pcpin)+","+getMax(pcpin));
			writer.newLine();
			writer.write(getMin(sndpth)+","+getMax(sndpth));
			writer.newLine();
			writer.write(getMin(wind)+","+getMax(wind));
			writer.close();	
		}
		catch(IOException e)
		{
			
		}
	}

	private class Model
	{
		double tmpfMaxIndex;
		double tmpfMinIndex1;
		double tmpfMinIndex2;
		
		double rhMaxIndex;
		double rhMinIndex1;
		double rhMinIndex2;
		
		double visMaxIndex;
		double visMinIndex;
		
		double ccMaxIndex;
		double ccMinIndex;
		
		double pcpinMaxIndex;
		double pcpinMinIndex;
		
		double sndpthMaxIndex;
		double sndpthMinIndex;
		
		double windMaxIndex;
		double windMinIndex;
		
		public Model(String Path)
		{
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(Path));
				List<Double> attribute = new ArrayList<Double>();
				
				String line = reader.readLine();
				String[] tmpf = line.split(",");
				tmpfMaxIndex = Double.parseDouble(tmpf[0]);
				tmpfMinIndex1 = Double.parseDouble(tmpf[1]);
				tmpfMinIndex2 = Double.parseDouble(tmpf[2]);
				
				line = reader.readLine();
				String[] rh = line.split(",");
				rhMaxIndex = Double.parseDouble(rh[0]);
				rhMinIndex1 = Double.parseDouble(rh[1]);
				rhMinIndex2 = Double.parseDouble(rh[2]);
				
				line = reader.readLine();
				String[] vis = line.split(",");
				visMaxIndex = Double.parseDouble(vis[0]);
				visMinIndex = Double.parseDouble(vis[1]);
				
				line = reader.readLine();
				String[] cc = line.split(",");
				ccMaxIndex = Double.parseDouble(cc[0]);
				ccMinIndex = Double.parseDouble(cc[1]);
				
				line = reader.readLine();
				String[] pcpin = line.split(",");
				pcpinMaxIndex = Double.parseDouble(pcpin[0]);
				pcpinMinIndex = Double.parseDouble(pcpin[1]);
				
				line = reader.readLine();
				String[] sndpth = line.split(",");
				sndpthMaxIndex = Double.parseDouble(sndpth[0]);
				sndpthMinIndex = Double.parseDouble(sndpth[1]);
				
				line = reader.readLine();
				String[] wind = line.split(",");
				windMaxIndex = Double.parseDouble(wind[0]);
				windMinIndex = Double.parseDouble(wind[1]);
				reader.close();
			}
			catch(IOException e)
			{
				
			}
		}
	}
	
	public double getScore(List<Double> values)
	{
		double sum = scoreUnit(model.tmpfMaxIndex, model.tmpfMinIndex1, model.tmpfMinIndex2, values.get(0)) +
				scoreUnit(model.rhMaxIndex, model.rhMinIndex1, model.rhMinIndex2, values.get(1)) +
				scoreUnit(model.visMaxIndex, model.visMinIndex, values.get(2)) +
				scoreUnit(model.ccMaxIndex, model.ccMinIndex, values.get(3)) +
				scoreUnit(model.pcpinMaxIndex, model.pcpinMinIndex, values.get(4)) +
				scoreUnit(model.sndpthMaxIndex, model.sndpthMinIndex, values.get(5)) +
				scoreUnit(model.windMaxIndex, model.windMinIndex, values.get(6));
		
		return sum/7;
	}
	
	public static void main(String[] args) throws Exception 
	{	
		ClimateScorer scorer = new ClimateScorer("model");
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("climate_test.csv"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("result.csv"));
			List<Double> attribute = new ArrayList<Double>();
			
			String line = reader.readLine();
			int number = 0;
			
			while(line != null)
			{
				String[] attributes = line.split(",");
				for(int i = 2; i < attributes.length; i++)
					attribute.add(Double.parseDouble(attributes[i]));
				double score = scorer.getScore(attribute);
				System.out.println(number + "\t" + score);
				writer.write(line + "," + score);
				writer.newLine();
				line = reader.readLine();
				number++;
				attribute.clear();
				
			}
			writer.close();
			reader.close();
		}
		catch(IOException e)
		{
			
		}
	}

}

