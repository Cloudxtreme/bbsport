<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <!-- 页面索引 -->
总记录数:${pageView.totalrecord},每页显示${pageView.maxresult},总页数:${pageView.totalpage},当前页:${pageView.currentpage}
<c:forEach begin="${pageView.pageIndex.startindex}" end="${pageView.pageIndex.endindex}" var="wp">
      <c:if test="${pageView.currentpage == wp}"><font color="#FFFFFF">第${wp}页</font></c:if>
      <c:if test="${pageView.currentpage != wp}"><a href="javascript:topage('${wp}')" class="a03">第${wp}页</a></c:if>
</c:forEach>
