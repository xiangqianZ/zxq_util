package com.zxq.util;//package com.zxq.util;
//
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.yaml.snakeyaml.Yaml;
//
//import java.util.Map;
//
///**
// * @Author aihui075
// * @Date 2018/5/11.
// * @Description
// */
//public class YmlConfig {
//
//    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
//
//    static Map<String, Object> map = null;
//
//    static {
//        try {
//            Yaml yaml = new Yaml();
//            Resource resource = resourceLoader.getResource("classpath:/application.yml");
//            map = (Map<String, Object>) yaml.load(resource.getInputStream());
//
//        } catch (Exception e) {
//            System.out.println("application.yml找不到");
//            e.printStackTrace();
//        }
//    }
//
//    public static String get(String key) {
//        try {
//            String[] keys = key.split("\\.");
//            Map<String, Object> mm = map;
//            for (String k : keys) {
//                Object obj = result(k, mm);
//                if (obj instanceof String)
//                    return obj.toString();
//                mm = (Map<String, Object>) obj;
//
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private static Object result(String key, Object value) {
//        return ((Map) value).get(key);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(YmlConfig.get("mybatis.mapper-package"));
//    }
//}
