package testcases;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;
import mycfg.CFEdge;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;

import org.junit.Before;
import org.junit.Test;

import see.SEE;
import set.SET;
import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;

public class TestSEE {

	SEE mSEE;
	private ICFG mCFG;
	private ICFGBasicBlockNode mCFGStart, mCFGEnd;
	
	@Before
	public void setUp() throws Exception {
		try {
			this.mCFGStart = new CFGBasicBlockNode(null);
			this.mCFGEnd = new CFGBasicBlockNode(null);
			this.mCFG = new CFG(this.mCFGStart, this.mCFGEnd);
			this.mSEE = new SEE(this.mCFG);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public final void testSEE() {
		Assert.assertNotNull(this.mSEE.getSET());
	}

	@Test
	public final void testSingleStep_1() {
		ICFEdge edge = new CFEdge(null, this.mCFGStart, this.mCFGEnd);
		this.mCFG.addEdge(edge);
		Set<ICFEdge> set = new HashSet<ICFEdge>();
		set.add(edge);
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 2);
	}

	@Test
	public final void testSingleStep_0() {
		Set<ICFEdge> set = new HashSet<ICFEdge>();
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 1);
	}

	@Test
	public final void testSingleStep_2() {
		ICFGDecisionNode A = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(A);
		ICFEdge e1 = new CFEdge(null, this.mCFGStart, A);	this.mCFG.addEdge(e1);
		ICFGBasicBlockNode B = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(B);
		ICFGBasicBlockNode C = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(C);
		ICFEdge e2 = new CFEdge(null, A, B); this.mCFG.addEdge(e2);
		ICFEdge e3 = new CFEdge(null, A, C); this.mCFG.addEdge(e3);
		A.addOutgoingEdge(e2);
		A.addOutgoingEdge(e3);
		ICFEdge e4 = new CFEdge(null, B, this.mCFGEnd); this.mCFG.addEdge(e4);
		ICFEdge e5 = new CFEdge(null, C, this.mCFGEnd); this.mCFG.addEdge(e5);
		

		Set<ICFEdge> set = new HashSet<ICFEdge>();
		set.add(e1);
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 1);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);
	}

	@Test
	public final void testSingleStep_3() {
		ICFGDecisionNode A = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(A);
		ICFEdge e1 = new CFEdge(null, this.mCFGStart, A);	this.mCFG.addEdge(e1);
		ICFGBasicBlockNode B = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(B);
		ICFGBasicBlockNode C = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(C);
		ICFEdge e2 = new CFEdge(null, A, B); this.mCFG.addEdge(e2);
		ICFEdge e3 = new CFEdge(null, A, C); this.mCFG.addEdge(e3);
		A.addOutgoingEdge(e2);
		A.addOutgoingEdge(e3);
		ICFEdge e4 = new CFEdge(null, B, this.mCFGEnd); this.mCFG.addEdge(e4);
		ICFEdge e5 = new CFEdge(null, C, this.mCFGEnd); this.mCFG.addEdge(e5);
		

		Set<ICFEdge> set = new HashSet<ICFEdge>();
		set.add(e1);
		set.add(e2);
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 1);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);
	}


	@Test
	public final void testSingleStep_4() {
		ICFGDecisionNode A = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(A);
		ICFEdge e1 = new CFEdge(null, this.mCFGStart, A);	this.mCFG.addEdge(e1);
		ICFGBasicBlockNode B = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(B);
		ICFGBasicBlockNode C = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(C);
		ICFEdge e2 = new CFEdge(null, A, B); this.mCFG.addEdge(e2);
		ICFEdge e3 = new CFEdge(null, A, C); this.mCFG.addEdge(e3);
		A.setThenEdge(e2);
		A.setElseEdge(e3);
		ICFEdge e4 = new CFEdge(null, B, this.mCFGEnd); this.mCFG.addEdge(e4);
		ICFEdge e5 = new CFEdge(null, C, this.mCFGEnd); this.mCFG.addEdge(e5);
		
		//step 1
		try {
			this.mSEE.singleStep(e1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 1);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);
		
		//step 1
		try {
			this.mSEE.singleStep(e2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		s = this.mSEE.getSET();
		System.out.println("bb = " + s.getNumberOfBasicBlockNodes());
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 2);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);
	}

	@Test
	public final void testSingleStep_5() {
		ICFGDecisionNode A = new CFGDecisionNode(this.mCFG, null, null, null);
		this.mCFG.addDecisionNode(A);
		ICFEdge e1 = new CFEdge(null, this.mCFGStart, A);	this.mCFG.addEdge(e1);
		ICFGBasicBlockNode B = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(B);
		ICFGBasicBlockNode C = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(C);
		ICFEdge e2 = new CFEdge(null, A, B); this.mCFG.addEdge(e2);
		ICFEdge e3 = new CFEdge(null, A, C); this.mCFG.addEdge(e3);
		A.setThenEdge(e2);
		A.setElseEdge(e3);
		ICFEdge e4 = new CFEdge(null, B, this.mCFGEnd); this.mCFG.addEdge(e4);
		ICFEdge e5 = new CFEdge(null, C, this.mCFGEnd); this.mCFG.addEdge(e5);
		
		//step 1
		try {
			this.mSEE.singleStep(e1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 1);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);		
		
		//step 1
		try {
			Set<ICFEdge> set = new HashSet<ICFEdge>();
			set.add(e2);
			set.add(e3);			
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		s = this.mSEE.getSET();
		System.out.println("bb = " + s.getNumberOfBasicBlockNodes());
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 3);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);
	}
}

