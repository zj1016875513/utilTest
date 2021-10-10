package com.zz.javase;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {

    }

    @Test
    public void collectionUtilsTest() {
        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("dalian");
        list.add("gateway");
        list.add("gateway");
        list.add("28");
        list.add("35");
        //查找第一个符合条件的元素
        Object find1 = CollectionUtils.find(list, new Predicate() {
            @Override
            public boolean evaluate(Object arg0) {
                //是数字就返回false
                if(StringUtils.isNumeric(arg0.toString())) {
                    return false;
                }
                return true;
            }
        });

        //排除不符合条件的元素
        Collection select = CollectionUtils.select(list, new Predicate() {
            @Override
            public boolean evaluate(Object arg0) {
                if(StringUtils.isNumeric(arg0.toString())) {
                    return false;
                }
                return true;
            }
        });
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
        System.out.println(find1);//zhangsan
        System.out.println(select);//[zhangsan, dalian, gateway, gateway]


        List<String> list21 = new ArrayList<String>();
        list21.add("zhangsan");
        list21.add("dalian2");
        list21.add("282");
        //查看两个集合中是否有相同的元素
        boolean containsAny = CollectionUtils.containsAny(list, list21);
        System.out.println(containsAny);//true

        //查找集合中某元素的个数 下面方法是查找数字的个数
        int countMatches = CollectionUtils.countMatches(list, new Predicate() {
            @Override
            public boolean evaluate(Object arg0) {
                //只要是数字就返回true
                if(StringUtils.isNumeric(arg0.toString())) {
                    return true;
                }
                return false;
            }
        });
        System.out.println(countMatches);//2

        //查找集合中是否存在某元素
        boolean exists = CollectionUtils.exists(list, new Predicate() {
            @Override
            public boolean evaluate(Object arg0) {
                if(StringUtils.isNumeric(arg0.toString())) {
                    return true;
                }
                return false;
            }
        });
        System.out.println(exists);//true

        //删除集合中的某些元素  下面方法是过滤掉数字
        List<String> list1 = new ArrayList<>(list);
        System.out.println(list1);//[zhangsan, dalian, gateway, gateway, 28, 35]
        CollectionUtils.filter(list1, new Predicate() {
            @Override
            public boolean evaluate(Object arg0) {
                if(StringUtils.isNumeric(arg0.toString())) {
                    return false;
                }
                return true;
            }
        });
        System.out.println(list1);//[zhangsan, dalian, gateway, gateway]

        //查找集合中符合条件的第一个元素
        Object find2 = CollectionUtils.find(list, new Predicate() {
            @Override
            public boolean evaluate(Object arg0) {
                if(StringUtils.isNumeric(arg0.toString())) {
                    return false;
                }
                return true;
            }
        });
        System.out.println(find2);//zhangsan

        //闭包回调函数
        System.out.println(list);
        CollectionUtils.forAllDo(list, new Closure() {
            @Override
            public void execute(Object arg0) {
                if(StringUtils.isNumeric(arg0.toString())) {
                    work(arg0); //集合中每个数字都会执行一次work函数
                }
            }
        });
        System.out.println(list);

        //查找集合中元素的个数
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
        Map cardinalityMap = CollectionUtils.getCardinalityMap(list);
        System.out.println(cardinalityMap);//{35=1, 28=1, zhangsan=1, dalian=1, gateway=2}   集合中只有gateway出现过2次

        //查找两个集合中的相同元素
        List<String> list2 = new ArrayList<String>();
        list2.add("lisi");
        list2.add("beijing");
        list2.add("gateway");
        list.add("19");
        Collection intersection1 = CollectionUtils.intersection(list, list2);
        System.out.println(intersection1);//[gateway]

        //获取两个List<Map<String, Object>>中相同的部分，可以用于对比两个库中的相同表数据是否相等
        List<Map<String, Object>> list3 = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> list4 = new ArrayList<Map<String,Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "zhangsan");
        map1.put("age", 18);
        map1.put("address", "dalian");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "张三");
        map2.put("age", "十八");
        map2.put("address", "大连");
        list3.add(map1);
        list3.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "李四");
        map3.put("age", "十八");
        map3.put("address", "大连");
        list3.add(map3);
        list4.add(map1);
        list4.add(map2);
        System.out.println(list3);
        System.out.println(list4);
        Collection intersection2 = CollectionUtils.intersection(list3, list4);
        System.out.println(intersection2);//[{address=dalian, name=zhangsan, age=18}]

        //判断两个集合是否相同
        boolean equalCollection = CollectionUtils.isEqualCollection(list3, list4);
        System.out.println(equalCollection);

//        判断param2是否包含param1，且不能完全相同
        System.out.println(list3);//[{address=dalian, name=zhangsan, age=18}, {address=大连, name=张三, age=十八}, {address=大连, name=李四, age=十八}]
        System.out.println(list4);//[{address=大连, name=张三, age=十八}, {address=dalian, name=zhangsan, age=18}]
        boolean properSubCollection = CollectionUtils.isProperSubCollection(list4, list3);
        System.out.println(properSubCollection);//true

        //断定某集合是否含有某元素，如果包含则抛异常（我觉得应该少用为妙）
//        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
//        Collection predicatedCollection = CollectionUtils.predicatedCollection(list, new Predicate() {
//            @Override
//            public boolean evaluate(Object object) {
//                if(StringUtils.isNumeric(object.toString())) {
//                    return false;
//                }
//                return true;
//            }
//        });
//        System.out.println(predicatedCollection);//Exception in thread "main" java.lang.IllegalArgumentException: Cannot add Object '28' - Predicate rejected it

        List<String> list20 = new ArrayList<>();
        ArrayList<String> list22 = new ArrayList<>(list);
        list20.add("gateway");
        System.out.println(list22);
        System.out.println(list20);
        //删除list1中的list2
        Collection<String> removeAll = CollectionUtils.removeAll(list22, list20);
        System.out.println(removeAll);

        //反转数组
        String[] array = new String[] {"zs","ls","ww"};
        System.out.println(StringUtils.join(array, ","));//zs,ls,ww
        CollectionUtils.reverseArray(array);
        System.out.println(StringUtils.join(array, ","));//ww,ls,zs

//        删除list中的list12
        System.out.println(list);
        List<String> list12 = new ArrayList<>();
        list12.add("zhangsan");
        list12.add("dalian");
        Collection<String> subtract = CollectionUtils.subtract(list, list12);
        System.out.println(subtract);

        //更改集合中的特定值
        Collection collect = CollectionUtils.collect(list, new Transformer() {
            @Override
            public Object transform(Object arg0) {
                if(StringUtils.isNumeric(arg0.toString())) {
                    return 15;
                }
                return arg0;
            }
        });
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35, 19]
        System.out.println(collect);//[zhangsan, dalian, gateway, gateway, 15, 15, 15]

        System.out.println("---------");
        //更改集合中的特定值，改变集合本身
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]
        CollectionUtils.transform(list, new Transformer() {

            @Override
            public Object transform(Object input) {
                if(StringUtils.isNumeric(input.toString())) {
                    return 15;
                }
                return input;
            }
        });
        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 15, 15]
        List<String> list23 = new ArrayList<String>();
        list23.add("docker");
        list23.add("zhangsan");
        System.out.println(list);
        System.out.println(list23);
        //将list和list2中的元素合并，但去除list23中的重复元素  合并后总集合里只有一个 zhangsan  所以这个union相当于 list左外连接list23
        Collection union = CollectionUtils.union(list, list23);
        System.out.println(union);
    }

    private static void work(Object obj){
        System.out.println("闭包回调函数:我是数字，"+obj);
    }

    @Test
    public void arrayUtilsTest() {
        String[] array1 =new String[] {"zs", "ls"};

        //增加元素
        Object[] add = ArrayUtils.add(array1, "ww");
        System.out.println(StringUtils.join(add, ","));//zs,ls,ww

        //合并数组
        String[] array2 =new String[] {"ww", "ss"};
        Object[] addAll = ArrayUtils.addAll(array1, array2);
        System.out.println(StringUtils.join(addAll, ","));//zs,ls,ww,ss

        //克隆数组
        Object[] clone = ArrayUtils.clone(array1);
        System.out.println(StringUtils.join(clone, ","));//zs,ls


        boolean contains = ArrayUtils.contains(array1, "ww");
        boolean contains1 = ArrayUtils.contains(array1, "zs");
        System.out.println(contains+","+contains1);//false,true
    }

}
