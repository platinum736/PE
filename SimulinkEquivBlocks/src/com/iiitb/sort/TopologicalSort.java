package com.iiitb.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Sorts graph - Subsystem representation
 * 
 */

public class TopologicalSort {

	/**
	 * 
	 * Identify Root Nodes of the Subsystem
	 * 
	 */
	public ArrayList<String> rootNodes(
			Map<String, LinkedList<String>> adjacencyList) {

		List<String> rootNodes = new ArrayList<String>();
		Iterator keySetIter = adjacencyList.keySet().iterator();

		String key = "";
		String rootCheck = "";
		while (keySetIter.hasNext()) {
			rootCheck = (String) keySetIter.next();
			Iterator iter = adjacencyList.keySet().iterator();
			boolean rootCheckFlag = true;
			while (iter.hasNext()) {
				key = (String) iter.next();

				if (adjacencyList.get(key).contains(rootCheck)) {
					rootCheckFlag = false;
					break;
				}

			}

			if (rootCheckFlag)
				rootNodes.add(rootCheck);

		}

		return (ArrayList<String>) rootNodes;

	}

	/**
	 * 
	 * @param adjacencyList
	 * @return - Returns incoming edge count of each vertex (* Only for vertex that has
	 *         an outgoing edge)
	 */

	public Map<String, Integer> incomingCount(
			Map<String, LinkedList<String>> adjacencyList) {

		Map<String, Integer> countIncoming = new HashMap<String, Integer>();
		Iterator keySetIter = adjacencyList.keySet().iterator();

		String key = "";
		String rootCheck = "";
		int incomingCount = 0;
		while (keySetIter.hasNext()) {
			rootCheck = (String) keySetIter.next();
			incomingCount = 0;
			Iterator iter = adjacencyList.keySet().iterator();

			while (iter.hasNext()) {
				key = (String) iter.next();

				if (adjacencyList.get(key).contains(rootCheck)) {

					incomingCount++;
				}

			}

			countIncoming.put(rootCheck, incomingCount);

		}
		return countIncoming;
	}

	/**
	 * Sorts graph (Represented in Adjacency list representation) - Topological
	 * Sort of the subsystem . "rootNodes" hold the root nodes of the subsystem
	 * 
	 */
	public ArrayList<String> sortGraph(
			Map<String, LinkedList<String>> adjacencyList) {

		// Identify Root Nodes in the Subsystem
		List<String> rootNodes = rootNodes(adjacencyList);

		// Get incoming edge count for each vertex
		Map<String, Integer> incomingCount = incomingCount(adjacencyList);

		List<String> sortedList = new ArrayList<String>();

		return new ArrayList<String>();
	}
}
