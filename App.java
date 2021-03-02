import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        ArrayList<Files> files_list = new ArrayList<>();
        
        /* Criando um Objeto 'Files' que guarda toda a informação de um arquivo, como
        o próprio arquivo, o FileReader (método que permite leitura) e BufferedReader 
        (método que permite ler linha por linha - read line - até que um \n seja encontrado); */
        File f1 = new File("files/file1.txt");
        /* O caminho dos arquivos já existentes é uma pasta dentro do projeto; */
        FileReader fr1 = new FileReader(f1);
        BufferedReader br1 = new BufferedReader(fr1);
        Files file1 = new Files(f1, fr1, br1);
        files_list.add(file1);
        /* Ao final, o objeto 'Files' é adicionado na lista de obj 'Files'; */

        //Repete-se o processo para os 4 arquivos existentes;

        //File 2
        File f2 = new File("files/file2.txt");
        FileReader fr2 = new FileReader(f2);
        BufferedReader br2 = new BufferedReader(fr2);
        Files file2 = new Files(f2, fr2, br2);
        files_list.add(file2);

        //File 3
        File f3 = new File("files/file3.txt");
        /* O caminho dos arquivos já existentes é uma pasta dentro do projeto; */
        FileReader fr3 = new FileReader(f3);
        BufferedReader br3 = new BufferedReader(fr3);
        Files file3 = new Files(f3, fr3, br3);
        files_list.add(file3);

        //File 4
        File f4 = new File("files/file4.txt");
        /* O caminho dos arquivos já existentes é uma pasta dentro do projeto; */
        FileReader fr4 = new FileReader(f4);
        BufferedReader br4 = new BufferedReader(fr4);
        Files file4 = new Files(f4, fr4, br4);
        files_list.add(file4);


        String line = " ";
        
        ArrayList<Graph> graphs_list = new ArrayList<>();

        for (Files file : files_list){
            line = file.getBR().readLine();
            int vertices = Integer.parseInt(line);

            line = file.getBR().readLine();
            int edges = Integer.parseInt(line);

            line = file.getBR().readLine();
            byte directed = Byte.parseByte(line);

            line = file.getBR().readLine();
            byte weighted = Byte.parseByte(line);

            Graph graph = new Graph(vertices, edges, directed, weighted);
            graphs_list.add(graph);

            //aqui tenho que gravar vértices e arestas
            
            // while (file nao acabou){
            //     continua lendo
            // }
            // fecha reader e buffer do arquivo
        }


        scan.close();
    }
}
