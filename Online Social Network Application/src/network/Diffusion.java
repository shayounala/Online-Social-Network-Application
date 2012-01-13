package network;

public class Diffusion {

	private static int diffusiontime = 0;
	public static double variance_post;
	public static double mean_post;
	public static double mean_repost;
	public static double variance_repost;
	public static double mean_tweetnumber;
	public static double variance_tweetnumber;
	private Network network;

	public Diffusion(Network network, int diffusiontime) {
		// TODO Auto-generated constructor stub
		this.network = network;
		this.diffusiontime = diffusiontime;
		
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
		
		outputdiffusionresults();
		savediffusionresults();
	}

	private void outputdiffusionresults() {
		// TODO Auto-generated method stub
		//output the average tweets and reposts per user.
		double averagetweets = 0, averagereposts = 0;
		for(int humanorder=0;humanorder<network.getNodeNum();humanorder++){
			averagetweets += network.getHumans().get(humanorder).getTweets().size();
			averagereposts += network.getHumans().get(humanorder).getReposts().size();
		}
		averagetweets /= network.getNodeNum();
		averagereposts /= network.getNodeNum();
		System.out.println("Average Tweets per User is "+averagetweets);
		System.out.println("Average Reposts per User is "+averagereposts);
		
		//output 
	}

	private void savediffusionresults() {
		// TODO Auto-generated method stub
		
	}

}
