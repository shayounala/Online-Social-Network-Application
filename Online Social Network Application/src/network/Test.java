package network;

import java.util.ArrayList;
import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testlimitedinsert();
		testinsertsort();
	}

	private static void testinsertsort() {
		// TODO Auto-generated method stub
		ArrayList<Integer> toptweetmainkeys = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			Random random = new Random();
			int randomint = random.nextInt()%1000;
			while(toptweetmainkeys.contains(randomint)){
				randomint = random.nextInt()%1000;
			}
			toptweetmainkeys.add(randomint);
		}
		System.out.println("ArrayList: "+toptweetmainkeys);
		
		Function.insertsort(toptweetmainkeys);
	}

	private static void testlimitedinsert() {
		// TODO Auto-generated method stub
		int integer = 85;
		ArrayList<Integer> toptweetmainkeys = new ArrayList<Integer>();
		for(int i=0;i<30;i++){
			toptweetmainkeys.add(100-i*2);
		}
		System.out.println("ArrayList: "+toptweetmainkeys.toString());
		System.out.println("Insert Integer: "+integer);
		Function.limitedinsert(toptweetmainkeys, integer);
	}

}
