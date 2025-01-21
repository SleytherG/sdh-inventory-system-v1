package sdh.store.inventory.manager.util;

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
public class ListPageableDTO<T> {

    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
    private boolean isFirstPage;
    private boolean isLastPage;

    public ListPageableDTO(PageInfo<T> pageInfo) {
        this.content = pageInfo.getList();
        this.currentPage = pageInfo.getPageNum();
        this.totalPages = pageInfo.getPages();
        this.totalItems = pageInfo.getTotal();
        this.pageSize = pageInfo.getPageSize();
        this.isFirstPage = pageInfo.isIsFirstPage();
        this.isLastPage = pageInfo.isIsLastPage();
    }
}
