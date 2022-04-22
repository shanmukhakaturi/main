package com.ros.inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.ros.inventory.mapper.purchaseorder.CreateNewPurchaseOrderMapper;
import com.ros.inventory.mapper.purchaseorder.CreateNewPurchaseOrderMapperImpl;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Value(value = "${swagger.url}")
	public String url;

	@Bean
	public OpenAPI customOpenAPI() {
		Server server = new Server();
		List<Server> servers = new ArrayList<>();
		server.setUrl(url);
		servers.add(server);
		OpenAPI openAPI = new OpenAPI();
		openAPI.setServers(servers);
		return openAPI;

	}
	
	@Bean
	public CreateNewPurchaseOrderMapper getCreateNewPurchaseOrderMapper() {
		return new CreateNewPurchaseOrderMapperImpl();
	}
	

	
}
