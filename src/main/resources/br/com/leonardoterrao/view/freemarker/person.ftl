<#-- @ftlvariable name="" type="br.com.leonardoterrao.view.PersonView" -->
<html>
    <head>
        <link rel="stylesheet" href="/assets/pure-min.css">
    </head>
    <body>
    <!-- calls getPerson().getName() and sanitizes it -->
        <h1>Hello, ${person.name?html}!</h1>
        You are an awesome ${person.job?html}.
    </body>
</html>