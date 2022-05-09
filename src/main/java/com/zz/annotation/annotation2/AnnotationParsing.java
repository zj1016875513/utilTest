package com.zz.annotation.annotation2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {
	public static void main(String[] args){
		try{
			
			for(Method method:(AnnotationParsing.class
					.getClassLoader().
					loadClass("com.zz.annotation.annotation2.AnnotationExample").
					getMethods())){
				System.out.println(method.getName());

				//方法是否存在这个注解
				if(method.isAnnotationPresent(MethodInfo.class)){
					try{
						//通过反射看看都有哪些注解
						for(Annotation anno: method.getDeclaredAnnotations()){
							System.out.println("Annotation in Method # "+method+":"+anno);
						}

						//拿反射方法里的值
					MethodInfo methodAnno=method.getAnnotation(MethodInfo.class);

						//使用反射方法注解里的值
					if(methodAnno.revision()==1){
						System.out.println("Method with revid=sion no 1 + "+method);
					}
				}catch(Throwable ex){
					ex.printStackTrace();
				}			
				}
				System.out.println();
			}
		}catch(SecurityException|ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
