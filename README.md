#Spring JDBC + WebMVC example

http://stackoverflow.com/questions/10529963/what-is-the-best-way-to-create-jsp-layout-template
http://stackoverflow.com/questions/1296235/jsp-tricks-to-make-templating-easier
http://stackoverflow.com/questions/12951047/spring-mvc-jsp-mapping

http://blog.inflinx.com/2012/10/09/spring-mvc-sitemesh-3-integration/

http://zousu.com/wp/selenium-webdriver-with-maven/

http://stackoverflow.com/questions/5784329/how-can-i-make-jenkins-ci-with-git-trigger-on-pushes-to-master

http://blog.cloudbees.com/2012/03/using-git-with-jenkins.html

http://blog.cloudbees.com/2012/01/better-integration-between-jenkins-and.html

http://stackoverflow.com/questions/7743749/shiro-authorization-permission-check-using-annotation-not-working

<c:if test="${c.index % resultsPerPage == 0}">
	<a href="/app/api/type/list?offset=${c.index}&max=${resultsPerPage}" class="btn">${currentPage}</a>
	<fmt:parseNumber var="currentPage" value="${currentPage + 1}" />
</c:if>