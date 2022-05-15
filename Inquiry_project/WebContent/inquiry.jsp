<%@page import="com.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/inquiry.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Inquiry Management</h1>
         <form id="formInquiry" name="formInquiry">

          Reference No:
         <input id="refno" name="refno" type="text"
         class="form-control form-control-sm">

         <br> User Name:
         <input id="uname" name="uname" type="text"
         class="form-control form-control-sm">

         <br>  Email:
         <input id="email" name="email" type="text"
         class="form-control form-control-sm">

         <br>  Contact No:
         <input id="pnum" name="pnum" type="text"
         class="form-control form-control-sm">

         <br> Description:
         <input id="des" name="des" type="text"

 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
 <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divIUsersGrid">
 <%
    Inquiry inquiryObj = new Inquiry();
	out.print(inquiryObj.readInquiry());
 %>
</div>
</div> </div> </div>
</body>
</html>