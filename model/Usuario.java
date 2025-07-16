package model;

import controller.GerenciadorDeTarefas;

/**
 * Escreva uma descrição da classe Rotina aqui.
 * 
 * @author Joao Avila
 * @version 16/04/2025
 */
public class Usuario
{
    private String nome;
    private GerenciadorDeTarefas tarefas;
    private Meta meta;
    /**
     * Construtor para objetos da classe Rotina
     */
    public Usuario(String nome, Meta meta)
    {
        this.nome = nome;
        this.meta = meta;
        tarefas = new GerenciadorDeTarefas();
    }
    public Usuario(String nome, Meta meta, int p)
    {
        this.nome = nome;
        this.meta = meta;
        tarefas = new GerenciadorDeTarefas(p);
    }
    
    public void alterarMeta(int m)
    {
        meta.setValor(m);
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public void setNome(String n)
    {
        nome = n;
    }
    
    public Meta getMeta()
    {
        return meta;
    }
    
    public GerenciadorDeTarefas getGerenciador()
    {
        return tarefas;
    }
}
