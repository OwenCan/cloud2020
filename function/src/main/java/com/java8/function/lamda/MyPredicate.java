package com.java8.function.lamda;

import jdk.nashorn.internal.objects.annotations.Function;

@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);

}
