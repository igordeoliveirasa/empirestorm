<head>
	<title>PlaceType [index]</title>
</head>
<body>
	<h1>Listing PlaceTypes</h1>

	<table>
		<tr>
			<th>Name</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${placeTypeList}" var="placeType">
			<tr>
				<td>${placeType.name}</td>
				<td><a href="${pageContext.request.contextPath}/placeTypes/${placeType.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/placeTypes/${placeType.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/placeTypes/${placeType.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/placeTypes/new">New PlaceType</a> 
</body>