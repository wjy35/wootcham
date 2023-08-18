package com.ssafy.wcc.common.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // http://localhost:8080/swagger-ui.html

    @Bean
    public Docket rankApi(){
        return getDocket("com.ssafy.wcc.domain.rank", "랭킹", Predicates.or(PathSelectors.regex("/rank.*")));
    }

    @Bean
    public Docket authApi(){
        return getDocket("com.ssafy.wcc.domain.jwt", "갱신", Predicates.or(PathSelectors.regex("/refresh")));
    }

    @Bean
    public Docket collectionApi(){
        return getDocket("com.ssafy.wcc.domain.collection", "아이템", Predicates.or(PathSelectors.regex("/collection.*")));
    }

    @Bean
    public Docket recordApi(){
        return getDocket("com.ssafy.wcc.domain.record", "전적", Predicates.or(PathSelectors.regex("/record.*")));
    }

    @Bean
    public Docket reportApi(){
        return getDocket("com.ssafy.wcc.domain.report", "신고", Predicates.or(PathSelectors.regex("/report.*")));
    }

    @Bean
    public Docket topicApi(){
        return getDocket("com.ssafy.wcc.domain.topic", "키워드", Predicates.or(PathSelectors.regex("/topic.*")));
    }

    @Bean
    public Docket memberApi() {
        return getDocket("com.ssafy.wcc.domain.member", "회원", Predicates.or(PathSelectors.regex("/member.*")));
    }

    @Bean
    public Docket noticeApi(){
        return getDocket("com.ssafy.wcc.domain.notice", "공지", Predicates.or(PathSelectors.regex("/notice.*")));
    }


    public Docket getDocket(String base, String groupName, Predicate<String> predicate) {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization") //헤더 이름
                .description("Access Tocken") //설명
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(aParameters)
                .groupName(groupName).select()
                .apis(RequestHandlerSelectors.basePackage(base)).paths(predicate)
                .apis(RequestHandlerSelectors.any()).build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
    }
}

