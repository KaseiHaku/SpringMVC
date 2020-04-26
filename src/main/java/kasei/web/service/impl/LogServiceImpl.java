package kasei.web.service.impl;


import kasei.springcloud.webflux.repository.dao.LogDao;
import kasei.springcloud.webflux.repository.entity.Log;
import kasei.web.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;

    public <S extends Log> List<S> insert(Iterable<S> entities){
        return logDao.insert(entities);
    }

}
