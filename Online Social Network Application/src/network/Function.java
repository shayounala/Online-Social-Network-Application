package network;

import java.util.ArrayList;

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

	public static void insertsort(ArrayList<Integer> toptweetmainkeys) {
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
		
		System.out.println(toptweetmainkeys);
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
		
		System.out.println(toptweetmainkeys);
	}

}
