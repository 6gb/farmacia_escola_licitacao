<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="daof" scope="request" type="br.ufsm.csi.dao.FornecedorDAO"/>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Fornecimento de matérias primas e embalagens</title>
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

    <div class="float-start">
        <form action="fornecimento-controller" method="post">
            <h2>Incluir preço</h2>
            <table>
                <tr>
                    <th><label for="idFmp">Fornecedor</label></th>
                    <td>
                        <select id="idFmp" name="idF">
                            <c:forEach var="f" items="${daof.fornecedores}">
                                <option value="${f.id}">${f.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="idMP">Matéria prima</label></th>
                    <td>
                        <select id="idMP" name="idMP">
                            <jsp:useBean id="daomp" scope="request" type="br.ufsm.csi.dao.MateriaPrimaDAO"/>
                            <c:forEach var="mp" items="${daomp.materiasPrimas}">
                                <option value="${mp.id}">${mp.descricao}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="precoMP">Preço</label></th>
                    <td><input type="number" step="0.01" id="precoMP" name="preco"/></td>
                </tr>
            </table>
            <input type="hidden" name="opcao" value="gravarMP"/>
            <input type="hidden" name="idL" value="${param.idL}"/>
            <input type="submit" class="btn btn-success" value="Salvar"/>
        </form>

        <c:if test="${retorno == 'ok'}">
            <h3>Operação realizada com sucesso</h3>
        </c:if>

        <table>
            <caption class="caption-top"><h2>Matérias primas</h2></caption>
            <tr>
                <th>Fornecedor</th>
                <th>Matéria prima</th>
                <th>Preço</th>
                <th>Média</th>
                <th>Opção</th>
            </tr>
            <%--@elvariable id="fmp" type="java.util.List"--%>
            <c:forEach var="f" items="${fmp}">
                <tr>
                    <td>${daof.getFornecedor(f.idFornecedor).nome}</td>
                    <td>${daomp.getMateriaPrima(f.idMateriaPrima).descricao}</td>
                    <td>${f.preco}</td>
                    <td>${f.media}</td>
                    <td>
                        <a href="http://localhost:8080/farmacia_escola_licitacao/fornecimento-controller?opcao=excluirMP&&idL=${f.idLicitacao}&&idF=${f.idFornecedor}&&idMP=${f.idMateriaPrima}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="float-start">
        <form action="fornecimento-controller" method="post">
            <h2>Incluir preço</h2>
            <table>
                <tr>
                    <th><label for="idFe">Fornecedor</label></th>
                    <td>
                        <select id="idFe" name="idF">
                            <c:forEach var="f" items="${daof.fornecedores}">
                                <option value="${f.id}">${f.nome}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="idE">Embalagem</label></th>
                    <td>
                        <select id="idE" name="idE">
                            <jsp:useBean id="daoe" scope="request" type="br.ufsm.csi.dao.EmbalagemDAO"/>
                            <c:forEach var="e" items="${daoe.embalagens}">
                                <option value="${e.id}">${e.descricao}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="precoE">Preço</label></th>
                    <td><input type="number" step="0.01" id="precoE" name="preco"/></td>
                </tr>
            </table>
            <input type="hidden" name="opcao" value="gravarE"/>
            <input type="hidden" name="idL" value="${param.idL}"/>
            <input type="submit" class="btn btn-success" value="Salvar"/>
        </form>

        <c:if test="${retorno == 'ok'}">
            <h3>Operação realizada com sucesso</h3>
        </c:if>

        <table>
            <caption class="caption-top"><h2>Embalagens</h2></caption>
            <tr>
                <th>Fornecedor</th>
                <th>Embalagem</th>
                <th>Preço</th>
                <th>Média</th>
                <th>Opção</th>
            </tr>

            <%--@elvariable id="fe" type="java.util.List"--%>
            <c:forEach var="f" items="${fe}">
                <tr>
                    <td>${daof.getFornecedor(f.idFornecedor).nome}</td>
                    <td>${daoe.getEmbalagem(f.idEmbalagem).descricao}</td>
                    <td>${f.preco}</td>
                    <td>${f.media}</td>
                    <td>
                        <a href="http://localhost:8080/farmacia_escola_licitacao/fornecimento-controller?opcao=excluirE&&idL=${f.idLicitacao}&&idF=${f.idFornecedor}&&idE=${f.idEmbalagem}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
