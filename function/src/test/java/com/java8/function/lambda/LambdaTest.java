package com.java8.function.lambda;

import com.java8.function.lamda.Employe;
import com.java8.function.lamda.FilterByAge;
import com.java8.function.lamda.FilterBySalary;
import com.java8.function.lamda.MyPredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-24 19:26
 **/
public class LambdaTest {

    List<Employe> employes = Arrays.asList(
            new Employe("张三", 18, 9999.99),
            new Employe("李四", 38, 5555.99),
            new Employe("王五", 50, 6666.66),
            new Employe("赵六", 16, 3333.33),
            new Employe("田七", 10, 7777.77)
    );

    @Test
    public void test1() {
        //按年龄过滤
        List<Employe> list = filterEmployee(employes);
        list.forEach(System.out::println);

        System.out.println("--------------------------------------------------");
        //按工资过滤
        List<Employe> list2 = filterEmployee2(employes);
        list2.forEach(System.out::println);
    }


    //需求1：获取当前公司中员工年龄大于35的员工信息
    public List<Employe> filterEmployee(List<Employe> list) {
        List<Employe> emps = new ArrayList<>();

        for (Employe emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    //需求2：获取当前公司中员工工资大于5000的员工信息
    public List<Employe> filterEmployee2(List<Employe> list) {
        List<Employe> emps = new ArrayList<>();

        for (Employe emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }
        return emps;
    }

    @Test
    public void getEmployes1() {
        List<Employe> employes = fiterEmplage(this.employes, (Predicate<Employe>) new FilterByAge());
    }


    @Test
    public void getEmployes() {
        List<Employe> employes = fiterEmplage(this.employes, (Predicate<Employe>) new FilterByAge());
        System.out.println(employes);
    }


    //创建共有方法
    public List<Employe> fiterEmplage(List<Employe> employeList, Predicate<Employe> predicate) {
        List<Employe> employes = new ArrayList<>();

        for (Employe e : employeList) {
            if (predicate.test(e)) {
                employes.add(e);
            }
        }
        return employes;
    }

    @Test
    public void test() {
        List<Employe> employes = fiterEmplage(this.employes, t -> t.getAge() >= 35);
        List<Employe> employes1 = fiterEmplage(this.employes, t -> t.getSalary() > 3500);
    }

    @Test
    public void test2() {
        employes.stream()
                .filter((e) -> e.getAge() >= 35)//过滤年龄为35的
                .limit(1) //取前1条
                .forEach(System.out::println);

        employes.stream()
                .map(Employe::getName)//遍历所有的名字
                .forEach(System.out::println);
    }

    @Test
    public void test11() {
        Comparator<Integer> comparable = (a,b)->Integer.compare(a, b);;
        comparable.compare(1, 2);
    }




}
