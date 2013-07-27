<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/places" method="post">
  
	<c:if test="${not empty place.id}">
		<input type="hidden" name="place.id" value="${place.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Name:<br />
	
		<input type="text" name="place.name" value="${place.name}"/>
	</div>
	
	<div class="field">
		X:<br />
	
		<input type="text" name="place.x" value="${place.x}"/>
	</div>
	
	<div class="field">
		Y:<br />
	
		<input type="text" name="place.y" value="${place.y}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/places">Back</a>
