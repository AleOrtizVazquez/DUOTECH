package com.duotech.config;

import com.duotech.entity.Product;
import com.duotech.repository.ProductRepository;
import com.duotech.entity.Announcement;
import com.duotech.repository.AnnouncementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(ProductRepository products, AnnouncementRepository announcements) {
        return args -> {
            if (products.count() == 0) {
                products.save(new Product("Servicio empresarial", "Servicios",
                    "Solución adaptable para cualquier negocio.", 499.00));
                products.save(new Product("Paquete básico", "Paquetes",
                    "Configuración inicial de la plataforma.", 799.00));
                products.save(new Product("Producto demo", "Catálogo",
                    "Elemento de ejemplo para probar el catálogo.", 129.00));
            }
            if (announcements.count() == 0) {
                announcements.save(new Announcement("Bienvenido a DúoTech",
                    "Esta es la sección de comunicación de la empresa."));
                announcements.save(new Announcement("Nueva promoción",
                    "Consulta con la empresa las promociones disponibles."));
            }
        };
    }
}
