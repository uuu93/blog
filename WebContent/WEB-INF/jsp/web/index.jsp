<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="utf-8" />
  <base href="<%=basePath%>">
<title>Zqy</title> 

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="applijegleryion/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="static/blog/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<script type="text/javascript" src="static/blog/js/jquery-1.11.1.min.js"></script>
<link href="static/blog/css/style.css" rel='stylesheet' type='text/css' />	
<!--webfonts-->
<link href='http://fonts.useso.com/css?family=Candal|Raleway:500,600,400' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script type="text/javascript" src="static/blog/js/move-top.js"></script>
<script type="text/javascript" src="static/blog/js/easing.js"></script>
<!--/script-->
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
				});
			});
</script>
</head> 
 
<body> 
  
  
  
  <!--start-home-->
					<ul class="side_nav">
							<li><a class="active scroll" href="#home"></a></li>
								<li><a class="scroll" href="#about"></a></li>
								<li><a class="scroll" href="#experience"></a></li>
								<li><a class="scroll" href="#skill"></a></li>
								<li><a class="scroll" href="#project"></a></li>
								<li><a class="scroll" href="#port"></a></li>
								<li><a class="scroll" href="#contact"></a></li>
					</ul>
		<div class="banner" id="home">
			<div class="header-top">
				<div class="container">	
					<!--top-nav-->
					<span class="menu"> </span>
					<div class="top-menu">
						<nav>
							<ul class="cl-effect-16">
								<li><a class="active scroll" href="#home" data-hover="Home">Home</a></li>
								<li><a class="scroll" href="#about" data-hover="About">About</a></li>
								<li><a class="scroll" href="#experience" data-hover="experience">experience</a></li>
								<li><a class="scroll" href="#skill" data-hover="Skills">Skills</a></li>
								<li><a class="scroll" href="#project" data-hover="Projects">Projects</a></li>
								<li><a class="scroll" href="#port" data-hover="Portfolio">Portfolio</a></li>
								<li><a class="scroll" href="#contact" data-hover="Contact">Contact</a></li>
								<div class="clearfix"></div>
							</ul>
						</nav>		
					</div>
					<div class="login-pop">
						<div id="loginpop"><a href="#" id="loginButton"><span>Login <i class="arrow glyphicon glyphicon-chevron-right"></i></span></a>
								<div id="loginBox">                
									<form id="loginForm">
											<fieldset id="body">
												<fieldset>
													  <label for="email">Email Address</label>
													  <input type="text" name="email" id="email">
												</fieldset>
												<fieldset>
														<label for="password">Password</label>
														<input type="password" name="password" id="password">
												 </fieldset>
												<input type="submit" id="login" value="Sign in">
												<label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label>
											</fieldset>
										<span><a href="#">Forgot your password?</a></span>
								 </form>
							</div>
					    </div>
					</div><script src="js/menu_jquery.js"></script>

				<!-- script-for-menu -->
								<script>
									$("span.menu").click(function(){
										$(".top-menu ul").slideToggle("slow" , function(){
										});
									});
								</script>
								<!-- script-for-menu -->

				 <div class="clearfix"></div>
			<!-- script-for-menu -->
		    </div>
		  </div>
		<!--banner-->	
		<div class="container">	
		      <!-- script-for-menu -->	  	 
				  <div class="banner-info">	
						 <div class="logo">
							  <a href="index.html"><h1>Personale </h1></a>
						  </div>
						<p> Donec rhoncus bibendum tempor.Lorem Ipsum Maecenas tristique orci ac sem.<lable> Duis ultricies pharetra magna. Donec accumsan malesuada orci. Donec sit amet eros. Lorem ipsumipsum dolor sit amet,</lable> consectetuer dolor sit.</p>
						<div class="scroll-down">
						 <h4>SCROLL DOWN</h4>
						<a class="downarrow scroll" href="#about"><span></span></a>
						</div>
				  </div>
			</div>
		</div>
	<!--about-->
	<div class="about" id="about">
	   <div class="container">
	   <h3 class="tittle">About Me</h3>
	      <div class="col-md-5 ab-grid pic">
		  <img src="static/blog/images/ab.jpg" alt=" " title="ab">
		  </div>
		  <div class="col-md-7 ab-grid text">
		    <h4>I am jessica, I live in Newyork. I am WP Theme Developer, UI/UX Developer, Internet Consultant.</h4>
			<p>We are a professional Software comapny with the best talent and most modern technology for online solutions & focused on providing clients with effective online marketing solutions.</p>
			<p>We focused on providing clients with effective online marketing solutions.</p>
			<p>I know 4 languages. Working on this field for 5+ years and got many design awards.</p>
			 
		  </div>
		  <div class="clearfix"> </div>
	   </div>
    </div>	   
	<!--//about-->
	<!--experience-starts-->
	<div class="experience" id="experience">
		<div class="container">
				<h3 class="tittle four">Education & Experience</h3>
				<div class="experience-main">
					<span class="devide-line"></span>
					<div class="experience-left">
						<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
						<h4>MASTER OF FINE ARTS</h4>
						<h5>DESIGN UNIVERSITY [ 2011 - 2013 ]</h5>
						<p>Sed a tortor sed ipsum porta iaculis id eget massa. Quisque imperdiet urna sed lorem facilisis, id imperdiet magna dignissim. Maecenas quis dui at magna ullamcorper suscipit. In malesuada, massa at semper fermentum, metus eros imperdiet justo</p>
					</div>
					<div class="experience-left-snd">
						<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
					</div>
					<div class="experience-right-snd">
						<h4>DESIGN CORPORATION</h4>
						<h5>UX DEVELOPER [ 2013 - 2014 ]</h5>
						<p>Lorem ipsum dolor sit amet, rebum dolore labores cu pri. Ferri iudico scripta ut eam, diceret euismod gubergren has eu, an quo tale vivendum. Ad quidam gubergren vituperatoribus sit. Ius etiam nemore consulatu ne, at meliore explicari conceptam qui.</p>
					</div>
					<div class="experience-left-trd">
						<span class="glyphicon glyphicon-gift" aria-hidden="true"></span>
						<h4>CREATIVE DESIGN STUDIO</h4>
						<h5>UX DEVELOPER [ 2016 - 2018 ]</h5>
						<p>Morbi porta eros diam, vel dictum mauris tristique cursus. Curabitur at feugiat nulla. Morbi facilisis vulputate ligula non dignissim. Nunc sagittis non tellus eu hendrerit. Aenean quis enim tristique, lobortis ligula non, sollicitudin tortor.</p>
					</div>
					<div class="clearfix"> </div>
				</div>
		</div>
	</div>
	<!--experience-end-->
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >手机网站模板</a></div>
<!--my-skill-->
  <div class="my-skills text-center" id="skill">
	<div class="container">
		<h3 class="tittle">My Skills</h3>
		<div class="skill-grids">
					<div class="col-md-2 skills-grid text-center">
						<div class="circle" id="circles-1"></div>
						<p>Photoshop</p>
					</div>
					<div class="col-md-2 skills-grid text-center">
						<div class="circle" id="circles-2"></div>
						<p>Illustrator</p>
					</div>
					<div class="col-md-2 skills-grid text-center">
						<div class="circle" id="circles-3"></div>
						<p>Photography</p>
					</div>
					<div class="col-md-2 skills-grid text-center">
						<div class="circle" id="circles-4"></div>
						<p>Html 5 / CSS 3</p>
					</div>
					<div class="col-md-2 skills-grid text-center">
						<div class="circle" id="circles-5"></div>
						<p>C4D</p>
					</div>
					<div class="col-md-2 skills-grid text-center">
						<div class="circle" id="circles-6"></div>
						<p>After Effect</p>
					</div>
					<div class="clearfix"> </div>
				 <script type="text/javascript" src="static/blog/js/circles.js"></script>
					         <script>
								var colors = [
										['#f6608a', '#ffffff'], ['#f6608a', '#ffffff'], ['#f6608a', '#ffffff'], ['#f6608a', '#ffffff'], ['#f6608a', '#ffffff'], ['#f6608a', '#ffffff']
									];
								for (var i = 1; i <= 7; i++) {
									var child = document.getElementById('circles-' + i),
										percentage = 30 + (i * 10);
										
									Circles.create({
										id:         child.id,
										percentage: percentage,
										radius:     80,
										width:      10,
										number:   	percentage / 1,
										text:       '%',
										colors:     colors[i - 1]
									});
								}
						
				</script>
		</div>
	</div>	
</div>
<!--//my-skill-->
<!-- projects -->
		<div class="projects" id="project">
			<div class="container">
				<div class="project-grids">
					<div class="cycle">
						<span> </span>
					</div>
					<div class="project-grid total-project">
						<p>79</p>
						<h4>Projects</h4>
						<span> <i class="glyphicon glyphicon-edit"> </i></span>
					</div>
					<div class="project-grid clientsgrid">
						<div>
						<p>25</p>
						<h4>Clients</h4>
						</div>
						<span> <i class="glyphicon glyphicon-user"> </i></span>
					</div>
					<div class="project-grid people-grid">
						<div>
						<p>100%</p>
						<h4>Satisfaction</h4>
						</div>
						<span> <i class="glyphicon glyphicon-thumbs-up"> </i></span>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
		</div>
		<!-- projects -->
		<!--portfolio-->
		<script src="js/jquery.chocolat.js"></script>
			<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen" charset="utf-8">
			<!--light-box-files -->
			<script type="text/javascript" charset="utf-8">
			$(function() {
				$('.gallery a').Chocolat();
			});
			</script>

	<div class="gallery" id="port">
		<div class="container">
			<h3 class="tittle port">Portfolio</h3>
		   <div class="gallery-bottom">
					<div class="col-md-4 bottom-gallery">
						<a href="static/blog/images/g1.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
						<img class="img-responsive" src="static/blog/images/g1.jpg" />
						<div class="b-wrapper">
						  <h3 class="b-animate b-from-left    b-delay03 "> </h3>
						</div>
	                    </a>
					</div>
					<div class="col-md-4 bottom-gallery">
						<a href="static/blog/images/g2.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
						<img class="img-responsive" src="static/blog/images/g2.jpg" />
						<div class="b-wrapper">
						  <h3 class="b-animate b-from-left    b-delay03 "> </h3>
						</div>
	                      </a>
					</div>
					<div class="col-md-4 bottom-gallery">
						<a href="static/blog/images/g3.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
						<img class="img-responsive" src="static/blog/images/g3.jpg" />
						<div class="b-wrapper">
						  <h3 class="b-animate b-from-left    b-delay03 "> </h3>
						</div>
	                      </a>
					</div>
					<div class="col-md-4 bottom-gallery">
						<a href="static/blog/images/g4.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
						<img class="img-responsive" src="static/blog/images/g4.jpg" />
						<div class="b-wrapper">
						  <h3 class="b-animate b-from-left    b-delay03 "> </h3>
						</div>
	                      </a>
					</div>
					<div class="col-md-4 bottom-gallery">
						<a href="static/blog/images/g5.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
						<img class="img-responsive" src="static/blog/images/g5.jpg" />
						<div class="b-wrapper">
						  <h3 class="b-animate b-from-left    b-delay03 "> </h3>
						</div>
	                      </a>
					</div>
					<div class="col-md-4 bottom-gallery">
						<a href="static/blog/images/g6.jpg" rel="title" class="b-link-stripe b-animate-go  thickbox">
						<img class="img-responsive" src="static/blog/images/g6.jpg" />
						<div class="b-wrapper">
						  <h3 class="b-animate b-from-left    b-delay03 "> </h3>
						</div>
	                      </a>
					</div>
					<div class="clearfix"> </div>
			</div>
			
		</div>
	</div>
		<script src="static/blog/js/jquery.chocolat.js"></script>
		<link rel="stylesheet" href="static/blog/css/chocolat.css" type="text/css" media="screen" charset="utf-8">
		<!--light-box-files -->
		<!-- Javascript calls -->
		<script type="text/javascript" charset="utf-8">
				$(function() {
					$('#port a').Chocolat({overlayColor:'#000',leftImg:'static/blog/images/leftw.gif',rightImg:'static/blog/images/rightw.gif',closeImg:'static/blog/images/closew.gif'});
				});
		</script>

<!--//portfolio-->

 <!--start-contact-->
	 <div class="contact_desc" id="contact">
		        <div class="container">
		        	<h3 class="tittle">Contact Me</h3>
			         <div class="contact-form">
				  	   <form class="left_form">
					    	<div>
						    	<span><label>NAME</label></span>
						    	<span><input name="userName" type="text" class="textbox"></span>
						    </div>
						    <div>
						    	<span><label>E-MAIL</label></span>
						    	<span><input name="userEmail" type="text" class="textbox"></span>
						    </div>
						    <div>
						     	<span><label>Fax</label></span>
						    	<span><input name="userPhone" type="text" class="textbox"></span>
						    </div>
					    </form>
					    <form class="right_form">
					        <div>					    	
						    	<span><label>SUBJECT</label></span>
						    	<span><textarea name="userMsg"> </textarea></span>
						    </div>
						   <div>
						   		<input type="submit" value="Submit " />
						  </div>
					    </form>
					    <div class="clearfix"></div>
				  </div>
				  </div>
			</div>

		   <div class="contact" id="contact">
		         <div class="col-md-6 contact-top">
				     	<div class="point"> <i class="glyphicon glyphicon-map-marker"></i></div>
					  <h3 class="tittle two">Find Me</h3>
				      <h4>For Any questions, Please feel free to Contact me by mail.</h4>
					     <div class="contact-ad">
								<p>Address:Newyork Still Road.756 gt globel Place.<p>
								<p>Phone:Newyork Still Road.756 gt globel Place.<p>
								<p>E-mail :<a href="mailto:info@example.com">mail@example.com</a></p>

					     </div>
			      </div>
				  <div class="col-md-6 map">
						<div class="black"> 
						  <div class="map-l"> <i class="glyphicon glyphicon-map-marker"></i></div>
						</div>
					</div>
					<div class="clearfix"> </div>
	       </div>
	<!--//contact-->


	<!--footer-->
	<div class="copy">
		<p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
	</div>
							<!--start-smoth-scrolling-->
						<script type="text/javascript">
									$(document).ready(function() {
										/*
										var defaults = {
								  			containerID: 'toTop', // fading element id
											containerHoverID: 'toTopHover', // fading element hover id
											scrollSpeed: 1200,
											easingType: 'linear' 
								 		};
										*/
										
										$().UItoTop({ easingType: 'easeOutQuart' });
										
									});
								</script>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
  
  
  
</body> 
</html>
