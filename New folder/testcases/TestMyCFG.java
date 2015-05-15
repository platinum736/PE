package testcases;

import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import mycfg.CFEdge;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;

import org.junit.Test;

import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;
import cfg.ICFGNode;
import expression.Variable;

public class TestMyCFG {

	private ICFG mCFG;
	private ICFGBasicBlockNode mStart, mEnd;
	
	@Test
	public final void testCFG() {
		this.mStart = new CFGBasicBlockNode(this.mCFG);
		this.mEnd = new CFGBasicBlockNode(this.mCFG);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		Assert.assertEquals(this.mStart.getCFG(), this.mCFG);
		Assert.assertEquals(this.mEnd.getCFG(), this.mCFG);
		Assert.assertEquals(this.mStart, this.mCFG.getStartNode());
		Assert.assertEquals(this.mEnd, this.mCFG.getStopNode());
	}

	@Test
	public final void testAddDecisionNode() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGDecisionNode node = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(node);
		Assert.assertEquals(this.mCFG.hasDecisionNode(node), true);
		Assert.assertEquals(this.mCFG.hasNode(node), true);
	}

	@Test
	public final void testAddBasicBlockNode() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
		Assert.assertEquals(this.mCFG.hasBasicBlockNode(node), true);
		Assert.assertEquals(this.mCFG.hasNode(node), true);
	}

	@Test
	public final void testDeleteBasicBlockNode() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
		Assert.assertEquals(this.mCFG.hasBasicBlockNode(node), true);
		Assert.assertEquals(this.mCFG.hasNode(node), true);
		this.mCFG.deleteBasicBlockNode(node);
		Assert.assertEquals(this.mCFG.hasBasicBlockNode(node), false);
		Assert.assertEquals(this.mCFG.hasNode(node), false);
	}

	@Test
	public final void testGetNumberOfBasicBlockNodes() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGBasicBlockNode node = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(node);
		Assert.assertEquals(this.mCFG.getNumberOfBasicBlockNodes(), 3);
	}

	@Test
	public final void testGetNumberOfCFGDecisionNodes() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGDecisionNode node = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(node);
		Assert.assertEquals(this.mCFG.getNumberOfDecisionNodes(), 1);
	}

	@Test
	public final void testHasNode() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGDecisionNode dnode = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(dnode);
		ICFGBasicBlockNode bbnode = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(bbnode);
		
		Assert.assertEquals(this.mCFG.hasNode(this.mStart), true);
		Assert.assertEquals(this.mCFG.hasNode(this.mEnd), true);
		Assert.assertEquals(this.mCFG.hasNode(dnode), true);
		Assert.assertEquals(this.mCFG.hasNode(bbnode), true);
		ICFGBasicBlockNode bbnode1 = new CFGBasicBlockNode(null);
		Assert.assertEquals(this.mCFG.hasNode(bbnode1), false);
		ICFGDecisionNode dnode1 = new CFGDecisionNode(null, null, null, null);
		Assert.assertEquals(this.mCFG.hasNode(dnode1), false);
	}

	@Test
	public final void testGetNumberOfNodes() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFGDecisionNode dnode = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(dnode);
		ICFGBasicBlockNode bbnode = new CFGBasicBlockNode(null);
		this.mCFG.addBasicBlockNode(bbnode);
		Assert.assertEquals(this.mCFG.getNumberOfNodes(), 4);
	}

	@Test
	public final void testAddEdge() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		Assert.assertEquals(edge.getHead(), this.mEnd);
		Assert.assertEquals(edge.getTail(), this.mStart);
		this.mCFG.addEdge(edge);
		Assert.assertEquals(this.mStart.getOutgoingEdge(), edge);
		List<ICFEdge> list = this.mEnd.getIncomingEdgeList();
		Assert.assertEquals(list.size(), 1);
		Assert.assertEquals(list.get(0), edge);
	}

	@Test
	public final void testDeleteEdge() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		this.mCFG.addEdge(edge);
		this.mCFG.deleteEdge(edge);
		Assert.assertEquals(this.mCFG.hasEdge(edge), false);
		Assert.assertNotSame(this.mCFG, edge.getCFG());
		Assert.assertNotSame(this.mStart.getOutgoingEdge(), edge);
		List<ICFEdge> list = this.mEnd.getIncomingEdgeList();
		Assert.assertEquals(list.size(), 0);
	}

	@Test
	public final void testGetNumberOfEdges() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		this.mCFG.addEdge(edge);
		Assert.assertEquals(this.mCFG.getNumberOfEdges(), 1);
		this.mCFG.deleteEdge(edge);
		Assert.assertEquals(this.mCFG.getNumberOfEdges(), 0);
	}

	@Test
	public final void testGetNodeSet() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		Set<ICFGNode> set = this.mCFG.getNodeSet();
		Assert.assertEquals(set.size(), 2);
		Assert.assertEquals(set.contains(this.mStart), true);
		Assert.assertEquals(set.contains(this.mEnd), true);
	}

	@Test
	public final void testGetEdgeSet() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		Set<ICFEdge> set = this.mCFG.getEdgeSet();
		Assert.assertEquals(set.size(), 0);
		ICFEdge edge = new CFEdge(this.mCFG, this.mStart, this.mEnd);
		this.mCFG.addEdge(edge);
		Assert.assertEquals(set.size(), 1);
	}

	@Test
	public final void testGetDecisionNodeSet() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		Set<ICFGDecisionNode> set = this.mCFG.getDecisionNodeSet();
		Assert.assertEquals(set.size(), 0);
		Assert.assertEquals(set.contains(this.mStart), false);
		Assert.assertEquals(set.contains(this.mEnd), false);
		ICFGDecisionNode dnode = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(dnode);
		set = this.mCFG.getDecisionNodeSet();
		Assert.assertEquals(set.size(), 1);
		Assert.assertEquals(set.contains(dnode), true);
	}

	@Test
	public final void testGetBasicBlockNodeSet() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		Set<ICFGBasicBlockNode> set = this.mCFG.getBasicBlockNodeSet();
		Assert.assertEquals(set.size(), 2);
		Assert.assertEquals(set.contains(this.mStart), true);
		Assert.assertEquals(set.contains(this.mEnd), true);
	}

	@Test
	public final void testAddVariable() {
		this.mStart = new CFGBasicBlockNode(null);
		this.mEnd = new CFGBasicBlockNode(null);
		try {
			this.mCFG = new CFG(this.mStart, this.mEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		Variable v = null;
		try {
			v = new Variable("var1", this.mCFG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mCFG.addVariable(v);
		Assert.assertEquals(this.mCFG.hasVariable(v), true);
	}
}
