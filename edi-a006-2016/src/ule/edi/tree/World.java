package ule.edi.tree;

import java.util.ArrayList;
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
@SuppressWarnings("unused")
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
		insertRec(new StringBuilder(address),e);
	}

	private void insertRec(StringBuilder sb, Entity e) {
		char c;
		World w;
		if(sb.length()==0){
			this.addEntity(e);
			return;
		}
		
		if(this.content==null) 
			this.content = new LinkedList<Entity>();
		if(this.travelLeft()==null) 
			this.setLeft(new World());
		if(this.travelRight()==null) 
			this.setRight(new World());
		if(this.content.isEmpty()) 
			this.content.add(new Entity(Entity.FOREST));

		c = sb.charAt(0);
		sb.deleteCharAt(0);
		if(c=='R'){
			this.travelRight().insertRec(sb, e);
		}
		if(c=='L'){
			this.travelLeft().insertRec(sb, e);
		}
	}

	/**
	 * Itera sobre la lista de entitidades y en caso de que haya alguna entidad del
	 * mismo tipo que la que vamos a agnadir aumentamos su cardinal.
	 * @param e entidad a agnadir
	 */
	private void addEntity(Entity e) {
		if(this.content==null){
			this.content = new LinkedList<Entity>();
		}
		if(this.travelLeft()==null){
			this.setLeft(new World());
		}
		if(this.travelRight()==null){
			this.setRight(new World());
		}

		List<Entity> l = this.content;
		for (Entity entity : l) {
			if(e.getType()==entity.getType()){
				entity.setCount(entity.getCount()+e.getCount()); //busca la entidad a agnadir y
				return;
			}
		}
		l.add(e); // caso que no haya ninguna del mismo tipo
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
		_int acum = new _int(0);
		countCastlesCloserThanRec(distance,0,acum);
		return acum.get();
	}

	private void countCastlesCloserThanRec(long total_distance,long current_distance, _int acum) {
		getEntCount(acum,Entity.CASTLE);
		if(total_distance==current_distance){
			return;
		}else{
			current_distance++;
			if(this.travelLeft()!=null) this.travelLeft().countCastlesCloserThanRec(total_distance, current_distance, acum);
			if(this.travelRight()!=null) this.travelRight().countCastlesCloserThanRec(total_distance, current_distance, acum);
		}
	}

	static class _int{
		public long n;
		_int(int n){
			this.n = n;
		}
		public long get(){
			return this.n;
		}
		public void acumm(long n){
			this.n = this.n + n;
		}
		public void doTrue(){
			this.n = 1;
		}
		public boolean getBoolValue(){
			return this.n==1 ? true : false;
		}
	}
	/**
	 * 
	 * @param acum objeto en el que devolvera la cardinalidar
	 * @param type typo de la entidad sobre la que se quiere saber la cardinalidad
	 */
	private void getEntCount(_int acum,int type) {
		try{
			for(Entity en : this.content){
				if(en.getType()==type){//Sacamos el cardinal de la las entidad que se le ha pasado
					acum.acumm(en.getCount());
					return;
				}
			}
		}catch(NullPointerException e){

		}
	}

	/**
	 * Indica cuántas entidades del tipo dado hay en un nivel.
	 * 
	 * @param type tipo de entidad.
	 * @param n nivel a considerar.
	 * @return cuántas entidades de ese tipo hay en ese nivel.
	 */
	public long countAtLevel(int type, int n) {

		_int acum = new _int(0);
		countAtLevelRec(type,n,1,acum);
		return acum.get();

	}

	private void countAtLevelRec(int type, int level_final, int level_current, _int acum) {

		if(level_current==level_final){
			getEntCount(acum,type);
		}else{
			level_current++;
			if(this.travelLeft()!=null) this.travelLeft().countAtLevelRec(type, level_final, level_current, acum);
			if(this.travelLeft()!=null) this.travelRight().countAtLevelRec(type, level_final, level_current, acum);
		}
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

	/*devolver la ruta donde se encuentra la princesa enesima*/

	public boolean findNPrincessInorden(long n, LinkedList<Character> rx) {
		if(n<1) throw new IllegalArgumentException();
		if(this.isEmpty()) return false;
		_int bool = new _int(0);
		_int princessFound = new _int(0);
		findNPrincessInordenRec(this,n,rx,bool,princessFound);
		return bool.getBoolValue();
	}

	private void findNPrincessInordenRec(World origin, long n, LinkedList<Character> list, _int bool, _int princessFound) {
		
		
		if(!this.travelLeft().isEmpty() && !bool.getBoolValue()){
			list.add('L');
			this.travelLeft().findNPrincessInordenRec(origin, n, list, bool, princessFound); //llamada por la izquierda
		}
		
		princessFound.acumm(this.getPrincess()); //Acumulamos princesas que haya en este nodo 
		if(princessFound.get()>=n){
			bool.doTrue(); //Se ha encontrado la princesa
		}else{
			if(!list.isEmpty()) list.removeLast();
		}
		
		if(!this.travelRight().isEmpty() && !bool.getBoolValue()){
			list.add('R');
			this.travelRight().findNPrincessInordenRec(origin, n, list, bool, princessFound);
		}
		
		
		
	}

	private long getPrincess() {
		long con=0;
		for(Entity i : this.content){
			if(i.getType()==Entity.PRINCESS){
				con = i.getCount();
			}
		}
		return con;
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
	 * Para el recorrido en anchura simularemos una cola con un indice
	 * @return el número de nodos no vacíos que ha recorrido antes del dragón.
	 */

	public long findFirstDragonInBreadthOrder() {

		int count = 0;
		ArrayList<World> queue = new ArrayList<World>();
		queue.add(this);

		while(!queue.isEmpty()){

			World w = queue.get(0); 
			queue.remove(0);

			if(!w.isEmpty()){
				if(w.hasDragon())
					return count;

				count++; //Aumentamos el contador de nodos no vacios

				if(w.travelLeft()!=null)
					queue.add(w.travelLeft());

				if(w.travelRight()!=null) 
					queue.add(w.travelRight());
			}
		}
		//No se ha encontrado ningun dragon, se han encolado todos los elementos del arbol
		return count;
	}

	private boolean hasDragon() {
		for(Entity i : this.content){
			if(i.getType()==Entity.DRAGON){
				return true;
			}
		}
		return false;
	}

}
