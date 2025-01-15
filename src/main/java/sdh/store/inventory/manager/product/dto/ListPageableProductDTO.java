package sdh.store.inventory.manager.product.dto;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPageableProductDTO {

    private List<ProductDTO> products;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    public ListPageableProductDTO(PageInfo<ProductDTO> pageInfo) {
        this.products = pageInfo.getList();
        this.currentPage = pageInfo.getPageNum();
        this.totalPages = pageInfo.getPages();
        this.totalItems = pageInfo.getTotal();
    }



}
