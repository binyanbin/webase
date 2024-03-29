package com.bin.api.dao.mybatis.base;

import com.bin.api.dao.mybatis.model.Branch;
import com.bin.api.dao.mybatis.model.BranchExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BranchMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    long countByExample(BranchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int deleteByExample(BranchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int insert(Branch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int insertSelective(Branch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    List<Branch> selectByExample(BranchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    Branch selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Branch record, @Param("example") BranchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Branch record, @Param("example") BranchExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Branch record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_branch
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Branch record);
}