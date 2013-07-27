<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/playerSkills" method="post">
  
	<c:if test="${not empty playerSkills.id}">
		<input type="hidden" name="playerSkills.id" value="${playerSkills.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Walk velocity:<br />
	
		<input type="text" name="playerSkills.walkVelocity" value="${playerSkills.walkVelocity}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/playerSkills">Back</a>
