package model;

/**
 * Classe para descrever a meta definida pelo usuario
 * 
 * @author Joao Avila
 * @version 16/04/2025
 */
public class Meta
{
    private int valor;
    /**
     * Construtor para objetos da classe Meta
     */
    public Meta(int valor)
    {
        this.valor = valor;    
    }

    public void setValor(int val)
    {
        valor = val;
    }
    
    public int getValor()
    {
        return valor;
    }
}
