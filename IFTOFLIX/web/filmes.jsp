<%-- 
    Document   : index
    Created on : 31/10/2019, 19:14:37
    Author     : vinicius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=500, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>IFTOFLIX</title>
        <link href="https://fonts.googleapis.com/css?family=Crimson+Text&display=swap" rel="stylesheet">
        <style>
            *{
                box-sizing: border-box;
                margin:0;
            }
            body{
                background-color: #2A2F35;
                color:#FFF;
                font-family: 'Crimson Text', serif;
            }
            header{
                height: 80px;
                width: 100vw;
                padding: 25px;
                background-color: rgba(0, 0, 0, 0.75);
                display: flex;
                color:gainsboro;
                justify-content: space-around;
            }
            main{
                margin:10px;
            }
            img{
                height: 100%;
            }
            h1{
                font-size: 1.4em;
            }

            .filmes{
                background-color: #1E2126;
                border-radius: 28px;
                padding: 10px;
                margin: 5px;

            }
            .categoria{
                height: 25vh;
                display:flex;
                justify-content:center;


            }
            .filme{
                height: 100%;
                padding: 5px;
                border:solid 1px;
                display:flex;
                justify-content:center;
                width:10vw;
                border-radius: 10px


            }
            .filme img{
                height: 100%;
            }
            .op-filmes select{
            padding: 10px;
            border-radius: 28px;
            width: 30vh;
            }
        </style>
    </head>
    <body>
        <header>
            <a href="./ControleVideo"><img src="./iflix.png" alt="logo"></a> 
            <div class="op-filmes">
                
                <select onchange='changeAction(this.value)'>
                    <option>Selecione...</option>
                    <c:forEach var="g" items="${listaGenero}">
                   <option value=${g.id}>${g.genero}</option>
                   </c:forEach>
                </select>
            </div>

            <h1>Bem Vindo, ${user.nome}</h1>
        </header>
        <main>
             <h1>Filmes</h1>
        <span id="mensagem">${requestScope.mensagem}</span>
        <article class="filmes">

                <h3>Mais Votados</h3>
                <section class="categoria">

                    <c:forEach var="mv" items="${requestScope.maisVotados}">

                        <a class="filme" href="/IFTOFLIX/Info?id=${mv.id}">
                            <img src="imagens/${mv.capa}">
                        </a>


                    </c:forEach>
                  
                  
                </section>
        </article>
        
        
        <c:forEach var="p" items="${requestScope.lista}">
            <article class="filmes">

                <h3>${p.g.genero}</h3>
                <section class="categoria">

                    <c:forEach var="v" items="${p.videos}">

                        <a class="filme" href="/IFTOFLIX/Info?id=${v.id}">
                            <img src="imagens/${v.capa}">
                        </a>


                    </c:forEach>
                  
                  
                </section>


            </article>
        </c:forEach>       
        </main>
       
        <script>
            
            function  changeAction(a){
                window.location.href = "/IFTOFLIX/Categoria?cat="+a;
            }
        </script>
        
    </body>
</html>
