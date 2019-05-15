package com.generator;

import com.generator.properties.GeneratorProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * <p>Description:[启动类]</p>
 * Create on 2019/5/6
 *
 * @author learrings
 */
@SpringBootApplication
@EnableConfigurationProperties({GeneratorProperties.class, DataSourceProperties.class})
public class GeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}
}
