<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Matérias primas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <ul class="nav">
        <li class="navbar-brand"><img class="mr-auto" style="width: 106px; height: 43px;" src="<c:url value='/resources/logo.jpg'/>" alt="Farmácia Escola UFSM"></li>
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<c:url value="licitacao-controller"/>">Licitações</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value="fornecedor-controller"/>">Fornecedores</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value="embalagem-controller"/>">Embalagens</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value="materiaprima-controller"/>">Matérias primas</a></li>
    </ul>
<form action="materiaprima-controller" method="post">
    <c:choose>
        <c:when test="${materiaprima.id != null}">
            <h2>Editar matéria prima</h2>
            <input type="hidden" name="id" value="${materiaprima.id}" />
        </c:when>
        <c:otherwise>
            <h2>Incluir matéria prima</h2>
            <input type="hidden" name="id" value="0" />
        </c:otherwise>
    </c:choose>
    <table>
        <tr>
            <th>Descrição</th>
            <td><input type="text" name="descricao" value="${materiaprima.descricao}" /></td>
        </tr>
        <tr>
            <th>CAS</th>
            <td><input type="text" name="cas" value="${materiaprima.cas}" /></td>
        </tr>
        <tr>
            <th>DCB</th>
            <td><input type="text" name="dcb" value="${materiaprima.dcb}" /></td>
        </tr>
        <tr>
            <th>DCI</th>
            <td><input type="text" name="dci" value="${materiaprima.dci}" /></td>
        </tr>
    </table>
    <input type="hidden" name="opcao" value="gravar"/>
    <input type="submit" class="btn btn-success" value="Salvar"/>
</form>

<c:if test="${retorno == 'ok'}">
    <h3>Operação realizada com sucesso</h3>
</c:if>

<table>
    <caption class="caption-top"><h2>Matérias primas</h2></caption>
    <tr>
        <th>Descrição</th>
        <th>CAS</th>
        <th>DCB</th>
        <th>DCI</th>
        <th>Ações</th>
    </tr>
    <%--@elvariable id="materiasprimas" type="java.util.List"--%>
    <c:forEach var="mp" items="${materiasprimas}">
        <tr>
            <td>${mp.descricao}</td>
            <td>${mp.cas}</td>
            <td>${mp.dcb}</td>
            <td>${mp.dci}</td>
            <td>
                <a href="<c:url value="materiaprima-controller?opcao=excluir&&id=${mp.id}"/>">Excluir</a>
                <a href="<c:url value="materiaprima-controller?opcao=editar&&id=${mp.id}"/>">Editar</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
