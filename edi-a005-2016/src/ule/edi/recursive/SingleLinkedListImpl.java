package ule.edi.recursive;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedList<T> {
	
	public SingleLinkedListImpl() {

	}
	//
	
	@SuppressWarnings("unchecked")
	public SingleLinkedListImpl(T... values) {
		int a;
		for (T i : values) {

			addToRear(i);
		}
	}

	@Override
	public void addToRear(T content) {

		// TODO Implementar de forma recursiva
	}

	@Override
	public boolean isAscend() {
		
		// TODO Implementar de forma recursiva
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
		
		// TODO Implementar de forma recursiva
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
