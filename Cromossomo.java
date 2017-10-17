package mochila;



public class Cromossomo {

    private byte[] genes;
    private long avaliacao;
    private int tElementos;

    public Cromossomo(int elementos) {
        genes = new byte[elementos];
        this.avaliacao = 0;
        this.tElementos = elementos;

        gerar();
    }

    public Cromossomo(int elementos, boolean t) {
        genes = new byte[elementos];
        this.avaliacao = 0;
        this.tElementos = elementos;
    }

    public void avaliar() {
        long valor = 0, pesoParcial = 0;
        long parcial[] = new long[GA.qtdeMochila];
        
        for(int r = 0; r<GA.qtdeMochila; ++r)
            parcial[r] = 0;
        
        for(int i=0; i<tElementos ; ++i){
            if(genes[i]==1){
                pesoParcial +=  GA.pesos[i] ;
                for(int j = 0; j < GA.qtdeMochila; ++j){
                    parcial[j] += GA.mochilas[j].getValor(i);
                    
                }
            }
        }
        
        valor = pesoParcial;
        for(int r = 0; r<GA.qtdeMochila; ++r){
            if(parcial[r]>GA.capacidades[r]){
                valor = 0;
                break;
            }
        }
        
        avaliacao = valor;
        //System.out.println(" avaliacao"+avaliacao+" "+toString());
    }

    public void gerar() {

        for (int i = 0; i < tElementos; ++i) {
            if(Math.random()<0.5)
                genes[i] = 0;
            else
               genes[i] = 1; 
        }
        //genes[(int) (Math.random() * tElementos)] = 1;

    }

    public void mutação() {
        for (int i = 0; i < tElementos; ++i) {
            if (Math.random() < GA.mutacao) {
                if (genes[i] == 1) {
                    genes[i] = 0;
                } else {
                    genes[i] = 1;
                }
            }
        }
    }

    public long getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(long avaliacao) {
        this.avaliacao = avaliacao;
    }

    public byte getGene(int elementos) {
        return genes[elementos];
    }

    public void setGene(int elementos, byte valor) {
        genes[elementos] = valor;
    }

    public String toString() {
        String elemento = new String();

        for (int i = 0; i < tElementos; ++i) {
            elemento = elemento + genes[i];
        }
        return elemento;
    }
}
