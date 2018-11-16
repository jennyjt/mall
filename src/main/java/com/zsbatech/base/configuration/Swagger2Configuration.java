package com.zsbatech.base.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wxcsdb88 on 2017/8/13 21:35.
 */
@Configuration
@EnableSwagger2
//@ComponentScan(basePackages = {"com.zsbatech.passport.controller"})
public class Swagger2Configuration {

    // 如果某个接口不想暴露,可以使用以下注解
    // @ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下

//    @Bean
//    public SecurityScheme apiKey() {
//        return new ApiKey("access_token", "accessToken", "header");
//    }

    @Bean
    public Docket buildDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo()).useDefaultResponseMessages(false);
        docket
                .groupName("baas-deploy-manager")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zsbatech"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("baas-deploy-manager API")
                .description("spring boot for baas-deploy-manager API")
                .version("1.0.0")
                .build();
    }

}
