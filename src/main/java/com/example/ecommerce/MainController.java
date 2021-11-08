package com.example.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class MainController {

    @Autowired
    private final ProductService productService;
//    private final Iterable<Product> products;

    public MainController(ProductService productService) {
        this.productService = productService;
//        this.products = productService.getAllProduct();
    }


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("product",new Product());
        return "home";
    }

    @GetMapping("/products")
    public String product(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "product";
    }



    @GetMapping("/products/{id}")
    public String getAProduct(@PathVariable long id, Model model){
        try{
            model.addAttribute("id",productService.getAProduct(id).orElseThrow());
            return "aproduct";
        } catch(NoSuchElementException e) {
            return "404";
        }
    }


    @PostMapping("/products")
    public String postProduct(Product product, Model model){
        productService.addProduct(product);
        model.addAttribute("products",productService.getAllProduct());
        return "product";
    }

    @GetMapping("/search")
    public String search(Model model, Search search){

        return "search";
    }

    @PostMapping("/search/id")
    public String searchById(Search search){
        return "redirect:/products/" + search.getId();
    }

    @PostMapping("/search/brand")
    public String searchByBrand(Search search, Model model){
        model.addAttribute("brands",productService.findByBrand(search.getBrand()));
        return "results";
    }

    @GetMapping("/products/update/{id}")
    public String update(@PathVariable long id, Model model){

        model.addAttribute("product",productService.getAProduct(id).orElseThrow());
        return "update";
    }


    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable long id, Product product, Model model){
        var temp = productService.getAProduct(id).orElseThrow();
        temp.setName(product.getName());
        temp.setBrand(product.getBrand());
        temp.setPrice(product.getPrice());
        productService.addProduct(temp);
        model.addAttribute("id",temp);
        return "aproduct";
    }

    @RequestMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteById(id);
        return "redirect:/products";
    }


}
