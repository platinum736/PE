package com.iiitb.helper;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Delay;
import com.iiitb.inter.IHelper;




public class DelayHelper implements IHelper {

	@Override
	public void setAttr(NodeList attributes, int iter, Block block,String attrToFetch) {
		// TODO Auto-generated method stub
		System.out.println("testing "+ "Entered for delay");
		System.out.println("testing "+attributes.item(iter).getTextContent());
		((Delay)block).setInitialValue(Integer.parseInt(attributes.item(iter).getTextContent()));
		
		
		
		
	}

}