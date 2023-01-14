<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_0g.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="page-main">
		<div class="main-title">
			<h2>관리자 마이페이지</h2>
		</div>
		<div class="main-menu01">
			<button id="btn02" value="주문조회" onclick="location.href='orderList.do'">주문조회</button>
			<button id="btn01" value="예약조회" onclick="location.href='resList.do'">예약조회</button>
			<button id="btn01" value="회원정보" onclick="location.href='memList.do'">회원정보</button>
		</div>
		<div class="main-content">
			<div id="title-count">
		  		<p id="title">배송지 정보</p>
	  		</div>
	  		<div class="order_form">
			<!-- <form action="modify.do" method="post" id="order_form"> -->
			<%-- <input type="hidden" name="order_main_num" value="${order.order_main_num}"> --%>
			<ul id="order_ul">
				<li>	
					<label>받는 사람</label>
					${order.receive_name}
				</li>
				<li>
					<label>우편번호</label>
					${order.receive_zipcode}
				</li>
				<li>
					<label>주소</label>
					${order.receive_address1} ${order.receive_address2}
				</li>	
				<li>
					<label>전화번호</label>
					${order.receive_phone}
				</li>
				<li>
					<c:if test="${order.notice == null}">
					</c:if>
					<c:if test="${order.notice != null}">
						<label>남기실 말씀</label>
						${order.notice}
					</c:if>
				</li>
				<li>
					<label>결제수단</label>
					<c:if test="${order.payment == 1}">은행입금</c:if>
					<c:if test="${order.payment == 2}">카드결제</c:if>                        
				</li>
				<li>
					<form action="modify.do" method="post" id="order_form">
					<input type="hidden" name="order_main_num" value="${order.order_main_num}">
						<label>배송상태</label>
						<c:if test="${order.status !=5}">
						<input type="radio" name="status" id="status1" value="1" 
						   <c:if test="${order.status == 1}">checked</c:if>>결제완료
						   
						   <input type="radio" name="status" id="status2" value="2" 
						   <c:if test="${order.status == 2}">checked</c:if>>배송준비중
						   
						   <input type="radio" name="status" id="status3" value="3" 
						   <c:if test="${order.status == 3}">checked</c:if>>배송중
						   
						   <input type="radio" name="status" id="status4" value="4" 
						   <c:if test="${order.status == 4}">checked</c:if>>배송완료
						</c:if>
						<input type="radio" name="status" id="status5" value="5"
						   <c:if test="${order.status == 5}">checked</c:if>>환불
					</form>
				</li>
				<%-- <li>
					<form action="updateMemAuth.do" method="post">
						<input type="hidden" id="num" name="mem_num" value="${detail.mem_num}"/>
						<label for="auth">등급</label>
						<c:if test="${detail.mem_auth == 2}">
							ㅣ <input type="text" name="auth" id="auth" value="일반회원" readonly/>
						</c:if>
						<c:if test="${detail.mem_auth == 0}">
							ㅣ <input type="text" name="auth" id="auth" value="탈퇴회원" readonly/>
						</c:if>
						<c:if test="${detail.mem_auth == 1}">
							ㅣ <input type="text" name="auth" id="auth" value="정지회원" readonly/>
						</c:if>
						<c:if test="${detail.mem_auth == 9}">
							ㅣ <input type="text" name="auth" id="auth" value="관리자" readonly/>
						</c:if>
						<select name="mem_auth" id="select_auth" style="display:none;">
							<option value=2 <c:if test="${detail.mem_auth == 2}">selected</c:if>>일반회원</option>
							<option value=0 <c:if test="${detail.mem_auth == 0}">selected</c:if>>탈퇴회원</option>
							<option value=1 <c:if test="${detail.mem_auth == 1}">selected</c:if>>정지회원</option>
							<option value=9 <c:if test="${detail.mem_auth == 9}">selected</c:if>>관리자</option>
						</select>
						<input type="button" id="update_auth" value="수정"/>
						<input type="submit" id="submit_auth" style="display:none" value="완료"/>
					</form>
				</li> --%>
			</ul>
			<div class="align-center">
				<c:if test="${order.status != 5}">
				<input type="submit" value="주문수정">
				</c:if>
				<input type="button" value="주문목록"
				 onclick="location.href='list.do'">
			</div>
		</div>
		<p id="title2">주문 내역</p>
		<hr size="1" noshade="noshade" width="100%">
			<table>
				<tr class="th">
					<th>상품명</th>
					<th>수량</th>
					<th>상품가격</th>
					<th>합계</th>
				</tr>
				<c:forEach var="detail" items="${list}">
					<tr class="td">
						<td><c:out value="${detail.pro_name}"/></td>
						<td><c:out value="${detail.order_main_count}"/>개</td>
						<td>
							<c:out value="${detail.pro_price}"/>원
						</td>
						<td>
							<c:out value="${detail.pro_total}"/>원
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" id="total_td">
						<b>총 구매금액</b>
					</td>
					<td id="total_td">
						<fmt:formatNumber value="${order.order_main_total}"/>원
					</td>
				</tr>
			</table>
			<hr size="1" noshade="noshade" width="100%">
			
		  	
			<div>
				<input type="button" value="취소" onclick="window.location.reload()">
				<input type="button" value="목록으로" onclick="location.href='orderList.do'">
			</div>
		</div>
	</div>
</body>

</body>
</html>