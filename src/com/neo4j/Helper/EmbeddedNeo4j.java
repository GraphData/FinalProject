package com.neo4j.Helper;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;


public class EmbeddedNeo4j {
	//public static final String DB_PATH = "neo4j-db";
	public static final String DB_PATH = "F:\\JavaProject\\houfang\\workspace\\GraphData\\neo4j-db";
	public static final GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	public static final IndexManager index = graphDb.index();
	Node firstNode;
	Node secondNode;
	Relationship relationship;
	
	public static enum RelTypes implements RelationshipType
	{
		KNOWS,
		CONTAIN
	}
	
	public void createDb()
	{
		registerShutdownHook(graphDb);
	}
	
	public static void cleanUp(final GraphDatabaseService graphDb,
			final Index<Node> nodeIndex) {
		for (Node node : graphDb.getAllNodes()) {
			for (Relationship rel : node.getRelationships()) {
				rel.delete();
			}
			nodeIndex.remove(node);
			node.delete();
		}
	}
 
	public static Index<Node> getIndex(String name)
	{
		return index.forNodes(name);
	}
	
	public static void registerShutdownHook(final GraphDatabaseService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
}
