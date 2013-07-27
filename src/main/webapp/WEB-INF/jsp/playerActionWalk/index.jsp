<head>
	<title>PlayerActionWalk [index]</title>
</head>
<body>
	<h1>Listing PlayerActionWalks</h1>

	<table>
		<tr>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${playerActionWalkList}" var="playerActionWalk">
			<tr>
				<td><a href="${pageContext.request.contextPath}/playerActionWalks/${playerActionWalk.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/playerActionWalks/${playerActionWalk.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/playerActionWalks/${playerActionWalk.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/playerActionWalks/new">New PlayerActionWalk</a> 
</body>