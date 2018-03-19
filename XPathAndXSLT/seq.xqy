(:
 генерируемая последовательность
 функции, которые могут использоваться в последовательностях:
    count($seq as item()*) - кол-во элементов в последовательности
    sum($seq as item()*) - сумма эл-тов в последовательности
    avg($seq as item()*) - среднее значение среди элементов последовательности
    min($seq as item()*) - минимальное значение элемента в последовательности
    max($seq as item()*) - максимальное значение элемента в последовательности
    distinct-values($seq as item()*) - уникальные значения последовательности
    subsequence($seq as item()*, $startingLoc as xs:double, $length as xs:double) - вывести последовательность исходной последовательности определенной длины
    insert-before($seq as item()*, $position as xs:integer, $inserts as item()*) - вставить последовательность в определённую позицию в другую последовательность
    remove($seq as item()*, $position as xs:integer) - удаляет конкретный элемент из последовательности
    reverse($seq as item()*) - возвращает последовательность в обратном порядке
    index-of($seq as anyAtomicType()*, $target as anyAtomicType()) - возвращет индекс указанного элемента в последовательности
    last() - последний элемент в последовательности
    position() - текущая позиция элемента в последовательности

 строковые функции:
    string-length($string as xs:string) - длина строки
    concat($input as xs:anyAtomicType?) - конкатенация строк
    string-join($sequence as xs:string*, $delimiter as xs:string) - объединение элементов в строку с определённым разделителем

 функции дат:
    current-date() - текущая дата
    current-time() - текущее время
    current-dateTime() - текущая дата и время

 регулярные выражения:
    matches($input, $regex) - возвращает true, если input соответствует регулярному выражению
    replace($input, $regex, $string) - заменяет совподающую входную строку заданной строкой
    tokenize($input, $regex) - возвращает последовательность элементов, соответствующих регулярному выражению

:)
let $items := ('orange', <apple/>, <fruit type="juicy"/>, <vehicle type="car">sentro</vehicle>, 1, 2, 3, 'a', 'b', "abc")
let $count := count($items)
return
    <result>
        <count>{$count}</count>
        <items>
            {
                for $item in $items
                return <item>{$item}</item>
            }
        </items>
    </result>