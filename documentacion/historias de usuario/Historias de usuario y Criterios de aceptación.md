# Historias de Usuario y Criterios de aceptación

* Como jugador, quiero crear mi personaje, editando sus características principales (como raza y casta) para verme reflejado en mi avatar.
  * Dado un **Personaje**, cuando el **Personaje** escoja una **Casta** entonces podrá escoger una de las siguientes razas: **Humano**, **Holograma** y **BOT**.

* Como Personaje, quiero poder escoger una de las siguientes razas: Humano, Holograma y BOT, para poder tener un estilo de pelea particular.
  * Dado un **Humano**, cuando el **Humano** escoja una **Casta** entonces podrá escoger una de las siguientes castas: **Programador**, **Tester** y **Soporte**.
  * Dado un **Holograma**, cuando el **Humano** escoja una **Casta** entonces podrá escoger una de las siguientes castas: **Programador**, **Tester** y **Soporte**.
  * Dado un **BOT**, cuando el **Humano** escoja una **Casta** entonces podrá escoger una de las siguientes castas: **Programador**, **Tester** y **Soporte**.
  * Dado una **Raza**, cuando escoja una **Casta** entonces variarán en cada una las siguientes habilidades: **Fuerza**, **Energía** e **Ingenio**.
  * Dado una **Raza**, cuando escoja una **Casta** entonces poserá skills que me distingan de las otras **Castas**.

* Como Jugador, quiero poder tener una cuenta para que mi Nickname sea único y me distinga de los demás jugadores.
  * Dado un **Jugador**, cuando el **Jugador** escoja un Nickname debe ser unívoco entonces este no deberá estar ocupado por ningún otro **Jugador**.
  * Dado un **Jugador**, cuando el **Jugador** ingresa a su cuenta entonces debe ingresar correctamente la contraseña.

* Como jugador, quiero ingresar a un mundo para adquirir experiencia, items, y habilidades nuevas.
  * Dado un **Personaje**, cuando el **Personaje** intenta caminar hacia una zona fuera del mapa entonces los límites del mapa no se lo permitirán.
  * Dado un **Personaje**, cuando el **Personaje** camina por el mapa entonces no podrá atravesar obstaculos ni otros Jugadores.
  * Dado un **Mundo**, cuando el **Mundo** tenga 20 **Jugadores** en él entonces el mismo estará lleno.
  * Dado un **Personaje**, cuando el **Personaje** elija un mundo entonces el mismo no tendrá que estar lleno.

* Como jugador, quiero eliminar enemigos para aumentar mi experiencia.
  * Dado un **Personaje** y un **NPC**, cuando el **Personaje** elimina al **NPC** entonces aumenta el nivel de experiencia del **Personaje**:
  * Dado un **Personaje** y un **Personaje Enemigo**, cuando el **Personaje** elimina al **Personaje Enemigo** entonces aumenta el nivel de experiencia del **Personaje**.
 * Dado un **Personaje**, cuando el **Personaje** no tiene mas energia entonces no puede atacar.
  * Dado un **Personaje** y un **Personaje Enemigo** cuando el **Personaje** elimina al **Jugador Enemigo** entonces el **Jugador Enemigo** no pierde experiencia.
  * Dado un **Personaje** y un **Personaje Enemigo** cuando el **Personaje** elimina al **Jugador Enemigo** entonces el **Jugador Enemigo** droppea su mejor item.
  * Dado un **Personaje** y un **Personaje Enemigo** cuando el **Personaje** elimina al **Jugador Enemigo** entonces el **Jugador Enemigo** revive en el instante.

* Como jugador, quiero acumular experiencia para poder subir de nivel.
  * Dado un **Personaje**, cuando obtenga una cierta cantidad de experiencia entonces subirá de nivel.
  * Dado un **Nivel de experiencia**, cuando el **Nivel de experiencia** sea mayor entonces requerirá mas cantidad de experiencia para subir de nivel.
  * Dado un **Nivel de experiencia**, cuando el **Nivel de experiencia** llegue al nivel máximo entonces este será nivel 32.
  
* Como jugador, quiero subir de nivel para poder asignar puntos adicionales a mis habilidades.
  * Dado un **Personaje**, cuando el **Personaje** suba de nivel entonces podrá distribuir sus puntos de habilidades en: **Fuerza**, **Energía** e **Ingenio**.
  * Dado un **Personaje**, cuando el **Personaje** distribuya sus puntos de habilidad entonces no podrá volver a distribuirlos.
  
* Como jugador, quiero aumentar mis habilidades para poder manipular ítems de manera más eficiente.
  * Dado un **Personaje**, cuando el **Personaje** obtenga un **item**, entonces necesita una cantidad mínima de puntos de habilidad para equiparlo.
 
* Como jugador, quiero equipar items para poder potenciar mis habilidades.
  * Dado un **Personaje**, cuando el **Personaje** obtenga un **item**, entonces el mismo incrementará o decrementará los **Puntos de Defensa** o **Puntos de Ataque** del **Personaje**.
  * Dado un **Personaje**, cuando el **Personaje** equipa un **item** entonces el mismo no podrá ser desequipado.
  * Dado un **Personaje**, cuando el **Personaje** recoja un **item** entonces el mismo se equipará sin importar la cantidad de **items** que ya tenga equipados.

* Como jugador, quiero disponer de habilidades de Fuerza, Energía o Ingenio para afectar a mis puntos de ataque y defensa.
  * Dado un **Personaje**, cuando el **Personaje** aumenta/decrementa su Fuerza, entonces aumenta o decrementa sus **Puntos de Defensa**.
  * Dado un **Personaje**, cuando el **Personaje** aumenta/decrementa su Ingenio, entonces aumenta o decrementa su **Puntos de Ataque**.
  * Dado un **Personaje**, cuando el **Personaje** aumenta/decrementa su Energía, entonces aumenta o decrementa su **Puntos de Ataque**.

* Como jugador, quiero encontrarme con otros jugadores en el mismo mundo para aliarse a ellos o combatir contra ellos.
  * Dado un **Personaje** y una **Raza**, cuando el **Personaje** elija su **Raza** entonces esta determinará a que Alianza pertenece el **Personaje**.
  * Dado dos **Personajes**, cuando los dos pertenezcan a la misma **Alianza** entonces no podrán atacarse entre ellos.
  * Dado dos **Personaje**, cuando los dos pertenezcan a distintas **Alianzas** entonces podrán atacarse entre ellos.
  * Dado un **Personaje** y un **Personaje Enemigo**, cuando comience un combate entonces todos los **Personajes** cercanos a ellos que pertenezcan a esas dos **Alianzas** entrarán en el combate.
  * Dado un **Personaje** y un **Personaje Enemigo**, cuando estén en combate entonces cualquiera de los dos podrá huir pero tendrá una penalización de salud.
  
* Como jugador, quiero aliarme con otro jugador para combatir junto a él y aumentar la experiencia que recolectamos en ese tiempo.
  * Dado un **Personaje** y un **NPC**, cuando el **Personaje** está con un compañero de la **Alianza** y elimina al **NPC**, entonces obtendrá un bonus de experiencia.
  * Dado un **Personaje** y un **Jugador Enemigo**, cuando el **Personaje** está con un compañero de la **Alianza** y elimina al **Jugador Enemigo**, entonces obtendrá un bonus de experiencia.
  
* Como jugador, quiero combatir contra otros jugadores para obtener sus ítems al derrotarlos.
  * Dado una **Alianza** y **Jugadores Enemigos**, cuando la **Alianza gana la batalla, entonces cada **Jugador Enemigo** droppea la misma cantidad de items que de **Jugadores** sobrevivientes del combate.

* Como jugador, quiero cambiar las alianzas establecidas cada cierta cantidad de tiempo para poder traicionar a mis aliados
  * Dado un **Personaje**, cuando haya pasado un tiempo mínimo de 5 minutos de estar en una **Alianza** entonces se me permite cambiar la misma.

