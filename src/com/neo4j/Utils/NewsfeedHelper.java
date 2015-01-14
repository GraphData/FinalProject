package com.neo4j.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;

import com.GraphData.Model.AccountProfile;
import com.GraphData.Model.Newsfeed;

import com.neo4j.Helper.EmbeddedNeo4j;
import com.neo4j.Helper.EmbeddedNeo4j.RelTypes;

public class NewsfeedHelper {
	public static void PublishNewsfeed(String name, String content)
	{
		Node people, news;
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			System.out.println("user name" + name);
			IndexHits<Node> hits = peopleIndex.get("name", name);
			people = hits.getSingle();
			Index<Node> newsIndex = EmbeddedNeo4j.getIndex("news"); 
			news = EmbeddedNeo4j.graphDb.createNode();
			news.setProperty( "content", content);
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
			String time = dateFormat.format( now ); 
			news.setProperty("time", time);
			news.setProperty("publisher", name);
			newsIndex.add(news, "content", content);
			people.createRelationshipTo(news, RelTypes.PUBLISHES);
			
			tx.success();
		}
		finally
		{
			tx.finish();
		}
		return;
	}
	
	public static List<Newsfeed> getNewsfeedList(String name)
	{
		List<Newsfeed> news = new ArrayList<Newsfeed>();
		Transaction tx = EmbeddedNeo4j.graphDb.beginTx();
		String output = "";
		Node user;
		try
		{
			Index<Node> peopleIndex = EmbeddedNeo4j.getIndex("people");
			IndexHits<Node> hits = peopleIndex.get("name", name);
			user = hits.getSingle();
			TraversalDescription followsTraversal = EmbeddedNeo4j.graphDb.traversalDescription()
			        .depthFirst()
			        .relationships(RelTypes.FOLLOWS, Direction.OUTGOING)
			        .uniqueness(Uniqueness.RELATIONSHIP_GLOBAL);
			
			TraversalDescription publishTraversal = EmbeddedNeo4j.graphDb.traversalDescription()
			        .depthFirst()
			        .relationships(RelTypes.PUBLISHES, Direction.OUTGOING)
			        .uniqueness(Uniqueness.RELATIONSHIP_GLOBAL);
			
			for (Node follow : followsTraversal
					.evaluator(Evaluators.toDepth(1))
			        .traverse(user)
			        .nodes())
			{
				for(Node currentNew : publishTraversal
				        .traverse(follow)
				        .nodes())
				{
					try
					{
						Newsfeed n = new Newsfeed();
						n.setContent(currentNew.getProperty("content").toString());
						n.setPublisher(currentNew.getProperty("publisher").toString());
						n.setTime(currentNew.getProperty("time").toString());
						news.add(n);
						System.out.println("add success");
						output += n.getContent();
					}
					catch(Exception e)
					{
						System.out.println("no content");
					}
				}
			}
			System.out.println("news:\n" + output);
			
		}
		finally
		{
			tx.finish();
		}
		
		return news;
	}
}
