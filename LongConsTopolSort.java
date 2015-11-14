import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Digraph{
    private final int V;
    private final List<Edge> [] adj;

    public Digraph(int V){
	this.V = V; 
	adj = (List<Edge> []) new List[V];
	for (int v = 0; v < V; v++){
	    adj[v] = new ArrayList<Edge>();
	}
    } 
    public int V(){
	return V;
    }

    public void addEdge(Edge edge){
	int v  = edge.from();
	adj[v].add(edge);
	}


    public Iterable<Edge> adj(int v){
	return adj[v];
	}
}
class Edge{
    int v;
    int w;
    int weight;
    Edge(int v, int w){
	this.v = v;
	this.w = w;
	weight = -1;
    }
    public int  from(){ return v;}
    public int to(){ return w;}
    public int weight(){ return weight;}
}

class TopologicalSort{
    private boolean [] marked;
    private Stack<Integer> reverse;

    TopologicalSort(Digraph graph){
    reverse = new Stack<Integer>();
    marked =  new boolean[graph.V()];

    for (int v = 0; v < graph.V(); v++){
        if (!marked[v]) dfs(graph, v);    
    }
    }
    private void dfs(Digraph graph, int v){
        marked[v] = true;
	for (Edge e: graph.adj(v)){
	    if (!marked[e.to()]){
		dfs(graph, e.to());
	    }
	}
	
	reverse.push(v);
    
    }

    public List<Integer> getTopological(){
    List<Integer> list =  new ArrayList<Integer>();
    while(!reverse.empty()){
        list.add(reverse.pop());
    }
    return list;
    }
    
}


class LongestPath{
    public int distTo [];
    public Edge edgeTo [];
   

    LongestPath(Digraph graph){
	distTo = new int [graph.V()];
	edgeTo = new Edge [graph.V()];

	for (int v=0; v < graph.V(); v++){
	    distTo[v] = Integer.MIN_VALUE;
	}
       
	TopologicalSort ts = new TopologicalSort(graph);
	List<Integer> list = ts.getTopological();
	 
	for (int v : list) {
	    
	    for (Edge e: graph.adj(v)) relax(e); 
	}
    }

    private void relax(Edge e){
	int v =  e.from();
	int w = e.to();
	
	if (distTo[w] < distTo[v] + e.weight()){
	    distTo[w] =  distTo[v] + e.weight();
	    edgeTo[w] =  e;
	}
    }
    public List<Integer> pathTo(int v){
	List<Integer> list =  new ArrayList<Integer>();
	Stack<Integer> st =  new Stack<Integer>();
	st.push(v);
	for (Edge e = edgeTo[v]; e !=null; e = edgeTo[e.from()])
	    st.push(e.from());
	while(!st.empty()){
        list.add(st.pop());
    }
	return list;
    }
}
public class LongConsTopolSort{

    private boolean check(int a, int b){
        return (a + 1 == b) ? true : false;
    } 
    

    
    public List<Integer> longestConsec(int [][] m){
        int n = m.length;
        Digraph graph = new Digraph(n*n);
        for (int i = 0; i < n; i++){
	    for (int j = 0; j < n; j++){
		if (j == n-1 && i == n-1) continue;
                else if (j == n-1) {
		    int a = m[i][j];
		    int b = m[i+1][j];
		    if (check(a,b)) graph.addEdge(new Edge(i*n+j, (i+1)*n + j));
		    if (check(b,a)) graph.addEdge(new Edge((i+1)*n+j, i*n+j));
                    

                    continue;
		}
		else if (i == n-1){
		    int a = m[i][j];
		    int b = m[i][j+1];
                    if (check(a,b)) graph.addEdge(new Edge(i*n+j, i*n + j+1));
		    if (check(b,a)) graph.addEdge(new Edge(i*n+j+1, i*n+j)); 
		    continue;
		}
		else{
		    int a = m[i][j];
		    int b = m[i][j+1];
		    int c = m[i+1][j];
		    if (check(a,b)) graph.addEdge(new Edge(i*n+j, i*n+j+1));
		    if (check(b,a)) graph.addEdge(new Edge(i*n+j+1, i*n+j));
		    if (check(a,c)) graph.addEdge(new Edge(i*n+j, (i+1)*n+j));
		    if (check(c,a)) graph.addEdge(new Edge((i+1)*n+j, i*n+j));
		}//else
	    }//for
	}//for
	 
	//print digraph
	/*for (int v = 0; v < graph.V(); v++){
	    for (Edge e : graph.adj(v)){

		System.out.println(v + " : " + e.from() + " --> " + e.to());
	    }
 
	    }*/

	LongestPath lp =  new LongestPath(graph);
	List<Integer> list = new ArrayList<Integer>();
	List<Integer> longest = new ArrayList<Integer>();
	
	longest = lp.pathTo(0);	
	int maxsize =  longest.size();
        for (int v = 1; v< graph.V(); v++){
	    list = lp.pathTo(v);
	    if (maxsize <= list.size()) {
                longest = list;
		maxsize = list.size(); 
	    }

	}
      
        List<Integer> result = new ArrayList<Integer>();
        for (int i: longest) result.add(m[i/n][i%n]);
	return result;
    }
    
    public static void main(String [] args){
	int [][] matrix = new int [4][4];
	int n = matrix.length;
	
	//1 2 3 4 5 6	
	matrix[0][0] =  1;
	matrix[0][1] = 6;
	matrix[0][2] = 7;
	matrix[0][3] = 2;
        
	matrix[1][0] =  2;
	matrix[1][1] = 3;
	matrix[1][2] = 3;
	matrix[1][3] = 7;
        
	matrix[2][0] =  6;
	matrix[2][1] = 4;
	matrix[2][2] = 2;
	matrix[2][3] = 1;
	
	matrix[3][0] =  5;
	matrix[3][1] = 4;
	matrix[3][2] = 3;
	matrix[3][3] = 1;

	//3 4 5 6 7 8 9 10 11 12
	/*matrix[0][0] = 1;
	matrix[0][1] = 10;
	matrix[0][2] = 11;
	matrix[0][3] = 12;
        
	matrix[1][0] =  8;
	matrix[1][1] = 9;
	matrix[1][2] = 13;
	matrix[1][3] = 14;
        
	matrix[2][0] =  7;
	matrix[2][1] = 6;
	matrix[2][2] = 3;
	matrix[2][3] = 16;
	
	matrix[3][0] =  2;
	matrix[3][1] = 5;
	matrix[3][2] = 4;
	matrix[3][3] = 15;*/
	
	//3 4 5 6 7 8
	/*matrix[0][0] = 1;
	matrix[0][1] = 2;
	matrix[0][2] = 5;
	matrix[0][3] = 8;
        
	matrix[1][0] =  8;
	matrix[1][1] = 3;
	matrix[1][2] = 4;
	matrix[1][3] = 9;
        
	matrix[2][0] =  7;
	matrix[2][1] = 6;
	matrix[2][2] = 3;
	matrix[2][3] = 10;
	
	matrix[3][0] =  1;
	matrix[3][1] = 5;
	matrix[3][2] = 4;
	matrix[3][3] = 11;*/

	LongConsTopolSort lcts = new LongConsTopolSort();
        
	List<Integer> list = lcts.longestConsec(matrix);
	for (int i : list) System.out.print(i + " " );
	System.out.println();
    }
}
