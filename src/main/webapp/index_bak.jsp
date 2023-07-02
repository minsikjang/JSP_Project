<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--          meta 선언          -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--          link 선언          -->
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/style_index.css">
<!--          script 선언          -->
<script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script src="./js/script.js"></script>

<title>여행을떠나요</title>
</head>
<body>

	<header>
		<div class="header_container">
			<div class="logo_container">
				<a href="./index.jsp">메인 페이지</a>
			</div>
			<div class="nav_container" id="nav_menu">
				<div class="menu_container">
					<ul class="menu">
						<li class="menu_1"><a class="menu_title">메뉴 1</a>
							<ul class="menu_1_content">
								<li><a class="menu_index" href="#">메뉴 1 - 1</a></li>
								<li><a class="menu_index" href="#">메뉴 1 - 2</a></li>
								<li><a class="menu_index" href="#">메뉴 1 - 3</a></li>
							</ul></li>
						<li class="menu_2"><a class="menu_title">메뉴 2</a>
							<ul class="menu_2_content">
								<li><a class="menu_index" href="#">메뉴 2 - 1</a></li>
								<li><a class="menu_index" href="#">메뉴 2 - 2</a></li>
								<li><a class="menu_index" href="#">메뉴 2 - 3</a></li>
							</ul></li>
						<li class="menu_3"><a class="menu_title">메뉴 3</a>
							<ul class="menu_3_content">
								<li><a class="menu_index" href="#">메뉴 3 - 1</a></li>
								<li><a class="menu_index" href="#">메뉴 3 - 2</a></li>
								<li><a class="menu_index" href="trip.jsp">오시는길</a></li>
							</ul></li>
					</ul>
				</div>
				<div class="login_container">
					<ul class="login">
						<li>${sessionScope.memberName}</li>
						<li class="menu_login"><a class="menu_title" href="<%= request.getContextPath() %>/login/login.do">로그인</a></li>
						<li class="menu_join"><a class="menu_title" href="<%= request.getContextPath() %>/login/register.do">회원가입</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<!-- 사진 집어넣을까 -->

	<div class="main_container">
		<div class="conA">
			<div class="slide img1">
				<img src="./images/exterior.jpg">
			</div>
			<div class="slide img2">
				<img src="./images/lobby.jpg">
			</div>
			<div class="slide img3">
				<img src="./images/restaurant.jpg">
			</div>
		</div>


		<div class="conB">
			<div class="conB_title">
				<h3>About</h3>
			</div>
			<div class="conB_container">
				<div class="conB_small_container">
					<div class="conB_icon">
						<i class="fas fa-comment-dots icon"></i>
					</div>
					<div class="conB_content">
						<h3>문의 사항</h3>
						<p>Index 1</p>
					</div>
				</div>
				<div class="conB_small_container">
					<div class="conB_icon">
						<i class="fas fa-calendar icon"></i>
					</div>
					<div class="conB_content">
						<h3>달력</h3>
						<p>Index 2</p>
					</div>
				</div>
				<div class="conB_small_container" onclick="open3()">
					<div class="conB_icon">
						<i class="fas fa-plane icon" ></i>
					</div>
					<div class="conB_content">
						<h3>오시는 길</h3>
						<p>Index 3</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer style="background-color: black; color: white;">
		<div style="margin-top: 70px;">
			<div style="text-align: center;">
				<span>회사명 : (주)더조은 아케데미 | 주소 : 서울시 종로구 종각역 ** 빌딩 5층 | 대표: 누굴까
					| 개인정보관리책임자 : 나임</span> <br /> <span>사업자등록번호 : 000-00-00000 |
					통신판매업신고 : 00000호 | 이메일 : ktym14452@naver.com</span><br />
				<br />
				<p>여기에 집어넣고 싶은 문구 작성</p>
				<br />
				<p>많은 문의 바랍니다.</p>
			</div>
		</div>
		<div>
			<audio id="musicPlayer" hidden="hidden" src="./music/05 Second Run.flac" controls="controls" autoplay="autoplay"></audio>
		</div>
	</footer>
	
</body>

</html>