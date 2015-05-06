package com.iiitb.utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Delay;
import com.iiitb.constant.Constants;

import expression.Expression;
import expression.Variable;

public class FetchInputFromLineTest {
	public static Map<String, LinkedList<String>> adjacencyList = new HashMap<String, LinkedList<String>>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public Block parseLineTest(ArrayList<Block> blockList,
			NodeList attributes) {
		// TODO Auto-generated method stub

		String sourceNode = "";
		String destNode = "";
		
		String destPort="";
		// Map to generate adjacency list representation of subsystem

		
		/* TEST : 1
		 * 
		 * blockList contains all the blocks in model as java objects with necessary attributes set
		 * 
		 * As per our model this basically contains 'Constant,Constant1,Sum'
		 * 
		 * attributes contain child nodes of <Line>
		 * 
		 * 
		 * */
		
		
		
		for (int iter = 0; iter < attributes.getLength(); iter++) {

			
			if (attributes.item(iter).getNodeName() == "P")

			{
				// for a single <p>
				NamedNodeMap temp = attributes.item(iter).getAttributes();

				for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {

					 
					if (temp.item(tempIter).getNodeValue()
							.equalsIgnoreCase("SrcBlock")) {

						sourceNode = attributes.item(iter).getTextContent();
						

					}

					if (temp.item(tempIter).getNodeValue()
							.equalsIgnoreCase("DstBlock")) {

						destNode = attributes.item(iter).getTextContent();
						
					}
					
					// Used for switch to identify port of input and condition
					if (temp.item(tempIter).getNodeValue()
							.equalsIgnoreCase("DstPort")) {

						destPort = attributes.item(iter).getTextContent();
						
					}

				}
			
				
				
			}
		}
		
		
		/* There are 2 <Line> for our model 
		 * 
		 * Test sourceNode and destNode
		 * 
		 * <Line>
		 * <P Name="ZOrder">1</P>
		 * <P Name="SrcBlock">Constant</P>
		 * <P Name="SrcPort">1</P>
		 * <P Name="Points">[68, 0; 0, 60]</P>
		 * <P Name="DstBlock">Sum</P>
		 * <P Name="DstPort">1</P>
		 * </Line>
		 * 
		 * <Line>
		 * <P Name="ZOrder">2</P>
		 * <P Name="SrcBlock">Constant1</P>
		 * <P Name="SrcPort">1</P>
		 * <P Name="Points">[150, 0]</P>
		 * <P Name="DstBlock">Sum</P>
		 * <P Name="DstPort">2</P>
		 * </Line>
		 * 
		 * 
		 * We are interested only in SrcBlock,DstBlock,DstPort in each <Line>
		 * 
		 * Note : DstPort is not used for this model as there is no 'Switch' block
		 * 
		 * 
		 * */
		

		if(sourceNode.equalsIgnoreCase("Constant") || sourceNode.equalsIgnoreCase("Constant1"))
		{
			assertEquals("Sum",destNode);
		}
		
		
		
		
		/* Create an adjacency list for each Node 
		 * 
		 * 
		 * As per our model , adjacency list should be
		 * 
		 * Constant -> Sum
		 * Constant1 -> Sum
		 * 
		 * 
		 * */
		
		
		if (sourceNode != "" && destNode!="") {

			if (adjacencyList.get(sourceNode) != null) {

				adjacencyList.get(sourceNode).add(destNode);

			} else {
				LinkedList<String> addList = new LinkedList<String>();

				addList.add(destNode);

				adjacencyList.put(sourceNode, addList);

			}
			
			
			
			// Test adjacency list
			assertEquals(adjacencyList.get(sourceNode),"Sum");
		
			
			

			Iterator blockListIter = blockList.iterator();

			while (blockListIter.hasNext()) {

				Block blockObj = (Block) blockListIter.next();

			

				if (destNode.equalsIgnoreCase(((Variable)blockObj.getOutput()).getName())) {
					
					// input is not set for delay block as for delay block input is based on delay length
					if(!destNode.startsWith(Constants.DELAY))
					blockObj.setInput(sourceNode,destPort);

					// System.out.println("Super ");

					if (blockObj.isInputSetFlag() || destNode.startsWith(Constants.DELAY)) {

						// System.out.println("Entered both input");
						// Applicable only for delay block. For other blocks input is set.
						//Flag to set input based on delay length.
						/* If delay length is 1 then input is directly the source node
						 */
						boolean flag = false;
						if(((Variable)blockObj.getOutput()).getName().startsWith(Constants.DELAY))
						{
								if( ((Delay)blockObj).getDelayLength()>1)
								{
										blockObj.setInput(((Variable)blockObj.getOutput()).getName()+"_"+"delay_"+(((Delay)blockObj).getDelayLength()-2),destPort);
								}
								else
								{
									blockObj.setInput(sourceNode,destPort);
									flag = true;
								}
						}
						
						List<Expression> input = new ArrayList<Expression>();
						input = blockObj.getInput();
						blockObj.getAccfg().setInput(input);
						List<Expression> expr = new ArrayList<Expression>();
						expr.add(blockObj.expression());
						
						/* For Delay block there shouldn't be FP. Also check for delay length
						 and add additional delay blocks to delayLengthList */
						if(((Variable)blockObj.getOutput()).getName().startsWith(Constants.DELAY))
						{
							List<Delay> delayLengthSet = new ArrayList<Delay>();
							Block delayObj;
							for(int i=0;i<((Delay)blockObj).getDelayLength()-1;i++)
							{
								String name = ((Variable)blockObj.getOutput()).getName()+"_"+"delay_"+i;
								String tempInput;
								if(i==0)
									tempInput = sourceNode;
								else
									tempInput = ((Variable)blockObj.getOutput()).getName()+"_"+"delay_"+(i-1);
								
								
								delayObj = new Delay(name); 
								delayObj.setInput(tempInput, destPort);
								
								
								List<Expression> inputTemp = new ArrayList<Expression>();
								inputTemp = delayObj.getInput();
								delayObj.getAccfg().setInput(inputTemp);
								List<Expression> exprTemp = new ArrayList<Expression>();
								exprTemp.add(delayObj.expression());
								delayObj.getAccfg().setDelay(exprTemp);
								
								delayLengthSet.add(0,(Delay)delayObj);
								
							}
							;
							((Delay)blockObj).setDelayLengthList(delayLengthSet);
							blockObj.getAccfg().setDelay(expr);
							
							// Finally only sourceNode is input to delay block
							if(!flag)
							{
							blockObj.setInput(sourceNode,destPort);
							input = blockObj.getInput();
							
							blockObj.getAccfg().setInput(input);
							}
						}
						
						else
						{
						
						blockObj.getAccfg().setFp(expr);
						}
						return blockObj;

					} else {
						return blockObj;
					}

				}

			}

		}

		return null;
	}

}
