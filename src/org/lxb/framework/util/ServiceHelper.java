package org.lxb.framework.util;

import org.lxb.framework.service.system.menu.impl.MenuService;
import org.lxb.framework.service.system.role.impl.RoleService;
import org.lxb.framework.service.system.user.IUserService;


/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		//WebApplicationContextUtils.
		return Const.WEB_APP_CONTEXT.getBean(serviceName);
	}
	
	public static IUserService getUserService(){
		return (IUserService) getService("userService");
	}
	
	public static RoleService getRoleService(){
		return (RoleService) getService("roleService");
	}
	
	public static MenuService getMenuService(){
		return (MenuService) getService("menuService");
	}
}
