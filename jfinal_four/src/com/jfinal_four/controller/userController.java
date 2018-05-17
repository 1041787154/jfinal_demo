package com.jfinal_four.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal_four.model.User;
import com.jfinal_four.validator.userValidator;

public class userController extends Controller{
	/**
	 * 
	 * 呈现用户的相关信息
	 */
	public void  index() {
//		String sql = "select * from user_t";
//		setAttr("objectlist",User.dao.find(sql));//查询多条信息
		String sql = "from user_t order by id desc";
		int pageNumber = getParaToInt("pageNum",1);	
		setAttr("objectlist",User.dao.paginate(pageNumber, 3, "select *", sql));//查询多条信息并分页
		renderFreeMarker("index.html");
	}
	/**
	 * 去向添加用户界面
	 */
	public void addUser() {
		renderFreeMarker("adduser.html");
	}
	/**
	 * 处理添加用户
	 */
	@Before(userValidator.class)
    public void  doAddUser() {//
		User user = getModel(User.class);//此时页面的name属性值需要是user
		boolean tag = user.save();
		if (tag)
			redirect("/user/");//跳转到之前的user/路径下，（重新进入配置文件下，进行）
			
		else 
			renderText("sorry!添加失败");	
	}
    /**
     * 删除用户信息
     */
	public void deleteUserById() {
		int id = getParaToInt(0);
		boolean tag = User.dao.deleteById(id);
		if(tag)
			redirect("/user/");
		else 
			renderTemplate("sorry!删除失败");
		
	}
	/**
	 * 查询单条用户信息
	 */
    public void  queryUserById() {
		int id = getParaToInt(0);//从路径中获取参数，即ID
		String sql = "select * from user_t where id = ? limit 1";
		User user = User.dao.findFirst(sql,id);
		System.out.println("更新结果user:"+user);
		setAttr("user", user);//将要修改的数据提取出来，用于edit页面的默认值获取
		renderFreeMarker("edituser.html");
	}
    /**
	 * 更新用户信息
	 */
    @Before(userValidator.class)
    public void  updateUser() {//点击更新按钮时，自动更新
    	User user = getModel(User.class);//小坑，必须在更新的user中确定ID号
    	System.out.println("更新用户信息："+user);
		boolean tag  = user.update();
		if(tag)
			redirect("/user/");//重定向
		else 
			renderText("sorry!更新失败");
		
	}

}
