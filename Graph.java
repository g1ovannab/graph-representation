public class Graph {
    private int vertices;
    private int edges;
    private Boolean directed;
    private Boolean weighted;

    public int getVertices() { return vertices; }
    public void setVertices(int v) { 
        if(v < 2){
            this.vertices = 2;
        } else {
            this.vertices = v;
        }
    }

    public int getEdges() { return edges; }
    public void setEdges(int e) { 
        if(e < 1){
            this.edges = 1;
        } else {
            this.edges = e;
        }
    }

    public Boolean isDirected() { return directed; }
    public Boolean setIsDirected(byte d) { 
        if(d == 0){
            return this.directed = false;
        } else if(d == 1){
            return this.directed = true;
        }
        return null;
    }

    public Boolean isWeighted() { return weighted; }
    public Boolean setIsWeighted(byte w) { 
        if(w == 0){
            return this.weighted = false;
        } else if(w == 1){
            return this.weighted = true;
        }
        return null;
    }

    public Graph(){
        this.vertices = 0;
        this.edges = 0;
        this.directed = null;
        this.weighted = null;
    }

    public Graph(int v, int e, byte d, byte w){
        this.vertices = v;
        this.edges = e;
        this.directed = setIsDirected(d);
        this.weighted = setIsWeighted(w);
    }
}
