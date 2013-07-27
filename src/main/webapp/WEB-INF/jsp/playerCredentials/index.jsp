<head>
	<title>PlayerCredentials [index]</title>
</head>
<body>
	<h1>Listing PlayerCredentials</h1>

	<table>
		<tr>
			<th>Email</th>
			<th>Password</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${playerCredentialsList}" var="playerCredentials">
			<tr>
				<td>${playerCredentials.email}</td>
				<td>${playerCredentials.password}</td>
				<td><a href="${pageContext.request.contextPath}/playerCredentials/${playerCredentials.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/playerCredentials/${playerCredentials.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/playerCredentials/${playerCredentials.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/playerCredentials/new">New PlayerCredentials</a> 
</body>