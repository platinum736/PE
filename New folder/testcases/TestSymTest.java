package testcases;

import java.util.HashSet;
import java.util.Set;

import mycfg.CFEdge;
import mycfg.CFG;
import mycfg.CFGBasicBlockNode;
import mycfg.CFGDecisionNode;

import org.junit.Before;
import org.junit.Test;

import statement.IStatement;
import statement.Statement;
import tester.SymTest;
import cfg.ICFEdge;
import cfg.ICFG;
import cfg.ICFGBasicBlockNode;
import cfg.ICFGDecisionNode;
import expression.AddExpression;
import expression.AndExpression;
import expression.ConcreteConstant;
import expression.EqualsExpression;
import expression.False;
import expression.GreaterThanExpression;
import expression.IArithmeticExpression;
import expression.IExpression;
import expression.Input;
import expression.NotExpression;
import expression.OrExpression;
import expression.True;
import expression.Type;
import expression.Variable;

public class TestSymTest {
	private ICFG mCFG;
	private ICFGBasicBlockNode mCFGStart, mCFGEnd;
	
	@Before
	public void setUp() throws Exception {
		try {
			this.mCFGStart = new CFGBasicBlockNode("S", null);
			this.mCFGEnd = new CFGBasicBlockNode("T", null);
			this.mCFG = new CFG(this.mCFGStart, this.mCFGEnd);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public final void testGenerateTestSequence() {
		Variable v1 = null;
		try {
			v1 = new Variable("v1", this.mCFG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		this.mCFG.addVariable(v1);
		Input i1;
		try {
			i1 = new Input(this.mCFG);

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
			ICFEdge e7 = new CFEdge(null, this.mCFGEnd, this.mCFGStart);
			this.mCFG.addEdge(e7);

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
			System.out.println("e7.id = " + e7.getId());

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

			Set<ICFEdge> targets = new HashSet<ICFEdge>();
			targets.add(e2);
			targets.add(e3);
			SymTest tester = new SymTest(this.mCFG, targets);
			tester.generateTestSequence();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public final void testGMCS1() {
		//input variables
		Variable i1 = null;
		Variable i2 = null;
		Variable i3 = null;
		Variable i4 = null;
		
		try {
			i1 = new Variable("i1", this.mCFG);
			i2 = new Variable("i2", this.mCFG);
			i3 = new Variable("i3", this.mCFG);
			i4 = new Variable("i4", this.mCFG);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		//constants
		try {
			ConcreteConstant C1 = new ConcreteConstant(1, this.mCFG);
			ConcreteConstant C2 = new ConcreteConstant(2, this.mCFG);
			ConcreteConstant C3 = new ConcreteConstant(3, this.mCFG);
			ConcreteConstant C4 = new ConcreteConstant(4, this.mCFG);
			ConcreteConstant C5 = new ConcreteConstant(5, this.mCFG);
			IExpression C6 = new True(this.mCFG);
			ConcreteConstant C7 = new ConcreteConstant(7, this.mCFG);
			ConcreteConstant C8 = new ConcreteConstant(8, this.mCFG);

			//internal variables
			Variable v1 = null;
			Variable v2 = null;
			Variable v3 = null;
			Variable v4 = null;
			Variable v5 = null;
			Variable v6 = null;
			Variable v7 = null;
			Variable v9 = null;
			Variable v10 = null;
			Variable v14 = null;
		
			v1 = new Variable("v1", Type.BOOLEAN, this.mCFG);
			v2 = new Variable("v2",  Type.BOOLEAN, this.mCFG);
			v3 = new Variable("v3",  Type.BOOLEAN, this.mCFG);
			v4 = new Variable("v4",  Type.BOOLEAN, this.mCFG);
			v5 = new Variable("v5",  Type.BOOLEAN, this.mCFG);
			v6 = new Variable("v6",  Type.BOOLEAN, this.mCFG);
			v7 = new Variable("v7",  Type.BOOLEAN, this.mCFG);
			v9 = new Variable("v9",  Type.BOOLEAN, this.mCFG);
			v10 = new Variable("v10",  Type.BOOLEAN, this.mCFG);
			v14 = new Variable("v14",  Type.BOOLEAN, this.mCFG);
		
			ICFGBasicBlockNode InputBlock = new CFGBasicBlockNode("IB", this.mCFG);
			this.mCFG.addBasicBlockNode(InputBlock);

			Input I1;
			I1 = new Input(this.mCFG);

			Statement inputStatement1 = new Statement(this.mCFG, i1, I1);
			InputBlock.addStatement(inputStatement1);

			Input I2 = new Input(this.mCFG);
			Statement inputStatement2 = new Statement(this.mCFG, i2, I2);
			InputBlock.addStatement(inputStatement2);

			Input I3 = new Input(this.mCFG);
			Statement inputStatement3 = new Statement(this.mCFG, i3, I3);
			InputBlock.addStatement(inputStatement3);

			Input I4 = new Input(this.mCFG);
			Statement inputStatement4 = new Statement(this.mCFG, i4, I4);
			InputBlock.addStatement(inputStatement4);

			//A
			//ADB
			EqualsExpression ADBC = new EqualsExpression(this.mCFG, i1, C1);
			CFGDecisionNode ADB = new CFGDecisionNode("ADB", this.mCFG, ADBC, null, null);
			//ABB1
			Statement ABB1S = new Statement(this.mCFG, v1, new True(this.mCFG));
			CFGBasicBlockNode ABB1 = new CFGBasicBlockNode("ABB1", this.mCFG);
			ABB1.addStatement(ABB1S);

			//ABB2
			Statement ABB2S = new Statement(this.mCFG, v1, new False(this.mCFG));
			CFGBasicBlockNode ABB2 = new CFGBasicBlockNode("ABB2", this.mCFG);
			ABB2.addStatement(ABB2S);
			//B
			//BDB
			EqualsExpression BDBC = new EqualsExpression(this.mCFG, i3, C2);
			CFGDecisionNode BDB = new CFGDecisionNode("BDB", this.mCFG, BDBC, null, null);
			//BBB1
			Statement BBB1S = new Statement(this.mCFG, v3, new True(this.mCFG));
			CFGBasicBlockNode BBB1 = new CFGBasicBlockNode("BBB1", this.mCFG);
			BBB1.addStatement(BBB1S);
			//BBB2
			Statement BBB2S = new Statement(this.mCFG, v3, new False(this.mCFG));
			CFGBasicBlockNode BBB2 = new CFGBasicBlockNode("BBB2", this.mCFG);
			BBB2.addStatement(BBB2S);
			//C
			//CDB
			EqualsExpression CDBC = new EqualsExpression(this.mCFG, i2, C3);
			CFGDecisionNode CDB = new CFGDecisionNode("CDB", this.mCFG, CDBC, null, null);

			//CBB1
			Statement CBB1S = new Statement(this.mCFG, v4, new True(this.mCFG));
			CFGBasicBlockNode CBB1 = new CFGBasicBlockNode("CBB1", this.mCFG);
			CBB1.addStatement(CBB1S);
			//CBB2
			Statement CBB2S = new Statement(this.mCFG, v4, new False(this.mCFG));
			CFGBasicBlockNode CBB2 = new CFGBasicBlockNode("CBB2", this.mCFG);
			CBB2.addStatement(CBB2S);
			//D
			EqualsExpression DDBC = new EqualsExpression(this.mCFG, i2, C4);
			CFGDecisionNode DDB = new CFGDecisionNode("DDB", this.mCFG, DDBC, null, null);

			//DBB1
			Statement DBB1S = new Statement(this.mCFG, v5, new True(this.mCFG));
			CFGBasicBlockNode DBB1 = new CFGBasicBlockNode("DBB1", this.mCFG);
			DBB1.addStatement(DBB1S);
			//DBB2
			Statement DBB2S = new Statement(this.mCFG, v5, new False(this.mCFG));
			CFGBasicBlockNode DBB2 = new CFGBasicBlockNode("DBB2", this.mCFG);
			DBB2.addStatement(DBB2S);

			//E
			//EDB
			NotExpression EDBC = new NotExpression(this.mCFG, new EqualsExpression(this.mCFG, i3, i4));
			CFGDecisionNode EDB = new CFGDecisionNode("EDB", this.mCFG, EDBC, null, null);
			//EBB1
			Statement EBB1S = new Statement(this.mCFG, v2, new True(this.mCFG));
			CFGBasicBlockNode EBB1 = new CFGBasicBlockNode("EBB1", this.mCFG);
			EBB1.addStatement(EBB1S);
			//EBB2
			Statement EBB2S = new Statement(this.mCFG, v2, new False(this.mCFG));
			CFGBasicBlockNode EBB2 = new CFGBasicBlockNode("EBB2", this.mCFG);
			EBB2.addStatement(EBB2S);

			//BB
			Statement BBS1 = new Statement(this.mCFG, v6, new OrExpression(this.mCFG, v4, v5));
			Statement BBS2 = new Statement(this.mCFG, v7, new OrExpression(this.mCFG, v2, new OrExpression(this.mCFG, v3, v6)));
			Statement BBS3 = new Statement(this.mCFG, v9, new OrExpression(this.mCFG, v6, C6));
			Statement BBS4 = new Statement(this.mCFG, v10, new AndExpression(this.mCFG, v1, v7));
			
			CFGBasicBlockNode BB = new CFGBasicBlockNode("BB", this.mCFG);
			BB.addStatement(BBS1);
			BB.addStatement(BBS2);
			BB.addStatement(BBS3);
			BB.addStatement(BBS4);
			//F
			//FDB
			EqualsExpression FDBC = new EqualsExpression(this.mCFG, v10, new True(this.mCFG));
			CFGDecisionNode FDB = new CFGDecisionNode("FDB", this.mCFG, FDBC, null, null);
			//FBB1
			Statement FBB1S = new Statement(this.mCFG, v14, C5);
			CFGBasicBlockNode FBB1 = new CFGBasicBlockNode("FBB1", this.mCFG);
			FBB1.addStatement(FBB1S);		
			//G
			EqualsExpression GDBC = new EqualsExpression(this.mCFG, v9, new True(this.mCFG));
			CFGDecisionNode GDB = new CFGDecisionNode("GDB", this.mCFG, GDBC, null, null);

			//GBB1
			Statement GBB1S = new Statement(this.mCFG, v14, C7);
			CFGBasicBlockNode GBB1 = new CFGBasicBlockNode("GBB1", this.mCFG);
			GBB1.addStatement(GBB1S);
			//GBB2
			Statement GBB2S = new Statement(this.mCFG, v14, C8);
			CFGBasicBlockNode GBB2 = new CFGBasicBlockNode("GBB2", this.mCFG);
			GBB2.addStatement(GBB2S);

			//Edges
			CFEdge e1 = new CFEdge("e1", this.mCFG, this.mCFGStart, InputBlock);
			CFEdge e2 = new CFEdge("e2", this.mCFG, InputBlock, ADB);
			CFEdge e3 = new CFEdge("e3", this.mCFG, ADB, ABB1);
			CFEdge e4 = new CFEdge("e4", this.mCFG, ADB, ABB2);
			CFEdge e5 = new CFEdge("e5", this.mCFG, ABB1, BDB);
			CFEdge e6 = new CFEdge("e6", this.mCFG, ABB2, BDB);
			CFEdge e7 = new CFEdge("e7", this.mCFG, BDB, BBB1);
			CFEdge e8 = new CFEdge("e8", this.mCFG, BDB, BBB2);
			CFEdge e9 = new CFEdge("e9", this.mCFG, BBB1, CDB);
			CFEdge e10 = new CFEdge("e10", this.mCFG, BBB2, CDB);
			CFEdge e11 = new CFEdge("e11", this.mCFG, CDB, CBB1);
			CFEdge e12 = new CFEdge("e12", this.mCFG, CDB, CBB2);
			CFEdge e13 = new CFEdge("e13", this.mCFG, CBB1, DDB);
			CFEdge e14 = new CFEdge("e14", this.mCFG, CBB2, DDB);
			CFEdge e15 = new CFEdge("e15", this.mCFG, DDB, DBB1);
			CFEdge e16 = new CFEdge("e16", this.mCFG, DDB, DBB2);
			CFEdge e17 = new CFEdge("e17", this.mCFG, DBB1, EDB);
			CFEdge e18 = new CFEdge("e18", this.mCFG, DBB2, EDB);
			CFEdge e19 = new CFEdge("e19", this.mCFG, EDB, EBB1);
			CFEdge e20 = new CFEdge("e20", this.mCFG, EDB, EBB2);
			CFEdge e21 = new CFEdge("e21", this.mCFG, EBB1, BB);
			CFEdge e22 = new CFEdge("e22", this.mCFG, EBB2, BB);
			CFEdge e23 = new CFEdge("e23", this.mCFG, BB, FDB);
			CFEdge e24 = new CFEdge("e24", this.mCFG, FDB, FBB1);
			CFEdge e25 = new CFEdge("e25", this.mCFG, FDB, GDB);
			CFEdge e26 = new CFEdge("e26", this.mCFG, GDB, GBB1);
			CFEdge e27 = new CFEdge("e27", this.mCFG, GDB, GBB2);
			CFEdge e28 = new CFEdge("e28", this.mCFG, FBB1, this.mCFGEnd);
			CFEdge e29 = new CFEdge("e29", this.mCFG, GBB1, this.mCFGEnd);
			CFEdge e30 = new CFEdge("e30", this.mCFG, GBB2, this.mCFGEnd);
			CFEdge e31 = new CFEdge("e31", this.mCFG, this.mCFGEnd, this.mCFGStart);

			System.out.println(this.mCFG.toString());

			for(ICFEdge edge : this.mCFG.getEdgeSet()) {
				System.out.println("CFG Edge " + edge.getId());
			}
			Set<ICFEdge> targets = new HashSet<ICFEdge>();
			targets.add(e1);
			targets.add(e2);
			targets.add(e3);
			targets.add(e4);
			targets.add(e5);
			targets.add(e6);
			targets.add(e7);
			targets.add(e8);
			targets.add(e9);
			targets.add(e10);
			targets.add(e11);
			targets.add(e12);
			targets.add(e13);
			targets.add(e14);
			targets.add(e15);
			targets.add(e16);
			targets.add(e17);
			targets.add(e18);
			targets.add(e19);
			targets.add(e20);
			targets.add(e21);
			targets.add(e22);
			targets.add(e23);
			targets.add(e24);
			targets.add(e25);
			targets.add(e26);
			targets.add(e27);
			targets.add(e28);
			targets.add(e29);
			targets.add(e30);
//			targets.add(e31);
			System.out.println("No. of targets = " + targets.size());
			SymTest tester = new SymTest(this.mCFG, targets);
			tester.generateTestSequence();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	@Test
	public final void testGMCS2() {
		//input variables
		Variable i1 = null;
		Variable i2 = null;
		Variable i3 = null;
		Variable i4 = null;
		
		try {
			i1 = new Variable("i1", this.mCFG);
			i2 = new Variable("i2", this.mCFG);
			i3 = new Variable("i3", this.mCFG);
			i4 = new Variable("i4", this.mCFG);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		//constants
		try {
			ConcreteConstant C1 = new ConcreteConstant(1, this.mCFG);
			ConcreteConstant C2 = new ConcreteConstant(2, this.mCFG);
			ConcreteConstant C3 = new ConcreteConstant(3, this.mCFG);
			ConcreteConstant C4 = new ConcreteConstant(4, this.mCFG);
			IExpression C5 = new True(this.mCFG);
			IExpression C6 = new False(this.mCFG);
			ConcreteConstant C7 = new ConcreteConstant(7, this.mCFG);
			ConcreteConstant C8 = new ConcreteConstant(8, this.mCFG);

			//internal variables
			Variable v1 = null;
			Variable v2 = null;
			Variable v3 = null;
			Variable v4 = null;
			Variable v5 = null;
			Variable v6 = null;
			Variable v7 = null;
			Variable v9 = null;
			Variable v10 = null;
			Variable v14 = null;
		
			v1 = new Variable("v1", Type.BOOLEAN, this.mCFG);
			v2 = new Variable("v2",  Type.BOOLEAN, this.mCFG);
			v3 = new Variable("v3",  Type.BOOLEAN, this.mCFG);
			v4 = new Variable("v4",  Type.BOOLEAN, this.mCFG);
			v5 = new Variable("v5",  Type.BOOLEAN, this.mCFG);
			v6 = new Variable("v6",  Type.BOOLEAN, this.mCFG);
			v7 = new Variable("v7",  Type.BOOLEAN, this.mCFG);
			v9 = new Variable("v9",  Type.BOOLEAN, this.mCFG);
			v10 = new Variable("v10",  Type.BOOLEAN, this.mCFG);
			v14 = new Variable("v14",  Type.BOOLEAN, this.mCFG);

			
			
			//InitBlock
			Statement initS = new Statement(this.mCFG, v14, new True(this.mCFG));
			ICFGBasicBlockNode InitBlock = new CFGBasicBlockNode("InitBlock", this.mCFG);
			this.mCFG.addBasicBlockNode(InitBlock);
			InitBlock.addStatement(initS);
			
			// InputBlock
			ICFGBasicBlockNode InputBlock = new CFGBasicBlockNode("IB", this.mCFG);
			this.mCFG.addBasicBlockNode(InputBlock);

			Input I1;
			I1 = new Input(this.mCFG);

			Statement inputStatement1 = new Statement(this.mCFG, i1, I1);
			InputBlock.addStatement(inputStatement1);

			Input I2 = new Input(this.mCFG);
			Statement inputStatement2 = new Statement(this.mCFG, i2, I2);
			InputBlock.addStatement(inputStatement2);

			Input I3 = new Input(this.mCFG);
			Statement inputStatement3 = new Statement(this.mCFG, i3, I3);
			InputBlock.addStatement(inputStatement3);

			Input I4 = new Input(this.mCFG);
			Statement inputStatement4 = new Statement(this.mCFG, i4, I4);
			InputBlock.addStatement(inputStatement4);

			//A
			//ADB
			EqualsExpression ADBC = new EqualsExpression(this.mCFG, i1, C1);
			CFGDecisionNode ADB = new CFGDecisionNode("ADB", this.mCFG, ADBC, null, null);
			//ABB1
			Statement ABB1S = new Statement(this.mCFG, v1, new True(this.mCFG));
			CFGBasicBlockNode ABB1 = new CFGBasicBlockNode("ABB1", this.mCFG);
			ABB1.addStatement(ABB1S);

			//ABB2
			Statement ABB2S = new Statement(this.mCFG, v1, new False(this.mCFG));
			CFGBasicBlockNode ABB2 = new CFGBasicBlockNode("ABB2", this.mCFG);
			ABB2.addStatement(ABB2S);
			//B
			//BDB
			EqualsExpression BDBC = new EqualsExpression(this.mCFG, i3, C2);
			CFGDecisionNode BDB = new CFGDecisionNode("BDB", this.mCFG, BDBC, null, null);
			//BBB1
			Statement BBB1S = new Statement(this.mCFG, v3, new False(this.mCFG));
			CFGBasicBlockNode BBB1 = new CFGBasicBlockNode("BBB1", this.mCFG);
			BBB1.addStatement(BBB1S);
			//BBB2
			Statement BBB2S = new Statement(this.mCFG, v3, new True(this.mCFG));
			CFGBasicBlockNode BBB2 = new CFGBasicBlockNode("BBB2", this.mCFG);
			BBB2.addStatement(BBB2S);
			//C
			//CDB
			EqualsExpression CDBC = new EqualsExpression(this.mCFG, i2, C3);
			CFGDecisionNode CDB = new CFGDecisionNode("CDB", this.mCFG, CDBC, null, null);

			//CBB1
			Statement CBB1S = new Statement(this.mCFG, v4, new True(this.mCFG));
			CFGBasicBlockNode CBB1 = new CFGBasicBlockNode("CBB1", this.mCFG);
			CBB1.addStatement(CBB1S);
			//CBB2
			Statement CBB2S = new Statement(this.mCFG, v4, new False(this.mCFG));
			CFGBasicBlockNode CBB2 = new CFGBasicBlockNode("CBB2", this.mCFG);
			CBB2.addStatement(CBB2S);
			//D
			IExpression DDBC = new NotExpression(this.mCFG, new EqualsExpression(this.mCFG, i2, C4));
			CFGDecisionNode DDB = new CFGDecisionNode("DDB", this.mCFG, DDBC, null, null);

			//DBB1
			Statement DBB1S = new Statement(this.mCFG, v5, new True(this.mCFG));
			CFGBasicBlockNode DBB1 = new CFGBasicBlockNode("DBB1", this.mCFG);
			DBB1.addStatement(DBB1S);
			//DBB2
			Statement DBB2S = new Statement(this.mCFG, v5, new False(this.mCFG));
			CFGBasicBlockNode DBB2 = new CFGBasicBlockNode("DBB2", this.mCFG);
			DBB2.addStatement(DBB2S);

			//E
			//EDB
			NotExpression EDBC = new NotExpression(this.mCFG, new EqualsExpression(this.mCFG, i3, i4));
			CFGDecisionNode EDB = new CFGDecisionNode("EDB", this.mCFG, EDBC, null, null);
			//EBB1
			Statement EBB1S = new Statement(this.mCFG, v2, new False(this.mCFG));
			CFGBasicBlockNode EBB1 = new CFGBasicBlockNode("EBB1", this.mCFG);
			EBB1.addStatement(EBB1S);
			//EBB2
			Statement EBB2S = new Statement(this.mCFG, v2, new True(this.mCFG));
			CFGBasicBlockNode EBB2 = new CFGBasicBlockNode("EBB2", this.mCFG);
			EBB2.addStatement(EBB2S);

			//BB
			Statement BBS1 = new Statement(this.mCFG, v6, new OrExpression(this.mCFG, v4, v5));
			Statement BBS2 = new Statement(this.mCFG, v7, new OrExpression(this.mCFG, v2, new OrExpression(this.mCFG, v3, v6)));
			Statement BBS3 = new Statement(this.mCFG, v9, new AndExpression(this.mCFG, new AndExpression(this.mCFG, v6, C5), v14));
			Statement BBS4 = new Statement(this.mCFG, v10, new NotExpression(this.mCFG, v1));
			
			CFGBasicBlockNode BB = new CFGBasicBlockNode("BB", this.mCFG);
			BB.addStatement(BBS1);
			BB.addStatement(BBS2);
			BB.addStatement(BBS3);
			BB.addStatement(BBS4);
			//F
			//FDB
			EqualsExpression FDBC = new EqualsExpression(this.mCFG, v10, new True(this.mCFG));
			CFGDecisionNode FDB = new CFGDecisionNode("FDB", this.mCFG, FDBC, null, null);
			//FBB1
			Statement FBB1S = new Statement(this.mCFG, v14, C6);
			CFGBasicBlockNode FBB1 = new CFGBasicBlockNode("FBB1", this.mCFG);
			FBB1.addStatement(FBB1S);		
			//G
			EqualsExpression GDBC = new EqualsExpression(this.mCFG, v9, new True(this.mCFG));
			CFGDecisionNode GDB = new CFGDecisionNode("GDB", this.mCFG, GDBC, null, null);

			//GBB1
			Statement GBB1S = new Statement(this.mCFG, v14, C7);
			CFGBasicBlockNode GBB1 = new CFGBasicBlockNode("GBB1", this.mCFG);
			GBB1.addStatement(GBB1S);
			//GBB2
			Statement GBB2S = new Statement(this.mCFG, v14, C8);
			CFGBasicBlockNode GBB2 = new CFGBasicBlockNode("GBB2", this.mCFG);
			GBB2.addStatement(GBB2S);

			//Edges
			CFEdge e1 = new CFEdge("e1", this.mCFG, this.mCFGStart, InitBlock);
			CFEdge e32 = new CFEdge("e32", this.mCFG, InitBlock, InputBlock);
			CFEdge e2 = new CFEdge("e2", this.mCFG, InputBlock, ADB);
			CFEdge e3 = new CFEdge("e3", this.mCFG, ADB, ABB1);
			CFEdge e4 = new CFEdge("e4", this.mCFG, ADB, ABB2);
			CFEdge e5 = new CFEdge("e5", this.mCFG, ABB1, BDB);
			CFEdge e6 = new CFEdge("e6", this.mCFG, ABB2, BDB);
			CFEdge e7 = new CFEdge("e7", this.mCFG, BDB, BBB1);
			CFEdge e8 = new CFEdge("e8", this.mCFG, BDB, BBB2);
			CFEdge e9 = new CFEdge("e9", this.mCFG, BBB1, CDB);
			CFEdge e10 = new CFEdge("e10", this.mCFG, BBB2, CDB);
			CFEdge e11 = new CFEdge("e11", this.mCFG, CDB, CBB1);
			CFEdge e12 = new CFEdge("e12", this.mCFG, CDB, CBB2);
			CFEdge e13 = new CFEdge("e13", this.mCFG, CBB1, DDB);
			CFEdge e14 = new CFEdge("e14", this.mCFG, CBB2, DDB);
			CFEdge e15 = new CFEdge("e15", this.mCFG, DDB, DBB1);
			CFEdge e16 = new CFEdge("e16", this.mCFG, DDB, DBB2);
			CFEdge e17 = new CFEdge("e17", this.mCFG, DBB1, EDB);
			CFEdge e18 = new CFEdge("e18", this.mCFG, DBB2, EDB);
			CFEdge e19 = new CFEdge("e19", this.mCFG, EDB, EBB1);
			CFEdge e20 = new CFEdge("e20", this.mCFG, EDB, EBB2);
			CFEdge e21 = new CFEdge("e21", this.mCFG, EBB1, BB);
			CFEdge e22 = new CFEdge("e22", this.mCFG, EBB2, BB);
			CFEdge e23 = new CFEdge("e23", this.mCFG, BB, FDB);
			CFEdge e24 = new CFEdge("e24", this.mCFG, FDB, FBB1);
			CFEdge e25 = new CFEdge("e25", this.mCFG, FDB, GDB);
			CFEdge e26 = new CFEdge("e26", this.mCFG, GDB, GBB1);
			CFEdge e27 = new CFEdge("e27", this.mCFG, GDB, GBB2);
			CFEdge e28 = new CFEdge("e28", this.mCFG, FBB1, this.mCFGEnd);
			CFEdge e29 = new CFEdge("e29", this.mCFG, GBB1, this.mCFGEnd);
			CFEdge e30 = new CFEdge("e30", this.mCFG, GBB2, this.mCFGEnd);
			CFEdge e31 = new CFEdge("e31", this.mCFG, this.mCFGEnd, InputBlock);

			System.out.println(this.mCFG.toString());

			for(ICFEdge edge : this.mCFG.getEdgeSet()) {
				System.out.println("CFG Edge " + edge.getId());
			}
			Set<ICFEdge> targets = new HashSet<ICFEdge>();
			targets.add(e1);
			targets.add(e2);
			targets.add(e3);
			targets.add(e4);
			targets.add(e5);
			targets.add(e6);
			targets.add(e7);
			targets.add(e8);
			targets.add(e9);
			targets.add(e10);
			targets.add(e11);
			targets.add(e12);
			targets.add(e13);
			targets.add(e14);
			targets.add(e15);
			targets.add(e16);
			targets.add(e17);
			targets.add(e18);
			targets.add(e19);
			targets.add(e20);
			targets.add(e21);
			targets.add(e22);
			targets.add(e23);
			targets.add(e24);
			targets.add(e25);
			targets.add(e26);
			targets.add(e27);
			targets.add(e28);
			targets.add(e29);
			targets.add(e30);
			targets.add(e32);
			System.out.println("No. of targets = " + targets.size());
			SymTest tester = new SymTest(this.mCFG, targets);
			tester.generateTestSequence();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
