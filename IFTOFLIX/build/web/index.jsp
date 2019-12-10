<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>IFTO Flix</title>
        <link rel="stylesheet" href="style.css">
        <style>
header{
    height: 80px;
    width: 100vw;
    padding: 25px;
    background-color: rgba(0, 0, 0, 0.75);
    display: flex;
    color:gainsboro;
    justify-content: space-around;
}

img{
    height: 100%;
}
    </style>
    </head>
    <body>
        <header>
            <img src="./iflix.png" alt="logo">

        </header>
        <section>
            <div class="links">
                <button class="aba" onclick="abrir('Login', this, 'blue')" id="defaultOpen">Login</button>
                <button class="aba" onclick="abrir('Signup', this, 'green')" >Inscreva-se</button>
            </div>
            <div class="forms">
                <div id="Login" class="tabcontent">

                    <form method="POST" action="Usuario">
                        ${requestScope.mensagemL}
                        <input type="text" id="email" name="emailL" placeholder="Email" required>
                        <input type="password" name="passwordL" id="Senha" placeholder="Senha" required>
                        <input type="hidden"  name="acao" value="1">
                        <button class="button">Login</button>
                    </form>
                </div>

                <div id="Signup" class="tabcontent">
                    <span id="mensagem">${requestScope.mensagemS}</span>
                    <form method="POST" action="Usuario">
                        <input type="text" name="nomeS" placeholder="Nome" required>
                        <input type="text" id="email" placeholder="Email" name="emailS" required>
                        <input type="password" name="passwordS" id="Senha" placeholder="Senha" required>
                        <input type="hidden"  name="acao" value="2">
                        <button class="button">Cadastrar</button>
                    </form>


                </div>
            </div>

        </section>
        <script src="cod.js"></script>
    </body>
</html>
