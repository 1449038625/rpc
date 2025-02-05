package com.zbz.rpc.spi;

import cn.hutool.core.io.resource.ResourceUtil;
import com.zbz.rpc.constant.RpcConstant;
import com.zbz.rpc.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Slf4j
public class SpiLoader {
    /**
     * 存储所有接口的所有实现类
     */
    private static Map<String, Map<String,Class<?>>> loaderMap=new ConcurrentHashMap<>();
    /**
     * 存储对象的实例
     */
    private static Map<String,Object> instanceCache=new ConcurrentHashMap<>();
    /**
     * 系统的spi的目录
     */
    private static final String RPC_SYSTEM_SPI_DIR= RpcConstant.RPC_SYSTEM_SPI_DIR;
    /**
     * 用户自定义的spi的目录
     */
    private static final String RPC_CUSTOM_SPI_DIR=RpcConstant.RPC_CUSTOM_SPI_DIR;
    /**
     * 扫描路径
     */
    private static final String[] SCAN_DIRS = new String[]{RPC_SYSTEM_SPI_DIR, RPC_CUSTOM_SPI_DIR};
    /**
     * 动态加载类列表
     */
    // todo 这里写死了，只放了Serializer类，后续可以扩展
    private static final List<Class<?>> LOAD_CLASS_LIST = Arrays.asList(Serializer.class);
    /**
     * 加载所有类型
     */
    public static void loadAll(){
        log.info("load all spi");
        for (Class<?> aClass : LOAD_CLASS_LIST) {
            load(aClass);
        }
    }

    /**
     * 根据接口类加载实现类
     * @param loadClass
     * @return
     */
    public static Map<String, Class<?>> load(Class<?> loadClass){
        log.info("load spi {}",loadClass.getName());
        HashMap<String, Class<?>> keyClassMap = new HashMap<>();
        for (String scanDir : SCAN_DIRS) {
            List<URL> resources = ResourceUtil.getResources(scanDir + loadClass.getName());
            for (URL resource : resources) {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(resource.openStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        String[] strArray = line.split("=");
                        if(strArray.length>1){
                            String key = strArray[0];
                            String className = strArray[1];
                            keyClassMap.put(key,Class.forName(className));
                        }
                    }
                }catch (Exception e){
                    log.error("load spi {} error",loadClass.getName(),e);
                }
            }
        }
        loaderMap.put(loadClass.getName(), keyClassMap);
        return keyClassMap;
    }

    public static <T> T getInstance(Class<T> tClass,String key){
        String tClassName = tClass.getName();
        Map<String, Class<?>> stringClassMap = loaderMap.get(tClassName);
        if(stringClassMap==null){
            throw new RuntimeException("找不到spi："+tClassName);
        }
        if(!stringClassMap.containsKey(key)){
            throw new RuntimeException("找不到spi："+tClassName+" key:"+key);
        }
        Class<?> implClass = stringClassMap.get(key);
        String implClassName = implClass.getName();
        if(!instanceCache.containsKey(implClassName)){
            try {
                instanceCache.put(implClassName,implClass.newInstance());
            }catch (Exception e){
                throw new RuntimeException("找不到spi："+tClassName+" key:"+key,e);
            }
        }
        return (T)instanceCache.get(implClassName);
    }

    public static void main(String[] args) {
        loadAll();
        System.out.println(loaderMap);
        Serializer serializer = getInstance(Serializer.class, "j");
        System.out.println(serializer);
    }
}
