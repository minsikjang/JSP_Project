<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	<style type="text/css">
		
	</style>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-3.7.0.js"></script> 
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/common.js"></script> 
	<script>
		$(() => {
	 		
		});
		
		var idCheck = false;
		
		function idConfirm() {
			if($("#id").val() == ""){
				alert("아이디를 입력하세요");	
				return;
			}
			
			var url = '<%= request.getContextPath() %>/login/register.do?id=' + $("#id").val();
			
			fetch(url, {method: "GET"})
	  		.then((response) => {
			  	return response.json();
		  	})
		  	.then((json) => {
		  		idCheck = json.result;
		  		alert(json.message);
		  	})
		  	.catch((error) => console.log("error:", alert(error)));
		}
		
		function postCode() {
			new daum.Postcode({
		        oncomplete: function(data) {
		            $("input[name=postcode]").val(data.zonecode);
		            $("input[name=addr1]").val(data.address);
		        }
		    }).open();
			
			return;
		}
		
		function passwordCheck() {
			$("#passwordConfirm").next().remove("span");
			if ($("#password").val() != $("#passwordConfirm").val()) {
				var al = $("<span style='color:red; position: absolute; top: 20px; left: 300px;'>비밀번호를 확인하세요.</span>");
				$("#passwordConfirm").after(al);
			}
		}
		
		function lengthCheck(obj) {
			if (obj.value.length > obj.maxLength){
				obj.value = obj.value.slice(0, obj.maxLength);
		    }   
		}
		
		function emailChange(obj) {
			$("#email2").val(obj.value);
		}
		
		function submitConfirm() {
			if ($("#password").val() == $("#passwordConfirm").val()) {
				if (idCheck) {
					$("#postcode").attr("disabled", false);
					$("#addr1").attr("disabled", false);
					
					var submitBool = onSubmit();	
					if(!submitBool) {
						$("#postcode").attr("disabled", true);
						$("#addr1").attr("disabled", true);
					}
					
					return submitBool;
				}
			} else {
				alert("비밀번호를 확인하세요.");
				return false;
			}
			
			idConfirm();
			return false;
		}
	</script>
</head>
<body>
	<div class="container-sm">
		<main class="form-signin w-60 m-auto" align="center" style="width: 500px;">
		  <img class="mb-4" src="<%= request.getContextPath() %>/images/logo.png" alt="" width="280" style="position:relative; top:30px;">
		  <form action="<%= request.getContextPath()%>/login/register.do" method="post" onsubmit="return submitConfirm()">
		  	<img class="mb-4" src="<%= request.getContextPath()%>/images/logo.gif" alt="" width="200">
		  	<div class="input-group mb-3">
		    	<div class="form-floating">
			      <input type="text" class="form-control vaildation" id="id" placeholder="아이디" name="id"/>
			      <label for="floatingInput">아이디</label>
			    </div>
			  	<span class="input-group-text" id="basic-addon2">
			  		<input type="button" id="idCheck" class="btn btn-outline-primary" value="중복 확인" onclick="idConfirm()"/>
		  		</span>
			</div>
			<div class="input-group mb-3">
			    <div class="form-floating">
			      <input type="password" class="form-control vaildation" id="password" placeholder="패스워드" name="pwd">
			      <label for="floatingPassword">비밀번호</label>
			    </div>
		    </div>
		    <div class="input-group mb-3">
			    <div class="form-floating">
			      <input type="password" class="form-control vaildation" id="passwordConfirm" placeholder="패스워드 확인" name="pwd2" onchange="passwordCheck()">
			      <label for="floatingPassword">비밀번호 확인</label>
			    </div>
		    </div>
		    <div class="input-group mb-3">
			    <div class="form-floating">
			      <input type="text" class="form-control vaildation" id="name" placeholder="이름" name="name">
			      <label for="floatingName">이름</label>
			    </div>
		    </div>
		    <div class="input-group mb-3">
			    <div class="form-floating">
			      <input type="number" class="form-control vaildation" id="phone" placeholder="핸드폰 번호" name="phone" maxlength="11" oninput="lengthCheck(this)">
			      <label for="floatingPhoneNumber">핸드폰 번호</label>
			    </div>
		    </div>
		    <div class="input-group mb-3">
			  <div class="form-floating">
			      <input type="text" class="form-control vaildation" id="email1" placeholder="email1" name="email1">
			      <label for="floatingEmail">이메일</label>
			  </div>
			  <span class="input-group-text">@</span>
			  <div class="form-floating">
			      <input type="text" class="form-control vaildation" id="email2" placeholder="email2" name="email2">
			      <label for="floatingEmail">이메일</label>
			  </div>
			  <select class="form-select" aria-label="메일 선택" onchange="emailChange(this)">
			  	<option value="" selected>직접 입력</option>
			  	<option value="naver.com">naver.com</option>
			  	<option value="gmail.com">gmail.com</option>
			  	<option value="nate.com">nate.com</option>
			  </select>
			</div>
		    <div class="input-group mb-3">
		    	<div class="form-floating">
			      <input type="number" class="form-control vaildation" id="jumin1" placeholder="주민번호1" name="jumin1" maxlength="6" oninput="lengthCheck(this)">
			      <label for="floatingJumin1">주민번호1</label>
			    </div>
			  	<div class="form-floating">
			      <input type="password" class="form-control vaildation" id="jumin2" placeholder="주민번호2" name="jumin2" maxlength="7">
			      <label for="floatingJumin2">주민번호2</label>
			    </div>
			</div>
		    <div class="input-group mb-3">
		    	<div class="form-floating">
		  			<input type="text" class="form-control vaildation" id="postcode" placeholder="우편번호" name="postcode" disabled="disabled">
		  			<label for="floatingInput">우편번호</label>
		  		</div>
			  	<span class="input-group-text" id="basic-addon2">
			  		<input type="button" id="btnPostCode" class="btn btn-outline-primary" onclick="postCode()" value="우편번호 찾기"/>
		  		</span>
			</div>
			<div class="input-group mb-3">
				<div class="form-floating">
		  			<input type="text" class="form-control" id="addr1" placeholder="주소" name="addr1" disabled="disabled">
		  			<label for="floatingInput">주소</label>
		  		</div>
	  		</div>
	  		<div class="input-group mb-3">
		  		<div class="form-floating">
		  			<input type="text" class="form-control vaildation" id="addr2" placeholder="상세 주소" name="addr2">
		  			<label for="floatingInput">상세 주소</label>
		  		</div>
	  		</div>
			
		    <button class="btn btn-primary w-100 py-2" type="submit">회원가입</button>
		    <br><br>
		  </form>
		</main>
	</div>
</body>
</html>