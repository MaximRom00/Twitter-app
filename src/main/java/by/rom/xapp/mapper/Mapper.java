package by.rom.xapp.mapper;

public interface Mapper<T, K> {

    T toEntity(K K);

    K toDto(T t);
}
