package testcases;

import graph.IEdge;
import graph.IGraph;
import graph.INode;
import graph.IPath;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import mygraph.Edge;
import mygraph.Graph;
import mygraph.Node;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tester.FindCFPathAlgorithm;

public class TestFindCFPathAlgorithm {

	FindCFPathAlgorithm mAlgo;
	IGraph mGraph;
	INode s;
	INode t;
	Set<IEdge> targets;
	@Before
	public void setUp() throws Exception {
		s = new Node(null);
		t = new Node(null);
		this.mGraph = new Graph(s);
		this.mGraph.addNode(t);
		targets = new HashSet<IEdge>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFindCFPathAlgorithm1() throws Exception {
		// basic graph with 2 nodes and one edge e. e is included in target. 
		IEdge e = new Edge(this.mGraph, s, t);
		targets.add(e);
		this.mAlgo = new FindCFPathAlgorithm(this.mGraph, targets, t);
		
		IPath p = this.mAlgo.findCFPath();
		List<IEdge> l = p.getPath();
		Assert.assertEquals(l.get(0), e);
		Assert.assertEquals(l.size(), 1);
	}

	@Test
	public final void testFindCFPathAlgorithm2() throws Exception {
		// basic graph with 2 nodes and one edge e. e isn't included in target. 
		this.mAlgo = new FindCFPathAlgorithm(this.mGraph, targets, t);
		
		IPath p = this.mAlgo.findCFPath();
		List<IEdge> l = p.getPath();
		Assert.assertEquals(l.size(), 0);
	}

	@Test
	public final void testFindCFPathAlgorithm3() throws Exception {
		// Motivating example in SymTest paper: 
		// basic graph with 2 nodes and one edge e. e is included in target. 
		INode n = new Node(this.mGraph);
		
		IEdge e1 = new Edge(this.mGraph, s, n);
		IEdge e2 = new Edge(this.mGraph, n, t);
		IEdge e3 = new Edge(this.mGraph, s, n);
		IEdge e4 = new Edge(this.mGraph, n, t);
		
		System.out.println("e1 name = " + e1.getId());
		System.out.println("e2 name = " + e2.getId());
		System.out.println("e3 name = " + e3.getId());
		System.out.println("e4 name = " + e4.getId());
		
		targets.add(e1);
		targets.add(e2);
		
		this.mAlgo = new FindCFPathAlgorithm(this.mGraph, targets, t);
		
		IPath p = this.mAlgo.findCFPath();
		List<IEdge> l = p.getPath();
		System.out.println("size = " + l.size());
		Assert.assertEquals(l.size(), 2);
		Assert.assertEquals(l.get(0), e1);
		Assert.assertEquals(l.get(1), e2);
	}


	@Test
	public final void testFindCFPathAlgorithm4() throws Exception {
		// Motivating example in SymTest paper: Total edge coverage. 
		// basic graph with 2 nodes and one edge e. e is included in target. 
		INode n = new Node(this.mGraph);
		
		IEdge e1 = new Edge(this.mGraph, s, n);
		IEdge e2 = new Edge(this.mGraph, n, t);
		IEdge e3 = new Edge(this.mGraph, s, n);
		IEdge e4 = new Edge(this.mGraph, n, t);
		
		System.out.println("e1 name = " + e1.getId());
		System.out.println("e2 name = " + e2.getId());
		System.out.println("e3 name = " + e3.getId());
		System.out.println("e4 name = " + e4.getId());
		
		targets.add(e1);
		targets.add(e2);
		targets.add(e3);
		targets.add(e4);
		
		this.mAlgo = new FindCFPathAlgorithm(this.mGraph, targets, t);
		
		IPath p = this.mAlgo.findCFPath();
		List<IEdge> l = p.getPath();
		System.out.println("size = " + l.size());
		Assert.assertEquals(l.size(), 4);
		Assert.assertEquals(l.get(0), e1);
		Assert.assertEquals(l.get(1), e2);
		Assert.assertEquals(l.get(2), e3);
		Assert.assertEquals(l.get(3), e4);
	}
}
