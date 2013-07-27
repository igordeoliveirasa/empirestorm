<head>
	<title>State [index]</title>
</head>
<body>
	<h1>Listing States</h1>

	<table>
		<tr>
			<th>Thirsty level</th>
			<th>Hungry level</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${stateList}" var="state">
			<tr>
				<td>${state.thirstyLevel}</td>
				<td>${state.hungryLevel}</td>
				<td><a href="${pageContext.request.contextPath}/states/${state.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/states/${state.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/states/${state.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/states/new">New State</a> 
</body>