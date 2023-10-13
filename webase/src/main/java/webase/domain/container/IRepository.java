package webase.domain.container;

public interface IRepository<T> {

    T getModel(Long id);

    void add(T model);

    void delete(Long id);

    void update(T model);

    Long getMaxId();

    String getTableName();
}
