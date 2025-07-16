package view;

import controller.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Escreva uma descrição da classe AdicionadorDeTarefas aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class AdicionadorDeTarefas extends JFrame
{
    private JPanel panGeral;
    private JPanel panL1;
    private JPanel panL2;
    private JPanel panL3;
    private JTextField txtNome;
    private JComboBox boxSinal;
    private JTextField txtValor;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private Controlador frameControlador;
    public AdicionadorDeTarefas(Controlador c)
    {
        frameControlador = c;
        iniciarComponentes();
    }
    private void iniciarComponentes()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        panGeral = new JPanel();
        panGeral.setLayout(new BoxLayout(panGeral, BoxLayout.Y_AXIS));
        
        panL1 = new JPanel();
        panL1.setLayout(new FlowLayout());
        txtNome = new JTextField("Nome da Tarefa");
        panL1.add(txtNome);
        
        panL2 = new JPanel();
        panL2.setLayout(new FlowLayout());
        boxSinal = new JComboBox();
        boxSinal.addItem("+");
        boxSinal.addItem("-");
        panL2.add(boxSinal);
        txtValor = new JTextField("Valor");
        panL2.add(txtValor);
        
        panL3 = new JPanel();
        panL3.setLayout(new FlowLayout());
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setAlignmentX(LEFT_ALIGNMENT);
        panL3.add(btnCancelar);
        
        btnCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }
        });
        
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setAlignmentX(RIGHT_ALIGNMENT);
        panL3.add(btnConfirmar);
        
        btnConfirmar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnConfirmarActionPerformed(evt);
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
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt)
    {                        
        dispose();
    }
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt)
    {        
        if(verificarCampos())
        {
            int valor = Integer.parseInt(txtValor.getText());
            if(boxSinal.getSelectedItem().equals("-"))
            {
                valor = -valor;
            }
            frameControlador.getUsuario().novaTarefa(txtNome.getText(), valor); // Adiciona a tarefa a lista do usuario
            frameControlador.atualizarTarefas(txtNome.getText(), valor);
            dispose();    
        }
    }
    private boolean verificarCampos()
    {
        boolean verif = true;
    
        if (txtNome.getText().isBlank())
        {
            JOptionPane.showMessageDialog(this, "Nome de Tarefa inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
            verif = false;
        }
        else if(tarefaJaListada(txtNome.getText()))
        {
            JOptionPane.showMessageDialog(this, "Tarefa ja listada anteriormente", "ERRO", JOptionPane.ERROR_MESSAGE);
            verif = false;
        }
        else if (!Util.ehInteiro(txtValor.getText()))
        {
            JOptionPane.showMessageDialog(this, "Valor precisa ser um número inteiro", "ERRO", JOptionPane.ERROR_MESSAGE);
            verif = false;
        }
    
        return verif;
    }
    private boolean tarefaJaListada(String nomeTarefa)
    {
        if(frameControlador.tarefaJaCadastrada(nomeTarefa))
        {
            return true;
        }
        return false;
    }
}
