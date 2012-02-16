package network;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;

public class Network {
	
	public boolean [][] matrix;
	public double [][] weightedmatrix;
	public static double variance_post;
	public static double mean_post;
	public static double mean_repost;
	public static double variance_repost;
	public static double mean_tweetnumber;
	public static double variance_tweetnumber;
	private ArrayList<Human> humans;
	private int NodeNum;
	
	public int getNodeNum() {
		return NodeNum;
	}

	public ArrayList<Human> getHumans() {
		return humans;
	}

	public Network(int NodeNum, int LeastNum) {
		// TODO Auto-generated constructor stub
		InitiationHumans(NodeNum);
	}

	private void InitiationHumans(int nodeNum) {
		// TODO Auto-generated method stub
		this.NodeNum = nodeNum;
		humans = new ArrayList<Human>();
		for(int i=0;i<nodeNum;i++){
			int maxtweetnumber = Function.getNormalPositiveInt(mean_tweetnumber, variance_tweetnumber);
			double thres_post = Function.getNormalDouble(mean_post, variance_post);
			double thres_repost = Function.getNormalDouble(mean_repost, variance_repost);
			humans.add(new Human(i, maxtweetnumber, thres_post, thres_repost));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Network() {
		// TODO Auto-generated constructor stub
		Mongo mongo;
		try {
			mongo = new Mongo("10.3.4.84", 27017);	
			mongo.getDB("db").authenticate("cssc", new char[] { '1' });
			DB db = mongo.getDB("db");
			DBCollection anonymouscollection = db.getCollection("AnonymousUserInformation");	
			ArrayList<ArrayList> followerkeys = getFollowerkeys(anonymouscollection, "Followers IDs");

			
			InitiationHumans(followerkeys.size());
			for(int i=0;i<followerkeys.size();i++){
				ArrayList<Integer> followerkey = followerkeys.get(i);
				for(int j=0;j<followerkey.size();j++){
					int follower = followerkey.get(j);
					humans.get(i).getFollowerkeys().add(follower);
					humans.get(follower).getFriendkeys().add(i);
				}
			}
			
			//System.exit(0);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<ArrayList> getFollowerkeys(DBCollection collection, String string) {
		// TODO Auto-generated method stub
		DBCursor cursor = collection.find(new BasicDBObject("User IDs",
				new BasicDBObject(QueryOperators.EXISTS, true)));

		ArrayList<ArrayList> followers = new ArrayList<ArrayList>();
		ArrayList<ArrayList> orderedfollowers = new ArrayList<ArrayList>();
		ArrayList<ArrayList> orderedfollowerkeys = new ArrayList<ArrayList>();
		ArrayList<Integer> follower = new ArrayList<Integer>();
		ArrayList<Integer> followerids = new ArrayList<Integer>();
		ArrayList<Integer> users = new ArrayList<Integer>();

		for (int i = 0; cursor.hasNext(); i++) {
			BasicDBObject object = (BasicDBObject) cursor.next();
			followers.add((ArrayList<Integer>) object.get(string));
			ArrayList<Integer> temp = (ArrayList) object.get("User IDs");
			users.addAll(temp);
		}
		

		int[] numberorder = new int[users.size()];
		numberorder = getNumberOrder(users);

		for (int i = 0; i < followers.size(); i++) {
			follower = followers.get(i);
			for (int j = 0; j < follower.size(); j++) {
				int followerid = follower.get(j);
				if (followerid == -1) {
					orderedfollowers.add((ArrayList) followerids.clone());
					followerids.clear();
				} else {
					followerids.add(followerid);
				}
			}

			follower.clear();
			//System.out.println(i);
		}

		for(int i=0;i<orderedfollowers.size();i++){
			orderedfollowerkeys.add(orderedfollowers.get(numberorder[i]));
			if(i!=users.get(numberorder[i])){
				System.out.println("i: "+i+"  user: "+users.get(numberorder[i]));
			}
		}
		// followers.clear();

		System.out.println("Size of User: " + orderedfollowers.size());

		int number = 0;
		for (ArrayList follow : orderedfollowers) {
			number += follow.size();
		}
		System.out.println("Size of Followers: " + number);
		return orderedfollowers;
	}

	private int[] getNumberOrder(ArrayList<Integer> numbers) {
		// TODO Auto-generated method stub
		int[] numberorder = new int[numbers.size()];

		for (int i = 0; i < numbers.size(); i++) {
			numberorder[i] = numbers.indexOf(i);
		}

		return numberorder;
	}

	public ArrayList<Integer> getneighbors(int mainkey) {
		// TODO Auto-generated method stub
		ArrayList<Integer> neighbormainkey = new ArrayList<Integer>();
		for(int humanorder=0;humanorder<NodeNum;humanorder++){
			if(matrix[mainkey][humanorder]){
				neighbormainkey.add(humanorder);
			}
		}
		
		return neighbormainkey;
	}

	

}
