package com.bin.api.dao.mybatis.base;

import com.bin.api.dao.mybatis.model.EmployeeFunction;
import com.bin.api.dao.mybatis.model.EmployeeFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    long countByExample(EmployeeFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int deleteByExample(EmployeeFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int insert(EmployeeFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int insertSelective(EmployeeFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    List<EmployeeFunction> selectByExample(EmployeeFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    EmployeeFunction selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EmployeeFunction record, @Param("example") EmployeeFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EmployeeFunction record, @Param("example") EmployeeFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EmployeeFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pl_employee_function
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EmployeeFunction record);
}