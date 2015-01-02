package com.neo4j.Utils;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import com.GraphData.Model.AccountProfile;
import com.GraphData.Model.AccountModel;
import com.neo4j.Helper.EmbeddedNeo4j;

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
			tx.finish();
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
			tx.finish();
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
			tx.finish();
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
		}
		finally
		{
			tx.finish();
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
}
