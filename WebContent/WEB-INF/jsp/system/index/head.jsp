﻿<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed');
		} catch (e) {
		}

		function changelocale(u) {
			$.ajax({
				url : u,
				success : function(data) {
					location.reload();
				}
			});
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<!-- #section:basics/sidebar.mobile.toggle -->
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

		<!-- /section:basics/sidebar.mobile.toggle -->
		<div class="navbar-header pull-left">
			<!-- #section:basics/navbar.layout.brand -->
			<a class="navbar-brand"> 
				<small> <i class="fa fa-leaf"></i>
					${pd.SYSNAME}
				</small>
			</a>
			<!-- /section:basics/navbar.layout.brand -->
			<!-- #section:basics/navbar.toggle -->
			<!-- /section:basics/navbar.toggle -->
		</div>

		<!-- #section:basics/navbar.dropdown -->
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="grey" title="语言环境">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
						<i class="ace-icon fa fa-tasks"></i>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-footer">
							<a href="javascript:changelocale('common/locale/changeLocale?locale=zh_CN')">
								中文(简体) <i class="ace-icon fa fa-arrow-right"></i>
							</a> 
							<a href="javascript:changelocale('common/locale/changeLocale?locale=zh_TW')">
								中文(繁體) <i class="ace-icon fa fa-arrow-right"></i>
							</a> 
							<a href="javascript:changelocale('common/locale/changeLocale?locale=en_US')">
								English <i class="ace-icon fa fa-arrow-right"></i>
							</a> 
							<a href="javascript:changelocale('common/locale/changeLocale?locale=ja_JP')">
								日本語の <i class="ace-icon fa fa-arrow-right"></i>
							</a> 
							<a href="javascript:changelocale('common/locale/changeLocale?locale=ms_MY')">
								Melayu <i class="ace-icon fa fa-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>
				<!-- #section:basics/navbar.user_menu -->
				<li class="light-blue">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
						<img class="nav-user-photo" src="static/images/logo.png" alt="Jason's Photo" /> 
						<span class="user-info" id="user_info"> </span> 
						<i class="ace-icon fa fa-caret-down"></i>
					</a>
					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a onclick="editUserH();" style="cursor: pointer;"><i class="ace-icon fa fa-cog"></i>修改资料</a> 
							<!-- editUserH()在 WebRoot\static\js\myjs\head.js中 -->
						</li>
						<li id="systemset">
							<a onclick="editSys();" style="cursor: pointer;"><i class="ace-icon fa fa-user"></i>系统设置</a>
							<!-- editSys()在 WebRoot\static\js\myjs\head.js中 --></li>
						<li class="divider"></li>
						<li>
							<a href="logout"><i class="ace-icon fa fa-power-off"></i>退出登录</a>
						</li>
					</ul>
				</li>
				<!-- /section:basics/navbar.user_menu -->
			</ul>
		</div>
		<!-- /section:basics/navbar.dropdown -->
	</div>
	<!-- /.navbar-container -->
</div>
