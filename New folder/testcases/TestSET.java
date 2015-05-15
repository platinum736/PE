package testcases;

import static org.junit.Assert.fail;

import java.util.Set;

import junit.framework.Assert;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;

import org.junit.Before;
import org.junit.Test;

import set.SET;
import set.SETBasicBlockNode;
import set.SETDecisionNode;
import set.SETEdge;
import set.SETNode;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;

public class TestSET {

	private SET mSET;

	private ICFG mCFG;
	private ICFGBasicBlockNode mCFGStart, mCFGEnd;

	@Test
	public final void testSET() {
		Assert.assertEquals(this.mSET.getCFG(), this.mCFG);
	}

	@Test
	public final void testGetStartNode() {
		SETNode start = this.mSET.getStartNode();
		Assert.assertNotNull(start);
		Set<SETNode> nodes = this.mSET.getNodeSet();
		Assert.assertEquals(nodes.size(), 1);
		Assert.assertEquals(nodes.contains(start), true);
	}

	@Test
	public final void testGetLeafNodes() {
		SETNode start = this.mSET.getStartNode();
		Assert.assertNotNull(start);
		Set<SETNode> leaves = this.mSET.getLeafNodes();
		Assert.assertEquals(leaves.size(), 1);
		Assert.assertEquals(leaves.contains(start), true);
	}

	@Test
	public final void testAddDecisionNode() {
		SETDecisionNode node = new SETDecisionNode(null, null, null);
		this.mSET.addDecisionNode(node);
		Assert.assertEquals(this.mSET.getNumberOfNodes(), 2);
		Assert.assertEquals(this.mSET.getNumberOfDecisionNodes(), 1);
		Assert.assertEquals(node.getSET(), this.mSET);
	}

	@Test
	public final void testAddBasicBlockNode() {
		SETBasicBlockNode node = new SETBasicBlockNode(null, null);
		this.mSET.addBasicBlockNode(node);
		Assert.assertEquals(this.mSET.getNumberOfNodes(), 2);
		Assert.assertEquals(this.mSET.getNumberOfBasicBlockNodes(), 2);
		Assert.assertEquals(node.getSET(), this.mSET);
	}

	@Test
	public final void testHasBasicBlockNode() {
		SETBasicBlockNode node = new SETBasicBlockNode(null, null);
		this.mSET.addBasicBlockNode(node);
		Assert.assertEquals(this.mSET.hasBasicBlockNode(node), true);
		Assert.assertEquals(this.mSET.hasBasicBlockNode(this.mSET.getStartNode()), true);	
	}

	@Test
	public final void testHasNode() {
		SETBasicBlockNode node = new SETBasicBlockNode(null, null);
		this.mSET.addBasicBlockNode(node);
		Assert.assertEquals(this.mSET.hasNode(node), true);
		Assert.assertEquals(this.mSET.hasNode(this.mSET.getStartNode()), true);	
		SETDecisionNode dnode = new SETDecisionNode(null, null, null);
		this.mSET.addDecisionNode(dnode);
		SETBasicBlockNode node1 = new SETBasicBlockNode(null, null);
		Assert.assertEquals(this.mSET.hasNode(node1), false);
	}

	@Test
	public final void testAddEdge() {
		SETEdge edge = new SETEdge(null, null, null);
		this.mSET.addEdge(edge);
		Assert.assertEquals(this.mSET.hasEdge(edge), true);
		Assert.assertEquals(edge.getSET(), this.mSET);
	}

	@Test
	public final void testGetNumberOfEdges() {
		Assert.assertEquals(this.mSET.getNumberOfEdges(), 0);
		SETEdge edge = new SETEdge(null, null, null);
		this.mSET.addEdge(edge);
		Assert.assertEquals(this.mSET.getNumberOfEdges(), 1);
	}

	@Test
	public final void testGetNodeSet() {
		SETNode start = this.mSET.getStartNode();
		Assert.assertNotNull(start);
		Set<SETNode> nodes = this.mSET.getNodeSet();
		Assert.assertEquals(nodes.size(), 1);
		SETBasicBlockNode node = new SETBasicBlockNode(null, null);
		this.mSET.addBasicBlockNode(node);
		nodes = this.mSET.getNodeSet();
		Assert.assertEquals(nodes.size(), 2);
		SETDecisionNode dnode = new SETDecisionNode(null, null, null);
		this.mSET.addDecisionNode(dnode);
		nodes = this.mSET.getNodeSet();
		Assert.assertEquals(nodes.size(), 3);
	}

	@Test
	public final void testGetEdgeSet() {

		Set<SETEdge> set = this.mSET.getEdgeSet();
		Assert.assertEquals(set.size(), 0);
		SETEdge edge = new SETEdge(null, null, null);
		this.mSET.addEdge(edge);
		set = this.mSET.getEdgeSet();
		Assert.assertEquals(set.size(), 1);
		Assert.assertEquals(set.contains(edge), true);
	}

	@Test
	public final void testGetDecisionNodeSet() {
		Set<SETDecisionNode> nodes = this.mSET.getDecisionNodeSet();
		Assert.assertEquals(nodes.size(), 0);
		SETBasicBlockNode node = new SETBasicBlockNode(null, null);
		this.mSET.addBasicBlockNode(node);
		nodes = this.mSET.getDecisionNodeSet();
		Assert.assertEquals(nodes.size(), 0);
		SETDecisionNode dnode = new SETDecisionNode(null, null, null);
		this.mSET.addDecisionNode(dnode);
		nodes = this.mSET.getDecisionNodeSet();
		Assert.assertEquals(nodes.size(), 1);
	}

	@Test
	public final void testGetBasicBlockNodeSet() {
		Set<SETBasicBlockNode> nodes = this.mSET.getBasicBlockNodeSet();
		Assert.assertEquals(nodes.size(), 1);
		SETBasicBlockNode node = new SETBasicBlockNode(null, null);
		this.mSET.addBasicBlockNode(node);
		nodes = this.mSET.getBasicBlockNodeSet();
		Assert.assertEquals(nodes.size(), 2);
		SETDecisionNode dnode = new SETDecisionNode(null, null, null);
		this.mSET.addDecisionNode(dnode);
		nodes = this.mSET.getBasicBlockNodeSet();
		Assert.assertEquals(nodes.size(), 2);
	}

	@Test
	public final void testAddVariable() {
		fail("Not yet implemented");
	}

	@Test
	public final void testHasVariable() {
		fail("Not yet implemented");
	}
	
	@Before
	public final void setup() {
		try {
			this.mCFGStart = new CFGBasicBlockNode(null);
			this.mCFGEnd = new CFGBasicBlockNode(null);
			this.mCFG = new CFG(this.mCFGStart, this.mCFGEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		this.mSET = new SET(this.mCFG);
	}
	
}
