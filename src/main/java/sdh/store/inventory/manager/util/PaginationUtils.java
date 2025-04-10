package sdh.store.inventory.manager.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.function.Supplier;

public class PaginationUtils {

    public static <T> PageInfo<T> toPageInfo(int page, int size, Supplier<List<T>> dataFetcher) {
        PageHelper.startPage(page, size);
        List<T> data = dataFetcher.get();
        return new PageInfo<T>(data);
    }
}
