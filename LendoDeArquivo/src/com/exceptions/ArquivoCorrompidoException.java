package com.exceptions;

public class ArquivoCorrompidoException extends Exception {
    private int contaLinhasCorrompidas;

    public int getContaLinhasCorrompidas() {
        return contaLinhasCorrompidas;
    }

    public void setContaLinhasCorrompidas(int contaLinhasCorrompidas) {
        this.contaLinhasCorrompidas = contaLinhasCorrompidas;
    }

}
