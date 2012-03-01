package network;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Diffusion {

	public static int diffusiontime = 0;
	private Network network;

	public Diffusion(Network network) {
		// TODO Auto-generated constructor stub
		this.network = network;
	}

	public void diffusionprocess() {
		// TODO Auto-generated method stub
		for (int unittime = 0; unittime < diffusiontime; unittime++) {

			System.out.println("Diffusion Time: " + unittime);

			// post a tweet
			System.out.println("Start posting: " + System.currentTimeMillis());
			for (int humanorder = 0; humanorder < network.getHumans().size(); humanorder++) {
				Human human = network.getHumans().get(humanorder);
				human.post(unittime);
			}
			System.out.println("Finish posting: " + System.currentTimeMillis());

			// repost a tweet
			System.out
					.println("Start reposting: " + System.currentTimeMillis());
			for (int humanorder = 0; humanorder < network.getHumans().size(); humanorder++) {
				Human human = network.getHumans().get(humanorder);
				human.repost(network, unittime);
			}
			System.out.println("Finish reposting: "
					+ System.currentTimeMillis());

		}

		outputdiffusionresults();
	}

	private void outputdiffusionresults() {
		// TODO Auto-generated method stub
		// calculate the average tweets and reposts per user.
		double averagetweets = 0, averagereposts = 0, averageoriginals = 0, averagewaiting = 0;
		for (int humanorder = 0; humanorder < network.getNodeNum(); humanorder++) {
			averagetweets += network.getHumans().get(humanorder).getTweets()
					.size();
			averagereposts += network.getHumans().get(humanorder).getReposts()
					.size();
			averageoriginals += network.getHumans().get(humanorder).getOriginals().size();
			averagewaiting += network.getHumans().get(humanorder).getWaitingtweets().size();
		}
		averagetweets /= network.getNodeNum();
		averagereposts /= network.getNodeNum();
		averageoriginals /= network.getNodeNum();
		averagewaiting /= network.getNodeNum();

		// calculate the maximum repost number of a single original
		ArrayList<Integer> repostsNum = new ArrayList<Integer>();
		
		//calculate the average depth and width of information diffusion
		ArrayList<Integer> diffusionDepth = new ArrayList<Integer>();
		ArrayList<Integer> diffusionWidth = new ArrayList<Integer>();
		double averageWidth = 0;
		double averageDepth = 0;

		for(int humanorder = 0; humanorder<network.getHumans().size();humanorder++){
			Human human = network.getHumans().get(humanorder);
			for(int originalorder=0;originalorder<human.getOriginals().size();originalorder++){
				Tweet tweet = Tweet.getTweets().get(human.getOriginals().get(originalorder));
				
				int repostnumber = 0;
				for(int widthorder=0;widthorder<tweet.getWidth().size();widthorder++){
					repostnumber += tweet.getWidth().get(widthorder);
				}
				repostsNum.add(repostnumber);
				
				if(repostnumber>100){
					diffusionDepth.add(tweet.getWidth().size());
					averageDepth += tweet.getWidth().size();
					
					int tweetWidth = Function.selectTops(tweet.getWidth(), 1).get(0);
					diffusionWidth.add(tweetWidth);
					averageWidth += tweetWidth;
				}
				
			}
		}
		averageDepth /= diffusionDepth.size();
		averageWidth /= diffusionWidth.size();
		
		repostsNum = Function.insertsort(repostsNum);
		//diffusionDepth = Function.insertsort(diffusionDepth);
		//diffusionWidth = Function.insertsort(diffusionWidth);
		

		// output the average tweets and reposts per user
		System.out.println("Average Tweets per User is: " + averagetweets);
		System.out.println("Average Reposts per User is: " + averagereposts);
		System.out.println("Average Originals per User is: " + averageoriginals);
		System.out.println("Average WaitingTweets per User is: " + averagewaiting);

		// output the largest and smallest repost number of a single original
		System.out
				.println("The largest repost number of a single original is: "
						+ repostsNum.get(0));
		System.out
				.println("The smallest repost number of a single original is: "
						+ repostsNum.get(repostsNum.size() - 1));
		
		//output the average depth and width of information diffusion
		System.out.println("Average depth of information diffusion is: " + averageDepth);
		System.out.println("Average width of information diffuison is: " + averageWidth);


		try {
			FileOutputStream file = new FileOutputStream("results.txt", true);
			DataOutputStream data = new DataOutputStream(file);

			// save the average tweets and reposts per user
			data.writeBytes("Average Tweets per User is: " + averagetweets
					+ System.getProperty("line.separator"));
			data.writeBytes("Average Reposts per User is: " + averagereposts
					+ System.getProperty("line.separator"));
			data.writeBytes("Average Originals per User is: " + averageoriginals
					+ System.getProperty("line.separator"));
			data.writeBytes("Average WaitingTweets per User is: " + averagewaiting
					+ System.getProperty("line.separator"));

			// save the largest and smallest repost number of originals
			data.writeBytes("The largest repost number of a single original is: "
					+ repostsNum.get(0)
					+ System.getProperty("line.separator"));
			data.writeBytes("The smallest repost number of a single original is: "
					+ repostsNum.get(repostsNum.size() - 1)
					+ System.getProperty("line.separator"));

			
			
			//save the repost number of originals
			/*data.writeBytes("The repost number of originals: "+System.getProperty("line.separator"));
			for(int originalorder=0;originalorder<repostsNum.size();originalorder++){
				data.writeBytes(repostsNum.get(originalorder)+"	");
			}
			data.writeBytes(System.getProperty("line.separator"));*/
			
			//save the average depth and width of information diffusion
			data.writeBytes("Average width of information diffuison is: " + averageWidth+System.getProperty("line.separator"));
			data.writeBytes("Average depth of information diffuison is: " + averageDepth+System.getProperty("line.separator"));
			
			//save the depth and width of information diffusion
			data.writeBytes("The depth of information diffusion: "+System.getProperty("line.separator"));
			for(int originalorder=0;originalorder<diffusionDepth.size();originalorder++){
				data.writeBytes(diffusionDepth.get(originalorder)+"	");
				data.writeBytes(diffusionWidth.get(originalorder)+System.getProperty("line.separator"));
			}
			data.writeBytes(System.getProperty("line.separator"));
			


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
