<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/common.js"></script> 
	<script type="text/javascript">
		$(() => {
			var cookies = document.cookie.split('; ');
			var remember = "";
			var memberID = "";
			
			$.each(cookies, function (index, item) {
				var cookie = item.split('=');
				
				if (cookie[0] == "remember") {
					remember = cookie[1]; 
				}
				
				if (cookie[0] == "memberID") {
					memberID = cookie[1]; 
				}
			});
			
			if (remember == "Y") {
				$("#floatingInput").val(memberID);
				$("#flexCheckDefault").attr("checked", "checked");
			}
		});
	</script>
</head>
<body>
	<div class="container-sm" style="height: 100vh;">
		<main class="form-signin w-60 m-auto" align="center" style="width: 500px; position: relative; top: 25%">
		  <form action="<%= request.getContextPath()%>/login/login.do" method="post" onsubmit="return onSubmit()">
		    <img class="mb-4" src="<%= request.getContextPath() %>/images/logo.png" alt="" width="400">
		    
		    <div class="form-floating">
		      <input type="text" class="form-control vaildation" id="floatingInput" placeholder="아이디" name="id">
		      <label for="floatingInput">아이디</label>
		    </div><br>
		    <div class="form-floating">
		      <input type="password" class="form-control vaildation" id="floatingPassword" placeholder="비밀번호" name="pwd">
		      <label for="floatingPassword">비밀번호</label>
		    </div>
		
		    <div class="form-check text-start my-3">
		      <input class="form-check-input" type="checkbox" value="Y" id="flexCheckDefault" name="remember">
		      <label class="form-check-label" for="flexCheckDefault">아이디 기억</label>
		    </div>
		    <button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
		    <br><br>
		    <p class="mt-5 mb-3 text-body-secondary" style="display: inline; margin-top: 10px;">
		    	<label style="float: left;">펜션에 오신 것을 환영합니다.</label>
		    	<a href="<%= request.getContextPath() %>/login/register.do" style="float: right; text-decoration: none;">회원가입</a>
	    	</p>
		  </form>
		</main>
	</div>
</body>
</html>