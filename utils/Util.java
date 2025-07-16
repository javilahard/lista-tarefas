package utils;

/**
 * Escreva uma descrição da classe Util aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Util
{
    public static boolean ehInteiro(String texto)
    {
        try
        {
            Integer.parseInt(texto);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
}
