package com.bin.webase.core.context;

import java.util.List;

public interface IBranchRepository<T> {
    List<T> listByBranchId(Long branchId);
}
