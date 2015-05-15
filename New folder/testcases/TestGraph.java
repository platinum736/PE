package testcases;

import graph.IEdge;
import graph.INode;
import junit.framework.Assert;
import mygraph.Edge;
import mygraph.Graph;
import mygraph.Node;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGraph {

	Graph mGraph;
	Node mRoot;
	Node n1;
	Node n2;
	Edge e1;
	Edge e2;
	
	@Before
	public void setUp() throws Exception {
		this.mRoot = new Node(null);
		this.mGraph = new Graph(mRoot);
		this.n1 = new Node(null);
		this.n2 = new Node(null);
		this.e1 = new Edge(null, n1, n2);
		this.e2 = new Edge(null, n1, n1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGraph() {
		Assert.assertEquals(this.mRoot, this.mGraph.getRoot());
		Assert.assertEquals(this.mGraph.getNumberOfNodes(), 1);
	}

	@Test
	public final void testGetRoot() {
		Assert.assertEquals(this.mRoot, this.mGraph.getRoot());
	}

	@Test
	public final void testAddNode() {
		this.mGraph.addNode(n1);
		Assert.assertEquals(this.mGraph.getNumberOfNodes(), 2);
	}

	@Test
	public final void testHasNode() {
		this.mGraph.addNode(n1);
		boolean r1 = this.mGraph.hasNode(n1);
		boolean r2 = this.mGraph.hasNode(n2);
		Assert.assertEquals(r1, true);
		Assert.assertEquals(r2, false);
	}

	@Test
	public final void testDeleteNode() {
		this.mGraph.addNode(n1);
		INode r1 = this.mGraph.deleteNode(n1);
		INode r2 = this.mGraph.deleteNode(n2);
		Assert.assertEquals(n1, r1);
		Assert.assertEquals(r2, null);
		Assert.assertEquals(this.mGraph.hasNode(n1), false);
	}

	@Test
	public final void testAddEdge() {
		this.mGraph.addNode(n1);
		this.mGraph.addNode(n2);
		this.mGraph.addEdge(e1);
		Assert.assertEquals(true, this.mGraph.hasEdge(e1));
		Assert.assertEquals(this.mGraph, e1.getGraph());
		Assert.assertEquals(n1, e1.getTail());
		Assert.assertEquals(n2, e1.getHead());
		Assert.assertEquals(this.mGraph.getNumberOfEdges(), 1);
		Assert.assertEquals(n1.isOutgoingEdge(e1), true);
		Assert.assertEquals(n2.isIncomingEdge(e1), true);
	}

	@Test
	public final void testDeleteEdge() {
		this.mGraph.addNode(n1);
		this.mGraph.addNode(n2);
		this.mGraph.addEdge(e1);
		IEdge r1 = this.mGraph.deleteEdge(e1);
		IEdge r2 = this.mGraph.deleteEdge(e2);
		Assert.assertEquals(r1, e1);
		Assert.assertEquals(r2, null);
		Assert.assertEquals(e1.getGraph(), null);
		Assert.assertEquals(this.mGraph.getNumberOfEdges(), 0);
		Assert.assertEquals(n1.isOutgoingEdge(e1), false);
		Assert.assertEquals(n1.isIncomingEdge(e1), false);
	}

	@Test
	public final void testHasEdge() {
		Assert.assertEquals(true, true);
	}

}
