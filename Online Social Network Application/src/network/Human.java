package network;

import java.util.ArrayList;

public class Human {

	private int mainkey;
	private ArrayList<Integer> tweets;
	private ArrayList<Integer> originals;
	public ArrayList<Integer> getOriginals() {
		return originals;
	}

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
	private double thres_post;
	private double thres_repost;
	private ArrayList<Integer> waitingtweets;
	public ArrayList<Integer> getWaitingtweets() {
		return waitingtweets;
	}

	private ArrayList<Integer> waitingtimes;
	private int checkfrequency;
	public int getCheckfrequency() {
		return checkfrequency;
	}

	private Integer processtime;
	private ArrayList<Integer> friendreception;
	private ArrayList<Integer> diversityreception;
	private double averagerepostrate;
	private ArrayList<Double> repostrates;

	public ArrayList<Integer> getDiversityreception() {
		return diversityreception;
	}

	public ArrayList<Integer> getFriendreception() {
		return friendreception;
	}

	public Integer getProcesstime() {
		return processtime;
	}

	public double getThres_repost() {
		return thres_repost;
	}

	public void setThres_repost(double thres_repost) {
		this.thres_repost = thres_repost;
	}

	public double getThres_post() {
		return thres_post;
	}

	public void setThres_post(double thres_post) {
		this.thres_post = thres_post;
	}

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

	public Human(int mainkey, int maxtweetnumber, double thres_post, double thres_repost) {
		// TODO Auto-generated constructor stub
		this.mainkey = mainkey;
		followerkeys = new ArrayList<Integer>();
		friendkeys = new ArrayList<Integer>();
		friendreception = new ArrayList<Integer>();
		repostrates = new ArrayList<Double>();
		averagerepostrate = 0;
		tweets = new ArrayList<Integer>();
		originals = new ArrayList<Integer>();
		reposts = new ArrayList<Integer>();
		waitingtweets = new ArrayList<Integer>();
		waitingtimes = new ArrayList<Integer>();
		this.Maxtweetnumber = maxtweetnumber;
		this.thres_post = thres_post;
		this.thres_repost = thres_repost;
		//System.out.println("Mainkey of Human: "+mainkey+" maxtweetnumber: "+maxtweetnumber+" thres_post: "+thres_post+" thres_repost: "+thres_repost);
	}

	public Human(int mainkey2, int maxtweetnumber2, double thres_post2,
			double thres_repost2, int checkfrequency2, int processtime2) {
		// TODO Auto-generated constructor stub
		this.mainkey = mainkey2;
		followerkeys = new ArrayList<Integer>();
		friendkeys = new ArrayList<Integer>();
		friendreception = new ArrayList<Integer>();
		diversityreception = new ArrayList<Integer>();
		repostrates = new ArrayList<Double>();
		averagerepostrate = 0;
		tweets = new ArrayList<Integer>();
		originals = new ArrayList<Integer>();
		reposts = new ArrayList<Integer>();
		waitingtweets = new ArrayList<Integer>();
		waitingtimes = new ArrayList<Integer>();
		this.Maxtweetnumber = maxtweetnumber2;
		this.thres_post = thres_post2;
		this.thres_repost = thres_repost2;
		this.checkfrequency = checkfrequency2;
		this.processtime = processtime2;
	}

	public void post(int posttime) {
		// TODO Auto-generated method stub
		if(istopost()){
			Tweet tweet = new Tweet(this.mainkey, posttime);
			tweets.add(tweet.getMainkey());
			originals.add(tweet.getMainkey());
		}
	}

	private boolean istopost() {
		// TODO Auto-generated method stub
		double random = Function.getNormalDouble(Network.mean_post, Network.variance_post);
		if(random>thres_post){
			return true;
		}else{
			return false;
		}
	}

	public void repost(Network network, int reposttime) {
		// TODO Auto-generated method stub
		ArrayList<Integer> tweetmainkeys = accesslimitedtweets(network, Maxtweetnumber);
			
		for(int tweetorder=0;tweetorder<tweetmainkeys.size();tweetorder++){
			int tweetmainkey = tweetmainkeys.get(tweetorder);
				waitingtweets.add(tweetmainkey);
				waitingtimes.add(getProcesstime());
		}
			
		dealwithwaitingtweets(reposttime);
			
	}

	private void dealwithwaitingtweets(int reposttime) {
		// TODO Auto-generated method stub
		for(int tweetorder=0;tweetorder<waitingtweets.size();tweetorder++){
			int tweetmainkey = waitingtweets.get(tweetorder);
			int waitingtime = waitingtimes.get(tweetorder);
			if(waitingtime == 0){
				if(istorepost(tweetmainkey)){
					Tweet tweet = new Tweet(this.mainkey, reposttime, waitingtweets.get(tweetorder));
					tweets.add(tweet.getMainkey());
					reposts.add(tweet.getMainkey());
					int friendorder = friendkeys.indexOf(tweet.getInreplytouserid());
					double repostrate = repostrates.get(friendorder)*0.9+(1-0.9);
					averagerepostrate = averagerepostrate+(repostrate-repostrates.get(friendorder))/repostrates.size();
					repostrates.set(friendorder, repostrate);
				}
				
				waitingtimes.remove(tweetorder);
				waitingtweets.remove(tweetorder);
			}else{
				waitingtime--;
				waitingtimes.set(tweetorder, waitingtime);
			}
			
		}
	}

	private ArrayList<Integer> accesslimitedtweets(Network network,
			int maxtweetnumber) {
		// TODO Auto-generated method stub
		ArrayList<Integer> tweetmainkeys = new ArrayList<Integer>();
		
		//access tweets from every neighbor with maximum number
		for(int neighbororder=0;neighbororder<friendkeys.size();neighbororder++){
			int receptiontime = friendreception.get(neighbororder);
			if(receptiontime != 0){
				friendreception.set(neighbororder, receptiontime-1);
				continue;
			}else{
				getfriendreception(neighbororder);
			}
			Human human = network.getHumans().get(friendkeys.get(neighbororder));
			int tweetsize = human.tweets.size();
			ArrayList<Integer> friendtweets = new ArrayList<Integer>();

			if(tweetsize<maxtweetnumber){
				friendtweets = human.tweets;
			}else{
				friendtweets.addAll(human.tweets.subList(tweetsize-maxtweetnumber, tweetsize-1));
			}
			
			if(tweetmainkeys.size()<maxtweetnumber){
				tweetmainkeys.addAll(friendtweets);
				tweetmainkeys = Function.insertsort(tweetmainkeys);
			}else{
				tweetloop:for(int friendtweetorder = 0;friendtweetorder<friendtweets.size();friendtweetorder++){
					if(friendtweets.get(friendtweetorder)<tweetmainkeys.get(maxtweetnumber-1)){
						break tweetloop;
					}else{
						Function.limitedinsert(tweetmainkeys, friendtweets.get(friendtweetorder));
					}
				}
			}

		}
		
		return tweetmainkeys;
	}

	private void getfriendreception(int neighbororder) {
		// TODO Auto-generated method stub
		int receptiontime = diversityreception.get(neighbororder)+(int)(repostrates.get(neighbororder)-averagerepostrate);
		diversityreception.set(neighbororder, receptiontime);
		friendreception.set(neighbororder, diversityreception.get(neighbororder)+checkfrequency);
	}

	@SuppressWarnings("unused")
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

	private boolean istorepost(int tweetmainkey) {
		// TODO Auto-generated method stub
		for(int tweetorder=0;tweetorder<tweets.size();tweetorder++){
			if(!Tweet.isSameOrginal(tweetmainkey, tweets.get(tweetorder))){
				return false;
			}
		}
		double imitation = getimitation(tweetmainkey);
		double random = Function.getNormalDouble(Network.mean_repost, Network.variance_repost);
		double randoms = (imitation+random)/2;
		if(random>thres_repost){
			return true;
		}else{
			return false;
		}
	}

	private double getimitation(int tweetmainkey) {
		// TODO Auto-generated method stub
		double imitation = 0;
		
		double self = 0;
		for(int tweetorder=0;tweetorder<tweets.size();tweetorder++){
			if(Tweet.isSameOrginal(tweetmainkey, tweets.get(tweetorder))){
				self++;
			}
		}
		
		self = self/tweets.size();
		
		double other = 0;
		for(int tweetorder=0;tweetorder<waitingtweets.size();tweetorder++){
			if(Tweet.isSameOrginal(tweetmainkey, waitingtweets.get(tweetorder))){
				other++;
			}
		}
		
		other = other/waitingtweets.size();
		
		imitation = (other-self+1)/2;
		return imitation;
	}

}
