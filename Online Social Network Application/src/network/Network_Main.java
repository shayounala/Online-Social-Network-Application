package network;

public class Network_Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Set up the parameters of Network
		{
			Network.mean_tweetnumber = 10;
			Network.variance_tweetnumber = 1;
			Network.mean_post = 10;
			Network.variance_post = 1;
			Network.mean_repost = 10;
			Network.variance_repost = 1;
			Network.mean_checkfrequency = 5;
			Network.variance_checkfrequency = 1;
			Network.mean_processtime = 5;
			Network.variance_processtime = 1;
		}
		Network network = new Network();
		Network_Analysis analysis = new Network_Analysis();
		
		System.out.println("Network analysis");
		analysis.calculateAVE_D(network);//Calculate the average degree of network
		analysis.calculateCE(network);//Calculate the clustering efficiency of network
		//double averagedistance = analysis.calculateAVE_Dis(network);//Calculate the average distance of network
		
		System.out.println();
		//System.exit(0);
		
		Diffusion diffusion = new Diffusion(network);
		//Set up the parameters of diffusion
		{
			Diffusion.diffusiontime = 10;
		}
		
		diffusion.diffusionprocess();
	}

}
