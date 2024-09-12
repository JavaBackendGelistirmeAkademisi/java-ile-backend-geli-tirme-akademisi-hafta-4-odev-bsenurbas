import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Customer {
   private String name;
   private String email;

   public Customer(String name, String email){
       this.name = name;
       this.email = email;
   }

   public String getName(){
       return name;
   }

   public String getEmail(){
       return email;
   }
}

class Order {
    private Customer customer;
    private List<Product> products;

    public Order(Customer customer){
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> getProducts(){
        return products;
    }

    public void saveOrderToFile(){
        String fileName = "order_" + customer.getName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Customer: " + customer.getName() + " Email: " + customer.getEmail());
            writer.newLine();
            writer.write("Products:");
            writer.newLine();
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Error saving order to file" + e.getMessage());
        }
    }
}