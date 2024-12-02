package br.com.unisales.Cliente;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas
                .allowedOrigins("http://localhost:8090") // Permitir solicitações da origem especificada
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permitir todos os cabeçalhos
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") // Cabeçalhos expostos
                .allowCredentials(true); // Permitir credenciais, se necessário
    }
}

