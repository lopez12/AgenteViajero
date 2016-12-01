package grafos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.graphstream.graph.Node;

import gui.GUI;

public class Application {

	private List<Vertex> nodes;
	private List<Edge> edges;

	public Application() throws IOException {
		buildGraph();
	}

	public void buildGraph() throws IOException {
		String inicio, destino;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < GUI.nodeNames.length; i++) {
			Vertex location = new Vertex(GUI.nodeNames[i], GUI.nodeNames[i]);
			nodes.add(location);
		}

		addLane("Edge_0", 0, 1, 85);
		addLane("Edge_1", 0, 2, 217);
		addLane("Edge_2", 0, 4, 173);
		addLane("Edge_3", 2, 6, 186);
		addLane("Edge_4", 2, 7, 103);
		addLane("Edge_5", 3, 7, 183);
		addLane("Edge_6", 5, 8, 250);
		addLane("Edge_7", 8, 9, 84);
		addLane("Edge_8", 7, 9, 167);
		addLane("Edge_9", 4, 9, 502);
		addLane("Edge_10", 9, 10, 40);
		addLane("Edge_11", 1, 10, 600);

		Graph graph = new Graph(nodes, edges);
		Algoritmo dijkstra = new Algoritmo(graph);
		
		while(true){
			int posInicio=-1, posDestino=-1;
			System.out.println("Elige ciudad inicio...");
			inicio = br.readLine();
			cleanMap();
			System.out.println("Elige ciudad destino...");
			destino = br.readLine();			
			for(int i=0;i<nodes.size();i++){
				if(nodes.get(i).getId().equalsIgnoreCase(inicio)){
					posInicio=i;
				}
				if(nodes.get(i).getId().equalsIgnoreCase(destino)){
					posDestino=i;
				}
			}
			if(posInicio == -1 || posDestino == -1){
				System.out.println("Ciudad no encontrada.. Verifique datos de entrada!");
				continue;
			}
			dijkstra.execute(nodes.get(posInicio));
			LinkedList<Vertex> path = dijkstra.getPath(nodes.get(posDestino));
			if(path.size() <= 0){
				System.out.println("No se encontro ruta desde  " + inicio + " a " + destino);
				continue;
			}
			for (Vertex vertex : path) {
				Iterator<Node> iterator = GUI.graph.iterator();
				while(iterator.hasNext()){
					Node node = iterator.next();
					if(node.getId().equals(vertex.getId())){
						node.setAttribute("ui.class", "marked");
						sleep();
						break;
					}
				}
				System.out.println(vertex);
			}
		}
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		Edge lane2 = new Edge(laneId, nodes.get(destLocNo), nodes.get(sourceLocNo), duration);
		edges.add(lane);
		edges.add(lane2);
	}
	
	protected void sleep() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}
	
	private void cleanMap(){
		Iterator<Node> iterator = GUI.graph.iterator();
		while(iterator.hasNext()){
			Node next = iterator.next();
			next.removeAttribute("ui.class");
		}
	}
}
