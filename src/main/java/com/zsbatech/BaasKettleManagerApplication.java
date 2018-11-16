package com.zsbatech;

import com.zsbatech.base.async.ThreadPoolConfig;
import com.zsbatech.base.utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = {"com.zsbatech.*.dao"})
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
@EnableConfigurationProperties({ThreadPoolConfig.class})
@Import(value = {SpringUtil.class})
public class BaasKettleManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaasKettleManagerApplication.class, args);
	}
}
