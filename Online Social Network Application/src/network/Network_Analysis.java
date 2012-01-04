package network;

public class Network_Analysis {

	/**
	 * @param network
	 * @return the average degree of network
	 */
	public double calculateAVE_D(Network network) {
		// TODO Auto-generated method stub
		double averagedegree = 0, totaldegree = 0;
		
		int NodeNum = network.getHumans().size();
		for(int i=0;i<NodeNum;i++){
			Human human = network.getHumans().get(i);
			totaldegree += human.getFollowerkeys().size()+human.getFriendkeys().size();
		}
		averagedegree = totaldegree/NodeNum;
		
		System.out.println("The average degree of network is: "+averagedegree);
		return averagedegree;

	}

	/**
	 * @param network
	 * @return the clustering efficiency of network
	 */
	public double calculateCE(Network network) {
		// TODO Auto-generated method stub
		double clustereff = 0;
		
		System.out.println("The clustering efficiency of network is: "+clustereff);
		return clustereff;
	}

	public double calculateAVE_Dis(Network network) {
		// TODO Auto-generated method stub
		double averagedistance = 0;
		
		System.out.println("The average distance of network is: "+averagedistance);
		return averagedistance;
	}



}
