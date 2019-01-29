package game.risk.model;
import java.util.LinkedList;
import java.util.Scanner;


public class Mapeditor
{

    static class Graph
    {
        int V;
        LinkedList<Integer> graphadjacentlist[];

        Graph(int V)
        {
            this.V = V;
            graphadjacentlist = new LinkedList[V];

            for(int i = 0; i < V ; i++)
            {
                graphadjacentlist[i] = new LinkedList<>();
            }
        }

    }

    static void addConnectivity(Graph graph, int source, int destination)
    {
        graph.graphadjacentlist[source].add(destination); // adding an edge from the source to the destination
        graph.graphadjacentlist[destination].add(source); // to add the edge from the destination to the source as well.

    }


    static void graphprint(Graph graph)
    {
        for(int v = 0; v < graph.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Integer moving: graph.graphadjacentlist[v]){
                System.out.print(" -> "+moving);
            }
            System.out.println("\n");
        }
    }

    public static void main(String args[])
    {

        int V = 5;
//		System.out.println("Enter the number of countries that you want to have in the MAP");
//		Scanner scanner = new Scanner(System.in);
//		int V = scanner.nextInt();

        Graph graph = new Graph(V);


        addConnectivity(graph, 0, 1);
        addConnectivity(graph, 0, 4);
        addConnectivity(graph, 1, 2);
        addConnectivity(graph, 1, 3);
        addConnectivity(graph, 1, 4);
        addConnectivity(graph, 2, 3);
        addConnectivity(graph, 3, 4);

        graphprint(graph);

//		for(int j = 0; j < V; j ++)
//		{
//			System.out.println("Enter the two integers for the graph connectivity");
//			int src = scanner.nextInt();
//			int dest = scanner.nextInt();
//		addConnectivity(graph,src, dest);
//		}
    }

}
