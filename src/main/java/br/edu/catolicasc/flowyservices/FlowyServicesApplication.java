package br.edu.catolicasc.flowyservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import br.edu.catolicasc.flowyservices.config.CorsConfig;

@Import(CorsConfig.class)
@SpringBootApplication
public class FlowyServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowyServicesApplication.class, args);
    }

}
