package ule.edi.ring;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Almacena datos en una estructura doblemente enlazada circular.
 * 
 * Un anillo siempre tiene un nodo fijo de 'referencia' (nodo cabecera)
 * que tiene a <code>null</code> su contenido. Un anillo vacío será un
 * nodo de referencia (R) que se apunta a sí mismo como 'next' y
 * como 'previous', para cerrar el anillo.
 * 
 * 	> (R) <> (R) <
 * 
 * Cuando se añaden elementos, se hace en nuevos nodos que
 * deben mantener la estructura circular cerrada; por ejemplo tras
 * insertar A, B y C se tendría:
 * 
 * 	> (R) <> A <> B <> C <> (R) <
 * 
	 * Se define el sentido de las agujas del reloj como el indicado
	 * por las referencias 'next' en los nodos:
	 *  
	 *   (R)  A  B  C  (R)
	 *   
	 * y el sentido contrario el dado por las referencias 'previous':
	 * 
	 *   (R)  C  B  A  (R)
 *   
 * @author profesor
 *
 * @param <T> tipo de dato en cada posición del anillo
 */
public class Ring<T> implements Iterable<T> {

	/**
	 * Nodo doblemente enlazado para almacenar un dato.
	 * 
	 * @author profesor
	 *
	 * @param <G> tipo de dato almacenado en este nodo.
	 */
	protected static class Node<G> {
		
		public Node(G content) {
			
			this.content = content;
			
			this.previous = this.next = null;
		}
		
		G content;
		
		Node<G> next;
		
		Node<G> previous;

		@Override
		public String toString() {
			
			return "(" + (content != null ? content : "null") + ")"; 
		}
		
	}
	
	//	Cada anillo tiene un nodo vacío fijo como referencia
	//
	private final Node<T> reference = new Node<T>(null);
	
	//	Las clases que heredan de ésta pueden consultar la referencia
	//	pero no cambiarla.
	//
	protected Node<T> reference() {
		
		return reference;
	}
	
	//	Número de elementos en el anillo
	//
	protected int nElements;
	
	/**
	 * Dirección en sentido horario en el anillo.
	 */
	protected static final int FORWARD = 0;
	
	/**
	 * Dirección en sentido contrario al sentido horario.
	 */
	protected static final int BACKWARDS = 1;

	
	protected Ring() {
		
		// TODO dejar este anillo como vacío
	}
	
	/**
	 * Indica el número de elementos en este anillo. (Si el anillo está vacío el tamaño es 0)
	 * 
	 * @return cuántos elementos hay en este anillo.
	 */
	public int size() {
		
		// TODO implementar el método
		return 0;
	}
	
	/**
	 * Busca el primer nodo con el contenido dado, a partir de otro nodo.
	 * 
	 * A partir del siguiente nodo al dado, en la dirección indicada, busca
	 * un nodo cuyo contenido sea igual (Object#equals) al dado. Si no lo
	 * encuentra con una vuelta completa al anillo, devolverá el nodo inicial.
	 * 
	 * Por ejemplo, con un anillo 'x' como el siguiente:
	 * 
	 * 	> (R) <> A <> B <> C <> D <> (R) <
	 * 
	 * se tendría que
	 * 
	 * 	z = x.find(Ring.FORWARD, x.reference(), B)
	 * 
	 * devuelve el nodo que contiene B, y a continuación
	 * 
	 *  x.find(Ring.FORWARD, z, B)
	 * 
	 * devuelve z ya que tras visitar C, D, A vuelve a z sin encontrar otra B.
	 * 
	 * @param direction en qué dirección buscar.
	 * @param start nodo que marca el inicio de la búsqueda, pero no se incluye en ella.
	 * @param target contenido a buscar.
	 * @return un nodo encontrado, o el inicio 'start' si no se encuentra el dato.
	 */
	protected Node<T> find(int direction, Node<T> start, T target) {
	
		// TODO implementar el método
		return start;
	}

	/**
	 * Indica si el anillo es vacío.
	 * 
	 * @return está este anillo vacío? 
	 */
	public boolean isEmpty() {
		
		// TODO implementar el método
		return true;
	}
	
	/**
	 * Elimina el nodo dado de este anillo.
	 * 
	 * Si el nodo indicado fuera el nodo cabecera, no hace nada.
	 * 
	 * @param n un nodo en este anillo.
	 */
	protected void remove(Node<T> n) {
		
		// TODO implementar el método
	}
	
	/**
	 * Inserta un nuevo nodo en el anillo.
	 * 
	 * La inserción se realiza al lado del nodo base indicado,
	 * en la dirección dada. Por ejemplo:
	 * 
	 * 	x = > (R) <> (R) <
	 * 	x.insert(x.reference(), Ring.FORWARD, A)
	 *
	 * 	x = > (R) <> A <> (R) <
	 *  x.insert(x.reference(), Ring.FORWARD, B)
	 *  
	 *  x = > (R) <> B <> A <> (R) <
	 *  x.insert(x.reference(), Ring.BACKWARDS, C)
	 *  
	 *  x = > (R) <> B <> A <> C <> (R) <
	 *  x.insert(x.find(Ring.FORWARD, x.reference(), A), Ring.BACKWARDS, D)
	 *  
	 *  x = > (R) <> B <> D <> A <> C <> (R) <
	 *  
	 * @param base nodo referencia para la inserción
	 * @param direction en qué dirección insertar
	 * @param element dato para el nuevo nodo
	 */
	protected void insert(Node<T> base, int direction, T element) {
		
		// TODO implementar el método
	}
	
	@Override
	public String toString() {
		
		Node<T> i;
		
		StringBuffer rx = new StringBuffer();
		
		rx.append("> (R) <> ");
		
		i = reference.next; while (i != reference) {
			
			rx.append(i.content);
			rx.append(" <> ");
			
			i = i.next;
		}
		
		rx.append("(R) <");
		
		return rx.toString();
	}
	
	/**
	 * Genera una cadena con el contenido de cada elemento.
	 * 
	 * Se genera al concatenar el resultado de invocar {@link Object#toString()}
	 * en cada elemento, en un recorrido del anillo en la dirección dada.
	 *  
	 * Por ejemplo, si x es un anillo con T String:
	 * 
	 *  x = > (R) <> B <> D <> A <> C <> (R) <
	 * 
	 * se tiene que
	 * 
	 *  x.toSequence(Ring.FORWARD) es "BDAC"
	 *  
	 * y
	 * 
	 *  x.toSequence(Ring.BACKWARDS) es "CADB"
	 *  
	 * @return cadena con el contenido del anillo.
	 */
	public String toSequence(int direction) {

		// TODO implementar el método
		return "";
	}
	
	/**
	 * Iterador sobre los elementos del anillo.
	 * 
	 * En el constructor se indica en qué sentido se desea
	 * recorrer el anillo.
	 * 
	 * @author profesor
	 *
	 */
	private class IteratorImpl implements Iterator<T> {

		public IteratorImpl(int direction) {
			
			// TODO implementar el método
		}
		
		@Override
		public boolean hasNext() {
			
			// TODO implementar el método
			return false;
		}

		@Override
		public T next() {
			
			// TODO implementar el método
			return null;
		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		
		return new IteratorImpl(Ring.FORWARD);
	}

	public Iterator<T> forwardIterator() {
		
		return new IteratorImpl(Ring.FORWARD);
	}

	public Iterator<T> backwardsIterator() {
		
		return new IteratorImpl(Ring.BACKWARDS);
	}

	/**
	 * Compara anillos, elemento a elemento.
	 * 
	 * Serán iguales si contienen los mismos elementos y en el mismo
	 * orden. Las comparaciones entre elementos serán con
	 * {@link Object#equals(Object)}.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj instanceof Ring<?>) {
			
			Ring<?> other = (Ring<?>) obj;
			
			// TODO comprobar si son iguales
			return false;
		}
				
		return false;
	}
	
}
