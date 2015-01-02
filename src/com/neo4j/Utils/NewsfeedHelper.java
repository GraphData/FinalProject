package com.neo4j.Utils;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

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
			newsIndex.add(news, "content", content);
			people.createRelationshipTo(news, RelTypes.CONTAIN);
			
			tx.success();
		}
		finally
		{
			tx.finish();
		}
		return;
	}
}
