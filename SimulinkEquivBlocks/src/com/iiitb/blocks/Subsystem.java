package com.iiitb.blocks;

import java.util.ArrayList;
import java.util.Set;

import com.iiitb.cfg.Accfg;

import expression.Expression;
import expression.SubBlockExpression;
import expression.Variable;


/** Class for Subsystem block. 
 * This is a special kind of block whose Accfg instance is derived from Accfg instances of enclosed subsystems 
 *
 */
public class Subsystem extends Block{

	
	@Override
	public Expression expression() {
		// TODO Auto-generated method stub
		Variable retVar=null;
		try {
			return(new SubBlockExpression(((Variable)getAccfg().getOutput()).getName(),this,getOutput()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null
		return retVar;
		
		//return getName() + "=" + getAccfg().getOutput();
	}

	@Override
	// Purposefully not overridden
	public ArrayList<Expression> getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// Purposefully not overridden
	public void setInput(String input,String port) {
		// TODO Auto-generated method stub

	}

	public Subsystem(Accfg accfg, String name) {
		super(name);

		/*
		 * For eg : If output of inner subsystem is 'sum' and name of the
		 * current subsystem is 'subsystem' then add 'subsystem = sum' and then
		 * proceed with evaluation of current subsystem
		 */
		
		// Set to access in expression() method
		setAccfg(accfg);
		
		Accfg accfgLocal = new Accfg();
		accfgLocal.setFp(accfg.getFp());
		accfgLocal.getFp().add(expression());
		
		/* There wont be an input to subsystem block. 
		If input is to be set , fetch from passed Accfg instance*/
		accfgLocal.setInput(new ArrayList<Expression>());

		// Set output as block name
		accfgLocal.setOutput(getOutput());
		
		setAccfg(accfgLocal);
	}

	
	
	// IProgram methods to override - Intentionally left blank
	
	@Override
	public Variable addVariable(Variable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Variable> getVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasVariable(Variable arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
