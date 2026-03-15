package az.restapi.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.restapi.demo.exceptions.UnauthorizedException;
import az.restapi.demo.maps.UpdateEmailMapper;
import az.restapi.demo.models.Customer;
import az.restapi.demo.security.ApiKeyAuthorization;
import az.restapi.demo.services.CustomerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private ApiKeyAuthorization apiKeyAuthorization;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create-new")
    public void newCustomer(@RequestHeader("API-Key") String apiKey, 
                            @Valid @RequestBody Customer customer){

        if(apiKeyAuthorization.isUnauthorized(apiKey)){customerService.newCustomer(customer);}
        else{throw new UnauthorizedException("Invalid API Key");}

        
    }

    @GetMapping("/show-all")
    public List<Customer> getAll(@RequestHeader("API-Key") String apiKey){
        if(apiKeyAuthorization.isUnauthorized(apiKey)){return customerService.getAll();}
        else{throw new UnauthorizedException("Invalid API Key");}
        
    }

    @GetMapping("get/{mail}")
    public Customer getByMail(@RequestHeader("API-Key") String apiKey, 
                                @PathVariable String mail) {

        if(apiKeyAuthorization.isUnauthorized(apiKey)){return customerService.getByMail(mail);}
        else{throw new UnauthorizedException("Invalid API Key");}                            

        
    }

    @PatchMapping("/update-mail")
    public void updateMail(@RequestHeader("API-Key") String apiKey, 
                            @RequestBody UpdateEmailMapper map){
        
        if(apiKeyAuthorization.isUnauthorized(apiKey)){customerService.updateMail(map);}
        else{throw new UnauthorizedException("Invalid API Key");}                        
        

    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@RequestHeader("API-Key") String apiKey, 
                                @RequestParam int id){

        if(apiKeyAuthorization.isUnauthorized(apiKey)){customerService.deleteCustomer(id);}
        else{throw new UnauthorizedException("Invalid API Key");}                             
        

    }

    

}
