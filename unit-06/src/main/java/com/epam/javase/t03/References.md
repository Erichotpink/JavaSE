| **Expression** | **Correctness** | **Explanation** |
| --- | --- | --- |
| Doctor doctor1 = **new** Doctor(); | TRUE | Object type equals to the reference type. |
| Doctor doctor2 = **new** MedicalStaff(); | FALSE | Doctor class extends MedicalStaff class. Reference  variable can&#39;t refer to objects with superior type. |
| Doctor doctor3 = **new** HeadDoctor(); | TRUE | Reference type is superior than object&#39;s type. |
| Object object1 = **new** HeadDoctor(); | TRUE | In Java each class extends Object class. |
| HeadDoctor doctor5 = **new** Object(); | FALSE | Reference variable can&#39;t refer to objects with superior type. |
| Doctor doctor6 = **new** Nurse(); | FALSE | Nurse class doesn&#39;t extend Doctor class. |
| Nurse nurse = **new** Doctor(); | FALSE | Nurse class doesn&#39;t inherit Doctor class. |
| Object object2 = **new** Nurse(); | TRUE | In Java each class extends Object class. |
| List&lt;Doctor&gt; list1= **new** ArrayList&lt;Doctor&gt;(); | TRUE | Reference type equals to the object type. |
| List&lt;MedicalStaff&gt; list2 = **new** ArrayList&lt;Doctor&gt;(); | FALSE | List is parameterized interface with a concrete type, thus the reference type and object type must be equal. |
| List&lt;Doctor&gt; list3 = **new** ArrayList&lt;MedicalStaff&gt;(); | FALSE | List is parameterized interface with a concrete type, thus the reference type and object type must be equal. |
| List&lt;Object&gt; list4 = **new** ArrayList&lt;Doctor&gt;(); | FALSE | List is parameterized interface with a concrete type, thus the reference type and object type must be equal. |
| List&lt;Object&gt; list5 = **new** ArrayList&lt;Object&gt;(); | TRUE | Reference type equals to the object type. |