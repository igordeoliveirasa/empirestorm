<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/placeTypes" method="post">
  
	<c:if test="${not empty placeType.id}">
		<input type="hidden" name="placeType.id" value="${placeType.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Name:<br />
	
		<input type="text" name="placeType.name" value="${placeType.name}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/placeTypes">Back</a>
