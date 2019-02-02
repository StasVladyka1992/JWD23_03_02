<%--
  Created by IntelliJ IDEA.
  User: Стас
  Date: 28.01.2019
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="../../styles/menuCss.css"/>"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="prop" var="result"/>


    <fmt:message bundle="${result}" key="coldSnackMenu" var="coldSnackMenuP"/>
    <fmt:message bundle="${result}" key="hotSnackMenu" var="hotSnackMenuP"/>
    <fmt:message bundle="${result}" key="breakfastMenu" var="breakfastMenuP"/>
    <fmt:message bundle="${result}" key="saladsMenu" var="saladsMenuP"/>
    <fmt:message bundle="${result}" key="soupMenu" var="soupMenuP"/>
    <fmt:message bundle="${result}" key="fishMenu" var="fishMenuP"/>
    <fmt:message bundle="${result}" key="meatMenu" var="meatMenuP"/>
    <fmt:message bundle="${result}" key="menu" var="menuP"/>
    <fmt:message bundle="${result}" key="photo" var="photoP"/>
    <fmt:message bundle="${result}" key="name" var="nameP"/>
    <fmt:message bundle="${result}" key="description" var="descriptionP"/>
    <fmt:message bundle="${result}" key="portion" var="portionP"/>
    <fmt:message bundle="${result}" key="price" var="priceP"/>
    <fmt:message bundle="${result}" key="previous" var="previousP"/>
    <fmt:message bundle="${result}" key="next" var="nextP"/>
    <fmt:message bundle="${result}" key="toMain" var="toMainP"/>

</head>

<body>
<div class="container-fluid">
    <div class="row">
        <h1 class="mx-auto"><c:out value="${menuP}"></c:out></h1>
    </div>
    <div class="row">
        <div class="col-sm-2">
            <div class="list-group">
                <a href="/parser?menuType=ColdSnackMenu&command=PARSE&parser=${sessionScope.get("parser")}"
                   class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${coldSnackMenuP}"/></a>
                <a href="/parser?menuType=HotSnackMenu&command=PARSE&parser=${sessionScope.get("parser")}"
                   class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${hotSnackMenuP}"/></a>
                <a href="/parser?menuType=BreakfastMenu&command=PARSE&parser=${sessionScope.get("parser")}"
                   class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${breakfastMenuP}"/></a>
                <a href="#" class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${saladsMenuP}"/></a>
                <a href="#" class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${soupMenuP}"/></a>
                <a href="#" class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${fishMenuP}"/></a>
                <a href="#" class="list-group-item list-group-item-action list-group-item-secondary"><c:out
                        value="${meatMenuP}"/></a>
            </div>
        </div>
        <div class="col-sm-10">
            <table class="table table-hover">
                <thead>
                <th class="align-middle"><c:out value="${photoP}"/></th>
                <th class="align-middle"><c:out value="${nameP}"/></th>
                <th class="align-middle"><c:out value="${descriptionP}"/></th>
                <th class="align-middle"><c:out value="${portionP}"/></th>
                <th class="align-middle"><c:out value="${priceP}"/></th>
                </thead>

                <c:forEach var="dish" items="${menu}">
                    <c:set var="lastDish" value="${lastDish+1}"></c:set>
                    <tr>
                        <c:set var="description" value="${dish.description}"></c:set>
                        <td><img src="<c:out value="${dish.photo}"/>"/></td>
                        <td><c:out value="${dish.name}"/></td>
                        <td>
                            <c:if test="${description.size()>1}">
                                <c:if test="${not empty dish.additionalInfo}">
                                    <c:out value="${dish.additionalInfo}"></c:out>
                                </c:if>
                                <ol>
                                    <c:forEach items="${description}" var="description_price">
                                        <li><c:out value="${description_price.description}"/></li>
                                    </c:forEach>
                                </ol>
                            </c:if>
                            <c:if test="${description.size()==1}">
                                <c:if test="${not empty dish.additionalInfo}">
                                    <c:out value="${dish.additionalInfo}"></c:out>
                                </c:if>
                                <c:forEach items="${description}" var="description_price">
                                    <c:out value="${description_price.description}"/>
                                </c:forEach>
                            </c:if>
                        </td>
                        <td><c:out value="${dish.portion}"/></td>
                        <td>
                            <c:if test="${description.size()>1}">
                                <c:if test="${not empty dish.additionalInfo}">
                                    </br>
                                </c:if>
                                <ol>
                                    <c:forEach items="${description}" var="description_price">
                                        <c:choose>
                                            <c:when test="${description_price.price==0}">
                                                <li><c:out value=""/></li>
                                            </c:when>
                                            <c:when test="${description_price.price!=0}">
                                                <li><c:out value="${description_price.price}"/></li>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </ol>
                            </c:if>
                            <c:if test="${description.size()==1}">
                                <c:if test="${not empty dish.additionalInfo}">
                                </br>
                                </c:if>
                                <c:forEach items="${description}" var="description_price">
                                    <c:choose>
                                        <c:when test="${description_price.price==0}">
                                            <c:out value=""/>
                                        </c:when>
                                        <c:when test="${description_price.price!=0}">
                                            <c:out value="${description_price.price}"/>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <c:if test="${pagesNumber!=1}">
        <ul class="pagination justify-content-center">
            <!--disabled "Previous" link-->
            <c:if test="${currentPage>1}">
                <li class="page-item"><a class="page-link"
                                         href="/parser?currentPage=${currentPage-1}&lastdish=${(currentPage-2)*5}&menutype=${menuType}&command=PARSE&parser=${parser}"><c:out
                        value="${previousP}"/></a>
                </li>
            </c:if>
            <!--available "Previous" link-->
            <c:if test="${currentPage==1}">
                <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${previousP}"/></a></li>
            </c:if>
            <!--setting  query's parameters depending on clicked page-->
            <c:forEach begin="${1}" end="${pagesNumber}" var="i">
                <c:choose>
                    <c:when test="${currentPage==i}">
                        <li class="page-item active"><a class="page-link"
                                                        href="/parser?currentPage=${i}&lastDish=${lastDish-5}&menuType=${menuType}&command=PARSE&parser=${parser}">${i}</a>
                        </li>
                    </c:when>
                    <c:when test="${i!=currentPage}">
                        <c:if test="${i>currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/parser?currentPage=${i}&lastDish=${(i-1)*5}&menuType=${menuType}&command=PARSE&parser=${parser}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${i<currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/parser?currentPage=${i}&lastDish=${(i-1)*5}&menuType=${menuType}&command=PARSE&parser=${parser}">${i}</a>
                            </li>
                        </c:if>
                    </c:when>
                </c:choose>
            </c:forEach>
            <!--available "Next" link-->
            <c:if test="${currentPage+1<=pagesNumber}">
                <li class="page-item"><a class="page-link"
                                         href="/parser?currentPage=${currentPage+1}&lastDish=${lastDish}&menuType=${menuType}&command=PARSE&parser=${parser}"><c:out
                        value="${nextP}"/></a>
                </li>
            </c:if>
            <!--disabled "Next" link-->
            <c:if test="${currentPage==pagesNumber}">
                <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${nextP}"/></a></li>
            </c:if>
        </ul>
    </c:if>
    <div>
        <a href="../../index.html">&larr;<c:out value="${toMainP}"/></a>
    </div>
</div>
</body>
</html>
