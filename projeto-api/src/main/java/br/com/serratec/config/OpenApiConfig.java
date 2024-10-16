package br.com.serratec.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;

	@Bean
	public OpenAPI myOpenApi() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("URL da área de desenvolvimento");
		
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("URL da área de Produção");
		
		Contact contact = new Contact();
		contact.setName("Roni Schanuel");
		contact.setEmail("roni_inf@hotmail.com");
		contact.setUrl("https://www.serratec.com.br");
		
		License apacheLicense = new License().name("Apache")
								.url("https://www.apache.org/licenses/LICENSE-2.0");
		
		Info info = new Info().title("API DO SERRATEC").version("1.0").contact(contact)
				.description("API PARA ESTUDOS").termsOfService("https://www.serratec.com.br")
				.license(apacheLicense);
		
		return new OpenAPI().info(info).servers(List.of(devServer,prodServer));
		
	}
	
}
