package com.zz.annotation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationExample {
	public static void main(String[] args){
		
	}
	
	@Override
	@MethodInfo(author="Pankaj",comments="Main method",date="Nov 17 2012",revision=1)
	public String toString(){
		return "Overriden toString method";
	}
	
	@Deprecated
	@MethodInfo(comments="deprecated method",date="Nov 17 20112",revision = 1)
	public static void oldMethod(){
		System.out.println("old method,don't usr it ");
	}
	
	public static void genericsTest() throws FileNotFoundException{
		List l=new ArrayList();
		l.add("abc");
		oldMethod();
	}
	
}
