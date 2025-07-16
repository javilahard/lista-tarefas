package view;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Contem os dados de uma tarefa que sao mostrados na classe Controlador (X, nome, Ponto)
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class TarefaControlador
{
    private JPanel panNovaTarefa;
    private JButton btnExcluirTarefa;
    private JButton btnConcluirTarefa;
    private JLabel lblNomeTarefa;
    private Controlador c;
    public TarefaControlador(String nome, int valor, Controlador c)
    {
        this.c = c; 
        panNovaTarefa = new JPanel();
        panNovaTarefa.setLayout(new FlowLayout());
        
        btnExcluirTarefa = new JButton("X");
        panNovaTarefa.add(btnExcluirTarefa);
        
        btnExcluirTarefa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExcluirTarefaActionPerformed(evt);
            }
        });
        
        lblNomeTarefa = new JLabel(nome);
        panNovaTarefa.add(lblNomeTarefa);    
        
        String sinal = "";  // Verifica o sinal do valor da tarefa antes de enviar
        if(valor > 0)
        {
            sinal = "+";
        }
        btnConcluirTarefa = new JButton(sinal + Integer.toString(valor));
        panNovaTarefa.add(btnConcluirTarefa);
        
        btnConcluirTarefa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnConcluirTarefaActionPerformed(evt);
            }
        });
    }
    public JPanel getPainel()
    {
        return panNovaTarefa;
    }
    public String getNome()
    {
        return lblNomeTarefa.getText();
    }
    private void btnExcluirTarefaActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        c.excluirTarefa(this);
    }
    private void btnConcluirTarefaActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        c.concluirTarefa(this);
    }
}
