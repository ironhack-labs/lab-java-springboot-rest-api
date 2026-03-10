package service;

import model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
     private static List<Product> productList=new ArrayList<>();
     public void addProduct(Product product){
         productList.add(product);
     }
     public List<Product> getAllProduct(){
         return this.productList;
     }
     public Product getProductByName(String name){
         for(Product product:productList){
             if(name.equals(product.getName())){
                 return product;
             }
         }
         return null;
     }
     public void updateProduct(String name, Product updateInfo){
         for(Product product:productList){
             if(name.equalsIgnoreCase(product.getName())){
                 product.setPrice(updateInfo.getPrice());
                 product.setCategory(updateInfo.getCategory());

                 System.out.println("Model.Product succesfully updated!");
                 return;
             }
         }
         System.out.println("Sorry, product named' "+name+"'not found");
     }

     public void deleteProduct(String name){
         boolean removed= productList.removeIf(product ->product.getName().equalsIgnoreCase(name));
         if(removed){
            System.out.println("Model.Product successfully deleted");
        }
        else{
            System.out.println("Model.Product not found");
        }
     }
     public List<Product> getProductByCategory(String category){
               List<Product> categoryProducts=new ArrayList<>();
               for(Product product:productList){
                   if(category.equalsIgnoreCase(product.getCategory())){
                       categoryProducts.add(product);
                   }
               }
               if(categoryProducts.isEmpty()){
                   System.out.println("No products found in category: " + category);
               }
               return categoryProducts;
     }
     public List<Product> getProductsByPriceRange(double min_price, double max_price){
         List<Product> resultList=new ArrayList<>();
          for(Product product: productList){
              if(product.getPrice()>=min_price && product.getPrice()<=max_price){
                    resultList.add(product);
              }
          }
          return resultList;
     }

}
