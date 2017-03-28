| Class | Ordering | Random Access | Key-Value Pairs | Allows Duplicates | Allows Null Values | Thread Safe | Blocking Operations |
| --- | --- | --- | --- | --- | --- | --- | --- |
|   |   |   |   |   |   |   |   |
| **Old** |   |   |   |   |   |   |   |
| Vector | Yes (insertion order) | Yes | No | Yes | Yes | Yes | Yes |
| Hashtable | No | No | Yes | No | Yes | Yes | Yes |
|   |   |   |   |   |   |   |   |
| **Deque** |   |   |   |   |   |   |   |
| LinkedBlockingDeque | Yes (insertion order) | No | No | Yes | No | Yes | Yes |
| ArrayDeque | Yes (insertion order) | No | No | Yes | No | No | No |
|   |   |   |   |   |   |   |   |
| **Queue** |   |   |   |   |   |   |   |
| PriorityQueue | Yes (natural ordering, comparator) | No | No | Yes | No | No | No |
| LinkedBlockingQueue | Yes (insertion order) | No | No | Yes | No | Yes | Yes (put, take, offer(timeout), poll(timeout)) |
| ArrayBlockingQueue | Yes (insertion order) | No | No | Yes | No | Yes | Yes (put, take, offer(timeout), poll(timeout)) |
| PriorityBlockingQueue | Yes (natural ordering, comparator) | No | No | Yes | No | Yes | Yes (put, take, offer(timeout), poll(timeout)) |
| DelayQueue | No | No | No | Yes | No | Yes | Yes (put, take, offer(timeout), poll(timeout)) |
| SynchronousQueue | No | No | No | No | No | Yes | Yes (put, take, offer(timeout), poll(timeout)) |
| LinkedTransferQueue | No | No | No | Yes | No | Yes | Yes (put, take, offer(timeout), poll(timeout), transfer, trytransfer(timeout)) |
|   |   |   |   |   |   |   |   |
| **Map** |   |   |   |   |   |   |   |
| HashMap | No | No | Yes | No (Except values) | Yes | No | No |
| TreeMap | Yes (natural ordering, comparator) | No | Yes | No (Except values) | No (Yes, comparator depends) | No | No |
| LinkedHashMap | Yes (insertion order, access-order) | No | Yes | No (Except values) | Yes | No | No |
| EnumMap | Yes (natural ordering) | No | Yes | No | No | No | No |
| WeakHashMap | No | No | Yes | No | Yes | No | No |
| ConcurrentHashMap | No | No | Yes | No | No | Yes | No |
| IdentityHashMap | No | No | Yes | No (Except values) | Yes | No | No |
|   |   |   |   |   |   |   |   |
| **List** |   |   |   |   |   |   |   |
| ArrayList | No | Yes | No | Yes | Yes | No | No |
| LinkedList | No | No | No | Yes | Yes | No | No |
| CopyOnWriteArrayList | No | Yes | No | Yes | Yes | Yes | Yes |
|   |   |   |   |   |   |   |   |
| **Set** |   |   |   |   |   |   |   |
| HashSet | No | No | No | No | Yes | No | No |
| TreeSet | Yes (natural ordering, comparator) | No | No | No | No (Yes, comparator depends) | No | No |
| LinkedHashSet | Yes (insertion order) | No | No | No | Yes | No | No |
| EnumSet | Yes (natural ordering) | No | No | No | No | No | No |
| CopyOnWriteArraySet | No | No | No | No | Yes | Yes | Yes |