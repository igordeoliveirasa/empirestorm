<head>
	<title>Feedback [index]</title>
</head>
<body>
	<h1>Listing Feedbacks</h1>

	<table>
		<tr>
			<th>Facebook user</th>
			<th>Subject</th>
			<th>Message</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${feedbackList}" var="feedback">
			<tr>
				<td>${feedback.facebookUserId}</td>
				<td>${feedback.subject}</td>
				<td>${feedback.message}</td>
				<td><a href="${pageContext.request.contextPath}/feedbacks/${feedback.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/feedbacks/${feedback.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/feedbacks/${feedback.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/feedbacks/new">New Feedback</a> 
</body>