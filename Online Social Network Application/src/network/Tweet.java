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

	public int getUserid() {
		return userid;
	}

	public Tweet(int mainkey, int posttime) {
		// TODO Auto-generated constructor stub
		this.userid = mainkey;
		this.createdtime = posttime;
		this.mainkey = Number;
		this.original = -1;
		reposts =  new ArrayList<Integer>();
		tweets.add(this);
		
		Number++;
	}

	public Tweet(int mainkey, int reposttime, int mainkey2) {
		// TODO Auto-generated constructor stub
		this.userid = mainkey;
		this.isretweet = true;
		this.createdtime = reposttime;
		this.mainkey = Number;
		tweets.add(this);
		
		Number++;
		
		this.inreplytouserid = tweets.get(mainkey2).userid;
		this.inreplytomainkey = mainkey2;
		this.original = tweets.get(mainkey2).original;
		tweets.get(mainkey2).reposts.add(mainkey);
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
