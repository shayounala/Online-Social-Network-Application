package network;

import java.util.ArrayList;

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
		double totaltriplets = 0, closedtriplets = 0;
		int totalfollowers = 0;

		for (int i = 0; i < network.getHumans().size(); i++) {
			ArrayList<Integer> followerids = network.getHumans().get(i).getFollowerkeys();
			totalfollowers += followerids.size();

/*			System.out.println("User: " + i + " followerids: "
					+ followerids.size());*/

			for (int j = 0; j < followerids.size(); j++) {
				for (int k = j; k < followerids.size(); k++) {
					if (k != j) {
						ArrayList<Integer> temp = network.getHumans().get(followerids
								.get(j)).getFollowerkeys();
						if (temp.contains(followerids.get(k))) {
							totaltriplets++;
							closedtriplets++;
						} else {
							totaltriplets += 3;
						}
					}
				}
			}
		}

		double CE = closedtriplets / totaltriplets;

		System.out.println("The clustering effiency of the network is: " + CE);
		System.out.println("Total Followers: " + totalfollowers
				+ " Total Triplets: " + totaltriplets + " Closed Triplets: "
				+ closedtriplets);

		return CE;
	}

	public double calculateAVE_Dis(Network network) {
		// TODO Auto-generated method stub
		double averagedistance = 0;
		
		System.out.println("The average distance of network is: "+averagedistance);
		return averagedistance;
	}



}
