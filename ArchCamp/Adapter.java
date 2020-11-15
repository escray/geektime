public class Adapter {

}

interface NewSortable<T> {
    int size();
    T getElement(int i );
    void setElement(int i, T o);
}

// Class Adapter
class SortableList<T> extends ArrayList<T> implements NewSortable<T> {
    public T getElement(int i) {
        return get(i);
    }

    public void setElement(int i, T o) {
        set(i, o);
    }
}

// Object Adapter
class ListSortable<T> implements Sortable<T> {
    private final List<T> list;

    public ListSortable(List<T> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public T get(int i) {
        return list.get(i);
    }

    public void set(int i, T o) {
        list.set(i, o);
    }
}
