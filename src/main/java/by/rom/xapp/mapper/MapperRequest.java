package by.rom.xapp.mapper;

public interface MapperRequest<T, K> {

    T requestToEntity(K K);
}
