<head>
	<title>Feedback [show]</title>
</head>
<body>
	<p>
		<b>Facebook user:</b>
		${feedback.facebookUserId}
	</p>
	<p>
		<b>Subject:</b>
		${feedback.subject}
	</p>
	<p>
		<b>Message:</b>
		${feedback.message}
	</p>

	<a href="${pageContext.request.contextPath}/feedbacks/${feedback.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/feedbacks">Back</a>
</body>