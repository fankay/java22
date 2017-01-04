package com.kaishengit.test;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jimi_jin on 2016-12-26.
 */
public class GuavaTest {

    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1,2,3,4,5,12,24,13);
        Collection<Integer> collection = Collections2.filter(list1, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        });
        List<Integer> list2 = Lists.newArrayList(collection);
        for (int i =0 ; i<list2.size();i++){
            System.out.println(list2.get(i));
        }


    }
}
