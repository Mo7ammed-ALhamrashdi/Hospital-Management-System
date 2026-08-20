package interfaces;

public interface Manageable {
        void add(Object entity);

        void removeById(String id);

        Object[] getAll();
    }

