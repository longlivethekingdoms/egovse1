<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/template/header.do" charEncoding="utf-8">
	<c:param name="title" value="비밀번호찾기"/>
	<c:param name="css" value="/asset/member/css/login.css"/>
	<c:param name="commonCssUseAt" value="Y"/>
</c:import>

<h2 class="icon1">비밀번호 찾기</h2>
<p class="mB20">회원가입 시 등록한 정보를 입력해주세요.</p>

<div class="bg-area change_box">
	<form id="frm" name="frm" action="/member/findPasswordRegist.do" method="post" onsubmit="return checkForm(this)">
		<fieldset>
			<legend>비밀번호찾기 폼</legend>
			<div class="change_inp">
				<div>
					<label for="userNm">회원명</label>
					<input type="text" name="userNm" class="inp" id="userNm"/>
				</div>
				<div>
					<label for="emplyrId">아이디</label>
					<input type="text" name="emplyrId" class="inp" id="emplyrId"/>
				</div>
				<div>
					<label for="passwordHint">힌트</label>
					<select id="passwordHint" name="passwordHint" required>
						<option value="1">취미 생활은?</option>
						<option value="2">애완견 이름은?</option>
						<option value="3">취직하고 싶은 곳은?</option>
						<option value="4">여행가고 싶은 곳은?</option>
					</select>
				</div>
				<div>
					<label for="passwordCnsr">정답</label>
					<input type="text" name="passwordCnsr" class="inp" id="passwordCnsr"/>
				</div>
			</div>
			<div class="btn-cont">
				<button type="submit" class="btn spot">확인</button>
				<button type="reset" class="btn">취소</button>
			</div>
		</fieldset>
	</form>
</div>

<script>
<c:if test="${not empty message}">
	alert("${message}");
</c:if>

function checkForm(){
	if(!$("#userNm").val()){
		alert("회원명을 입력해주세요.");
		return false;
	}else if(!$("#passwordCnsr").val()){
		alert("비밀번호 정답을 입력해주세요.");
		return false;
	}
}
</script>

<c:import url="/template/footer.do" charEncoding="utf-8"/>