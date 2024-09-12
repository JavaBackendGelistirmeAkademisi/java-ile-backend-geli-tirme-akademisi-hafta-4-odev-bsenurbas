import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        productManagement productManagement = new productManagement();

        try {
            productManagement.loadProducts();
        } catch (IOException e) {
            System.out.println("Error loading products" + e.getMessage());
        }

        Product newProduct = new Product("Laptop",10,15000);
        productManagement.addProduct(newProduct);

        Customer customer = new Customer("Buse Nur", "busenur@example.com");
        Order order = new Order(customer);
        order.addProduct(newProduct);
        order.saveOrderToFile();

        try{
            productManagement.updateStock("Laptop", 8);
        }catch (ProductNotFoundException e){
            System.out.println("Product not found" + e.getMessage());
        }
    }
}