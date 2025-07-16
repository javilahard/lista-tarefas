package armazenamento;

import model.*;
import controller.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;

public class PersistenciaTxt
{
    public static void salvarDados(String nomeLista, int meta, int pontuacaoAtual, List<Tarefa> tarefas)
    {
        String nomeDaPasta = "listas";
        String nomeArquivo = nomeLista + ".txt";
        String caminhoCompleto = nomeDaPasta + File.separator + nomeArquivo;

        // Criar a pasta "listas" se não existir
        File pasta = new File(nomeDaPasta);
        if (!pasta.exists())
        {
            pasta.mkdirs();
        }

        File arquivo = new File(caminhoCompleto);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo)))
        {
            writer.write(nomeLista);
            writer.newLine();
            writer.write(String.valueOf(meta));
            writer.newLine();
            writer.write(String.valueOf(pontuacaoAtual));
            writer.newLine();

            for (Tarefa tarefa : tarefas)
            {
                writer.write(tarefa.getNome() + ";" + tarefa.getValor());
                writer.newLine();
            }

            JOptionPane.showMessageDialog(null, "✅ Dados de '" + nomeArquivo + "' salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "❌ Erro ao salvar dados no arquivo '" + nomeArquivo + "': " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static List<String> listarNomesDeListas()
    {
        List<String> nomesDeListas = new ArrayList<>();
        String nomeDaPasta = "listas";
        File pasta = new File(nomeDaPasta);

        // Verifica se a pasta existe e é um diretório
        if (pasta.exists() && pasta.isDirectory())
        {
            File[] arquivos = pasta.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".txt"));

            if (arquivos != null)
            {
                for (File arquivo : arquivos)
                {
                    // Remove a extensão ".txt" do nome do arquivo
                    String nomeLista = arquivo.getName().replaceFirst("[.][tT][xX][tT]$", "");
                    nomesDeListas.add(nomeLista);
                }
            }
        }

        return nomesDeListas;
    }
    public static void excluirLista(String nomeLista)
    {
        String nomeDaPasta = "listas";
        String nomeArquivo = nomeLista + ".txt";
        File arquivo = new File(nomeDaPasta + File.separator + nomeArquivo);
        if (arquivo.exists())
        {
            if (arquivo.delete())
            {
                JOptionPane.showMessageDialog(null, "Lista '" + nomeLista + "' excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Não foi possível excluir a lista '" + nomeLista + "'.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "A lista '" + nomeLista + "' não foi encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static UsuarioController carregarDados(String nomeLista)
    {
        String nomeDaPasta = "listas";
        String nomeArquivo = nomeDaPasta + File.separator + nomeLista + ".txt";
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists())
        {
            System.err.println("Arquivo '" + nomeArquivo + "' não encontrado.");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo)))
        {
            String nome = reader.readLine();
            int metaValor = Integer.parseInt(reader.readLine());
            int pontuacaoAtual = Integer.parseInt(reader.readLine());

            Meta meta = new Meta(metaValor);
            Usuario u = new Usuario(nome, meta, pontuacaoAtual);

            String linha;
            while ((linha = reader.readLine()) != null)
            {
                String[] partes = linha.split(";");
                if (partes.length == 2)
                {
                    String nomeTarefa = partes[0];
                    int valor = Integer.parseInt(partes[1]);
                    u.getGerenciador().adicionarTarefa(new Tarefa(nomeTarefa, valor));
                }
            }
            
            UsuarioController uc = new UsuarioController(u);
            return uc;
        }
        catch (IOException | NumberFormatException e)
        {
            System.err.println("❌ Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }
}
