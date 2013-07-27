<head>
	<title>PlayerSkills [index]</title>
</head>
<body>
	<h1>Listing PlayerSkills</h1>

	<table>
		<tr>
			<th>Walk velocity</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${playerSkillsList}" var="playerSkills">
			<tr>
				<td>${playerSkills.walkVelocity}</td>
				<td><a href="${pageContext.request.contextPath}/playerSkills/${playerSkills.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/playerSkills/${playerSkills.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/playerSkills/${playerSkills.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/playerSkills/new">New PlayerSkills</a> 
</body>