// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;

class Heap
{
    private int[] a;	   // heap array
    private int[] hPos;	   // hPos[h[k]] == k
    private int[] dist;    // dist[v] = priority of v

    private int N;         // heap size
   
    // The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array

    // Constructor for Initialising the heap with a max size and distance arrays
    public Heap(int maxSize, int[] _dist, int[] _hPos) 
    {
        N = 0;
        a = new int[maxSize + 1];
        dist = _dist;
        hPos = _hPos;
    }


    public boolean isEmpty() 
    {
        return N == 0;
    }


    // Method to sift up for maintaining heap order
    public void siftUp( int k) 
    {
        int v = a[k];
        // code yourself       
        while (dist[v] < dist[a[k / 2]] && k > 1) {
            a[k] = a[k / 2]; // Move parent node down
            hPos[a[k]] = k; // Update position of parent node in hPos
            k = k / 2; // Move to parents index
        }

        // Place the node v at the correct position where the loop ends
        a[k] = v;
        hPos[v] = k; // Updates the position of node v in hPos array
    }


    public void siftDown( int k) 
    {
        int v  = a[k]; // Start node at index k
        int j; // variable for child index
        // code yourself 

        // Continue sifting down as long as there are children
        while (k <= N / 2) {
            j = 2 * k; // Calc the left child index
            if (j < N && dist[a[j]] > dist[a[j + 1]]) { // Check if there is a right child (j < N) and if it has a lower distance value than the left child.
                j++; // Use the right child if it has a smaller distance.
            }

            // If the current node v has a distance less than or equal to the smaller child
            if (dist[v] <= dist[a[j]]) {
                break;
            }

            // Swap the current node with its smaller child
            a[k] = a[j]; // move child up 
            hPos[a[k]] = k; // update child position in array
            k = j; // move child down
        }

        // place node v at final position where loop ends
        a[k] = v;
        hPos[v] = k; // update position node v in array
    }


    // Insert an element into the heap
    public void insert( int x) 
    {
        a[++N] = x;
        siftUp( N);
    }

    // remove and return the top element from the heap
    public int remove() 
    {   
        int v = a[1];
        hPos[v] = 0; // v is no longer in heap
        a[N+1] = 0;  // put null node into empty spot
        
        a[1] = a[N--];
        siftDown(1);
        
        return v;
    }

}

class Graph {
    class Node {
        public int vert;
        public int wgt;
        public Node next;
    }
    
    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node z;
    private int[] mst;
    
    // used for traversing graph
    private int[] visited;
    private int id;
    
    
    // default constructor
    public Graph(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;
        Node t;

        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	           
        String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();        
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        
        // create sentinel node
        z = new Node(); 
        z.next = z;
        
        // create adjacency lists, initialised to sentinel node z       
        adj = new Node[V+1];        
        for(v = 1; v <= V; ++v)
            adj[v] = z;               
        
       // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
            
            System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));   

           
            
            // write code to put edge into adjacency matrix     
            
        }	       
    }
   
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
    // method to display the graph representation
    public void display() {
        int v;
        Node n;
        
        for(v=1; v<=V; ++v){
            System.out.print("\nadj[" + toChar(v) + "] ->" );
            for(n = adj[v]; n != z; n = n.next) 
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");    
        }
        System.out.println("");
    }


    
	public void MST_Prim(int s)
	{
        int v, u;
        int wgt, wgt_sum = 0;
        int[]  dist, parent, hPos;
        Node t;

        //code here
        
        //dist[s] = 0;
        
        //Heap h =  new Heap(V, dist, hPos);
        //h.insert(s);
        
        //while ( ...)  
        //{
            // most of alg here
            
       // }
        System.out.print("\n\nWeight of MST = " + wgt_sum + "\n");
        
                  		
	}
    
    public void showMST()
    {
            System.out.print("\n\nMinimum Spanning tree parent array is:\n");
            for(int v = 1; v <= V; ++v)
                System.out.println(toChar(v) + " -> " + toChar(mst[v]));
            System.out.println("");
    }

    public void SPT_Dijkstra(int s)
    {

    }

}

public class GraphLists {
    public static void main(String[] args) throws IOException
    {
        int s = 2;
        String fname = "wGraph1.txt";               

        Graph g = new Graph(fname);
       
        g.display();

       //g.DF(s);
       //g.breadthFirst(s);
       //g.MST_Prim(s);   
       //g.SPT_Dijkstra(s);               
    }
}
