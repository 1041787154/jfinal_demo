package com.jfinal_four.validator;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.jfinal_four.model.User;

public class userValidator extends Validator{
/**
 * 
 * 校验的
 */
	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		validateRequiredString("user.username", "userName", "用户名不能为空！！");
		validateRequiredString("user.realname", "realName", "真实姓名不能为空！！");
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		//c.renderFreeMarker("adduser.html");
		String  actionKey  = getActionKey();
		String view = null;
		c.keepModel(User.class);
		if (actionKey.equals("/user/doAddUser")) {
			view = "adduser.html";
		}
		if (actionKey.equals("/user/updateUser")) {
			view = "edituser.html";
		}
		c.renderFreeMarker(view);
	}

}
