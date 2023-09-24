package by.rom.xapp.mapper;

public interface MapperResponse <T, K> {

    T entityToResponse(K K);
}
