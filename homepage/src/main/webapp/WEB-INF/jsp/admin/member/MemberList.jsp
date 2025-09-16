<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/template/header.do" charEncoding="utf-8">
	<c:param name="title" value="회원관리"/>
</c:import>

<%-- 기본 URL --%>
<c:url var="_BASE_PARAM" value="">
	<c:param name="searchCondition" value="${searchVO.searchCondition }"/>
	<c:param name="searchKeyword" value="${searchVO.searchKeyword }"/>
</c:url>

<div class="container">
	<div id="contents">
		<%-- 검색영역 --%>
		<div id="bbs_search">
			<form name="frm" method="post" action="/admin/member/memberList.do">
				<fieldset>
					<legend>검색조건입력폼</legend>
					<label for="ftext" class="hdn">검색분류선택</label>
					<select name="searchCondition" id="ftext">
						<option value="0"<c:if test="${searchVO.searchCondition eq '0'}">selected="selected"</c:if>>회원아이디</option>
						<option value="1"<c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>회원명</option>
					</select>
					<label for="inp_text" class="hdn">검색어입력</label>
					<input name="searchKeyword" value="<c:out value="${searchVO.searchKeyword}"/>" type="text" class="inp_s" id="inp_text"/>
					<span class="bbtn_s"><input type="submit" value="검색" title="검색"/></span>
				</fieldset>
			</form>
		</div>
		
		<%--목록영역 --%>
		<div id="bbs_wrap">
			<div class="total">
				총 게시물
				<strong><c:out value="${paginationInfo.totalRecordCount}"/></strong>건 |
				현재페이지 <strong><c:out value="${paginationInfo.currentPageNo}"/></strong>/
				<c:out value="${paginationInfo.totalPageCount}"/>
			</div>
			
			<div class="bss_list">
				<table class="list_table">
					<thead>
						<tr>
							<th class="num" scope="col">번호</th>
							<th scope="col">회원아이디</th>
							<th scope="col">회원명</th>
							<th scope="col">가입일</th>
							<th scope="col">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${resultList}" varStatus = "status">
							<tr>
								<td><c:out value="${paginationInfo.totalRecordCount - ((searchVO.pageIndex-1) * searchVO.pageUnit) - (status.count - 1 ) }"/></td>
								<td><c:out value="${result.emplyrId }"/></td>
								<td><c:out value="${result.userNm }"/></td>
								<td><fmt:formatDate value="${result.sbscrbDe}" pattern="yyyy-MM-dd"/></td>
								<td>
									<c:url var="viewUrl" value="/admin/member/memberRegist.do${_BASE_PARAM}">
										<c:param name="esntlId" value="${result.esntlId }"/>
										<c:param name="emplyrId" value="${result.emplyrId }"/>
										<c:param name="pageIndex" value="${searchVO.pageIndex }"/>
									</c:url>
									<a href="${viewUrl}" class="btn spot">수정</a>
								</td>
							</tr>
						</c:forEach>
					
					<%--결과 값이 없을 경우 --%>
					<c:if test="${fn:length(resultList) == 0 }">
						<tr class="empty"><td colspan="5">검색 데이터가 없습니다.</td></tr>
					</c:if>
					</tbody>
				</table>
			</div>
			
			<div id="paging">
				<c:url var="pageUrl" value="/admin/member/memberList.do${_BASE_PARAM }"/>
				<c:set var="pagingParam"><c:out value="${pageUrl }"/></c:set>
				<ui:pagination paginationInfo="${paginationInfo }" type="image" jsFunction="${pagingParam }"/>
			</div>
		</div>
	</div>
</div>

<c:import url="/template/footer.do" charEncoding="utf-8"/>