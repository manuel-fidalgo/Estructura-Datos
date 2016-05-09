package ule.edi.recursive;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedList<T> {

	public SingleLinkedListImpl() {}

	@SuppressWarnings({ "unchecked", "unused" })
	public SingleLinkedListImpl(T... values) {
		int a;
		for (T i : values) {
			addToRear(i);
		}
	}

	@Override
	public void addToRear(T content) {
		Node<T> insert = new Node<T>(content);
		if(first==null){
			first = new Node<T>(content);
		}else{
			addToRearRecursive(insert,this.first);
		}
	}
	private void addToRearRecursive(Node<T> insert, Node<T> current){
		if(current.next==null){
			current.next = insert;
		}else{
			addToRearRecursive(insert,current.next);
		}
	}

	@Override
	public boolean isAscend() {
		return isAscendOrDescendRec(this.first,true);
	}

	@Override
	public boolean isDescend() {
		return isAscendOrDescendRec(this.first,false);
	}
	/**
	 * @param current nodo que estamos en cada una de las llamadas recursivas
	 * @param status true is para evaluar si es ascende, false para evaluar si es descendente
	 * @return true o false si segun el parametro status
	 */
	private boolean isAscendOrDescendRec(Node<T> current,boolean status){
		if(current==null || current.next==null){ //Caso que hayamos iterado la lista por completo o que la lista este vacia o con solo un elemento
			return true;
		}

		T current_element = current.content;
		T next_element = current.next.content;

		if(status){
			if(areAscend(current_element,next_element)){
				return isAscendOrDescendRec(current.next,status);
			}else{
				return false;
			}
		}else{
			if(areDescend(current_element,next_element)){
				return isAscendOrDescendRec(current.next,status);
			}else{
				return false;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private boolean areAscend(T current_element, T next_element) {
		Comparable<T> c1 = (Comparable<T>) current_element;
		if(c1.compareTo(next_element)<=0){
			return true;
		}else{
			return false;
		}

	}
	@SuppressWarnings("unchecked")
	private boolean areDescend(T current_element, T next_element){
		Comparable<T> c1 = (Comparable<T>) current_element;
		if(c1.compareTo(next_element)>=0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isEqualStructure(AbstractSingleLinkedList<T> other) {
		if(this.first==null && other.first==null){
			return true;
		}else if(this.first==null && other.first!=null || this.first!=null && other.first==null){
			return false;
		}else{
			return isEqualStructureRec(this.first, other.first);
		}
	}
	private boolean isEqualStructureRec(Node<T> current_lista_1, Node<T> current_lista_2){
		if(current_lista_1.next==null && current_lista_2.next != null ||
				current_lista_1.next!=null && current_lista_2.next == null){
			return false;
		}
		if(current_lista_1.next==null && current_lista_2.next==null){
			return true;
		}
		return isEqualStructureRec(current_lista_1.next, current_lista_2.next);
	}


	@Override
	public void dropElements(int n) {

		if(n>0 && this.first!= null){
			dropElementsRecursive(n, first, 0);
		}
	}
	private void dropElementsRecursive(int n, Node<T> current, int cout){
		if(cout==n){
			this.first = current;
			return;
		}else if(current.next==null){
			this.first = null;
			return;
		}else{
			cout++;
			current = current.next;
			dropElementsRecursive(n, current, cout);
		}
	}

	@Override
	public SimpleListADT<T> reverse() {
		SimpleListADT<T> rev = new SingleLinkedListImpl<T>();
		if(this.first==null){
			return rev;
		}else{
			return reverseRec(this,rev,this.first);
		}
	}

	public SimpleListADT<T> reverseRec(SimpleListADT<T> original,SimpleListADT<T> reversed, Node<T> curret){
		if(curret==null){
			return reversed;//Termina, hemos acabado de iterar con la lista
		}else{
			reversed.addFirst(curret.content);
			return reverseRec(original, reversed, curret.next);
		}
	}


	@Override
	public SimpleListADT<T> repeatAllElements() {
		if(this.first==null){
			return this;
		}else{
			return repeatAllElementsRec(this, new SingleLinkedListImpl<T>(), this.first);
		}
	}

	public SimpleListADT<T> repeatAllElementsRec(SimpleListADT<T> lista, SimpleListADT<T> list_rep,Node<T> current) {
		if(current==null){
			return list_rep;
		}else{
			addToRearNTimes(list_rep,current.content,2,0);
			return repeatAllElementsRec(lista, list_rep, current.next);
		}
	}


	@Override
	public SimpleListADT<T> repeatNElements(int n) {
		if(this.first==null){
			return this;
		}else{
			return repeaNElementsRec(this, new SingleLinkedListImpl<T>(), this.first, n,0);
		}
	}

	private SimpleListADT<T> repeaNElementsRec(SimpleListADT<T> lista, SimpleListADT<T> list_rep, Node<T> current ,int n, int cout) {
		if(current==null){
			return list_rep;
		}else{
			if(cout<n){
				addToRearNTimes(list_rep,current.content,2,0);
			}else{
				addToRearNTimes(list_rep, current.content,1,0);
			}
			cout++;
			return repeaNElementsRec(lista, list_rep, current.next,n,cout);
		}
	}

	private void addToRearNTimes(SimpleListADT<T> list_rep, T element, int nveces, int cout){
		if(cout==nveces){
			return;
		}else{
			list_rep.addToRear(element);
			cout++;
			addToRearNTimes(list_rep, element, nveces, cout);
		}
	}

}
