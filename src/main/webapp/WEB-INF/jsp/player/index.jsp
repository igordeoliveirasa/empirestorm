<head>
	<title>Player [index]</title>
</head>
<body>
	<h1>Listing Players</h1>

	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${playerList}" var="player">
			<tr>
				<td>${player.name}</td>
				<td><a href="${pageContext.request.contextPath}/players/${player.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/players/${player.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/players/${player.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/players/new">New Player</a> 
</body>