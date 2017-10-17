/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class GA {

    public static double mutacao;
    private int quantidadePopulacao;
    private int geracao;
    public static int qtdeMochila = 0;
    private int qtdeElementos = 0;
    public static int pesos[] = null;
    public static Mochila mochilas[] = null;
    public static int capacidades[] = null;
    private Cromossomo[] populacao, novaPopulacao;
    private double soma ;

    public GA(int populacao, int geracao, double mutacao) {
        this.geracao = geracao;
        this.mutacao = mutacao;
        this.quantidadePopulacao = populacao;
    }

    void executar() {
        long tempo1, tempo2;
        criarPopulacao();
        int cont = 0;
        tempo1 = System.currentTimeMillis();
        tempo2 = tempo1;
        while ((cont < geracao)&&(tempo2-tempo1<60000)) {
            avaliar();
            reproduzir();
            populacao=novaPopulacao;
            ++cont;
            tempo2 = System.currentTimeMillis();
        }
        melhor();
    }
    
    public int selecionar(){
        int k = quantidadePopulacao/5, cont=0;
        int melhorID, tempID;
        long melhorAvaliacao, tempAvaliacao;
        melhorID = (int)Math.random()*quantidadePopulacao;
        melhorAvaliacao = populacao[melhorID].getAvaliacao();
        
        while(cont<k){
            tempID = (int)Math.random()*quantidadePopulacao;
            tempAvaliacao = populacao[melhorID].getAvaliacao();
            
            if(tempAvaliacao>melhorAvaliacao){
                melhorAvaliacao = tempAvaliacao;
                melhorID = tempID;
            }
            ++cont;
        }
        return melhorID;
    }
    
    public int selecionar2(){
        double  valor, temp = 0;
        int cont;

        valor = Math.random()*soma;
        
        for(cont = 0; cont<quantidadePopulacao; ++cont){ 
            temp += populacao[cont].getAvaliacao()+1;
            if(temp>=valor){
                break;
            }
        }
        
        return cont;
       
    }

    public void reproduzir(){
        int pai1, pai2, corte;
        Cromossomo filho;
        novaPopulacao = new Cromossomo[quantidadePopulacao];
        for(int cont=0; cont<quantidadePopulacao; ++cont){
            
            filho = new Cromossomo(qtdeElementos, true);
            pai1 = selecionar2();
            pai2 = selecionar2();

            corte = ((int)Math.random()*(qtdeElementos-1))+1;

            for(int i=0; i < qtdeElementos; ++i){
                if(i<corte){
                    filho.setGene(i, populacao[pai1].getGene(i));
                }else{
                    filho.setGene(i, populacao[pai2].getGene(i));
                }

            }
            filho.mutação();
            novaPopulacao[cont] = filho;
        }
    }
    
    public void avaliar() {
        soma = 0;
        for(int i=0; i<quantidadePopulacao; ++i){
            populacao[i].avaliar();
            soma += (populacao[i].getAvaliacao()+1);
            
        }
    }
    
    public void melhor() {
        long melhor = 0;
        int id = 0;
        for(int i=0; i<quantidadePopulacao; ++i){
            populacao[i].avaliar();
            if(populacao[i].getAvaliacao()>melhor){
                melhor = populacao[i].getAvaliacao();
                id = i;
            }
        }
        
        System.out.println("melhor: "+melhor+" > "+populacao[id].toString());
    }
    

    public void criarPopulacao() {
        int i = 0, j = 0;
        int otimo = 0;
        try {
            FileReader fileReader = new FileReader("entrada.txt");
            Scanner scanner = new Scanner(fileReader);

            Mochila m;
            
            qtdeElementos = scanner.nextInt();
            qtdeMochila = scanner.nextInt();
            
            otimo = scanner.nextInt();

            this.pesos = new int[qtdeElementos];
            this.mochilas = new Mochila[qtdeMochila];

            while ((scanner.hasNext()) && (i < qtdeElementos)) {
                pesos[i] = scanner.nextInt();
                ++i;
            }
            
            
            capacidades = new int[qtdeMochila];
            
            

            j = 0;
            while (j < qtdeMochila) {
                m = new Mochila(qtdeElementos);
                i = 0;
                while ((scanner.hasNext()) && (i < qtdeElementos)) {
                    m.addValor(i, scanner.nextInt());
                    ++i;
                }
                mochilas[j] = m;
                ++j;
            }
            
            j = 0;
            while ((scanner.hasNext()) && (j < qtdeMochila)) {
                capacidades[j] = scanner.nextInt();
                //System.out.println(capacidades[j]);
                ++j;
            }
            
            fileReader.close();
        } catch (FileNotFoundException f) {
            System.out.println("erro");
        } catch (IOException e) {
            System.out.println("erro2");
        }
        i = 0;

        this.populacao = new Cromossomo[quantidadePopulacao];

        while (i < quantidadePopulacao) {
            populacao[i] = new Cromossomo(qtdeElementos);
            //System.out.println(populacao[i].toString());
            ++i;
        }
    }

}
