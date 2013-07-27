<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/states" method="post">
  
	<c:if test="${not empty state.id}">
		<input type="hidden" name="state.id" value="${state.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Thirsty level:<br />
	
		<input type="text" name="state.thirstyLevel" value="${state.thirstyLevel}"/>
	</div>
	
	<div class="field">
		Hungry level:<br />
	
		<input type="text" name="state.hungryLevel" value="${state.hungryLevel}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/states">Back</a>
