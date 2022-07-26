package oi.avaliadorcredito.ex;

import lombok.Getter;

public class ErroComunicationMicroserviceExeption extends Exception{

    @Getter
    private Integer status;

    public ErroComunicationMicroserviceExeption(String msg,Integer status){
        super(msg);
        this.status = status;
    }
}
