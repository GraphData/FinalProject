package com.neo4j.Utils;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;

import com.GraphData.Model.AccountProfile;
import com.GraphData.Model.AccountModel;
import com.neo4j.Helper.EmbeddedNeo4j;
import com.neo4j.Helper.EmbeddedNeo4j.RelTypes;

public class UserHelper {
	
	public static void createNewUser(String name, String password)
	{
		Node people;
		//Node secondNode;
		//Relationship relationship;
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people"); 
			people = EmbeddedNeo4j.graphDb.createNode();
			people.setProperty( "name", name );
			people.setProperty("password", password);
			peopleIndex.add(people, "name", people.getProperty("name"));

			//relationship = firstNode.createRelationshipTo( secondNode, EmbeddedNeo4j.RelTypes.CONTAIN );
			//relationship.setProperty( "name", "has password" );
			tx.success();
		}
		finally
		{
			tx.close();
		}
	}
	
	public static Boolean checkPassword(String name, String password)
	{
		Node people;
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", name);
			people = hits.getSingle();
			if(people.getProperty("password").toString().equals(password))
			{
				tx.success();
				return true;
			}
			tx.success();
		}
		finally
		{
			tx.close();
		}
		
		return false;
	}

	public static void createUserProfile(AccountProfile account)
	{
		Node people;
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", account.getUsername());
			people = hits.getSingle();
			System.out.println("profile saved:" + people.getProperty("password").toString());
			System.out.println("birthday" + account.getBirthday());
			people.setProperty("birthday", account.getBirthday());
			people.setProperty("college", account.getCollege());
			people.setProperty("major", account.getMajor());
			people.setProperty("hobby", account.getHobby());
			System.out.println("profile saved:" + people.getProperty("password").toString());
			tx.success();
		}
		finally
		{
			tx.close();
		}
		
	}
	
	public static AccountProfile getProfile(String name)
	{
		Node people;
		AccountProfile accountProfile = new AccountProfile();
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", name);
			people = hits.getSingle();
			accountProfile.setBirthday(people.getProperty("birthday").toString());
			accountProfile.setCollege(people.getProperty("college").toString());
			accountProfile.setMajor(people.getProperty("major").toString());
			accountProfile.setHobby(people.getProperty("hobby").toString());
			tx.success();
		}
		finally
		{
			tx.close();
		}
		
		return accountProfile;
	}
	
	public static List<AccountModel> searchUser(String name)
	{
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		List<AccountModel> accounts = new ArrayList<AccountModel>();
		Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
		AccountModel accountModel;
		System.out.println("search database start" + name + "aa");
		/*
		IndexHits<Node> findOnesMore=peopleIndex.get("name", "java"); 
		Node nodeTemp1=findOnesMore.getSingle();
		System.out.println("java password:" + nodeTemp1.getProperty("password"));
		*/
		if(name.equals("houfang"))
		{
			System.out.println("equal");
		}
		for (Node searchPeople : peopleIndex.query("name", name))
		{
		    // This will return Reeves and Bellucci
			accountModel = new AccountModel();
			accountModel.setUsername(searchPeople.getProperty("name").toString());
			accountModel.setPassword(searchPeople.getProperty("password").toString());
			System.out.println(searchPeople.getProperty("name").toString());
			accounts.add(accountModel);
		}
		tx.success();
		System.out.println("search database finish");
		return accounts;
	}
	
	public static void followPeople(String from, String to)
	{
		Node f, t;
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", from);
			f = hits.getSingle();
			hits = peopleIndex.get("name", to);
			t = hits.getSingle();
			System.out.println("from:" + f.getProperty("name").toString() + " to:" + t.getProperty("name").toString());
			f.createRelationshipTo(t, RelTypes.FOLLOWS);
			//f.getSingleRelationship(arg0, arg1)
			tx.success();
		}
		finally
		{
			tx.close();
		}
		
		return;
	}
	
	public static List<AccountProfile> getFollowList(String name)
	{
		Node f, t;
		String output = "";
		List<AccountProfile> followPeople = new ArrayList<AccountProfile>();
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", name);
			f = hits.getSingle();
		
			for(Relationship r : f.getRelationships(RelTypes.FOLLOWS, Direction.OUTGOING))
			{
				//System.out.println(name + " follows " + r.getEndNode().getProperty("name"));
				output += r.getEndNode().getProperty("name") + "\n";
				AccountProfile people = new AccountProfile();
			    people.setUsername(r.getEndNode().getProperty("name").toString());
			    followPeople.add(people);
			}
			/*
			TraversalDescription followsTraversal = EmbeddedNeo4j.graphDb.traversalDescription()
			        .depthFirst()
			        .relationships(RelTypes.FOLLOWS)
			        .uniqueness(Uniqueness.RELATIONSHIP_GLOBAL);
			
			for (Node currentNode : followsTraversal
			        .traverse(f)
			        .nodes())
			{
				if(currentNode.getProperty("name").toString().equals(name))
				{
					continue;
				}
			    output += currentNode.getProperty("name") + "\n";
			    AccountProfile people = new AccountProfile();
			    people.setUsername(currentNode.getProperty("name").toString());
			    followPeople.add(people);
			}*/
			System.out.println("following:\n" + output);
		}
		finally
		{
			tx.close();
		}
		
		return followPeople;
	}
	
	public static void cancelFollowPeople(String from, String to)
	{
		Node f, t;
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", from);
			f = hits.getSingle();
			hits = peopleIndex.get("name", to);
			t = hits.getSingle();
			System.out.println("from:" + f.getProperty("name").toString() + " to:" + t.getProperty("name").toString());
			for(Relationship r : f.getRelationships(RelTypes.FOLLOWS))
			{
				System.out.println(from + " follows " + r.getEndNode().getProperty("name"));
				if(r.getEndNode().getProperty("name").equals(to))
				{
					System.out.println(from + "-" + to);
					r.delete();
				}
			}
				
			tx.success();
		}
		finally
		{
			tx.close();
		}
		
		return;
	}
	
	
}
