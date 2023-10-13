package webase.domain.container;


import com.bin.webase.domain.entity.DbDomain;

import java.util.List;

/**
 * @author yanbin
 */
public interface ISequence {


    void init(IRepository repository);

    Long newKey(DbDomain dbDomain);


    /**
     * 获取一个表一组序列
     *
     * @param size 长度
     * @return 一组id
     */
    List<Long> newKeys(DbDomain domain, Long size);

    Long newKey(Class clazz);

}
