package ule.edi.list;


import java.util.Iterator;
import java.util.NoSuchElementException;

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
		public Node<G> getTwoNext(){
			return this.next.next;
		}

		G element;

		Node<G> next;
	}

	public UnorderedSingleLinkedList() {
		//this.first = null;
	}

	@SuppressWarnings("unchecked")
	public UnorderedSingleLinkedList(T... v) {
		for (T Vi : v) {
			addToRear(Vi);
		}
	}
	/*
	public Node<T> getFirts(){
		return this.first;
	}
	*/

	@Override
	public void addToFront(T element) {
		if(this.first==null){
			this.first= new Node<T>(element);
		}
		else{
			Node<T> aux = first;
			first = new Node<T>(element);
			first.next = aux;
		}

	}

	@Override
	public void addToRear(T element) {
		if(first==null){
			first = new Node<T>(element);
		}else{
			Node<T> current = first;
			while(current.next != null){
				current = current.next;
			}
			//Current ultimo de la lista, su next es null
			current.next= new Node<T>(element);
		}
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
		//if(first==null) throw new EmptyCollectionException(CLASSNAME);
		if(this.size()==0) throw new EmptyCollectionException(CLASSNAME);
		
		if(this.size()==1){
			Node<T> aux = first;
			this.first = null;
			return aux.element;
		}

		Node<T> current = first;
		while(current.getTwoNext()!=null){
			current = current.next;
		}
		Node<T> aux = current.next;
		current.next = null;
		return aux.element;
	}
	/**
	 * @param element.
	 * @return El elemento borrado
	 * @throws EmptyCollectionException
	 * @throws NoElementException ?
	 */
	@Override
	public T remove(T element) throws EmptyCollectionException {
		if(first==null){
			throw new EmptyCollectionException(CLASSNAME);
		}

		Node<T> current = first;
		Node<T> aux = null;
		if(current.element.equals(element)){
			aux = current;
			first = current.next;
			return aux.element;
		}
		//Avanzamos punteros hasta que lleguemos a que si next es el valor a remover
		try{
			while(!current.next.element.equals(element)){
				current = current.next;
			}
		}catch(NullPointerException e){
			throw new NoSuchElementException();
		}
		//El valor del next del current es el elemento a quitar entonces
		aux = current.next;
		current.next = aux.next;
		return aux.element;
	}


	@Override
	public T getFirst() throws EmptyCollectionException {
		if(first==null){
			throw new EmptyCollectionException(CLASSNAME);
		}
		return first.element;
	}

	@Override
	public T getLast() throws EmptyCollectionException {
		if(first==null){
			throw new EmptyCollectionException(CLASSNAME);
		}

		Node<T> current = first;
		while(current.next!=null){
			current = current.next;
		}
		return current.element;
	}


	@Override
	public boolean contains(T target) {
		if (isEmpty()){
			return false;
		}

		Node<T> current = first;
		while(current!=null){
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
		if(isEmpty()){
			return 0;
		}

		int count = 1;
		Node<T> current = first;
		while (current.next!=null) {
			current = current.next;
			count++;
		}
		return count;
	}

	/**
	 * THIS METHOD USES 1..N INDEX
	 * @param i
	 * @return the element in the i position
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public T getElementAt(int i) throws IndexOutOfBoundsException {
		if(i<1) throw new IndexOutOfBoundsException();
		//if(i>size()) throw new IndexOutOfBoundsException();

		int currentpos=1;
		Node<T> current = first;
		while(current != null){
			if(currentpos==i) return current.element;
			current = current.next;
			currentpos++;
		}
		throw new IndexOutOfBoundsException();
	}
	/*Devuelve el nodo como que esta en la posicion que se le pasa como parametro*/
	public Node<T> getNodeAt(int i) throws IndexOutOfBoundsException {
		if(i<1) throw new IndexOutOfBoundsException();
		//if(i>size()) throw new IndexOutOfBoundsException();

		int currentpos=1;
		Node<T> current = first;
		while(current != null){

			if(currentpos==i) return current;

			current = current.next;
			currentpos++;
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public T removeElementAt(int i) throws IndexOutOfBoundsException {
		if(i<1) throw new IndexOutOfBoundsException();
		//if(i>size()) throw new IndexOutOfBoundsException();

		int currentpos=1;
		Node<T> current = first;
		if(i==1){
			Node<T> aux = first;
			first = first.next;
			return aux.element;
		}
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
		throw new IndexOutOfBoundsException();
	}

	@Override
	public T replaceElementAt(int n, T element) {
		if(n<1) throw new IndexOutOfBoundsException();
		//if(n>size()) throw new IndexOutOfBoundsException();

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
		throw new IndexOutOfBoundsException();
	}


	@Override
	public Iterator<T> iterator() {
		return new DefaultIteratorImpl();
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

		Node<T> siguiente;
		@Override
		public boolean hasNext() {
			if(siguiente==null)
				return false;
			else
				return true;
		}

		@Override
		public T next() {
			if(siguiente==null) throw new NoSuchElementException();
			Node<T> aux = siguiente;
			siguiente = siguiente.next;
			return aux.element;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public DefaultIteratorImpl(){
			siguiente = first;
		}
	}

	private class SkippingIteratorImpl implements Iterator<T> {

		Node<T> siguiente;
		@Override
		public boolean hasNext() {
			if(siguiente==null)
				return false;
			else
				return true;
		}

		@Override
		public T next() {
			if(siguiente==null) throw new NoSuchElementException();
			Node<T> aux = siguiente;
			try{
				siguiente = siguiente.getTwoNext();
			}catch(NullPointerException e){
				/**Evita que estando en la ultima posicon de intente refrenciar al next del ultimo ya que seria null*/
				siguiente = null;
			}
			return aux.element;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public SkippingIteratorImpl(){
			this.siguiente = first;
		}
	};

	@Override
	public Iterator<T> skippingIterator() {
		return new SkippingIteratorImpl();
	}

	private class RangedIteratorImpl implements Iterator<T> {

		private int from,to,step,current_position;
		private Node<T> siguiente;

		public RangedIteratorImpl(int from, int to, int step) {
			this.from = from;
			this.to = to;
			this.step = step;
			this.current_position = from;

			int currentpos=1;
			Node<T> current = first;
			while(current != null){
				if(currentpos==from) break;
				current = current.next;
				currentpos++;
			}
			this.siguiente = current;

		}

		@Override
		public boolean hasNext() {
			if( siguiente == null || this.current_position>to)
				return false;
			else 
				return true;

		}

		@Override
		public T next() {
			if(siguiente==null) throw new NoSuchElementException();
			Node<T> aux = siguiente;
			for (int i = 0; i < step; i++) {
				try{
					siguiente = siguiente.next;
					current_position++;
				}catch(NullPointerException e){
					siguiente=null;
					break;
				}
			}
			if(current_position > to+1 ) siguiente = null;
			return aux.element;
		}

		@Override
		public void remove() {			
			throw new UnsupportedOperationException();
		}

	};

	@Override
	public Iterator<T> rangedIterator(int from, int to, int step) {
		return new RangedIteratorImpl(from,to,step);
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
		UnorderedSingleLinkedList<E> aux = new UnorderedSingleLinkedList<E>();
		E aux_element;
		boolean is;
		for (int i = 0; i < T1.size(); i++) {
			aux_element = T1.getElementAt(i+1);
			is = false;
			for (int j = 0; j < aux.size(); j++) {
				if(aux_element.equals(aux.getElementAt(j+1))) is=true;
			}
			if(!is) aux.addToRear(aux_element);
		}

		return aux;
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
		UnorderedSingleLinkedList<E> aux = new UnorderedSingleLinkedList<E>();
		Iterator<E> t = T1.iterator();
		while(t.hasNext()){
			aux.addToFront(t.next());
		}

		return aux;
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
		UnorderedSingleLinkedList<E> aux = new UnorderedSingleLinkedList<E>();
		while(contents.hasNext()){
			aux.addToRear(contents.next());
		}
		return aux;
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
