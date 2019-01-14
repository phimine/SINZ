package cn.funion;

import cn.funion.datasources.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EnableScheduling
@Import({DynamicDataSourceConfig.class})
public class SinzAdminApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SinzAdminApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SinzAdminApiApplication.class);
	}
}
