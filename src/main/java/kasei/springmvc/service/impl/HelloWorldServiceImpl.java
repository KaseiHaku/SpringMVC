package kasei.springmvc.service.impl;


import kasei.springmvc.service.HelloWorldService;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String getHelloWorldString() {
        return "hello world service";
    }
}
