
package mochila;


public class Mochila {
    private int valor[] ;
    
    private double capacidade;
    private double avaliacao;
    private int quantidade;
    
    public Mochila(int tamanho){
        this.valor = new int[tamanho];
        this.capacidade = 0;
        this.avaliacao = 0;
        this.quantidade = 0;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void addValor(int id, int peso){
        valor[id] = peso;
    }
    public int getValor(int id) {
        return valor[id] ;
    }
}
