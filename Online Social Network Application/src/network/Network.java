package network;

import java.util.ArrayList;

public class Network {
	
	public boolean [][] matrix;
	public double [][] weightedmatrix;
	private ArrayList<Human> humans;
	private int NodeNum;
	
	public ArrayList<Human> getHumans() {
		return humans;
	}

	public Network(int NodeNum, int LeastNum) {
		// TODO Auto-generated constructor stub
		this.NodeNum = NodeNum;
		InitiationHumans(NodeNum);
	}

	private void InitiationHumans(int nodeNum) {
		// TODO Auto-generated method stub
		humans = new ArrayList<Human>();
		for(int i=0;i<nodeNum;i++){
			humans.add(new Human(i));
		}
	}

	public Network() {
		// TODO Auto-generated constructor stub
		//InitiationHumans();
		
	}

	public ArrayList<Integer> getneighbors(int mainkey) {
		// TODO Auto-generated method stub
		ArrayList<Integer> neighbormainkey = new ArrayList<Integer>();
		for(int humanorder=0;humanorder<NodeNum;humanorder++){
			if(matrix[mainkey][humanorder]){
				neighbormainkey.add(humanorder);
			}
		}
		
		return neighbormainkey;
	}

	

}
