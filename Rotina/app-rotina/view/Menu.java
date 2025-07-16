package view;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Escreva uma descrição da classe Menu aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Menu extends JFrame
{
    private JPanel panGeral;
    private JPanel panL1;
    private JPanel panL2;
    private JPanel panL3;
    private JLabel lblTitulo;
    private JButton btnNovaLista;
    private JButton btnCarregarLista;
    private Editor frameEditor;
    private Carregador frameCarregador;
    public Menu()
    {
        iniciarComponentes();
    }
    private void iniciarComponentes()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panGeral = new JPanel();
        panGeral.setLayout(new BoxLayout(panGeral, BoxLayout.Y_AXIS));
        
        panL1 = new JPanel();
        panL1.setLayout(new FlowLayout());
        lblTitulo = new JLabel("Super Controlador de Tarefas");
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        panL1.add(lblTitulo);
        
        panL2 = new JPanel();
        panL2.setLayout(new FlowLayout());
        btnNovaLista = new JButton("Nova Lista");
        btnNovaLista.setAlignmentX(CENTER_ALIGNMENT);
        panL2.add(btnNovaLista);
        
        btnNovaLista.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnNovaListaActionPerformed(evt);
            }
        });
        
        panL3 = new JPanel();
        panL3.setLayout(new FlowLayout());
        btnCarregarLista = new JButton("Carregar Lista");
        btnCarregarLista.setAlignmentX(CENTER_ALIGNMENT);
        panL3.add(btnCarregarLista);
        
        btnCarregarLista.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCarregarListaActionPerformed(evt);
            }
        });
        
        panGeral.add(panL1);
        panGeral.add(panL2);
        panGeral.add(panL3);
        add(panGeral);
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);
        setVisible(true);
    }
    private void btnNovaListaActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        frameEditor = new Editor();
        setVisible(false);
    }
    private void btnCarregarListaActionPerformed(java.awt.event.ActionEvent evt)
    {                                           
        frameCarregador = new Carregador();
        setVisible(false);
    }
}
