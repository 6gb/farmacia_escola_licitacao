<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Fornecedores</title>
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
<form action="fornecedor-controller" method="post">
    <c:choose>
        <c:when test="${fornecedor.cnpj != null}">
            <h2>Editar fornecedor</h2>
            <input type="hidden" name="id" value="${fornecedor.id}" />
        </c:when>
        <c:otherwise>
            <h2>Incluir fornecedor</h2>
            <input type="hidden" name="id" value="0" />
        </c:otherwise>
    </c:choose>
    <table>
        <tr>
            <th>Nome</th>
            <td><input type="text" name="nome" value="${fornecedor.nome}" /></td>
        </tr>
        <tr>
            <th>CNPJ</th>
            <td><input type="text" name="cnpj" value="${fornecedor.cnpj}" /></td>
        </tr>
        <tr>
            <th>Endereco</th>
            <td><input type="text" name="endereco" value="${fornecedor.endereco}" /></td>
        </tr>
        <tr>
            <th>Telefone</th>
            <td><input type="text" name="telefone" value="${fornecedor.telefone}" /></td>
        </tr>
        <tr>
            <th>SICAF</th>
            <td><input type="text" name="sicaf" value="${fornecedor.sicaf}" /></td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td><input type="email" name="email" value="${fornecedor.email}" /></td>
        </tr>
    </table>
    <input type="hidden" name="opcao" value="gravar"/>
    <input type="submit" class="btn btn-success" value="Salvar"/>
</form>

<c:if test="${retorno == 'ok'}">
    <h3>Operação realizada com sucesso</h3>
</c:if>

<table>
    <caption class="caption-top"><h2>Fornecedores</h2></caption>
    <tr>
        <th>Nome</th>
        <th>CNPJ</th>
        <th>Endereco</th>
        <th>Telefone</th>
        <th>SICAF</th>
        <th>E-mail</th>
        <th>Ações</th>
    </tr>
    <%--@elvariable id="fornecedores" type="java.util.List"--%>
    <c:forEach var="f" items="${fornecedores}">
        <tr>
            <td>${f.nome}</td>
            <td>${f.cnpj}</td>
            <td>${f.endereco}</td>
            <td>${f.telefone}</td>
            <td>${f.sicaf}</td>
            <td>${f.email}</td>
            <td>
                <a href="<c:url value="fornecedor-controller?opcao=excluir&&id=${f.id}"/>">Excluir</a>
                <a href="<c:url value="fornecedor-controller?opcao=editar&&id=${f.id}"/>">Editar</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
