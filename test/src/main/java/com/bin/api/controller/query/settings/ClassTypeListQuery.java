package com.bin.api.controller.query.settings;

import com.bin.api.controller.query.settings.vo.ClassTypeVo;
import com.bin.api.dao.mybatis.model.ClassType;
import com.bin.api.dao.repository.view.BranchView;
import com.bin.api.operate.domain.cache.WebSession;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.query.Query;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTypeListQuery extends Query<List<ClassTypeVo>, NoParam> {


    @Override
    protected List<ClassTypeVo> getData(NoParam param) {
        WebSession webSession = getToken(WebSession.class);
        BranchView branchView = new BranchView(webSession.getBranchId());
        List<ClassType> classTypes = branchView.listClassType();
        List<ClassTypeVo> result = Lists.newArrayList();
        for (ClassType classType : classTypes) {
            result.add(new ClassTypeVo(classType));
        }
        return result;
    }
}
