<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="<%= request.getContextPath()%>/img/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/styles.css"/>
    
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-3.7.0.js"></script> 
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	<style type="text/css">
		table thead {
			text-align: center;
		}
		
		table thead tr th:first-child {
			border-radius: 10px 0px 0px 0px;
		}	
		
		table thead tr th:last-child {
			border-radius: 0px 10px 0px 0px;
		}	
		
		.input-group-text {
			width: 100px; 
			display:inline-block; 
			text-align: center;
		}
		
		a {
			text-decoration: none;
		}
		
		.memberIds {
			cursor: pointer;
			color: rgb(13,110,253);
		}
		
	</style>
	
	<script type="text/javascript">
		$(() => {
			$("#allCheck").click(function () {
				if ($(this).is(":checked")) {
					$(".check").prop("checked", true);
				} else {
					$(".check").prop("checked", false);
				}
			});
			
			$(".check").click(function () {
				if (!$(this).is(":checked")) {
					$("#allCheck").prop("checked", false);
				}
			});
			
			$("#del").click(function () {
				var delIds = "";
				$(".check").each(function (index) {
					if ($(this).is(":checked")) {
						delIds += "," + $(this).val();
						//delIds += ",'" + $(this).val() + "'";
					}
				});
				
				if (delIds == "") {
					alert("삭제 할 회원을 체크하세요.");
				} else {
					if (confirm("삭제하시겠습니까?")) {
						var url = '<%= request.getContextPath() %>/admin/member.do?delData=' + delIds.substr(1, delIds.length);
						
						fetch(url, {method: "delete"})
				  		.then((response) => {
						  	return response.json();
					  	})
					  	.then((json) => {
					  		window.location.reload();
					  	})
					  	.catch((error) => console.log("error:", alert(error)));
					}
				}
				
			});
		});
		
		function memberUpdate(idx) {
			
			$("#idx").val(idx);
			//alert(idx);
			
			/* const exampleModal = document.getElementById('exampleModal')
			if (exampleModal) {
			  exampleModal.addEventListener('show.bs.modal', event => {
			    // Button that triggered the modal
			    const button = event.relatedTarget
			    // Extract info from data-bs-* attributes
			    const recipient = button.getAttribute('data-bs-whatever')
			    // If necessary, you could initiate an Ajax request here
			    // and then do the updating in a callback.

			    // Update the modal's content.
			    const modalTitle = exampleModal.querySelector('.modal-title')
			    const modalBodyInput = exampleModal.querySelector('.modal-body input')

			    modalTitle.textContent = `New message to ${recipient}`
			    modalBodyInput.value = recipient
			  })
			} */
		}
	</script>
<body>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar-->
       	<div class="border-end bg-white" id="sidebar-wrapper">
            <jsp:include page="/WEB-INF/views/layout/side.jsp"/>
        </div>
        
        <!-- Page content wrapper-->
        <div id="page-content-wrapper">
            <!-- Top navigation-->
            <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            	<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
            </nav>
            <br>
            
            <%-- <%
				out.println(request.getAttribute("list"));
            %>
            회원 --%>
            
            <!-- <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            	
           	</nav> -->
           	
            <div class="table-responsive" style="padding: 20px;">
            	<form action="<%= request.getContextPath() %>/admin/member.do" method="get">
            	<div class="input-group" style="margin-bottom: 30px;">
			  		<span class="input-group-text">아이디</span>
			  		<input type="text" aria-label="First name" class="form-control" name="id" value="${param.id}">
				  	<span class="input-group-text">이름</span>
				  	<input type="text" aria-label="Last name" class="form-control" name="name" value="${param.name}">
				  	<span class="input-group-text">휴대폰번호</span>
				  	<input type="text" aria-label="Last name" class="form-control" name="phone" value="${param.phone}">
				  	<span class="input-group-text">회원유무</span>
				  	<select class="form-select" name="useYN">
					  	<option value="ALL" ${ param.useYN == "ALL" ? "selected" : "" }>전체</option>
					    <option value="Y" ${ param.useYN == "Y" ? "selected" : "" }>사용</option>
					    <option value="N" ${ param.useYN == "N" ? "selected" : "" }>미사용</option>
				  	</select>
				  	<button class="btn btn-primary" type="submit" id="btnSearch" style="width: 100px;">검색</button>
				</div>
				</form>
				
				<div class="btn-group float-end" role="group" aria-label="Basic example">
					<button id="add" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#memberModal" style="width: 100px;">추가</button>
				    <button id="del" class="btn btn-danger" style="width: 100px;">삭제</button>
            	</div><br><br>
				
            	<table class="table table-striped table-hover">
	            	<thead class="table-dark">
	            		<tr>
	            			<th><input type="checkbox" id="allCheck"/></th>
	            			<th>아이디</th>
	            			<th>성명</th>
	            			<th>비밀번호</th>
	            			<th>주민번호1</th>
	            			<th>주민번호2</th>
	            			<th>우편번호</th>
	            			<th>주소1</th>
	            			<th>주소2</th>
	            			<th>이메일</th>
	            			<th>휴대폰번호</th>
	            			<th>회원유무</th>
	            			<th>등록일</th>
	            			<th>수정일</th>
	            		</tr>
	            	</thead>
	            	<tbody class="table-group-divider">
	            		<c:forEach var="item" items="${list}">
	            		<tr>
	            			<th><input type="checkbox" class="check" value="${item.idx}"/></th>
	            			<td>
	            				<span class="memberIds" onclick="memberUpdate(${item.idx})" 
	            					data-bs-toggle="modal" data-bs-target="#memberModal">${item.id}
	            				</span>
            				</td>
	            			<td>${item.name}</td>
	            			<td>${item.password}</td>
	            			<td>${item.jumin1}</td>
	            			<td>${item.jumin2}</td>
	            			<td>${item.postcode}</td>
	            			<td>${item.addr1}</td>
	            			<td>${item.addr2}</td>
	            			<td><c:out value="${item.email1}@${item.email2}"/></td>
	            			<td>${item.phone}</td>
	            			<td>${item.useYN}</td>
	            			<td>${item.regdate}</td>
	            			<td>${item.updatedate}</td>
	            		</tr>
	            		</c:forEach>
	            	</tbody>
	            </table>
	            
            	<nav aria-label="Page navigation">${ paging }</nav>
            </div>
            
        </div>
    </div>
    
	<!-- Modal -->
	<div class="modal fade" id="memberModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">회원 정보</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        ...
	        <input id="idx" type="text"></input>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary">저장</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</body>
</html>