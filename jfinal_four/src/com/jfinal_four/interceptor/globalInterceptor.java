package com.jfinal_four.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.InterceptorManager;
import com.jfinal.aop.InterceptorStack;
import com.jfinal.aop.Invocation;

public class globalInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		System.out.println("我是女invoke方法之前的操作");
		inv.invoke();
		System.out.println("我是女invoke方法之后的操作");
		
	}



}
