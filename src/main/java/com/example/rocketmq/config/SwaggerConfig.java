//package com.example.rocketmq.config;
//
//import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @author lishuai
// * @date 2023/2/17
// */
//@Configuration
//@EnableSwagger2
//@EnableKnife4j
//@Import(BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfig {
//
//    @Bean(value = "test")
//    public Docket test() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(this.apiInfo())
//                //分组名称
//                .groupName("ShardingJdbc测试")
//                .select()
//                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.lhz.rocket.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("API")
//                .description("ShardingJdbc测试文档")
//                .contact(new Contact("lhz", null, "xxxxxx@gmail.com"))
//                .version("1.0")
//                .build();
//    }
//}