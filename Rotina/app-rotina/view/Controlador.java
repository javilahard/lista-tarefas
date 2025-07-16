package view;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Escreva uma descrição da classe Controlador aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Controlador extends JFrame
{
    private UsuarioController usuario;   // Usuario atual para receber os dados apresentados
    private JPanel panGeral;   // Painel para a janela geral
    private JPanel panL1;
    private JPanel panL2;  // Painel que contem todas as tarefas do usuario
    private ArrayList<TarefaControlador> panTarefas;
    private JPanel panL3;
    private JPanel panL4;    // Painel para menu, meta e editar meta
    private JLabel lblNome;
    private JLabel lblPontuacao;
    private JLabel lblMeta;
    private JButton btnAdicionarTarefa;
    private JButton btnMenu;
    private JButton btnEditar;
    private JButton btnSalvar;
    private JButton btnReiniciarPontuacao;
    /**
     * Construtor para objetos da classe Controlador
     */
    public Controlador(UsuarioController usuario)
    {
        this.usuario = usuario;
        iniciarComponentes();    
    }
    private void iniciarComponentes()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panGeral = new JPanel();
        panGeral.setLayout(new BoxLayout(panGeral, BoxLayout.Y_AXIS));
        
        panL1 = new JPanel();
        panL1.setLayout(new FlowLayout());
        
        panL2 = new JPanel();
        panL2.setLayout(new BoxLayout(panL2, BoxLayout.Y_AXIS));
        
        panL3 = new JPanel();
        panL3.setLayout(new FlowLayout());
        
        panL4 = new JPanel();
        panL4.setLayout(new FlowLayout());
        
        panTarefas = new ArrayList<>();
        
        lblNome = new JLabel(usuario.getNome());
        panL1.add(lblNome);
        
        btnAdicionarTarefa = new JButton("Adicionar Tarefa");
        btnAdicionarTarefa.setAlignmentX(CENTER_ALIGNMENT);
        panL2.add(btnAdicionarTarefa);
        
        btnAdicionarTarefa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAdicionarTarefaActionPerformed(evt);
            }
        });
        
        btnReiniciarPontuacao = new JButton("Reiniciar");
        panL3.add(btnReiniciarPontuacao);
        
        btnReiniciarPontuacao.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReiniciarPontuacaoActionPerformed(evt);
            }
        });
        
        lblPontuacao = new JLabel("Pontuacao: " + usuario.getPontuacaoAtual());
        panL3.add(lblPontuacao);
        
        lblMeta = new JLabel("Meta: " + usuario.getMeta());
        panL3.add(lblMeta);
        
        btnMenu = new JButton("Menu");
        panL4.add(btnMenu);
        
        btnMenu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMenuActionPerformed(evt);
            }
        });
        
        btnEditar = new JButton("Editar");
        panL4.add(btnEditar);
        
        btnEditar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditarActionPerformed(evt);
            }
        });
        
        btnSalvar = new JButton("Salvar");
        panL4.add(btnSalvar);
        
        btnSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSalvarActionPerformed(evt);
            }
        });
        
        panGeral.add(panL1);
        panGeral.add(panL2);
        panGeral.add(panL3);
        panGeral.add(panL4);
        
        add(panGeral);
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);
        setVisible(true);
        verificarTarefas();
        atualizarPontuacao();
    }
    private void btnAdicionarTarefaActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        AdicionadorDeTarefas frameNovaTarefa = new AdicionadorDeTarefas(this);
    }
    private void btnReiniciarPontuacaoActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        usuario.setPontuacaoAtual(0);
        lblPontuacao.setText("Pontuacao: " + Integer.toString(usuario.getPontuacaoAtual()));
    }
    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        Menu menu = new Menu();
        dispose();
    }
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        Editor e = new Editor(this);
        setVisible(false);
    }
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        usuario.salvar();
    }
    public UsuarioController getUsuario()
    {
        return usuario;
    }
    public void atualizarValores()
    {
        lblNome.setText(usuario.getNome());
        lblMeta.setText("Meta: " + Integer.toString(usuario.getMeta()));
        pack();
    }
    public void atualizarTarefas(String n, int v)
    {
        TarefaControlador panNovaTarefa = new TarefaControlador(n, v, this);
        panTarefas.add(panNovaTarefa);
        atualizarPanL2(); // Atualiza todos os componentes da janela do controlador
    }
    private void atualizarPanL2()
    {
        for(int i = 0; i < panTarefas.size(); i++)
        {
            panL2.add(panTarefas.get(i).getPainel());
        }
        setVisible(false);
        setVisible(true);
        pack();
    }
    public void excluirTarefa(TarefaControlador t)
    {
        String n = t.getNome(); // Pega o nome da tarefa
        usuario.removerTarefa(n);   // Remove a tarefa da lista do usuario
        for(int i = 0; i < panTarefas.size(); i++)  
        {
            panL2.remove(panTarefas.get(i).getPainel());    // Remove todas as tarefas do painel
        }
        panTarefas.remove(t);   // Remove a tarefa especifica a ser excluida
        atualizarPanL2();   // Atualiza corretamente
    }
    public void concluirTarefa(TarefaControlador t)
    {
        String n = t.getNome();
        usuario.concluirTarefa(n);
        atualizarPontuacao();
    }
    public void atualizarPontuacao()
    {
        lblPontuacao.setText("Pontuacao: " + usuario.getPontuacaoAtual());
        pack();
    }
    private void verificarTarefas()
    {
        if(!usuario.getTarefas().isEmpty())
        {
            for(int i = 0; i < usuario.getTarefas().size(); i++)
            {
                atualizarTarefas(usuario.getTarefas().get(i).getNome(), usuario.getTarefas().get(i).getValor());
            }    
        }
    }
    public boolean tarefaJaCadastrada(String nome)    // Verifica se a tarefa ja foi listada
    {
        for(int i = 0; i < usuario.getTarefas().size(); i++)    // Percorre as tarefas
        {
            if(usuario.getTarefas().get(i).getNome().equals(nome))
            {
                return true;
            }
        }
        return false;
    }
}
