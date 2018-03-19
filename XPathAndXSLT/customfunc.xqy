(: определяем функцию :)
declare function local:discount($price as xs:decimal, $percentDiscount as xs:decimal)
    as xs:decimal{
    let $discount := $price - ($price*$percentDiscount div 100)
    return $discount
};

(: определяем переменные :)
let $originalPrice := 100
let $discountPrice := 10

(: вызываем функцию :)
return (local:discount($originalPrice, $discountPrice))