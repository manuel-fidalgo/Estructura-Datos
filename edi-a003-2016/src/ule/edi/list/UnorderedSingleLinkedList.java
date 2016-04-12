package ule.edi.list;


import java.util.Iterator;

import ule.edi.EmptyCollectionException;

public class UnorderedSingleLinkedList<T> implements UnorderedListADT<T> {

	public final static String CLASSNAME = "UnorderedSingleLinkedList<T>";

	//Primer nodo de la lista
	protected Node<T> first = null;
	protected Node<T> last = null; //Solo se usara en caso de que sea necesario
	// Clase para cada nodo en la lista
	//
	// Es estática y genérica, un 'nodo con elementos de tipo G'.
	//
	// Arriba se usa para implementar una lista de elementos
	// de tipo T.
	//
	protected static class Node<G> {

		Node(G element) {
			this.element = element;
			this.next = null;
		}

		@Override
		public String toString() {
			return "(" + element + ")";
		}
		public Node<G> getNext(){
			return this.next;
		}

		G element;

		Node<G> next;
	}

	public UnorderedSingleLinkedList() {
		// Vacía
	}

	@SuppressWarnings("unchecked")
	public UnorderedSingleLinkedList(T... v) {
		// Añadir en el mismo orden que en 'v'
		for (T Vi : v) {
			addToRear(Vi);
		}
	}

	public Node<T> getFirts(){
		return this.first;
	}
	@Override
	public void addToFront(T element) {
		if(this.first==null){
			this.first= new Node<T>(element);
		}
		else{
			Node<T> aux = first.next;
			first = new Node<T>(element);
			first.next = aux;
		}

	}

	@Override
	public void addToRear(T element) {
		Node<T> current = first;

		while(current.next!=null){
			current = current.next;
		}
		//current ultimo de la lista, su next es null
		current.next= new Node<T>(element);
	}


	@Override
	public T removeFirst() throws EmptyCollectionException {
		if(first==null) throw new EmptyCollectionException(CLASSNAME);
		Node<T> aux = first;	//Almacenamo el primero en una varaible auxiliar
		first = first.next;		// Hacemos first apunte al segundo
		return aux.element;		//El colector de basura de java se encargara de eliminar el noto sin referencia
	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		if(first==null) throw new EmptyCollectionException(CLASSNAME);

		Node<T> current = first;
		while(current.next!=null){
			current = current.next;
		}
		//current ultimo de la lista, su next es null
		return null;
	}
	/**
	 * @param element.
	 * @return El elemento borrado
	 * @throws EmptyCollectionException
	 * @throws NoElementException ?
	 */
	@Override
	public T remove(T element) throws EmptyCollectionException {
		if(first==null) throw new EmptyCollectionException(CLASSNAME);

		Node<T> current = first;
		Node<T> aux = null;
		//Avanzamos punteros hasta que lleguemos a que si next es el valor a remover
		while(!current.next.element.equals(element)){
			current = current.next;
		}
		//El valor del next del current es el elemento a quitar entonces
		aux = current.next;
		current.next = aux.next;
		return aux.element;
	}


	@Override
	public T getFirst() throws EmptyCollectionException {
		if(first==null) throw new EmptyCollectionException(CLASSNAME);
		return first.element;
	}

	@Override
	public T getLast() throws EmptyCollectionException {
		if(first==null) throw new EmptyCollectionException(CLASSNAME);

		Node<T> current = first;
		while(current.next!=null){
			current = current.next;
		}
		return current.element;
	}


	@Override
	public boolean contains(T target) {
		if (isEmpty()) return false;

		Node<T> current = first;
		while(current.next!=null){
			if(current.element.equals(target)) return true;
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(first==null)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		if(isEmpty()) return 0;
		int count = 1;
		Node<T> current = first;
		while (current.next!=null) {
			count++;
		}
		return count;
	}


	@Override
	public T getElementAt(int i) throws IndexOutOfBoundsException {
		if(i<1||i>size()) throw new IndexOutOfBoundsException();
		int currentpos=1;
		Node<T> current = first;
		while(current != null){
			if(currentpos==i) return current.element;
			current = current.next;
			currentpos++;
		}
		return null;
	}
	/*Devuelve el nodo como que esta en la posicion que se le pasa como parametro*/
	private Node<T> getNodeAt(int i) throws IndexOutOfBoundsException {
		if(i<1||i>size()) throw new IndexOutOfBoundsException();
		int currentpos=1;
		Node<T> current = first;
		while(current != null){
			if(currentpos==i) return current;
			current = current.next;
			currentpos++;
		}
		return null;
	}

	@Override
	public T removeElementAt(int i) throws IndexOutOfBoundsException {
		if(i<1||i>size()) throw new IndexOutOfBoundsException();
		int currentpos=1;
		Node<T> current = first;
		//Cuidado que el elemento a remover sea el ultimo
		while(current != null){
			if(currentpos==i){
				//current pos elemento a devolver y eliminar;
				Node<T> aux = current;
				getNodeAt(i-1).next = current.next;
				return aux.element; //El colector de basura se encargara de recoger el elementos sin referencia
			}
			current = current.next;
			currentpos++;
		}
		return null;
	}

	@Override
	public T replaceElementAt(int n, T element) {
		if(n<1||n>size()) throw new IndexOutOfBoundsException();
		int currentpos=1;
		Node<T> current = first;
		while(current != null){
			if(currentpos==n){
				T aux = current.element;
				current.element = element;
				return aux;
			}
			current = current.next;
			currentpos++;
		}
		return null;
	}


	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Clase para el iterador.
	 * 
	 * Como clase interior (anidada no estática) tiene acceso a los atributos
	 * de la clase que la contiene. También al parámetro 'T' del tipo genérico.
	 * 
	 * Si fuera anidada y estática, no tendría acceso.
	 * 
	 * @author profesor
	 */	
	private class DefaultIteratorImpl implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// Según el contrato de {@link java.util.Iterator}
			throw new UnsupportedOperationException();
		}
	}

	private class SkippingIteratorImpl implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			//	Según el contrato de {@link java.util.Iterator} 
			throw new UnsupportedOperationException();
		}
	};

	@Override
	public Iterator<T> skippingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	private class RangedIteratorImpl implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {			
			throw new UnsupportedOperationException();
		}

	};

	@Override
	public Iterator<T> rangedIterator(int from, int to, int step) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Elimina duplicados y devuelve el resultado como otra lista.
	 * 
	 * Si T1 es vacía, devuelve una lista vacía.
	 * 
	 * Únicamente se dispone de las operaciones del TAD y del iterador por defecto.
	 * 
	 * Por ejemplo, con una lista x de números [1, 2, 3, 1, 2, 3, 4, 5, 3] se tendría:
	 * 
	 * 	UnorderedSingleLinkedList.distinct(x) es [1, 2, 3, 4, 5]
	 * 
	 * @param T1 una lista de elementos
	 * @return una lista con los elementos de T1 sin duplicados
	 */
	public static <E> UnorderedListADT<E> distinct(UnorderedListADT<E> T1) {
		// TODO Auto-generated method stub		
		return null;
	}

	/**
	 * Devuelve una lista en orden inverso.
	 * 
	 * Únicamente se dispone de las operaciones del TAD y del iterador por defecto.
	 * 
	 * Si T1 es vacía, devuelve una lista vacía.
	 * 
	 * Por ejemplo, sea x una lista de caracteres ['A', 'B', 'C', 'C', 'D'], se tiene:
	 * 
	 *  UnorderedSingleLinkedList.reverse(x) es ['D', 'C', 'C', 'B', 'A']
	 *  
	 * @param T1 una lista de elementos. 
	 * @return otra lista con los mismos elementos en orden inverso.
	 */
	public static <E> UnorderedListADT<E> reverse(UnorderedListADT<E> T1) {
		//Vamos a darle al vuelta a la lista,
		//lksdvnaslbvasv
		return null;
	}

	/**
	 * Construye y devuelve una lista con los elementos dados por un iterador.
	 * 
	 * Por ejemplo, si el iterador devuelve sucesivamente los caracteres
	 * 'a', 'z', 'b', 'y' al invocar a next(), la lista resultado sería
	 * 
	 * 	['a', 'z', 'b', 'y']
	 * 
	 * @param contents iterador de los elementos a añadir en la nueva lista.
	 * @return una lista con los elementos que va entregando el iterador.
	 */
	public static <E> UnorderedListADT<E> listWith(Iterator<E> contents) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {

		// Construye y devuelve con el formato adecuado
		StringBuffer rx = new StringBuffer();

		rx.append("[");

		for (T i : this) {
			rx.append(i);
			rx.append(", ");
		}
		// Elimina ", " de más
		if (!isEmpty()) {
			rx.delete(rx.length() - 2, rx.length());
		}

		rx.append("]");

		return rx.toString();
	}


}
