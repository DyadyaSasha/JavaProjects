for $x in doc("D:\Projects\Projects for github\JavaProjects\XPathAndXSLT\src\main\resources\books.xml")/books/book
where $x/price > 30
return $x/title