package se.urmo.spring.rest;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;

@SpringBootApplication
public class ResttestApplication {
    private final Faker faker = new Faker();

    public static String CURIE_NAMESPACE = "restbucks";

    @Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider(CURIE_NAMESPACE, new UriTemplate("/docs/{rel}.html"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ResttestApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDb(PieRepository repository) {
        return (args) -> {
            repository.deleteAll();
            //Insert some random pies
            for (int i = 0; i < 20; i++) {
                repository.save(new Pie(faker.lorem().word(), faker.lorem().sentence()));
            }
        };
    }
}
