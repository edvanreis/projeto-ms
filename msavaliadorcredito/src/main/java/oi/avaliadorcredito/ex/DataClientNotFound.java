package oi.avaliadorcredito.ex;

public class DataClientNotFound extends Exception{

    public DataClientNotFound(){
        super("Dados dos client não encontrado para cpf informado!");
    }
}
