import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        /* ArrayList that keeps the infos about the content of the Files we have; */
        ArrayList<Files> files_list = new ArrayList<>();
        
        /* Creating a Object 'Files' that stores all the info in a file, such as 
        the file itself, the FileReader (readable method) and BufferedReader
        (method that allows you to read line by line until a \n is found); */
        File f1 = new File("files/graph1.txt");
        /* The path of existing filesis a folder inside the project; */

        FileReader fr1 = new FileReader(f1);
        BufferedReader br1 = new BufferedReader(fr1);
        Files file1 = new Files(f1, fr1, br1);
        files_list.add(file1);
        /* At the end, the object 'Files' is added in the 'Files' objects list; */

        //Repeat all the process for the 4 existing files;

        //File 2
        File f2 = new File("files/graph2.txt");
        FileReader fr2 = new FileReader(f2);
        BufferedReader br2 = new BufferedReader(fr2);
        Files file2 = new Files(f2, fr2, br2);
        files_list.add(file2);

        //File 3
        File f3 = new File("files/graph3.txt");
        FileReader fr3 = new FileReader(f3);
        BufferedReader br3 = new BufferedReader(fr3);
        Files file3 = new Files(f3, fr3, br3);
        files_list.add(file3);

        //File 4
        File f4 = new File("files/graph4.txt");
        FileReader fr4 = new FileReader(f4);
        BufferedReader br4 = new BufferedReader(fr4);
        Files file4 = new Files(f4, fr4, br4);
        files_list.add(file4);


        String line = " ";
        
        /* ArrayList that keeps the infos about the content of graphs; */
        ArrayList<Graph> graphs_list = new ArrayList<>();

        /* For each file in the 'files_list' list, do the following code: */
        for (Files file : files_list){

            line = file.getBR().readLine();
            byte directed = Byte.parseByte(line);

            line = file.getBR().readLine();
            byte weighted = Byte.parseByte(line);

            line = file.getBR().readLine();
            int vertices = Integer.parseInt(line);

            line = file.getBR().readLine();
            int edges = Integer.parseInt(line);

            /* Here, the code reads the first 4 lines of the file that keeps (respectively)
            if the graph is directed, if the file is weighted, how many verices it has and 
            how many edges it has; */

            /* Store in a 'Graph' object the infos that we got and add this graph to the graphs list; */
            Graph graph = new Graph(vertices, edges, directed, weighted);
            graphs_list.add(graph);

            /* Reads the next line that informs the set of vertices (which for convenience
            we leave in ascending and sequential order); */
            line = file.getBR().readLine();

            /* Variable that tracks which graph we're working with, to use when printing; */
            byte k = 0;

            /* Here, we create the representation to the graphs, depending on if it's directed and weighted, or not; */
            if (directed == 0 && weighted == 0){
                /* The 'matrix' variable stores the value returned from the method call; */
                int[][] matrix1 = new int[vertices][vertices];
                adjacencyMatrix(matrix1, graph, file, line);

                k = 1;
                /* Calls the method that prints the graph; */
                printGraphs(scan, graph, matrix1, null, k);

            } else if (directed == 0 && weighted == 1){
                /* The 'matrix' variable stores the value returned from the method call; */
                int[][] matrix2 = new int[vertices][vertices];
                adjacencyMatrix(matrix2, graph, file, line);

                k = 2;
                /* Calls the method that prints the graph; */
                printGraphs(scan, graph, matrix2, null, k);                

            } else if (directed == 1 && weighted == 0){
                //lista de adjacência
                //System.out.println("Lista de Adjacência ainda não implementada.");

                ArrayList<ArrayList<Integer>> list = new ArrayList<>();
                adjacencyList(list, graph, file, line);

                k = 3;
                printGraphs(scan, graph, null, list, k);

            } else if (directed == 1 && weighted == 1){
                /* The 'matrix' variable stores the value returned from the method call; */
                int[][] matrix3 = new int[vertices][vertices];
                adjacencyMatrix(matrix3, graph, file, line);
                
                k = 4;
                /* Calls the method that prints the graph; */
                printGraphs(scan, graph, matrix3, null, k);                    
            }

        }
        
        scan.close();
    }

    //COMMENT
    public static void printGraphs(Scanner scan, Graph graph, int[][] matrix, ArrayList<ArrayList<Integer>> list, byte k){
        String answer = "";
        System.out.println("Do you want to print the representation of graph "+ k +"? Type y or n.");
        answer = scan.nextLine().toLowerCase();

        if (answer.equals("yes") || answer.equals("y")){

            if (k == 1){
                System.out.println("GRAPH 1 - Not directed and not weighted.\n");
                for (int i = 0; i < graph.getVertices(); i++){
                    for (int j = 0; j < graph.getVertices(); j++){
                        System.out.print(matrix[i][j] + "  ");
                    }
                    System.out.print("\n");
                }
            }
            
            if (k == 2){
                System.out.println("GRAPH 2 - Not directed and weighted.\n");
                for (int i = 0; i < graph.getVertices(); i++){
                    for (int j = 0; j < graph.getVertices(); j++){
                        if (matrix[i][j] >= 0 && matrix[i][j] < 10){
                            System.out.print(" " + matrix[i][j] + " ");
                        } else {
                            System.out.print(matrix[i][j] + " ");
                        }
                    }
                    System.out.print("\n");
                }    
            }

            if (k == 3){
                System.out.println("GRAPH 3 - Directed and not weighted.\n");
                System.out.println("\nThis list is implemented using successors.");
                for (int i = 0; i < list.size(); i++){
                    System.out.println((i + 1) + " -> " + list.get(i));
                }
            }

            if (k == 4){
                System.out.print("GRAPH 4 - Directed and weighted.\n");
                for (int i = 0; i < graph.getVertices(); i++){
                    for (int j = 0; j < graph.getVertices(); j++){
                        if (matrix[i][j] >= 0 && matrix[i][j] < 10){
                            System.out.print(" " + matrix[i][j] + " ");
                        } else {
                            System.out.print(matrix[i][j] + " ");
                        }
                    }
                    System.out.print("\n");
                }      
            }

        } else{
            if (k == 4)
                System.out.println("Okay, thanks for visiting. Check out the code!");
            else 
                System.out.println("Okay!");
        }
    }

    public static void adjacencyMatrix(int[][] adjMatrix, Graph graph, Files file, String lineRead) throws IOException {

        /* Variables that will monitor the filling in the matrices; */
        boolean directed = graph.isDirected();
        boolean weighted = graph.isWeighted();

        /* Variable that will determinate the number of rows and columns of the matrix; */
        int vertices = graph.getVertices();

        if (weighted == true){ /* If the graph is weighted, we'll fill the matrix with -1,
        so, if there's some weighted value equal 0, we'll know how to distinguish; */
            for (int row = 0; row < vertices; row++){
                for (int column = 0; column < vertices; column++){
                    adjMatrix[row][column] = -1;
                }
            }
        } else { /*If the graph isn't weighted, we'll fill the matrix with 0 like usually; */
            for (int row = 0; row < vertices; row++){
                for (int column = 0; column < vertices; column++){
                    adjMatrix[row][column] = 0;
                }
            }
        }
        
        /* Start reading the edges set; */
        lineRead = file.getBR().readLine();
        
        while(lineRead != null){ /* If this line isn't null, it means that has a edge value; */
            String regexEdges = " ";

            /* We'll slice this line in a array of Strings; */
            String[] slicedEdges = lineRead.split(regexEdges);

            /* The first 2 values of this array is equal to some vertice and according to 
            the concept of adjacency matrix, we'll represent the edge from the rows and columns; */
            int row = Integer.parseInt(slicedEdges[0]); 
            int column = Integer.parseInt(slicedEdges[1]);

            if (slicedEdges.length == 2){ /* If the edges set has only 2 values, it means that
                the graph isn't weighted. This means that we'll fill the value of the matriz with 1; */

                adjMatrix[row - 1][column - 1] = 1;

                /* Meantime, if the graph is NOT directed, we need to transform in a transposed matrix, 
                and the values will be mirrored; */
                if (directed == false){
                    adjMatrix[column - 1][row - 1] = 1;
                }

            } else if (slicedEdges.length == 3){ /* If the edges set has 3 values, it means that 
                the graph is weighted. This means that we'll fill the value of the matrix with
                the wieghted value; */
                
                /* If there's a weighted value, he's in the 3rd position of the array (index 2); */
                int value = Integer.parseInt(slicedEdges[2]);
                adjMatrix[row - 1][column - 1] = value;

                /* Meantime, if the graph is NOT directed, we need to transform in a transposed matrix, 
                and the values will be mirrored; */
                if (directed == false){
                    adjMatrix[column - 1][row - 1] = value;
                }
            }

            /* We need to read the next line, so it can go back to the while validation;  */
            lineRead = file.getBR().readLine();
        }
    }

    //COMMENT
    public static void adjacencyList(ArrayList<ArrayList<Integer>> edgesSet, Graph graph, Files file, String lineRead) throws IOException {

        int vertices = graph.getVertices();

        //ArrayList<ArrayList<Integer>> edgesSet = new ArrayList<>();
        edgesSet = new ArrayList<>();

        ArrayList<Integer> destiny = new ArrayList<>();

        for (int i = 0; i < vertices; i++){
            destiny = new ArrayList<>();
            edgesSet.add(i, destiny);
        }

        /* Start reading the edges set; */
        lineRead = file.getBR().readLine();
        
        while(lineRead != null){ /* If this line isn't null, it means that has a edge value; */
            String regexEdges = " ";

            /* We'll slice this line in a array of Strings; */
            String[] slicedEdges = lineRead.split(regexEdges);

            int s = Integer.parseInt(slicedEdges[0]); //source
            int d = Integer.parseInt(slicedEdges[1]); //destiny

            destiny = edgesSet.get(s - 1);
            destiny.add(d);
            edgesSet.set(s - 1, destiny);


            /* We need to read the next line, so it can go back to the while validation;  */
            lineRead = file.getBR().readLine();
        }
    }
    

}
