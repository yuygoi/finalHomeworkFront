package com.jerry.gamemarket;

import com.jerry.gamemarket.dto.SearchOrderDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import java.util.Random;

@SpringBootApplication
@EnableSwagger2
public class GamemarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamemarketApplication.class, args);
	}
	@Bean
	public Random random(){
		return  new Random();
	}
	@Bean
	public JPAQueryFactory queryFactory(EntityManager entityManager){
		return new JPAQueryFactory(entityManager);
	}
	@Bean
	public Docket createRestApi(){
		// System.out.println(this.getClass().getSimpleName().split("Application")[0].toLowerCase());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jerry.gamemarket.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("Spring Boot中使用Swagger构建Rest Api version Member")
				.version("1.0").build();
	}
}