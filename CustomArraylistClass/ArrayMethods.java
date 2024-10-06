package CustomArraylistClass;

/**
 Интерфейс для ArrayList
 */
public interface ArrayMethods<T> {
  /**
   * Добавляет в массив элемент number
   * @param number - элемент, который добавляется
   */
  void add(T number);

  /**
   * Удаляет элемент только по индексу
   * @param index - индекс элемента, который удаляется из массива
   * @return удаленный элемент
   */
  Object remove(Integer index);

  /**
   * Возвращает элемент массива по индексу
   * @param index - индекс элемента, который нужно получить
   * @return элемент массива
   */
  Object get(Integer index);
}
