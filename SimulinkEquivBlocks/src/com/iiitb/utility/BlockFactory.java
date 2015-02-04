package com.iiitb.utility;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.iiitb.blocks.Block;
import com.iiitb.blocks.Constant;
import com.iiitb.blocks.Sum;

public class BlockFactory {

	public static Block generateBlock(String blockName, NodeList attributes) {

		Block block = null;
		if (blockName.startsWith(Constants.CONST)) {
			block = new Constant(blockName);
			for (int iter = 0; iter < attributes.getLength(); iter++) {
				//Prevent #text tag
				if (attributes.item(iter).getNodeName() == Constants.PARA_TAG)

				{

					NamedNodeMap temp = attributes.item(iter).getAttributes();

					for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {
						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase(Constants.VALUE)) {

							block.setValue(attributes.item(iter)
									.getTextContent());
							
							// Set FP based on value
							List<String> expr = new ArrayList<String>();
							expr.add(block.expression());
							block.getAccfg().setFp(expr);
							
						}

					}

				}

			}

		}

		if (blockName.startsWith(Constants.SUM)) {
			block = new Sum(blockName);
			for (int iter = 0; iter < attributes.getLength(); iter++) {

				if (attributes.item(iter).getNodeName() == Constants.PARA_TAG)

				{

					NamedNodeMap temp = attributes.item(iter).getAttributes();

					for (int tempIter = 0; tempIter < temp.getLength(); tempIter++) {
						if (temp.item(tempIter).getNodeValue()
								.equalsIgnoreCase(Constants.INPUT)) {
							
							
							if(attributes.item(iter)
							.getTextContent().replaceAll("|","").contains("++"))
							{
								block.setSign(1);
							}
							
							else if(attributes.item(iter)
									.getTextContent().replaceAll("|","").contains("+-"))
							{
								block.setSign(2);
							}
							
							else
							{
								
								block.setSign(3);
							}
							
							
							
							
						}

					}

				}

			}

		}

		return block;

	}
}
