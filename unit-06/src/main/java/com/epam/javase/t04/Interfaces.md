<html>

<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta name=Generator content="Microsoft Word 15 (filtered)">
</head>

<body lang=EN-US>

<div class=WordSection1>

<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=859
 style='width:644.0pt;margin-left:-.5pt;border-collapse:collapse'>
 <tr style='height:21.0pt'>
  <td width=103 style='width:77.0pt;border:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;
  height:21.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:16.0pt;font-family:"Times New Roman",serif;
  color:black'>&nbsp;</span></p>
  </td>
  <td width=309 style='width:232.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0in 5.4pt 0in 5.4pt;height:21.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:16.0pt;font-family:"Times New Roman",serif;
  color:black'>Основная функциональность</span></p>
  </td>
  <td width=447 style='width:335.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0in 5.4pt 0in 5.4pt;height:21.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span style='font-size:16.0pt;font-family:"Times New Roman",serif;
  color:black'>Примеры типичного использования</span></p>
  </td>
 </tr>
 <tr style='height:75.0pt'>
  <td width=103 nowrap style='width:77.0pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0in 5.4pt 0in 5.4pt;height:75.0pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><span style='font-size:14.0pt;
  color:black'>Set</span></p>
  </td>
  <td width=309 valign=top style='width:232.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:75.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Коллекция которая не может
  содержать одинаковых элементов, при этом порядок вставки элементов не
  сохраняется(исключение - </span><span style='color:black'>LinkedHashSet</span><span
  lang=RU style='color:black'>). </span><span style='color:black'>Реализует все
  методы интерфейса Collection.</span></p>
  </td>
  <td width=447 valign=top style='width:335.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:75.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Имена файлов в одной директории,
  список </span><span style='color:black'>e</span><span lang=RU
  style='color:black'>-</span><span style='color:black'>mail</span><span
  lang=RU style='color:black'> адресов, имена/</span><span style='color:black'>id</span><span
  lang=RU style='color:black'> запущенных процессов, колода игральных карт</span></p>
  </td>
 </tr>
 <tr style='height:255.0pt'>
  <td width=103 nowrap style='width:77.0pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0in 5.4pt 0in 5.4pt;height:255.0pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><span style='font-size:14.0pt;
  color:black'>List</span></p>
  </td>
  <td width=309 valign=top style='width:232.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:255.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Коллекция которая сохраняет
  последовательность вставки элементов. Допускает существование дубликатов.
  Помимо реализации всех методов интерфейса </span><span style='color:black'>Collection</span><span
  lang=RU style='color:black'> включет методы для работы с элементами по
  индексу: добавление/удаление/получение/замена элемента по указанному индексу;
  </span><span style='color:black'>indexOf</span><span lang=RU
  style='color:black'> и </span><span style='color:black'>lastIndexOf</span><span
  lang=RU style='color:black'> для поиска индекса элемента; метод для
  добавления нескольких элементов начиная с указанной позиции, а также метод </span><span
  style='color:black'>sublist</span><span lang=RU style='color:black'>
  позволяющий работать с частью листа. Помимо стандартного итератора определяет
  итератор </span><span style='color:black'>ListIterator</span><span lang=RU
  style='color:black'>, который позволяет итерироваться по листу в обоих
  направлениях, а также добавлять и удалять элементы.</span></p>
  </td>
  <td width=447 valign=top style='width:335.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:255.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>В случаях когда необходимо
  сохранить определенный порядок обработки элементов. Например: история
  посещения страниц в веб-браузере, в системах работы с памятью для отображения
  сбодных/занятых блоков памяти, последовательность действий для достижения
  результата(какая-либо инструкция, очередь), протокол </span><span
  style='color:black'>TCP</span><span lang=RU style='color:black'>, задача
  коммивояжера.</span></p>
  </td>
 </tr>
 <tr style='height:330.0pt'>
  <td width=103 nowrap style='width:77.0pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0in 5.4pt 0in 5.4pt;height:330.0pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><span style='font-size:14.0pt;
  color:black'>Queue</span></p>
  </td>
  <td width=309 valign=top style='width:232.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:330.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Реулизует методы интерфейса </span><span
  style='color:black'>Collection</span><span lang=RU style='color:black'>, а
  также 6 дополнительных методов для организации работы с очередью. Методы </span><span
  style='color:black'>add</span><span lang=RU style='color:black'>/</span><span
  style='color:black'>remove</span><span lang=RU style='color:black'>/</span><span
  style='color:black'>element</span><span lang=RU style='color:black'>
  соответственно добавляет/удаляет с возвратом значения/получает элемент без
  удаления возращают </span><span style='color:black'>exception</span><span
  lang=RU style='color:black'> если не удалось выполнить операцию. Методы </span><span
  style='color:black'>offer</span><span lang=RU style='color:black'>/</span><span
  style='color:black'>poll</span><span lang=RU style='color:black'>/</span><span
  style='color:black'>peek</span><span lang=RU style='color:black'> выполняют
  аналогичные действия, однако в случае неудачи возвращают </span><span
  style='color:black'>false</span><span lang=RU style='color:black'> или </span><span
  style='color:black'>null</span><span lang=RU style='color:black'>(в случае
  если очередь ограничена размером рекомендуется использовать последнюю группу
  методов во избежании исключений). </span><span style='color:black'>LinkedList</span><span
  lang=RU style='color:black'> имплиментирует очередь и позволяет добавлять </span><span
  style='color:black'>null</span><span lang=RU style='color:black'> значения,
  что не рекомендуется при использовании очереди, поскольку некоторые методы
  возвращают </span><span style='color:black'>null</span><span lang=RU
  style='color:black'> как ошибку выполнения операции. </span><span
  style='color:black'>PriorityQueue</span><span lang=RU style='color:black'>
  реализует порядок очереди на основе приоритетов, порядок </span><span
  style='color:black'>FIFO</span><span lang=RU style='color:black'> в таком
  случае может не соблюдаться. </span></p>
  </td>
  <td width=447 valign=top style='width:335.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:330.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Очередь выделения процессорных
  ресурсов в зависимости от приоритета, приоритет обработки пакетов (</span><span
  style='color:black'>QoS</span><span lang=RU style='color:black'>), </span><span
  style='color:black'>I</span><span lang=RU style='color:black'>/</span><span
  style='color:black'>O</span><span lang=RU style='color:black'>  </span><span
  style='color:black'>operations</span><span lang=RU style='color:black'>,
  операции </span><span style='color:black'>Undo</span><span lang=RU
  style='color:black'>/</span><span style='color:black'>Forward</span><span
  lang=RU style='color:black'> в редакторах, </span><span style='color:black'>Back</span><span
  lang=RU style='color:black'>/</span><span style='color:black'>Forward</span><span
  lang=RU style='color:black'> в браузерах.</span></p>
  </td>
 </tr>
 <tr style='height:195.0pt'>
  <td width=103 nowrap style='width:77.0pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0in 5.4pt 0in 5.4pt;height:195.0pt'>
  <p class=MsoNormal align=center style='margin-bottom:0in;margin-bottom:.0001pt;
  text-align:center;line-height:normal'><span style='font-size:14.0pt;
  color:black'>Map</span></p>
  </td>
  <td width=309 valign=top style='width:232.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:195.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Не имплиментирует интерфейс </span><span
  style='color:black'>Collection</span><span lang=RU style='color:black'>,
  стоит особняком. Представляет собой некий словарь, т.е. Отображает некоторый
  ключ в значение. Набор ключей уникален, т.е. Ключи повторяться не могут.
  Содержит базовые методы для работы с объектами:</span><span style='color:
  black'>put</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>get</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>containsKey</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>containsValue</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>size</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>isEmpty</span><span lang=RU style='color:black'>.  Методы
  для выполнения массовых операций вставки и удаления - </span><span
  style='color:black'>putAll</span><span lang=RU style='color:black'>/</span><span
  style='color:black'>clear</span><span lang=RU style='color:black'>. А также
  методы позволяющие вернуть объекты </span><span style='color:black'>MAP</span><span
  lang=RU style='color:black'> в виде коллекций: </span><span style='color:
  black'>keySet</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>values</span><span lang=RU style='color:black'>, </span><span
  style='color:black'>entrySet</span><span lang=RU style='color:black'>,
  соответственно все ключи/все значения/все записи вида ключ-значение. </span></p>
  </td>
  <td width=447 valign=top style='width:335.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0in 5.4pt 0in 5.4pt;height:195.0pt'>
  <p class=MsoNormal style='margin-bottom:0in;margin-bottom:.0001pt;line-height:
  normal'><span lang=RU style='color:black'>Для хранения статистики, например
  кол-во посещений страницы, организации кэша данных, таблицы индексации.</span></p>
  </td>
 </tr>
</table>

<p class=MsoNormal><span lang=RU>&nbsp;</span></p>

</div>

</body>

</html>
