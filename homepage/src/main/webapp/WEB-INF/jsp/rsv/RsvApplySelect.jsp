<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/template/header.do" charEncoding="utf-8">
	<c:param name="title" value="예약"/>
</c:import>

<!-- content 시작 -->
<div id = "content">
	<div class="container">
		<div id="contents">
			<form action="/rsv/rsvApplyUpdate.do" method="post" id="frm" onsubmit="return regist()">
				<input type="hidden" name="reqstId" value="${result.reqstId}"/>
				
				<table class="chart2">
					<caption>예약정보 작성</caption>
					<colgroup>
						<col style = "width:120px"/>
						<col/>
					</colgroup>
					<tbody>
						<%-- 접수 중일 때만 수정가능 --%>
						<c:choose>
							<c:when test="${result.confmSeCode eq 'R'}">
								<tr>
									<th scope="row">*예약자명</th>
									<td>
										<input type="text" id="chargerNm" name="chargerNm" title="예약자명" value="<c:out value="${result.chargerNm}"/>"/>
									</td>
								</tr>
								<tr>
									<th scope="row">*연락처</th>
									<td>
										<input type="text" id="telno" name="telno" title="연락처" value="<c:out value="${result.telno}"/>"/>
									</td>
								</tr>
								<tr>
									<th scope="row">이메일</th>
									<td>
										<input type="text" id="email" name="email" title="이메일" value="<c:out value="${result.email}"/>"/>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<th scope="row">신청자명</th>
									<td><c:out value="${result.chargerNm}"/>(<c:out value="${result.frstRegisterId}"/>)</td>
								</tr>
								<tr>
									<th scope="row">연락처</th>
									<td>
										<c:out value="${result.telno}"/>
									</td>
								</tr>
								<tr>
									<th scope="row">이메일</th>
									<td>
										<c:choose>
											<c:when test="${empty result.email}">없음</c:when>
											<c:otherwise>
												<c:out value="${result.email}"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<th scope="row">승인여부</th>
									<td>
										<c:choose>
											<c:when test="${result.confmSeCode eq 'R'}">신청 접수 중</c:when>
											<c:when test="${result.confmSeCode eq 'O'}">신청 승인</c:when>
											<c:when test="${result.confmSeCode eq 'X'}">신청 반려</c:when>
										</c:choose>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
						
						<%--반려일 시 반려사유에 대해 나옴 --%>
						<c:if test="${result.confmSeCode eq 'X'}">
							<tr>
								<th>반려사유</th>
								<td><c:out value="${result.returnResn}"/></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<div class="btn-cont ar">
					<%--접수중만 수정가능--%>
					<c:if test="${result.confmSeCode eq 'R'}">
						<a href="/rsv/rsvApplyUpdate.do" id="btn-upt" class="btn">수정</a>
						
						<c:url var="delUrl" value="rsv/rsvApplyDelete.do${_BASE_PARAM}">
							<c:param name="reqstId" value="${result.reqstId}"/>
						</c:url>
						<a href="${delUrl}" id="btn-del" class="btn"><i class="ico-del"></i>삭제</a>
					</c:if>
					<c:url var="listUrl" value="/rsv/selectApplyList.do${_BASE_PARAM}"/>
					<a href="${listUrl}" class="btn">목록</a>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- //content 끝 -->
<script>
	${document}.ready(function(){
		//예약자 삭제
		$("#btn-del").click(function(){
		if(!confirm("삭제하시겠습니까?")){
			return false;
			}
		});
		
		//수정
		$("#btn-upt").click(function(){
			$("#frm").submit();
			return false;
		});
	});
	
	//vali
	function regist(){
		if(!$("chargerNm").val()){
			alert("예약자명을 입력해주세요");
			$("#chargerNm").focus();
			return false;
		}else if(!$("#telno").val()){
			alert("연락처를 입력해주세요.");
			$("#telno").focus();
			return false;
		}
</script>
<c:import url="/template/footer.do" charEncoding="utf-8"/>