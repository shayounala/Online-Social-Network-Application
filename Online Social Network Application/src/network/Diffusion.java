package network;

public class Diffusion {

	private static final int diffusiontime = 0;
	private Network network;

	public Diffusion(Network network) {
		// TODO Auto-generated constructor stub
		this.network = network;
	}

	public void diffusionprocess() {
		// TODO Auto-generated method stub
		for(int unittime=0;unittime<diffusiontime;unittime++){
			
			//post a tweet
			for(int humanorder=0;humanorder<network.getHumans().size();humanorder++){
				Human human = network.getHumans().get(humanorder);
				human.post(unittime);
			}
			
			
			//repost a tweet
			for(int humanorder=0;humanorder<network.getHumans().size();humanorder++){
				Human human = network.getHumans().get(humanorder);
				human.repost(network, unittime);
			}
			
			
		}
	}

}
