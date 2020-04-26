package kasei.web.service;

import kasei.springcloud.webflux.repository.entity.Log;

import java.util.List;


public interface LogService {
    <S extends Log> List<S> insert(Iterable<S> entities);
}
