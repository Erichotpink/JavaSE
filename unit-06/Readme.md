## Unit-06 - Generic and Collections.

##### **Task01. Collection classes**

##### 

Изучите классы реализации коллекций и заполните следующую таблицу

              Ordering   Random Access   Key-Value Pairs   Allows Duplicates   Allows Null Values   Thread Safe   Blocking Operations
  ----------- ---------- --------------- ----------------- ------------------- -------------------- ------------- ---------------------
  ArrayList   Yes/No     …                                                                                        
  …                                                                                                               

##### **Task02. Using Map collection**

Создать “универсальный” класс, позволяющий получить значение из любого
properties-файла. Физическое чтение файла должно происходить только один
раз. Результаты чтения храните в коллекции типа Map. Ответьте на вопрос:
как ведет себя map-коллекция если в нее добавить элемент с ключом,
который уже присутствует?

##### **Task03. References to collections**

Определена иерархия классов

**class** MedicalStaff{}

**class** Doctor **extends** MedicalStaff{}

**class** Nurse **extends** MedicalStaff{}

**class** HeadDoctor **extends** Doctor{}

Укажите корректные и некорректные операторы. Дайте ответу пояснение.

                                                                 correct   not correct
  -------------------------------------------------------------- --------- -------------
  Doctor doctor1 = **new** Doctor();                                       
  Doctor doctor2 = **new** MedicalStaff();                                 
  Doctor doctor3 = **new** HeadDoctor();                                   
  Object object1 = **new** HeadDoctor();                                   
  HeadDoctor doctor5 = **new** Object();                                   
  Doctor doctor6 = **new** Nurse();                                        
  Nurse nurse = **new** Doctor();                                          
  Object object2 = **new** Nurse();                                        
                                                                           
  List&lt;Doctor&gt; list1= **new** ArrayList&lt;Doctor&gt;();             

  List&lt;MedicalStaff&gt; list2 = **new** ArrayList&lt;Doctor&gt;();      
  --------------------------------------------------------------------- -- --
  List&lt;Doctor&gt; list3 = **new** ArrayList&lt;MedicalStaff&gt;();      
  List&lt;Object&gt; list4 = **new** ArrayList&lt;Doctor&gt;();            
  List&lt;Object&gt; list5 = **new** ArrayList&lt;Object&gt;();            

##### **Task04. Collections usage**

Заполните таблицу.

          Основная функциональность   Примеры типичного использования
  ------- --------------------------- ---------------------------------
  Set                                 
  List                                
  Queue                               
  Map                                 


