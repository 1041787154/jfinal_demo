package com.jfinal_four.config;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.jfinal_four.controller.userController;
import com.jfinal_four.interceptor.globalInterceptor;
import com.jfinal_four.model.User;
import com.jfinal_four.update.fileUpdate;

public class config extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		PropKit.use("a_little_config.txt");//链接数据库
		me.setViewType(ViewType.FREE_MARKER);//前端页面
		me.setDevMode(PropKit.getBoolean("devMode",true));//开发者模式
		me.setBaseUploadPath("D:\\tomcatInstall\\apache-tomcat-8.5.30\\webapps\\jfinal_four_upload");
	}

	
	
	//dfsdfsdfsdf
	@Override
	public void configRoute(Routes me) {//配置路由。
		me.add("/",userController.class);
		me.add("/user",userController.class);
		me.add("/update",fileUpdate.class);
	}

	@Override
	public void configEngine(Engine me) {
		
	}

	/**
	 * 映射实体和数据库中的表格 
	 */
	@Override
	public void configPlugin(Plugins me) {
		String user = PropKit.get("user");
		String pass = PropKit.get("password");
		DruidPlugin druid = new DruidPlugin(PropKit.get("jdbcUrl"),user,pass);//阿里连接池
		me.add(druid);
		ActiveRecordPlugin aPlugin = new ActiveRecordPlugin(druid);//热拔插件
		me.add(aPlugin);
		aPlugin.setShowSql(true);
		//aPlugin.addMapping("user_t","id",User.class);//实体和表哥的映射（表名和实体类 ）方法二
		aPlugin.addMapping("user_t", User.class);//实体和表哥的映射（表名和实体类 ）
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//me.add(new globalInterceptor());//定义去全局变量
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));//设置上下路径
	}

}
