package com.main;

import com.exceptions.ArquivoCorrompidoException;
// import com.objetos.ResultadosTurma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Principal {

    private static float medias;
    private static int contadorDeLinhas;
    private static HashMap<Long, Float> drebyMediasDaTurma;
    private static ArquivoCorrompidoException k;


    public Principal() {
        medias = 0;
    }

    /**
     * A funcao apenas inicializa um Arquivo.
     * @param pathDoArquivo
     */
    private static Scanner abrirArquivo(String pathDoArquivo) throws FileNotFoundException {
        File arquivo = new File(pathDoArquivo);
        return new Scanner(arquivo);
    }

    /**
     * A funcao transforma o arquivo aberto e passa para um hashmap para armazenamento.
     * @param file
     * @return a media da turma.
     * @throws ArquivoCorrompidoException
     */
    private static float calcularMedia(Scanner file) throws ArquivoCorrompidoException {
        while (file.hasNext()){
            try {
                String scanner = file.nextLine();
                String[] tokens = scanner.split(" ");
                float notas = Float.parseFloat(tokens[1]);
                long keyDre = Long.parseLong(tokens[0]);
                drebyMediasDaTurma.put(keyDre, notas);
            } catch (Exception e){
                contadorDeLinhas++;
            }
        }

        for (float x : drebyMediasDaTurma.values()) {
            medias += x;
        }

        if (contadorDeLinhas <= drebyMediasDaTurma.size()){
            return medias/drebyMediasDaTurma.size();
        } else{
            k.setContaLinhasCorrompidas(contadorDeLinhas);
            throw k;
        }

    }

    public static void main(String[] args) {
        boolean arquivoEncontrado = false;
        Scanner file = null;


        //Inicializa e cria o Hashmap e a Exception.
        drebyMediasDaTurma = new HashMap<>();
        k = new ArquivoCorrompidoException();

        //Um laco fardado a apenas uma unica rolagem se o arquivo digitado for encontrado.
        while (!arquivoEncontrado){
            System.out.println("Digite o nome do arquivo:");
            Scanner sc = new Scanner(System.in);
            String path = sc.nextLine();
            String pathDoArquivo = "src/com/file/" + path + ".txt";
            try {
                file = abrirArquivo(pathDoArquivo);
                arquivoEncontrado = true;
            } catch (FileNotFoundException e) {
                arquivoEncontrado = false;
                System.out.println("Arquivo nao encontrado, digite o caminho do arquivo!");
            }
        }

        //Try para garantir caso o arquivo esteja corrompido informe o erro ao usuario.
        try{
            System.out.printf("Media Geral da Turma: %.2f%n", calcularMedia(file));
        } catch (ArquivoCorrompidoException e) {
            System.out.println("Erro: Existe mais linhas incoerentes do que notas vállidas." + "\n"
                    + "Linhas Invalidas:" + k.getContaLinhasCorrompidas());
        }

    }
}



