package mevmax;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("mevmax.mapper")
public class MevmaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MevmaxApplication.class, args);
	}

}
