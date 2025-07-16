package view;

import controller.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Escreva uma descrição da classe Editor aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Editor extends JFrame
{
    private JPanel panGeral;
    private JPanel panL1;
    private JPanel panL2;
    private JPanel panL3;
    private JTextField txtNome;
    private JLabel lblMeta;
    private JTextField txtMeta;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private Controlador frameControlador;   // Copia da janela controlador das tarefas que chamou para editar
    private UsuarioController usuario;  // Dados do usuario para mostrar no controlador
    public Editor() // Contrutor para a chamada a partir do menu
    {
        iniciarComponentes();    
    }
    public Editor(Controlador c)    // Construtor para chamada a partir do controlador
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
        
        if(frameControlador != null)
        {
            txtNome = new JTextField(frameControlador.getUsuario().getNome());    
        }
        else
        {
            txtNome = new JTextField("Nome da Lista");
        }
        //txtNome.setAlignmentX(CENTER_ALIGNMENT);
        panL1.add(txtNome);
        
        panL2 = new JPanel();
        panL2.setLayout(new FlowLayout());
        
        lblMeta = new JLabel("Meta:");
        panL2.add(lblMeta);
        
        if(frameControlador != null)
        {
            txtMeta = new JTextField(Integer.toString(frameControlador.getUsuario().getMeta()));    
        }
        else
        {
            txtMeta = new JTextField("Numero");
        }
        panL2.add(txtMeta);
        
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
        if(frameControlador == null)
        {
            Menu menu = new Menu();
        }
        else
        {
            frameControlador.setVisible(true);
        }
        dispose();
    }
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt)
    {                     
        if(verificarCampos())
        {
            if(frameControlador == null)
            {
                usuario = new UsuarioController(txtNome.getText(), Integer.parseInt(txtMeta.getText())); 
                frameControlador = new Controlador(usuario);
            }
            else
            {
                frameControlador.getUsuario().setNome(txtNome.getText());
                frameControlador.getUsuario().setMeta(Integer.parseInt(txtMeta.getText()));
                frameControlador.atualizarValores();
            }
            setVisible(false);
            frameControlador.setVisible(true);    
            }
            //dispose();
    }
    private boolean verificarCampos()
    {
        boolean verif = true;
    
        if (txtNome.getText().isBlank())
        {
            JOptionPane.showMessageDialog(this, "Nome de Lista inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
            verif = false;
        }
        else if (!Util.ehInteiro(txtMeta.getText()))
        {
            JOptionPane.showMessageDialog(this, "Meta precisa ser um número inteiro", "ERRO", JOptionPane.ERROR_MESSAGE);
            verif = false;
        }
    
        return verif;
    }
}
