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
		<li class="current">약관동의</li>
		<li>회원유형</li>
		<li>정보입력</li>
		<li>가입완료</li>
	</ul>
</div>

<h2 class="icon1">약관동의</h2>

<p>약관 및 개인정보 수집 · 이용 및 제 3자 제공 동의에 대한 내용을 자세히 읽어 보신 후 동의 여부를 결정해 주시기 바랍니다.</p>

<div class="agree_box">
	<form id="agreeFrm" name="agreeFrm" action="/join/memberType.do" method="post">
	<h3 class="icon2"> 이용약관(필수)</h3>
	<div class="cont" tabindex="0">
		이용약관 내용 ~
	</div>
	<div class="check">
		필수(
			<input type="radio" class="vMid" id="agree01" name="agree01" value="Y">
			<label for="agree01">동의</label>
			<input type="radio" class="vMid" id="agree01-1" name="agree01" value="N" checked="checked">
			<label for="agree01-1">비동의</label>
		)
	</div>
	
	<h3 class="icon2">개인정보 수집·이용(필수)</h3>
	<div class="cont" tabindex="0">
		개인정보 수집·이용 내용~
	</div>
	<div class="check">
		필수(
			<input type="radio" class="vMid" id="agree02" name="agree02" value="Y">
			<label for="agree02">동의</label>
			<input type="radio" class="vMid" id="agree02-1" name="agree02" value="N" checked="checked">
			<label for="agree02-1">비동의</label>
		)
	</div>
	
	<h3 class="icon2">개인정보 제 3자 제공 및 위탁에 관한 안내</h3>
	<div class="cont" tabindex="0">
		개인정보 제 3자 제공 및 위탁에 관한 안내 내용~
	</div>
	<div class="check">
		선택(
			<input type="radio" class="vMid" id="agree03" name="agree03" value="Y">
			<label for="agree03">동의</label>
			<input type="radio" class="vMid" id="agree03-1" name="agree03" value="N" checked="checked">
			<label for="agree03-1">비동의</label>
		)
	</div>
	</form>
</div>

<div class="agree_all">
	<label><input type="checkbox" id="check_all"/>모든 약관에 동의합니다.</label>
</div>
<div class="btn-cont">
	<a href="/join/memberType.do" class="btn spot btn_next">다음</a>
</div>

<script>
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
	
	function confirm(){
		if(!$('input[id=agree01]:checked').is(':checked')){
			alert('이용약관에 동의하지 않으셨습니다.');
			return false;
		}
		if(!$('input[id=agree02]:checked').is(':checked')){
			alert('개인정보 수집·이용에 동의하지 않으셨습니다.');
			return false;
		}
		$("#agreeFrm").submit();
}
	
$(document).ready(function(){
	$("#check_all").click(function(){
		if($(this).is(":checked")){
			$("#agree01, #agree02, #agree03").click();
		}
	});
	
	$(".btn_next").click(function(){
		confirm();
		return false;
	});
});
</script>

<c:import url="/template/footer.do" charEncoding="utf-8"/>