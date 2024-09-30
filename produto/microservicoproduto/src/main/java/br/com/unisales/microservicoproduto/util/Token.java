package br.com.unisales.microservicoproduto.util;

import java.nio.charset.Charset;
import java.util.UUID;

public class Token {
    public boolean compararToken(UUID token) {
        String chave = "Sistem de micro servico login";
        Charset charset = Charset.forName("ASCII");
        byte[] byteArrray = chave.getBytes(charset);
        UUID uuid = UUID.nameUUIDFromBytes(byteArrray);
        if (uuid.compareTo(token) == 0)
            return true;
        return false;
    }

    public void generationToken() {
        String chave = "Sistem de micro servico login";
        Charset charset = Charset.forName("ASCII");
        byte[] byteArrray = chave.getBytes(charset);
        UUID uuid = UUID.nameUUIDFromBytes(byteArrray);
        System.out.println("Tokne: " + uuid);
    }
}
