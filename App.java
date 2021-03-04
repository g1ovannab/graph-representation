import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        ArrayList<Files> files_list = new ArrayList<>();
        
        /* Criando um Objeto 'Files' que guarda toda a informação de um arquivo, como
        o próprio arquivo, o FileReader (método que permite leitura) e BufferedReader 
        (método que permite ler linha por linha - read line - até que um \n seja encontrado); */
        File f1 = new File("files/graph1.txt");
        /* O caminho dos arquivos já existentes é uma pasta dentro do projeto; */
        FileReader fr1 = new FileReader(f1);
        BufferedReader br1 = new BufferedReader(fr1);
        Files file1 = new Files(f1, fr1, br1);
        files_list.add(file1);
        /* Ao final, o objeto 'Files' é adicionado na lista de obj 'Files'; */

        //Repete-se o processo para os 4 arquivos existentes;

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
        
        ArrayList<Graph> graphs_list = new ArrayList<>();

        for (Files file : files_list){
            line = file.getBR().readLine();
            byte directed = Byte.parseByte(line);

            line = file.getBR().readLine();
            byte weighted = Byte.parseByte(line);

            line = file.getBR().readLine();
            int vertices = Integer.parseInt(line);

            line = file.getBR().readLine();
            int edges = Integer.parseInt(line);

            Graph graph = new Graph(vertices, edges, directed, weighted);
            graphs_list.add(graph);


            String regexVertices = " ";

            line = file.getBR().readLine();
            String [] sliced = line.split(regexVertices);
            //preciso desse array?


            if (directed == 0 && weighted == 0){
                int[][] matrix = adjacencyMatrix(graph, file, line);

                System.out.print("MATRIZ GRAFO 1\n");
                for (int i = 0; i < graph.getVertices(); i++){
                    for (int j = 0; j < graph.getVertices(); j++){
                        if (matrix[i][j] == -1){
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.print(" " + matrix[i][j] + " ");
                    }
                    System.out.print("\n");
                }

            } else if (directed == 0 && weighted == 1){
                int[][] matrix = adjacencyMatrix(graph, file, line);

                System.out.print("MATRIZ GRAFO 2\n");
                for (int i = 0; i < graph.getVertices(); i++){
                    for (int j = 0; j < graph.getVertices(); j++){
                        if (matrix[i][j] == -1){
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.print(" " + matrix[i][j] + " ");
                    }
                    System.out.print("\n");
                }

            } else if (directed == 1 && weighted == 0){
                //lista de incidência
                System.out.println("Lista de Incidência ainda não implementada.");

            } else if (directed == 1 && weighted == 1){
                int[][] matrix = adjacencyMatrix(graph, file, line);

                System.out.print("MATRIZ GRAFO 3\n");
                for (int i = 0; i < graph.getVertices(); i++){
                    for (int j = 0; j < graph.getVertices(); j++){
                        if (matrix[i][j] == -1){
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.print(" " + matrix[i][j] + " ");
                    }
                    System.out.print("\n");
                }             
            }

        }


        
        scan.close();
    }

    public static int[][] adjacencyMatrix(Graph graph, Files file, String lineRead) throws IOException {
        //Pensar nos 3 casos que vamos utilizar: 
            // N DIR E N PON
            // N DIR E PON
            // DIR E PON
        
        boolean directed = graph.isDirected();
        boolean weighted = graph.isWeighted();

        int vertices = graph.getVertices();


        int[][] adjMatrix = new int[vertices][vertices];

        if (weighted == true){ /*Se for ponderado, vamos preencher a matriz com -1, assim, se houver
            algum ponderado com o valor 0, saberemos distinguir.*/
            for (int line = 0; line < vertices; line++){
                for (int column = 0; column < vertices; column++){
                    adjMatrix[line][column] = -1;
                }
            }
        } else { /*Se não for ponderado, vamos preencher a matriz com 0 normalmente.*/
            for (int line = 0; line < vertices; line++){
                for (int column = 0; column < vertices; column++){
                    adjMatrix[line][column] = 0;
                }
            }
        }
        

        lineRead = file.getBR().readLine();
        // System.out.println(lineRead);
        
        while(lineRead != null){
            String regexEdges = " ";

            String[] slicedEdges = lineRead.split(regexEdges);

            int line = Integer.parseInt(slicedEdges[0]); 
            int column = Integer.parseInt(slicedEdges[1]);

            if (slicedEdges.length == 2){ /*Se o conjunto de arestas tiver somente 2 valores, significa que não é
                ponderado. Isso quer dizer que preencheremos o valor da matriz com 1.*/

                adjMatrix[line - 1][column - 1] = 1;

                if (directed == false){
                    adjMatrix[column - 1][line - 1] = 1;
                }

            } else if (slicedEdges.length == 3){ /*Se o conjunto de arestas tiver 3 valores, significa que é 
                ponderado. Iso quer ddizer que preencheremos o valor da matriz com o valor do ponderado.*/
                
                int value = Integer.parseInt(slicedEdges[2]);
                adjMatrix[line - 1][column - 1] = value;

                if (directed == false){
                    adjMatrix[column - 1][line - 1] = value;
                }
            }

            lineRead = file.getBR().readLine();
        }

        return adjMatrix;
    }


    

}
