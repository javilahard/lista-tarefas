package view;

import controller.*;
import armazenamento.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Contem a acao dos botoes de uma lista na classe carregador
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class ListaCarregador
{
    private JPanel panLista;
    private JButton btnExcluirLista;
    private JButton btnAcessarLista;
    private String nomeLista;
    private Carregador c;
    public ListaCarregador(String nome, Carregador c)
    {
        this.c = c;
        panLista = new JPanel();
        panLista.setLayout(new FlowLayout());
        
        btnExcluirLista = new JButton("X");
        panLista.add(btnExcluirLista);
        
        btnExcluirLista.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExcluirListaActionPerformed(evt);
            }
        });
        
        nomeLista = nome;
        btnAcessarLista = new JButton(nomeLista);
        panLista.add(btnAcessarLista);
        
        btnAcessarLista.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAcessarListaActionPerformed(evt);
            }
        });
    }
    private void btnExcluirListaActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        c.excluirLista(this);
    }
    private void btnAcessarListaActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        c.acessarLista(this);
    }
    public JPanel getPainel()
    {
        return panLista;
    }
    public String getNome()
    {
        return nomeLista;
    }
}
