package gui;
import java.io.IOException;
import java.util.Iterator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import grafos.Application;

public class GUI {
	
	//http://graphstream-project.org/doc/Tutorials/Storing-retrieving-and-displaying-data-in-graphs/
	
	public static  String[] nodeNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
	public static Graph graph = new SingleGraph("tutorial 1");
	
	
	public static void main(String args[]) throws IOException {
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GUI gui = new GUI();
			}
			
		});
		t.start();
		new Application();
		
	}

	public GUI() {
            graph.addAttribute("ui.stylesheet", styleSheet);
            graph.setAutoCreate(true);
            graph.setStrict(false);
            graph.display();
            
            addNodes();
            addEdges();
            
            for (Node node : graph) {
                node.addAttribute("ui.label", node.getId());
            }

            //explore(graph.getNode("A"));
        }

	public void explore(Node source) {
		Iterator<? extends Node> k = source.getBreadthFirstIterator();

		while (k.hasNext()) {
			Node next = k.next();
			next.setAttribute("ui.class", "marked");
			sleep();
		}
		
		Iterator<? extends Node> j = source.getBreadthFirstIterator();
		while(j.hasNext()){
			Node next = j.next();
			next.removeAttribute("ui.class");
			sleep();
		}
	}
	
	public void addNodes(){
		for(String node: nodeNames){
			Node a = graph.addNode(node);
	        a.addAttribute("ui.label", node);
		}
	}
	
	public void addEdges(){
		graph.addEdge("Edge_0", nodeNames[0], nodeNames[1]).addAttribute("ui.label", 85);
		graph.addEdge("Edge_1", nodeNames[0], nodeNames[2]).addAttribute("ui.label", 217);
		graph.addEdge("Edge_2", nodeNames[0], nodeNames[4]).addAttribute("ui.label", 173);
		graph.addEdge("Edge_3", nodeNames[2], nodeNames[6]).addAttribute("ui.label", 186);
		graph.addEdge("Edge_4", nodeNames[2], nodeNames[7]).addAttribute("ui.label", 103);
		graph.addEdge("Edge_5", nodeNames[3], nodeNames[7]).addAttribute("ui.label", 183);
		graph.addEdge("Edge_6", nodeNames[5], nodeNames[8]).addAttribute("ui.label", 250);
		graph.addEdge("Edge_7", nodeNames[8], nodeNames[9]).addAttribute("ui.label", 84);
		graph.addEdge("Edge_8", nodeNames[7], nodeNames[9]).addAttribute("ui.label", 167);
		graph.addEdge("Edge_9", nodeNames[4], nodeNames[9]).addAttribute("ui.label", 502);
		graph.addEdge("Edge_10", nodeNames[9], nodeNames[10]).addAttribute("ui.label", 40);
		graph.addEdge("Edge_11", nodeNames[1], nodeNames[10]).addAttribute("ui.label", 600);
	}

	protected void sleep() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	protected String styleSheet = "node {" + "	fill-color: black;" + "}" + "node.marked {" + "	fill-color: red;" + "}";
}


 
 

