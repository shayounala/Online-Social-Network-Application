package network;

public class Network_Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Network network = new Network(10000, 10);
		Network_Analysis analysis = new Network_Analysis();
		
		double averagedegree = analysis.calculateAVE_D(network);//Calculate the average degree of network
		double clustereff = analysis.calculateCE(network);//Calculate the clustering efficiency of network
		double averagedistance = analysis.calculateAVE_Dis(network);//Calculate the average distance of network
	}

}
