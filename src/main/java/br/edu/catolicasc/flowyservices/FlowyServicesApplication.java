package br.edu.catolicasc.flowyservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import br.edu.catolicasc.flowyservices.config.CorsConfig;
import io.github.cdimascio.dotenv.Dotenv;

@Import(CorsConfig.class)
@SpringBootApplication
public class FlowyServicesApplication {

    public static void main(String[] args) {
        Dotenv.load();
        SpringApplication.run(FlowyServicesApplication.class, args);
    }

}
