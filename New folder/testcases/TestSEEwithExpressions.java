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
import set.SETtoStringVisitor;
import statement.IStatement;
import statement.Statement;
import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;
import expression.AddExpression;
import expression.ConcreteConstant;
import expression.GreaterThanExpression;
import expression.IArithmeticExpression;
import expression.Input;
import expression.Type;
import expression.Variable;

public class TestSEEwithExpressions {

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
	public final void testSingleStep_2() {
		Variable v1 = null;
		try {
			Type type = Type.getInstance();
			if(type == null) {
				Exception e = new Exception("Type is null!");
				throw e;
			}
			else {
				System.out.println("type exists.");
			}
			v1 = new Variable("v1", this.mCFG);

			this.mCFG.addVariable(v1);
			Input i1 = new Input(this.mCFG);

			ICFGBasicBlockNode D = new CFGBasicBlockNode(this.mCFG);
			this.mCFG.addBasicBlockNode(D);
			CFEdge e6 = new CFEdge(this.mCFG, this.mCFGStart, D);
			Statement s1 = new Statement(this.mCFG, v1, i1);
			D.addStatement(s1);

			ConcreteConstant exp1 = new ConcreteConstant(15, this.mCFG);
			GreaterThanExpression exp2 = new GreaterThanExpression(this.mCFG, v1, exp1);
			ICFGDecisionNode A = new CFGDecisionNode(this.mCFG, exp2, null, null);
			this.mCFG.addDecisionNode(A);
			ICFEdge e1 = new CFEdge(null, D, A);
			this.mCFG.addEdge(e1);

			ICFGBasicBlockNode B = new CFGBasicBlockNode(this.mCFG);
			IArithmeticExpression exp3 = new AddExpression(this.mCFG, new ConcreteConstant(10, this.mCFG), new ConcreteConstant(20, this.mCFG));
			B.addStatement(new Statement( this.mCFG, v1, exp3));
			this.mCFG.addBasicBlockNode(B);

			ICFGBasicBlockNode C = new CFGBasicBlockNode(this.mCFG);
			IArithmeticExpression exp4 = new AddExpression(this.mCFG, new ConcreteConstant(1, this.mCFG), new ConcreteConstant(2, this.mCFG));
			B.addStatement(new Statement( this.mCFG, v1, exp4));
			this.mCFG.addBasicBlockNode(C);

			ICFEdge e2 = new CFEdge(null, A, B); this.mCFG.addEdge(e2);
			ICFEdge e3 = new CFEdge(null, A, C); this.mCFG.addEdge(e3);
			A.addOutgoingEdge(e2);
			A.addOutgoingEdge(e3);
			ICFEdge e4 = new CFEdge(null, B, this.mCFGEnd);
			this.mCFG.addEdge(e4);
			ICFEdge e5 = new CFEdge(null, C, this.mCFGEnd);
			this.mCFG.addEdge(e5);

			System.out.println("start.id = " + this.mCFGStart.getId());
			System.out.println("D.id = " + D.getId());
			System.out.println("A.id = " + A.getId());
			System.out.println("B.id = " + B.getId());
			System.out.println("C.id = " + C.getId());
			System.out.println("end.id = " + this.mCFGEnd.getId());

			System.out.println("e1.id = " + e1.getId());
			System.out.println("e2.id = " + e2.getId());
			System.out.println("e3.id = " + e3.getId());
			System.out.println("e4.id = " + e4.getId());
			System.out.println("e5.id = " + e5.getId());

			System.out.println("A.condition = " + A.getCondition().toString());
			for(IStatement s : B.getStatements()) {
				System.out.println("B.statement = " + s.toString());
			}
			for(IStatement s : C.getStatements()) {
				System.out.println("C.statement = " + s.toString());
			}

			Set<ICFEdge> set = new HashSet<ICFEdge>();
			set.add(e6);
			try {
				this.mSEE.singleStep(set);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			SET s = this.mSEE.getSET();

			Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 2);
			Assert.assertEquals(s.getNumberOfDecisionNodes(), 0);

			SETtoStringVisitor sv = new SETtoStringVisitor(s);
			sv.visit();
			System.out.println(sv.getOutputString());
		} catch (Exception e7) {
			// TODO Auto-generated catch block
			e7.printStackTrace();
		}
	}

	@Test
	public final void testSingleStep_3() {
		Variable v1 = null;
		try {
			v1 = new Variable("v1", this.mCFG);
		this.mCFG.addVariable(v1);
		Input i1 = new Input(this.mCFG);
		
		ICFGBasicBlockNode D = new CFGBasicBlockNode(this.mCFG);
		this.mCFG.addBasicBlockNode(D);
		CFEdge e6 = new CFEdge(this.mCFG, this.mCFGStart, D);
		Statement s1 = new Statement(this.mCFG, v1, i1);
		D.addStatement(s1);
		
		ConcreteConstant exp1 = new ConcreteConstant(15, this.mCFG);
		GreaterThanExpression exp2 = new GreaterThanExpression(this.mCFG, v1, exp1);
		ICFGDecisionNode A = new CFGDecisionNode(this.mCFG, exp2, null, null);
		this.mCFG.addDecisionNode(A);
		ICFEdge e1 = new CFEdge(this.mCFG, D, A);

		ICFGBasicBlockNode B = new CFGBasicBlockNode(this.mCFG);
		IArithmeticExpression exp3 = new AddExpression(this.mCFG, new ConcreteConstant(10, this.mCFG), new ConcreteConstant(20, this.mCFG));
		B.addStatement(new Statement( this.mCFG, v1, exp3));
		this.mCFG.addBasicBlockNode(B);
		
		ICFGBasicBlockNode C = new CFGBasicBlockNode(this.mCFG);
		IArithmeticExpression exp4 = new AddExpression(this.mCFG, new ConcreteConstant(1, this.mCFG), new ConcreteConstant(2, this.mCFG));
		C.addStatement(new Statement( this.mCFG, v1, exp4));
		this.mCFG.addBasicBlockNode(C);
		
		ICFEdge e2 = new CFEdge(null, A, B); this.mCFG.addEdge(e2);
		ICFEdge e3 = new CFEdge(null, A, C); this.mCFG.addEdge(e3);
		A.setThenEdge(e2);
		A.setElseEdge(e3);
		ICFEdge e4 = new CFEdge(null, B, this.mCFGEnd);
		this.mCFG.addEdge(e4);
		ICFEdge e5 = new CFEdge(null, C, this.mCFGEnd);
		this.mCFG.addEdge(e5);
		
		System.out.println("start.id = " + this.mCFGStart.getId());
		System.out.println("D.id = " + D.getId());
		System.out.println("A.id = " + A.getId());
		System.out.println("B.id = " + B.getId());
		System.out.println("C.id = " + C.getId());
		System.out.println("end.id = " + this.mCFGEnd.getId());
		
		System.out.println("e6.id = " + e6.getId());
		System.out.println("e1.id = " + e1.getId());
		System.out.println("e2.id = " + e2.getId());
		System.out.println("e3.id = " + e3.getId());
		System.out.println("e4.id = " + e4.getId());
		System.out.println("e5.id = " + e5.getId());

		for(IStatement s : D.getStatements()) {
			System.out.println("D.statement = " + s.toString());
		}

		System.out.println("A.condition = " + A.getCondition().toString());
		for(IStatement s : B.getStatements()) {
			System.out.println("B.statement = " + s.toString());
		}
		for(IStatement s : C.getStatements()) {
			System.out.println("C.statement = " + s.toString());
		}
		
		Set<ICFEdge> set = new HashSet<ICFEdge>();
		set.add(e6);
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		SET s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 2);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 0);

		set = new HashSet<ICFEdge>();
		set.add(e1);
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		s = this.mSEE.getSET();
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 2);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);

		set = new HashSet<ICFEdge>();
		set.add(e2);
		set.add(e3);
		try {
			this.mSEE.singleStep(set);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		s = this.mSEE.getSET();
		SETtoStringVisitor sv = new SETtoStringVisitor(s);
		sv.visit();
		System.out.println(sv.getOutputString());

		System.out.println("bbnodes = " + s.getNumberOfBasicBlockNodes());
		Assert.assertEquals(s.getNumberOfBasicBlockNodes(), 4);
		Assert.assertEquals(s.getNumberOfDecisionNodes(), 1);		
		} catch (Exception e7) {
			// TODO Auto-generated catch block
			e7.printStackTrace();
		}
	}
}
