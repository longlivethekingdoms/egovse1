<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/template/header.do" charEncoding="utf-8">
	<c:param name="title" value="로그인"/>
	<c:param name="css" value="/asset/member/css/login.css"/>
	<c:param name="commonCssUseAt" value="Y"/>
</c:import>
<h2 class="icon1">로그인</h2>
<p class="mB20">스마트소프트웨어 하이테크과정 홈페이지 방문을 환영합니다.</p>

<div class="login_box">
	<div class="mem_login">
		<div class="smartshool login">
			<div class="login inp_wrap">
				<div class="login_inp">
					<form action="/login/actionlogin.do" name="frmGnrllogin" method="post" id="frmGnrllogin" onsubmit="return checkGnrlLogin(this)">
						<fieldset>
							<legend>로그인 입력폼</legend>
							<span>
								<label for="id"><img src="/asset/member/images/member/ico_id.svg" alt="아이디"></label>
								<input type="text" id="id" name="id" value="" class="inp" title="아이디를 입력하세요." placeholder="아이디를 입력하세요.">
							</span>
							<span>
								<label for="pwd"><img src="/asset/member/images/member/ico_pw.svg" alt="패스워드"></label>
								<input type="password" id="pwd" name="password" value="" class="inp" title="비밀번호를 입력하세요." placeholder="비밀번호를 입력하세요." autocomplete="off">
							</span>
							
							<button type="submit" class="btn-lg spot p10">로그인</button>
							
							<div class="btn-cont">
								<a class="btn-kakao" href="#" data-type="login">
									<img src="/asset/front/images/common/btn-kakao.png" width="200" alt="카카오 로그인 버튼"/>
								</a>
							</div>
						</fieldset>
					</form>
				</div>
			
				<ul>
					<li><p>회원으로 가입하시겠습니까?</p><a href="/join/siteUseAgree.do">회원가입</a></li>
					<li><p>아이디를 잊으셨나요?</p><a href="/member/findId.do">아이디찾기</a></li>
					<li><p>회원으로 가입하시겠습니까?</p><a href="/member/findPassword.do">패스워드 찾기</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<script>
<c:if test="${not empty message}">
	alert("${message}");
</c:if>

<c:if test="${not empty loginMessage}">
	alert("${loginMessage}");
</c:if>

function checkGnrlLogin(frm){
	if(frm.id.value == ""){
		alert("아이디를 입력해주세요");
		frm.id.focus();
		return false;
	}
	
	if(frm.pwd.value == ""){
		alert("비밀번호를 입력해주세요");
		frm.pwd.focus();
		return false;
	}
	
	$("#frmGnrlLogin");
}

$(document).ready(function(){
	//아이디 입력 창 포커스 설정
	$('#id').focus();
});
</script>

<form id="frmLogin" name="frmLogin" method="post" action="/login/actionlogin.do">
	<input type="hidden" name="loginType" value=""/>
	<input type="hidden" id="snsid" name="id"/>
</form>

<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
	$(document).ready(function(){
		$(".btn-kakao").click(function(){
			const type = $(this).data("type");
			kakaoLogin(type);
			return false;
		});
	});
	
	Kakao.init('4bd2b571d07744f19087d35e8f5be9da');
	
	Kakao.isInitialized();
	
	function kakaoLogin(type){
		Kakao.Auth.login({
			success:function(response){
				Kakao.Auth.login({
					success:function(response){
						Kakao.API.request({
							url:'/v2/user/me',
							success:function(response){
								console.log(response)
								$("input[name=loginType]").val("KAKAO");
								$("#snsid").val(response.id);
								$("#frmLogin").submit();
							},
							fail: function(error){
								console.log(error)
							},
						})
					},fail: function(error){
						console.log(error)
					},
				})
			}
		})
	}
</script>
<c:import url="/template/footer.do" charEncoding="utf-8"/>