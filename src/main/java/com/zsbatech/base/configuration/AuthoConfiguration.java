package com.zsbatech.base.configuration;


import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zsbatech.base.interceptor.TokenInterceptor;
import com.zsbatech.BaasKettleManagerApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxcsdb88 on 2017/8/13 23:04.
 */
@Configuration
@ComponentScan(basePackageClasses = BaasKettleManagerApplication.class, useDefaultFilters = true)
public class AuthoConfiguration extends WebMvcConfigurationSupport {

    public static String[] includeUrlsArray1;
    


    static {
        // 需要验证登陆状态的接口
        ArrayList<String> includeUrls = new ArrayList<>();
//        includeUrls.add("/data_center/**");
        includeUrls.add("/deploy/**");
        includeUrlsArray1 = includeUrls.toArray(new String[0]);

     
       
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(new MediaType(MediaType.ALL.getType()));
        fastConverter.setSupportedMediaTypes(mediaTypeList);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setFeatures(
                Feature.AllowArbitraryCommas,
                Feature.AllowUnQuotedFieldNames,
                Feature.DisableCircularReferenceDetect
        );
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteSlashAsSpecial
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).order(1)
                .addPathPatterns(includeUrlsArray1);
                
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
