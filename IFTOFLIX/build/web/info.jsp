<%-- 
    Document   : info
    Created on : 29/11/2019, 18:07:05
    Author     : maria.maciel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>IFTO Flix</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        main{
            background-color: rgba(0, 0, 0, 0.55);
            height: calc(100vh - 80px);


        }
        .titulo{
            height: 10%;
            color:gainsboro;
            padding: 20px;
        }
        .info{
            display:flex;
            height: 60%;
            color:gainsboro;
            
        }
        .info .sinopse{
            width: 50vw;
            text-align: justify;
            justify-content:center;
            align-items:center;
            padding: 20px;
           
        }
        .info .video{
            width: 50vw;
            justify-content:center;
            align-items:center;
            padding: 20px;
        }
        
     

        .fa-star{
            background-color: whitesmoke;
            font-size: 1.5em;
        }

        .active {
            color:orange;
            font-size: 2em;

        }
        .rcontainer{    
            display:flex;
            padding: 20px;
            justify-content:center;
        
        }
    </style>
</head>
<body>
    <header>
        <a href="./ControleVideo"><img src="./iflix.png" alt="logo"></a>
      
        <h1>Bem Vindo, ${user.nome}</h1>
    </header>
    <main> 
        <div class="titulo">
            <h1>${requestScope.listav.nome}</h1>
            <p>${requestScope.listav.duracao}</p>
        </div>

        <div class="info">


            <div class="sinopse">
                <img src="imagens/${requestScope.listav.capa}">
                <p>${requestScope.listav.sinopse}</p>
            </div>
            <div class="video">
                <iframe width="560" height="315" src="${requestScope.listav.url}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
<aside class="rcontainer">
            <div class="rating">
                <span class="fa fa-star" onclick="a(0,${user.id},${requestScope.listav.id})"></span>
                <span class="fa fa-star" onclick="a(1,${user.id},${requestScope.listav.id})"></span>
                <span class="fa fa-star" onclick="a(2,${user.id},${requestScope.listav.id})"></span>
                <span class="fa fa-star" onclick="a(3,${user.id},${requestScope.listav.id})"></span>
                <span class="fa fa-star" onclick="a(4,${user.id},${requestScope.listav.id})"></span>

            </div>
        </aside>
            </div>


        </div >

        
    </main> 
    <script>
        
            function criarAjaxPost(voto,usuario,filme)
{


    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200)
        {
            alert(this.responseText)
        }
    }
    ajax.open("POST", "Voto", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
ajax.send("voto="+voto+"&usuario="+usuario+"&filme="+filme);
}
                const estrela = document.getElementsByClassName('fa-star')
                function a(pt,user,filme){
                for (let i = pt; i > - 1; i--){
                estrela[i].classList.add('active')
                }
                for (let i = pt + 1; i < 5; i++){
                estrela[i].classList.remove('active')
                }
                  pt++
                  
                  criarAjaxPost(pt,user,filme)
                  
                  

                }

    </script>
</body>
</html>
