<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytag" uri="http://org.lxb.mytag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="static/ace/css/admin.css"  />
<%@ include file="../index/top.jsp"%>
<script src="plugins/echarts/echarts.min.js"></script>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
					<div class="BoxStyleBg">
			 	<div class="main-content-inner" style="float:left">
				<div class="page-content">
					<div class="row">
					<div class="BoxStyleBox" id="show_back_stage_down">
					<div class="BoxStyleBox_ListBox">   
					<table class="BoxStyleBox_BgTable">  
					  <tbody>
					  <tr>    
					   <td class="BoxStyleBox_td">    
		  			<div class="BoxStyleBox_List">     
		  			  <div class="ContentTitle_Bg">       
		  				<table class="ContentTitle_BgTable">        
		  				 <tbody><tr>        
		  					<td valign="top" width="48">           
		  					<div class="TitleIcon_01"></div>        
		  					</td>          
		  					<td valign="top">           
		  					<div class="ContentTitle_Box">    
		  					<div class="BoxStyleBox_ListTitle">资源管理</div>         
		  					<div class="BoxStyleBox_ListSubTitle">商城的参数配置</div>         
		  					</div>          
		  					</td>
		  					<td><img  src="static/images/update.png" onclick="updateSystem()"/></td>       
		  					</tr>        
		  					</tbody>
		  					</table>     
		  					<div class="PopupBox_HintBg_BottomRight j_prompt" style="position: absolute; top: 50px; right: 5px;">      
		  			<div class="PopupBox_HintBg_IconBox ">          
		  			<div class="PopupBox_HintBg_Icon j_prompt_hover" >?</div>     
		 			 </div>        
		 			  <div class="PopupBox_HintBg_ContentBox j_prompt_content" style="display: none;" >   
		  			<div class="PopupBox_HintBg_ContentBox_Arrow"></div>       
		  			<div class="PopupBox_HintBg_ContentBox_Text">点击更新将自动更新网站信息</div>     
		  			</div>        
		  			</div>      
		  			</div>      
					 <div class="BasicSet_StartBox">     
					 <div class="Table_Bg">       
					 <div class="Table_Box" id="status_info">
					 <table> 
					 <tbody><tr class="Table_TitleBgBox">  
					 <td style="border: 1px solid #cad2e2; min-width: 120px" width="75">   
					 <div class="Table_TitleBg">名称</div>   
					  </td>  
					 <td style="border: 1px solid #cad2e2" width="33%">   
					 <div class="Table_TitleBg">已使用</div>   
					  </td>   
					 <td style="border: 1px solid #cad2e2" width="33%">   
					 <div class="Table_TitleBg">最大限值</div>   
					 </td>  
					  </tr>  
					   <tr>   
					  <td>     
					  <div class="Table_Content">     
					 <div class="Table_Content_Info">商品数量(个)</div> 
					 </div>    
					 </td>    
					 <td>     
					 <div class="Table_Content">    
					 <div class="Table_Content_Info">${proAmount}</div>     
					 </div>    
					 </td>    
					 <td>   
					 <div class="Table_Content">      
					 <div class="Table_Content_Info">${DataMap['maxProduct']}</div>    
					 </div>    
					 </td>   
					 </tr>   
					 <tr>    
					 <td>    
					 <div class="Table_Content">     
					 <div class="Table_Content_Info">日订单量(单)</div>  
					 </div>   
					  </td>    
					 <td>     
					 <div class="Table_Content">     
					 <div class="Table_Content_Info">${dailyOrderCount}</div>     
					 </div>    
					 </td>  
					 <td>     
					 <div class="Table_Content">    
					 <div class="Table_Content_Info">${DataMap['dayOrder']}</div>    
					 </div>    
					 </td>   
					 </tr>  
			 </tbody>
			 </table>
			 <!-- 显示网站开始运营日期 与网站费用截止日期  -->
			 	<div style="margin-top:20px;font-size:16px;color:#448db7">
			 		<p>${PROMPT}</p>
			 		<c:if test="${CHARGES_CODE=='10001' }">
			 			<p>运营日期:${START_DATE}</p>
			 			<p>截止日期:${END_DATE}</p>
			 		</c:if>
			 	</div>
			 </div>  
			 </div>      
			  </div>      
			  </div>     
			  </td>   
			   </tr>   
			    <tr>    
			   </tr>  
			    </tbody>
			   </table> 
			  </div>    
			   <div style="right: 4px;" class="toolbar_questionnaire"><div class="ButtonBg">   
			 </div>
			 </div>
			 </div>
			  <div class="BoxStyleBox" id="show_back_stage_down" style="width:66.6%;height:100%">
					<div class="BoxStyleBox_ListBox">   
					<table class="BoxStyleBox_BgTable">  
					  <tbody>
					  <tr>    
					   <td class="BoxStyleBox_td">    
		  			<div class="BoxStyleBox_List">     
		  			  <div class="ContentTitle_Bg">       
		  				<table class="ContentTitle_BgTable">        
		  				 <tbody><tr>        
		  					<td valign="top" width="48">           
		  					<div class="TitleIcon_05"></div>        
		  					</td>          
		  					<td valign="top">           
		  					<div class="ContentTitle_Box">    
		  					<div class="BoxStyleBox_ListTitle">用户统计</div>         
		  					<div class="BoxStyleBox_ListSubTitle">用户活跃数量对比折线图</div>         
		  					</div>          
		  					</td>         
		  					</tr>        
		  					</tbody>
		  					</table>     
		  					<div class="PopupBox_HintBg_BottomRight j_prompt" style="position: absolute; top: 50px; right: 5px;">      
		  			<div class="PopupBox_HintBg_IconBox ">          
		  			<div class="PopupBox_HintBg_Icon j_prompt_hover">?</div>     
		 			 </div>        
		 			  <div class="PopupBox_HintBg_ContentBox j_prompt_content" style="display: none;">   
		  			<div class="PopupBox_HintBg_ContentBox_Arrow"></div>       
		  			<div class="PopupBox_HintBg_ContentBox_Text">最近一个月用户活跃数量</div>     
		  			</div>        
		  			</div>      
		  			</div>      
					 <div class="BasicSet_StartBox">     
					 <div class="Table_Bg">       
					 <div class="Table_Box" id="status_info">
					<div id="userdataline"
					style="width:80%;height:300px;float:left;margin-left:10%"></div>
						<script type="text/javascript">
						
						var xAxisData = [];
						var series2Data = [];
						<c:forEach items="${lastMonthCounts}" var="element">
							xAxisData.push('${element.week}');
							series2Data.push('${element.activeCounts}');
						</c:forEach>
						
							 //图表渲染的容器对象  
					        var chartContainer = document.getElementById("userdataline");  
					        //加载图表  
					        var myChart = echarts.init(chartContainer);  
					        myChart.setOption({  
					            //图表标题  
					            title: {  
					                text: "最近一个月活跃用户数量", //正标题  
					                link: "", //正标题链接 点击可在新窗口中打开  
					                x: "center", //标题水平方向位置  
					                subtext: "", //副标题  
					                sublink: "", //副标题链接  
					                //正标题样式  
					                textStyle: {  
					                    fontSize:16,
					                    color:"#ff6415"
					        			
					                },  
					                //副标题样式  
					                subtextStyle: {  
					                    fontSize:12,  
					                    color:"red"  
					                }  
					        },  
					        //数据提示框配置  
					        tooltip: {  
					            trigger: 'axis' //触发类型，默认数据触发，见下图，可选为：'item' | 'axis' 其实就是是否共享提示框  
					        },  
					        //图例配置  
					        legend: {  
					            data: ['活跃人数'], //这里需要与series内的每一组数据的name值保持一致  
					            y:"bottom"  
					        },  
					        //轴配置  
					        xAxis: [  
					                {  
					                    type: 'category',  
					                    data: xAxisData,  
					                    name: "周"  
					                }  
					            ],  
					        //Y轴配置  
					        yAxis: [  
					                {  
					                    type: 'value',  
					                    splitArea: { show: true },  
					                    name:"用户量"  
					                }  
					            ],  
					        //图表Series数据序列配置  
					        series: [  
					                {  
					                    name: '活跃人数',  
					                    type: 'line',  
					                    stack: '总量',
							            areaStyle: {normal: {}},
							            data:series2Data 
					                },  
					            ]  
					    });  
					
						    </script>
				
			 </div>  
			 </div>      
			  </div>      
			  </div>     
			  </td>   
			   </tr>   
			    <tr>    
			   </tr>  
			    </tbody>
			   </table> 
			  </div>    
			   <div style="right: 4px;" class="toolbar_questionnaire"><div class="ButtonBg">   
			 </div>
			 </div>
			 </div>
			 
			 
			 
					</div>
				</div>
			</div>
			
			</div>
			<!-- 每日订单/资金统计/产品 -->
			<div class="BoxStyleBg">
			 <div class="BoxStyleBox" id="show_back_stage_down">
					<div class="BoxStyleBox_ListBox">   
					<table class="BoxStyleBox_BgTable">  
					  <tbody>
					  <tr>    
					   <td class="BoxStyleBox_td">    
		  			<div class="BoxStyleBox_List">     
		  			  <div class="ContentTitle_Bg">       
		  				<table class="ContentTitle_BgTable">        
		  				 <tbody><tr>        
		  					<td valign="top" width="48">           
		  					<div class="TitleIcon_02"></div>        
		  					</td>          
		  					<td valign="top">           
		  					<div class="ContentTitle_Box">    
		  					<div class="BoxStyleBox_ListTitle">当日订单</div>         
		  					<div class="BoxStyleBox_ListSubTitle">不同状态的日订单金额与数量</div>         
		  					</div>          
		  					</td>         
		  					</tr>        
		  					</tbody>
		  					</table>     
		  					<div class="PopupBox_HintBg_BottomRight j_prompt" style="position: absolute; top: 50px; right: 5px;">      
		  			<div class="PopupBox_HintBg_IconBox ">          
		  			<div class="PopupBox_HintBg_Icon j_prompt_hover">?</div>     
		 			 </div>
		 			  <div class="PopupBox_HintBg_ContentBox j_prompt_content" style="display: none;">   
		  			<div class="PopupBox_HintBg_ContentBox_Arrow"></div>       
		  			<div class="PopupBox_HintBg_ContentBox_Text">不同状态的日订单金额与数量</div>     
		  			</div>        
		  			</div>      
		  			</div>      
					 <div class="BasicSet_StartBox">     
					 <div class="Table_Bg">       
					 <div class="Table_Box" id="status_info">
					 <table> 
					 <tbody><tr class="Table_TitleBgBox">  
					 <td style="border: 1px solid #cad2e2; min-width: 120px" width="75">   
					 <div class="Table_TitleBg">订单状态</div>   
					  </td>  
					 <td style="border: 1px solid #cad2e2" width="33%">   
					 <div class="Table_TitleBg">数量</div>   
					  </td>   
					 <td style="border: 1px solid #cad2e2" width="33%">   
					 <div class="Table_TitleBg">金额</div>   
					 </td>  
					  </tr>  
					  <c:forEach items="${allOrderState }" var="varstate" varStatus="vsa">
					  	  <tr>   
							  <td>     
							  <div class="Table_Content">     
							 <div class="Table_Content_Info">${varstate.NAME}</div> 
							 </div>    
							 </td> 
							 <td>     
							 <div class="Table_Content">    
							 <div class="Table_Content_Info">
								 <c:choose>
									 <c:when test="${mapOrder[varstate.BIANMA] != null}">${mapOrder[varstate.BIANMA].ordernum}</c:when>
									 <c:otherwise>0</c:otherwise>
								 </c:choose>
							 </div>     
							 </div>    
							 </td>    
							 <td>   
							 <div class="Table_Content">      
							 <div class="Table_Content_Info">
							 <c:choose>
									 <c:when test="${mapOrder[varstate.BIANMA] != null}">${mapOrder[varstate.BIANMA].sumamount}</c:when>
									 <c:otherwise>0</c:otherwise>
								 </c:choose>
							 </div>    
							 </div>    
							 </td>   
							 </tr>   
					  </c:forEach>
					  	
					  
			 </tbody>
			 </table>
			 </div>  
			 </div>      
			  </div>      
			  </div>     
			  </td>   
			   </tr>   
			    <tr>    
			   </tr>  
			    </tbody>
			   </table> 
			  </div>    
			   <div style="right: 4px;" class="toolbar_questionnaire"><div class="ButtonBg">   
			 </div>
			 </div>
			 </div>
			 <div class="BoxStyleBox" id="show_back_stage_down">
					<div class="BoxStyleBox_ListBox">   
					<table class="BoxStyleBox_BgTable">  
					  <tbody>
					  <tr>    
					   <td class="BoxStyleBox_td">    
		  			<div class="BoxStyleBox_List">     
		  			  <div class="ContentTitle_Bg">       
		  				<table class="ContentTitle_BgTable">        
		  				 <tbody><tr>        
		  					<td valign="top" width="48">           
		  					<div class="TitleIcon_04"></div>        
		  					</td>          
		  					<td valign="top">           
		  					<div class="ContentTitle_Box">    
		  					<div class="BoxStyleBox_ListTitle">资金统计</div>         
		  					<div class="BoxStyleBox_ListSubTitle">当日订单的总资金</div>         
		  					</div>          
		  					</td>         
		  					</tr>        
		  					</tbody>
		  					</table>     
		  					<div class="PopupBox_HintBg_BottomRight j_prompt" style="position: absolute; top: 50px; right: 5px;">      
		  			<div class="PopupBox_HintBg_IconBox ">          
		  			<div class="PopupBox_HintBg_Icon j_prompt_hover">?</div>     
		 			 </div>        
		 			  <div class="PopupBox_HintBg_ContentBox j_prompt_content" style="display: none;">   
		  			<div class="PopupBox_HintBg_ContentBox_Arrow"></div>       
		  			<div class="PopupBox_HintBg_ContentBox_Text">每日订单的总资金</div>     
		  			</div>        
		  			</div>      
		  			</div>      
					 <div class="BasicSet_StartBox">     
					 <div class="Table_Bg">       
					 <div class="Table_Box" id="status_info">
					 <table> 
					 <tbody><tr class="Table_TitleBgBox">  
					 <td style="border: 1px solid #cad2e2; min-width: 120px" width="75">   
					 <div class="Table_TitleBg">支付方式</div>   
					  </td>  
					 <td style="border: 1px solid #cad2e2" width="33%">   
					 <div class="Table_TitleBg">总金额</div>   
					  </td>   
					  </tr> 
					   <c:forEach items="${allPayCenter }" var="varstate" varStatus="vsa">
					  	  <tr>   
							  <td>     
							  <div class="Table_Content">     
							 <div class="Table_Content_Info">${varstate.NAME}</div> 
							 </div>    
							 </td> 
							 <td>   
							 <div class="Table_Content">      
							 <div class="Table_Content_Info">
							 <c:choose>
									 <c:when test="${orderPayMap[varstate.BIANMA] != null}">${orderPayMap[varstate.BIANMA].sumamount}</c:when>
									 <c:otherwise>0</c:otherwise>
								 </c:choose>
							 </div>    
							 </div>    
							 </td>   
							 </tr>   
					  </c:forEach>
			 </tbody>
			 </table>
			 </div>  
			 </div>      
			  </div>      
			  </div>     
			  </td>   
			   </tr>   
			    <tr>    
			   </tr>  
			    </tbody>
			   </table> 
			  </div>    
			   <div style="right: 4px;" class="toolbar_questionnaire"><div class="ButtonBg">   
			 </div>
			 </div>
			 </div>
			 	<div class="BoxStyleBox" id="show_back_stage_down">
					<div class="BoxStyleBox_ListBox">   
					<table class="BoxStyleBox_BgTable">  
					  <tbody>
					  <tr>    
					   <td class="BoxStyleBox_td">    
		  			<div class="BoxStyleBox_List">     
		  			  <div class="ContentTitle_Bg">       
		  				<table class="ContentTitle_BgTable">        
		  				 <tbody><tr>        
		  					<td valign="top" width="48">           
		  					<div class="TitleIcon_03"></div>        
		  					</td>          
		  					<td valign="top">           
		  					<div class="ContentTitle_Box">    
		  					<div class="BoxStyleBox_ListTitle">商品管理</div>         
		  					<div class="BoxStyleBox_ListSubTitle">各状态下的商品数量</div>         
		  					</div>          
		  					</td>         
		  					</tr>        
		  					</tbody>
		  					</table>     
		  					<div class="PopupBox_HintBg_BottomRight j_prompt" style="position: absolute; top: 50px; right: 5px;">      
		  			<div class="PopupBox_HintBg_IconBox ">          
		  			<div class="PopupBox_HintBg_Icon j_prompt_hover">?</div>     
		 			 </div>        
		 			  <div class="PopupBox_HintBg_ContentBox j_prompt_content" style="display: none;">   
		  			<div class="PopupBox_HintBg_ContentBox_Arrow"></div>       
		  			<div class="PopupBox_HintBg_ContentBox_Text">各状态下的商品数量</div>     
		  			</div>        
		  			</div>      
		  			</div>      
					 <div class="BasicSet_StartBox">     
					 <div class="Table_Bg">       
					 <div class="Table_Box" id="status_info">
					 <table> 
					 <tbody><tr class="Table_TitleBgBox">  
					 <td style="border: 1px solid #cad2e2; min-width: 120px" width="75">   
					 <div class="Table_TitleBg">商品状态</div>   
					  </td>  
					 <td style="border: 1px solid #cad2e2" width="33%">   
					 <div class="Table_TitleBg">数量</div>   
					  </td>   
					  </tr>
					  ${pro }  
					   <tr>   
					  <td>     
					  <div class="Table_Content">     
					 <div class="Table_Content_Info">在售</div> 
					 </div>    
					 </td>    
					 <td>     
					 <div class="Table_Content">    
					 <div class="Table_Content_Info">${isSale}</div>     
					 </div>    
					 </td>    
					 </tr> 
					   <tr>   
					  <td>     
					  <div class="Table_Content">     
					 <div class="Table_Content_Info">下架</div> 
					 </div>    
					 </td>    
					 <td>     
					 <div class="Table_Content">    
					 <div class="Table_Content_Info">${downPro }</div>     
					 </div>    
					 </td>    
					 </tr>
					   <tr>   
					  <td>     
					  <div class="Table_Content">     
					 <div class="Table_Content_Info">无库存</div> 
					 </div>    
					 </td>    
					 <td>     
					 <div class="Table_Content">    
					 <div class="Table_Content_Info">${noStock }</div>     
					 </div>    
					 </td>    
					 </tr>        
			 </tbody>
			 </table>
			 </div>  
			 </div>      
			  </div>      
			  </div>     
			  </td>   
			   </tr>   
			    <tr>    
			   </tr>  
			    </tbody>
			   </table> 
			  </div>    
			   <div style="right: 4px;" class="toolbar_questionnaire"><div class="ButtonBg">   
			 </div>
			 </div>
			 </div>
			</div>
		</div>
	</div>

	<%@ include file="../index/foot.jsp"%>
	<script type="text/javascript" src="static/ace/js/ace/ace.js"></script>
	<script type="text/javascript" src="static/ace/js/jquery.js"></script>
	<script type="text/javascript">
		$(top.hangge());
		// 提示隐藏显示
		$(".j_prompt_hover").mouseover(function(){
			$(this).parent().next().show();
		});
		$(".j_prompt_hover").mouseout(function(){
			$(this).parent().next().hide();
		});
		function updateSystem(){
			 top.jzts();
			 window.location.href="<%=basePath%>updateSystem.do";
		}
	</script>
</body>
</html>