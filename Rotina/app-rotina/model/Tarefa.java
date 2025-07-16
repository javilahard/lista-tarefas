package model;

/**
 * Classe para descrever a tarefa a ser concluida
 * 
 * @author Joao Avila 
 * @version 16/04/2025
 */
public class Tarefa
{
    private String nome;
    private int valor;
    /**
     * Construtor para objetos da classe Tarefa
     */
    public Tarefa(String nome, int valor)
    {
        this.nome = nome;
        this.valor = valor;
    }
    
    /**
     * Retorna o nome definido para a tarefa
     *
     * @return nome da tarefa
     */
    public String getNome()
    {
        return nome;
    }
    
    /**
     * Altera o nome da tarefa
     *
     * @param nome para modificacao
     */
    public void setNome(String s)
    {
        nome = s;
    }
    
    /**
     * Retorna o valor correspondente da tarefa
     *
     * @return valor da tarefa
     */
    public int getValor()
    {
        return valor;
    }
    
    /**
     * Altera o valor de uma tarefa
     *
     * @param novo valor
     */
    public void setValor(int val)
    {
        valor = val;
    }
}
