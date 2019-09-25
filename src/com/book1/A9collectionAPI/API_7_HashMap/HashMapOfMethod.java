package com.book1.A9collectionAPI.API_7_HashMap;

import java.util.HashMap;
import java.util.Random;

/**
 * <describe>HashMap的一些函数的用法</describe>
 *
 * @author book1
 * @version 2019-09-17 1.0.1
 */
public class HashMapOfMethod {
    private static HashMap<Integer, String> hashMap;

    /**
     * <descirbe>测试入口</descirbe>
     *
     * @param args
     */
    public static void main(String[] args) {
        initHasMap();
        //testCompute();
        //testComputeIfAbsent();
        testMerge();

        hashMap.replaceAll((k, v) -> {
            return v = "zxm-" + k;
        });

        System.out.println("调用 replaceAll 之后");
        System.out.println("hashMap = " + hashMap);

    }

    /**
     * <describe>初始化hashMap</describe>
     */
    private static void initHasMap(){
        hashMap = new HashMap<>();
        Random random = new Random(1001);
        int cur;
        for (int i = 0; i < 10; ++i) {
            cur = random.nextInt(50);
            hashMap.put(cur, "jjf_" + cur);
        }

        System.out.println("hashMap = " + hashMap);
    }

    /**
     * <describe> compute(K key,BiFunction<? super K,? super V,? extends V> remappingFunction) </describe>
     */
    private static void testCompute(){

        System.out.println(hashMap.compute(48, (k, v) -> v == null ? k + "_Non-existent" : v + "_existent"));
        System.out.println(hashMap.compute(20, (k, v) -> v == null ? k + "_Non-existent" : v + "_existent"));
        System.out.println("调用compute之后：");
        System.out.println("hashMap = " + hashMap);
    }

    /**
     * <describe> computeIfAbsent(K key,Function<? super K,? extends V> mappingFunction) </describe></>
     */
    private static void testComputeIfAbsent(){
        System.out.println(hashMap.computeIfAbsent(48,k -> k + "_Non-existent"));
        System.out.println(hashMap.computeIfAbsent(20, k -> k + "_Non-existent"));
        System.out.println("调用 testComputeIfAbsent 之后：");
        System.out.println("hashMap = " + hashMap);
    }

    private static void testMerge(){
        System.out.println(hashMap.merge(48,"000", (v1, v2) -> v1 + v2));
        System.out.println(hashMap.merge(20, "111", (v1, v2) -> v1 + v2));
        System.out.println("调用compute之后：");
        System.out.println("hashMap = " + hashMap);
    }

}
