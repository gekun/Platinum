function onload(){
	if (($.browser.msie) && ($.browser.version == "6.0")){
		document.getElementById("MainBox").style.height="590px";
	}
}
$(document).ready(function(){
	if (($.browser.msie) && ($.browser.version == "6.0")){
		$("ul.TextList li a div").css({"display":"inline"});
		$("#sideBar").css({"width":"260px"});
		$("#imageContent").css({"margin-left":"10px"});
	}
});

function jiaowu(){
	if (($.browser.msie) && ($.browser.version == "6.0")){
		$("#header #_logo").css({
			"margin":"35px 0 0 0",
			"height":"60px",
			"width":"700px",
			"background:url":"(/static/jiaowu/images/logo.png) no-repeat",
			"float":"left"
		});
		$("#header #link_search").css({
			"margin":"40px 10px 0 0",
			"height":"60px",
			"width":"200px",
			"float":"right"
		});
		$("#link_search #search").css({
			"padding":"0",
			"margin":"10px 0 0 0",
			"width":"160px",
			"height":"22px",
			"background":"url(/static/jiaowu/images/textfield.png) no-repeat"
		});
		$("#ja-mainnav ul").css({
			"background-color":"#155897",
			"padding-left":"20px"
		});
		$("#ja-mainnav ul li").css({
			"border-left":"1px solid #104c96",
			"border-right":"1px solid #2d759b",
			"margin-right":"1px"
		});
		$("#ja-mainnav ul li.active").css({
			"background":"url(/static/jiaowu/images/hover.png) no-repeat bottom center #444444",
			"color":"#CCCCCC"
		});
		$("#bottom2 span a").css({
			"color":"#fff",
			"text-decoration":"none"
		});
		$("#bottom2 span a:hover").css({
			"color":"#7BA566"
		});
		$("#bodyContent #more #postList").css({
			"width":"100%",
			"list-style-image":"none"
		});
		$("#tupianList #post1").css({"display":"block"});
		$("#post1 .bx-caption").css({"display":"block"});
		$("#home_slider ul li").css({
			"display":"none",
			"width":"333px",
			"height":"273px",
			"list-style":"none",
			"margin":"0",
			"padding":"0"
		});
		$("#left_part ul li").css({"height":"37px"});
		$(".category_1").css({"height":"283px"});
		$("#sideBar").css({"height":"650px","position":"relative","float":"right"});
	}
}
function xuegong(){
	if (($.browser.msie) && ($.browser.version == "6.0")){
		$("#collegelogo img").attr({ src: "../../../static/xuegong/images/logoie6.png"});
		$("#secondlogo img").attr({ src: "../../../static/xuegong/images/xuegongie6.png"});
		$("#xgcontent #mainContent #gongNews li").css({ "background-image":"url(../../../static/xuegong/images/listie6.png)"});
		$("#xgcontent #mainContent #email li").css({ "background-image":"url(../../../static/xuegong/images/listie6.png)"});
		$("#xgcontent #mainContent #informs li").css({ "background-image":"url(../../../static/xuegong/images/listie6.png)"});
		$("#xgcontent #mainContent #teaching li").css({ "background-image":"url(../../../static/xuegong/images/listie6.png)"});
		$("#sideBar").css({"height":"650px","position":"relative","float":"right"});
		$("#more").css({"width":"692px"});
		$("#postDetail").css({"float":"left"});
		$("#bodyContent #more").css({"position":"absolute","top":"0"});
	}
}
function tuanwei(){
	if (($.browser.msie) && ($.browser.version == "6.0")){
		$("#browsable").css({"margin-left":"0px"});
		$("#linkLabel ul").css({"width":"100%"});
		$("#sideBar").css({"height":"650px","position":"relative","float":"right"});
		$("#more").css({"height":"646px"});
		$("#postList li span").css({"padding-right":"15px"});
	}
}