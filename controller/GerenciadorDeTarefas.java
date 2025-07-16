package controller;

import java.util.*;
import model.Tarefa;

/**
 * Escreva uma descrição da classe GerenciadorDeTarefas aqui.
 *    
 * @author Joao Avila
 * @version 16/04/2025
 */
public class GerenciadorDeTarefas
{
    private List<Tarefa> tarefas;
    private int pontuacaoAtual;
    /**
     * Construtor para novos usuarios (pontuacao atual = 0)
     */
    public GerenciadorDeTarefas()
    {
        setPontuacaoAtual(0);
        tarefas = new ArrayList<Tarefa>();
    }
    /**
     * Construtor com a definicao da pontuacao atual do usuario
     */
    public GerenciadorDeTarefas(int pont)
    {
        setPontuacaoAtual(pont);
        tarefas = new ArrayList<Tarefa>();
    }
    /**
     * Altera a pontuacao do usuario
     */
    public void setPontuacaoAtual(int p)
    {
        pontuacaoAtual = p;
    }
    /**
     * Adiciona uma tarefa a lista de tarefas do usuario
     */
    public void adicionarTarefa(Tarefa t)
    {
        tarefas.add(t);
    }
    /**
     * Remove uma tarefa pelo nome da lista de tarefas do usuario
     */
    public void excluirTarefa(String n)
    {
        for(int i = 0; i < tarefas.size(); i++)
        {
            if(tarefas.get(i).getNome().equals(n))
            {
                tarefas.remove(i);    
            }
        }
    }
    /**
     * Soma a pontuacao da tarefa correspondente a pontuacao atual do usuario
     */
    public void concluirTarefa(String n)
    {
        for(int i = 0; i < tarefas.size(); i++)
        {
            if(tarefas.get(i).getNome().equals(n))
            {
                int v = pontuacaoAtual + tarefas.get(i).getValor();
                setPontuacaoAtual(v);
            }
        }
    }
    /**
     * @return A pontuacao atual do usuario
     */
    public int getPontuacaoAtual()
    {
        return pontuacaoAtual;
    }
    /**
     * @return A lista de tarefas do usuario
     */
    public List<Tarefa> getTarefas()
    {
        return tarefas;
    }
}
