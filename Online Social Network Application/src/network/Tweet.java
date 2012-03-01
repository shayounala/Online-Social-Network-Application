package network;

import java.util.ArrayList;

public class Tweet {

	private static ArrayList<Tweet>tweets = new ArrayList<Tweet>();
	private static int Number = 0;
	private int createdtime;
	public int getCreatedtime() {
		return createdtime;
	}

	public int getMainkey() {
		return mainkey;
	}

	private int mainkey;
	private boolean isretweet;
	public boolean isIsretweet() {
		return isretweet;
	}

	private int userid;
	private int inreplytomainkey;
	public int getInreplytomainkey() {
		return inreplytomainkey;
	}

	public void setInreplytomainkey(int inreplytomainkey) {
		this.inreplytomainkey = inreplytomainkey;
	}

	public static ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public static int getNumber() {
		return Number;
	}

	public int getInreplytouserid() {
		return inreplytouserid;
	}

	private int inreplytouserid;
	private ArrayList<Integer> reposts;
	public ArrayList<Integer> getReposts() {
		return reposts;
	}

	private int original;
	private int depth;
	private ArrayList<Integer> width;

	public ArrayList<Integer> getWidth() {
		return width;
	}

	public int getDepth() {
		return depth;
	}

	public int getUserid() {
		return userid;
	}

	public Tweet(int mainkey, int posttime) {
		// TODO Auto-generated constructor stub
		this.userid = mainkey;
		this.createdtime = posttime;
		this.mainkey = Number;
		this.original = this.mainkey;
		reposts =  new ArrayList<Integer>();
		width = new ArrayList<Integer>();
		tweets.add(this);
		this.depth = 1;
		this.width.add(1);
		Number++;
	}

	public Tweet(int mainkey, int reposttime, int mainkey2) {
		// TODO Auto-generated constructor stub
		this.userid = mainkey;
		this.isretweet = true;
		this.createdtime = reposttime;
		this.mainkey = Number;
		this.original = tweets.get(mainkey2).original;
		reposts = new ArrayList<Integer>();
		tweets.add(this);
		
		Number++;
		
		this.inreplytouserid = tweets.get(mainkey2).userid;
		this.inreplytomainkey = mainkey2;
		this.depth = tweets.get(mainkey2).depth+1;
		tweets.get(mainkey2).reposts.add(mainkey);
		if(tweets.get(original).width.size()<depth){
			tweets.get(original).width.add(1);
		}else{
			int temp = tweets.get(original).width.get(depth-1);
			tweets.get(original).width.set(depth-1, temp+1);
		}
	}

	public static boolean isSameOrginal(int tweetmainkey, int tweetmainkey2) {
		// TODO Auto-generated method stub
		if(tweets.get(tweetmainkey).original == tweets.get(tweetmainkey2).original){
			return true;
		}else{
			return false;
		}
	}

}
