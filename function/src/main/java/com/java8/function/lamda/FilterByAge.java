package com.java8.function.lamda;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-24 19:28
 **/
public class FilterByAge implements MyPredicate<Employe> {
    @Override
    public boolean test(Employe employe) {
        return employe.getAge() > 35;
    }
}
