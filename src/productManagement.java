import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String productName;
    private int stock;
    private double price;

    public Product(String productName, int stock, double price) {
        this.productName = productName;
        this.stock = stock;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return "Product Name: " + productName + " Stock: " + stock + " Price: " + price;
    }
}

class productManagement {
    private List<Product> productList = new ArrayList<>();
    private static final String FILE_NAME = "product.txt";

    public void loadProducts() throws IOException{
        File file = new File(FILE_NAME);
        if (!file.exists()){
            System.out.println("File not found, creating new file");
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null){
                String [] productData = line.split(",");
                Product product = new Product(productData[0], Integer.parseInt(productData[1]), Double.parseDouble(productData[2]));
                productList.add(product);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error reading file" + e.getMessage());
        }
    }

    public void addProduct(Product product){
        productList.add(product);
        writeProductsToFile();
    }

    public void writeProductsToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Product product : productList){
                writer.write(product.toString());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Error writing to file" + e.getMessage());
        }
    }

    public void updateStock(String productName, int newStock) throws ProductNotFoundException{
        boolean found = false;
        for (Product product : productList){
            if (product.getProductName().equals(productName)){
                product.setStock(newStock);
                found = true;
                writeProductsToFile();
                break;
            }
        }
        if (!found){
            throw new ProductNotFoundException("Product not found"+ productName);
        }
    }
}

class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message){
        super(message);
    }
}
