package kasei.web.service.impl;


import kasei.web.service.HelloWorldService;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String getHelloWorldString() {
        return "hello world service";
    }
}
