<head>
	<title>PlayerState [index]</title>
</head>
<body>
	<h1>Listing PlayerStates</h1>

	<table>
		<tr>
			<th>Thirsty level</th>
			<th>Hungry level</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${playerStateList}" var="playerState">
			<tr>
				<td>${playerState.thirstyLevel}</td>
				<td>${playerState.hungryLevel}</td>
				<td><a href="${pageContext.request.contextPath}/playerStates/${playerState.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/playerStates/${playerState.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/playerStates/${playerState.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/playerStates/new">New PlayerState</a> 
</body>