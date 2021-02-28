package com.java8.function.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-24 21:22
 **/
public class FourLambda {

    //消费性接口
    @Test
    public void get() {
        happy(1000, (m) -> System.out.println("consumer what money" + m));
    }
    public void happy(Integer money,Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    //供给型接口
    @Test
    public void get1() {
        List<Integer> numlist = getNumlist(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numlist) {
            System.out.println(integer);
        }
    }

    public List<Integer> getNumlist(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }


    @Test
    public void getString() {
        String s = strHandler("21    ", (a) ->
        a.trim()
        );

        String sub = strHandler("112123", (a) -> a.substring(2, 3));
        System.out.println(s);
        System.out.println(sub);
    }

    public String strHandler(String s1, Function<String, String> function) {
        return function.apply(s1);
    }


    @Test
    public void test6() {
        String[] sarr = {"11", "22"};
        List<String> strings = Arrays.asList(sarr);
        List<String> strings1 = filterStr(strings, (s -> s.equals("11")));
        for (String s : strings1) {
            System.out.println(s);
        }
    }

    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                newList.add(s);
            }
        }
        return newList;
    }
}
