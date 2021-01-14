<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
     <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" >
</head>
<body>
  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} </h2>
        <button class="btn btn-primary btn-sm" onclick="document.forms['logoutForm'].submit()">Logout</button>
    </c:if>
  </div>

	<div class="container">

		<h4 class="form-todo-heading"> Add New To Do List Item</h4>
		
		<form:form class="form-signin" method="POST" action="${contextPath}/addToDoItem" modelAttribute="toDoListForm" >
					<form:input class="form-control" type="text" path="name" placeholder="To Do Name" autofocus="true"></form:input>
					
					<form:textarea class="form-control" path = "description" placeholder="To Do Description" rows = "5" cols = "30" />
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			
		</form:form>


		<c:if test="${not empty lists}">

			<table class="table table-dark">
				<tr>
					<th scope="col">To Do List Item</th>
					<th scope="col">Description</th>
					<th scope="col">Last Updated</th>
					<th scope="col">Status</th>
					<th scope="col">Remove</th>
				</tr>
				<c:forEach var="listValue" items="${lists}">
					<form:form class="form-signin" method="POST"
						action="${contextPath}/updateToDoItem"
						modelAttribute="toDoListUpdateForm">
						<tr>
							<form:input type="hidden" path="toDoListId"
								value="${ listValue.toDoListId }"></form:input>

							<td><form:input type="text" path="name"
									value="${ listValue.name }" class="form-control"></form:input>
							</td>
							<td><form:input type="text" path="description"
									value="${ listValue.description }" class="form-control"></form:input>

							</td>
							<td>${listValue.lastUpdate}</td>
							<td><form:checkbox path="status" /></td>

							<td><a
								href="${contextPath}/deleteItem?itemId=${listValue.toDoListId} ">Delete</a></td>

							<td><button class="btn btn-primary btn-sm" type="submit">Update</button></td>
						</tr>
					</form:form>
				</c:forEach>
			</table>

		</c:if>

	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>