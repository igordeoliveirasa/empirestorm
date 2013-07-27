<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/playerCredentials" method="post">
  
	<c:if test="${not empty playerCredentials.id}">
		<input type="hidden" name="playerCredentials.id" value="${playerCredentials.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Email:<br />
	
		<input type="text" name="playerCredentials.email" value="${playerCredentials.email}"/>
	</div>
	
	<div class="field">
		Password:<br />
	
		<input type="text" name="playerCredentials.password" value="${playerCredentials.password}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/playerCredentials">Back</a>
