(:
let - позволяет назначать значение переменной
можно иначе с помощью XPath:
    let $books := doc("books.xml")/books/book[price > 30]
    for $x in $books
    return $x/title
:)
let $books := (doc("books.xml")/books/book)
return <results>
    {
        for $x in $books
        where $x/price > 30
        order by  $x/price descending
        return  $x/title
    }
</results>