package network;

import java.util.ArrayList;
import java.util.Random;

public class Function {

	public static ArrayList<Integer> selectTops(ArrayList<Integer> tweetmainkeys,
			int maxtweetnumber) {
		// TODO Auto-generated method stub
		ArrayList<Integer> toptweetmainkeys = new ArrayList<Integer>();
		
		for(int i=0;i<tweetmainkeys.size();i++){
			if(i < maxtweetnumber){
				toptweetmainkeys.add(tweetmainkeys.get(i));
			}else if(i == maxtweetnumber){
				insertsort(toptweetmainkeys);
				limitedinsert(toptweetmainkeys, tweetmainkeys.get(i));
			}else{
				limitedinsert(toptweetmainkeys, tweetmainkeys.get(i));
			}
		}
		
		return toptweetmainkeys;
	}

	public static ArrayList<Integer> insertsort(ArrayList<Integer> toptweetmainkeys) {
		// TODO Auto-generated method stub
		for(int index=1;index<toptweetmainkeys.size();index++){  
            int Comparablekey = toptweetmainkeys.get(index);  
            int position = index;  
            //shift larger values to the right  
            while(position>0 && toptweetmainkeys.get(position-1)<Comparablekey){  
                toptweetmainkeys.set(position, toptweetmainkeys.get(position-1));  
                position--;  
            }  
            toptweetmainkeys.set(position, Comparablekey);  
        }
		
		return toptweetmainkeys;
		//System.out.println(toptweetmainkeys);
	}

	public static void limitedinsert(ArrayList<Integer> toptweetmainkeys,
			Integer integer) {
		// TODO Auto-generated method stub
		int start = 0, end = toptweetmainkeys.size();
		while(start != end && end-start != 1){
			int middle = (start+end)/2;

			if(toptweetmainkeys.get(middle)>integer){
				start = middle;
			}else{
				end = middle;
			}
			
		}
		
			
		if(toptweetmainkeys.get(start)>integer){
			toptweetmainkeys.add(end, integer);
		}else{
			toptweetmainkeys.add(start, integer);
		}
		toptweetmainkeys.remove(toptweetmainkeys.size()-1);
		
		//System.out.println(toptweetmainkeys);
	}

	public static double getNormalDouble(double mean, double variance) {
		// TODO Auto-generated method stub
		double randomdouble = 0;
		Random random = new Random();
		randomdouble = random.nextGaussian()*variance+mean;
		return randomdouble;
	}

	public static int getNormalPositiveInt(double mean, double variance) {
		// TODO Auto-generated method stub
		double randomdouble = getNormalDouble(mean, variance);
		int randomint = (int)Math.abs(randomdouble)+1;
		return randomint;
	}

	public static ArrayList<Double> insertsortDouble(ArrayList<Double> input) {
		// TODO Auto-generated method stub
		for(int index=1;index<input.size();index++){  
            double Comparablekey = input.get(index);  
            int position = index;  
            //shift larger values to the right  
            while(position>0 && input.get(position-1)<Comparablekey){  
            	input.set(position, input.get(position-1));  
                position--;  
            }  
            input.set(position, Comparablekey);  
        }
		
		return input;
	}

}
