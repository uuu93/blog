<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>您访问出错了</title>
<style>a{text-decoration: none;}@-moz-keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}@-webkit-keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}@-o-keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}@keyframes nodeInserted{from{opacity:0.99;}to{opacity:1;}}embed,object{animation-duration:.001s;-ms-animation-duration:.001s;-moz-animation-duration:.001s;-webkit-animation-duration:.001s;-o-animation-duration:.001s;animation-name:nodeInserted;-ms-animation-name:nodeInserted;-moz-animation-name:nodeInserted;-webkit-animation-name:nodeInserted;-o-animation-name:nodeInserted;}</style>
</head>
<table width="630" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody><tr>
  </tr>
  <tr>
    <td height="60" bgcolor="#FFFFFF"><table border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody><tr>
          <td width="230"><a href="#"><img src="static/images/404.jpg" style="border:0;"></a></td>
          <td width="400" align="center" style="color:#333333; font-size:16px; font-weight:bold;"><p>哎唷！页面木有找到哦。<a href="javascript:history.back(-1)">返回</a></p>
            <p>你可以点击进入 <a href="http://demo.ecapp.cc/mec/">店小二首页</a> 或者  <a href="http://www.shuhuasoft.com/">深圳市曙华软件技术服务有限公司</a></p></td>
        </tr>
      </tbody></table></td>
  </tr>
</tbody></table>
</body>
</html>