package com.zxq.mybatis;//package com.zxq.mybatis;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.github.pagehelper.PageHelper;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import tk.mybatis.mapper.common.Mapper;
//import tk.mybatis.spring.mapper.MapperScannerConfigurer;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//
///**
// * @Author aihui075
// * @Date 3018/4/13.
// * @Description
// */
//@Configuration
//public class MyBatisConfig {
//
////    @Value("${mybatis.mapper-locations}")
////    private String mapperLocations;
////    @Value("${mybatis.mapper-package}")
////    private String mapperPackage;
//
////    @Bean
////    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
////        return new PropertySourcesPlaceholderConfigurer();
////    }
//
//    @Bean(name="dataSource")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource dataSource() {
//        System.out.println("----------加载DataSource----------");
//        return new DruidDataSource();
//    }
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "dataSource") DataSource dataSource
//            ,@Qualifier(value = "pageHelper") PageHelper pageHelper) throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
//
//        sqlSessionFactoryBean.setDataSource(dataSource);
//
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MybatisInit.mapperLocations));
//
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//
//        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//
//        return sqlSessionFactory;
//    }
//
//    @Bean(name = "dataSourceTransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(value = "dataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "sqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Bean(name = "pageHelper")
//    public PageHelper pageHelper(@Qualifier("dataSource")DataSource dataSource) {
//        PageHelper pageHelper = new PageHelper();
//        Properties p = new Properties();
//        p.setProperty("offsetAsPageNum", "true");
//        p.setProperty("rowBoundsWithCount", "true");
//        p.setProperty("reasonable", "true");
//        pageHelper.setProperties(p);
//        return pageHelper;
//    }
//
//    @Bean(name = "mapperScannerConfigurer")
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        System.out.println("----------------------加载mapperScannerConfigurer");
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage(MybatisInit.mapperPackage);
//        Properties properties = new Properties();
//        // 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。
//        properties.setProperty("mappers", Mapper.class.getName());
//        properties.setProperty("notEmpty", "false");
//        properties.setProperty("IDENTITY", "MYSQL");
//        mapperScannerConfigurer.setProperties(properties);
//        return mapperScannerConfigurer;
//    }
//}
