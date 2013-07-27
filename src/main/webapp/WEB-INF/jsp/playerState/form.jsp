<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/playerStates" method="post">
  
	<c:if test="${not empty playerState.id}">
		<input type="hidden" name="playerState.id" value="${playerState.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Thirsty level:<br />
	
		<input type="text" name="playerState.thirstyLevel" value="${playerState.thirstyLevel}"/>
	</div>
	
	<div class="field">
		Hungry level:<br />
	
		<input type="text" name="playerState.hungryLevel" value="${playerState.hungryLevel}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/playerStates">Back</a>
