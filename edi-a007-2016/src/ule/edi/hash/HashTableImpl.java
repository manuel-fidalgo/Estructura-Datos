package ule.edi.hash;

import java.util.List;

public class HashTableImpl<K, V> implements HashTable<K, V> {
	
	/**
	 * Función que indica el índice en el array de celdas para una clave.
	 * 
	 * @author profesor
	 *
	 * @param <K>
	 */
	public static interface HashFunction<K> {
		
		/**
		 * Indica el índice en un array de tamaño n.
		 * 
		 * @param n tamaño del array destino.
		 * @param g clave del par (clave,valor) a almacenar.
		 * @return índice para almacenar el par (clave,valor).
		 */
		public int apply(int n, K g);
	};

	/**
	 * Valor para los 'enlaces' a siguiente similar a null en listas enlazadas
	 */
	static final int NILL = -1;
	

	static class Cell<K, V> {
		
		public Cell(K key, V value) {
		
			this.key = key;
			
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
		
			if (this == obj) {
				
				return true;
			}
			
			if (obj instanceof Cell) {
				
				Cell<?, ?> other = (Cell<?, ?>) obj;
				
				return (key.equals(other.key));
			}
			
			return false;
		}


		@Override
		public String toString() {
			
			return "(" + key + ", " + value + ")";
		}


		K key;
		
		V value;
	};
	
	//	Array de celdas, primario
	private Object[] cells;
	
	//	Enlaces
	private int[] clinks;
	
	
	//	Colisiones
	private int firstAvailable;
	
	private Object[] overflow;
	
	private int[] olinks;
	
	
	private long nElements;
	
	//	por defecto
	private HashFunction<K> hash = new HashFunction<K>() {

		@Override
		public int apply(int n, K g) {

			return (g == null ? 0 : (g.hashCode() % n));
		}
	};

	//	Gestión de arrays de Cell<K, V>
	//
	@SuppressWarnings("unchecked")
	private Cell<K, V> getCell(Object[] from, int n) {
		
		return (Cell<K, V>) from[n];
	}
	
	private void setCell(Cell<K, V> c, int n, Object[] into) {
		
		into[n] = c;
	}

	private boolean isAvailable(Object[] in, int n) {
		
		return (in[n] == null);
	}


	static final int DEFAULT_CELL_ARRAY_SIZE = 5;
	
	static final int DEFAULT_OVERFLOW_ARRAY_SIZE = 5;
	
	/**
	 * Construye una tabla vacía del tamaño por defecto.
	 * 
	 * Una tabla vacía sería (con 5 celdas, 5 overflow):
	 * 
	 * En cells[] nulls, en clinks[] NILL:
	 * 
     * Celdas:
     * 00000: null >> -
     * 00001: null >> -
     * 00002: null >> -
     * 00003: null >> -
     * 00004: null >> -
     * 
     * En overflow[] nulls, en olinks[] índices:
     * 
     * Colisiones: primero disponible: 00000
     * 00000: null >> 1
     * 00001: null >> 2
     * 00002: null >> 3
     * 00003: null >> 4
     * 00004: null >> -
     * 
     * Los valores de olink construyen junto con el índice del primero
     * disponible, una 'lista enlazada' de posiciones disponibles en desbordamiento.
	 */
	public HashTableImpl() {
	
		// TODO preparar una tabla vacía de tamaños por defecto
	}
	
	/**
	 * Construye una tabla vacía con parámetros específicos.
	 * 
	 * @param h función hash a utilizar
	 * @param n número de celdas
	 * @param m tamaño de la zona de desbordamiento
	 */
	public HashTableImpl(HashFunction<K> h, int n, int m) {
		
		// TODO preparar una tabla vacía de tamaños y función hash dados		
	}

	/*
	 * Si al ir a insertar en desbordamiento no queda espacio disponible,
	 * se produce un 'rehash' con nuevas estructuras de datos de tamaño
	 * mayor. Se supondrá que el nuevo tamaño para un array 'x' será
	 * 
	 * 	Primes.next(2 * x.length)
	 * 
	 * para las celdas y para la zona de desbordamiento.
	 * 
	 * Por ejemplo, una tabla con 3 celdas y 6 de desbordamiento se
	 * transforma a una tabla de 7 celdas y 13 posiciones para
	 * desbordamiento.
	 * 
	 *  (non-Javadoc)
	 * @see ule.edi.hash.HashTable#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(K key, V value) {

		// TODO implementar según especificación
	}

	@Override
	public boolean contains(K key) {
	
		// TODO implementar según especificación
		return false;
	}

	@Override
	public V get(K key) {
		
		// TODO implementar según especificación
		return null;
	}

	@Override
	public void remove(K key) {

		// TODO implementar según especificación
	}

	@Override
	public String toString() {
		
		StringBuffer rx = new StringBuffer();
		
		rx.append("Celdas:\n");
		for (int n = 0; n < cells.length; ++n) {
			rx.append(String.format("%05d", n) + ": ");
			rx.append((cells[n] != null ? cells[n].toString() : "null"));
			rx.append(" >> " + (clinks[n] != NILL ? clinks[n] : "-"));
			rx.append("\n");
		}
		rx.append("\n");
		
		rx.append("Colisiones: primero disponible: " + String.format("%05d", firstAvailable) + "\n");
		for (int n = 0; n < overflow.length; ++n) {
			rx.append(String.format("%05d", n) + ": ");
			rx.append((overflow[n] != null ? overflow[n].toString() : "null"));
			rx.append(" >> " + (olinks[n] != NILL ? olinks[n] : "-"));
			rx.append("\n");
		}
		rx.append("\n");
		
		return rx.toString();
	}

	@Override
	public long size() {

		// TODO implementar según especificación
		return 0;
	}

	@Override
	public List<K> keys() {
		
		// TODO implementar según especificación
		return null;
	}

	@Override
	public List<V> values() {

		// TODO implementar según especificación
		return null;
	}
	
	//
	//	Para JUnit
	//
	Object[] getCells() {
		
		return cells;
	}
	
	int[] getCLinks() {
		
		return clinks;
	}
	
	Object[] getOverflow() {
		
		return overflow;
	}
	
	int[] getOLinks() {
		
		return olinks;
	}

	HashFunction<K> getHashFunction() {
		
		return hash;
	}
		
}
