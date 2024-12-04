<#--&lt;#&ndash;<html lang="ru">&ndash;&gt;-->
<#--&lt;#&ndash;<head>&ndash;&gt;-->
<#--&lt;#&ndash;    <meta charset="UTF-8">&ndash;&gt;-->
<#--&lt;#&ndash;    <title>Главная страница</title>&ndash;&gt;-->
<#--&lt;#&ndash;    <link rel="stylesheet"&ndash;&gt;-->
<#--&lt;#&ndash;          href="https://cdn.jsdelivr.net/gh/yegor256/tacit@gh-pages/tacit-css-1.6.0.min.css"/>&ndash;&gt;-->
<#--&lt;#&ndash;</head>&ndash;&gt;-->

<#--&lt;#&ndash;<body>&ndash;&gt;-->

<#--&lt;#&ndash;<h1>Список статей</h1>&ndash;&gt;-->
<#--&lt;#&ndash;<table>&ndash;&gt;-->
<#--&lt;#&ndash;    <tr>&ndash;&gt;-->
<#--&lt;#&ndash;        <th>Название</th>&ndash;&gt;-->
<#--&lt;#&ndash;    </tr>&ndash;&gt;-->
<#--&lt;#&ndash;    <#list articles as article>&ndash;&gt;-->
<#--&lt;#&ndash;        <tr>&ndash;&gt;-->
<#--&lt;#&ndash;            <td>${article.name}</td>&ndash;&gt;-->
<#--&lt;#&ndash;        </tr>&ndash;&gt;-->
<#--&lt;#&ndash;    </#list>&ndash;&gt;-->

<#--&lt;#&ndash;    <tr>&ndash;&gt;-->
<#--&lt;#&ndash;        <th>Теги</th>&ndash;&gt;-->
<#--&lt;#&ndash;    </tr>&ndash;&gt;-->
<#--&lt;#&ndash;    <#list articles as article>&ndash;&gt;-->
<#--&lt;#&ndash;        <tr>&ndash;&gt;-->
<#--&lt;#&ndash;            <td>${article.tags}</td>&ndash;&gt;-->
<#--&lt;#&ndash;        </tr>&ndash;&gt;-->
<#--&lt;#&ndash;    </#list>&ndash;&gt;-->

<#--&lt;#&ndash;    <tr>&ndash;&gt;-->
<#--&lt;#&ndash;        <th>Комментарии</th>&ndash;&gt;-->
<#--&lt;#&ndash;    </tr>&ndash;&gt;-->
<#--&lt;#&ndash;    <#list articles as article>&ndash;&gt;-->
<#--&lt;#&ndash;        <tr>&ndash;&gt;-->
<#--&lt;#&ndash;            <td>${article.comments}</td>&ndash;&gt;-->
<#--&lt;#&ndash;        </tr>&ndash;&gt;-->
<#--&lt;#&ndash;    </#list>&ndash;&gt;-->
<#--&lt;#&ndash;</table>&ndash;&gt;-->

<#--&lt;#&ndash;</body>&ndash;&gt;-->

<#--&lt;#&ndash;</html>&ndash;&gt;-->


<#--<html lang="ru">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Главная страница</title>-->
<#--    <link rel="stylesheet"-->
<#--          href="https://cdn.jsdelivr.net/gh/yegor256/tacit@gh-pages/tacit-css-1.6.0.min.css"/>-->
<#--    <style>-->
<#--        body {-->
<#--            font-family: Arial, sans-serif;-->
<#--            margin: 20px;-->
<#--            padding: 20px;-->
<#--            background-color: #f4f4f9;-->
<#--        }-->

<#--        h1 {-->
<#--            color: #333;-->
<#--            text-align: center;-->
<#--        }-->

<#--        table {-->
<#--            width: 100%;-->
<#--            margin-bottom: 20px;-->
<#--            border-collapse: collapse;-->
<#--        }-->

<#--        th, td {-->
<#--            padding: 12px;-->
<#--            text-align: left;-->
<#--            border: 1px solid #ddd;-->
<#--        }-->

<#--        th {-->
<#--            background-color: #f2f2f2;-->
<#--        }-->

<#--        tr:nth-child(even) {-->
<#--            background-color: #f9f9f9;-->
<#--        }-->

<#--        tr:hover {-->
<#--            background-color: #f1f1f1;-->
<#--        }-->

<#--        .table-container {-->
<#--            margin-bottom: 30px;-->
<#--        }-->

<#--        .table-title {-->
<#--            font-size: 1.2em;-->
<#--            font-weight: bold;-->
<#--            margin-bottom: 10px;-->
<#--        }-->

<#--    </style>-->
<#--</head>-->

<#--<body>-->

<#--<h1>Список статей</h1>-->

<#--<div class="table-container">-->
<#--    <div class="table-title">Название статей</div>-->
<#--    <table>-->
<#--        <#list articles as article>-->
<#--            <tr>-->
<#--                <td>${article.name}</td>-->
<#--            </tr>-->
<#--        </#list>-->
<#--    </table>-->
<#--</div>-->

<#--<div class="table-container">-->
<#--    <div class="table-title">Теги</div>-->
<#--    <table>-->
<#--        <#list articles as article>-->
<#--            <tr>-->
<#--                <td>${article.tags}</td>-->
<#--            </tr>-->
<#--        </#list>-->
<#--    </table>-->
<#--</div>-->

<#--<div class="table-container">-->
<#--    <div class="table-title">Количество Комментариев</div>-->
<#--    <table>-->
<#--        <#list articles as article>-->
<#--            <tr>-->
<#--                <td>${article.comments}</td>-->
<#--            </tr>-->
<#--        </#list>-->
<#--    </table>-->
<#--</div>-->

<#--</body>-->
<#--</html>-->

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/yegor256/tacit@gh-pages/tacit-css-1.6.0.min.css"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f9;
        }

        h1 {
            color: #333;
            text-align: center; /* Центрирование заголовка */
        }

        table {
            width: 100%;
            margin-top: 20px; /* Отступ сверху для таблицы */
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>

<body>

<h1>Список статей</h1>

<table>
    <tr>
        <th>Название</th>
        <th>Теги</th>
        <th>Количество комментариев</th>
    </tr>
    <#list articles as article>
        <tr>
            <td>${article.name}</td>
            <td>${article.tags}</td>
            <td>${article.comments}</td>
        </tr>
    </#list>
</table>

</body>
</html>
