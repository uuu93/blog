/**
 * 
 */
(function(){
	
	var _key="私有变量。如有需要后面可加上key实现加密";
	window.Shuhua = {
			_bashpath:"/jee",//TODO 第一次加载js时从后台获取
			/**
			 * get/post/put/delete请求。动态参数
			 * <br />0：请求地址。类型：String 默认值: 当前页地址。发送请求的地址。
			 * <br />1：成功回调。类型：Function 请求成功后的回调函数。参数：由服务器返回，并根据 dataType 参数进行处理后的数据；描述状态的字符串。这是一个 Ajax 事件。
			 * <br />2：失败回调。类型：Function 默认值: 自动判断 (xml 或 html)。请求失败时调用此函数。有以下三个参数：XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象。如果发生了错误，错误信息（第二个参数）除了得到 null 之外，还可能是 "timeout", "error", "notmodified" 和 "parsererror"。这是一个 Ajax 事件。
			 * <br />3：请求数据。类型：String 发送到服务器的数据。将自动转换为请求字符串格式。GET 请求中将附加在 URL 后。查看 processData 选项说明以禁止此自动转换。必须为 Key/Value 格式。如果为数组，jQuery 将自动为不同值对应同一个名称。如 {foo:["bar1", "bar2"]} 转换为 '&foo=bar1&foo=bar2'。
			 * <br />4：是否异步。类型：Boolean 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
			 * <br />5：返回的数据类型。类型：String 预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如 XML MIME 类型就被识别为 XML。在 1.4 中，JSON 就会生成一个 JavaScript 对象，而 script 则会执行这个脚本。随后服务器端返回的数据会根据这个值解析后，传递给回调函数。可用值:"xml": 返回 XML 文档，可用 jQuery 处理。"html": 返回纯文本 HTML 信息；包含的 script 标签会在插入 dom 时执行。"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了 "cache" 参数。注意：在远程请求时(不在同一个域下)，所有 POST 请求都将转为 GET 请求。（因为将使用 DOM 的 script标签来加载）"json": 返回 JSON 数据 。"jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。"text": 返回纯文本字符串
			 * <br />6：请求的数据类型。类型：String 默认值: "application/x-www-form-urlencoded"。发送信息至服务器时内容编码类型。默认值适合大多数情况。如果你明确地传递了一个 content-type 给 $.ajax() 那么它必定会发送给服务器（即使没有数据要发送）。
			 * <br />7：请求超时时间。类型：Number 设置请求超时时间（毫秒）。此设置将覆盖全局设置。
			 * <br />8：请求头数组。类型：Array 设置自定义请求头
			 */
			doGet:function(){
				var options = {
						type: "GET"
				};
				_realReq(arguments,options);
			},
			doPost:function(){
				var options = {
						type: "POST"
				};
				_realReq(arguments,options);
			},
			doPut:function(){
				var options = {
						type: "PUT"
				};
				_realReq(arguments,options);
			},
			doDelete:function(){
				var options = {
						type: "DELETE"
				};
				_realReq(arguments,options);
			},
			/**
			 * get/post/put/delete的表单提交。动态参数
			 * <br />0：表单ID。类型：String 要提交的表单ID
			 * <br />1：成功回调。类型：Function 请求成功后的回调函数。参数：由服务器返回，并根据 dataType 参数进行处理后的数据；描述状态的字符串。这是一个 Ajax 事件。
			 * <br />2：失败回调。类型：Function 默认值: 自动判断 (xml 或 html)。请求失败时调用此函数。有以下三个参数：XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象。如果发生了错误，错误信息（第二个参数）除了得到 null 之外，还可能是 "timeout", "error", "notmodified" 和 "parsererror"。这是一个 Ajax 事件。
			 * <br />3：是否异步。类型：Boolean 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
			 * <br />4：返回的数据类型。类型：String 预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如 XML MIME 类型就被识别为 XML。在 1.4 中，JSON 就会生成一个 JavaScript 对象，而 script 则会执行这个脚本。随后服务器端返回的数据会根据这个值解析后，传递给回调函数。可用值:"xml": 返回 XML 文档，可用 jQuery 处理。"html": 返回纯文本 HTML 信息；包含的 script 标签会在插入 dom 时执行。"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了 "cache" 参数。注意：在远程请求时(不在同一个域下)，所有 POST 请求都将转为 GET 请求。（因为将使用 DOM 的 script标签来加载）"json": 返回 JSON 数据 。"jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。"text": 返回纯文本字符串
			 * <br />5：请求超时时间。类型：Number 设置请求超时时间（毫秒）。此设置将覆盖全局设置。
			 * <br />6：请求头数组。类型：Array 设置自定义请求头
			 */
			doGetForm:function(){
				var options = {
						type: "GET"
				};
				var rt = _initFormDataR(arguments);
				_realReq("doGetForm",rt,options);
			},
			doPostForm:function(){
				var options = {
						type: "POST"
				};
				var rt = _initFormDataR(arguments);
				_realReq("doPostForm",rt,options);
			},
			doPutForm:function(){
				var options = {
						type: "PUT"
				};
				var rt = _initFormDataR(arguments);
				_realReq("doPutForm",rt,options);
			},
			doDeleteForm:function(){
				var options = {
						type: "DELETE"
				};
				var rt = _initFormDataR(arguments);
				_realReq("doDeleteForm",rt,options);
			},
			/**
			 * 根据表单ID，转换为json对象
			 * @param formId 表单ID（表单元素务必添加name值）
			 * @returns 转换之后的json对象
			 */
			formToJson:function(formId){
				var f = $("#"+formId);
				var serializeObj={};
				if(f.length>0){
					var array = f.serializeArray();
					//var str = f.serialize();
					$(array).each(function(){
						if(serializeObj[this.name]){
							if($.isArray(serializeObj[this.name])){
								serializeObj[this.name].push(this.value);
							}else{
								serializeObj[this.name]=[serializeObj[this.name],this.value];
							}
						}else{
							serializeObj[this.name]=this.value;
						}	
					});
				}else{
					console.error("Shuhua.formToJson:表单ID不存在!");
					return ;
				}
				return serializeObj;
			},
			/**
			 * 页面跳转(当前窗口跳转)
			 * @param url 页面路径（不带后缀）
			 */
			toPage:function(url){
				window.location=this._bashpath+'/view?p='+url;
			},
			/**
			 * 页面跳转（新窗口打开）
			 * @param url 页面路径（不带后缀）
			 */
			toPageForNW:function(url){
				window.open(this._bashpath+'/view?p='+url);
			},
			/**
			 * 新菜单打开页面(仅fh admin框架，且必须需要引入static/js/myjs/head.js)
			 * @param id 标识id
			 * @param fid 选中的状态id
			 * @param menu_name 新菜单窗口名称
			 * @param menu_url 新页面路径（不带后缀）
			 */
			toPageForMenu:function(id,fid,menu_name,menu_url){
				siMenu(id,fid,menu_name,this._bashpath+'/view?p='+menu_url)
			},
			/**
			 * 根据data中的key值，动态渲染页面中指定name的元素值
			 * @param data 要渲染的json对象
			 */
			renderJsonByName:function(data){
				for(var key in data){
					$("[name='"+key+"']");
					var value = data[key];
				}
			}
		};
	
	_initFormDataR = function(_arguments){
		var al = _arguments.length;
		if(al == 0){
			console.error("没有传递任何参数！");
			return ;
		}
		var data = this.formToJson(_arguments[0]);
		var url = $("#"+_arguments[0]).attr("action");
		var args = new Array();
		args.push(url);
		args.push(al>=2&&_arguments[1]?_arguments[1]:"");
		args.push(al>=3&&_arguments[2]?_arguments[2]:"");
		args.push(data);
		args.push(al>=4&&_arguments[3]?_arguments[3]:"");
		args.push(al>=5&&_arguments[4]?_arguments[4]:"");
		args.push("json");
		args.push(al>=6&&_arguments[5]?_arguments[5]:"");
		args.push(al>=7&&_arguments[6]?_arguments[6]:"");
		return args;
	};
	//最终请求
	_realReq = function(my_arguments,options){
		var al = my_arguments.length;
		if(al == 0){
			console.error("没有传递任何参数！");
			return ;
		}
		al>=1&&my_arguments[0]?options.url = my_arguments[0]:"";
		al>=2&&my_arguments[1]?options.success = my_arguments[1]:"";
		al>=3&&my_arguments[2]?options.error = my_arguments[2]:"";
		al>=4&&my_arguments[3]?options.data = my_arguments[3]:"";
		al>=5&&my_arguments[4]?options.async = my_arguments[4]:"";
		al>=6&&my_arguments[5]?options.dataType = my_arguments[5]:"";
		al>=7&&my_arguments[6]?options.contentType = my_arguments[6]:"";
		al>=8&&my_arguments[7]?options.timeout = my_arguments[7]:"";
		al>=9&&my_arguments[8]?options.beforeSend = function(request){
			//TODO 获取所有请求参数，按字母排序，然后和_key一起md5加密，然后传递到后台
			request.setRequestHeader("sign", this._key);
			//封装请求头
			$(my_arguments[8]).each(function(){
				request.setRequestHeader(this.name, this.value);
			});
		}:"";
		$.ajax(options);
	};
	
})();



//Shuhua.doGet();

