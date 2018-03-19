(: можно использовать конструкцию
if(condition) then
    ...
else
    ...
:)
if (not(doc("books.xml"))) then (
    <error>
        <message>books.xml does not exist</message>
    </error>
) else (
    for $x in doc("books.xml")/books/book
    where $x/price > 30
    return $x/title
)