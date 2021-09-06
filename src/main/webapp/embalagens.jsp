<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Embalagens</title>
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
<form action="embalagem-controller" method="post">
    <c:choose>
        <c:when test="${embalagem.id != null}">
            <h2>Editar embalagem</h2>
            <input type="hidden" name="id" value="${embalagem.id}" />
        </c:when>
        <c:otherwise>
            <h2>Incluir embalagem</h2>
            <input type="hidden" name="id" value="0" />
        </c:otherwise>
    </c:choose>
    <table>
        <tr>
            <th>Descrição</th>
            <td><input type="text" name="descricao" value="${embalagem.descricao}" /></td>
        </tr>
        <tr>
            <th>Material</th>
            <td><input type="text" name="material" value="${embalagem.material}" /></td>
        </tr>
        <tr>
            <th>Capacidade(g)</th>
            <td><input type="number" name="capacidade" value="${embalagem.capacidade}" /></td>
        </tr>
    </table>
    <input type="hidden" name="opcao" value="gravar"/>
    <input type="submit" class="btn btn-success" value="Salvar"/>
</form>

<c:if test="${retorno == 'ok'}">
    <h3>Operação realizada com sucesso</h3>
</c:if>

<table>
    <caption class="caption-top"><h2>Embalagens</h2></caption>
    <tr>
        <th>Descrição</th>
        <th>Material</th>
        <th>Capacidade(g)</th>
        <th>Ações</th>
    </tr>
    <%--@elvariable id="embalagens" type="java.util.List"--%>
    <c:forEach var="e" items="${embalagens}">
        <tr>
            <td>${e.descricao}</td>
            <td>${e.material}</td>
            <td>${e.capacidade}</td>
            <td>
                <a href="<c:url value="embalagem-controller?opcao=excluir&&id=${e.id}"/>">Excluir</a>
                <a href="<c:url value="embalagem-controller?opcao=editar&&id=${e.id}"/>">Editar</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
