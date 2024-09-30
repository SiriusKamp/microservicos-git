package br.com.unisales.microservicologin.util;

import java.nio.charset.Charset;
import java.util.UUID;

public class Token {
    public UUID generationToken() {
        String chave = "Sistem de micro servico login";
        Charset charset = Charset.forName("ASCII");
        byte[] byteArrray = chave.getBytes(charset);
        return UUID.nameUUIDFromBytes(byteArrray);
    }
}
