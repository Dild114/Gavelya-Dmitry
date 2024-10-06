package CustomArraylistClass;

/**
 CustomArrayList
 */

public class CustomArrayList<T> implements ArrayMethods<T> {
  private Integer value;
  private Object[] list;
  private Integer counter = 0;

  /**
   * Если при инициализации не передется параметр,
   * то изначальный размер массива 5
   */
  public CustomArrayList() {
    list = new Object[5];
  }

  /**
   * Создает массив размера value
   */
  public CustomArrayList(Integer value) {
    this.value = value;
    list = new Object[value];
  }

  /**
   * Удаляет элемент из массива
   * @param index - индекс элемента, который удаляется из массива
   * @return удаленный массив
   */
  @Override
  public Object remove(Integer index) {
    Object element = list[index];
    for (int i = index; i < counter - 1; i++) {
      list[i] = list[i + 1];
    }
    list[counter - 1] = null;
    --counter;
    return element;
  }
  /**
   * Возвращает элемент только по индексу
   * @param index - индекс элемента, который нужно получить
   */
  @Override
  public Object get(Integer index) {
    return list[index];
  }

  /**
   * Добавляет элемент в массив
   * @param number - элемент, который добавляется
   */
  @Override
  public void add(T number) {
    if (number == null) {
      throw new IllegalArgumentException();
    }
    if (list.length == counter) {
      resize();
    }
    list[counter] = number;
    ++counter;
  }

  /**
   * Релокация массива
   * Увеличивает размер массива в два раза
   */
  private void resize() {
    int newSize = value * 2;
    Object[] newList = new Object[newSize];
    for (int i = 0; i < value; i++) {
      newList[i] = list[i];
    }
    list = newList;
  }
}