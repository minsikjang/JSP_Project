<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>오시는 길</title>
<style>
    table {
        width: 600px;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
</style>
</head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e877dae5b5819be5c98e49539ec3e5dc"></script>
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
								<li><a class="menu_index" href="#">메뉴 3 - 3</a></li>
							</ul></li>
					</ul>
				</div>
				<div class="login_container">
					<ul class="login">
						<li class="menu_login"><a class="menu_title" href="#">로그인</a></li>
						<li class="menu_join"><a class="menu_title"
							href="insert.html">회원가입</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
   <!-- ====================================================================  -->	
	
<div style="display: flex;">
  <div style="flex: 1;">
    <div id="map" style="width: 600px; height: 350px;"></div>
    <script src="./js/mapScript.js"></script>
  </div>
  <div style="flex: 1;">
    <h2 style="background-color: silver;">오시는 길</h2>
    <table>
      <tr>
        <th>주소</th>
        <th>전화번호</th>
      </tr>
      <tr>
        <td id="address">대한민국 서울특별시 종로구 우정국로2길 21</td>
        <td id="phoneNumber">010-0000-0000</td>
      </tr>
		<tr>
	        <td >여기다가 설명 적는 구간~!!!!!!!!!!! </td>
	        <td >여기다가 설명</td>
		</tr>
		<tr>
	        <td >여기다가 설명 적는 구간!!!!!!!!!!! </td>
	        <td >여기다가 설명</td>
		</tr>
		<tr>
	        <td >여기다가 설명 적는 구간!!!!!!!!!!! </td>
	        <td >여기다가 설명</td>
		</tr>
    </table>
  </div>
</div>

    
    
   <!-- ====================================================================  -->
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
	</footer>
</body>
</html>
