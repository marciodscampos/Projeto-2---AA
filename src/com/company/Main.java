package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ReadFile trainingFile = new ReadFile("input/treino.txt");
        ReadFile testFile = new ReadFile("input/teste.txt");
        int hits = 0;
        int total = 0;

        // Guarda as series temporais do arquivo de treino em uma lista
        ArrayList<TimeSerie> training = new ArrayList<>();
        while(trainingFile.hasNextLine()) {
            training.add(trainingFile.nextSerie());
        }
        training.trimToSize();

        // Processa cada linha do arquivo de teste e contabiliza um acerto ou erro
        while(testFile.hasNextLine()) {
            TimeSerie serieT = testFile.nextSerie();
            total++;
            for (int i = 0; i < training.size(); i++) {
                // Calcula a dtw e faz a classificacao
                serieT.dtw(training.get(i));
            }
            // Verifica se a classificacao foi correta e contabiliza os acertos
            if (serieT.isClassificationRight())
                hits++;
        }

        // imprime o resultado
        System.out.print("Total de testes:" + total + "\nAcertos:" + hits + "\nTaxa de acertos:" + (hits*1.0/total*1.0));
    }
}
