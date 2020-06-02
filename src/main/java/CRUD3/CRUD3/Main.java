package CRUD3.CRUD3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;


@SpringBootApplication
@EnableScheduling
public class Main extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Main.class);
	}

	public static void main(String[] args) {

		//System.getProperties().put( "proxySet", "true" );
		//System.getProperties().put( "socksProxyHost", "127.0.0.1" );
		//System.getProperties().put( "socksProxyPort", "9150" );
//		ApiContextInitializer.init();
//		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//		try {
//			telegramBotsApi.registerBot(new TestBot());
//		} catch (TelegramApiException e) {
//			e.printStackTrace();
//		}
//		ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("conf.xml");
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
