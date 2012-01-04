package network;

import java.util.ArrayList;

public class Human {

	private int mainkey;
	private ArrayList<Integer> tweets;
	private ArrayList<Integer> reposts;
	private ArrayList<Integer> followerkeys;
	public ArrayList<Integer> getFollowerkeys() {
		return followerkeys;
	}

	public void setFollowerkeys(ArrayList<Integer> followerkeys) {
		this.followerkeys = followerkeys;
	}

	public ArrayList<Integer> getFriendkeys() {
		return friendkeys;
	}

	public void setFriendkeys(ArrayList<Integer> friendkeys) {
		this.friendkeys = friendkeys;
	}

	private ArrayList<Integer> friendkeys;
	private int Maxtweetnumber;

	public ArrayList<Integer> getReposts() {
		return reposts;
	}

	public ArrayList<Integer> getTweets() {
		return tweets;
	}

	public int getMainkey() {
		return mainkey;
	}

	public void setMainkey(int mainkey) {
		this.mainkey = mainkey;
	}

	public Human(int mainkey) {
		// TODO Auto-generated constructor stub
		this.mainkey = mainkey;
		followerkeys = new ArrayList<Integer>();
		friendkeys = new ArrayList<Integer>();
	}

	public void post(int posttime) {
		// TODO Auto-generated method stub
		if(istopost()){
			Tweet tweet = new Tweet(this.mainkey, posttime);
			tweets.add(tweet.getMainkey());
		}
	}

	private boolean istopost() {
		// TODO Auto-generated method stub
		return false;
	}

	public void repost(Network network, int reposttime) {
		// TODO Auto-generated method stub
		ArrayList<Integer> tweetmainkeys = accesstweets(network, Maxtweetnumber);
		
		for(int tweetorder=0;tweetorder<tweetmainkeys.size();tweetorder++){
			int tweetmainkey = tweetmainkeys.get(tweetorder);
			if(istorepost()){
				Tweet tweet = new Tweet(this.mainkey, reposttime, tweetmainkey);
				tweets.get(tweet.getMainkey());
				reposts.add(tweet.getMainkey());
			}
		}
	}

	private ArrayList<Integer> accesstweets(Network network, int maxtweetnumber) {
		// TODO Auto-generated method stub
		ArrayList<Integer> tweetmainkeys = new ArrayList<Integer>();
		
		//access tweets from every neighbor with maximum number
		for(int neighbororder=0;neighbororder<friendkeys.size();neighbororder++){
			Human human = network.getHumans().get(friendkeys.get(neighbororder));
			int tweetsize = human.tweets.size();
			
			if(tweetsize<maxtweetnumber){
				tweetmainkeys.addAll(human.tweets);
			}else{
				tweetmainkeys.addAll(human.tweets.subList(tweetsize-maxtweetnumber, tweetsize-1));
			}
		}
		
		//select newest tweets with maximum number
		if(tweetmainkeys.size() > maxtweetnumber){
			return Function.selectTops(tweetmainkeys, maxtweetnumber);
		}
		return tweetmainkeys;
	}

	private boolean istorepost() {
		// TODO Auto-generated method stub
		return false;
	}

}
