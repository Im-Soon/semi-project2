<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임상세</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/game.review.js"></script>
<script type="text/javascript">
$(function(){
	//장바구니 상품 담기 이벤트 처리
	$('#item_cart').submit(function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		if($('#cart_count').val()==''){
			alert('수량을 입력하세요');
			$('#cart_count').focus();
			return false;
		}
		
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'../cart/write.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('장바구니에 담았습니다.');
					location.href='../cart/cart.do';
				}else if(param.result == 'overquantity'){
					alert('이미 장바구니에 담겨있는 상품입니다.');
				}else{
					alert('장바구니 담기 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
});
</script>
</head>


<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="detailpage-main">
	<h1>게임상세</h1>
		<div class="image-detail">
			<img src="${pageContext.request.contextPath}/images/seul/${detail.pro_picture}">
		</div>
		<div class="small-header">
			<b class="game-name">${detail.pro_name}</b>
			<span>${detail.pro_price}</span>
			<!-- <input type="button" value="구매하기"> -->
			<form id="item_cart" method="post">
				<input type="hidden" name="pro_num" 
					       	value="${detail.pro_num}" id="pro_num">
					<input type="hidden" name="item_price"
					       	value="${detail.pro_price}" id="pro_price">
					<input type="hidden" name="pro_count"
							value="${detail.pro_count}" id="pro_count">
				<input type="submit" value="장바구니">
			</form>
			<hr size="1" noshade="noshade" width="250px">
		</div>
		<div class="game-explanation">
			게임 설명 : ${detail.explanation} 
			<hr size="1" noshade="noshade" width="250px">
		</div>
		<div class="small-footer">
		<span>난이도 : ${detail.pro_level}</span>
		<span>게임 인원 : ${detail.person}</span>
		</div>
		<br>
		<br>
		<!-- 평가 및 리뷰 시작 -->
		<div id="review_div">
			<span class="re-title">리뷰 쓰기</span>
			<form id="re_form">
				<input type="hidden" name="pro_num" value="${detail.pro_num}" id="pro_num">
				<textarea rows="3" cols="50" name="rev_content" id="rev_content" class="rev-content"
				<c:if test="${empty  user_num}">disabled="disabled"</c:if>
				><c:if test="${empty user_num}">로그인 후 리뷰를 남겨주세요!.</c:if></textarea>
				<c:if test="${!empty user_num}">
				<div id="re_first">
					<span class="letter-count">500/500</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="전송">    <!-- 전송하면 어디로 담기는지...? -->
				</div>
				</c:if>
			</form>
		</div>
		<!-- 리뷰 목록 출력 시작 -->
		<div id="output"></div>
			<div class="paging-button" style="display:none;">
				<input type="button" value="다음글 보기">
			</div>
			<div id="loading" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
			</div>
		<!-- 리뷰 목록 출력 끝 -->
		<!-- 평가 및 리뷰 끝 -->
</div>
</body>
</html>