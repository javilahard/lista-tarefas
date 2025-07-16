package view;

import controller.*;
import armazenamento.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Escreva uma descrição da classe Carregador aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Carregador extends JFrame
{
    private JPanel panGeral;
    private JPanel panL1;
    private JPanel panL2;
    private JPanel panL3;
    private ArrayList<ListaCarregador> panListas;    // Painel para exibir a lista de Listas salvas
    private JLabel lblTitulo;
    private JButton btnMenu;
    /**
     * Construtor para objetos da classe Carregador
     */
    public Carregador()
    {
        iniciarComponentes();
    }
    private void iniciarComponentes()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panGeral = new JPanel();
        panGeral.setLayout(new BoxLayout(panGeral, BoxLayout.Y_AXIS));
        
        panListas = new ArrayList<>();
        
        panL1 = new JPanel();
        panL1.setLayout(new FlowLayout());
        
        lblTitulo = new JLabel("Listas Salvas");
        panL1.add(lblTitulo);
        
        panL2 = new JPanel();
        panL2.setLayout(new BoxLayout(panL2, BoxLayout.Y_AXIS));
        
        panL3 = new JPanel();
        panL3.setLayout(new FlowLayout());
        
        btnMenu = new JButton("Menu");
        panL3.add(btnMenu);
        
        btnMenu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMenuActionPerformed(evt);
            }
        });
        
        adicionarListas();
        atualizarPanL2();
        
        panGeral.add(panL1);
        panGeral.add(panL2);
        panGeral.add(panL3);
        
        add(panGeral);
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);
        setVisible(true);
    }
    private void adicionarListas()  // Adiciona as listas salvas para mostrar ao usuario
    {
        List<String> listas = new ArrayList<>();
        listas = PersistenciaTxt.listarNomesDeListas();
        
        for(String lista : listas)  // Percorre a lista com as listas de tarefas
        {
            ListaCarregador l = new ListaCarregador(lista, this);
            panListas.add(l);
        }
    }
    public void atualizarPanL2()   // Mostra no painel as listas salvas
    {
        for(int i = 0; i < panListas.size(); i++)
        {
            panL2.add(panListas.get(i).getPainel());
        }
        setVisible(false);
        setLocationRelativeTo(null); // Centraliza a janela
        pack();
        setVisible(true);
    }
    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        Menu menu = new Menu();
        dispose();
    }
    public void excluirLista(ListaCarregador l)
    {
        String n = l.getNome(); // Pega o nome da lista
        PersistenciaTxt.excluirLista(n);
        for(int i = 0; i < panListas.size(); i++)  
        {
            panL2.remove(panListas.get(i).getPainel());    // Remove todas as listas do painel
        }
        panListas.remove(l);   // Remove a lista especifica a ser excluida
        atualizarPanL2();   // Atualiza corretamente
    }
    public void acessarLista(ListaCarregador l)
    {
        String n = l.getNome();
        UsuarioController uc = PersistenciaTxt.carregarDados(n);
        Controlador c = new Controlador(uc);
        dispose();
    }
}
