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
<!-- 작은 창에서 실행되게끔 진행하는 jsp 코드 입니다 .  -->
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
<!-- 헤더 뺌 -->
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
   <!-- 풋터 뺌 -->
</body>
</html>
