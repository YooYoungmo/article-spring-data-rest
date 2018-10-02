package ymyoo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ymyoo.item.Item;
import ymyoo.item.ItemRepository;

import java.util.Optional;

@RepositoryRestController
public class ProductController {
  private ProductRepository productRepository;
  private ItemRepository itemRepository;

  @Autowired
  public void setProductRepository(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Autowired
  public void setItemRepository(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @RequestMapping(path = "/products/{id}/change", method = { RequestMethod.PUT })
  public @ResponseBody
  ResponseEntity<?> appendDataSource(@PathVariable("id") Long id) {
    Optional<Product> find = productRepository.findById(id);
    if(find.isPresent()) {
      Product product = find.get();
      product.setName("changed..");
    }

    Item item = new Item("new item..");
    itemRepository.save(item);

    return ResponseEntity.noContent().build();
  }
}
