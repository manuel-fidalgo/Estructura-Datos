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

		return false;
	}
	
	private boolean isAscendRecursive(Node<T> current){
		return false;
	}
	

	@Override
	public boolean isDescend() {

		// TODO Implementar de forma recursiva
		return false;
	}

	@Override
	public boolean isEqualStructure(AbstractSingleLinkedList<T> other) {

		// TODO Implementar de forma recursiva
		return false;
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
			//Se ha acabado la lista, devolvemos la vacia
			return;
		}else{
			cout++;
			current = current.next;
			dropElementsRecursive(n, current, cout);
		}
	}

	@Override
	public SimpleListADT<T> reverse() {

		// TODO Implementar de forma recursiva
		return null;
	}

	@Override
	public SimpleListADT<T> repeatAllElements() {

		// TODO Implementar de forma recursiva
		return null;
	}

	@Override
	public SimpleListADT<T> repeatNElements(int n) {

		// TODO Implementar de forma recursiva
		return null;
	}

}
