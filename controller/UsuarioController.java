package controller;
import model.*;
import java.util.*;
import armazenamento.*;
import java.io.File;
public class UsuarioController
{
    private Usuario usuario;
    /**
     * Construtor para um usuario carregado de um arquivo
     */
    public UsuarioController(Usuario usuario)   
    {
        this.usuario = usuario;
    }
    /**
     * Construtor para a um usuario inicial
     */
    public UsuarioController(String nome, int m)  
    {
        Meta meta = new Meta(m);
        usuario = new Usuario(nome, meta);
    }
    /**
     * @return O nome do usuario
     */
    public String getNome()
    {
        return usuario.getNome();
    }
    /**
     * Altera o nome do usuario
     */
    public void setNome(String n)
    {
        usuario.setNome(n);
    }
    /**
     * @return A meta do usuario
     */
    public int getMeta()
    {
        return usuario.getMeta().getValor();
    } 
    /**
     * Altera a meta do usuario
     */
    public void setMeta(int m)
    {
        usuario.alterarMeta(m);
    }
    public List<Tarefa> getTarefas()
    {
        return usuario.getGerenciador().getTarefas();
    }
    public int getPontuacaoAtual()
    {
        return usuario.getGerenciador().getPontuacaoAtual();
    }
    public void setPontuacaoAtual(int p)
    {
        usuario.getGerenciador().setPontuacaoAtual(p);
    }
    public void novaTarefa(String nome, int valor)
    {
        Tarefa t = new Tarefa(nome, valor);
        usuario.getGerenciador().adicionarTarefa(t);
    }
    public void removerTarefa(String nome)
    {
        usuario.getGerenciador().excluirTarefa(nome);
    }
    public void concluirTarefa(String nome)
    {
        usuario.getGerenciador().concluirTarefa(nome);
    }
    public void salvar()
    {
        PersistenciaTxt.salvarDados(usuario.getNome(), usuario.getMeta().getValor(), getPontuacaoAtual(), getTarefas());
    }
}
