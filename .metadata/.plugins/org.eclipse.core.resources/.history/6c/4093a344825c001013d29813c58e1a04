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
		<li>회원유형</li>
		<li class="current">정보입력</li>
		<li>가입완료</li>
	</ul>
</div>

<h2 class="icon1">회원정보입력</h2>
<p class="mB20">회원가입을 위해 아래의 정보를 입력해 주십시오. <br class="m-block"/><strong class="star">*</strong>표시는 필수 입력사항 입니다.</p>

<h3 class="icon2 hidden">기본정보</h3>
<form id="frm" name="frm" method="post" action="/join/insertMember.do" onsubmit="return regist();">
	<input type="hidden" name="loginType" value="${searchVO.loginType}"/>
	<input type="hidden" id="idCheckAt" value="N"/>
	
	<table class="join_chart">
		<caption>게시글 작성</caption>
		<colgroup>
			<col style="width:200px"/>
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th><strong class="star">*</strong><label for="emplyrId">회원 아이디</label></th>
				<td>
					<input type="text" id="emplyrId" name="emplyrId" required/>
					<a href="#" id="btn-id-check" class="btn-sm spot fn" title="중복확인(새창열림)"><span>중복확인</span></a>
				</td>
			</tr>
			<tr>
				<th><strong class="star">*</strong><label for="userNm">회원 명</label></th>
				<td><input type="text" id="userNm" name="userNm" required/></td>
			</tr>
			<tr>
				<th><strong class="star">*</strong><label for="password">비밀번호</label></th>
				<td><input type="password" id="password" name="password" required/></td>
			</tr>
			<tr>
				<th><strong class="star">*</strong><label for="password2">비밀번호 확인</label></th>
				<td><input type="password" id="password2" required/></td>
			</tr>
			<tr>
				<th><strong class="star">*</strong><label for="passwordHint">비밀번호 힌트</label></th>
				<td>
					<select id="passwordHint" name="passwordHint" required>
						<option value="1">취미 생활은?</option>
						<option value="2">애완견 이름은?</option>
						<option value="3">취직하고 싶은 곳은?</option>
						<option value="4">여행가고 싶은 곳은?</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><strong class="star">*</strong><label for="passwordCnst">비밀번호 정답</label></th>
				<td><input type="text" id="passwordCnsr" name="passwordCnsr" required/></td>
			</tr>
			<tr>
				<th><strong class="star">*</strong><label for="emailId">이메일</label></th>
				<td>
					<input type="text" id="emailId" name="emailId" required/> @
					<input type="text" id="emailDomain" name="emailDomain" required/>
					
					<select id="domain">
						<option value="">직접입력</option>
						<option value="daum.net">다음</option>
						<option value="naver.com">네이버</option>
						<option value="gmail.com">구글(gmail)</option>
						<option value="nate.com">네이트</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btn-cont ac">
		<button type="submit" class="btn spot">가입</button>
	</div>
</form>

<script>
$(document).ready(function(){
	$("#domain").change(function(){
		let domain = $(this).val();
		
		$("#emailDomain").val(domain);
	});
	
	$("#btn-id-check").click(function(){
		var emplyrId = $("#emplyrId").val();
		
		if(emplyrId){
			$.ajax({
				url : "/join/duplicateCheck.do",
				type : "post",
				data : {"emplyrId" : emplyrId},
				dataType : "json",
				success : function(data) {
					if(data.successYn == "Y"){
						alert(data.message);
						$("#idCheckAt").val("Y");
					}else{
						alert(data.message);
						$("#idCheckAt").val("N");
					}
				}, error : function(){
					alert("error");
				}
			});
		}else{
			alert("ID를 입력해주세요.");
		}
		
		return false;
	});
});

function regist(){
	if($("#idCheckAt").val() != "Y"){
		alert("아이디 중복 검사를 해주세요.");
		return false;
	}else if(!$("#emplyrId").val()){
		alert("아이디를 입력해주세요.");
		return false;
	}else if(!$("#userNm").val()){
		alert("이름을 입력해주세요.");
		return false;
	}else if(!$("#password").val()){
		alert("비밀번호를 입력해주세요.");
		return false;
	}else if(!$("#password2").val()){
		alert("비밀번호 확인을 입력해주세요.");
		return false;
	}else if(!$("#passwordHint").val()){
		alert("비밀번호 힌트를 입력해주세요.");
		return false;
	}else if(!$("#passwordCnsr").val()){
		alert("비밀번호를 정답을 입력해주세요.");
		return false;
	}else if($("#password").val() != $("#password2").val()){
		alert("비밀번호와 비밀번호 확인 정보가 다릅니다.");
		return false;
	}
	
	if($("#password").val()){
		var pw = $("#password").val(),
		message = "",
		passwordRules1 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,30}$/,
		passwordRules2 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{10,30}$/,
		result = false;
		
		if(pw.length < 10){
			if(!passwordRules1.test(pw)){
				message="영문, 숫자, 특수문자 등 3가지 사용시 8자 이상, 2가지 사용시 10자리 이상";
			}else{
				result=true;
			}
		}
		
		if(!passwordRules2.test(pw) && !result){
		}else{
			result = true;
		}
		
		if(message.length > 0){
			alert(message + " 입력해주세요.");
			return false;
		}
	}
}

</script>
<c:import url="/template/footer.do" charEncoding="utf-8"/>