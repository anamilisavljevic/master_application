<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Login</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width" />
<base href="/" />
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.min.css"></link>
</head>

<body>
 
	<div class="container">
	
	<h2>Sorry, but we could not find all the information so please fill the following fields</h2>
		<form th:action="@{/registration}" th:object="${userBean}" method="post">
			<table>
				<tr th:unless="${userBean.email}">
					<td>Email:</td>
					<td><input type="text" th:th:field="*{email}" /></td>
					<td th:if="${#fields.hasErrors('email')}" th:errors="*{email}">Name Error</td>
				</tr>
				<tr th:if="${userBean.email}">
					<input type="hidden" th:field="*{email}" />
				</tr>
				
				<tr th:unless="${userBean.title}">
					<td>Title:</td>
					<td><select th:field="*{title}">
							<option value="Mr" th:text="Mr"></option>
							<option value="Mrs" th:text="Mrs"></option>
					</select></td>
					<td th:if="${#fields.hasErrors('title')}" th:errors="*{title}">Title Error</td>
				</tr>
				<tr th:if="${userBean.title}">
					<input type="hidden" th:field="*{title}" />
				</tr>
				
				<tr th:unless="${userBean.firstName}">
					<td>Email:</td>
					<td><input type="text" th:field="*{firstName}" /></td>
					<td th:if="${#fields.hasErrors('firstName')}" th:errors="*{email}">Name Error</td>
				</tr>
				<tr th:if="${userBean.firstName}">
					<input type="hidden" th:field="*{firstName}" />
				</tr>
				
				<tr th:unless="${userBean.lastName}">
					<td>Email:</td>
					<td><input type="text" th:field="*{lastName}" /></td>
					<td th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}">Name Error</td>
				</tr>
				<tr th:if="${userBean.lastName}">
					<input type="hidden" th:field="*{lastName}" />
				</tr>
				 
				<tr th:unless="${userBean.country}">
					<td>Country:</td>
					<td><select th:field="*{country}">
							<option value="India" th:text="India"></option>
							<option value="UK" th:text="UK"></option>
							<option value="US" th:text="US"></option>
							<option value="Iraq" th:text="Iraq"></option>
					</select></td>
					<td th:if="${#fields.hasErrors('country')}" th:errors="*{country}">Country Error</td>
				</tr>
				<tr th:if="${userBean.country}">
					<input type="hidden" th:field="*{country}" />
				</tr>
				<input type="hidden" th:field="*{password}" />
				<input type="hidden" th:field="*{passwordConfirm}"  />
				 
				<tr>
					<td><button type="submit">Submit</button></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>