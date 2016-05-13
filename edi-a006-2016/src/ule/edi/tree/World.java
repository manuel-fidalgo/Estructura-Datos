package ule.edi.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * En cada nodo se almacena una lista de entidades, cada una con su tipo y
 * cardinalidad. Ver {@link Entity}.
 * 
 * Si se codifica "bajar por la izquierda" como "L" y
 * "bajar por la derecha" como "R", el camino desde un 
 * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
 * cadena de Ls y Rs que indica cómo llegar desde N hasta M.
 *
 * Se define también el camino vacío desde un nodo N hasta
 * él mismo, como cadena vacía.
 * 
 * Por ejemplo, el mundo:
 * 
 * {[F(1)], {[F(1)], {[D(2), P(1)], ∅, ∅}, {[C(1)], ∅, ∅}}, ∅}
 * 
 * o lo que es igual:
 * 
 * [F(1)]
 * |  [F(1)]
 * |  |  [D(2), P(1)]
 * |  |  |  ∅
 * |  |  |  ∅
 * |  |  [C(1)]
 * |  |  |  ∅
 * |  |  |  ∅
 * |  ∅
 * 
 * contiene un bosque (forest) en "", otro en "L", dos dragones y una princesa en "LL" y
 * un castillo en "LR".
 * 
 * @author profesor
 *
 */
public class World extends AbstractBinaryTreeADT<LinkedList<Entity>> {

	/**
	 * Devuelve el mundo al que se llega al avanzar a la izquierda.
	 * 
	 * @return
	 */
	protected World travelLeft() {
		
		return (World) leftSubtree;
	}

	private void setLeft(World left) {
		
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el mundo al que se llega al avanzar a la derecha.
	 * 
	 * @return
	 */
	protected World travelRight() {
		
		return (World) rightSubtree;
	}

	private void setRight(World right) {
		
		this.rightSubtree = right;
	}
	
	private World() {
		
		//	Crea un mundo vacío
		this.content = null;
		
		this.leftSubtree = this.rightSubtree = null;
	}
	
	public static World createEmptyWorld() {
		
		return new World();
	}
	
	/**
	 * Inserta la entidad indicada en este árbol.
	 * 
	 * La inserción se produce en el nodo indicado por la dirección; todos
	 * los nodos recorridos para alcanzar aquél que no estén creados se
	 * inicializarán con una entidad 'bosque'.
	 * 
	 * La dirección se supondrá correcta, compuesta de cero o más Ls y Rs.
	 * 
	 * Dentro de la lista del nodo indicado, la inserción de nuevas entidades
	 * se realizará al final, como último elemento.
	 * 
	 * Por ejemplo, en un árbol vacío se pide insertar un 'dragón' en la
	 * dirección "LL". El resultado final será:
	 * 
     * [F(1)]
     * |  [F(1)]
     * |  |  [D(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  ∅
     * |  ∅
     * 
     * La dirección "" indica la raíz, de forma que insertar un 'guerrero' en
     * "" en el árbol anterior genera:
     * 
     * [F(1), W(1)]
     * |  [F(1)]
     * |  |  [D(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  ∅
     * |  ∅
     * 
     * La inserción tiene en cuenta la cardinalidad, de forma que al volver a
     * insertar un guerrero en "" se tiene:
     * 
     * [F(1), W(2)]
     * |  [F(1)]
     * |  |  [D(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  ∅
     * |  ∅
     *  
	 * @param address dirección donde insertar la entidad.
	 * @param e entidad a insertar.
	 */
	public void insert(String address, Entity e) {
		
		// TODO Implementar el método
	}
	
	/**
	 * Indica cuántos castillos hay a no más de la distancia indicada.
	 * 
	 * Pasar de un nivel del árbol al siguiente supone avanzar una distancia
	 * unitaria.
	 * 
	 * Por ejemplo, en el mundo:
	 * 
     * [C(1)]
     * |  [C(1)]
     * |  |  ∅
     * |  |  ∅
     * |  [D(1)]
     * |  |  [C(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  [C(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * 
     * hay 1 castillo a no más de 0 de distancia, 2 a no más de 1 y 4 a no
     * más de 2.
     * 
	 * @param distance límite de distancia
	 * @return número de castillos a no más de esa distancia.
	 */
	public long countCastlesCloserThan(long distance) {
	
		// TODO Implementar el método
		return 0;
	}
	
	/**
	 * Indica cuántas entidades del tipo dado hay en un nivel.
	 * 
	 * @param type tipo de entidad.
	 * @param n nivel a considerar.
	 * @return cuántas entidades de ese tipo hay en ese nivel.
	 */
	public long countAtLevel(int type, int n) {
		
		// TODO Implementar el método	
		return 0;
	}
			
	/**
	 * Localiza la n-ésima princesa en in-orden.
	 * 
	 * Por ejemplo, en este mundo:
	 * 
     * [F(1)]
     * |  [F(1)]
     * |  |  [D(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  [C(1)]
     * |  |  |  [P(1)]
     * |  |  |  |  ∅
     * |  |  |  |  [P(2)]
     * |  |  |  |  |  ∅
     * |  |  |  |  |  ∅
     * |  |  |  ∅
     * |  ∅
     * 
     * la primera princesa está en 
     * 
     * 	[L, R, L]
     * 
     * y la segunda y tercera están ambas en
     * 
     * 	[L, R, L, R]
     * 
	 * @param n posición relativa entre las princesas en in-orden, n >= 1
	 * @param rx camino del nodo que contiene a la princesa encontrada.
	 * @return <code>true</code> si la encontró.
	 */
	public boolean findNPrincessInorden(long n, LinkedList<Character> rx) {
		
		// TODO Implementar el método
		return false;
	}
		
	/**
	 * Busca el primer dragón en anchura y devuelve cuántos nodos hay antes.
	 * 
	 * Los nodos vacíos no se cuentan. Por ejemplo, aquí devolvería 2:
	 * 
     * [F(1)]
     * |  [F(1)]
     * |  |  [D(1)]
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  [C(1)]
     * |  |  |  [P(1)]
     * |  |  |  |  ∅
     * |  |  |  |  [P(2)]
     * |  |  |  |  |  ∅
     * |  |  |  |  |  ∅
     * |  |  |  ∅
     * |  ∅
     * 
	 * Si no hubiera ningún dragón, devolverá el número de nodos no vacíos
	 * en el mundo.
	 * 
	 * @return el número de nodos no vacíos que ha recorrido antes del dragón.
	 */
	public long findFirstDragonInBreadthOrder() {
		
		//	TODO Implementar el método		
		return 0;
	}

}
