<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/template/header.do" charEncoding="utf-8">
	<c:param name="title" value="회원가입"/>
	<c:param name="css" value="/asset/member/css/login.css"/>
	<c:param name="headerUseAt" value="N"/>
</c:import>

<div class="tit_intro_step">
	<ul>
		<li>약관동의</li>
		<li class="current">회원유형</li>
		<li>정보입력</li>
		<li>가입완료</li>
	</ul>
</div>

<h2 class="icon1">회원유형 선택</h2>

<form id="frm" name="frm" action="/join/memberRegist.do" method="post">
	<input type="hidden" name="agree01" value="${searchVO.agree01}"/>
	<input type="hidden" name="agree02" value="${searchVO.agree02}"/>
	<input type="hidden" name="agree03" value="${searchVO.agree03}"/>
	<input type="hidden" name="loginType" value="normal"/>
	
	<div class="confirm-area user-area">
		<article>
			<h3 class="icon2 ico-user">일반회원</h3>
			<div class="confirm_box">
				<p class="mB20">일반회원</p>
				<div class="btn-cont">
					<a href="/join/memberRegist.do?loginType=normal" class="btn spot member-type btn-regist"><span>회원가입</span></a>
				</div>
			</div>
		</article>
		
		<article>
			<h3 class="icon2 ico-user">SNS회원</h3>
			<div class="confirm_box">
				<p class="mB20">카카오 회원</p>
				<div class="btn-cont">
					<a class="btn-kakao" href="#" data-type="join">
						<img src="/asset/front/images/common/btn-kakao.png" width="150" alt="카카오 로그인 버튼"/>
					</a>	
				</div>
			</div>
		</article>
	</div>
</form>

<script>
	$(document).ready(function(){
		$(".btn-regist").click(function(){
			$("#frm").submit();
			return false;
		});
	});
	
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
	
	<c:if test="${not empty loginMessage}">
		alert("${loginMessage}");
	</c:if>
</script>

<form id="joinFrm" name="joinFrm" method="post" action="/join/insertMember.do">
	<input type="hidden"name="loginType" value=""/>
	<input type="hidden"name="emplyrId"/>
	<input type="hidden"name="userNm"/>
	<input type="hidden"name="emailAdres"/>
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

function kakaoLogin(type) {
	Kakao.Auth.login({
		success: function (response) {
			Kakao.API.request({
				url:'/v2/user/me',
				success: function (response) {
					console.log(response)
					$("input[name=loginType]").val("KAKAO");
					$("input[name=emplyrId]").val(response.id);
					$("input[name=userNm]").val(response.properties.nickname);
					$("input[name=emailAdres]").val(response.kakao_account.email);
					$("#joinFrm").submit();
				},
				fail: function (error){
					console.log(error)
				},
			})
		}, fail: function(error){
			console.log(error)
		},
	})
}

</script>
<c:import url="/template/footer.do" charEncoding="utf-8"/>