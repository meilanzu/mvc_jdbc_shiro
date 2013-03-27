#Spring JDBC + WebMVC example

http://stackoverflow.com/questions/10529963/what-is-the-best-way-to-create-jsp-layout-template
http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
http://stackoverflow.com/questions/12951047/spring-mvc-jsp-mapping

http://blog.inflinx.com/2012/10/09/spring-mvc-sitemesh-3-integration/

http://zousu.com/wp/selenium-webdriver-with-maven/

<c:if test="${c.index % resultsPerPage == 0}">
	<a href="/app/api/type/list?offset=${c.index}&max=${resultsPerPage}" class="btn">${currentPage}</a>
	<fmt:parseNumber var="currentPage" value="${currentPage + 1}" />
</c:if>