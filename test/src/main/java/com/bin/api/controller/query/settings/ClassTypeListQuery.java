package com.bin.api.controller.query.settings;

import com.bin.api.controller.query.settings.vo.ClassTypeVo;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.repository.view.BranchView;
import com.bin.webase.core.query.FunctionQuery;
import com.google.common.collect.Lists;

import java.util.List;

public class ClassTypeListQuery extends FunctionQuery<List<ClassTypeVo>> {

    public ClassTypeListQuery() {
        super();
        WebSession webSession = getToken(WebSession.class);
        BranchView branchView = new BranchView(webSession.getBranchId());
        List<ClassType> classTypes = branchView.listClassType();
        List<ClassTypeVo> result = Lists.newArrayList();
        for (ClassType classType : classTypes) {
            result.add(new ClassTypeVo(classType));
        }
        setData(result);
    }
}
