<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
  div{
  height: 100px;
  width: 100px;
  }
  </style>
</head>
<body>

       <p>제목 :${data.TITLE} </p>
       <p>URL :${data.VIDEO_URL} </p>
    <div>
       <video controls loop>
	    <source src="${data.VIDEO_URL}" type="video/mp4">
       </video>
    </div>   
   
   
   
   
   
</body>
</html>