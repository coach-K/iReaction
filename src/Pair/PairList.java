package Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This is a generics Pair List class, holds a list of pair of any type
 * @literal <K> key of element}
 * {@literal <V> value of element}
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../src/pair/pairlist/index.html">
 * Java Collections Framework</a>.
 *
 * @author OGUNDE KEHINDE
 * @see     Pair
 * @see     Collection
 * @see     java.util.List
 * @see     java.util.ArrayList
 * @since   7.1
 */
public class PairList<K, V> implements Collection<Pair<K, V>> {

    /**
     * Shared empty list instance used for empty instances.
     */
    ArrayList<Pair<K, V>> pairs;

    /**
     * Constructs an empty list.
     */
    public PairList() {
        pairs = new ArrayList<>();
    }

    /**
     * Returns the <tt>pair object</tt> at the specified position in this list.
     *
     * @param  index index of the <tt>pair object</tt> to return
     * @return the <tt>pair object</tt> at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public Pair<K, V> get(int index){
        return pairs.get(index);
    }

    /**
     * Returns a <tt>pair object</tt> if its key equals search in this list.
     *
     * @param  search key of the <tt>pair object</tt> to test its presence
     * @return the <tt>pair object</tt> if search is present in this list
     */
    public Pair<K, V> get(String search) {
        for (Pair<K, V> pair : pairs) {
            if (pair.getKey().equals(search))
                return pair;
        }
        return null;
    }

    /**
     * Returns the number of <tt>pair object</tt> in this list
     *
     * @return the number of <tt>pair object</tt> in this list
     */
    @Override
    public int size() {
        return pairs.size();
    }

    /**
     * Returns <tt>true</tt> if this list contains no element
     *
     * @return <tt>tt</tt> if this list contains no element
     */
    @Override
    public boolean isEmpty() {
        return pairs.size() == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * {@code} <tt> o == null ? e == null : o.equals(e); </tt>
     *
     * @param o element whose presence in the list to be tested.
     * @return <tt>true</tt> if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        return pairs.contains(o);
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified pair object
     * More formally, returns <tt>true</tt> if and only if list list contains
     * at least one pair object <tt>e</tt> such that
     * {@code} <tt> o == null ? e == null : o.getKey().equals(e.getKey()); </tt>
     *
     * @param o pair object whose presence in the list to be tested.
     * @return <tt>true</tt> if this list contains the specified pair object.
     */
    public boolean containsPair(Pair<K, V> o) {
        for (Pair<K, V> t : pairs) {
            if (t.getKey().equals(o.getKey()))
                return true;
        }
        return false;
    }

    /**
     * Returns an iterator over the pair objects in the list in a proper sequence.
     *
     * @return an iterator over the pair objects in the list in a proper sequence.
     */
    @Override
    public Iterator<Pair<K, V>> iterator() {
        return pairs.iterator();
    }

    /**
     * Returns an array containing all of the pair in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the pairs in this list in
     *         proper sequence
     */
    @Override
    public Object[] toArray() {
        return pairs.toArray();
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @see java.util.Collection#toArray(T[] a)
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return pairs.toArray(a);
    }

    /**
     * Appends the specified pair to the end of this list.
     *
     * @param kvPair pair to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(Pair<K, V> kvPair) {
        if (!this.containsPair(kvPair))
            return pairs.add(kvPair);
        else return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list.
     * If it is present.
     *
     * @see {@link java.util.ArrayList#add}
     * @param o element to be removed from this list, if present.
     * @return <tt>true</tt> if this list contains the specified element.
     */
    @Override
    public boolean remove(Object o) {
        return pairs.remove(o);
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified elements.
     *
     * @param c elements whose presence in the list to be tested.
     * @return <tt>true</tt> if this list contains the specified elements.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return pairs.containsAll(c);
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified elements.
     *
     * @param c elements whose presence in the list to be tested.
     * @return <tt>true</tt> if this list contains the specified elements.
     */
    public boolean containsAllPair(Collection<Pair<K, V>> c) {
        boolean flag = false;
        for (Pair<K, V> pair : c){
            flag = this.containsPair(pair);
        }
        return flag;
    }

    /**
     * Appends the specified pairs to the end of this list.
     *
     * @param c pairs to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#addAll})
     */
    @Override
    public boolean addAll(Collection<? extends Pair<K, V>> c) {
        return pairs.addAll(c);
    }

    /**
     * Removes the occurrence of the specified element from this list.
     * If it is present.
     *
     * @see {@link java.util.ArrayList#add}
     * @param c element to be removed from this list, if present.
     * @return <tt>true</tt> if this list contains the specified element.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return pairs.removeAll(c);
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.  In other words, removes from this list all
     * of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return pairs.retainAll(c);
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        pairs.clear();
    }
}