## Robot Block World

Una vez tuve una entrevista donde me pidieron hacer un ejercicio en vivo junto a dos ingenieros que iban a evaluar mi desempeño. 

Bueno, los nervios me ganaron y fallé estrepitosamente, pero esto no tiene porque pasarte a vos :)

Mientras volvía a casa se me ocurrió una forma interesante de resolver el ejercicio, así que me tomé un momento para escribirlo y compartirlo.

## Ejercicio

Dada una cantidad fija de cajas numeradas de 1 a n, cada una en su correspondiente fila, se solicita mediante instrucciones de comando del tipo "move A to B" apilarlas, siendo A y B números de cajas.

Por ejemplo, si tenemos 5 cajas (y por tanto 5 filas), y la primer instrucción es "move 1 to 2", la caja 1 quedaría encima de la caja 2 en la segunda fila.

El programa termina cuando se ingresa el comando "quit", y debe mostrar por salida estandar cada fila con los números de las cajas apiladas, y en caso que una fila no tenga cajas, muestra un vacío.

El ejemplo anterior debería mostrar:
> 1.  
> 2. 2 1
> 3. 3
> 4. 4
> 5. 5

Si la caja a mover tiene cajas apiladas, supongamos mover la caja 2 del ejemplo anterior, la caja 1 vuelve a su posición inicial.

En caso de ingresar un comando no permitido u erróneo se saltea la instrucción.

No se posible mover una caja sobre otra que tiene cajas apiladas.

No es posible mover una caja sobre otra en su misma fila.

No es posible mover una caja sobre si misma.

## Solución

Como aprendimos en la facultad la idea es descomponer el problema en partes. 

Primero validamos la entrada, o sea, la cantidad de bloques.

Segundo inicializamos el array con los bloques. 

Tercero comenzamos a leer los comandos de movimientos, uno a uno.

Cuarto para cada movimiento, evaluamos si es correcto, y si es correcto lo ejecutamos.

Quinto continuamos con los dos puntos anteriores hasta leer el comando de fin.

Último mostramos el resultado por la salida estandar.

## Requerimientos
1. Java JDK 8
2. Maven 3

## Frameworks
1. Junit 4

## Funcionamiento

Cuando empieza el programa se carga un array con los bloques de números en la posición inicial.

Los bloques dentro del array no se mueven, por tanto se los accede por posición.

Cada bloque tiene una referencia a un padre y a un hijo, inicialmente vacía. Al ir ejecutando las instrucciones de movimiento se asignan estas referencias convenientemente.

Al mover un bloque con hijos, se limpian las referencias padre e hijo de ellos, para regresarlos a su estado inicial.
