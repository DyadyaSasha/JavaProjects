<?xml version ="1.0" encoding = "UTF-8"?>
<!-- xsl документ служит для xml файла как css для html -->
<!-- определяем namespace, в котором определён XSLT, чтобы преобразовывать xml документ -->
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- template указывает процессору XSLT какую часть xml документа нужно отформатировать, в данном случае корневую, т.е весь документ -->
    <xsl:template match="/">
        <html>
            <body>
                <h2>Students</h2>

                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Roll No</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Nick Name</th>
                        <th>Marks</th>
                    </tr>

                    <!-- выбор элементов происходит с помощью XPath
                     / - обозначает корень документа
                     . - текущий узел
                     .. - родительский узел текущего узла
                     @ - атрибут узла
                     name - все узлы с именем name
                     name/nextname - выбрать все nextname элементы, которые являются детьми name
                     //name - выбрать все name элементы независимо от того где они находятся в документе
                     Примеры:
                        <xsl:value-of select = "name(/*)"/> - выбрать корневой узел
                        <xsl:value-of select = "name(/class)"/> - выбрать корневой узел по имени class
                        <xsl:value-of select = "name(/class/*)"/> - выбрать все узлы внутри корневого узла с именем class
                        <xsl:for-each select = "/class/*"> - выбрать все узлы внутри корневого узла с именем class
                        <xsl:for-each select = "/class/student"> - выбрать все узлы student внутри корневого узла class
                        <xsl:for-each select = "//student"> - выбрать все узлы student независимо от того где они находятся в документе
                        <xsl:value-of select = "firstname"/> - берём значение тега firstname
                        <xsl:value-of select = "@rollno"/> - берём значеение атрибута rollno текущего узла
                        <xsl:value-of select = "/class/student/preceding-sibling::comment()"/> - берём значение комментария, предшествующего узлу student

                      Оси(используется для определения элементов по их отношению к другим элементам):
                        ancestor - предки текущего узла до корневого узла
                        ancestor-or-self - текущий узел и его предки
                        attribute - атрибуты текущего узла
                        child - потомки текущего узла
                        descendant - потомки (с своими потомками) текущего узла
                        descendant-or-self - текущий узел и его потомки
                        following - все узлы после текущего узла
                        following-sibling - все одноуровневые узлы некоторого родителя
                        namespace - представляет пространство имён текущего узла
                        parent - представляет родительский узел текущего узла
                        preceding - представляет все предшествующие узлы текущего узла
                        self - представляет текущий узел


                        Операторы:
                        В XPath можно использовать:
                        1) =, !=, <, >, <=, >=
                        2) and, or, not():
                            <xsl:for-each select = "class/student[(@rollno = 393) or ((@rollno  =  493))]"> - выбираем узлы student, у которых атрибут rollno равен 393 или 493
                        3) числовые операторы: +, -, *, div, mod, celling()(округление в меньшую сторону), floor()(округление в большую сторону), round()(округление к ближайшему числу), sum()(сумма двух чисел)
                        4) функции строк: starts-with(string1, string2)(начинается ли string1 с строки string2), contains(string1, string2)(содержится ли в строке string1 строка string2), substring(string, offset, length?)(подстрока строки string начиная с offset длиной length, если length не указано, то с offset и до конца строки), substring-before(string1, string2)(подстрока, предшествующая подстроке string2 в строке string1),
                           substring-after(string1, string2)(подстрока, следующая за подстрокой string2 в строке string1), string-length(string)(длина строки), normalize-space(string)(возвращает строку с удалёнными предшествующими и последующими пробелами), translate(string1, string2, string3)(замена подстроки string2 подстрокой string3 в строке string1), concat(string1, string2, ...)(конкатенация строк), format-number(number1, string1, string2)(возвращает число в формате, указанном в string1; string2 - необязательная строка языкового стандарта)
                        5) операции для узлов:
                           / - выбрать узел внутри конкретного узла
                           // - выбрать узел внутри кооневого узла
                           [...] - используется, чтобы проверить значение самого узла или его атрибута
                           | - объединение двух множеств узлов
                           comment() - выбрать узел, который является комментарием
                           node() - выбор любого вида узла
                           processing-instruction() - выбрать узлы, которые обрабатывают инструкцию
                           text() - выбрать текстовый узел
                           name() - содержит имя узла(выбор по имени)
                           position() - содержит позицию узла(например намер по счёту)
                           last() - выбрать последний узел относительно текущего узла

                        Wildcards(подстановочные знаки):
                            * - используется, чтобы сопаставить любой узел
                            . - используется, чтобы взять текущий узел
                            @* - используется, чтобы сопаставить любой атрибут
                            node() - используется, чтобы сопаставить любой узел любого типа

                        Предикаты(относится к выражению, записанному в квадратных скобках(ограничения к выбору узлов)):
                            /class/student[1] - выбрать первый элемент student, который является ребёнком узла class
                            /class/student[last()] - выбрать последний элемент student, который является ребёнком узла class
                            /class/student[@rolllno = 493] - выбрать узлы student с атрибутом rollno равным 493
                            /class/student[marks>85] - выбрать узлы student, у которых значение тега marks > 85

                     если локация узла в Xpath выражении начинается с "/", то это абсолютный путь, если XPath выражение начинается с имени узла, то это относительный путь(то есть относительно родительского узла)
                     -->
                    <!-- указанный внутри этого тега шаблон формотирования будет повторяться для всех элементов с тегом student, находящихся внутри тега class -->
                    <xsl:for-each select="class/student">

                        <!-- данные появятся в отсортированном виде по тегу lastname(по умолчанию с начала алфавита) -->
                        <xsl:sort select="lastname"/>
                        <!-- будут учитываться только те студенты, у которых значение тега marks > 85 -->
                        <xsl:if test="marks > 85">
                            <tr>
                                <td>
                                    <!-- выбор значения, которое должно отобразиться, в данном случае выбирается значение атрибута тега student -->
                                    <xsl:value-of select="@rollno"/>
                                </td>
                                <td>
                                    <!-- берём значение тега firstname, находящегося внутри тега student -->
                                    <xsl:value-of select="firstname"/>
                                </td>
                                <td>
                                    <xsl:value-of select="lastname"/>
                                </td>
                                <td>
                                    <xsl:value-of select="nickname"/>
                                </td>
                                <td>
                                    <xsl:value-of select="marks"/>
                                </td>

                                <td>
                                    <!-- ветвление, при разных условиях будет напечатаны разные значения -->
                                    <xsl:choose>
                                        <xsl:when test="marks > 93">
                                            High
                                        </xsl:when>
                                        <xsl:when test="marks > 89">
                                            Medium
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Low
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>