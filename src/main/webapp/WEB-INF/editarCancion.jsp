<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Canción</title>
</head>
<body>
<form:form action="/canciones/procesa/editar/${cancion.id}" 
               method="post" modelAttribute="cancion">

        <div>
            <label for="titulo">Título:</label>
            <form:input path="titulo" id="titulo"/>
            <form:errors path="titulo" cssClass="error"/>
        </div>

        <div>
            <label for="artista">Artista:</label>
            <form:input path="artista" id="artista"/>
            <form:errors path="artista" cssClass="error"/>
        </div>

        <div>
            <label for="album">Álbum:</label>
            <form:input path="album" id="album"/>
            <form:errors path="album" cssClass="error"/>
        </div>

        <div>
            <label for="genero">Género:</label>
            <form:input path="genero" id="genero"/>
            <form:errors path="genero" cssClass="error"/>
        </div>

        <div>
            <label for="idioma">Idioma</label>
            <form:input path="idioma" id="idioma"/>
            <form:errors path="idioma" cssClass="error"/>
        </div>
            
        <div>
            <input type="submit" value="Actualizar"/>
        </div>
    </form:form>

    <br>
    <a href="/canciones">Volver a lista de canciones</a>
</body>
</html>