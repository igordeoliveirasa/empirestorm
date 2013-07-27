<head>
	<title>Place [index]</title>
</head>
<body>
	<h1>Listing Places</h1>

	<table>
		<tr>
			<th>Name</th>
			<th>X</th>
			<th>Y</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${placeList}" var="place">
			<tr>
				<td>${place.name}</td>
				<td>${place.x}</td>
				<td>${place.y}</td>
				<td><a href="${pageContext.request.contextPath}/places/${place.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/places/${place.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/places/${place.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/places/new">New Place</a> 
</body>