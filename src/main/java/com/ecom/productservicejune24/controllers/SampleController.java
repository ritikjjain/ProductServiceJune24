package com.ecom.productservicejune24.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
// This class will be hosting a set of HTTP API's.
public class SampleController {

    @GetMapping("/hello/{name}/{number}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("number") int number){
        //return "Hello " + name;
        String output = "";
        for (int i=0 ; i<number ; i++){
            output = output + "Hello " + name;
        }
        /* System.out.println(output); */
        return output;
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "Bye World";
    }
}
