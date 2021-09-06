<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Licitações</title>
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

    <table>
        <caption class="caption-top"><h2>Licitações</h2></caption>
        <tr>
            <th>Data da criação</th>
            <th>Ações</th>
        </tr>
        <%--@elvariable id="licitacoes" type="java.util.List"--%>
        <c:forEach var="l" items="${licitacoes}">
            <tr>
                <td>${l.dataCriacao}</td>
                <td>
                    <a href="<c:url value="fornecimento-controller?idL=${l.id}"/>">Ver/Editar</a>
                    <a href="<c:url value="licitacao-controller?opcao=excluir&&id=${l.id}"/>">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="licitacao-controller" method="post">
        <input type="hidden" name="opcao" value="cadastrar"/>
        <input type="submit" class="btn btn-link" value="Cadastrar nova"/>
    </form>
    <c:if test="${retorno == 'ok'}">
        <h3>Operação realizada com sucesso</h3>
    </c:if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
